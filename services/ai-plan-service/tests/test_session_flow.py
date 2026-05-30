"""End-to-end smoke tests with mocked downstream services + mocked LLM.

Two paths under test:
  * Stub-LLM (offline) — orchestrator falls back to deterministic composer.
  * Mock tool-using LLM — orchestrator runs the agent loop, parses finalize_draft.

Run from `Mravel_Backend/services/ai-plan-service`:
    pytest -q
"""

import base64
import json
from typing import Any, Dict, List
from uuid import uuid4

import pytest
from fastapi.testclient import TestClient

from app.api import dependencies as deps
from app.clients.catalog_client import CatalogClient
from app.clients.plan_client import PlanClient
from app.config import get_settings
from app.llm.base import LLMClient, LLMTurn, ToolUse
from app.main import create_app


def _bearer(user_id: int = 42) -> str:
    payload = base64.urlsafe_b64encode(
        json.dumps({"id": user_id, "sub": "test@example.com"}).encode()
    ).rstrip(b"=").decode()
    header = base64.urlsafe_b64encode(b'{"alg":"none","typ":"JWT"}').rstrip(b"=").decode()
    return f"Bearer {header}.{payload}."


class _FakeCatalog(CatalogClient):
    def __init__(self) -> None:
        super().__init__(get_settings())

    async def search_places(self, q, page=0, size=10):
        return [
            {"id": 1, "slug": "place-1", "name": "Bana Hills", "latitude": 16.0, "longitude": 108.0, "avgRating": 4.6, "coverImageUrl": "x"},
            {"id": 2, "slug": "place-2", "name": "Marble Mountains", "latitude": 16.1, "longitude": 108.1, "avgRating": 4.4, "coverImageUrl": "x"},
            {"id": 3, "slug": "place-3", "name": "My Khe Beach", "latitude": 16.2, "longitude": 108.2, "avgRating": 4.7, "coverImageUrl": "x"},
            {"id": 4, "slug": "place-4", "name": "Linh Ung Pagoda", "latitude": 16.3, "longitude": 108.3, "avgRating": 4.5, "coverImageUrl": "x"},
        ]

    async def search_restaurants(self, location, page=0, size=10, **filters):
        return [
            {"id": 11, "slug": "r-1", "name": "Bun Cha", "latitude": 16.0, "longitude": 108.0, "minPricePerPerson": 80000, "avgRating": 4.5},
            {"id": 12, "slug": "r-2", "name": "Seafood House", "minPricePerPerson": 200000, "avgRating": 4.6},
            {"id": 13, "slug": "r-3", "name": "Pho 24", "minPricePerPerson": 60000, "avgRating": 4.3},
            {"id": 14, "slug": "r-4", "name": "BBQ Garden", "minPricePerPerson": 250000, "avgRating": 4.4},
        ]

    async def search_hotels(self, location, page=0, size=10, **filters):
        return [
            {"id": 100, "slug": "hotel-vip", "name": "Beach Resort", "minNightlyPrice": 1500000, "avgRating": 4.7},
        ]


class _FakePlan(PlanClient):
    """Mimics plan-service: createPlan seeds one DAY list per day in the date
    range (+ a TRASH list). Cards are attached to those seeded lists via the board."""

    def __init__(self) -> None:
        super().__init__(get_settings())
        self.created_plan_id = 999
        self.board_lists: List[Dict[str, Any]] = []
        self.cards: List[Dict[str, Any]] = []
        self.renamed: Dict[int, str] = {}
        self._next_list = 1
        self._next_card = 1

    @property
    def day_lists(self) -> List[Dict[str, Any]]:
        return [x for x in self.board_lists if x["type"] == "DAY"]

    async def create_plan(self, bearer_token, body):
        assert bearer_token
        from datetime import date, timedelta

        start = date.fromisoformat(body["startDate"])
        end = date.fromisoformat(body["endDate"])
        days = (end - start).days + 1
        self.board_lists = []
        for i in range(days):
            lid = self._next_list
            self._next_list += 1
            self.board_lists.append({
                "id": lid,
                "title": "Danh sách hoạt động",
                "position": i,
                "type": "DAY",
                "dayDate": (start + timedelta(days=i)).isoformat(),
                "cards": [],
            })
        # TRASH list last.
        self.board_lists.append({
            "id": self._next_list, "title": "Thùng rác", "position": days,
            "type": "TRASH", "dayDate": None, "cards": [],
        })
        self._next_list += 1
        return {"id": self.created_plan_id, "title": body["title"]}

    async def get_board(self, bearer_token, plan_id):
        assert bearer_token
        return {"planId": plan_id, "lists": self.board_lists}

    async def rename_list(self, bearer_token, plan_id, list_id, title):
        self.renamed[list_id] = title
        for x in self.board_lists:
            if x["id"] == list_id:
                x["title"] = title
        return {}

    async def create_card(self, bearer_token, plan_id, list_id, body, idempotency_key=None):
        card_id = self._next_card
        self._next_card += 1
        self.cards.append({"id": card_id, "list_id": list_id, "body": body})
        # Matches plan-service /board/lists/{id}/cards CardDto shape.
        return {"id": card_id, "text": body.get("text"), "listId": list_id}


