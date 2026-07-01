# -*- coding: utf-8 -*-
"""Deterministic "thêm hoạt động" editor — offline safety net for filling days.

The weak free LLM often ends an edit turn WITHOUT calling `propose_plan_edits`, so a
clear request like "thêm hoạt động vào ngày 2 và ngày 3" just got parroted back with
"Bạn muốn mình chỉnh sửa gì?". This module builds real `create_card` operations for the
target days using the same geo/price scheduler as the composer — no LLM needed.

It reuses `DraftComposer` (destination inferred from the plan) to generate a dense,
correctly-timed day, then emits `create_card` ops that target each day by 1-based index
(resolved to the real list_id at apply time by EditService).
"""

from __future__ import annotations

import re
from typing import Any, Dict, List, Optional

from app.agent.edits import EditOperation, operation_summary
from app.models.session import Constraints, DraftActivity
from app.services.draft_composer import DraftComposer, _canonical_city

# "thêm/điền/lên/gợi ý … hoạt động/địa điểm/lịch/chơi …" → fill intent.
_FILL_MARKERS = (
    "thêm hoạt động", "thêm các hoạt động", "thêm hoạt đông", "thêm hoạt dong",
    "điền hoạt động", "điền các hoạt động", "lên hoạt động", "thêm lịch",
    "thêm địa điểm", "thêm các địa điểm", "gợi ý hoạt động", "thêm vui chơi",
    "hoạt động vui chơi", "thêm hoạt động vui chơi", "thêm cho tôi các hoạt",
    "thêm giúp", "điền giúp", "lấp đầy", "fill", "thêm hoạt", "thêm chỗ chơi",
    "thêm chỗ ăn", "thêm điểm",
)


def detect_fill_intent(text: str) -> bool:
    low = (text or "").lower()
    if any(m in low for m in _FILL_MARKERS):
        return True
    # "thêm … vào ngày N" is a fill request even if worded loosely.
    return bool(re.search(r"thêm.*ngày\s*\d", low))


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


def parse_target_days(text: str, num_days: int) -> List[int]:
    """1-based day numbers named in the message ("ngày 2 và ngày 3" → [2,3]); [] if none."""
    low = (text or "").lower()
    days = sorted({
        int(n) for n in re.findall(r"ngày\s*(\d{1,2})", low)
        if 1 <= int(n) <= num_days
    })
    return days


def _board_travelers(board: Dict[str, Any]) -> int:
    best = 0
    for lst in board.get("lists") or []:
        for card in (lst.get("cards") or []) if isinstance(lst, dict) else []:
            try:
                pc = card.get("participantCount")
                if pc:
                    best = max(best, int(pc))
            except (TypeError, ValueError):
                pass
    return best or 2


def build_constraints_from_board(board: Dict[str, Any], num_days: int) -> Constraints:
    """Infer trip constraints from the plan so the composer can regenerate real venues."""
    title = board.get("planTitle") or board.get("title") or ""
    desc = board.get("description") or ""
    dest = _canonical_city(f"{title} {desc}")
    if not dest:
        # Fall back to a city named in any card address.
        for lst in board.get("lists") or []:
            for card in (lst.get("cards") or []) if isinstance(lst, dict) else []:
                dest = _canonical_city(str(card.get("text") or "") + " " + str(card.get("description") or ""))
                if dest:
                    break
            if dest:
                break
    return Constraints(destination=dest, num_days=num_days, travelers=_board_travelers(board))


def _rec_dict(act: DraftActivity) -> Optional[Dict[str, Any]]:
    r = act.recommendation
    if not r:
        return None
    return {
        "kind": r.catalog_kind,
        "id": r.catalog_id,
        "slug": r.catalog_slug,
        "name": r.name,
        "latitude": r.latitude,
        "longitude": r.longitude,
        "coverImageUrl": r.cover_image_url,
        "avgRating": r.avg_rating,
    }


def _activity_to_create_op(act: DraftActivity, day_index: int) -> EditOperation:
    op = EditOperation(
        op="create_card",
        day_index=day_index,
        text=act.title,
        description=act.description or None,
        start_time=act.start_time,
        end_time=act.end_time,
        duration_minutes=act.duration_minutes,
        activity_type=getattr(act.activity_type, "value", act.activity_type),
        estimated_cost_vnd=act.estimated_cost_vnd,
        unit_price_vnd=act.unit_price_vnd,
        quantity=act.quantity,
        participant_count=act.quantity,
        location_name=act.location_name,
        address=act.address,
        note=act.note,
        route_hint=act.route_hint,
        distance_text=act.distance_text,
        transport_mode=act.transport_mode,
        recommendation=_rec_dict(act),
    )
    op.summary = operation_summary(op)
    return op


async def build_fill_ops(
    board: Dict[str, Any], message: str, composer: DraftComposer
) -> List[EditOperation]:
    """`create_card` ops that populate the target days with real, timed activities.

    Target = the day numbers named in the message, else every EMPTY day on the board.
    Returns [] when there's nothing to do or the destination can't be inferred."""
    day_lists = _ordered_day_lists(board)
    num_days = len(day_lists)
    if num_days == 0:
        return []

    targets = parse_target_days(message, num_days)
    if not targets:
        targets = [
            i + 1 for i, lst in enumerate(day_lists) if not (lst.get("cards") or [])
        ]
    if not targets:
        return []

    constraints = build_constraints_from_board(board, num_days)
    if not constraints.destination:
        return []

    draft = await composer.compose(constraints)
    ops: List[EditOperation] = []
    for day_i in targets:
        if day_i < 1 or day_i > len(draft.days):
            continue
        for act in draft.days[day_i - 1].activities:
            ops.append(_activity_to_create_op(act, day_i))
    return ops
