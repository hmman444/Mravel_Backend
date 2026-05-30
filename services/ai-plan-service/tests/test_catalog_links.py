"""Tests for Mravel catalog deep-links surfaced to the agent.

The agent answers "what's on Mravel / give me links" by searching the catalog and
linking each item to its detail page via the `mravel_url` field. These tests lock
in the URL shape and that every summariser emits it."""

import pytest

from app.agent.tools import (
    ToolDispatcher,
    catalog_web_url,
    _summarise_hotel,
    _summarise_place,
    _summarise_restaurant,
)


class _FakeCatalog:
    """Duck-typed catalog client. Hotels/restaurants only exist for 'hoi-an' (and the
    un-located broaden), mirroring the real seeded dataset; faceted returns POIs."""

    def __init__(self, hotels=None, restaurants=None, places=None, faceted=None):
        self._hotels = hotels or []
        self._restaurants = restaurants or []
        self._places = places or []
        self._faceted = faceted or []
        self.calls = []

    async def search_hotels(self, location, page=0, size=10, **f):
        self.calls.append(("hotels", location, f))
        return self._hotels if location in (None, "", "hoi-an") else []

    async def search_restaurants(self, location, page=0, size=10, **f):
        self.calls.append(("restaurants", location, f))
        return self._restaurants if location in (None, "", "hoi-an") else []

    async def search_places(self, q, page=0, size=10):
        self.calls.append(("places", q))
        return self._places

    async def search_places_faceted(self, parent_slug=None, q=None, page=0, size=10):
        self.calls.append(("faceted", parent_slug, q))
        return self._faceted


def test_catalog_web_url_relative_paths():
    # No base → relative path the browser resolves against the SPA origin.
    assert catalog_web_url("HOTEL", "muong-thanh-da-nang") == "/hotels/muong-thanh-da-nang"
    assert catalog_web_url("RESTAURANT", "be-man") == "/restaurants/be-man"
    # Place route is singular on the frontend.
    assert catalog_web_url("PLACE", "ba-na-hills") == "/place/ba-na-hills"


def test_catalog_web_url_absolute_base():
    assert (
        catalog_web_url("HOTEL", "abc", base_url="https://mravel.vn/")
        == "https://mravel.vn/hotels/abc"
    )


def test_catalog_web_url_guards():
    assert catalog_web_url("HOTEL", None) is None  # no slug → no link
    assert catalog_web_url("HOTEL", "") is None
    assert catalog_web_url("UNKNOWN", "x") is None  # unknown kind → no link


def test_summarisers_emit_mravel_url():
    hotel = _summarise_hotel({"id": "1", "name": "H", "slug": "h-slug"})
    assert hotel["mravel_url"] == "/hotels/h-slug"

    resto = _summarise_restaurant({"id": "2", "name": "R", "slug": "r-slug"})
    assert resto["mravel_url"] == "/restaurants/r-slug"

    place = _summarise_place({"id": "3", "name": "P", "slug": "p-slug"})
    assert place["mravel_url"] == "/place/p-slug"


def test_summariser_without_slug_has_no_url():
    # _drop_none strips the None mravel_url so the key is simply absent.
    hotel = _summarise_hotel({"id": "1", "name": "H"})
    assert "mravel_url" not in hotel


@pytest.mark.asyncio
async def test_search_places_uses_faceted_for_destination():
    """A destination scopes to the faceted endpoint (slug-based, accent-proof) so a
    city's POIs are returned even though the free-text index is diacritic-sensitive."""
    cat = _FakeCatalog(faceted=[{"id": "1", "name": "Bãi biển Mỹ Khê", "slug": "bai-bien-my-khe"}])
    res = await ToolDispatcher(cat).run("search_places", {"destination": "Đà Nẵng"})
    assert res["count"] == 1
    assert res["items"][0]["mravel_url"] == "/place/bai-bien-my-khe"
    assert ("faceted", "da-nang", None) in cat.calls


@pytest.mark.asyncio
async def test_search_hotels_empty_location_returns_alternatives():
    """No hotels for the requested city → broaden and surface what Mravel DOES have,
    so the agent can offer a nearby alternative with a real link instead of dead-ending."""
    cat = _FakeCatalog(hotels=[{"id": "1", "name": "Hoa Thu Homestay",
                                 "slug": "hoa-thu-homestay", "cityName": "Hội An"}])
    res = await ToolDispatcher(cat).run("search_hotels", {"location": "Đà Nẵng"})
    assert res["count"] == 0
    assert res["no_results_for_location"] == "da-nang"
    assert res["available_on_mravel"], "should surface available hotels from a broadened search"
    assert res["available_on_mravel"][0]["mravel_url"] == "/hotels/hoa-thu-homestay"
    assert res["available_on_mravel"][0]["city"] == "Hội An"
