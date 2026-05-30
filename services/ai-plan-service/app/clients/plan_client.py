from typing import Any, Dict, Optional

import httpx

from app.config import Settings
from app.core.errors import UpstreamError


class PlanClient:
    """Authenticated client for `plan-service`. Forwards the caller's bearer token."""

    def __init__(self, settings: Settings, client: httpx.AsyncClient | None = None) -> None:
        self._base_url = settings.plan_service_base_url.rstrip("/")
        self._timeout = settings.http_timeout_seconds
        self._client = client

    async def _request(
        self,
        method: str,
        path: str,
        bearer_token: str,
        *,
        json_body: Optional[Dict[str, Any]] = None,
        idempotency_key: Optional[str] = None,
    ) -> Dict[str, Any]:
        headers = {
            "Authorization": f"Bearer {bearer_token}",
            "Content-Type": "application/json",
        }
        if idempotency_key:
            headers["Idempotency-Key"] = idempotency_key

        owns_client = self._client is None
        client = self._client or httpx.AsyncClient(timeout=self._timeout)
        try:
            response = await client.request(
                method, f"{self._base_url}{path}", headers=headers, json=json_body
            )
            if response.status_code in (401, 403):
                raise UpstreamError(f"plan-service rejected token ({response.status_code})")
            if response.status_code >= 500:
                raise UpstreamError(f"plan-service {response.status_code} on {path}")
            payload = response.json()
            if not payload.get("success", True):
                raise UpstreamError(payload.get("message", f"plan-service error on {path}"))
            return payload.get("data") or {}
        except httpx.HTTPError as exc:
            raise UpstreamError(f"plan-service unreachable: {exc}") from exc
        finally:
            if owns_client:
                await client.aclose()

    async def create_plan(self, bearer_token: str, body: Dict[str, Any]) -> Dict[str, Any]:
        return await self._request("POST", "/api/plans", bearer_token, json_body=body)

    async def list_my_plans(
        self, bearer_token: str, page: int = 1, size: int = 10
    ) -> Dict[str, Any]:
        """Read-only: paginated list of the current user's plans."""
        headers = {
            "Authorization": f"Bearer {bearer_token}",
            "Content-Type": "application/json",
        }
        owns_client = self._client is None
        client = self._client or httpx.AsyncClient(timeout=self._timeout)
        try:
            response = await client.get(
                f"{self._base_url}/api/plans/me",
                headers=headers,
                params={"page": page, "size": size},
            )
            if response.status_code in (401, 403):
                raise UpstreamError(f"plan-service rejected token ({response.status_code})")
            if response.status_code >= 500:
                raise UpstreamError(f"plan-service {response.status_code} on /api/plans/me")
            payload = response.json()
            if not payload.get("success", True):
                raise UpstreamError(payload.get("message", "plan-service /me failed"))
            return payload.get("data") or {}
        except httpx.HTTPError as exc:
            raise UpstreamError(f"plan-service unreachable: {exc}") from exc
        finally:
            if owns_client:
                await client.aclose()

    async def get_board(self, bearer_token: str, plan_id: int) -> Dict[str, Any]:
        """Read the full board (BoardResponse). plan-service seeds one DAY list per
        day in the plan's date range at create time, so this is how we discover the
        pre-existing list ids to attach cards to — instead of creating extra days."""
        return await self._raw_get(f"/api/plans/{plan_id}/board", bearer_token)

    async def rename_list(
        self,
        bearer_token: str,
        plan_id: int,
        list_id: int,
        title: str,
    ) -> Dict[str, Any]:
        return await self._raw_request(
            "PUT",
            f"/api/plans/{plan_id}/board/lists/{list_id}/rename",
            bearer_token,
            json_body={"title": title},
        )

    async def create_list(
        self,
        bearer_token: str,
        plan_id: int,
        body: Dict[str, Any],
        idempotency_key: Optional[str] = None,
    ) -> Dict[str, Any]:
        """DEPRECATED for the approval flow. plan-service auto-seeds DAY lists at
        plan-create time, and this endpoint APPENDS an extra day (dayDate =
        endDate+1) whose response `id` came back null in practice. ApprovalService
        now reads the seeded lists via `get_board` instead. Kept only for ad-hoc use."""
        headers_extra = {"Idempotency-Key": idempotency_key} if idempotency_key else {}
        return await self._raw_post(
            f"/api/plans/{plan_id}/board/lists",
            bearer_token,
            json_body=body,
            extra_headers=headers_extra,
        )

    async def create_card(
        self,
        bearer_token: str,
        plan_id: int,
        list_id: int,
        body: Dict[str, Any],
        idempotency_key: Optional[str] = None,
    ) -> Dict[str, Any]:
        # Use the command endpoint so the Idempotency-Key is actually honored —
        # the legacy /board/lists/{id}/cards path ignores it, so a retried approve
        # would duplicate cards. The cmd response (CommandResponse) is not read by
        # callers (approval/edit only need the write to land).
        headers_extra = {"Idempotency-Key": idempotency_key} if idempotency_key else {}
        return await self._raw_post(
            f"/api/plans/{plan_id}/board/cmd/lists/{list_id}/cards",
            bearer_token,
            json_body=body,
            extra_headers=headers_extra,
        )

    # --- Edit operations (used by the AI plan editor) ---

    async def update_card(
        self,
        bearer_token: str,
        plan_id: int,
        list_id: int,
        card_id: int,
        body: Dict[str, Any],
    ) -> Dict[str, Any]:
        return await self._raw_request(
            "PUT",
            f"/api/plans/{plan_id}/board/lists/{list_id}/cards/{card_id}",
            bearer_token,
            json_body=body,
        )

    async def delete_card(
        self, bearer_token: str, plan_id: int, list_id: int, card_id: int
    ) -> Dict[str, Any]:
        return await self._raw_request(
            "DELETE",
            f"/api/plans/{plan_id}/board/lists/{list_id}/cards/{card_id}",
            bearer_token,
        )

    async def move_card(
        self,
        bearer_token: str,
        plan_id: int,
        card_id: int,
        target_list_id: int,
        target_position: Optional[int] = None,
        idempotency_key: Optional[str] = None,
    ) -> Dict[str, Any]:
        body: Dict[str, Any] = {"targetListId": target_list_id}
        if target_position is not None:
            body["targetPosition"] = target_position
        headers_extra = {"Idempotency-Key": idempotency_key} if idempotency_key else {}
        return await self._raw_request(
            "PATCH",
            f"/api/plans/{plan_id}/board/cmd/cards/{card_id}/move",
            bearer_token,
            json_body=body,
            extra_headers=headers_extra,
        )

    async def delete_list(
        self, bearer_token: str, plan_id: int, list_id: int
    ) -> Dict[str, Any]:
        return await self._raw_request(
            "DELETE", f"/api/plans/{plan_id}/board/lists/{list_id}", bearer_token
        )

    async def update_plan_title(self, bearer_token: str, plan_id: int, title: str) -> Dict[str, Any]:
        return await self._raw_request(
            "PATCH", f"/api/plans/{plan_id}/title", bearer_token, json_body={"title": title}
        )

    async def update_plan_dates(
        self, bearer_token: str, plan_id: int, start_date: str, end_date: str
    ) -> Dict[str, Any]:
        return await self._raw_request(
            "PATCH",
            f"/api/plans/{plan_id}/dates",
            bearer_token,
            json_body={"startDate": start_date, "endDate": end_date},
        )

    async def update_plan_budget(
        self,
        bearer_token: str,
        plan_id: int,
        budget_total: int,
        budget_per_person: Optional[int] = None,
        currency: str = "VND",
    ) -> Dict[str, Any]:
        return await self._raw_request(
            "PUT",
            f"/api/plans/{plan_id}/budget",
            bearer_token,
            json_body={
                "budgetCurrency": currency,
                "budgetTotal": budget_total,
                "budgetPerPerson": budget_per_person if budget_per_person is not None else budget_total,
            },
        )

    async def _raw_post(
        self,
        path: str,
        bearer_token: str,
        *,
        json_body: Dict[str, Any],
        extra_headers: Optional[Dict[str, str]] = None,
    ) -> Dict[str, Any]:
        return await self._raw_request(
            "POST", path, bearer_token, json_body=json_body, extra_headers=extra_headers
        )

    async def _raw_get(self, path: str, bearer_token: str) -> Dict[str, Any]:
        return await self._raw_request("GET", path, bearer_token)

    async def _raw_request(
        self,
        method: str,
        path: str,
        bearer_token: str,
        *,
        json_body: Optional[Dict[str, Any]] = None,
        extra_headers: Optional[Dict[str, str]] = None,
    ) -> Dict[str, Any]:
        headers = {
            "Authorization": f"Bearer {bearer_token}",
            "Content-Type": "application/json",
            **(extra_headers or {}),
        }
        owns_client = self._client is None
        client = self._client or httpx.AsyncClient(timeout=self._timeout)
        try:
            response = await client.request(
                method, f"{self._base_url}{path}", headers=headers, json=json_body
            )
            if response.status_code in (401, 403):
                raise UpstreamError(f"plan-service rejected token ({response.status_code})")
            if response.status_code >= 500:
                raise UpstreamError(f"plan-service {response.status_code} on {path}")
            payload = response.json()
            if not payload.get("success", True):
                raise UpstreamError(payload.get("message", f"plan-service error on {path}"))
            return payload.get("data") or {}
        except httpx.HTTPError as exc:
            raise UpstreamError(f"plan-service unreachable: {exc}") from exc
        finally:
            if owns_client:
                await client.aclose()
