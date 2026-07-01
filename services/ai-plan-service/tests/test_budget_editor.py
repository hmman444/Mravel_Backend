# -*- coding: utf-8 -*-
"""Deterministic budget editor + cafe-pricing fix (the offline "làm rẻ hơn" path)."""

from app.services import budget_editor as be
from app.services import pricing


def test_parse_budget_vnd_variants():
    assert be.parse_budget_vnd("ngân sách chỉ có 10 triệu thôi") == 10_000_000
    assert be.parse_budget_vnd("10tr") == 10_000_000
    assert be.parse_budget_vnd("khoảng 10.000.000") == 10_000_000
    assert be.parse_budget_vnd("10,000,000 đồng") == 10_000_000
    assert be.parse_budget_vnd("500k") == 500_000
    # A plain small number (e.g. a plan id) must NOT be read as a budget.
    assert be.parse_budget_vnd("chỉnh plan 6") is None


def test_detect_budget_intent():
    assert be.detect_budget_intent("làm plan này rẻ hơn nhé")
    assert be.detect_budget_intent("ngân sách 10 triệu")
    assert not be.detect_budget_intent("thêm một quán ăn tối ngày 2")


def test_cafe_priced_as_drink_not_a_full_meal():
    """A restaurant venue used for a cafe slot must be priced with the cafe band,
    never the restaurant's full per-head bill (the inflated-cafe bug)."""
    restaurant = {"minPricePerPerson": 400_000, "maxPricePerPerson": 500_000}
    assert pricing.meal_cost_per_person(restaurant, "cafe") <= pricing._BANDS["cafe"][2]
    # Real meals still use the restaurant price.
    assert pricing.meal_cost_per_person(restaurant, "lunch") == 450_000


def _board():
    return {
        "budgetTotal": 16_924_728,
        "lists": [
            {
                "type": "DAY",
                "id": 51,
                "cards": [
                    {"id": 214, "text": "Cafe: The Temple", "activityType": "FOOD",
                     "participantCount": 3, "cost": {"estimatedCost": 1_350_000}},
                    {"id": 217, "text": "Nhận phòng Bespoke Villa", "activityType": "STAY",
                     "participantCount": 3, "cost": {"estimatedCost": 1_519_728}},
                    {"id": 234, "text": "Cafe: CoCo Grill", "activityType": "FOOD",
                     "participantCount": 3, "cost": {"estimatedCost": 2_100_000}},
                    {"id": 215, "text": "Chùa Cầu", "activityType": "SIGHTSEEING",
                     "participantCount": 3, "cost": {"estimatedCost": 216_000}},
                ],
            }
        ],
    }


def test_propose_budget_cuts_reduces_flexible_cards_only():
    ops = be.propose_budget_cuts(_board(), 10_000_000, travelers=3)
    by_card = {op.card_id: op for op in ops}
    # Cafes were slashed from their inflated restaurant price.
    assert by_card[214].estimated_cost_vnd < 1_350_000
    assert by_card[234].estimated_cost_vnd < 2_100_000
    # Every proposed op is a cost-reducing update_card and never raises the cost.
    assert all(op.op == "update_card" for op in ops)
    # The lodging card is not touched (can't be re-shopped offline).
    assert 217 not in by_card


def test_propose_budget_cuts_empty_when_already_cheap():
    board = {
        "lists": [
            {"type": "DAY", "id": 1, "cards": [
                {"id": 10, "text": "Ăn sáng", "activityType": "FOOD",
                 "participantCount": 2, "cost": {"estimatedCost": 40_000}},
            ]}
        ]
    }
    assert be.propose_budget_cuts(board, 10_000_000, travelers=2) == []
