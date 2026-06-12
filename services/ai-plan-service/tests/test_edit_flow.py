"""Tests for the AI plan EDITOR: parse/summary, EditService execution, the
orchestrator edit loop (read board → propose), and the apply-edits endpoint."""

import base64
import json
from typing import Any, Dict, List
from uuid import uuid4

import pytest
from fastapi.testclient import TestClient

from app.agent.edits import EditValidationError, board_summary, parse_operations
from app.api import dependencies as deps
from app.clients.catalog_client import CatalogClient
from app.clients.plan_client import PlanClient
from app.config import get_settings
from app.llm.base import LLMClient, LLMTurn, ToolUse
from app.main import create_app
from app.services.edit_service import EditService


def _bearer(user_id: int = 42) -> str:
    payload = base64.urlsafe_b64encode(
        json.dumps({"id": user_id, "sub": "test@example.com"}).encode()
    ).rstrip(b"=").decode()
    header = base64.urlsafe_b64encode(b'{"alg":"none","typ":"JWT"}').rstrip(b"=").decode()
    return f"Bearer {header}.{payload}."


class _NullCatalog(CatalogClient):
    """Edit mode reads the board, not the catalog; search tools just return empty."""

    def __init__(self) -> None:
        super().__init__(get_settings())

    async def search_places(self, q, page=0, size=10):
        return []

    async def search_restaurants(self, location, page=0, size=10, **filters):
        return []

    async def search_hotels(self, location, page=0, size=10, **filters):
        return []


# ---------- pure helpers ----------


def test_parse_operations_drops_invalid_keeps_valid():
    raw = [
        {"op": "update_card", "list_id": 10, "card_id": 100, "text": "Mới"},
        {"op": "bogus", "list_id": 1},  # unknown op → dropped
        {"op": "create_card"},  # missing list_id+text → dropped
        {"op": "delete_card", "list_id": 10, "card_id": 101},
    ]
    ops = parse_operations(raw)
    assert [o.op for o in ops] == ["update_card", "delete_card"]
    assert ops[0].summary  # auto-filled


def test_parse_operations_empty_raises():
    with pytest.raises(EditValidationError):
        parse_operations([{"op": "nope"}])


def test_board_summary_lists_ids():
    board = {
        "planId": 7, "planTitle": "Đà Nẵng", "startDate": "2026-06-01", "endDate": "2026-06-02",
        "lists": [
            {"id": 10, "type": "DAY", "position": 0, "dayDate": "2026-06-01", "cards": [
                {"id": 100, "text": "Ăn sáng", "startTime": "07:00", "endTime": "08:00",
                 "activityType": "FOOD", "cost": {"estimatedCost": 120000}},
            ]},
            {"id": 11, "type": "TRASH", "position": 1, "cards": []},
        ],
    }
    s = board_summary(board)
    assert "Plan #7" in s and "list_id=10" in s and "card_id=100" in s
    assert "TRASH" not in s  # only DAY lists shown


# ---------- EditService ----------


class _RecordingPlan(PlanClient):
    def __init__(self) -> None:
        super().__init__(get_settings())
        self.calls: List[tuple] = []

    async def update_card(self, bearer, plan_id, list_id, card_id, body):
        self.calls.append(("update_card", plan_id, list_id, card_id, body))
        return {"id": card_id}

    async def create_card(self, bearer, plan_id, list_id, body, idempotency_key=None):
        self.calls.append(("create_card", plan_id, list_id, body))
        return {"id": 999}

    async def delete_card(self, bearer, plan_id, list_id, card_id):
        self.calls.append(("delete_card", plan_id, list_id, card_id))
        return {}

    async def move_card(self, bearer, plan_id, card_id, target_list_id, target_position=None, idempotency_key=None):
        self.calls.append(("move_card", plan_id, card_id, target_list_id, target_position))
        return {}

    async def rename_list(self, bearer, plan_id, list_id, title):
        self.calls.append(("rename_list", plan_id, list_id, title))
        return {}


