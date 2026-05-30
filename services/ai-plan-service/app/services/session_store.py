"""Session store.

Two implementations share the same interface:
  * `InMemorySessionStore`     — dict + RLock. Default for tests.
  * `FileBackedSessionStore`   — JSONL file. Survives service restart. Used in
                                  production / dev. Atomic via rewriting a
                                  temp file then `os.replace()`.

Sessions are small (~few KB each), the user base is single-tenant per-process,
and we never need to query by anything other than session_id. A file is fine
for the current scope; swap to Redis/Postgres when concurrency demands it.
"""

import json
import logging
import os
import tempfile
from datetime import datetime, timezone
from pathlib import Path
from threading import RLock
from typing import Dict, List, Optional

from app.core.errors import DomainError
from app.models.session import PlanSession

logger = logging.getLogger("ai_plan.session_store")


class InMemorySessionStore:
    def __init__(self) -> None:
        self._lock = RLock()
        self._sessions: Dict[str, PlanSession] = {}

    def create(self, user_id: int) -> PlanSession:
        session = PlanSession(user_id=user_id)
        with self._lock:
            self._sessions[session.session_id] = session
        return session

    def get(self, session_id: str, user_id: int) -> PlanSession:
        with self._lock:
            session = self._sessions.get(session_id)
        if session is None:
            raise DomainError(f"Session not found: {session_id}")
        if session.user_id != user_id:
            raise DomainError("Session does not belong to current user")
        return session

    def save(self, session: PlanSession) -> PlanSession:
        session.updated_at = datetime.now(timezone.utc)
        with self._lock:
            self._sessions[session.session_id] = session
        return session

    def list_by_user(self, user_id: int) -> List[PlanSession]:
        """All of a user's sessions, newest activity first. Used for the AI chat
        history list so the user can resume any past conversation."""
        with self._lock:
            owned = [s for s in self._sessions.values() if s.user_id == user_id]
        owned.sort(key=lambda s: s.updated_at, reverse=True)
        return owned


class FileBackedSessionStore(InMemorySessionStore):
    """In-memory store + JSON-file persistence.

    Reads the file once at startup, writes on every `save()`. Single-process
    safe; multi-process callers should switch to Redis.
    """

    def __init__(self, file_path: Path) -> None:
        super().__init__()
        self._file_path = file_path
        self._file_path.parent.mkdir(parents=True, exist_ok=True)
        self._load()

    def _load(self) -> None:
        if not self._file_path.exists():
            return
        try:
            raw = self._file_path.read_text(encoding="utf-8")
        except OSError as exc:
            logger.warning("session file unreadable: %s — starting empty", exc)
            return
        if not raw.strip():
            return
        try:
            data = json.loads(raw)
        except json.JSONDecodeError as exc:
            # Don't crash on a corrupt file — back it up and start fresh.
            logger.warning("session file corrupt (%s); rotating to .bad", exc)
            try:
                self._file_path.rename(self._file_path.with_suffix(".bad"))
            except OSError:
                pass
            return
        loaded = 0
        for session_dict in data.get("sessions", []):
            try:
                session = PlanSession.model_validate(session_dict)
            except Exception as exc:  # noqa: BLE001
                logger.warning("skip invalid persisted session: %s", exc)
                continue
            self._sessions[session.session_id] = session
            loaded += 1
        if loaded:
            logger.info("loaded %d sessions from %s", loaded, self._file_path)

    def _flush(self) -> None:
        with self._lock:
            snapshot = [
                json.loads(s.model_dump_json()) for s in self._sessions.values()
            ]
        payload = {"version": 1, "sessions": snapshot}
        # Atomic write: tmp file in the same dir + os.replace.
        tmp_fd, tmp_path = tempfile.mkstemp(
            prefix=".sessions-", suffix=".json.tmp", dir=str(self._file_path.parent)
        )
        try:
            with os.fdopen(tmp_fd, "w", encoding="utf-8") as fh:
                json.dump(payload, fh, ensure_ascii=False, default=str)
            os.replace(tmp_path, self._file_path)
        except OSError as exc:
            logger.warning("session flush failed: %s", exc)
            try:
                os.unlink(tmp_path)
            except OSError:
                pass

    def create(self, user_id: int) -> PlanSession:
        session = super().create(user_id)
        self._flush()
        return session

    def save(self, session: PlanSession) -> PlanSession:
        result = super().save(session)
        self._flush()
        return result


