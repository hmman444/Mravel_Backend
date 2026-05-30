"""Edit-operation model + helpers for the AI plan editor.

When a chat session is opened on an existing plan, the agent reads the current board
and proposes a list of edit operations via the terminal `propose_plan_edits` tool.
Those operations are validated into `EditOperation` objects, shown to the user for
approval, and only then executed against plan-service (see EditService).

One flat model (rather than a discriminated union) keeps the LLM JSON simple: each
operation has an `op` and only the fields that op needs; everything else is null.
"""

from __future__ import annotations

from typing import Any, Dict, List, Optional

from pydantic import BaseModel

OP_TYPES = {
    "update_card",
    "create_card",
    "delete_card",
    "move_card",
    "rename_list",
    "add_day",
    "delete_list",
    "update_plan",
}


class EditOperation(BaseModel):
    op: str

    # Targets
    list_id: Optional[int] = None
    card_id: Optional[int] = None
    target_list_id: Optional[int] = None
    target_position: Optional[int] = None

    # Card / activity fields
    text: Optional[str] = None  # card title
    description: Optional[str] = None
    start_time: Optional[str] = None  # "HH:MM"
    end_time: Optional[str] = None
    duration_minutes: Optional[int] = None
    activity_type: Optional[str] = None  # STAY|FOOD|SIGHTSEEING|TRANSPORT|ENTERTAIN|...
    estimated_cost_vnd: Optional[int] = None
    unit_price_vnd: Optional[int] = None
    quantity: Optional[int] = None
    participant_count: Optional[int] = None
    address: Optional[str] = None
    note: Optional[str] = None
    # Rich card fields (mirror the Excel itinerary columns + the approval path) so an
    # edit-created card is as detailed as the catalog/approval ones.
    location_name: Optional[str] = None  # "Bánh canh Nam Phổ"
    reason: Optional[str] = None  # 1 line why it fits
    route_hint: Optional[str] = None  # "Home → Quán ăn", "Đà Nẵng → Hội An"
    distance_text: Optional[str] = None  # "500m", "~2km", "30km"
    transport_mode: Optional[str] = None  # "đi bộ", "xe máy", "taxi", "xe khách"
    recommendation: Optional[Dict[str, Any]] = None  # copied from a catalog search result

    # List / plan fields
    title: Optional[str] = None
    start_date: Optional[str] = None  # YYYY-MM-DD
    end_date: Optional[str] = None
    budget_total_vnd: Optional[int] = None

    # Human-readable description shown in the approval card.
    summary: Optional[str] = None


class EditValidationError(Exception):
    pass


def parse_operations(raw: Any, board: Optional[Dict[str, Any]] = None) -> List[EditOperation]:
    """Validate the LLM's `operations` payload into EditOperation objects.

    Invalid entries (unknown op, missing required ids) are dropped rather than
    aborting the whole proposal; at least one valid op is required.

    When `board` is given, also drop operations that reference a list_id/card_id
    not present on the board — this catches the LLM hallucinating ids so an
    invalid proposal never reaches the apply step."""
    if not isinstance(raw, list):
        raise EditValidationError("operations must be a list")
    list_ids, card_ids = _board_ids(board)
    ops: List[EditOperation] = []
    for entry in raw:
        if not isinstance(entry, dict):
            continue
        op = str(entry.get("op") or "").strip()
        if op not in OP_TYPES:
            continue
        try:
            model = EditOperation.model_validate(entry)
        except Exception:  # noqa: BLE001
            continue
        if not _has_required_targets(model):
            continue
        if board is not None and not _ids_exist(model, list_ids, card_ids):
            continue
        if not model.summary:
            model.summary = operation_summary(model)
        ops.append(model)
    if not ops:
        raise EditValidationError("no valid operations in proposal")
    return ops


def _board_ids(board: Optional[Dict[str, Any]]) -> tuple:
    if not isinstance(board, dict):
        return set(), set()
    lists = board.get("lists") or []
    list_ids = {x.get("id") for x in lists if isinstance(x, dict)}
    card_ids = {
        c.get("id")
        for x in lists
        if isinstance(x, dict)
        for c in (x.get("cards") or [])
        if isinstance(c, dict)
    }
    return list_ids, card_ids


