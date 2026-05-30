"""Web search fallback — used only when the catalog lacks an item or a price.

Provider: Tavily (https://tavily.com) — clean JSON, generous free tier, good for
extracting current prices ("giá thuê xe máy Đà Nẵng", "vé Bà Nà Hills 2025").
9Router exposes no web-grounded model over the OpenAI Chat Completions API, so a
dedicated search API is the reliable option.

Degrades gracefully: with no `WEB_SEARCH_API_KEY` configured, `enabled` is False and
callers fall back to heuristic pricing — the agent is told so it can be honest.
"""

from __future__ import annotations

import logging
from typing import Any, Dict, List

import httpx

from app.config import Settings

logger = logging.getLogger("ai_plan.web_search")

_TAVILY_URL = "https://api.tavily.com/search"


class WebSearchClient:
    def __init__(self, settings: Settings, client: httpx.AsyncClient | None = None) -> None:
        self._provider = (settings.web_search_provider or "tavily").strip().lower()
        self._api_key = (settings.web_search_api_key or "").strip()
        self._timeout = settings.http_timeout_seconds
        self._client = client

    @property
    def enabled(self) -> bool:
        return bool(self._api_key)

    async def search(self, query: str, max_results: int = 5) -> Dict[str, Any]:
        """Return {"enabled": bool, "results": [{title, url, content}], "answer": str?}.

        Never raises — on any failure returns an empty result set so a single bad
        search can't abort the whole planning turn.
        """
        if not self.enabled:
            return {"enabled": False, "results": [], "note": "web search not configured"}
        if self._provider != "tavily":
            return {"enabled": False, "results": [], "note": f"unsupported provider {self._provider}"}

        owns = self._client is None
        client = self._client or httpx.AsyncClient(timeout=self._timeout)
        try:
            resp = await client.post(
                _TAVILY_URL,
                json={
                    "api_key": self._api_key,
                    "query": query,
                    "max_results": max(1, min(int(max_results), 8)),
                    "search_depth": "basic",
                    "include_answer": True,
                },
            )
            if resp.status_code >= 400:
                logger.warning("web search %s: %s", resp.status_code, resp.text[:200])
                return {"enabled": True, "results": [], "note": f"search HTTP {resp.status_code}"}
            data = resp.json()
            results: List[Dict[str, Any]] = []
            for r in (data.get("results") or [])[:max_results]:
                results.append(
                    {
                        "title": r.get("title"),
                        "url": r.get("url"),
                        "content": (r.get("content") or "")[:600],
                    }
                )
            return {"enabled": True, "answer": data.get("answer"), "results": results}
        except (httpx.HTTPError, ValueError) as exc:
            logger.warning("web search failed: %s", exc)
            return {"enabled": True, "results": [], "note": "search unavailable"}
        finally:
            if owns:
                await client.aclose()
