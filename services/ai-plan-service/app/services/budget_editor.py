"""Deterministic budget-reduction editor — the offline safety net for "làm rẻ hơn".

When the LLM is unavailable (proxy down / rate-limited) the edit agent can no longer
propose changes, so a request like *"chỉnh plan 6 rẻ hơn, ngân sách 10 triệu"* used to
dead-end with an error and an unchanged plan. This module produces real, id-safe
`update_card` operations that cut the itinerary's cost toward the target budget, using
the same per-category price bands the composer uses — no LLM and no catalog call needed.

Strategy (honest, not fake):
  1. Re-price each flexible card (meals, cafes, tickets/entertainment) to a realistic
     LOW cost for its kind. This alone fixes the inflated-cafe bug where a cafe slot was
     priced as a full restaurant meal (e.g. 2,100,000đ) — the biggest source of overrun.
  2. If the total is still above the target, scale the remaining flexible costs down
     proportionally so the estimate meets the budget.
Lodging / transport cards are left untouched (can't be re-shopped without the catalog).
"""

from __future__ import annotations

import re
from typing import Any, Dict, List, Optional, Tuple

from app.agent.edits import EditOperation

# Words that signal the user wants a cheaper / budget-constrained plan.
_INTENT_MARKERS = (
    "rẻ hơn", "rẻ thôi", "giá rẻ", "tiết kiệm", "giảm chi phí", "giảm giá",
    "bớt tiền", "ít tiền", "ít tốn", "tốn ít", "ngân sách", "kinh phí",
    "budget", "cheaper", "cheap", "save money",
)

# Per-person LOW cost by category (VND) — the cheap end of pricing._BANDS.
_CHEAP_PER_PERSON = {
    "breakfast": 40_000,
    "cafe": 45_000,
    "lunch": 90_000,
    "dinner": 120_000,
    "entrance": 50_000,
}


def detect_budget_intent(text: str) -> bool:
    low = (text or "").lower()
    return any(m in low for m in _INTENT_MARKERS)


def parse_budget_vnd(text: str) -> Optional[int]:
    """Extract a VND amount from natural phrasing.

    Handles "10 triệu", "10tr", "10 củ", "10 million", "500k", "10.000.000",
    "10,000,000". Returns None when no amount is found.
    """
    low = (text or "").lower()

    # Millions: "10 triệu" / "10tr" / "10 củ" / "10 million" (allow "10,5 triệu").
    m = re.search(r"(\d+(?:[.,]\d+)?)\s*(triệu|trieu|tr|củ|cu|million|mil)\b", low)
    if m:
        return int(round(_to_float(m.group(1)) * 1_000_000))

    # Thousands: "500k" / "500 nghìn" / "500 ngàn".
    m = re.search(r"(\d+(?:[.,]\d+)?)\s*(k|nghìn|nghin|ngàn|ngan)\b", low)
    if m:
        return int(round(_to_float(m.group(1)) * 1_000))

    # Bare grouped digits: "10.000.000" / "10,000,000" / "10000000" (need ≥6 digits
    # once separators are stripped, so we don't grab a plan id like "6").
    for token in re.findall(r"\d[\d.,]*\d|\d", low):
        digits = re.sub(r"[.,]", "", token)
        if digits.isdigit() and len(digits) >= 6:
            return int(digits)
    return None


def _to_float(raw: str) -> float:
    # "10,5" (vi decimal) and "10.5" both mean 10.5; a grouping dot like "10.000"
    # is unusual before a unit word, so treat the last separator as a decimal point.
    return float(raw.replace(",", "."))


def _card_cost(card: Dict[str, Any]) -> Optional[int]:
    cost = card.get("cost")
    if isinstance(cost, dict):
        val = cost.get("estimatedCost")
        try:
            return int(val) if val is not None else None
        except (TypeError, ValueError):
            return None
    return None


def _participants(card: Dict[str, Any], default: int) -> int:
    for key in ("participantCount",):
        val = card.get(key)
        try:
            if val:
                return max(1, int(val))
        except (TypeError, ValueError):
            pass
    cost = card.get("cost")
    if isinstance(cost, dict) and cost.get("participantCount"):
        try:
            return max(1, int(cost["participantCount"]))
        except (TypeError, ValueError):
            pass
    return max(1, default)


