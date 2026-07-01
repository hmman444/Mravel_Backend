# -*- coding: utf-8 -*-
"""Deterministic re-time fallback: fix existing cards stuck at 23:59 into a real day."""

from app.services import retime_editor as R


def test_detect_retime_intent():
    assert R.detect_retime_intent("chỉnh lại thời gian ngày 1, đang đều là 23:59")
    assert R.detect_retime_intent("chỉnh lại thời gian chuẩn hơn")
    assert R.detect_retime_intent("sửa giờ giúp mình")
    assert not R.detect_retime_intent("thêm một quán ăn tối")


def _board():
    cards = [
        {"id": 212, "text": "Thuê xe máy", "activityType": "TRANSPORT", "position": 0},
        {"id": 213, "text": "Ăn sáng: Lẩu Gà", "activityType": "FOOD", "position": 1},
        {"id": 214, "text": "Cafe: Tiệm Lẩu", "activityType": "FOOD", "position": 2},
        {"id": 216, "text": "Ăn trưa: Mabu", "activityType": "FOOD", "position": 3},
        {"id": 219, "text": "Ăn tối: Ba Gác", "activityType": "FOOD", "position": 4},
        {"id": 221, "text": "Về khách sạn nghỉ ngơi", "activityType": "STAY", "position": 5},
    ]
    return {"lists": [
        {"id": 51, "type": "DAY", "position": 0, "cards": cards},
        {"id": 52, "type": "DAY", "position": 1, "cards": [
            {"id": 300, "text": "Ăn sáng ngày 2", "activityType": "FOOD", "position": 0}]},
    ]}


def test_retime_produces_chronological_non_2359_times():
    ops = R.build_retime_ops(_board(), "chỉnh lại thời gian ngày 1")
    assert ops and all(op.op == "update_card" for op in ops)
    # Only day 1 (list 51) is targeted.
    assert all(op.list_id == 51 for op in ops)
    times = [op.start_time for op in ops]
    assert "23:59" not in times
    # Chronological and anchored: breakfast ~morning, lunch ~noon, dinner in the evening.
    assert times == sorted(times)
    lunch = next(op for op in ops if op.card_id == 216)
    dinner = next(op for op in ops if op.card_id == 219)
    assert lunch.start_time >= "11:30"
    assert dinner.start_time >= "18:00"
    # A cost-only re-time must NOT touch the card's title/cost/venue.
    assert all(op.text is None and op.estimated_cost_vnd is None for op in ops)


def test_retime_all_days_when_none_named():
    ops = R.build_retime_ops(_board(), "chỉnh lại thời gian chuẩn hơn")
    lists = {op.list_id for op in ops}
    assert lists == {51, 52}
