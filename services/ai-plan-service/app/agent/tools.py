"""Tool catalog exposed to the LLM agent.

Each tool has:
  - a JSON-schema description the model sees
  - an async executor that runs server-side

The terminal tool is `finalize_draft` — when the model calls it, the orchestrator
parses the structured payload into a `PlanDraft` and stops the loop.
"""

import unicodedata
from datetime import date
from typing import Any, Dict, List, Optional

from pydantic import ValidationError

from app.clients.catalog_client import CatalogClient
from app.clients.plan_client import PlanClient
from app.core.errors import UpstreamError
from app.llm.base import ToolDefinition
from app.models.session import (
    Constraints,
    DraftActivity,
    DraftDay,
    PlanActivityType,
    PlanDraft,
    RecommendationRef,
)


def tool_definitions() -> List[ToolDefinition]:
    return [
        ToolDefinition(
            name="set_constraints",
            description=(
                "Record or update the trip facts you have learned from the conversation. "
                "Call this EVERY time the user reveals or changes a fact (destination, trip "
                "length, dates, number of travelers, budget, interests, pace) — even if you "
                "also ask a follow-up question in the same turn. Only include fields you "
                "actually know; omit unknown ones (do NOT guess or invent). This is the single "
                "source of truth for the trip, so the system never re-asks something you "
                "already captured here. For TIMING prefer `num_days` (how many days the user "
                "wants) — only fill start_date/end_date when the user names actual calendar dates."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "destination": {
                        "type": "string",
                        "description": "City/area, e.g. 'Đà Nẵng'. Omit if unknown.",
                    },
                    "num_days": {
                        "type": "integer",
                        "minimum": 1,
                        "description": (
                            "Trip length in days, e.g. 3 for '3 ngày 2 đêm' or '3 ngày 3 đêm'. "
                            "If the user gives a range ('3-4 ngày'), pick one sensible number. "
                            "Use this instead of asking for calendar dates."
                        ),
                    },
                    "start_date": {
                        "type": "string",
                        "description": "YYYY-MM-DD. ONLY when the user names an explicit calendar date; omit otherwise.",
                    },
                    "end_date": {
                        "type": "string",
                        "description": "YYYY-MM-DD. ONLY when the user names an explicit calendar date; omit otherwise.",
                    },
                    "travelers": {"type": "integer", "minimum": 1},
                    "budget_total_vnd": {"type": "integer", "minimum": 0},
                    "interests": {"type": "array", "items": {"type": "string"}},
                    "pace": {"type": "string", "enum": ["relaxed", "balanced", "packed"]},
                },
            },
        ),
        ToolDefinition(
            name="search_hotels",
            description=(
                "Search the Mravel hotel catalog for lodging candidates. You may call this "
                "multiple times with different filters (e.g. cheaper alternatives, higher "
                "star rating). Returns id, name, slug, lat/lng, min nightly price, rating."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "location": {
                        "type": "string",
                        "description": "Destination keyword, e.g. 'da-nang' or 'Da Nang'",
                    },
                    "max_results": {"type": "integer", "minimum": 1, "maximum": 20, "default": 5},
                    "max_price_vnd": {"type": "integer", "description": "Optional nightly cap"},
                    "min_star_rating": {"type": "integer", "minimum": 1, "maximum": 5},
                },
                "required": ["location"],
            },
        ),
        ToolDefinition(
            name="search_restaurants",
            description=(
                "Search the Mravel restaurant catalog near the destination. Call this multiple "
                "times to find lunch vs dinner with different cuisines or price ranges. "
                "Returns id, name, slug, lat/lng, price per person, cuisines, rating."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "location": {"type": "string"},
                    "cuisine": {"type": "string", "description": "Optional cuisine keyword e.g. 'seafood', 'vegetarian'"},
                    "max_price_per_person_vnd": {"type": "integer", "description": "Optional per-person cap"},
                    "max_results": {"type": "integer", "minimum": 1, "maximum": 20, "default": 10},
                },
                "required": ["location"],
            },
        ),
        ToolDefinition(
            name="search_places",
            description=(
                "Search Mravel attractions / points of interest. To list a destination's "
                "attractions, pass `destination` as the Vietnamese city name WITH diacritics "
                "(e.g. 'Đà Nẵng') — that reliably returns the city's POIs. Use `query` for a "
                "more specific search, also Vietnamese WITH diacritics (e.g. 'Đà Nẵng bãi biển', "
                "'Đà Nẵng bảo tàng', 'Đà Nẵng chợ đêm'). Provide at least one of the two; you "
                "may pass both. Returns id, name, slug, mravel_url, lat/lng, rating, cover image."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "destination": {
                        "type": "string",
                        "description": (
                            "City/area in Vietnamese WITH diacritics, e.g. 'Đà Nẵng'. Returns "
                            "that destination's attractions reliably (scoped by slug)."
                        ),
                    },
                    "query": {
                        "type": "string",
                        "description": (
                            "Vietnamese free text WITH diacritics, e.g. 'Đà Nẵng bãi biển'. "
                            "Avoid accent-free/English text — the place search is diacritic-sensitive."
                        ),
                    },
                    "max_results": {"type": "integer", "minimum": 1, "maximum": 20, "default": 10},
                },
            },
        ),
        ToolDefinition(
            name="route_legs",
            description=(
                "Compute REAL travel distance, time and transport mode between an ordered "
                "list of stops (use the latitude/longitude from search results). Call this "
                "before finalizing to set each activity's route_hint / distance_text / "
                "transport_mode and to space out start times realistically — a homestay far "
                "from the centre genuinely adds minutes between activities. Returns one leg "
                "per consecutive pair."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "stops": {
                        "type": "array",
                        "description": "Ordered stops for one travel chain (e.g. hotel → breakfast → sight).",
                        "items": {
                            "type": "object",
                            "properties": {
                                "name": {"type": "string"},
                                "latitude": {"type": "number"},
                                "longitude": {"type": "number"},
                            },
                            "required": ["name"],
                        },
                    }
                },
                "required": ["stops"],
            },
        ),
        ToolDefinition(
            name="web_search",
            description=(
                "Search the public web for CURRENT, time-sensitive facts the catalog lacks: "
                "prices (vé tham quan, thuê xe máy, vé xe khách/máy bay), opening hours, or "
                "whether a venue is still open. Always try the catalog tools FIRST for venues. "
                "ALWAYS include the current year (from the context primer) in the query so you "
                "don't get stale figures. Returns title/url/content snippets — read them for a "
                "concrete number and CITE the returned `url` as a Markdown link. Never quote a "
                "price from memory; if results are empty, say you couldn't verify and give a "
                "clearly-labelled estimate (don't invent a venue or a link)."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "query": {
                        "type": "string",
                        "description": (
                            "Specific query INCLUDING the current year, e.g. "
                            "'giá vé Bà Nà Hills <năm hiện tại>' or 'giờ mở cửa Asia Park <năm hiện tại>'"
                        ),
                    },
                    "max_results": {"type": "integer", "minimum": 1, "maximum": 8, "default": 5},
                },
                "required": ["query"],
            },
        ),
        ToolDefinition(
            name="view_my_plans",
            description=(
                "Read-only: list the current user's existing approved plans. Use this if the "
                "user references prior trips ('giống chuyến vừa rồi', 'kế hoạch Đà Nẵng tôi tạo "
                "tuần trước') or to avoid suggesting destinations they already booked. Returns "
                "id, title, destinations, start/end dates, status."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "max_results": {"type": "integer", "minimum": 1, "maximum": 20, "default": 5},
                },
                "required": [],
            },
        ),
        ToolDefinition(
            name="search_plans",
            description=(
                "Search EXISTING travel itineraries the current user can access — their own "
                "plans PLUS public / friends' / shared community plans, exactly like the "
                "/plans search bar. Use this when the user wants to FIND or REFERENCE ready-made "
                "plans, e.g. 'tìm lịch trình đi Đà Nẵng 3 ngày 2 đêm với kinh phí 10 triệu', "
                "'có plan nào đi Đà Lạt không'. This is DIFFERENT from search_places / "
                "search_hotels (those find individual venues, not whole itineraries). "
                "Returns each plan's title, destinations, số ngày (days), budget, author and a "
                "`mravel_url` link. After calling, reply with the links + a short explanation of "
                "each, then analyse which one best fits what the user asked for (matching days, "
                "budget, destination, vibe)."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "query": {
                        "type": "string",
                        "description": (
                            "Free-text keyword, e.g. 'Đà Nẵng biển', 'Đà Lạt chill'. Vietnamese "
                            "WITH diacritics is fine. Leave empty to browse with only the filters."
                        ),
                    },
                    "destinations": {
                        "type": "array",
                        "items": {"type": "string"},
                        "description": "Optional destination-name filter, e.g. ['Đà Nẵng'].",
                    },
                    "budget_min_vnd": {"type": "integer", "minimum": 0, "description": "Lower budget bound in VND."},
                    "budget_max_vnd": {
                        "type": "integer",
                        "minimum": 0,
                        "description": "Upper budget cap in VND, e.g. 10000000 for 'kinh phí 10 triệu'.",
                    },
                    "days_min": {"type": "integer", "minimum": 1, "description": "Min trip length in days."},
                    "days_max": {
                        "type": "integer",
                        "minimum": 1,
                        "description": "Max trip length in days, e.g. 3 for '3 ngày 2 đêm'.",
                    },
                    "max_results": {"type": "integer", "minimum": 1, "maximum": 20, "default": 8},
                },
            },
        ),
        ToolDefinition(
            name="finalize_draft",
            description=(
                "Submit the final multi-day itinerary draft. Call this exactly once at the end "
                "after you have searched the catalog. Every activity SHOULD have a `recommendation` "
                "object copied from one of the search results. Use the existing catalog items "
                "rather than inventing names."
            ),
            input_schema={
                "type": "object",
                "properties": {
                    "summary": {"type": "string"},
                    "destination": {"type": "string"},
                    "start_date": {"type": "string", "description": "YYYY-MM-DD"},
                    "end_date": {"type": "string", "description": "YYYY-MM-DD"},
                    "travelers": {"type": "integer", "minimum": 1},
                    "estimated_total_cost_vnd": {"type": "integer", "minimum": 0},
                    "days": {
                        "type": "array",
                        "items": {
                            "type": "object",
                            "properties": {
                                "day_index": {"type": "integer", "minimum": 1},
                                "day_date": {"type": "string", "description": "YYYY-MM-DD"},
                                "title": {"type": "string"},
                                "activities": {
                                    "type": "array",
                                    "items": {
                                        "type": "object",
                                        "properties": {
                                            "title": {"type": "string"},
                                            "description": {"type": "string"},
                                            "start_time": {"type": "string", "description": "HH:MM"},
                                            "end_time": {"type": "string", "description": "HH:MM"},
                                            "duration_minutes": {"type": "integer"},
                                            "activity_type": {
                                                "type": "string",
                                                "enum": [t.value for t in PlanActivityType],
                                            },
                                            "estimated_cost_vnd": {"type": "integer", "minimum": 0},
                                            "reason": {"type": "string"},
                                            "location_name": {
                                                "type": "string",
                                                "description": "Display name of the place, e.g. 'Bánh canh Nam Phố'",
                                            },
                                            "address": {
                                                "type": "string",
                                                "description": "Street address if known, e.g. '74 Trưng Nữ Vương, Đà Nẵng'",
                                            },
                                            "note": {
                                                "type": "string",
                                                "description": "Tip or reminder for this activity, e.g. 'chụp ảnh trước cửa hàng lưu niệm'",
                                            },
                                            "route_hint": {
                                                "type": "string",
                                                "description": "Route from previous step, e.g. 'Home tạm → Quán ăn' or 'HCM → Đà Nẵng'",
                                            },
                                            "distance_text": {
                                                "type": "string",
                                                "description": "Distance hint, e.g. '10.3km', '~30km', '500m'",
                                            },
                                            "transport_mode": {
                                                "type": "string",
                                                "description": "How to get there, e.g. 'xe máy', 'đi bộ', 'taxi', 'xe khách'",
                                            },
                                            "recommendation": {
                                                "type": "object",
                                                "properties": {
                                                    "catalog_kind": {
                                                        "type": "string",
                                                        "enum": ["PLACE", "RESTAURANT", "HOTEL"],
                                                    },
                                                    "catalog_id": {
                                                        "type": "string",
                                                        "description": "Mongo ObjectId from the search result — copy verbatim",
                                                    },
                                                    "catalog_slug": {"type": "string"},
                                                    "name": {"type": "string"},
                                                    "latitude": {"type": "number"},
                                                    "longitude": {"type": "number"},
                                                    "cover_image_url": {"type": "string"},
                                                    "avg_rating": {"type": "number"},
                                                    "estimated_cost_vnd": {"type": "integer"},
                                                },
                                            },
                                        },
                                        "required": ["title", "activity_type"],
                                    },
                                },
                            },
                            "required": ["day_index", "day_date", "title", "activities"],
                        },
                    },
                    "warnings": {"type": "array", "items": {"type": "string"}},
                },
                "required": ["destination", "start_date", "end_date", "days"],
            },
        ),
    ]