class _StubLLM(LLMClient):
    """Mimics the offline stub: regex extraction, no tool use."""

    def supports_tool_use(self) -> bool:
        return False

    async def extract_constraints(self, user_message, prior_constraints):
        # Reuse the real StubLLMClient logic via composition.
        from app.llm.stub import StubLLMClient

        return await StubLLMClient().extract_constraints(user_message, prior_constraints)

    async def chat_with_tools(self, system_prompt, messages, tools, max_tokens=2048):
        return LLMTurn(stop_reason="end_turn", text="stub")


class _ToolUsingLLM(LLMClient):
    """Three-turn scripted agent: search_hotels -> search_places -> finalize_draft."""

    def supports_tool_use(self) -> bool:
        return True

    async def extract_constraints(self, user_message, prior_constraints):
        from app.llm.stub import StubLLMClient

        return await StubLLMClient().extract_constraints(user_message, prior_constraints)

    async def chat_with_tools(self, system_prompt, messages, tools, max_tokens=2048):
        # Count past tool messages to decide what to do next.
        tool_messages = [m for m in messages if m.get("role") == "tool"]
        prior_calls = len(tool_messages)

        if prior_calls == 0:
            return self._call("search_hotels", {"location": "da-nang", "max_results": 1})
        if prior_calls == 1:
            return self._call("search_places", {"query": "Da Nang", "max_results": 4})
        if prior_calls == 2:
            return self._call("search_restaurants", {"location": "da-nang", "max_results": 4})
        # Finalize.
        days_payload = []
        for i in range(1, 4):  # 3 days
            days_payload.append({
                "day_index": i,
                "day_date": f"2026-05-{27 + i - 1:02d}",
                "title": f"Ngày {i}",
                "activities": [
                    {
                        "title": f"Khám phá Da Nang ngày {i}",
                        "activity_type": "SIGHTSEEING",
                        "start_time": "09:00",
                        "end_time": "11:00",
                        "duration_minutes": 120,
                        "estimated_cost_vnd": 200000,
                        "reason": "Top-rated place",
                        "recommendation": {
                            "catalog_kind": "PLACE",
                            "catalog_id": 1,
                            "catalog_slug": "place-1",
                            "name": "Bana Hills",
                            "avg_rating": 4.6,
                        },
                    }
                ],
            })
        return self._call(
            "finalize_draft",
            {
                "summary": "Lịch trình 3 ngày Da Nang",
                "destination": "Da Nang",
                "start_date": "2026-05-27",
                "end_date": "2026-05-29",
                "travelers": 2,
                "estimated_total_cost_vnd": 600000,
                "days": days_payload,
                "warnings": [],
            },
        )

    @staticmethod
    def _call(name: str, args: Dict[str, Any]) -> LLMTurn:
        tu_id = f"call_{uuid4().hex[:8]}"
        return LLMTurn(
            stop_reason="tool_use",
            tool_uses=[ToolUse(id=tu_id, name=name, arguments=args)],
            raw_assistant_message={
                "role": "assistant",
                "content": "",
                "tool_calls": [
                    {
                        "id": tu_id,
                        "type": "function",
                        "function": {"name": name, "arguments": json.dumps(args)},
                    }
                ],
            },
        )


def _build_client(llm: LLMClient) -> TestClient:
    fake_catalog = _FakeCatalog()
    fake_plan = _FakePlan()
    app = create_app()

    from app.agent.orchestrator import AgentOrchestrator
    from app.services.approval_service import ApprovalService
    from app.services.constraint_extractor import ConstraintExtractor
    from app.services.draft_composer import DraftComposer

    composer = DraftComposer(fake_catalog)
    app.dependency_overrides[deps.get_catalog_client] = lambda: fake_catalog
    app.dependency_overrides[deps.get_plan_client] = lambda: fake_plan
    app.dependency_overrides[deps.get_llm_client] = lambda: llm
    app.dependency_overrides[deps.get_draft_composer] = lambda: composer
    app.dependency_overrides[deps.get_constraint_extractor] = lambda: ConstraintExtractor(llm)
    app.dependency_overrides[deps.get_agent_orchestrator] = lambda: AgentOrchestrator(
        llm, fake_catalog, composer
    )
    app.dependency_overrides[deps.get_approval_service] = lambda: ApprovalService(fake_plan)

    tc = TestClient(app)
    tc.fake_plan = fake_plan  # type: ignore[attr-defined]
    return tc


