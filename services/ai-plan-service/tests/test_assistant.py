# -*- coding: utf-8 -*-
"""General-chat brain: MAI answers ordinary questions instead of forcing planning."""

from datetime import date

import pytest

from app.models.session import Constraints
from app.llm.stub import _parse_destination
from app.services import assistant as A


def test_general_questions_do_not_trigger_planning():
    for msg in [
        "bạn có thể làm gì",
        "thông tin về nền tảng này, nền tảng này là gì",
        "hôm nay là ngày mấy",
        "xin chào",
        "cảm ơn nhé",
    ]:
        assert A.wants_planning(msg, Constraints()) is False, msg


def test_trip_requests_trigger_planning():
    for msg in [
        "Lên kế hoạch Đà Nẵng 3 ngày 2 người",
        "đi Hội An chơi cuối tuần",
        "3 ngày 2 người",
        "gợi ý khách sạn ở Nha Trang",
    ]:
        assert A.wants_planning(msg, Constraints()) is True, msg


def test_continuation_uses_known_destination():
    # A short follow-up during an in-progress trip chat stays in planning.
    c = Constraints(destination="Đà Nẵng")
    assert A.wants_planning("nhịp thong thả thôi", c) is True
    # But a general question is still answered generally even mid-trip.
    assert A.wants_planning("nền tảng này là gì", c) is False


def test_date_reply_reports_today():
    reply = A.general_reply("hôm nay là ngày mấy")
    today = date.today()
    assert f"{today.day:02d}/{today.month:02d}/{today.year}" in reply


def test_about_and_capabilities_mention_mravel():
    assert "Mravel" in A.general_reply("nền tảng này là gì")
    assert "MAI" in A.general_reply("bạn giúp được gì")


def test_meta_phrase_is_not_a_destination():
    # The bug: "về nền tảng này" was captured as a destination.
    assert _parse_destination("thông tin về nền tảng này, nền tảng này là gì") is None
    # Real destinations still parse.
    assert _parse_destination("đi Đà Nẵng 3 ngày") == "Đà Nẵng"
    assert _parse_destination("du lịch Hội An") == "Hội An"


def test_live_fact_question_detection():
    assert A.is_live_fact_question("giá vé asia park vào thời điểm hiện tại")
    assert A.is_live_fact_question("giờ mở cửa Bà Nà")
    assert A.is_live_fact_question("thuê xe máy bao nhiêu tiền")
    # In-plan cost questions are NOT web questions (answer from the board).
    assert not A.is_live_fact_question("tổng chi phí kế hoạch là bao nhiêu")
    assert not A.is_live_fact_question("thêm hoạt động vào ngày 2")


@pytest.mark.asyncio
async def test_answer_web_question_formats_answer_and_sources():
    from app.agent.orchestrator import AgentOrchestrator
    from app.services.draft_composer import DraftComposer

    class _FakeWeb:
        enabled = True

        async def search(self, query, max_results=5):
            return {"enabled": True, "answer": "Vé ~200.000đ (2026).",
                    "results": [{"title": "Klook", "url": "https://klook.com/x", "content": "c"}]}

    class _NullCat:
        async def search_hotels(self, *a, **k):
            return []

        async def search_restaurants(self, *a, **k):
            return []

        async def search_places(self, *a, **k):
            return []

    orch = AgentOrchestrator(None, _NullCat(), DraftComposer(_NullCat()), web_search=_FakeWeb())
    ans = await orch._answer_web_question("giá vé asia park hiện tại", None)
    assert ans and "200.000đ" in ans and "klook.com/x" in ans
    # A non-fact message doesn't trigger a web answer.
    assert await orch._answer_web_question("thêm hoạt động ngày 2", None) is None
