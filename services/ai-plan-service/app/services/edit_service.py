"""Execute approved edit operations against plan-service.

Runs only behind an explicit user "Áp dụng" (apply) action — the agent merely
PROPOSES operations; this is the single place that mutates an existing real plan.
Each op maps to one plan-service endpoint via PlanClient. Failures are collected
per-op (one bad op doesn't abort the rest) and reported back to the user.
"""

import json
import logging
import uuid
from datetime import date
from typing import Any, Dict, List, Optional

from app.agent.edits import EditOperation
from app.clients.plan_client import PlanClient
from app.models.session import PlanActivityType
from app.services.catalog_location import catalog_location_fields

logger = logging.getLogger("ai_plan.edit")

_IDEM_NAMESPACE = uuid.UUID("8c2a9f55-1d8d-4f4b-9c2a-3f4bbf5d2f02")


def _idem(seed: str) -> str:
    return str(uuid.uuid5(_IDEM_NAMESPACE, seed))


def _batch_seed(plan_id: int, operations: List[EditOperation]) -> str:
    """Stable seed for this exact set of operations on this plan. Same proposal →
    same seed (idempotent re-apply); different proposal → different seed."""
    payload = json.dumps(
        [op.model_dump(exclude_none=True) for op in operations], sort_keys=True, default=str
    )
    return f"plan:{plan_id}:{uuid.uuid5(_IDEM_NAMESPACE, payload)}"


def _valid_type(value: Optional[str]) -> Optional[str]:
    if not value:
        return None
    try:
        return PlanActivityType(value.strip().upper()).value
    except ValueError:
        return PlanActivityType.OTHER.value


def _norm_time(value: Optional[str]) -> Optional[str]:
    if not value or not isinstance(value, str):
        return None
    parts = value.split(":")
    if len(parts) < 2:
        return None
    try:
        return f"{int(parts[0]):02d}:{int(parts[1]):02d}"
    except ValueError:
        return None


def _card_body(op: EditOperation, *, for_create: bool) -> Dict[str, Any]:
    participant = op.participant_count or op.quantity
    activity_data = {
        k: v
        for k, v in {
            "reason": op.reason,
            "locationName": op.location_name,
            "address": op.address,
            "note": op.note,
            "routeHint": op.route_hint,
            "distanceText": op.distance_text,
            "transportMode": op.transport_mode,
            "ticketPrice": op.unit_price_vnd,
            "ticketCount": op.quantity,
        }.items()
        if v
    }
    if isinstance(op.recommendation, dict) and op.recommendation:
        # Pass the catalog reference through (best-effort; the model copies it from a
        # search result) so FOOD/SIGHTSEEING/STAY cards keep a bookable link + image.
        rec = op.recommendation
        activity_data["recommendation"] = {
            "kind": rec.get("kind") or rec.get("catalog_kind"),
            "id": rec.get("id") or rec.get("catalog_id"),
            "slug": rec.get("slug") or rec.get("catalog_slug"),
            "name": rec.get("name"),
            "latitude": rec.get("latitude"),
            "longitude": rec.get("longitude"),
            "coverImageUrl": rec.get("coverImageUrl") or rec.get("cover_image_url"),
            "avgRating": rec.get("avgRating") or rec.get("avg_rating"),
        }
        # Mirror a manual place pick so the FE modal pre-fills the name + location and
        # the picker auto-focuses this catalog item (hotelLocation/restaurantLocation/…).
        activity_data.update(
            catalog_location_fields(
                op.activity_type, activity_data["recommendation"], address=op.address
            )
        )

    body: Dict[str, Any] = {}
    if op.text:
        body["text"] = op.text
    # Build a rich description (address/note/route) so the card reads well even before
    # the frontend renders activityDataJson — mirrors the approval path.
    if op.description is not None or op.address or op.note or op.route_hint:
        parts = []
        if op.description:
            parts.append(op.description)
        if op.address:
            parts.append(f"📍 {op.address}")
        if op.note:
            parts.append(f"💡 {op.note}")
        if op.route_hint:
            line = f"🗺️ {op.route_hint}"
            if op.distance_text:
                line += f" ({op.distance_text})"
            if op.transport_mode:
                line += f" — {op.transport_mode}"
            parts.append(line)
        body["description"] = "\n".join(parts)
    st = _norm_time(op.start_time)
    et = _norm_time(op.end_time)
    if st:
        body["startTime"] = st
    if et:
        body["endTime"] = et
    if op.duration_minutes:
        body["durationMinutes"] = op.duration_minutes
    atype = _valid_type(op.activity_type)
    if atype:
        body["activityType"] = atype
    elif for_create:
        body["activityType"] = PlanActivityType.OTHER.value
    if participant:
        body["participantCount"] = participant
    if activity_data:
        body["activityDataJson"] = json.dumps(activity_data, ensure_ascii=False)
    if op.estimated_cost_vnd is not None:
        body["cost"] = {
            "currencyCode": "VND",
            "estimatedCost": op.estimated_cost_vnd,
            **({"participantCount": participant} if participant else {}),
        }
    return body