@pytest.fixture
def stub_client() -> TestClient:
    return _build_client(_StubLLM())


@pytest.fixture
def agent_client() -> TestClient:
    return _build_client(_ToolUsingLLM())


# ---------- stub path (offline) ----------


def test_stub_path_full_session_flow(stub_client: TestClient) -> None:
    headers = {"Authorization": _bearer()}

    r = stub_client.post("/api/ai-plan/sessions", json={}, headers=headers)
    assert r.status_code == 201
    session_id = r.json()["data"]["session_id"]

    r = stub_client.post(
        f"/api/ai-plan/sessions/{session_id}/messages",
        json={"content": "Mình muốn đi Da Nang từ 27/6 đến 29/6 cho 2 người"},
        headers=headers,
    )
    assert r.status_code == 200, r.text
    body = r.json()["data"]
    assert body["needs_more_info"] is False
    assert len(body["draft"]["days"]) == 3
    # Destination is canonicalised to the proper Vietnamese name from the whitelist.
    assert body["constraints"]["destination"] == "Đà Nẵng"

    r = stub_client.post(f"/api/ai-plan/sessions/{session_id}/approve", headers=headers)
    assert r.status_code == 200
    approval = r.json()["data"]
    assert approval["plan_id"] == 999

    fake_plan: _FakePlan = stub_client.fake_plan  # type: ignore[attr-defined]
    # No phantom lists are created — cards attach to the 3 auto-seeded DAY lists.
    assert len(fake_plan.day_lists) == 3
    assert len(fake_plan.cards) > 0
    # Seeded lists were renamed to the draft's day titles.
    assert fake_plan.renamed


def test_stub_path_missing_constraints_asks_for_more(stub_client: TestClient) -> None:
    headers = {"Authorization": _bearer()}
    r = stub_client.post("/api/ai-plan/sessions", json={}, headers=headers)
    session_id = r.json()["data"]["session_id"]

    r = stub_client.post(
        f"/api/ai-plan/sessions/{session_id}/messages",
        json={"content": "Tôi muốn đi du lịch"},
        headers=headers,
    )
    body = r.json()["data"]
    assert body["needs_more_info"] is True
    assert body["draft"] is None


def test_missing_auth_rejected(stub_client: TestClient) -> None:
    r = stub_client.post("/api/ai-plan/sessions", json={})
    assert r.status_code == 401


# ---------- agent path (tool-using LLM) ----------


def test_agent_path_uses_tools_then_finalizes(agent_client: TestClient) -> None:
    headers = {"Authorization": _bearer()}
    r = agent_client.post("/api/ai-plan/sessions", json={}, headers=headers)
    session_id = r.json()["data"]["session_id"]

    r = agent_client.post(
        f"/api/ai-plan/sessions/{session_id}/messages",
        json={"content": "Mình muốn đi Da Nang 3 ngày cho 2 người"},
        headers=headers,
    )
    assert r.status_code == 200, r.text
    body = r.json()["data"]
    assert body["needs_more_info"] is False
    draft = body["draft"]
    assert draft["destination"] == "Da Nang"
    assert len(draft["days"]) == 3
    # finalize_draft payload was assembled from tool results — recommendation came from search_places.
    first_activity = draft["days"][0]["activities"][0]
    assert first_activity["recommendation"]["name"] == "Bana Hills"


def test_agent_path_regenerate_produces_new_draft(agent_client: TestClient) -> None:
    headers = {"Authorization": _bearer()}
    r = agent_client.post("/api/ai-plan/sessions", json={}, headers=headers)
    session_id = r.json()["data"]["session_id"]

    r = agent_client.post(
        f"/api/ai-plan/sessions/{session_id}/messages",
        json={"content": "Mình muốn đi Da Nang 3 ngày cho 2 người"},
        headers=headers,
    )
    first_draft_id = r.json()["data"]["draft"]["draft_id"]

    r = agent_client.post(
        f"/api/ai-plan/sessions/{session_id}/regenerate",
        json={"instructions": "rẻ hơn, đổi khách sạn khác"},
        headers=headers,
    )
    assert r.status_code == 200, r.text
    body = r.json()["data"]
    assert body["draft"]["draft_id"] != first_draft_id


def test_regenerate_before_messages_rejected(agent_client: TestClient) -> None:
    headers = {"Authorization": _bearer()}
    r = agent_client.post("/api/ai-plan/sessions", json={}, headers=headers)
    session_id = r.json()["data"]["session_id"]

    r = agent_client.post(
        f"/api/ai-plan/sessions/{session_id}/regenerate",
        json={},
        headers=headers,
    )
    assert r.status_code == 400
    assert "incomplete" in r.json()["message"].lower()