def _ids_exist(op: EditOperation, list_ids: set, card_ids: set) -> bool:
    """Whether the ids an op references actually exist on the board."""
    if op.op in ("update_card", "delete_card"):
        return op.card_id in card_ids and op.list_id in list_ids
    if op.op == "create_card":
        return op.list_id in list_ids
    if op.op == "move_card":
        return op.card_id in card_ids and op.target_list_id in list_ids
    if op.op in ("rename_list", "delete_list"):
        return op.list_id in list_ids
    return True  # add_day / update_plan don't reference existing ids


def _has_required_targets(op: EditOperation) -> bool:
    if op.op == "update_card":
        return op.list_id is not None and op.card_id is not None
    if op.op == "delete_card":
        return op.list_id is not None and op.card_id is not None
    if op.op == "create_card":
        return op.list_id is not None and bool(op.text)
    if op.op == "move_card":
        return op.card_id is not None and op.target_list_id is not None
    if op.op == "rename_list":
        return op.list_id is not None and bool(op.title)
    if op.op == "delete_list":
        return op.list_id is not None
    if op.op == "add_day":
        return True
    if op.op == "update_plan":
        return any([op.title, op.start_date, op.end_date, op.budget_total_vnd])
    return False


def operation_summary(op: EditOperation) -> str:
    """A short, human Vietnamese description for the approval UI. Intentionally
    id-free — the user reads this, so describe the change by name/time/price, never
    by internal card_id/list_id."""
    if op.op == "update_card":
        bits = []
        if op.text:
            bits.append(f"đổi thành “{op.text}”")
        if op.start_time or op.end_time:
            bits.append(f"giờ {op.start_time or '?'}–{op.end_time or '?'}")
        if op.estimated_cost_vnd is not None:
            bits.append(f"chi phí {op.estimated_cost_vnd:,}đ")
        if op.activity_type:
            bits.append(f"loại {op.activity_type}")
        detail = ", ".join(bits) or "cập nhật"
        return f"Cập nhật hoạt động: {detail}"
    if op.op == "create_card":
        return f"Thêm hoạt động “{op.text}”" + (
            f" lúc {op.start_time}" if op.start_time else ""
        )
    if op.op == "delete_card":
        return "Xoá một hoạt động"
    if op.op == "move_card":
        return "Chuyển hoạt động sang ngày khác"
    if op.op == "rename_list":
        return f"Đổi tên ngày → “{op.title}”"
    if op.op == "delete_list":
        return "Xoá một ngày khỏi lịch trình"
    if op.op == "add_day":
        return "Thêm ngày mới" + (f" “{op.title}”" if op.title else "")
    if op.op == "update_plan":
        bits = []
        if op.title:
            bits.append(f"tên “{op.title}”")
        if op.start_date or op.end_date:
            bits.append(f"ngày {op.start_date or '?'}→{op.end_date or '?'}")
        if op.budget_total_vnd is not None:
            bits.append(f"ngân sách {op.budget_total_vnd:,}đ")
        return "Cập nhật plan: " + (", ".join(bits) or "")
    return op.op