def strip_accents(text: str) -> str:
    """Fold Vietnamese accents to ASCII so 'Đà Nẵng' and 'Da Nang' hit the same
    catalog location keyword. đ/Đ need explicit handling (not a combining mark)."""
    text = text.replace("đ", "d").replace("Đ", "D")
    decomposed = unicodedata.normalize("NFD", text)
    return "".join(c for c in decomposed if unicodedata.category(c) != "Mn")


def edit_tool_definitions() -> List[ToolDefinition]:
    """Tools available when editing an existing plan: read the current board, search
    the catalog/web for replacements, compute routes, then propose edit operations."""
    from app.agent.edits import propose_tool_schema

    reuse = {"search_hotels", "search_restaurants", "search_places", "route_legs", "web_search"}
    tools = [t for t in tool_definitions() if t.name in reuse]
    tools.insert(
        0,
        ToolDefinition(
            name="get_current_plan",
            description=(
                "Read the CURRENT plan board (days, lists, cards with their ids, times and "
                "costs). Call this first when the user asks about or wants to change the plan, "
                "so you target the right list_id / card_id. A summary is also in the primer."
            ),
            input_schema={"type": "object", "properties": {}},
        ),
    )
    tools.append(
        ToolDefinition(
            name="propose_plan_edits",
            description=(
                "Terminal: submit the list of edit operations to apply to the current plan. "
                "Use ids from get_current_plan. The user reviews and approves before anything "
                "is written. Include a short Vietnamese `summary` per operation."
            ),
            input_schema={
                "type": "object",
                "properties": {"operations": propose_tool_schema()},
                "required": ["operations"],
            },
        )
    )
    return tools