class EditService:
    def __init__(self, plan_client: PlanClient) -> None:
        self._plan = plan_client

    async def apply(
        self, plan_id: int, operations: List[EditOperation], bearer_token: str
    ) -> Dict[str, Any]:
        results: List[Dict[str, Any]] = []
        applied = 0
        # Seed idempotency keys with a hash of THIS batch's content. Re-applying the
        # exact same proposal (double-click) dedups; a different proposal gets fresh
        # keys and applies — index alone would wrongly collide across proposals.
        batch_seed = _batch_seed(plan_id, operations)
        for i, op in enumerate(operations):
            try:
                detail = await self._apply_one(plan_id, op, bearer_token, i, batch_seed)
                applied += 1
                results.append({"op": op.op, "ok": True, "summary": op.summary, "detail": detail})
            except Exception as exc:  # noqa: BLE001 — collect, don't abort the batch
                logger.warning("apply op %s failed: %s", op.op, exc)
                results.append({"op": op.op, "ok": False, "summary": op.summary, "error": str(exc)})
        return {"applied": applied, "total": len(operations), "results": results}

    async def _apply_one(
        self, plan_id: int, op: EditOperation, bearer: str, idx: int, seed: str
    ) -> Any:
        if op.op == "update_card":
            return await self._plan.update_card(
                bearer, plan_id, op.list_id, op.card_id, _card_body(op, for_create=False)
            )
        if op.op == "create_card":
            return await self._plan.create_card(
                bearer, plan_id, op.list_id, _card_body(op, for_create=True),
                idempotency_key=_idem(f"{seed}:{idx}:create"),
            )
        if op.op == "delete_card":
            return await self._plan.delete_card(bearer, plan_id, op.list_id, op.card_id)
        if op.op == "move_card":
            resp = await self._plan.move_card(
                bearer, plan_id, op.card_id, op.target_list_id, op.target_position,
                idempotency_key=_idem(f"{seed}:{idx}:move"),
            )
            # The command API returns {success, data:null} on a rejected move; guard
            # against silently reporting a no-op as success.
            if not (isinstance(resp, dict) and resp.get("entityId")):
                raise RuntimeError("move_card không xác nhận được (entityId rỗng)")
            return resp
        if op.op == "rename_list":
            return await self._plan.rename_list(bearer, plan_id, op.list_id, op.title)
        if op.op == "delete_list":
            return await self._plan.delete_list(bearer, plan_id, op.list_id)
        if op.op == "add_day":
            await self._plan.create_list(
                bearer, plan_id, {"title": op.title or "Ngày mới"},
                idempotency_key=_idem(f"{seed}:{idx}:addday"),
            )
            # create_list returns a null id in practice — re-read the board to surface
            # the new day's id (so a follow-up turn can add activities to it).
            try:
                board = await self._plan.get_board(bearer, plan_id)
                days = [x for x in (board.get("lists") or []) if x.get("type") == "DAY"]
                days.sort(key=lambda x: (x.get("position") if x.get("position") is not None else 0))
                return {"new_list_id": days[-1]["id"] if days else None}
            except Exception:  # noqa: BLE001
                return {"new_list_id": None}
        if op.op == "update_plan":
            done = []
            if op.title:
                await self._plan.update_plan_title(bearer, plan_id, op.title)
                done.append("title")
            if op.start_date and op.end_date:
                # Reject an inverted range before writing — plan-service would store a
                # negative-duration plan. Parse failures fall through to plan-service.
                try:
                    if date.fromisoformat(op.start_date[:10]) > date.fromisoformat(op.end_date[:10]):
                        raise RuntimeError(
                            "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc."
                        )
                except ValueError:
                    pass
                await self._plan.update_plan_dates(bearer, plan_id, op.start_date, op.end_date)
                done.append("dates")
            if op.budget_total_vnd is not None:
                await self._plan.update_plan_budget(bearer, plan_id, op.budget_total_vnd)
                done.append("budget")
            return {"updated": done}
        raise ValueError(f"unknown op {op.op}")