@pytest.mark.asyncio
async def test_edit_service_maps_ops_to_calls_and_scales_cost():
    plan = _RecordingPlan()
    ops = parse_operations([
        {"op": "update_card", "list_id": 10, "card_id": 100, "text": "Bún bò Huế",
         "unit_price_vnd": 60000, "quantity": 2, "estimated_cost_vnd": 120000, "activity_type": "FOOD"},
        {"op": "create_card", "list_id": 10, "text": "Cafe view", "start_time": "9:00",
         "activity_type": "FOOD", "estimated_cost_vnd": 100000, "quantity": 2},
        {"op": "delete_card", "list_id": 10, "card_id": 101},
        {"op": "rename_list", "list_id": 10, "title": "Ngày 1 — Biển"},
    ])
    result = await EditService(plan).apply(5, ops, "token")
    assert result["applied"] == 4 and result["total"] == 4
    names = [c[0] for c in plan.calls]
    assert names == ["update_card", "create_card", "delete_card", "rename_list"]

    update_body = plan.calls[0][4]
    assert update_body["text"] == "Bún bò Huế"
    assert update_body["participantCount"] == 2
    assert update_body["cost"]["estimatedCost"] == 120000
    # activityData carries the per-person breakdown for the modal.
    assert json.loads(update_body["activityDataJson"])["ticketPrice"] == 60000

    create_body = plan.calls[1][3]
    assert create_body["startTime"] == "09:00"  # normalized
    assert create_body["activityType"] == "FOOD"


@pytest.mark.asyncio
async def test_edit_card_body_fills_rich_fields():
    """An edit-created card carries the full detail (address/route/distance/transport/
    recommendation), matching the catalog/approval path — not just a title + time."""
    plan = _RecordingPlan()
    ops = parse_operations([{
        "op": "create_card", "list_id": 10, "text": "Ăn sáng Bánh canh Nam Phổ",
        "start_time": "7:30", "end_time": "8:15", "activity_type": "FOOD",
        "estimated_cost_vnd": 150000, "unit_price_vnd": 75000, "quantity": 2,
        "location_name": "Bánh canh Nam Phổ", "address": "74 Trưng Nữ Vương, Đà Nẵng",
        "note": "hoặc Bún Mắm Hạnh - 36 Tăng Bạt Hổ", "route_hint": "Home → Quán ăn",
        "distance_text": "500m", "transport_mode": "xe máy", "reason": "gần khách sạn",
        "recommendation": {"kind": "RESTAURANT", "slug": "banh-canh-nam-pho",
                           "name": "Bánh canh Nam Phổ", "cover_image_url": "http://x/y.jpg"},
    }])
    await EditService(plan).apply(5, ops, "t")
    body = plan.calls[0][3]
    ad = json.loads(body["activityDataJson"])
    assert ad["locationName"] == "Bánh canh Nam Phổ"
    assert ad["address"].startswith("74 Trưng")
    assert ad["routeHint"] == "Home → Quán ăn"
    assert ad["distanceText"] == "500m"
    assert ad["transportMode"] == "xe máy"
    assert ad["reason"] == "gần khách sạn"
    assert ad["recommendation"]["slug"] == "banh-canh-nam-pho"
    assert ad["recommendation"]["coverImageUrl"] == "http://x/y.jpg"
    # A FOOD card with a RESTAURANT recommendation also stores the manual-pick shape so
    # the picker pre-selects + auto-focuses the catalog restaurant.
    assert ad["restaurantName"] == "Bánh canh Nam Phổ"
    assert ad["restaurantLocation"]["type"] == "RESTAURANT"
    assert ad["restaurantLocation"]["placeId"] == "banh-canh-nam-pho"  # slug fallback
    assert ad["restaurantLocation"]["fullAddress"].startswith("74 Trưng")
    assert "📍" in body["description"] and "🗺️" in body["description"]
    assert body["startTime"] == "07:30" and body["endTime"] == "08:15"


@pytest.mark.asyncio
async def test_edit_service_collects_failures():
    class _Boom(_RecordingPlan):
        async def delete_card(self, *a, **k):
            raise RuntimeError("nope")

    plan = _Boom()
    ops = parse_operations([
        {"op": "rename_list", "list_id": 1, "title": "X"},
        {"op": "delete_card", "list_id": 1, "card_id": 2},
    ])
    result = await EditService(plan).apply(5, ops, "t")
    assert result["applied"] == 1 and result["total"] == 2
    assert any(not r["ok"] for r in result["results"])


# ---------- orchestrator edit loop + endpoint (integration) ----------


