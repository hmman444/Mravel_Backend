# ai-plan-service — MAI (Mravel AI)

Python (FastAPI) orchestration service behind **MAI**, the Mravel AI travel assistant.
It owns chat sessions, constraint extraction, catalog-backed retrieval, the LLM
agent loop, the draft→approval workflow that writes real plans into `plan-service`,
and the in-chat plan **editor**.

> **Draft-first by design.** No write to a real plan fires until the user explicitly
> approves (`/approve`) or applies (`/apply-edits`). The agent only *proposes*.

---

## 1. The big picture

```
Frontend (React)                 Gateway            ai-plan-service (FastAPI :8092)
─────────────────                ────────           ────────────────────────────────
FloatingChatWidget ─┐                               ┌─ AgentOrchestrator (LLM loop)
ChatPage (/chat)    ├─ SSE ─► /api/ai-plan/** ─────► ┤   ├─ tools ─► catalog-service (read)
useAIPlanSession    │   (gateway :8080)              │   ├─ web_search ─► Tavily (prices)
AIPlanPanel         ┘                               │   └─ writes ─► plan-service (approve/edit)
                                                    └─ sessions ─► MongoDB Atlas
                                                                   (mravel_catalog.ai_plan_sessions)
```

- **Recommendation source:** `catalog-service` (public, read-only) — hotels / restaurants / places.
- **Write source of truth:** `plan-service` (authenticated, idempotent, RBAC-enforced).
- **AI provider:** any OpenAI-compatible endpoint (OpenRouter / 9Router / OpenAI / Groq / Ollama).
- **Session store:** MongoDB (same Atlas cluster the app uses). See [`docs/STORAGE.md`](docs/STORAGE.md).
- **Live prices / freshness:** Tavily web search, only for things the catalog lacks.

---

## 2. How to run it

```powershell
cd D:\LUAN\22110372\Mravel\Mravel_Backend\services\ai-plan-service
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -e ".[dev]"        # installs fastapi, openai, pymongo, pytest…
copy .env.example .env         # then edit .env (see §3)
python -m app.main             # uvicorn on port 8092 (single process, no reload)
```

- Health: `http://localhost:8092/api/ai-plan/health` (direct) · `http://localhost:8080/api/ai-plan/health` (via gateway).
- Needs running: `auth-service`, `catalog-service`, `plan-service`, `Mravel_Gateway`, and **MongoDB** (Atlas — same as catalog).
- On startup the log prints the chosen store, e.g. `session store: mongo mravel_catalog/ai_plan_sessions`.

Run tests (hermetic — in-memory store, no Mongo/LLM needed):

```powershell
pytest -q          # 45 tests
```

---

## 3. Configuration (`.env`)

Everything swappable lives in `.env` (git-ignored). Key knobs:

| Var | Meaning |
|---|---|
| `LLM_API_KEY` | Empty → **stub mode** (regex + deterministic composer, no agent). Set → **agent mode**. |
| `LLM_BASE_URL` / `LLM_MODEL` | Switch provider/model without code change (Chat Completions API). |
| `LLM_EXTRACTOR_MODEL` | Cheaper model for the per-message constraint extraction (optional). |
| `LLM_FALLBACK_MODELS` | Comma-list tried on 429/402/5xx from the primary model. |
| `WEB_SEARCH_API_KEY` | Tavily key. Empty → web search disabled (agent says "chưa xác minh"). |
| `PUBLIC_WEB_BASE_URL` | Empty → relative catalog links (`/hotels/<slug>`); set to absolute origin if needed. |
| `SESSION_STORE_BACKEND` | `mongo` (default) \| `file` \| `memory`. |
| `MONGO_URI` / `MONGO_DB` / `SESSION_COLLECTION` | Atlas cluster, `mravel_catalog`, `ai_plan_sessions`. |
| `JWT_SECRET` | Empty → JWT decoded WITHOUT signature check (plan-service re-validates writes). Set to harden. |