def location_slug(text: str) -> str:
    return strip_accents(text).strip().lower().replace(" ", "-")


# Frontend detail-page routes per catalog kind (see Mravel_Frontend App.jsx).
# Note: place is singular ("/place/"), hotels/restaurants are plural.
_CATALOG_PATHS = {"HOTEL": "/hotels", "RESTAURANT": "/restaurants", "PLACE": "/place"}


def catalog_web_url(kind: str, slug: Optional[str], base_url: str = "") -> Optional[str]:
    """Build a clickable link to a Mravel catalog detail page.

    With no `base_url` the path is relative (e.g. "/hotels/<slug>"); the browser
    resolves it against the SPA origin so the link works in-app. Returns None when
    the item has no slug (can't be linked)."""
    if not slug:
        return None
    path = _CATALOG_PATHS.get((kind or "").upper())
    if not path:
        return None
    return f"{(base_url or '').rstrip('/')}{path}/{slug}"


def plan_web_url(plan_id: Any, base_url: str = "") -> Optional[str]:
    """Build a clickable link to a plan's board page (frontend route `/plans/:planId`).
    Relative when base_url is empty so the SPA resolves it against its own origin."""
    if plan_id is None or str(plan_id).strip() == "":
        return None
    return f"{(base_url or '').rstrip('/')}/plans/{plan_id}"


