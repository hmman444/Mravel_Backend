from typing import Any, Dict, List

import httpx

from app.config import Settings
from app.core.errors import UpstreamError


class CatalogClient:
    """Read-only client for `catalog-service`. Endpoints are public, no auth needed."""

    def __init__(self, settings: Settings, client: httpx.AsyncClient | None = None) -> None:
        self._base_url = settings.catalog_service_base_url.rstrip("/")
        self._timeout = settings.http_timeout_seconds
        self._client = client

    async def _request(self, method: str, path: str, **kwargs: Any) -> Dict[str, Any]:
        owns_client = self._client is None
        client = self._client or httpx.AsyncClient(timeout=self._timeout)
        try:
            response = await client.request(method, f"{self._base_url}{path}", **kwargs)
            if response.status_code >= 500:
                raise UpstreamError(f"catalog-service {response.status_code} on {path}")
            payload = response.json()
            if not payload.get("success", True):
                raise UpstreamError(payload.get("message", f"catalog-service error on {path}"))
            return payload.get("data") or {}
        except httpx.HTTPError as exc:
            raise UpstreamError(f"catalog-service unreachable: {exc}") from exc
        finally:
            if owns_client:
                await client.aclose()

    async def search_places(self, q: str, page: int = 0, size: int = 10) -> List[Dict[str, Any]]:
        data = await self._request(
            "POST",
            "/api/catalog/places/search",
            params={"page": page, "size": size},
            json={"q": q},
        )
        return data.get("content") or data.get("items") or []

    async def search_places_faceted(
        self, parent_slug: str | None = None, q: str | None = None, page: int = 0, size: int = 10
    ) -> List[Dict[str, Any]]:
        """Faceted place search. Scoping by `parentSlug` (a destination slug like
        'da-nang') deterministically returns that destination's POIs and is
        accent-proof — unlike the free-text /search, whose index keeps Vietnamese
        diacritics so an accent-stripped 'Da Nang' misses the 'Đà Nẵng' POIs.

        The faceted response puts the list under `data.results` (not `data.content`)
        and returns clean, localised name/address strings."""
        body: Dict[str, Any] = {}
        if parent_slug:
            body["parentSlug"] = parent_slug
        if q:
            body["q"] = q
        data = await self._request(
            "POST",
            "/api/catalog/places/search/faceted",
            params={"page": page, "size": size},
            json=body,
        )
        return data.get("results") or data.get("content") or []

    async def search_restaurants(
        self, location: str | None, page: int = 0, size: int = 10, **filters: Any
    ) -> List[Dict[str, Any]]:
        body: Dict[str, Any] = {k: v for k, v in filters.items() if v is not None}
        if location:
            body["location"] = location
        data = await self._request(
            "POST",
            "/api/catalog/restaurants/search",
            params={"page": page, "size": size},
            json=body,
        )
        return data.get("content") or data.get("items") or []

    async def search_hotels(
        self, location: str | None, page: int = 0, size: int = 10, **filters: Any
    ) -> List[Dict[str, Any]]:
        body: Dict[str, Any] = {k: v for k, v in filters.items() if v is not None}
        if location:
            body["location"] = location
        data = await self._request(
            "POST",
            "/api/catalog/hotels/search",
            params={"page": page, "size": size},
            json=body,
        )
        return data.get("content") or data.get("items") or []