def _meal_category(text: str) -> Optional[str]:
    low = (text or "").lower()
    if "cafe" in low or "cà phê" in low or "ca phe" in low:
        return "cafe"
    if "ăn sáng" in low or "an sang" in low or "breakfast" in low:
        return "breakfast"
    if "ăn trưa" in low or "an trua" in low or "lunch" in low:
        return "lunch"
    if "ăn tối" in low or "an toi" in low or "dinner" in low:
        return "dinner"
    return None


def _cheap_cost(card: Dict[str, Any], travelers: int) -> Optional[Tuple[int, int]]:
    """The cheap (unit_per_person, total) for a flexible card, or None if the card
    is not something we re-price (lodging / transport / free activities)."""
    atype = str(card.get("activityType") or "OTHER").upper()
    text = card.get("text") or ""
    people = _participants(card, travelers)
    if atype == "FOOD":
        cat = _meal_category(text) or "lunch"
        unit = _CHEAP_PER_PERSON[cat]
        return unit, unit * people
    if atype in ("SIGHTSEEING", "ENTERTAIN", "EVENT", "CINEMA"):
        # Only re-price if it currently has a non-trivial cost (free spots stay free).
        current = _card_cost(card)
        if current and current > _CHEAP_PER_PERSON["entrance"] * people:
            unit = _CHEAP_PER_PERSON["entrance"]
            return unit, unit * people
    return None


def _iter_day_cards(board: Dict[str, Any]):
    for lst in board.get("lists") or []:
        if not isinstance(lst, dict) or lst.get("type") != "DAY":
            continue
        for card in lst.get("cards") or []:
            if isinstance(card, dict):
                yield lst, card


def propose_budget_cuts(
    board: Dict[str, Any], target_vnd: Optional[int], travelers: int = 2
) -> List[EditOperation]:
    """Build id-safe `update_card` ops that reduce the plan's cost toward `target_vnd`.

    Returns [] when nothing can be reduced (so the caller can fall back to an error
    message rather than proposing a no-op)."""
    # First pass: re-price each flexible card to its cheap band.
    reductions: List[Dict[str, Any]] = []  # {list_id, card_id, text, unit, total, orig}
    fixed_total = 0  # lodging/transport/free — not reducible
    for lst, card in _iter_day_cards(board):
        orig = _card_cost(card) or 0
        cheap = _cheap_cost(card, travelers)
        if cheap is None:
            fixed_total += orig
            continue
        unit, total = cheap
        # Never raise a card's cost — only cut. If it's already cheaper, keep it.
        if orig and total >= orig:
            fixed_total += orig
            continue
        reductions.append(
            {
                "list_id": lst.get("id"),
                "card_id": card.get("id"),
                "text": card.get("text") or "",
                "unit": unit,
                "total": total,
                "orig": orig,
                "people": _participants(card, travelers),
            }
        )

    if not reductions:
        return []

    # Second pass: if still over target after the cheap re-pricing, scale the
    # flexible costs down proportionally so the whole plan meets the budget.
    if target_vnd:
        flexible_total = sum(r["total"] for r in reductions)
        room = target_vnd - fixed_total
        if room < flexible_total and flexible_total > 0:
            scale = max(0.4, room / flexible_total) if room > 0 else 0.4
            for r in reductions:
                r["unit"] = max(10_000, int(r["unit"] * scale))
                r["total"] = r["unit"] * r["people"]

    ops: List[EditOperation] = []
    for r in reductions:
        if r["list_id"] is None or r["card_id"] is None:
            continue
        # Only the cost is changed. We deliberately do NOT set note/unit_price here:
        # those would rewrite the card's activityDataJson (edit_service._card_body) and
        # wipe the venue/recommendation/address. A cost-only update keeps the card intact.
        op = EditOperation(
            op="update_card",
            list_id=r["list_id"],
            card_id=r["card_id"],
            estimated_cost_vnd=r["total"],
        )
        saved = r["orig"] - r["total"]
        op.summary = (
            f"Giảm chi phí “{_shorten(r['text'])}” còn {r['total']:,}đ"
            + (f" (tiết kiệm {saved:,}đ)" if saved > 0 else "")
        )
        ops.append(op)
    return ops


def _shorten(text: str, limit: int = 40) -> str:
    text = " ".join((text or "").split())
    return text if len(text) <= limit else text[: limit - 1].rstrip() + "…"
