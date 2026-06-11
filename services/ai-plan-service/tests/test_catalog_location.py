"""The AI must attach a catalog venue the SAME way a manual place-pick does, so the
plan-board modal pre-fills the name + location and the picker auto-focuses the item.

Covers the shared `catalog_location_fields` mapper and the approval card-body path.
"""

import json

from app.models.session import DraftActivity, PlanActivityType, RecommendationRef
from app.services.approval_service import _activity_to_card_body
from app.services.catalog_location import catalog_location_fields


def test_stay_hotel_uses_catalog_id_as_place_id():
    rec = {"kind": "HOTEL", "id": "665af0c1e", "slug": "bespoke-villa-hoi-an",
           "name": "Bespoke Villa Hoi An", "latitude": 15.8765, "longitude": 108.3205}
    fields = catalog_location_fields("STAY", rec, address="153 Lê Hồng Phong, Hội An")
    assert fields["hotelName"] == "Bespoke Villa Hoi An"
    loc = fields["hotelLocation"]
    assert loc["type"] == "HOTEL"
    # id is preferred over slug — it matches the picker list row `id`.
    assert loc["placeId"] == "665af0c1e"
    assert loc["name"] == "Bespoke Villa Hoi An"
    assert loc["fullAddress"] == "153 Lê Hồng Phong, Hội An"
    assert loc["lat"] == 15.8765 and loc["lng"] == 108.3205


def test_food_restaurant_falls_back_to_slug():
    rec = {"kind": "RESTAURANT", "slug": "me-xanh", "name": "Me Xanh"}
    fields = catalog_location_fields("FOOD", rec)
    assert fields["restaurantName"] == "Me Xanh"
    assert fields["restaurantLocation"]["type"] == "RESTAURANT"
    assert fields["restaurantLocation"]["placeId"] == "me-xanh"
    # No address/coords given -> those keys are omitted (not stored as None/"").
    assert "fullAddress" not in fields["restaurantLocation"]
    assert "lat" not in fields["restaurantLocation"]


def test_sightseeing_place_maps_to_sight_location_and_place_name():
    rec = {"kind": "PLACE", "id": "p1", "name": "Cầu Rồng"}
    fields = catalog_location_fields("SIGHTSEEING", rec)
    assert fields["placeName"] == "Cầu Rồng"
    assert fields["sightLocation"]["type"] == "PLACE"
    assert fields["sightLocation"]["placeId"] == "p1"


def test_place_recommendation_on_entertain_card():
    rec = {"kind": "PLACE", "id": "p9", "name": "Asia Park"}
    fields = catalog_location_fields("ENTERTAIN", rec)
    # Entertain reads entertainLocation + placeName; a POI focuses on the PLACE tab.
    assert fields["entertainLocation"]["type"] == "PLACE"
    assert fields["placeName"] == "Asia Park"


def test_missing_activity_type_inferred_from_kind():
    rec = {"kind": "HOTEL", "id": "h1", "name": "Hotel X"}
    fields = catalog_location_fields(None, rec)
    assert "hotelLocation" in fields and fields["hotelLocation"]["placeId"] == "h1"


def test_no_recommendation_or_empty_ref_returns_nothing():
    assert catalog_location_fields("STAY", None) == {}
    assert catalog_location_fields("STAY", {}) == {}
    # A ref with neither an id/slug nor a name can't link anything.
    assert catalog_location_fields("STAY", {"kind": "HOTEL"}) == {}


def test_numeric_id_is_coerced_to_string():
    # A legacy numeric id must be stored as a string so the FE `=== item.id` match works.
    rec = {"kind": "PLACE", "id": 7, "name": "Chợ Hàn"}
    loc = catalog_location_fields("SIGHTSEEING", rec)["sightLocation"]
    assert loc["placeId"] == "7"


def test_transport_has_no_single_venue_mapping():
    rec = {"kind": "PLACE", "id": "p1", "name": "Sân bay"}
    assert catalog_location_fields("TRANSPORT", rec) == {}


def test_approval_card_body_writes_focusable_location():
    activity = DraftActivity(
        title="Tham quan Bà Nà Hills",
        day_index=1,
        activity_type=PlanActivityType.SIGHTSEEING,
        estimated_cost_vnd=900000,
        address="Hoà Vang, Đà Nẵng",
        recommendation=RecommendationRef(
            catalog_kind="PLACE", catalog_id="bana1", catalog_slug="ba-na-hills",
            name="Bà Nà Hills", latitude=15.99, longitude=107.99, avg_rating=4.6,
        ),
    )
    body = _activity_to_card_body(activity, travelers=2)
    ad = json.loads(body["activityDataJson"])
    assert ad["placeName"] == "Bà Nà Hills"
    assert ad["sightLocation"]["type"] == "PLACE"
    assert ad["sightLocation"]["placeId"] == "bana1"
    assert ad["sightLocation"]["fullAddress"] == "Hoà Vang, Đà Nẵng"
    # The original recommendation block is still present too.
    assert ad["recommendation"]["slug"] == "ba-na-hills"