_BOARD = {
    "planId": 5, "planTitle": "Đà Nẵng 2N", "startDate": "2026-06-01", "endDate": "2026-06-02",
    "myRole": "OWNER",
    "lists": [
        {"id": 10, "type": "DAY", "position": 0, "dayDate": "2026-06-01", "cards": [
            {"id": 100, "text": "Ăn sáng cũ", "startTime": "07:00", "endTime": "08:00",
             "activityType": "FOOD", "position": 0, "cost": {"estimatedCost": 100000}},
        ]},
        {"id": 11, "type": "TRASH", "position": 1, "cards": []},
    ],
}


class _EditPlan(_RecordingPlan):
    async def get_board(self, bearer, plan_id):
        self.calls.append(("get_board", plan_id))
        return _BOARD


class _EditLLM(LLMClient):
    """Scripted: turn 1 → get_current_plan; turn 2 → propose_plan_edits."""

    def supports_tool_use(self):
        return True

    async def extract_constraints(self, user_message, prior):
        return prior

    async def chat_with_tools(self, system_prompt, messages, tools, max_tokens=2048):
        tool_msgs = [m for m in messages if m.get("role") == "tool"]
        if len(tool_msgs) == 0:
            return self._call("get_current_plan", {})
        if len(tool_msgs) == 1:
            return self._call("propose_plan_edits", {"operations": [
                {"op": "update_card", "list_id": 10, "card_id": 100, "text": "Ăn sáng Mì Quảng",
                 "summary": "Đổi món sáng"},
                {"op": "create_card", "list_id": 10, "text": "Cafe Cộng", "start_time": "08:30",
                 "activity_type": "FOOD", "summary": "Thêm cafe sáng"},
            ]})
        return LLMTurn(stop_reason="end_turn", text="Mình đã đề xuất 2 thay đổi.")

    @staticmethod
    def _call(name, args):
        tid = f"call_{uuid4().hex[:8]}"
        return LLMTurn(
            stop_reason="tool_use",
            tool_uses=[ToolUse(id=tid, name=name, arguments=args)],
            raw_assistant_message={
                "role": "assistant", "content": "",
                "tool_calls": [{"id": tid, "type": "function",
                                "function": {"name": name, "arguments": json.dumps(args)}}],
            },
        )


def _edit_client():
    from app.agent.orchestrator import AgentOrchestrator
    from app.services.draft_composer import DraftComposer

    catalog = _NullCatalog()
    plan = _EditPlan()
    llm = _EditLLM()
    app = create_app()
    composer = DraftComposer(catalog)
    app.dependency_overrides[deps.get_catalog_client] = lambda: catalog
    app.dependency_overrides[deps.get_plan_client] = lambda: plan
    app.dependency_overrides[deps.get_llm_client] = lambda: llm
    app.dependency_overrides[deps.get_draft_composer] = lambda: composer
    app.dependency_overrides[deps.get_agent_orchestrator] = lambda: AgentOrchestrator(
        llm, catalog, composer, plan_client=plan
    )
    app.dependency_overrides[deps.get_edit_service] = lambda: EditService(plan)
    tc = TestClient(app)
    tc.fake_plan = plan  # type: ignore[attr-defined]
    return tc


def _sse_events(text: str) -> List[Dict[str, Any]]:
    events = []
    for frame in text.split("\n\n"):
        ev, data = None, None
        for line in frame.split("\n"):
            if line.startswith("event:"):
                ev = line[6:].strip()
            elif line.startswith("data:"):
                data = line[5:].strip()
        if ev:
            events.append({"event": ev, "data": json.loads(data) if data else None})
    return events


def test_edit_session_proposes_then_applies():
    client = _edit_client()
    headers = {"Authorization": _bearer()}

    # Create an edit-mode session bound to plan 5.
    r = client.post("/api/ai-plan/sessions", json={"plan_id": 5}, headers=headers)
    assert r.status_code == 201
    view = r.json()["data"]
    assert view["plan_id"] == 5
    session_id = view["session_id"]

    # Send an edit request → SSE stream should carry an edit_proposal.
    r = client.post(
        f"/api/ai-plan/sessions/{session_id}/messages/stream",
        json={"content": "Đổi món ăn sáng và thêm cafe"},
        headers=headers,
    )
    assert r.status_code == 200, r.text
    events = _sse_events(r.text)
    proposals = [e for e in events if e["event"] == "edit_proposal"]
    assert proposals, f"no edit_proposal in {[e['event'] for e in events]}"
    ops = proposals[0]["data"]["operations"]
    assert len(ops) == 2

    # Proposal persisted on the session.
    r = client.get(f"/api/ai-plan/sessions/{session_id}", headers=headers)
    assert len(r.json()["data"]["pending_edits"]) == 2

    # Apply → executes against plan-service.
    r = client.post(f"/api/ai-plan/sessions/{session_id}/apply-edits", headers=headers)
    assert r.status_code == 200, r.text
    data = r.json()["data"]
    assert data["applied"] == 2 and data["total"] == 2

    fake_plan: _EditPlan = client.fake_plan  # type: ignore[attr-defined]
    names = [c[0] for c in fake_plan.calls]
    assert "update_card" in names and "create_card" in names

    # Pending edits cleared after apply.
    r = client.get(f"/api/ai-plan/sessions/{session_id}", headers=headers)
    assert r.json()["data"]["pending_edits"] == []


