"""Unit tests for the geo-aware scheduler, geo helpers and per-person pricing."""

from datetime import date

import pytest

from app.models.session import Constraints, PlanActivityType
from app.services import pricing
from app.services.draft_composer import DraftComposer
from app.services.geo import format_distance, haversine_km, leg_between, pick_mode, route_legs


# ---------- geo ----------


def test_haversine_known_distance():
    # Đà Nẵng centre → Hội An ancient town ≈ 27-30 km.
    km = haversine_km((16.0544, 108.2022), (15.8801, 108.3380))
    assert 24 < km < 34


def test_pick_mode_thresholds():
    assert pick_mode(0.4) == "đi bộ"
    assert pick_mode(5) == "xe máy"
    assert pick_mode(40) == "ô tô"


def test_format_distance():
    assert format_distance(0.45).endswith("m")
    assert format_distance(2.3) == "~2.3km"
    assert format_distance(30.0) == "30km"


def test_leg_between_none_when_missing_coords():
    assert leg_between(None, (16.0, 108.0)) is None
    leg = leg_between((16.0, 108.0), (16.05, 108.05))
    assert leg and leg.minutes > 0 and leg.distance_km > 0


def test_route_legs_counts_and_modes():
    stops = [
        {"name": "A", "latitude": 16.0, "longitude": 108.0},
        {"name": "B", "latitude": 16.01, "longitude": 108.01},
        {"name": "C"},  # missing coords → leg distance None
    ]
    legs = route_legs(stops)
    assert len(legs) == 2
    assert legs[0]["distance_km"] is not None
    assert legs[1]["distance_km"] is None  # C has no coords


# ---------- pricing ----------


def test_rooms_and_bikes_scale():
    assert pricing.rooms_for(1) == 1
    assert pricing.rooms_for(2) == 1
    assert pricing.rooms_for(4) == 2
    assert pricing.rooms_for(5) == 3
    assert pricing.bikes_for(4) == 2


def test_meal_cost_prefers_catalog_price():
    item = {"min_price_per_person_vnd": 100_000, "max_price_per_person_vnd": 200_000}
    assert pricing.meal_cost_per_person(item, "lunch") == 150_000  # midpoint


def test_meal_cost_falls_back_to_band():
    assert pricing.meal_cost_per_person(None, "breakfast") > 0


def test_total_scales_by_travelers():
    assert pricing.total_for(100_000, 4) == 400_000


def test_lodging_per_room_per_night():
    item = {"min_nightly_price_vnd": 1_000_000}
    # 2 nights, 4 travelers → 2 rooms → 4,000,000
    assert pricing.lodging_total(item, nights=2, travelers=4) == 4_000_000


# ---------- scheduler ----------


class _Catalog:
    """Catalog with rich geo + price fields, like the real catalog summary DTOs."""

    async def search_places(self, q, page=0, size=10):
        return [
            {"id": "p1", "slug": "bana", "name": "Bà Nà Hills", "latitude": 15.99, "longitude": 107.99,
             "addressLine": "Hòa Vang", "provinceName": "Đà Nẵng", "avgRating": 4.6, "priceLevel": "expensive"},
            {"id": "p2", "slug": "marble", "name": "Ngũ Hành Sơn", "latitude": 16.00, "longitude": 108.26,
             "addressLine": "81 Huyền Trân Công Chúa", "districtName": "Ngũ Hành Sơn", "avgRating": 4.4},
            {"id": "p3", "slug": "mykhe", "name": "Biển Mỹ Khê", "latitude": 16.06, "longitude": 108.24,
             "addressLine": "Phước Mỹ", "districtName": "Sơn Trà", "avgRating": 4.7},
            {"id": "p4", "slug": "linhung", "name": "Chùa Linh Ứng", "latitude": 16.10, "longitude": 108.27,
             "addressLine": "Bãi Bụt, Sơn Trà", "avgRating": 4.5},
        ]

    async def search_restaurants(self, location, page=0, size=10, **filters):
        return [
            {"id": "r1", "name": "Mì Quảng 1A", "latitude": 16.06, "longitude": 108.21,
             "addressLine": "1 Hải Phòng", "minPricePerPerson": 50_000, "maxPricePerPerson": 90_000, "cuisineNames": ["Mì Quảng"]},
            {"id": "r2", "name": "Bé Mặn Hải Sản", "latitude": 16.05, "longitude": 108.24,
             "addressLine": "Võ Nguyên Giáp", "minPricePerPerson": 300_000, "maxPricePerPerson": 500_000},
            {"id": "r3", "name": "Cơm Gà A Hải", "latitude": 16.07, "longitude": 108.22,
             "addressLine": "100 Thái Phiên", "minPricePerPerson": 70_000, "maxPricePerPerson": 120_000},
        ]

    async def search_hotels(self, location, page=0, size=10, **filters):
        return [
            {"id": "h1", "slug": "resort", "name": "Furama Resort", "latitude": 16.04, "longitude": 108.25,
             "addressLine": "105 Võ Nguyên Giáp", "districtName": "Ngũ Hành Sơn", "minNightlyPrice": 2_000_000,
             "starRating": 5, "avgRating": 4.8, "distanceToCityCenterKm": 4.5},
        ]