# Hard cap on items per tool response — keeps the model's context lean.
_MAX_ITEMS_PER_TOOL = 8


def _drop_none(d: Dict[str, Any]) -> Dict[str, Any]:
    return {k: v for k, v in d.items() if v is not None and v != ""}


def _full_address(item: Dict[str, Any]) -> Optional[str]:
    """Compose a human address from the catalog's address parts."""
    parts = [
        item.get("addressLine"),
        item.get("wardName"),
        item.get("districtName"),
        item.get("cityName") or item.get("provinceName"),
    ]
    seen = []
    for p in parts:
        p = (p or "").strip()
        if p and p not in seen:
            seen.append(p)
    return ", ".join(seen) or None


def _summarise_hotel(item: Dict[str, Any], base_url: str = "") -> Dict[str, Any]:
    return _drop_none(
        {
            "id": item.get("id"),
            "slug": item.get("slug"),
            "name": item.get("name"),
            "mravel_url": catalog_web_url("HOTEL", item.get("slug"), base_url),
            "address": _full_address(item),
            "city": item.get("cityName") or item.get("provinceName"),
            "district": item.get("districtName"),
            "latitude": item.get("latitude"),
            "longitude": item.get("longitude"),
            "min_nightly_price_vnd": item.get("minNightlyPrice"),
            "reference_nightly_price_vnd": item.get("referenceNightlyPrice"),
            "avg_rating": item.get("avgRating"),
            "star_rating": item.get("starRating"),
            "hotel_type": item.get("hotelType"),
            "distance_to_center_km": item.get("distanceToCityCenterKm"),
            "facilities": item.get("mainFacilitiesSummary"),
            "cover_image_url": item.get("coverImageUrl"),
        }
    )