class MongoSessionStore:
    """MongoDB-backed store — the recommended backend.

    Each session is one document keyed by `_id = session_id`, so a save is a single
    upsert (no full-file rewrite → no overload as history grows). Indexed by user for
    fast history listing. Reuses the stack's Mongo instance.
    """

    def __init__(
        self,
        uri: str,
        db_name: str,
        collection: str,
        migrate_path: Optional[Path] = None,
    ) -> None:
        # Imported here so the dependency is only needed when this backend is used.
        from pymongo import ASCENDING, DESCENDING, MongoClient

        self._client = MongoClient(uri, serverSelectionTimeoutMS=3000, tz_aware=True)
        # Fail fast if Mongo is unreachable so the factory can fall back.
        self._client.admin.command("ping")
        self._col = self._client[db_name][collection]
        self._col.create_index([("user_id", ASCENDING), ("updated_at", DESCENDING)])
        if migrate_path is not None:
            self._migrate_from_file(migrate_path)

    @staticmethod
    def _to_doc(session: PlanSession) -> dict:
        doc = json.loads(session.model_dump_json())
        doc["_id"] = session.session_id
        return doc

    @staticmethod
    def _from_doc(doc: dict) -> PlanSession:
        doc.pop("_id", None)
        return PlanSession.model_validate(doc)

    def _migrate_from_file(self, path: Path) -> None:
        """One-time import of the legacy JSON file when the collection is still empty,
        so switching backends doesn't drop existing chat history."""
        if self._col.estimated_document_count() > 0 or not path.exists():
            return
        try:
            data = json.loads(path.read_text(encoding="utf-8"))
        except (OSError, json.JSONDecodeError) as exc:
            logger.warning("session migration skipped (unreadable file): %s", exc)
            return
        docs = []
        for raw in data.get("sessions", []):
            try:
                docs.append(self._to_doc(PlanSession.model_validate(raw)))
            except Exception as exc:  # noqa: BLE001
                logger.warning("skip invalid session during migration: %s", exc)
        if docs:
            self._col.insert_many(docs, ordered=False)
            logger.info("migrated %d sessions from %s into Mongo", len(docs), path)
        # Retire the source so a later manual wipe of the collection doesn't trigger a
        # re-import on the next restart (migration is genuinely one-time).
        try:
            path.rename(path.with_suffix(".migrated.json"))
        except OSError:
            pass

    def create(self, user_id: int) -> PlanSession:
        session = PlanSession(user_id=user_id)
        self._col.insert_one(self._to_doc(session))
        return session

    def get(self, session_id: str, user_id: int) -> PlanSession:
        doc = self._col.find_one({"_id": session_id})
        if doc is None:
            raise DomainError(f"Session not found: {session_id}")
        session = self._from_doc(doc)
        if session.user_id != user_id:
            raise DomainError("Session does not belong to current user")
        return session

    def save(self, session: PlanSession) -> PlanSession:
        from datetime import datetime, timezone

        session.updated_at = datetime.now(timezone.utc)
        self._col.replace_one({"_id": session.session_id}, self._to_doc(session), upsert=True)
        return session

    def list_by_user(self, user_id: int) -> List[PlanSession]:
        from pymongo import DESCENDING

        cursor = self._col.find({"user_id": user_id}).sort("updated_at", DESCENDING)
        return [self._from_doc(doc) for doc in cursor]


_store_singleton: Optional[InMemorySessionStore] = None


def get_session_store() -> InMemorySessionStore:
    global _store_singleton
    if _store_singleton is not None:
        return _store_singleton

    from app.config import get_settings

    settings = get_settings()
    backend = (settings.session_store_backend or "mongo").strip().lower()
    path = (settings.session_store_path or "").strip()

    def _file_or_memory():
        if path:
            logger.info("session store: file-backed at %s", path)
            return FileBackedSessionStore(Path(path))
        logger.info("session store: in-memory (no SESSION_STORE_PATH)")
        return InMemorySessionStore()

    if backend == "memory":
        _store_singleton = InMemorySessionStore()
        logger.info("session store: in-memory")
    elif backend == "file":
        _store_singleton = _file_or_memory()
    else:  # "mongo" (default)
        try:
            migrate = Path(path) if (settings.session_migrate_from_file and path) else None
            _store_singleton = MongoSessionStore(
                settings.mongo_uri, settings.mongo_db, settings.session_collection, migrate
            )
            logger.info(
                "session store: mongo %s/%s", settings.mongo_db, settings.session_collection
            )
        except Exception as exc:  # noqa: BLE001 — pymongo/connection errors
            logger.warning("mongo session store unavailable (%s) — falling back to file", exc)
            _store_singleton = _file_or_memory()
    return _store_singleton


def reset_session_store_for_tests() -> None:
    """Test helper. Drop the singleton so each test starts fresh."""
    global _store_singleton
    _store_singleton = None
