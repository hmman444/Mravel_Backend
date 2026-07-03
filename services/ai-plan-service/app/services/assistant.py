# -*- coding: utf-8 -*-
"""Deterministic general-chat brain for MAI when the LLM is unavailable.

Without a live LLM the orchestrator used to funnel EVERY message into the trip
planner — so "bạn có thể làm gì", "nền tảng này là gì", "hôm nay là ngày mấy" got
planning answers (or even a bogus draft). This module lets the offline path tell a
genuine trip request apart from ordinary conversation, and answer the latter properly:

  - `wants_planning(msg, constraints)` — True only when the user is actually asking to
    plan/edit a trip (a real destination, trip keywords, or a clear continuation).
  - `general_reply(msg)` — a helpful Vietnamese answer for capabilities / "what is
    Mravel" / today's date / greetings, so MAI never talks past the user.
"""

from __future__ import annotations

import re
from datetime import date
from typing import Optional

from app.llm.stub import _VN_DESTINATIONS
from app.models.session import Constraints

# Phrases that clearly mean "help me plan / edit a trip" even without a city name.
_TRIP_KEYWORDS = (
    "lên kế hoạch", "lên lịch", "lịch trình", "kế hoạch đi", "kế hoạch du lịch",
    "đi du lịch", "chuyến đi", "chuyến du lịch", "đi chơi", "đi phượt", "phượt",
    "tour", "dựng plan", "tạo plan", "tạo lịch", "lập kế hoạch", "gợi ý địa điểm",
    "gợi ý quán", "gợi ý khách sạn", "đặt phòng", "khách sạn", "nhà hàng",
    "quán ăn", "địa điểm", "vé máy bay", "resort", "homestay", "check-in",
    "plan trip", "itinerary", "travel plan", "book hotel",
)

# Ordinary-conversation intents we can answer deterministically.
_CAPABILITY_MARKERS = (
    "làm được gì", "làm gì được", "bạn có thể làm", "có thể làm gì", "giúp được gì",
    "giúp gì", "chức năng", "tính năng", "dùng để làm gì", "hỗ trợ gì",
    "what can you do", "help me with",
)
_ABOUT_MARKERS = (
    "nền tảng này là gì", "nền tảng này", "mravel là gì", "mravel", "trang web này",
    "website này", "app này", "ứng dụng này", "hệ thống này", "trang này là gì",
    "giới thiệu", "what is this",
)
_DATE_MARKERS = (
    "hôm nay là ngày", "hôm nay ngày", "hôm nay là thứ", "ngày mấy", "ngày bao nhiêu",
    "thứ mấy", "hôm nay thứ", "ngày hiện tại", "what day", "today's date", "what date",
)
_GREETING_MARKERS = (
    "xin chào", "chào bạn", "chào mai", "hello", "hi mai", "hey", "alo", "helo",
)
_THANKS_MARKERS = ("cảm ơn", "cám ơn", "thank", "thanks", "tks")

_WEEKDAY_VI = (
    "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật",
)


def _has_known_destination(text: str) -> bool:
    low = (text or "").lower()
    return any(s in low for _, surfaces in _VN_DESTINATIONS for s in surfaces)


def has_trip_signal(text: str) -> bool:
    """A real trip cue in THIS message: a known city, a trip keyword, or an explicit
    duration/people count (e.g. "3 ngày 2 người")."""
    low = (text or "").lower()
    if _has_known_destination(low):
        return True
    if any(k in low for k in _TRIP_KEYWORDS):
        return True
    if re.search(r"\d+\s*(ngày|đêm|người|khách)", low):
        return True
    return False


# External, time-sensitive facts worth a web_search when the LLM won't do it itself
# (ticket prices, rental/opening hours, "còn mở cửa không"). Kept specific so it doesn't
# fire on in-plan questions like "tổng chi phí kế hoạch".
_LIVE_FACT_MARKERS = (
    "giá vé", "vé vào", "giá thuê", "thuê xe", "giờ mở cửa", "mấy giờ mở", "mấy giờ đóng",
    "còn mở cửa", "còn hoạt động", "giá tour", "vé máy bay", "vé xe", "giá phòng",
    "bao nhiêu tiền", "giá bao nhiêu", "giá hiện tại", "cập nhật giá", "giá vé hiện",
)


def is_live_fact_question(text: str) -> bool:
    low = (text or "").lower()
    if "tổng chi phí" in low or "chi phí kế hoạch" in low:
        return False  # that's an in-plan question, answer from the board
    return any(m in low for m in _LIVE_FACT_MARKERS)