def _summarise_restaurant(item: Dict[str, Any], base_url: str = "") -> Dict[str, Any]:
    cuisines = item.get("cuisineNames")
    if isinstance(cuisines, list) and len(cuisines) > 3:
        cuisines = cuisines[:3]
    return _drop_none(
        {
            "id": item.get("id"),
            "slug": item.get("slug"),
            "name": item.get("name"),
            "mravel_url": catalog_web_url("RESTAURANT", item.get("slug"), base_url),
            "address": _full_address(item),
            "city": item.get("cityName") or item.get("provinceName"),
            "district": item.get("districtName"),
            "latitude": item.get("latitude"),
            "longitude": item.get("longitude"),
            "min_price_per_person_vnd": item.get("minPricePerPerson"),
            "max_price_per_person_vnd": item.get("maxPricePerPerson"),
            "price_level": item.get("priceLevel"),
            "restaurant_type": item.get("restaurantType"),
            "cuisines": cuisines,
            "avg_rating": item.get("avgRating"),
            "cover_image_url": item.get("coverImageUrl"),
        }
    )


def _summarise_place(item: Dict[str, Any], base_url: str = "") -> Dict[str, Any]:
    return _drop_none(
        {
            "id": item.get("id"),
            "slug": item.get("slug"),
            "name": item.get("name"),
            "mravel_url": catalog_web_url("PLACE", item.get("slug"), base_url),
            "address": _full_address(item),
            "latitude": item.get("latitude"),
            "longitude": item.get("longitude"),
            "kind": item.get("kind"),
            "venue_type": item.get("venueType"),
            "price_level": item.get("priceLevel"),
            "avg_rating": item.get("avgRating"),
            "province": item.get("provinceName"),
            "cover_image_url": item.get("coverImageUrl"),
        }
    )


def _summarise_plan_search_item(item: Dict[str, Any], base_url: str = "") -> Dict[str, Any]:
    """Trim a PlanFeedItem from /api/plans/search to what the model needs to present
    the plan: title, link, length, budget, destinations, author."""
    destinations = item.get("destinations") or []
    if isinstance(destinations, list):
        dest_names = [d.get("name") if isinstance(d, dict) else str(d) for d in destinations]
        dest_names = [d for d in dest_names if d]
    else:
        dest_names = []
    author = item.get("author") if isinstance(item.get("author"), dict) else {}
    return _drop_none(
        {
            "id": item.get("id"),
            "title": item.get("title"),
            "mravel_url": plan_web_url(item.get("id"), base_url),
            "days": item.get("days"),
            "start_date": item.get("startDate"),
            "end_date": item.get("endDate"),
            "destinations": dest_names or None,
            "budget_total_vnd": item.get("budgetTotal"),
            "budget_per_person_vnd": item.get("budgetPerPerson"),
            "author": author.get("name"),
            "views": item.get("views"),
            "visibility": item.get("visibility"),
            "description": item.get("description"),
            "cover_image_url": item.get("thumbnail"),
        }
    )


def _summarise_user_plan(item: Dict[str, Any]) -> Dict[str, Any]:
    # The /api/plans/me response uses PlanFeedItem fields. Keep only what the model needs.
    destinations = item.get("destinations") or []
    if isinstance(destinations, list):
        dest_names = [d.get("name") if isinstance(d, dict) else str(d) for d in destinations]
    else:
        dest_names = []
    return {
        "id": item.get("id"),
        "title": item.get("title"),
        "start_date": item.get("startDate"),
        "end_date": item.get("endDate"),
        "destinations": dest_names,
        "status": item.get("status"),
    }


