"""Map a catalog recommendation onto the frontend activity-modal storage shape.

When the agent attaches a real Mravel catalog item (hotel / restaurant / place) to an
activity, the frontend place picker can pre-select AND auto-focus that item only if the
card stores the location the SAME way a manual pick does:

    activityData.<locationKey> = {type, placeId, name, fullAddress, lat, lng}

plus the type-specific display name (e.g. hotelName / restaurantName / placeName). The
picker keys its list rows by `id` (then `slug`), so `placeId` must be the catalog id
(preferred) or slug — and the catalog id we hand the LLM (`recommendation.catalog_id`)
is exactly the `id` the picker list rows carry, so they match.

Without these keys the modal opens with an empty name field and no selection, and the
picker shows the default list instead of focusing the chosen venue. This helper produces
those extra keys from the recommendation the agent already copied from a search result.
"""

from typing import Any, Dict, Optional

# PlanActivityType value -> (location key the modal reads, display-name key it reads).
# Every single-venue activity type that has a place picker is mapped; TRANSPORT is
# intentionally excluded (it has a from/to pair, not one catalog venue).
_ACTIVITY_CATALOG_FIELDS: Dict[str, tuple[str, str]] = {
    "STAY": ("hotelLocation", "hotelName"),
    "FOOD": ("restaurantLocation", "restaurantName"),
    "SIGHTSEEING": ("sightLocation", "placeName"),
    "ENTERTAIN": ("entertainLocation", "placeName"),
    "SHOPPING": ("shoppingLocation", "storeName"),
    "EVENT": ("eventLocation", "eventName"),
    "CINEMA": ("cinemaLocation", "cinemaName"),
    "OTHER": ("otherLocation", "locationText"),
}

# The three catalog kinds = the three picker tabs.
_PICKER_TYPES = {"HOTEL", "RESTAURANT", "PLACE"}

# When the card has no usable catalog `kind`, fall back to the type the activity implies.
_DEFAULT_TYPE_BY_ACTIVITY = {"STAY": "HOTEL", "FOOD": "RESTAURANT"}

# When the card omitted its activity_type, infer one from the catalog kind so the venue
# still links to a sensible modal/location key.
_ACTIVITY_BY_KIND = {"HOTEL": "STAY", "RESTAURANT": "FOOD", "PLACE": "SIGHTSEEING"}


def catalog_location_fields(
    activity_type: Optional[str],
    recommendation: Optional[Dict[str, Any]],
    address: Optional[str] = None,
) -> Dict[str, Any]:
    """Extra ``activityData`` keys that let the frontend modal pre-select the catalog
    item and auto-focus it in the place picker.

    `recommendation` is the already-normalised dict stored under
    ``activityData["recommendation"]`` (keys: kind / id / slug / name / latitude /
    longitude / ...). Returns an empty dict when there's no place picker for this
    activity type or nothing to link.
    """
    if not isinstance(recommendation, dict) or not recommendation:
        return {}

    kind = str(recommendation.get("kind") or "").upper()
    resolved_activity = _resolve_activity(activity_type, kind)
    mapping = _ACTIVITY_CATALOG_FIELDS.get(resolved_activity) if resolved_activity else None
    if not mapping:
        return {}
    location_key, name_key = mapping

    # Prefer the catalog id (matches the picker list row `id`); slug is the fallback.
    # Coerce to str — the frontend matches selectedId === item.id as strings, and a
    # legacy numeric id would otherwise never match (edit ops carry a raw dict).
    place_id = recommendation.get("id") or recommendation.get("slug")
    if place_id is not None:
        place_id = str(place_id)
    name = recommendation.get("name")
    if not place_id and not name:
        return {}

    loc_type = kind if kind in _PICKER_TYPES else _DEFAULT_TYPE_BY_ACTIVITY.get(
        resolved_activity, "PLACE"
    )

    location = {
        "type": loc_type,
        "placeId": place_id,
        "name": name,
        "fullAddress": address,
        "lat": recommendation.get("latitude"),
        "lng": recommendation.get("longitude"),
    }
    location = {k: v for k, v in location.items() if v not in (None, "")}

    fields: Dict[str, Any] = {location_key: location}
    if name:
        fields[name_key] = name
    return fields


def _resolve_activity(activity_type: Optional[str], kind: str) -> Optional[str]:
    at = str(activity_type or "").strip().upper()
    if at:
        # A known type wins; a known-but-unmapped type (e.g. TRANSPORT) links nothing.
        return at if at in _ACTIVITY_CATALOG_FIELDS else None
    # activity_type omitted — infer a sensible one from the catalog kind.
    return _ACTIVITY_BY_KIND.get(kind)
