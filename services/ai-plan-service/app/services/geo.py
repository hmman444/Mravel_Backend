"""Geographic helpers for itinerary scheduling.

The catalog stores latitude/longitude on every hotel, restaurant and place, so we
can reason about real travel: a homestay far from the centre genuinely costs time
between activities, and consecutive stops should be clustered to avoid zig-zagging
across the city.

Everything here is pure + deterministic so it can be unit-tested and reused by both
the deterministic composer and (indirectly, via the `route_legs` tool) the LLM agent.
"""

from __future__ import annotations

import math
from dataclasses import dataclass
from typing import List, Optional, Tuple

# Average door-to-door speeds in a Vietnamese city, INCLUDING parking / lights /
# walking the last stretch. Deliberately conservative so the schedule has slack.
_WALK_KMH = 4.5
_BIKE_KMH = 22.0   # xe máy in mixed city traffic
_CAR_KMH = 28.0    # taxi / ô tô; intercity stretches average higher (see below)
_INTERCITY_KMH = 45.0  # long highway legs (e.g. Đà Nẵng ↔ Hội An)

# Distance thresholds (km) that pick the natural transport mode.
_WALK_MAX_KM = 0.8
_BIKE_MAX_KM = 18.0


@dataclass
class Leg:
    distance_km: float
    minutes: int
    mode: str          # "đi bộ" | "xe máy" | "taxi" | "ô tô"
    distance_text: str  # "500m", "~2km", "30km"


def haversine_km(a: Optional[Tuple[float, float]], b: Optional[Tuple[float, float]]) -> Optional[float]:
    """Great-circle distance in km between (lat, lng) pairs. None if either is missing."""
    if not a or not b:
        return None
    lat1, lng1 = a
    lat2, lng2 = b
    if None in (lat1, lng1, lat2, lng2):
        return None
    r = 6371.0
    p1, p2 = math.radians(lat1), math.radians(lat2)
    dphi = math.radians(lat2 - lat1)
    dlam = math.radians(lng2 - lng1)
    h = math.sin(dphi / 2) ** 2 + math.cos(p1) * math.cos(p2) * math.sin(dlam / 2) ** 2
    return 2 * r * math.asin(min(1.0, math.sqrt(h)))


def format_distance(km: Optional[float]) -> str:
    if km is None:
        return ""
    if km < 1.0:
        return f"{int(round(km * 1000 / 50.0) * 50)}m"  # round to nearest 50m
    if km < 10:
        return f"~{km:.1f}km"
    return f"{int(round(km))}km"


def pick_mode(km: float) -> str:
    if km <= _WALK_MAX_KM:
        return "đi bộ"
    if km <= _BIKE_MAX_KM:
        return "xe máy"
    return "ô tô"


def travel_minutes(km: float, mode: Optional[str] = None) -> int:
    mode = mode or pick_mode(km)
    if mode == "đi bộ":
        speed = _WALK_KMH
    elif mode == "xe máy":
        speed = _BIKE_KMH
    elif km > _BIKE_MAX_KM:
        speed = _INTERCITY_KMH
    else:
        speed = _CAR_KMH
    minutes = (km / speed) * 60.0
    # Fixed overhead: get ready, park, find the spot. Min 5 minutes for any hop.
    overhead = 5 if mode == "đi bộ" else 8
    return max(5, int(round(minutes + overhead)))


def leg_between(
    a: Optional[Tuple[float, float]],
    b: Optional[Tuple[float, float]],
    mode: Optional[str] = None,
) -> Optional[Leg]:
    """Full travel leg between two coordinates, or None if coords are unknown."""
    km = haversine_km(a, b)
    if km is None:
        return None
    chosen = mode or pick_mode(km)
    return Leg(
        distance_km=round(km, 2),
        minutes=travel_minutes(km, chosen),
        mode=chosen,
        distance_text=format_distance(km),
    )


def nearest_index(
    origin: Optional[Tuple[float, float]],
    candidates: List[Optional[Tuple[float, float]]],
    used: set,
) -> Optional[int]:
    """Index of the nearest unused candidate to `origin`. Falls back to the first
    unused index when coordinates are missing (so callers always make progress)."""
    best_i: Optional[int] = None
    best_km = math.inf
    first_unused: Optional[int] = None
    for i, c in enumerate(candidates):
        if i in used:
            continue
        if first_unused is None:
            first_unused = i
        km = haversine_km(origin, c)
        if km is not None and km < best_km:
            best_km = km
            best_i = i
    return best_i if best_i is not None else first_unused


def route_legs(stops: List[dict]) -> List[dict]:
    """Compute per-leg distance/time/mode for an ordered list of stops.

    Each stop is a dict with at least `name` and optionally `latitude`/`longitude`.
    Returns one entry per leg (len(stops) - 1) describing travel from stop i to i+1.
    Used by the `route_legs` tool so the LLM gets real distances instead of guessing.
    """
    legs: List[dict] = []
    for i in range(len(stops) - 1):
        a = _coords(stops[i])
        b = _coords(stops[i + 1])
        leg = leg_between(a, b)
        legs.append(
            {
                "from": stops[i].get("name"),
                "to": stops[i + 1].get("name"),
                "distance_km": leg.distance_km if leg else None,
                "distance_text": leg.distance_text if leg else None,
                "minutes": leg.minutes if leg else None,
                "transport_mode": leg.mode if leg else None,
                "route_hint": f"{stops[i].get('name')} → {stops[i + 1].get('name')}",
            }
        )
    return legs


def _coords(stop: dict) -> Optional[Tuple[float, float]]:
    lat = stop.get("latitude")
    lng = stop.get("longitude")
    if lat is None or lng is None:
        return None
    try:
        return (float(lat), float(lng))
    except (TypeError, ValueError):
        return None