class ToolDispatcher:
    """Executes read-only tools on behalf of the LLM. Per-request: pass
    `bearer_token` so user-scoped tools (e.g. `view_my_plans`) can authenticate."""

    def __init__(
        self,
        catalog: CatalogClient,
        plan_client: Optional[PlanClient] = None,
        bearer_token: Optional[str] = None,
        web_search: Optional[Any] = None,
        web_base_url: str = "",
    ) -> None:
        self._catalog = catalog
        self._plan = plan_client
        self._bearer = bearer_token
        self._web_search = web_search
        self._web_base_url = web_base_url

    def with_token(self, bearer_token: Optional[str]) -> "ToolDispatcher":
        return ToolDispatcher(
            self._catalog, self._plan, bearer_token, self._web_search, self._web_base_url
        )

    async def _empty_location_fallback(self, kind: str, location: str) -> Dict[str, Any]:
        """A location search returned nothing. Broaden to an un-located search so the
        agent can honestly offer what Mravel DOES have (often a nearby city) with real
        links, instead of dead-ending to external sites. `kind` is 'hotels'/'restaurants'."""
        try:
            if kind == "hotels":
                rows = await self._catalog.search_hotels(None, page=0, size=_MAX_ITEMS_PER_TOOL)
                alt = [_summarise_hotel(r, self._web_base_url) for r in rows[:_MAX_ITEMS_PER_TOOL]]
            else:
                rows = await self._catalog.search_restaurants(None, page=0, size=_MAX_ITEMS_PER_TOOL)
                alt = [_summarise_restaurant(r, self._web_base_url) for r in rows[:_MAX_ITEMS_PER_TOOL]]
        except UpstreamError:
            alt = []
        kind_vi = "khách sạn" if kind == "hotels" else "nhà hàng"
        return {
            "items": [],
            "count": 0,
            "no_results_for_location": location or "(unspecified)",
            "available_on_mravel": alt,
            "note": (
                f"Mravel chưa có {kind_vi} cho '{location}'. 'available_on_mravel' là các lựa "
                "chọn đang có trên Mravel (có thể ở thành phố lân cận) — hãy gợi ý kèm link "
                "mravel_url, đừng chỉ nói là không có."
            ),
        }

    async def run(self, tool_name: str, arguments: Dict[str, Any]) -> Dict[str, Any]:
        if tool_name == "search_hotels":
            raw_location = str(arguments.get("location", "")).strip()
            if not raw_location:
                return {
                    "items": [],
                    "count": 0,
                    "error": "Thiếu 'location' — cần một địa điểm để tìm khách sạn.",
                }
            location = location_slug(raw_location)
            limit = min(int(arguments.get("max_results") or 5), _MAX_ITEMS_PER_TOOL)
            filters: Dict[str, Any] = {}
            # Field names must match catalog HotelSearchRequest (maxPrice / starRating[]),
            # otherwise Jackson silently drops them and the filter does nothing.
            if arguments.get("max_price_vnd"):
                filters["maxPrice"] = int(arguments["max_price_vnd"])
            if arguments.get("min_star_rating"):
                star = max(1, min(5, int(arguments["min_star_rating"])))
                filters["starRating"] = list(range(star, 6))  # "at least N stars"
            results = await self._catalog.search_hotels(location, page=0, size=limit, **filters)
            trimmed = [_summarise_hotel(r, self._web_base_url) for r in results[:_MAX_ITEMS_PER_TOOL]]
            if trimmed:
                return {"items": trimmed, "count": len(trimmed)}
            return await self._empty_location_fallback("hotels", location)
        if tool_name == "search_restaurants":
            location = location_slug(str(arguments.get("location", "")))
            limit = min(int(arguments.get("max_results") or 10), _MAX_ITEMS_PER_TOOL)
            filters: Dict[str, Any] = {}
            # cuisine is a free-text hint; the catalog filters by slug set (cuisineSlugs).
            if arguments.get("cuisine"):
                filters["cuisineSlugs"] = [location_slug(str(arguments["cuisine"]))]
            results = await self._catalog.search_restaurants(location, page=0, size=limit, **filters)
            trimmed = [_summarise_restaurant(r, self._web_base_url) for r in results[:_MAX_ITEMS_PER_TOOL]]
            if trimmed:
                return {"items": trimmed, "count": len(trimmed)}
            return await self._empty_location_fallback("restaurants", location)
        if tool_name == "search_places":
            destination = str(arguments.get("destination", "")).strip()
            query = str(arguments.get("query", "")).strip()
            limit = min(int(arguments.get("max_results") or 10), _MAX_ITEMS_PER_TOOL)
            items: List[Dict[str, Any]] = []
            seen: set = set()

            def _add(rows: List[Dict[str, Any]]) -> None:
                for r in rows:
                    key = r.get("slug") or r.get("id")
                    if key and key not in seen:
                        seen.add(key)
                        items.append(r)

            # 1) Destination-scoped POIs via the faceted endpoint (slug-based →
            #    accent-proof and deterministic for a city's attractions).
            if destination:
                try:
                    _add(await self._catalog.search_places_faceted(
                        location_slug(destination), page=0, size=limit
                    ))
                except UpstreamError:
                    pass
            # 2) Free-text query (passed verbatim WITH diacritics — do NOT strip accents,
            #    the place index is diacritic-sensitive).
            if query:
                try:
                    _add(await self._catalog.search_places(query, page=0, size=limit))
                except UpstreamError:
                    pass
            trimmed = [_summarise_place(r, self._web_base_url) for r in items[:_MAX_ITEMS_PER_TOOL]]
            return {"items": trimmed, "count": len(trimmed)}
        if tool_name == "route_legs":
            from app.services.geo import route_legs

            stops = arguments.get("stops") or []
            if not isinstance(stops, list) or len(stops) < 2:
                return {"legs": [], "note": "need at least 2 stops"}
            legs = route_legs([s for s in stops if isinstance(s, dict)])
            total_km = sum(leg["distance_km"] for leg in legs if leg.get("distance_km"))
            total_min = sum(leg["minutes"] for leg in legs if leg.get("minutes"))
            return {"legs": legs, "total_km": round(total_km, 1), "total_minutes": total_min}
        if tool_name == "web_search":
            if not self._web_search or not getattr(self._web_search, "enabled", False):
                return {
                    "enabled": False,
                    "results": [],
                    "note": "Web search chưa được cấu hình — dùng ước tính giá hợp lý và ghi rõ là ước tính.",
                }
            query = str(arguments.get("query", "")).strip()
            if not query:
                return {"results": [], "note": "empty query"}
            limit = int(arguments.get("max_results") or 5)
            return await self._web_search.search(query, max_results=limit)
        if tool_name == "view_my_plans":
            if not self._plan or not self._bearer:
                return {"items": [], "count": 0, "note": "plan-service not available in this context"}
            limit = int(arguments.get("max_results") or 5)
            try:
                data = await self._plan.list_my_plans(self._bearer, page=1, size=limit)
            except UpstreamError as exc:
                return {"items": [], "count": 0, "error": str(exc)}
            items = data.get("items") if isinstance(data, dict) else None
            items = items or []
            return {"items": [_summarise_user_plan(i) for i in items], "count": len(items)}
        if tool_name == "search_plans":
            if not self._plan or not self._bearer:
                return {"items": [], "count": 0, "note": "plan-service not available in this context"}
            limit = min(int(arguments.get("max_results") or 8), _MAX_ITEMS_PER_TOOL)
            raw_dests = arguments.get("destinations")
            destinations = (
                [str(d) for d in raw_dests if str(d).strip()]
                if isinstance(raw_dests, list) and raw_dests
                else None
            )

            def _int(value: Any) -> Optional[int]:
                try:
                    return int(value) if value is not None else None
                except (TypeError, ValueError):
                    return None

            try:
                data = await self._plan.search_plans(
                    self._bearer,
                    q=str(arguments.get("query") or "").strip() or None,
                    destinations=destinations,
                    budget_min=_int(arguments.get("budget_min_vnd")),
                    budget_max=_int(arguments.get("budget_max_vnd")),
                    days_min=_int(arguments.get("days_min")),
                    days_max=_int(arguments.get("days_max")),
                    size=limit,
                )
            except UpstreamError as exc:
                return {"items": [], "count": 0, "error": str(exc)}
            plans = (data.get("plans") or {}).get("items") if isinstance(data, dict) else None
            plans = plans or []
            items = [_summarise_plan_search_item(p, self._web_base_url) for p in plans[:_MAX_ITEMS_PER_TOOL]]
            return {"items": items, "count": len(items)}
        return {"error": f"Unknown tool: {tool_name}"}


