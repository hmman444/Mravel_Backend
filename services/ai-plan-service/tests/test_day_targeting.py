# -*- coding: utf-8 -*-
"""Day-index targeting: move/create cards INTO a day added in the same batch.

Regression for "chuyển sang ngày 2 nhưng không chuyển gì" — the agent proposed
move_card into a just-added Ngày 2 whose list_id didn't exist yet, so the move
silently no-op'd. Now the agent targets days by 1-based index and EditService
resolves it to the real list id at apply time.
"""

import pytest

from app.agent.edits import parse_operations
from app.clients.plan_client import PlanClient
from app.config import get_settings
from app.services.edit_service import EditService, _ordered_day_list_ids, _resolve_day_targets


def test_ordered_day_list_ids_sorts_by_position():
    board = {"lists": [
        {"id": 30, "type": "DAY", "position": 2},
        {"id": 10, "type": "DAY", "position": 0},
        {"id": 99, "type": "TRASH", "position": 3},
        {"id": 20, "type": "DAY", "position": 1},
    ]}
    assert _ordered_day_list_ids(board) == [10, 20, 30]


def test_resolve_day_targets_maps_index_to_list_id():
    ops = parse_operations([
        {"op": "move_card", "card_id": 100, "target_day_index": 2},
        {"op": "create_card", "text": "Cafe", "day_index": 3},
    ])
    day_lists = [10, 20, 30]
    move = _resolve_day_targets(ops[0], day_lists)
    create = _resolve_day_targets(ops[1], day_lists)
    assert move.target_list_id == 20
    assert create.list_id == 30


class _StatefulPlan(PlanClient):
    """Simulates plan-service: add_day creates a new DAY list; move/create record targets."""

    def __init__(self) -> None:
        super().__init__(get_settings())
        self.days = [{"id": 10, "type": "DAY", "position": 0, "dayDate": "2026-06-01", "cards": [
            {"id": 100, "text": "Ăn trưa", "activityType": "FOOD"},
        ]}]
        self._next_id = 20
        self.moves: list = []
        self.creates: list = []

    async def get_board(self, bearer, plan_id):
        return {"planId": plan_id, "lists": [*self.days, {"id": 999, "type": "TRASH"}]}

    async def create_list(self, bearer, plan_id, body, idempotency_key=None):
        self.days.append({
            "id": self._next_id, "type": "DAY", "position": len(self.days),
            "dayDate": f"2026-06-0{len(self.days) + 1}", "cards": [],
        })
        self._next_id += 10
        return {}

    async def move_card(self, bearer, plan_id, card_id, target_list_id, target_position=None, idempotency_key=None):
        self.moves.append((card_id, target_list_id))
        return {"entityId": card_id}

    async def create_card(self, bearer, plan_id, list_id, body, idempotency_key=None):
        self.creates.append((list_id, body.get("text")))
        return {"id": 555}


@pytest.mark.asyncio
async def test_move_and_create_into_newly_added_days_resolve_ids():
    plan = _StatefulPlan()
    ops = parse_operations([
        {"op": "add_day", "title": "Ngày 2"},
        {"op": "add_day", "title": "Ngày 3"},
        {"op": "move_card", "card_id": 100, "target_day_index": 2,
         "start_time": "12:00", "end_time": "13:00"},
        {"op": "create_card", "text": "Tối: Phố đi bộ", "day_index": 3,
         "start_time": "19:00", "end_time": "21:00", "activity_type": "ENTERTAIN"},
    ])
    result = await EditService(plan).apply(5, ops, "token")
    assert result["applied"] == 4, result["results"]
    # The move landed in the real Ngày 2 list (id 20), create in Ngày 3 (id 30).
    assert plan.moves == [(100, 20)]
    assert plan.creates == [(30, "Tối: Phố đi bộ")]


@pytest.mark.asyncio
async def test_unresolvable_day_index_fails_loudly_not_silently():
    plan = _StatefulPlan()
    ops = parse_operations([
        {"op": "move_card", "card_id": 100, "target_day_index": 5},  # no such day
    ])
    result = await EditService(plan).apply(5, ops, "token")
    assert result["applied"] == 0
    assert not result["results"][0]["ok"]
    assert plan.moves == []  # never issued a bogus move
