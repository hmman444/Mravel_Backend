# -*- coding: utf-8 -*-
"""Cross-city venue filtering + travel-time clamp + num_days preservation.

Regression for a bogus draft: an HCM request produced a 1-day plan with a Hội An
homestay → 584km legs → every time collapsed to 23:59.
"""

import asyncio

from app.models.session import Constraints
from app.services.constraint_extractor import _normalize
from app.services.draft_composer import (
    DraftComposer,
    _canonical_city,
    _intraday_minutes,
    _same_city,
)
from app.services.geo import Leg


def test_canonical_city_and_same_city_filter():
    assert _canonical_city("TP. Hồ Chí Minh") == "TP. Hồ Chí Minh"
    hoian = {"name": "Hoa Thu Homestay", "cityName": "Hội An", "provinceName": "Quảng Nam"}
    hcm = {"name": "Mabu-KKO", "cityName": "TP. HCM"}
    unknown = {"name": "Quán ven đường"}  # no identifiable city → kept
    kept = _same_city([hoian, hcm, unknown], "TP. Hồ Chí Minh")
    names = [k["name"] for k in kept]
    assert "Mabu-KKO" in names and "Quán ven đường" in names
    assert "Hoa Thu Homestay" not in names


def test_intraday_travel_is_clamped():
    huge = Leg(distance_km=594, minutes=792, mode="ô tô", distance_text="594km")
    assert _intraday_minutes(huge) == 90
    normal = Leg(distance_km=3, minutes=16, mode="xe máy", distance_text="~3km")
    assert _intraday_minutes(normal) == 16
    assert _intraday_minutes(None) == 15


def test_normalize_keeps_regex_num_days_when_llm_returns_null():
    prior = Constraints(destination="TP. Hồ Chí Minh", num_days=3, budget_total_vnd=10_000_000)
    # LLM refinement pass returns explicit nulls — must NOT wipe the regex values.
    merged = _normalize(
        {"destination": "TP. Hồ Chí Minh", "num_days": None, "budget_total_vnd": None}, prior
    )
    assert merged.num_days == 3
    assert merged.budget_total_vnd == 10_000_000


class _FakeCatalog:
    async def search_hotels(self, loc, page=0, size=8):
        return [{"name": "Hoa Thu Homestay", "cityName": "Hội An",
                 "provinceName": "Quảng Nam", "latitude": 15.87, "longitude": 108.33,
                 "minNightlyPrice": 400_000}]

    async def search_restaurants(self, loc, page=0, size=30, cuisine=None):
        return [{"name": "Mabu-KKO", "cityName": "TP. Hồ Chí Minh",
                 "latitude": 10.782, "longitude": 106.703,
                 "minPricePerPerson": 150_000, "maxPricePerPerson": 200_000}]

    async def search_places(self, dest, page=0, size=30):
        return [
            {"name": "Bưu điện Trung tâm", "cityName": "TP. Hồ Chí Minh",
             "latitude": 10.78, "longitude": 106.699},
            {"name": "Phố cổ Hội An", "cityName": "Hội An",
             "latitude": 15.877, "longitude": 108.327},
        ]


def test_compose_drops_cross_city_and_keeps_sane_times():
    c = Constraints(destination="TP. Hồ Chí Minh", num_days=3, travelers=2,
                    budget_total_vnd=10_000_000)
    draft = asyncio.get_event_loop().run_until_complete(
        DraftComposer(_FakeCatalog()).compose(c)
    )
    assert len(draft.days) == 3  # num_days respected
    # No activity should start at the 23:59 overflow sentinel, and no Hội An venue.
    for day in draft.days:
        for a in day.activities:
            assert a.start_time != "23:59", a.title
            assert "Hội An" not in (a.title or "")
            assert "Hoa Thu" not in (a.title or "")