def apply_set_constraints(arguments: Dict[str, Any], prior: Constraints) -> Constraints:
    """Merge the agent's `set_constraints` payload into the live constraints.

    The agent is the single source of truth: a field present in `arguments` wins,
    a field absent keeps the prior value. Garbage (empty strings, unparseable dates,
    non-positive counts) is ignored rather than allowed to corrupt state.
    """

    def _date(value: Any, fallback: Optional[date]) -> Optional[date]:
        if isinstance(value, date):
            return value
        if isinstance(value, str) and value.strip():
            try:
                return date.fromisoformat(value.strip()[:10])
            except ValueError:
                return fallback
        return fallback

    destination = prior.destination
    raw_dest = arguments.get("destination")
    if isinstance(raw_dest, str):
        candidate = raw_dest.strip()
        # Conservative sanity check (no geocoding): a real place name has at least
        # two alphanumeric characters. Reject empty/whitespace-only or purely
        # symbolic garbage ("???", "---") so it can't corrupt the constraints.
        if len(candidate) >= 2 and sum(c.isalnum() for c in candidate) >= 2:
            destination = candidate

    num_days = prior.num_days
    if arguments.get("num_days") is not None:
        try:
            num_days = max(1, int(arguments["num_days"]))
        except (TypeError, ValueError):
            pass

    travelers = prior.travelers
    if arguments.get("travelers") is not None:
        try:
            travelers = max(1, int(arguments["travelers"]))
        except (TypeError, ValueError):
            pass

    budget = prior.budget_total_vnd
    if arguments.get("budget_total_vnd") is not None:
        try:
            budget = max(0, int(arguments["budget_total_vnd"]))
        except (TypeError, ValueError):
            pass

    interests = prior.interests
    raw_interests = arguments.get("interests")
    if isinstance(raw_interests, list) and raw_interests:
        interests = [str(i) for i in raw_interests]

    pace = prior.pace
    if arguments.get("pace") in ("relaxed", "balanced", "packed"):
        pace = arguments["pace"]

    return Constraints(
        destination=destination,
        start_date=_date(arguments.get("start_date"), prior.start_date),
        end_date=_date(arguments.get("end_date"), prior.end_date),
        num_days=num_days,
        travelers=travelers,
        budget_total_vnd=budget,
        interests=interests,
        pace=pace,
    )


