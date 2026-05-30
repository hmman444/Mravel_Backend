from functools import lru_cache

from fastapi import Depends

from app.agent.orchestrator import AgentOrchestrator
from app.clients.catalog_client import CatalogClient
from app.clients.plan_client import PlanClient
from app.clients.web_search_client import WebSearchClient
from app.config import get_settings
from app.llm.base import LLMClient
from app.llm.factory import build_llm_client
from app.services.approval_service import ApprovalService
from app.services.constraint_extractor import ConstraintExtractor
from app.services.draft_composer import DraftComposer
from app.services.edit_service import EditService
from app.services.session_store import InMemorySessionStore, get_session_store


# Singletons. `get_settings()` is itself `@lru_cache`d, so we read it here
# instead of taking it as a parameter — pydantic `Settings` is not hashable
# and would break `@lru_cache`'s key computation.
@lru_cache
def _catalog_client() -> CatalogClient:
    return CatalogClient(get_settings())


@lru_cache
def _plan_client() -> PlanClient:
    return PlanClient(get_settings())


@lru_cache
def _web_search_client() -> WebSearchClient:
    return WebSearchClient(get_settings())


@lru_cache
def _llm_client() -> LLMClient:
    return build_llm_client(get_settings())


def get_catalog_client() -> CatalogClient:
    return _catalog_client()


def get_plan_client() -> PlanClient:
    return _plan_client()


def get_web_search_client() -> WebSearchClient:
    return _web_search_client()


def get_llm_client() -> LLMClient:
    return _llm_client()


def get_constraint_extractor(llm: LLMClient = Depends(get_llm_client)) -> ConstraintExtractor:
    return ConstraintExtractor(llm)


def get_draft_composer(catalog: CatalogClient = Depends(get_catalog_client)) -> DraftComposer:
    return DraftComposer(catalog)


def get_agent_orchestrator(
    llm: LLMClient = Depends(get_llm_client),
    catalog: CatalogClient = Depends(get_catalog_client),
    composer: DraftComposer = Depends(get_draft_composer),
    plan_client: PlanClient = Depends(get_plan_client),
    web_search: WebSearchClient = Depends(get_web_search_client),
) -> AgentOrchestrator:
    return AgentOrchestrator(
        llm,
        catalog,
        composer,
        plan_client=plan_client,
        web_search=web_search,
        web_base_url=get_settings().public_web_base_url,
    )


def get_approval_service(plan_client: PlanClient = Depends(get_plan_client)) -> ApprovalService:
    return ApprovalService(plan_client)


def get_edit_service(plan_client: PlanClient = Depends(get_plan_client)) -> EditService:
    return EditService(plan_client)


def get_store() -> InMemorySessionStore:
    return get_session_store()