Current real provider (in `.env`): a local **9Router** proxy → OpenRouter free model
`openrouter/openai/gpt-oss-120b:free` (a smaller model — prompts are written defensively/explicitly for it).

---

## 4. The four behaviours of MAI

The same agent serves four jobs, decided per message:

1. **New-plan drafting** (no plan bound) — gather destination/dates/people → search the
   catalog → build a dense multi-day **draft** (`finalize_draft`) → user reviews → **Duyệt**
   → real plan created in plan-service.
2. **Discovery / Q&A** ("khách sạn nào trên web?", "cho link địa điểm Đà Nẵng") — searches
   the catalog FIRST and replies with **clickable Mravel links** (`mravel_url`). Only if the
   catalog is empty does it offer `available_on_mravel` (nearby city) or, as a last resort,
   a labelled `web_search` link. Never invents venues or pastes external links from memory.
3. **Plan editing** — when the user references a plan (`/plans/43`, "kế hoạch số 43") the
   session **binds** to that plan (after a permission check) and the agent **proposes** edits
   (`propose_plan_edits`); the user presses **Áp dụng** to write them.
4. **Freshness-aware answers** — any time-sensitive fact (giá vé, giờ mở cửa, thuê xe) MUST be
   looked up via `web_search` with the **current year** (injected into the prompt) and the
   source URL cited. Never quoted from training memory.

**Stub vs agent:** with `LLM_API_KEY` empty, the service still works in a deterministic
**stub** mode (regex constraint extraction + `DraftComposer` builds a draft straight from the
catalog). With a key set, the **agent loop** (≤ `MAX_ITERATIONS=10`) drives everything; on any
error/exhaustion it falls back to the stub composer so the user always gets a response.

---

## 5. Tools exposed to the LLM

Plan mode (`tool_definitions`):

