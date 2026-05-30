from functools import lru_cache
from typing import List

from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    model_config = SettingsConfigDict(env_file=".env", env_file_encoding="utf-8", extra="ignore")

    ai_plan_host: str = "0.0.0.0"
    ai_plan_port: int = 8092

    catalog_service_base_url: str = "http://localhost:8083"
    plan_service_base_url: str = "http://localhost:8086"

    jwt_secret: str = ""
    jwt_algorithm: str = "HS256"

    # LLM — any OpenAI-compatible endpoint (OpenRouter, OpenAI, Groq, 9Router, vLLM…).
    # Defaults target a local 9Router proxy with a combo named "Mravel".
    llm_api_key: str = "9router-local"
    llm_base_url: str = "http://localhost:20128/v1"
    llm_model: str = "Mravel"
    llm_extractor_model: str = ""  # falls back to llm_model
    # Comma-separated models to try when the primary returns 429/402/5xx, e.g.
    # "kc/deepseek/deepseek-chat,kc/google/gemini-2.5-flash". Adds resilience when
    # one provider is rate-limited or out of credits.
    llm_fallback_models: str = ""
    llm_app_name: str = "Mravel AI Plan Agent"
    llm_app_url: str = ""
    # Max output tokens for the agent turn. Dense multi-day itineraries (a single
    # finalize_draft / propose_plan_edits call with 8-12 rich activities × several
    # days) need a large budget; 2048 truncated the JSON and dropped activities.
    llm_max_tokens: int = 8000

    # Web search fallback for prices / venues missing from the catalog.
    # Empty api key → disabled (agent falls back to heuristic pricing).
    web_search_provider: str = "tavily"
    web_search_api_key: str = ""

    # Public origin of the Mravel web app, used to build clickable links to
    # catalog detail pages in chat (e.g. "https://mravel.vn"). Empty → relative
    # paths like "/hotels/<slug>", which the browser resolves against the current
    # origin (the SPA) so they work in-app without extra config.
    public_web_base_url: str = ""

    cors_origins: str = "http://localhost:5173,http://localhost:8080"
    http_timeout_seconds: float = 30.0

    # ----- Session storage -----
    # Backend: "mongo" (recommended — structured, per-record upsert, indexed),
    # "file" (legacy single-JSON file), or "memory" (no persistence; tests).
    session_store_backend: str = "mongo"
    # MongoDB — reuse the stack's instance. The project runs on MongoDB Atlas; the
    # catalog data lives in DB `mravel_catalog` (see catalog-service application.yml).
    # Put the real Atlas connection string (it contains credentials) in `.env`, NOT here.
    mongo_uri: str = "mongodb://localhost:27017"
    mongo_db: str = "mravel_catalog"
    session_collection: str = "ai_plan_sessions"
    # On first Mongo start, import sessions from the legacy file (below) if the
    # collection is empty — so existing chat history isn't lost in the switch.
    session_migrate_from_file: bool = True

    # Legacy "file" backend path (also the migration source). Empty → in-memory only.
    session_store_path: str = ".data/sessions.json"

    def cors_origin_list(self) -> List[str]:
        return [o.strip() for o in self.cors_origins.split(",") if o.strip()]

    def has_llm(self) -> bool:
        return bool(self.llm_api_key.strip())

    def effective_extractor_model(self) -> str:
        return self.llm_extractor_model.strip() or self.llm_model

    def fallback_model_list(self) -> List[str]:
        return [m.strip() for m in self.llm_fallback_models.split(",") if m.strip()]


@lru_cache
def get_settings() -> Settings:
    return Settings()
