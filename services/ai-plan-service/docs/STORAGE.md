# AI Plan Service — Data Storage

How and where the AI planner's data is persisted, and how to operate it safely.

## What is stored

The agent keeps **chat sessions** (the MAI conversations). One session contains:

- `session_id`, `user_id`, `status`, `created_at`, `updated_at`
- `messages[]` — the full user/assistant turns
- `constraints`, `draft` — the working trip draft (new-plan mode)
- `plan_id`, `pending_edits[]` — edit mode (the plan being edited + proposed ops)

No credentials or payment data are stored — only conversation/draft state.

## Where it is stored — MongoDB (recommended)

Sessions live in **MongoDB** — the SAME instance the rest of the app uses. The project
runs on **MongoDB Atlas** (cluster `mravel-cluster.i4rm2ea.mongodb.net`) and the catalog
data is in database **`mravel_catalog`** (see `catalog-service/.../application.yml`).
The planner stores its sessions there too, in collection **`ai_plan_sessions`** — so it
shows up in Compass right next to `hotels`, `places`, `restaurants`, etc.

| Setting | Value (this project) | Meaning |
|---|---|---|
| `SESSION_STORE_BACKEND` | `mongo` | `mongo` \| `file` \| `memory` |
| `MONGO_URI` | the Atlas `mongodb+srv://…` string (in **.env** only) | same cluster as catalog |
| `MONGO_DB` | `mravel_catalog` | database name |
| `SESSION_COLLECTION` | `ai_plan_sessions` | collection name |
| `SESSION_MIGRATE_FROM_FILE` | `false` | the local file is test junk — don't import it into Atlas |

### How to find it in MongoDB Compass

1. Connect to the Atlas cluster (`mravel-cluster.i4rm2ea.mongodb.net`).
2. Open database **`mravel_catalog`**.
3. The conversations are in collection **`ai_plan_sessions`** (created automatically the
   first time someone chats with MAI — empty until then). Each document = one chat
   (`_id` = session_id, `user_id`, `messages[]`, `draft`, `plan_id`, timestamps).

```js
// in Compass' MONGOSH for that cluster
use mravel_catalog
db.ai_plan_sessions.countDocuments()
db.ai_plan_sessions.find({ user_id: <yourId> }).sort({ updated_at: -1 })
```

Why Mongo (vs the old single JSON file):

- **No overload** — each session is one document; a save is a single `upsert`, not a
  rewrite of the whole history file. History can grow without slowing writes.
- **Structured + indexed** — indexed by `(user_id, updated_at)` so listing a user's
  history is fast.
- **Consistent with the stack** — no new infrastructure.

Retention: **kept indefinitely** (no TTL). Documents are small; if you ever want to
purge, drop old docs by `updated_at` or add a TTL index later.

### Inspecting / cleaning up

```bash
# count a user's sessions
mongosh "mongodb://localhost:27017/mravel" --eval 'db.ai_plan_sessions.countDocuments()'
# wipe all AI chats (e.g. clear test junk) — irreversible
mongosh "mongodb://localhost:27017/mravel" --eval 'db.ai_plan_sessions.deleteMany({})'
```

## Migration from the legacy file

Earlier the service wrote every session into a single `.data/sessions.json`
(rewritten on every save — the cause of the "no structure / overload" problem, and it
had ballooned to ~200 docs almost entirely from test runs). That local file is treated
as **disposable test junk**, so `SESSION_MIGRATE_FROM_FILE=false` by default — it is NOT
imported into Atlas, giving a clean start. `.data/` is git-ignored; delete it freely.

If you ever DO want to import a real legacy file once: set
`SESSION_MIGRATE_FROM_FILE=true`. On first start (collection empty) it imports the file,
then renames it `*.migrated.json` so it won't re-import later.

## Fallback backends

- `SESSION_STORE_BACKEND=file` — legacy single-JSON file at `SESSION_STORE_PATH`.
- `SESSION_STORE_BACKEND=memory` — no persistence (used by the test suite).
- If `mongo` is selected but unreachable at startup, the service logs a warning and
  falls back to the file backend so local dev still works.

## Security / hygiene

- **Secrets** (`LLM_API_KEY`, `WEB_SEARCH_API_KEY`, `JWT_SECRET`, `MONGO_URI` with
  credentials) live only in `.env`, which is **git-ignored**. Never commit `.env`;
  share `.env.example` (no secret values) instead.
- `.data/` (local session file) is git-ignored.
- Tests run with an in-memory store (`tests/conftest.py`), so they never touch Mongo
  or write local files.

> ⚠️ **Pre-existing risk (worth fixing):** `catalog-service/src/main/resources/application.yml`
> currently has the **Atlas username/password and a Gmail app password hard-coded and
> committed** to git. ai-plan-service deliberately does NOT copy them into source — its
> `MONGO_URI` is read from the git-ignored `.env`. Recommend moving catalog's secrets to
> env vars too (it already supports `SPRING_DATA_MONGODB_URI`) and rotating the exposed
> Atlas + Gmail credentials, since they are in the repo history.

## Note on `~/.claude/projects/.../memory/` (Claude Code memory)

Those files are **Claude Code's own cross-session memory** — the assistant's working
notes about this project. They are **not** part of this application, are **not**
required to run it, and are stored per-user under your OS profile
(`C:\Users\<you>\.claude\…`), outside the repo, so they are never committed. They
contain only architecture/decision notes (audited — no secrets). The durable project
knowledge that matters lives in the repo: this file, `README`s, and `project_context/`.
You may delete the `.claude` notes at any time; the only effect is the assistant loses
that recall on the next session.
