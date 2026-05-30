"""Cost estimation that is per-person aware and uses real prices when available.

Priority for every activity cost:
  1. A concrete catalog price (restaurant minPricePerPerson, hotel minNightlyPrice,
     place minPrice) — the source of truth.
  2. A `priceLevel` hint from the catalog mapped to a realistic VND band.
  3. A category heuristic band (NOT a flat 100k) reflecting typical 2024-2025
     Vietnamese prices. The LLM agent can override these with web_search results.

All meal / ticket / experience costs are multiplied by the traveler count.
Lodging is charged per ROOM (≈2 guests/room) × nights, not per person.
"""

from __future__ import annotations

import math
from typing import Any, Dict, Optional

# Per-person VND bands (low, typical, high) for a mid-range domestic trip.
_BANDS: Dict[str, tuple] = {
    "breakfast": (40_000, 60_000, 90_000),
    "cafe": (35_000, 55_000, 80_000),
    "lunch": (90_000, 150_000, 250_000),
    "dinner": (120_000, 200_000, 350_000),
    "streetfood": (30_000, 60_000, 100_000),
    "seafood": (300_000, 450_000, 700_000),
    "entrance": (50_000, 120_000, 250_000),  # paid attraction ticket / person
    "experience": (200_000, 400_000, 800_000),  # combo / theme park / activity
}

# priceLevel (catalog enum or $-count) → multiplier on the "typical" band value.
_PRICE_LEVEL_MULT = {
    "budget": 0.6, "economy": 0.6, "cheap": 0.6, "1": 0.6, "$": 0.6,
    "moderate": 1.0, "mid": 1.0, "standard": 1.0, "2": 1.0, "$$": 1.0,
    "expensive": 1.6, "premium": 1.6, "high": 1.6, "3": 1.6, "$$$": 1.6,
    "luxury": 2.4, "luxe": 2.4, "4": 2.4, "$$$$": 2.4,
}

# Local transport / logistics (NOT per-person unless noted).
MOTORBIKE_PER_DAY = 130_000          # per bike per day (city rental)
TAXI_SHORT = 60_000                  # a short in-city car/taxi hop
TAXI_MEDIUM = 150_000                # cross-town
INTERCITY_BUS_PER_PERSON = 120_000   # short-haul (e.g. Đà Nẵng ↔ Hội An round-ish)


def rooms_for(travelers: int) -> int:
    return max(1, math.ceil(max(1, travelers) / 2))


def bikes_for(travelers: int) -> int:
    return max(1, math.ceil(max(1, travelers) / 2))


def _num(value: Any) -> Optional[float]:
    if value is None:
        return None
    try:
        f = float(value)
        return f if f > 0 else None
    except (TypeError, ValueError):
        return None


def _band_value(category: str, level: Optional[Any]) -> int:
    low, mid, high = _BANDS.get(category, _BANDS["lunch"])
    if level is not None:
        mult = _PRICE_LEVEL_MULT.get(str(level).strip().lower())
        if mult is not None:
            return int(mid * mult)
    return mid


def meal_cost_per_person(item: Optional[Dict[str, Any]], meal_type: str) -> int:
    """Resolve a per-person meal price. meal_type ∈ breakfast|cafe|lunch|dinner|streetfood|seafood."""
    if item:
        lo = _num(item.get("min_price_per_person_vnd") or item.get("minPricePerPerson"))
        hi = _num(item.get("max_price_per_person_vnd") or item.get("maxPricePerPerson"))
        if lo and hi:
            return int((lo + hi) / 2)
        if lo:
            return int(lo)
        level = item.get("price_level") or item.get("priceLevel")
        if level is not None:
            return _band_value(meal_type, level)
    return _band_value(meal_type, None)


def place_cost_per_person(item: Optional[Dict[str, Any]]) -> int:
    """Resolve a per-person attraction cost. Returns 0 for free spots (beaches,
    pagodas, viewpoints) when the catalog gives no price and no paid signal."""
    if item:
        lo = _num(item.get("min_price_vnd") or item.get("minPrice"))
        if lo:
            return int(lo)
        level = item.get("price_level") or item.get("priceLevel")
        if level is not None:
            return _band_value("entrance", level)
    return 0


def total_for(unit_per_person: int, travelers: int) -> int:
    return int(unit_per_person) * max(1, travelers)


def lodging_total(item: Optional[Dict[str, Any]], nights: int, travelers: int) -> int:
    nightly = None
    if item:
        nightly = _num(item.get("min_nightly_price_vnd") or item.get("minNightlyPrice"))
    if not nightly:
        nightly = 800_000  # mid 3-star fallback when catalog price is missing
    return int(nightly) * max(1, nights) * rooms_for(travelers)
