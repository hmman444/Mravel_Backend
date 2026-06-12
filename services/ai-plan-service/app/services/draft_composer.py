"""Geo-aware, per-person itinerary scheduler (deterministic fallback).

This is the safety-net path used when no LLM is configured or the agent fails. It
produces a DENSE, realistic day that mirrors the reference Đà Nẵng spreadsheet:

  ăn sáng → cafe sáng → đi chơi → ăn trưa → nghỉ trưa (về KS) → đi chơi
  → ăn tối → đi chơi tối → về khách sạn nghỉ ngơi

Key properties:
  * Geography: venues are clustered per day by proximity to the hotel, start times
    are chained through REAL travel time (haversine + mode), and a homestay far from
    the centre genuinely pushes activities later.
  * Per-person cost: meals/tickets scale by traveler count; lodging is per room×night.
    Prices come from the catalog when present, else realistic VN bands (never flat 100k).
  * Full cards: address, route_hint, distance_text, transport_mode, alternative venue
    in the note — the same columns the spreadsheet has.

The LLM agent path produces even richer plans (and can web-search live prices); this
module guarantees a good baseline without one.
"""

import logging
from datetime import date, time, timedelta
from typing import Any, Dict, List, Optional, Tuple

from app.clients.catalog_client import CatalogClient
from app.models.session import (
    Constraints,
    DraftActivity,
    DraftDay,
    PlanActivityType,
    PlanDraft,
    RecommendationRef,
)
from app.services import pricing
from app.services.geo import leg_between, nearest_index

logger = logging.getLogger("ai_plan.composer")

DAY_START = time(6, 30)


# Slot templates per pace. Each slot: (key, category, duration_min, anchor).
# `anchor` is the EARLIEST realistic clock time for that slot. The scheduler chains
# real travel time but never starts a slot before its anchor — so lunch lands ~12:00,
# dinner ~19:00 and the day extends to ~22:00 even when the morning had few stops
# (this fixes "evening" firing at 13:11 and "về nghỉ ngơi" at 15:00).
# category ∈ breakfast|cafe|sight|lunch|rest|dinner|evening|sleep
_PACE_SLOTS: Dict[str, List[Tuple[str, str, int, Optional[time]]]] = {
    "relaxed": [
        ("breakfast", "breakfast", 60, time(7, 30)),
        ("morning_sight", "sight", 150, time(9, 0)),
        ("lunch", "lunch", 75, time(11, 45)),
        ("noon_rest", "rest", 120, time(13, 15)),
        ("afternoon_sight", "sight", 150, time(15, 30)),
        ("dinner", "dinner", 90, time(18, 45)),
        ("evening", "evening", 90, time(20, 15)),
        ("sleep", "sleep", 0, time(22, 0)),
    ],
    "balanced": [
        ("breakfast", "breakfast", 60, time(7, 0)),
        ("morning_cafe", "cafe", 45, time(8, 15)),
        ("morning_sight", "sight", 120, time(9, 15)),
        ("lunch", "lunch", 75, time(11, 45)),
        ("noon_rest", "rest", 90, time(13, 15)),
        ("afternoon_sight", "sight", 120, time(14, 45)),
        ("evening_cafe", "cafe", 60, time(16, 45)),
        ("dinner", "dinner", 90, time(18, 45)),
        ("evening", "evening", 110, time(20, 0)),
        ("sleep", "sleep", 0, time(22, 0)),
    ],
    "packed": [
        ("breakfast", "breakfast", 50, time(6, 45)),
        ("morning_cafe", "cafe", 40, time(7, 45)),
        ("morning_sight", "sight", 110, time(8, 40)),
        ("midmorning_sight", "sight", 90, time(10, 40)),
        ("lunch", "lunch", 70, time(12, 0)),
        ("noon_rest", "rest", 75, time(13, 15)),
        ("afternoon_sight", "sight", 120, time(14, 40)),
        ("late_sight", "sight", 90, time(16, 50)),
        ("evening_cafe", "cafe", 50, time(18, 0)),
        ("dinner", "dinner", 90, time(19, 0)),
        ("evening", "evening", 120, time(20, 40)),
        ("sleep", "sleep", 0, time(22, 45)),
    ],
}


def _clamp_anchor(chained: time, anchor: Optional[time]) -> time:
    """Start no earlier than the anchor — keeps each slot in its realistic part of day."""
    if anchor and chained < anchor:
        return anchor
    return chained


