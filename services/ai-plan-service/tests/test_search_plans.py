"""Unit tests for the `search_plans` agent tool.

The tool mirrors plan-service `GET /api/plans/search` (visibility-scoped to the bearer's
user), summarises each accessible plan and attaches a `/plans/<id>` link the agent can
surface. These tests exercise the dispatcher branch + the summariser directly, with a
fake PlanClient — no running plan-service required.
"""

import asyncio
from typing import Any, Dict

from app.agent.tools import ToolDispatcher, plan_web_url
from app.clients.catalog_client import CatalogClient
from app.config import get_settings


class _FakeCatalog(CatalogClient):
    def __init__(self) -> None:
        super().__init__(get_settings())


class _FakePlan:
    """Captures the params the dispatcher sends and returns a PlanSearchResponse-shaped
    payload (the `data` body of /api/plans/search)."""

    def __init__(self) -> None:
        self.captured: Dict[str, Any] = {}

    async def search_plans(self, bearer_token, **kwargs):
        self.captured = {"bearer": bearer_token, **kwargs}
        return {
            "query": kwargs.get("q"),
            "plans": {
                "items": [
                    {
                        "id": 123,
                        "title": "Lịch trình 3 ngày tại Đà Nẵng cho 2 người",
                        "days": 3,
                        "startDate": "2026-06-11",
                        "endDate": "2026-06-13",
                        "budgetTotal": 9500000,
                        "budgetPerPerson": 4750000,
                        "destinations": [{"name": "Đà Nẵng"}],
                        "author": {"id": 7, "name": "Đỗ Luân"},
                        "views": 12,
                        "visibility": "PUBLIC",
                        "thumbnail": "https://img/x.jpg",
                        "description": "Biển + ẩm thực",
                    }
                ],
                "size": kwargs.get("size"),
                "total": 1,
                "hasMore": False,
            },
            "users": [],
        }


def test_search_plans_summarises_and_links() -> None:
    plan = _FakePlan()
    dispatcher = ToolDispatcher(_FakeCatalog(), plan_client=plan, bearer_token="tok")

    result = asyncio.run(
        dispatcher.run(
            "search_plans",
            {
                "query": "Đà Nẵng biển",
                "budget_max_vnd": 10000000,
                "days_min": 3,
                "days_max": 3,
                "max_results": 5,
            },
        )
    )

    assert result["count"] == 1
    item = result["items"][0]
    # Link points at the plan board route the SPA resolves.
    assert item["mravel_url"] == "/plans/123"
    assert item["title"].startswith("Lịch trình 3 ngày")
    assert item["days"] == 3
    assert item["budget_total_vnd"] == 9500000
    assert item["destinations"] == ["Đà Nẵng"]
    assert item["author"] == "Đỗ Luân"

    # Filters were forwarded to plan-service verbatim.
    assert plan.captured["q"] == "Đà Nẵng biển"
    assert plan.captured["budget_max"] == 10000000
    assert plan.captured["days_min"] == 3
    assert plan.captured["days_max"] == 3
    assert plan.captured["bearer"] == "tok"


def test_search_plans_without_token_is_graceful() -> None:
    dispatcher = ToolDispatcher(_FakeCatalog(), plan_client=_FakePlan(), bearer_token=None)
    result = asyncio.run(dispatcher.run("search_plans", {"query": "Đà Lạt"}))
    assert result == {"items": [], "count": 0, "note": "plan-service not available in this context"}


def test_plan_web_url_helper() -> None:
    assert plan_web_url(42) == "/plans/42"
    assert plan_web_url(42, "https://mravel.app") == "https://mravel.app/plans/42"
    assert plan_web_url(None) is None
    assert plan_web_url("") is None