| Tool | What it does |
|---|---|
| `set_constraints(...)` | Record/confirm trip facts (the agent's memory). |
| `search_hotels(location, max_price_vnd?, min_star_rating?)` | Catalog hotels (price/star/rating/coords + `mravel_url`). |
| `search_restaurants(location, cuisine?)` | Catalog restaurants. |
| `search_places(destination?, query?)` | Catalog POIs. `destination` → faceted slug lookup (accent-proof); `query` → free text WITH diacritics. |
| `route_legs(stops[])` | Real distance/time between coords (haversine in `geo.py`). |
| `web_search(query)` | Tavily — live prices/info the catalog lacks. Current year + cite url. |
| `view_my_plans()` | List the user's existing plans (read-only). |
| `finalize_draft(...)` | **Terminal** — commits the multi-day draft for review. |

Edit mode (`edit_tool_definitions`): the search/web/route tools **plus** `get_current_plan`
and the terminal `propose_plan_edits(operations[])`. Edit ops: `update_card`, `create_card`,
`delete_card`, `move_card`, `rename_list`, **`delete_list`** (xoá ngày), `add_day`, `update_plan`.

Empty-location resilience: a hotel/restaurant search that returns 0 broadens and returns
`{no_results_for_location, available_on_mravel[]}` so the agent offers what Mravel *does* have.

---

## 6. HTTP API

All endpoints require `Authorization: Bearer <jwt>` and return the shared
`ApiResponse { success, message, data, timestamp }`.

| Endpoint | Purpose |
|---|---|
| `POST /api/ai-plan/sessions` | Create a session. Optional `plan_id` → edit mode (permission-checked at creation). |
| `GET /api/ai-plan/sessions` | **History list** for the user (id, title, preview, updated_at) — powers the conversation list. |
| `GET /api/ai-plan/sessions/{id}` | Full session view (constraints, messages, draft, plan_id, pending_edits). |
| `POST /api/ai-plan/sessions/{id}/messages` | Non-stream turn (new-plan only). |
| `POST /api/ai-plan/sessions/{id}/messages/stream` | **SSE** turn — the path the FE uses. Detects a referenced plan → routes to the edit stream. |
| `POST /api/ai-plan/sessions/{id}/regenerate[/stream]` | Re-run with a revision hint. |
| `POST /api/ai-plan/sessions/{id}/approve` | Materialise the draft as a real plan (idempotent). |
| `POST /api/ai-plan/sessions/{id}/apply-edits` | Apply the proposed edits to the plan (re-checks permission). |
| `GET /api/ai-plan/health` | Health probe. |

**SSE event vocabulary:** `session`, `thinking`, `tool_call`, `tool_result`, `assistant_message`,
`draft_ready`, `edit_proposal`, `constraints_updated`, `error`, `done`.

---

## 7. Data flow (streamed message)

```
POST /sessions/{id}/messages/stream  (FE: useAIPlanSession → streamMessage)
  store.get(session) ; append user msg ; save                       (MongoDB upsert)
  _extract_plan_id(content)?  → permission-check → bind plan_id      (→ edit mode)
  ── edit mode ───────────────────────────────────────────────
     orchestrator.edit_stream(plan_id):
        get_board(plan-service) → gate on myRole(OWNER/EDITOR)
        loop ≤10: LLM.chat_with_tools(EDIT_PROMPT, msgs, edit_tools)
           run search/web tools  ·  propose_plan_edits → validate ids → edit_proposal
     (user presses Áp dụng) → POST /apply-edits → EditService → plan-service writes
  ── new-plan / discovery ───────────────────────────────────
     ConstraintExtractor.update(...)          (regex baseline + optional LLM refine)
     orchestrator.plan_stream():
        loop ≤10: LLM.chat_with_tools(SYSTEM_PROMPT, msgs, tools)
           set_constraints / search_* / web_search / route_legs / finalize_draft
        finalize_draft → PlanDraft → draft_ready
     (user presses Duyệt) → POST /approve → ApprovalService → plan-service create_plan + cards
  persist assistant msg + draft/edits ; SSE `done`                   (MongoDB upsert)
```

The primer fed to the model each turn carries: **today's date** (freshness), known
constraints (plan mode) or the **board summary** with list_id/card_id (edit mode — those ids
are internal; the agent must NOT show them to the user).

---

## 8. Where data is stored

- **Chat sessions → MongoDB**, DB `mravel_catalog`, collection **`ai_plan_sessions`** (one
  document per conversation: `_id`=session_id, `user_id`, `messages[]`, `constraints`,
  `draft`, `plan_id`, `pending_edits`, timestamps). One `upsert` per save (no file rewrite),
  indexed `(user_id, updated_at)`, kept indefinitely. Full details + Compass how-to:
  [`docs/STORAGE.md`](docs/STORAGE.md).
- **Real plans → plan-service** (Postgres) via the approval/edit flow — the source of truth.
- **Frontend** keeps only the *active* session id in `localStorage` (`mravel_ai_plan_session_id`)
  to rehydrate on return.
- Catalog data is never copied here — it's fetched live from catalog-service per request.

---

## 9. Security model

- **Auth:** every endpoint requires the bearer JWT (forwarded to plan-service on writes).
  `JWT_SECRET` empty → token decoded without signature verification (dev default); plan-service
  re-validates against auth-service on every write, so writes are safe. Set `JWT_SECRET` to also
  harden ai-plan-service's own session ownership.
- **Edit authorization:** `plan-service` enforces `EDITOR` on every board mutation (source of
  truth). ai-plan-service adds an *early* gate (reads `BoardResponse.myRole`) at session
  create, in `edit_stream`, and again at `apply-edits` — a viewer gets a friendly refusal
  instead of crafting an un-appliable proposal.
- **Secrets** live only in git-ignored `.env`. `.data/` (legacy file) is git-ignored too.
  ⚠️ Note: `catalog-service` still has its Atlas + Gmail credentials hard-coded in a committed
  `application.yml` — recommend moving those to env vars and rotating them.

---

## 10. Frontend integration

- **`useAIPlanSession(planId)`** ([Mravel_Frontend/src/features/aiPlan/hooks/](../../../Mravel_Frontend/src/features/aiPlan/hooks/useAIPlanSession.js)) —
  owns the SSE stream, draft/edit state, history (`sessions`, `refreshSessions`, `switchSession`),
  and `boundPlanId` (flips to edit mode when the backend binds a plan).
- **`AIPlanPanel` / `AIPlanPanelView`** — the chrome-less chat UI (messages render Markdown via
  `marked`, so catalog links are clickable; a history overlay lists past chats; "Hội thoại mới"
  starts a new one).
- **`FloatingChatWidget`** — pins **MAI (Mravel AI)** at the top of the floating chat on every
  page. It *owns* the session so an answer keeps streaming + saves even if the popup is closed,
  and shows a **red dot** when a reply arrives while closed.
- **`ChatPage` (/chat)** — same MAI pinned in the sidebar (sentinel `PLANNER_CONV_ID="planner"`).
- The old "Lập kế hoạch với AI" button on the plan board was removed; MAI is now the single,
  global assistant.

---

## 11. File map (what lives where)

```
app/
  main.py                       FastAPI app, CORS, routers, health
  config.py                     all env settings (LLM, Mongo, web search, CORS…)
  api/
    ai_plan.py                  ALL endpoints + plan-id detection + permission gate (_assert_can_edit)
    schemas.py                  request/response models (SessionView, SessionSummary…)
    dependencies.py             DI wiring (singletons: llm, catalog, plan, store, orchestrator)
  agent/
    orchestrator.py             the agent loop + the two SYSTEM PROMPTS (plan & edit) + primers
    tools.py                    tool definitions + ToolDispatcher (catalog/web/route) + summaries
    edits.py                    EditOperation model, validation, board_summary, op summaries
  llm/
    base.py                     LLMClient interface (chat_with_tools, extract_constraints)
    openai_client.py            OpenAI-compatible impl (+ fallback models)
    stub.py                     offline regex/deterministic impl (no key)
    factory.py                  picks stub vs openai client
  services/
    session_store.py            InMemory / FileBacked / MongoSessionStore + factory
    constraint_extractor.py     hybrid regex + LLM constraint merge
    draft_composer.py           deterministic draft builder (stub path / fallback)
    approval_service.py         draft → plan-service (create_plan + seeded-day cards)
    edit_service.py             proposed ops → plan-service board mutations
    geo.py / pricing.py         haversine routing / price heuristics
  clients/
    catalog_client.py           catalog-service REST (search hotels/restaurants/places + faceted)
    plan_client.py              plan-service REST (board read + idempotent writes)
    web_search_client.py        Tavily client
  models/session.py             PlanSession, Constraints, PlanDraft, ChatMessage, enums
docs/STORAGE.md                 storage + Compass how-to + .claude memory note
tests/                          45 tests (conftest forces in-memory store)
```

---

## 12. Gotchas / things to know

- **Where are my chats?** MongoDB Atlas → `mravel_catalog` → `ai_plan_sessions` (not localhost,
  not a file). Restart the service after editing `.env`. See `docs/STORAGE.md`.
- **"plan 43" vs "kế hoạch 3 ngày"** — only the former binds to a plan; a count/duration is not
  mistaken for an id.
- **The model must never print `card_id`/JSON** in chat — that's plumbing; the UI shows approval
  cards. Enforced in the edit prompt.
- **Small model caveat** — `gpt-oss-120b:free` is modest; prompts are explicit and there are
  deterministic fallbacks. Swap `LLM_MODEL` to a stronger model for better itineraries.
- **The `~/.claude/...` files are Claude Code's notes**, not part of this app — see `docs/STORAGE.md`.