@pytest.mark.asyncio
async def test_scheduler_dense_geo_aware_per_person():
    c = Constraints(
        destination="Đà Nẵng", start_date=date(2026, 6, 1), end_date=date(2026, 6, 3),
        travelers=4, pace="balanced", budget_total_vnd=30_000_000,
    )
    draft = await DraftComposer(_Catalog()).compose(c)

    assert len(draft.days) == 3
    day1 = draft.days[0]
    types = [a.activity_type for a in day1.activities]

    # Dense: includes meals, a midday rest (STAY) and an evening return (STAY).
    assert types.count(PlanActivityType.FOOD) >= 3
    assert types.count(PlanActivityType.STAY) >= 2  # check-in/rest + nghỉ ngơi
    assert PlanActivityType.SIGHTSEEING in types

    # Lodging charged once: 2,000,000 × 2 nights × 2 rooms = 8,000,000.
    stay_costs = [a.estimated_cost_vnd for a in day1.activities if a.activity_type == PlanActivityType.STAY]
    assert 8_000_000 in stay_costs

    # Per-person meal scaling: a 50k breakfast for 4 → 200k somewhere.
    food_costs = [a.estimated_cost_vnd for a in day1.activities if a.activity_type == PlanActivityType.FOOD]
    assert all(c % 4 == 0 for c in food_costs if c)  # every meal cost is travelers-divisible

    # Times are chronological and chained.
    times = [a.start_time for a in day1.activities if a.start_time]
    assert times == sorted(times)

    # At least one activity carries a real route + distance + mode.
    assert any(a.route_hint and a.distance_text and a.transport_mode for d in draft.days for a in d.activities)

    # Day 1 rents a motorbike (per vehicle × days), and addresses are filled.
    assert any("Thuê xe máy" in a.title for a in day1.activities)
    assert any(a.address for a in day1.activities)


@pytest.mark.asyncio
async def test_scheduler_pace_changes_density():
    base = dict(destination="Đà Nẵng", start_date=date(2026, 6, 1), end_date=date(2026, 6, 1), travelers=2)
    relaxed = await DraftComposer(_Catalog()).compose(Constraints(**base, pace="relaxed"))
    packed = await DraftComposer(_Catalog()).compose(Constraints(**base, pace="packed"))
    assert len(packed.days[0].activities) > len(relaxed.days[0].activities)


@pytest.mark.asyncio
async def test_scheduler_anchors_meals_and_extends_evening():
    c = Constraints(
        destination="Đà Nẵng", start_date=date(2026, 6, 1), end_date=date(2026, 6, 1),
        travelers=2, pace="balanced",
    )
    draft = await DraftComposer(_Catalog()).compose(c)
    acts = draft.days[0].activities
    by_title = {a.title.split(":")[0].split(" (")[0]: a for a in acts}

    # Lunch lands around midday, dinner in the evening — not compressed into the morning.
    if "Ăn trưa" in by_title:
        assert by_title["Ăn trưa"].start_time >= "11:30"
    if "Ăn tối" in by_title:
        assert by_title["Ăn tối"].start_time >= "18:00"

    # The day extends into the evening (last activity ~21:00+), not ending at 15:00.
    last_start = max(a.start_time for a in acts if a.start_time)
    assert last_start >= "20:00"

    # No activity is labelled "Tối:" while actually happening before 17:00.
    for a in acts:
        if a.title.startswith("Tối:"):
            assert a.start_time >= "17:00"


@pytest.mark.asyncio
async def test_no_day_collapse_when_no_restaurants():
    class NoFood(_Catalog):
        async def search_restaurants(self, *a, **k):
            return []

    c = Constraints(
        destination="Đà Nẵng", start_date=date(2026, 6, 1), end_date=date(2026, 6, 1),
        travelers=2, pace="balanced",
    )
    draft = await DraftComposer(NoFood()).compose(c)
    # Still dense: meal slots are substituted with generic local-eatery placeholders.
    assert len(draft.days[0].activities) >= 8
    assert any("quán địa phương" in (a.title or "") for a in draft.days[0].activities)