class DraftValidationError(Exception):
    pass


def parse_finalize_draft(arguments: Dict[str, Any], constraints: Constraints) -> PlanDraft:
    """Convert the LLM's structured `finalize_draft` payload into a `PlanDraft`."""

    def _date(value: Any, fallback: Optional[date]) -> date:
        if isinstance(value, date):
            return value
        if isinstance(value, str) and value:
            try:
                return date.fromisoformat(value[:10])
            except ValueError:
                pass
        if fallback is None:
            raise DraftValidationError(f"Invalid date value: {value!r}")
        return fallback

    destination = (arguments.get("destination") or constraints.destination or "").strip()
    if not destination:
        raise DraftValidationError("finalize_draft is missing a destination")
    # When the user only gave a trip length (num_days), constraints have no calendar
    # date — anchor to today. resolved_date_range also returns an explicit range as-is.
    resolved_start, resolved_end = constraints.resolved_date_range()
    start_date = _date(arguments.get("start_date"), resolved_start)
    end_date = _date(arguments.get("end_date"), resolved_end)
    if end_date < start_date:
        raise DraftValidationError("finalize_draft start_date must be on or before end_date")
    travelers = int(arguments.get("travelers") or constraints.travelers or 2)

    days_payload = arguments.get("days") or []
    if not days_payload:
        raise DraftValidationError("finalize_draft must include at least one day")

    days: List[DraftDay] = []
    total_cost = 0
    for raw_day in days_payload:
        day_date = _date(raw_day.get("day_date"), start_date)
        activities_payload = raw_day.get("activities") or []
        activities: List[DraftActivity] = []
        for raw_act in activities_payload:
            try:
                activity_type = PlanActivityType(raw_act.get("activity_type", "OTHER"))
            except ValueError:
                activity_type = PlanActivityType.OTHER

            rec_payload = raw_act.get("recommendation")
            recommendation = None
            if isinstance(rec_payload, dict) and rec_payload.get("name"):
                try:
                    recommendation = RecommendationRef.model_validate(rec_payload)
                except ValidationError:
                    recommendation = None

            cost = int(raw_act.get("estimated_cost_vnd") or 0)
            if cost < 0:
                raise DraftValidationError(
                    f"activity '{raw_act.get('title') or '?'}' has a negative cost"
                )
            total_cost += cost
            activities.append(
                DraftActivity(
                    title=str(raw_act.get("title") or "Untitled"),
                    description=str(raw_act.get("description") or ""),
                    day_index=int(raw_day.get("day_index") or 1),
                    start_time=raw_act.get("start_time"),
                    end_time=raw_act.get("end_time"),
                    duration_minutes=int(raw_act.get("duration_minutes") or 60),
                    activity_type=activity_type,
                    estimated_cost_vnd=cost,
                    reason=str(raw_act.get("reason") or ""),
                    recommendation=recommendation,
                    location_name=raw_act.get("location_name") or None,
                    address=raw_act.get("address") or None,
                    note=raw_act.get("note") or None,
                    route_hint=raw_act.get("route_hint") or None,
                    distance_text=raw_act.get("distance_text") or None,
                    transport_mode=raw_act.get("transport_mode") or None,
                )
            )

        days.append(
            DraftDay(
                day_index=int(raw_day.get("day_index") or len(days) + 1),
                day_date=day_date,
                title=str(raw_day.get("title") or f"Ngày {len(days) + 1}"),
                activities=activities,
            )
        )

    warnings_payload = arguments.get("warnings")
    warnings: List[str] = [str(w) for w in warnings_payload] if isinstance(warnings_payload, list) else []

    estimated_total = arguments.get("estimated_total_cost_vnd")
    final_total = int(estimated_total) if isinstance(estimated_total, (int, float)) else total_cost

    return PlanDraft(
        summary=str(arguments.get("summary") or f"Lịch trình tại {destination}"),
        destination=destination,
        start_date=start_date,
        end_date=end_date,
        travelers=travelers,
        estimated_total_cost_vnd=final_total,
        days=days,
        warnings=warnings,
    )