def _time_prefix(start: time) -> str:
    """Label a flexible activity by the actual clock hour, not the slot key."""
    h = start.hour
    if h >= 18:
        return "Tối"
    if h >= 13:
        return "Chiều"
    return "Sáng"


def _coords(item: Optional[Dict[str, Any]]) -> Optional[Tuple[float, float]]:
    if not item:
        return None
    lat, lng = item.get("latitude"), item.get("longitude")
    if lat is None or lng is None:
        return None
    try:
        return (float(lat), float(lng))
    except (TypeError, ValueError):
        return None


def _address(item: Optional[Dict[str, Any]]) -> Optional[str]:
    if not item:
        return None
    parts = [
        item.get("addressLine"),
        item.get("wardName"),
        item.get("districtName"),
        item.get("cityName") or item.get("provinceName"),
    ]
    out: List[str] = []
    for p in parts:
        p = (p or "").strip()
        if p and p not in out:
            out.append(p)
    return ", ".join(out) or None


def _add_minutes(t: time, minutes: int) -> time:
    # Cap at 23:59 instead of rolling into the next day — a long final leg must not
    # wrap a late activity back to the morning (which would break chronological order).
    total = t.hour * 60 + t.minute + minutes
    total = max(0, min(total, 23 * 60 + 59))
    return time(total // 60, total % 60)


def _hhmm(t: time) -> str:
    return t.strftime("%H:%M")


def _short_name(name: Optional[str]) -> str:
    return (name or "điểm đến").strip()


class _Picker:
    """Round-robin picker that prefers the nearest unused item to a moving cursor.
    Resets `used` when the pool is exhausted so long trips keep filling slots."""

    def __init__(self, items: List[Dict[str, Any]]) -> None:
        self._items = items
        self._coords = [_coords(it) for it in items]
        self._used: set = set()

    def empty(self) -> bool:
        return not self._items

    def next_near(
        self, origin: Optional[Tuple[float, float]], avoid_name: Optional[str] = None
    ) -> Optional[Dict[str, Any]]:
        if not self._items:
            return None
        if len(self._used) >= len(self._items):
            self._used.clear()
        idx = nearest_index(origin, self._coords, self._used)
        if idx is None:
            return None
        # Don't pick the venue we're already standing at (back-to-back same spot)
        # when a different one is still available.
        if (
            avoid_name
            and (self._items[idx].get("name") or "").strip() == avoid_name.strip()
            and len(self._items) - len(self._used) > 1
        ):
            self._used.add(idx)
            idx2 = nearest_index(origin, self._coords, self._used)
            if idx2 is not None:
                idx = idx2
        self._used.add(idx)
        return self._items[idx]

    def peek_alternative(self, origin: Optional[Tuple[float, float]]) -> Optional[Dict[str, Any]]:
        """Second-nearest unused item — surfaced as an alternative in the note."""
        if len(self._items) - len(self._used) < 1:
            return None
        idx = nearest_index(origin, self._coords, self._used)
        return self._items[idx] if idx is not None else None


class DraftComposer:
    def __init__(self, catalog: CatalogClient) -> None:
        self._catalog = catalog

    async def compose(self, constraints: Constraints) -> PlanDraft:
        if not constraints.is_minimally_complete():
            raise ValueError("Constraints must include destination, start_date, end_date")

        destination = constraints.destination or ""
        travelers = max(1, constraints.travelers or 1)
        pace = (constraints.pace or "balanced").lower()
        if pace not in _PACE_SLOTS:
            pace = "balanced"
        slots = _PACE_SLOTS[pace]
        # Resolve concrete dates: when the user gave only a trip length, the start is
        # anchored to today (resolved_date_range). day_date below counts from start_date.
        start_date, end_date = constraints.resolved_date_range()
        duration = constraints.duration_days()
        nights = max(1, duration - 1)

        # Larger pools so each day gets distinct, well-clustered venues.
        from app.agent.tools import location_slug

        loc = location_slug(destination)
        hotels = await self._catalog.search_hotels(loc, page=0, size=8)
        restaurants = await self._catalog.search_restaurants(loc, page=0, size=30)
        cafes = await self._catalog.search_restaurants(loc, page=0, size=20, cuisine="cafe")
        places = await self._catalog.search_places(destination, page=0, size=30)

        hotel = hotels[0] if hotels else None
        hotel_coords = _coords(hotel)
        hotel_name = _short_name(hotel.get("name")) if hotel else "khách sạn"

        food_picker = _Picker(restaurants)
        cafe_picker = _Picker(cafes if cafes else restaurants)
        place_picker = _Picker(places)

        warnings: List[str] = []
        if not hotel:
            warnings.append("No hotels found in catalog for this destination")
        if not places:
            warnings.append("No places found in catalog for this destination")
        if not restaurants:
            warnings.append("No restaurants found in catalog for this destination")

        days: List[DraftDay] = []
        total_cost = 0

        for day_index in range(1, duration + 1):
            day_date = start_date + timedelta(days=day_index - 1)
            cursor = hotel_coords
            cursor_name = hotel_name
            t = DAY_START
            acts: List[DraftActivity] = []

            # Day 1: rent a motorbike for the whole trip (per vehicle).
            if day_index == 1 and hotel:
                bikes = pricing.bikes_for(travelers)
                rental = pricing.MOTORBIKE_PER_DAY * bikes * duration
                t = _add_minutes(t, 30)
                acts.append(
                    _activity(
                        title=f"Thuê xe máy ({bikes} xe cho cả chuyến)",
                        day_index=day_index,
                        atype=PlanActivityType.TRANSPORT,
                        start=t,
                        duration=30,
                        cost=rental,
                        unit_price=pricing.MOTORBIKE_PER_DAY * duration,
                        quantity=bikes,
                        note=f"{bikes} xe × {pricing.MOTORBIKE_PER_DAY:,}đ/ngày × {duration} ngày — có thể rẻ hơn khi thuê theo tuần.",
                        transport_mode="xe máy",
                    )
                )
                total_cost += rental
                t = _add_minutes(t, 30)

            for key, category, dur, anchor in slots:
                act, cost, cursor, cursor_name, t = self._build_slot(
                    category=category,
                    key=key,
                    duration=dur,
                    anchor=anchor,
                    day_index=day_index,
                    is_first_day=(day_index == 1),
                    is_last_day=(day_index == duration),
                    travelers=travelers,
                    nights=nights,
                    t=t,
                    cursor=cursor,
                    cursor_name=cursor_name,
                    hotel=hotel,
                    hotel_coords=hotel_coords,
                    hotel_name=hotel_name,
                    food_picker=food_picker,
                    cafe_picker=cafe_picker,
                    place_picker=place_picker,
                )
                if act is None:
                    continue
                acts.append(act)
                total_cost += cost

            days.append(
                DraftDay(
                    day_index=day_index,
                    day_date=day_date,
                    title=_day_title(day_index, duration, destination),
                    activities=acts,
                )
            )

        if constraints.budget_total_vnd and total_cost > constraints.budget_total_vnd:
            warnings.append(
                f"Estimated cost {total_cost:,} VND exceeds budget {constraints.budget_total_vnd:,} VND"
            )

        return PlanDraft(
            summary=(
                f"Lịch trình {duration} ngày tại {destination} cho {travelers} người "
                f"(nhịp {pace})"
            ),
            destination=destination,
            start_date=start_date,
            end_date=end_date,
            travelers=travelers,
            estimated_total_cost_vnd=total_cost,
            days=days,
            warnings=warnings,
        )

    def _build_slot(
        self,
        *,
        category: str,
        key: str,
        duration: int,
        anchor: Optional[time],
        day_index: int,
        is_first_day: bool,
        is_last_day: bool,
        travelers: int,
        nights: int,
        t: time,
        cursor: Optional[Tuple[float, float]],
        cursor_name: str,
        hotel: Optional[Dict[str, Any]],
        hotel_coords: Optional[Tuple[float, float]],
        hotel_name: str,
        food_picker: _Picker,
        cafe_picker: _Picker,
        place_picker: _Picker,
    ) -> Tuple[Optional[DraftActivity], int, Optional[Tuple[float, float]], str, time]:
        """Build one slot. Returns (activity|None, cost, new_cursor, new_cursor_name, new_time).

        Start time = max(chained travel time, slot anchor) so each slot lands in its
        realistic part of day. An empty catalog pool yields a generic placeholder
        instead of skipping the slot (keeps density and correct times)."""

        # --- Rest / sleep: travel back to the hotel. ---
        if category in ("rest", "sleep"):
            leg = leg_between(cursor, hotel_coords)
            travel = leg.minutes if leg else 15
            start = _clamp_anchor(_add_minutes(t, travel), anchor)
            quantity = 1
            unit = 0
            if category == "sleep":
                title = "Về khách sạn nghỉ ngơi"
                dur = 0
                cost = 0
            elif is_first_day:
                title = f"Nhận phòng + nghỉ trưa tại {hotel_name}"
                dur = duration
                cost = pricing.lodging_total(hotel, nights, travelers)
                quantity = pricing.rooms_for(travelers)
                unit = cost // quantity if quantity else cost
            else:
                title = "Về khách sạn nghỉ trưa"
                dur = duration
                cost = 0
            act = _activity(
                title=title,
                day_index=day_index,
                atype=PlanActivityType.STAY,
                start=start,
                duration=dur,
                cost=cost,
                unit_price=unit,
                quantity=quantity,
                rec=_rec("HOTEL", hotel) if hotel else None,
                address=_address(hotel),
                route_hint=f"{cursor_name} → {hotel_name}" if leg else None,
                distance_text=leg.distance_text if leg else None,
                transport_mode=leg.mode if leg else None,
                note=(
                    f"Lưu trú {nights} đêm × {pricing.rooms_for(travelers)} phòng"
                    if is_first_day else "Nghỉ ngơi, nạp lại năng lượng"
                ),
                description=("Trả phòng vào ngày cuối, dọn hành lý." if is_last_day and category == "sleep" else ""),
            )
            new_t = _add_minutes(start, dur)
            return act, cost, hotel_coords, hotel_name, new_t

        # --- Meals / cafe: pick nearest restaurant; surface a 2nd option in the note. ---
        if category in ("breakfast", "cafe", "lunch", "dinner"):
            picker = cafe_picker if category == "cafe" else food_picker
            item = picker.next_near(cursor, avoid_name=cursor_name)
            label = {
                "breakfast": "Ăn sáng", "cafe": "Cafe", "lunch": "Ăn trưa", "dinner": "Ăn tối",
            }[category]
            if item is None:
                # No catalog restaurant — keep the slot with a generic suggestion so the
                # day stays dense and correctly timed.
                start = _clamp_anchor(_add_minutes(t, 10), anchor)
                per_person = pricing.meal_cost_per_person(None, category)
                cost = pricing.total_for(per_person, travelers)
                act = _activity(
                    title=f"{label} (quán địa phương)",
                    day_index=day_index,
                    atype=PlanActivityType.FOOD,
                    start=start,
                    duration=duration,
                    cost=cost,
                    unit_price=per_person,
                    quantity=travelers,
                    note=f"~{per_person:,}đ/người (ước tính) · gợi ý chọn quán địa phương gần đây",
                )
                return act, cost, cursor, cursor_name, _add_minutes(start, duration)
            alt = picker.peek_alternative(_coords(item))
            leg = leg_between(cursor, _coords(item))
            travel = leg.minutes if leg else 12
            start = _clamp_anchor(_add_minutes(t, travel), anchor)
            per_person = pricing.meal_cost_per_person(item, category)
            cost = pricing.total_for(per_person, travelers)
            name = _short_name(item.get("name"))
            note_bits = [f"~{per_person:,}đ/người"]
            if alt:
                alt_addr = _address(alt)
                note_bits.append(
                    f"Lựa chọn khác: {_short_name(alt.get('name'))}" + (f" ({alt_addr})" if alt_addr else "")
                )
            moved = leg is not None and name != cursor_name
            act = _activity(
                title=f"{label}: {name}",
                day_index=day_index,
                atype=PlanActivityType.FOOD,
                start=start,
                duration=duration,
                cost=cost,
                unit_price=per_person,
                quantity=travelers,
                rec=_rec("RESTAURANT", item),
                address=_address(item),
                route_hint=f"{cursor_name} → {name}" if moved else None,
                distance_text=leg.distance_text if moved else None,
                transport_mode=leg.mode if moved else None,
                note=" · ".join(note_bits),
                description=f"{label} tại {name}" + (f" ({', '.join(item.get('cuisineNames')[:2])})" if isinstance(item.get("cuisineNames"), list) and item.get("cuisineNames") else ""),
            )
            new_t = _add_minutes(start, duration)
            return act, cost, _coords(item), name, new_t

        # --- Sightseeing / evening: pick nearest place. ---
        if category in ("sight", "evening"):
            item = place_picker.next_near(cursor, avoid_name=cursor_name)
            if item is None:
                start = _clamp_anchor(_add_minutes(t, 12), anchor)
                prefix = _time_prefix(start)
                act = _activity(
                    title=f"{prefix}: Tự do khám phá khu vực",
                    day_index=day_index,
                    atype=PlanActivityType.OTHER,
                    start=start,
                    duration=duration,
                    cost=0,
                    note="Dạo quanh khu vực / nghỉ ngơi — chưa có địa điểm trong catalog cho khung này.",
                )
                return act, 0, cursor, cursor_name, _add_minutes(start, duration)
            leg = leg_between(cursor, _coords(item))
            travel = leg.minutes if leg else 15
            start = _clamp_anchor(_add_minutes(t, travel), anchor)
            per_person = pricing.place_cost_per_person(item)
            cost = pricing.total_for(per_person, travelers) if per_person else 0
            name = _short_name(item.get("name"))
            atype = PlanActivityType.ENTERTAIN if category == "evening" else PlanActivityType.SIGHTSEEING
            # Label by actual clock hour, not the slot key (no "Tối:" at 13:11).
            title = f"{_time_prefix(start)}: {name}" if category == "evening" else name
            moved = leg is not None and name != cursor_name
            act = _activity(
                title=title,
                day_index=day_index,
                atype=atype,
                start=start,
                duration=duration,
                cost=cost,
                unit_price=per_person,
                quantity=travelers if per_person else 1,
                rec=_rec("PLACE", item),
                address=_address(item),
                route_hint=f"{cursor_name} → {name}" if moved else None,
                distance_text=leg.distance_text if moved else None,
                transport_mode=leg.mode if moved else None,
                note=(f"~{per_person:,}đ/người vé vào cửa" if per_person else "Miễn phí / vé rẻ"),
            )
            new_t = _add_minutes(start, duration)
            return act, cost, _coords(item), name, new_t

        return None, 0, cursor, cursor_name, t


def _day_title(day_index: int, duration: int, destination: str) -> str:
    if day_index == 1:
        return f"Ngày 1 — Đến {destination}, nhận phòng & khám phá"
    if day_index == duration:
        return f"Ngày {day_index} — Khám phá & trả phòng"
    return f"Ngày {day_index} — Khám phá {destination}"


def _rec(kind: str, item: Optional[Dict[str, Any]]) -> Optional[RecommendationRef]:
    if not item:
        return None
    try:
        raw_id = item.get("id")
        return RecommendationRef(
            catalog_kind=kind,
            catalog_id=str(raw_id) if raw_id is not None else None,
            catalog_slug=item.get("slug"),
            name=item.get("name") or "Unknown",
            latitude=item.get("latitude"),
            longitude=item.get("longitude"),
            cover_image_url=item.get("coverImageUrl"),
            avg_rating=item.get("avgRating"),
            estimated_cost_vnd=item.get("minNightlyPrice") or item.get("minPricePerPerson"),
        )
    except Exception as exc:  # noqa: BLE001
        logger.warning("skip malformed catalog item (%s): %s", kind, exc)
        return None


def _activity(
    *,
    title: str,
    day_index: int,
    atype: PlanActivityType,
    start: time,
    duration: int,
    cost: int = 0,
    rec: Optional[RecommendationRef] = None,
    address: Optional[str] = None,
    route_hint: Optional[str] = None,
    distance_text: Optional[str] = None,
    transport_mode: Optional[str] = None,
    note: Optional[str] = None,
    description: str = "",
    unit_price: Optional[int] = None,
    quantity: int = 1,
) -> DraftActivity:
    end = _add_minutes(start, duration) if duration else start
    return DraftActivity(
        title=title,
        description=description,
        day_index=day_index,
        start_time=_hhmm(start),
        end_time=_hhmm(end),
        duration_minutes=max(0, duration),
        activity_type=atype,
        estimated_cost_vnd=cost,
        unit_price_vnd=unit_price,
        quantity=max(1, quantity),
        recommendation=rec,
        location_name=rec.name if rec else None,
        address=address,
        route_hint=route_hint,
        distance_text=distance_text,
        transport_mode=transport_mode,
        note=note,
    )