def board_summary(board: Dict[str, Any]) -> str:
    """Compact text of the current board so the agent can answer about it and
    target the right list_id / card_id when proposing edits."""
    if not isinstance(board, dict):
        return "(không đọc được plan hiện tại)"
    lines: List[str] = []
    title = board.get("planTitle") or board.get("title") or ""
    pid = board.get("planId") or board.get("id")
    rng = f"{board.get('startDate', '?')} → {board.get('endDate', '?')}"
    budget = board.get("budgetTotal")
    head = f"Plan #{pid} “{title}” ({rng})"
    if budget:
        head += f", ngân sách {int(budget):,}đ"
    lines.append(head)
    # The board has no explicit destination field; the title/description carry it.
    # Surface the description so the agent can infer the city for catalog searches.
    desc = (board.get("description") or "").strip()
    if desc:
        lines.append(f"Mô tả: {desc[:200]}")

    lists = board.get("lists") or []
    day_lists = [x for x in lists if isinstance(x, dict) and x.get("type") == "DAY"]
    day_lists.sort(key=lambda x: (x.get("position") if x.get("position") is not None else 9999))
    for lst in day_lists:
        lines.append(
            f"• {lst.get('title') or 'Ngày'} (list_id={lst.get('id')}, {lst.get('dayDate') or '?'}):"
        )
        cards = lst.get("cards") or []
        cards.sort(key=lambda c: (c.get("position") if c.get("position") is not None else 9999))
        for c in cards:
            cost = (c.get("cost") or {}).get("estimatedCost") if isinstance(c.get("cost"), dict) else None
            t = c.get("startTime") or ""
            end = c.get("endTime") or ""
            tr = f"{t}-{end}" if t else ""
            cost_s = f" · {int(cost):,}đ" if cost else ""
            lines.append(
                f"    - card_id={c.get('id')} | {tr} {c.get('text') or ''} "
                f"[{c.get('activityType') or 'OTHER'}]{cost_s}"
            )
        if not cards:
            lines.append("    (chưa có hoạt động)")
    return "\n".join(lines)


def propose_tool_schema() -> Dict[str, Any]:
    """JSON schema for the terminal `propose_plan_edits` tool's `operations` array."""
    return {
        "type": "array",
        "description": "Ordered list of edit operations to apply to the current plan.",
        "items": {
            "type": "object",
            "properties": {
                "op": {
                    "type": "string",
                    "enum": sorted(OP_TYPES),
                    "description": "Which operation.",
                },
                "list_id": {"type": "integer", "description": "Target day/list id (from the board)."},
                "card_id": {"type": "integer", "description": "Target card/activity id (from the board)."},
                "target_list_id": {"type": "integer", "description": "Destination list for move_card."},
                "target_position": {"type": "integer"},
                "text": {"type": "string", "description": "Card title (create_card/update_card)."},
                "description": {"type": "string"},
                "start_time": {"type": "string", "description": "HH:MM"},
                "end_time": {"type": "string", "description": "HH:MM"},
                "duration_minutes": {"type": "integer"},
                "activity_type": {
                    "type": "string",
                    "enum": [
                        "STAY", "FOOD", "SIGHTSEEING", "TRANSPORT", "ENTERTAIN",
                        "EVENT", "SHOPPING", "CINEMA", "OTHER",
                    ],
                },
                "estimated_cost_vnd": {"type": "integer", "description": "Total cost (unit × people)."},
                "unit_price_vnd": {"type": "integer", "description": "Per-person price."},
                "quantity": {"type": "integer", "description": "Number of people/units."},
                "participant_count": {"type": "integer"},
                "address": {"type": "string", "description": "Exact street address, e.g. '74 Trưng Nữ Vương, Đà Nẵng'."},
                "note": {"type": "string", "description": "Tip / what to order / alternative venue / opening-hours quirk."},
                "location_name": {"type": "string", "description": "Venue/place name, e.g. 'Bánh canh Nam Phổ'."},
                "reason": {"type": "string", "description": "1 short line why this fits the user."},
                "route_hint": {"type": "string", "description": "Transit segment, e.g. 'Home → Quán ăn' or 'Đà Nẵng → Hội An'."},
                "distance_text": {"type": "string", "description": "'500m', '~2km', '30km'."},
                "transport_mode": {"type": "string", "description": "'đi bộ', 'xe máy', 'taxi', 'xe khách', 'máy bay'."},
                "recommendation": {
                    "type": "object",
                    "description": "Copy from a catalog search result (kind/slug/id/name/lat/lng/coverImageUrl) for STAY/FOOD/SIGHTSEEING cards.",
                },
                "title": {"type": "string", "description": "List title (rename_list/add_day) or plan title."},
                "start_date": {"type": "string", "description": "YYYY-MM-DD (update_plan)."},
                "end_date": {"type": "string"},
                "budget_total_vnd": {"type": "integer"},
                "summary": {"type": "string", "description": "1 short Vietnamese line describing this change."},
            },
            "required": ["op"],
        },
    }
