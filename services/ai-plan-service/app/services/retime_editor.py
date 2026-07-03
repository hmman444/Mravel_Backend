# -*- coding: utf-8 -*-
"""Deterministic 're-time' editor — fixes the clock times of EXISTING cards.

Plans built by the old broken composer have every activity at 23:59-23:59. When the
user says "chỉnh lại thời gian ngày 1" / "thời gian chuẩn hơn" the weak LLM won't act,
so this re-chains each day's existing cards into a realistic schedule
(sáng → trưa → nghỉ → chiều → tối) and emits `update_card` ops that change ONLY
start_time/end_time (leaving titles/venues/costs untouched).
"""

from __future__ import annotations

import re
from typing import Any, Dict, List, Optional, Tuple

from app.agent.edits import EditOperation

_RETIME_MARKERS = (
    "chỉnh lại thời gian", "chỉnh thời gian", "sửa lại thời gian", "sửa thời gian",
    "chỉnh lại giờ", "sửa lại giờ", "sửa giờ", "đổi giờ", "canh giờ", "canh lại giờ",
    "sắp xếp lại giờ", "sắp xếp giờ", "thời gian chuẩn", "giờ chuẩn", "thời gian hợp lý",
    "giờ hợp lý", "thời gian bị sai", "giờ bị sai", "thời gian đang sai", "giờ đang sai",
    "23:59", "thời gian đều là", "giờ giấc",
)


def detect_retime_intent(text: str) -> bool:
    low = (text or "").lower()
    return any(m in low for m in _RETIME_MARKERS)


def parse_target_days(text: str, num_days: int) -> List[int]:
    low = (text or "").lower()
    return sorted({
        int(n) for n in re.findall(r"ngày\s*(\d{1,2})", low) if 1 <= int(n) <= num_days
    })


def _ordered_day_lists(board: Dict[str, Any]) -> List[Dict[str, Any]]:
    lists = [
        x for x in (board.get("lists") or [])
        if isinstance(x, dict) and x.get("type") == "DAY"
    ]
    lists.sort(
        key=lambda x: (
            x.get("position") if x.get("position") is not None else 9999,
            str(x.get("dayDate") or ""),
        )
    )
    return lists


def _ordered_cards(lst: Dict[str, Any]) -> List[Dict[str, Any]]:
    cards = [c for c in (lst.get("cards") or []) if isinstance(c, dict)]
    cards.sort(key=lambda c: (c.get("position") if c.get("position") is not None else 9999))
    return cards


_MAX_MIN = 23 * 60 + 59


def _hhmm(minutes: int) -> str:
    minutes = max(0, min(minutes, _MAX_MIN))
    return f"{minutes // 60:02d}:{minutes % 60:02d}"


def _slot(atype: str, text: str) -> Tuple[int, Optional[int]]:
    """(duration_minutes, earliest-start anchor in minutes or None) for a card."""
    t = (text or "").lower()
    a = (atype or "").upper()
    if "cafe" in t or "cà phê" in t or "ca phe" in t:
        return 45, None
    if "ăn sáng" in t or "an sang" in t:
        return 60, 7 * 60
    if "ăn trưa" in t or "an trua" in t:
        return 75, 11 * 60 + 30
    if "ăn tối" in t or "an toi" in t:
        return 90, 18 * 60 + 30
    if "nghỉ trưa" in t or "nghi trua" in t:
        return 90, 13 * 60 + 15
    if "nghỉ ngơi" in t or "trả phòng" in t or "tra phong" in t:
        return 0, 21 * 60 + 30
    if "nhận phòng" in t or "nhan phong" in t or "check-in" in t:
        return 60, 14 * 60
    if "thuê xe" in t or "thue xe" in t or a == "TRANSPORT":
        return 30, None
    if a == "FOOD":
        return 60, None
    if a in ("SIGHTSEEING", "ENTERTAIN", "EVENT", "CINEMA"):
        return 120, None
    if a == "STAY":
        return 90, None
    return 60, None


def _retime_day(cards: List[Dict[str, Any]], list_id: int) -> List[EditOperation]:
    ops: List[EditOperation] = []
    t = 7 * 60  # start the day at 07:00
    for c in cards:
        dur, anchor = _slot(c.get("activityType") or "", c.get("text") or "")
        if anchor is not None and t < anchor:
            t = anchor
        start = min(t, _MAX_MIN)
        end = min(start + dur, _MAX_MIN)
        op = EditOperation(
            op="update_card",
            list_id=list_id,
            card_id=c.get("id"),
            start_time=_hhmm(start),
            end_time=_hhmm(end),
        )
        op.summary = f"Đổi giờ “{_shorten(c.get('text') or '')}” → {op.start_time}-{op.end_time}"
        ops.append(op)
        t = min(end + 12, _MAX_MIN)  # ~12' di chuyển giữa các điểm
    return ops


def build_retime_ops(board: Dict[str, Any], message: str) -> List[EditOperation]:
    """`update_card` ops re-timing each target day's existing cards into a realistic
    daily schedule. Targets = named days, else ALL days. Only start/end time change."""
    day_lists = _ordered_day_lists(board)
    if not day_lists:
        return []
    targets = parse_target_days(message, len(day_lists))
    ops: List[EditOperation] = []
    for idx, lst in enumerate(day_lists, start=1):
        if targets and idx not in targets:
            continue
        cards = [c for c in _ordered_cards(lst) if c.get("id") is not None]
        if not cards:
            continue
        ops.extend(_retime_day(cards, lst.get("id")))
    return ops


def _shorten(text: str, limit: int = 34) -> str:
    text = " ".join((text or "").split())
    return text if len(text) <= limit else text[: limit - 1].rstrip() + "…"