_VIEWER_BOARD = {**_BOARD, "myRole": "VIEWER"}


class _ViewerPlan(_RecordingPlan):
    async def get_board(self, bearer, plan_id):
        self.calls.append(("get_board", plan_id))
        return _VIEWER_BOARD


def test_viewer_cannot_open_edit_session():
    """A user with only VIEWER role on the plan must be refused (403) at session
    create — they can never reach the editor."""
    from app.agent.orchestrator import AgentOrchestrator
    from app.services.draft_composer import DraftComposer

    catalog = _NullCatalog()
    plan = _ViewerPlan()
    llm = _EditLLM()
    app = create_app()
    composer = DraftComposer(catalog)
    app.dependency_overrides[deps.get_catalog_client] = lambda: catalog
    app.dependency_overrides[deps.get_plan_client] = lambda: plan
    app.dependency_overrides[deps.get_llm_client] = lambda: llm
    app.dependency_overrides[deps.get_draft_composer] = lambda: composer
    app.dependency_overrides[deps.get_agent_orchestrator] = lambda: AgentOrchestrator(
        llm, catalog, composer, plan_client=plan
    )
    app.dependency_overrides[deps.get_edit_service] = lambda: EditService(plan)
    client = TestClient(app)

    r = client.post("/api/ai-plan/sessions", json={"plan_id": 5}, headers={"Authorization": _bearer()})
    assert r.status_code == 403, r.text
    assert "xem" in r.json()["message"].lower()


# ---------- plan-id detection + delete_list op ----------


def test_extract_plan_id():
    from app.api.ai_plan import _extract_plan_id

    assert _extract_plan_id("xóa ngày cuối http://localhost:5173/plans/43") == 43
    assert _extract_plan_id("sửa kế hoạch số 7 giúp mình") == 7
    assert _extract_plan_id("plan #12 đổi khách sạn") == 12
    # Natural phrasings without a marker still work.
    assert _extract_plan_id("xóa ngày cuối của plan 43") == 43
    assert _extract_plan_id("mở kế hoạch 43 giúp mình") == 43
    # Must NOT mistake a count/duration for a plan id.
    assert _extract_plan_id("Đà Nẵng 3 ngày 2 người") is None
    assert _extract_plan_id("kế hoạch 3 ngày chill") is None
    assert _extract_plan_id("kế hoạch 2 người đi biển") is None


def test_delete_list_op_parses_and_summarizes():
    ops = parse_operations([{"op": "delete_list", "list_id": 10}], board=_BOARD)
    assert len(ops) == 1 and ops[0].op == "delete_list"
    assert "ngày" in ops[0].summary  # human, id-free summary
    assert "#" not in ops[0].summary  # never expose internal ids
    # A list_id not on the board is dropped (hallucination guard).
    with pytest.raises(EditValidationError):
        parse_operations([{"op": "delete_list", "list_id": 999}], board=_BOARD)


def test_global_chat_binds_plan_from_message_then_edits():
    """A plan-less (global MAI) chat that references /plans/5 binds to that plan and
    runs the edit flow — exactly like the old plan-bound editor."""
    client = _edit_client()
    headers = {"Authorization": _bearer()}

    r = client.post("/api/ai-plan/sessions", json={}, headers=headers)
    assert r.status_code == 201
    view = r.json()["data"]
    assert view["plan_id"] is None
    sid = view["session_id"]

    r = client.post(
        f"/api/ai-plan/sessions/{sid}/messages/stream",
        json={"content": "xóa ngày cuối trong plan này http://localhost:5173/plans/5"},
        headers=headers,
    )
    assert r.status_code == 200, r.text
    events = _sse_events(r.text)
    assert any(e["event"] == "session" and e["data"].get("plan_id") == 5 for e in events), \
        f"session event missing bound plan_id in {[e['event'] for e in events]}"
    assert any(e["event"] == "edit_proposal" for e in events)

    # Session is now bound to plan 5 → subsequent turns + apply target it.
    r = client.get(f"/api/ai-plan/sessions/{sid}", headers=headers)
    assert r.json()["data"]["plan_id"] == 5