def is_general_question(text: str) -> bool:
    low = (text or "").lower()
    markers = (
        _CAPABILITY_MARKERS + _ABOUT_MARKERS + _DATE_MARKERS
        + _GREETING_MARKERS + _THANKS_MARKERS
    )
    return any(m in low for m in markers)


def wants_planning(text: str, constraints: Optional[Constraints] = None) -> bool:
    """Whether the offline path should engage the trip planner for this message.

    A clear general question with no trip cue → False (answer generally). A trip cue,
    or a continuation of an in-progress trip (a real destination already on file), → True.
    """
    if has_trip_signal(text):
        return True
    if is_general_question(text):
        return False
    # Continuation: we already know a real destination from earlier in this chat, so a
    # short follow-up ("3 người", "thêm ngày 2") is still about the trip.
    if constraints is not None and constraints.destination:
        low = (constraints.destination or "").lower()
        if any(s in low for _, surfaces in _VN_DESTINATIONS for s in surfaces):
            return True
    return False


def general_reply(text: str) -> str:
    """A deterministic, on-topic answer for ordinary conversation."""
    low = (text or "").lower()

    if any(m in low for m in _DATE_MARKERS):
        today = date.today()
        wd = _WEEKDAY_VI[today.weekday()]
        return (
            f"Hôm nay là {wd}, ngày {today.day:02d}/{today.month:02d}/{today.year}. "
            "Bạn muốn mình lên kế hoạch cho một chuyến đi sắp tới không?"
        )

    if any(m in low for m in _THANKS_MARKERS) and not has_trip_signal(low):
        return "Không có gì! Khi nào cần lên kế hoạch chuyến đi, cứ nhắn mình nhé. 😊"

    if any(m in low for m in _GREETING_MARKERS) and not has_trip_signal(low):
        return (
            "Chào bạn! Mình là **MAI (Mravel AI)** — trợ lý du lịch của Mravel. "
            "Mình có thể lên kế hoạch chuyến đi, gợi ý khách sạn/quán ăn/địa điểm, hay "
            "chỉnh sửa lịch trình sẵn có. Bạn muốn đi đâu, hoặc cần mình giúp gì nào?"
        )

    if any(m in low for m in _ABOUT_MARKERS) and not any(
        k in low for k in _CAPABILITY_MARKERS
    ):
        return _ABOUT_TEXT

    # Capabilities — also the default for a vague "help me" style question.
    return _CAPABILITIES_TEXT


_ABOUT_TEXT = (
    "**Mravel** là nền tảng du lịch giúp bạn lên kế hoạch và đặt dịch vụ cho chuyến đi ở "
    "Việt Nam: tìm và đặt **khách sạn**, **nhà hàng**, khám phá **địa điểm tham quan**, "
    "tạo và chia sẻ **lịch trình (plan)** theo ngày, đọc/đánh giá **review**, và trò chuyện "
    "với bạn bè.\n\n"
    "Mình là **MAI (Mravel AI)** — trợ lý trong Mravel. Mình giúp bạn:\n"
    "- Lên **kế hoạch chuyến đi** chi tiết theo ngày (điểm đến, số ngày, số người, ngân sách).\n"
    "- **Gợi ý** khách sạn / quán ăn / địa điểm có trên Mravel (kèm link đặt).\n"
    "- **Chỉnh sửa** một kế hoạch có sẵn (đổi quán, dời giờ, làm rẻ hơn theo ngân sách…).\n\n"
    "Bạn muốn bắt đầu bằng việc lên kế hoạch cho một chuyến đi không?"
)

_CAPABILITIES_TEXT = (
    "Mình là **MAI (Mravel AI)**, trợ lý du lịch của Mravel. Mình có thể giúp bạn:\n"
    "- **Lên kế hoạch chuyến đi** theo ngày — chỉ cần cho mình biết điểm đến và đi mấy ngày "
    "(số người, ngân sách nếu có). Ví dụ: *“Lên kế hoạch Đà Nẵng 3 ngày 2 người, ngân sách 8 triệu”*.\n"
    "- **Gợi ý** khách sạn, quán ăn, địa điểm tham quan có trên Mravel (kèm link để xem/đặt).\n"
    "- **Chỉnh sửa** kế hoạch có sẵn: thêm/bớt hoạt động, đổi quán, dời giờ, hay *“làm rẻ hơn”* "
    "theo ngân sách bạn muốn.\n"
    "- Trả lời các câu hỏi về chuyến đi (chi phí, lộ trình, gợi ý theo sở thích).\n\n"
    "Bạn muốn mình bắt đầu với chuyến đi nào?"
)
