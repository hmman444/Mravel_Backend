# -*- coding: utf-8 -*-
"""Deterministic 'thêm hoạt động' fallback — populates target days with real activities
when the LLM won't call propose_plan_edits."""

import asyncio

from app.services import fill_editor as F
from app.services.draft_composer import DraftComposer


def test_detect_fill_intent():
    assert F.detect_fill_intent("thêm hoạt động vào ngày 2 và ngày 3")
    assert F.detect_fill_intent("thêm cho tôi các hoạt động du lịch vui chơi")
    assert not F.detect_fill_intent("tổng chi phí ngày 1 là bao nhiêu")


def test_parse_target_days():
    assert F.parse_target_days("thêm hoạt động vào ngày 2 và ngày 3", 3) == [2, 3]
    assert F.parse_target_days("điền ngày 5 giúp", 3) == []  # out of range dropped
    assert F.parse_target_days("thêm hoạt động", 3) == []


class _FakeCatalog:
    async def search_hotels(self, loc, page=0, size=8):
        return [{"name": "KS Trung tâm", "cityName": "TP. Hồ Chí Minh",
                 "latitude": 10.78, "longitude": 106.70, "minNightlyPrice": 500_000}]

    async def search_restaurants(self, loc, page=0, size=30, cuisine=None):
        return [{"name": "Quán A", "cityName": "TP. Hồ Chí Minh",
                 "latitude": 10.782, "longitude": 106.70,
                 "minPricePerPerson": 120_000, "maxPricePerPerson": 150_000}]

    async def search_places(self, dest, page=0, size=30):
        return [{"name": "Bảo tàng X", "cityName": "TP. Hồ Chí Minh",
                 "latitude": 10.78, "longitude": 106.69, "minPrice": 50_000}]


def _board():
    return {
        "planTitle": "Lịch trình 3 ngày tại TP. Hồ Chí Minh cho 2 người",
        "lists": [
            {"id": 51, "type": "DAY", "position": 0,
             "cards": [{"id": 1, "text": "Ăn sáng", "participantCount": 2}]},
            {"id": 52, "type": "DAY", "position": 1, "cards": []},
            {"id": 53, "type": "DAY", "position": 2, "cards": []},
        ],
    }


def _run(coro):
    return asyncio.get_event_loop().run_until_complete(coro)


def test_build_fill_ops_targets_named_days_with_real_times():
    ops = _run(F.build_fill_ops(_board(), "thêm hoạt động vào ngày 2 và ngày 3",
                                DraftComposer(_FakeCatalog())))
    assert ops, "expected create_card ops"
    days = {op.day_index for op in ops}
    assert days == {2, 3}  # only the requested days
    assert all(op.op == "create_card" for op in ops)
    # Real, non-overflow times (the 23:59 bug must not resurface).
    assert all(op.start_time and op.start_time != "23:59" for op in ops)


def test_build_fill_ops_defaults_to_empty_days():
    # No day named → fill every EMPTY day (2 and 3), never the already-populated day 1.
    ops = _run(F.build_fill_ops(_board(), "thêm giúp tôi các hoạt động",
                                DraftComposer(_FakeCatalog())))
    days = {op.day_index for op in ops}
    assert days == {2, 3}
    assert 1 not in days