def test_viewer_referencing_plan_in_chat_is_refused_softly():
    """A viewer who points the global chat at a plan they can't edit gets a friendly
    assistant message (not bound, no proposal)."""
    from app.agent.orchestrator import AgentOrchestrator
    from app.services.draft_composer import DraftComposer

    catalog = _NullCatalog()
    plan = _ViewerPlan()
    llm = _EditLLM()
    app = create_app()
    composer = DraftComposer(catalog)
    app.dependency_overrides[deps.get_catalog_client] = lambda: catalog
    app.dependency_overrides[deps.get_plan_client] = lambda: plan
    app.dependency_overrides[deps.get_llm_client] = lambda: llm
    app.dependency_overrides[deps.get_draft_composer] = lambda: composer
    app.dependency_overrides[deps.get_agent_orchestrator] = lambda: AgentOrchestrator(
        llm, catalog, composer, plan_client=plan
    )
    app.dependency_overrides[deps.get_edit_service] = lambda: EditService(plan)
    client = TestClient(app)
    headers = {"Authorization": _bearer()}

    sid = client.post("/api/ai-plan/sessions", json={}, headers=headers).json()["data"]["session_id"]
    r = client.post(
        f"/api/ai-plan/sessions/{sid}/messages/stream",
        json={"content": "đổi ngày 1 trong /plans/5"},
        headers=headers,
    )
    assert r.status_code == 200, r.text
    events = _sse_events(r.text)
    assert not any(e["event"] == "edit_proposal" for e in events)
    texts = " ".join(e["data"].get("text", "") for e in events if e["event"] == "assistant_message")
    assert "xem" in texts.lower()
    # Not bound — stays a general chat.
    assert client.get(f"/api/ai-plan/sessions/{sid}", headers=headers).json()["data"]["plan_id"] is None


def test_list_sessions_returns_history():
    """GET /sessions lists a user's AI chats (newest first) so they can resume one."""
    from app.agent.orchestrator import AgentOrchestrator
    from app.services.draft_composer import DraftComposer
    from app.services.session_store import InMemorySessionStore

    catalog = _NullCatalog()
    plan = _EditPlan()
    llm = _EditLLM()
    store = InMemorySessionStore()  # isolated, so the history list is deterministic
    app = create_app()
    composer = DraftComposer(catalog)
    app.dependency_overrides[deps.get_catalog_client] = lambda: catalog
    app.dependency_overrides[deps.get_plan_client] = lambda: plan
    app.dependency_overrides[deps.get_llm_client] = lambda: llm
    app.dependency_overrides[deps.get_draft_composer] = lambda: composer
    app.dependency_overrides[deps.get_store] = lambda: store
    app.dependency_overrides[deps.get_agent_orchestrator] = lambda: AgentOrchestrator(
        llm, catalog, composer, plan_client=plan
    )
    app.dependency_overrides[deps.get_edit_service] = lambda: EditService(plan)
    client = TestClient(app)
    headers = {"Authorization": _bearer()}

    # No history yet.
    assert client.get("/api/ai-plan/sessions", headers=headers).json()["data"] == []

    sid = client.post(
        "/api/ai-plan/sessions", json={"plan_id": 5}, headers=headers
    ).json()["data"]["session_id"]
    client.post(
        f"/api/ai-plan/sessions/{sid}/messages/stream",
        json={"content": "đổi món sáng nhé"},
        headers=headers,
    )

    data = client.get("/api/ai-plan/sessions", headers=headers).json()["data"]
    assert len(data) == 1
    assert data[0]["session_id"] == sid
    assert data[0]["plan_id"] == 5
    assert data[0]["message_count"] >= 1
    assert data[0]["title"] == "đổi món sáng nhé"

    # A different user sees none of it.
    other = client.get("/api/ai-plan/sessions", headers={"Authorization": _bearer(user_id=99)})
    assert other.json()["data"] == []
