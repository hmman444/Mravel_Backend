import json
import logging
import re
from typing import AsyncIterator, List

from fastapi import APIRouter, Depends, status
from fastapi.responses import StreamingResponse

from app.agent.edits import parse_operations
from app.agent.links import linkify_catalog_links
from app.agent.orchestrator import AgentOrchestrator
from app.api.dependencies import (
    get_agent_orchestrator,
    get_approval_service,
    get_constraint_extractor,
    get_edit_service,
    get_plan_client,
    get_store,
)
from app.api.schemas import (
    ApplyEditsResult,
    ApprovalResult,
    CreateSessionRequest,
    RegenerateRequest,
    SendMessageRequest,
    SendMessageResponse,
    SessionSummary,
    SessionView,
)
from app.clients.plan_client import PlanClient
from app.core.errors import DomainError, ForbiddenError, UpstreamError
from app.core.response import ApiResponse
from app.core.security import CurrentUser, get_current_user
from app.models.session import ChatMessage, ChatRole, PlanSession, SessionStatus
from app.services.approval_service import ApprovalService
from app.services.constraint_extractor import ConstraintExtractor
from app.services.edit_service import EditService
from app.services.session_store import InMemorySessionStore

router = APIRouter(prefix="/api/ai-plan", tags=["ai-plan"])

# Roles (BoardResponse.myRole) allowed to edit a plan. plan-service is the
# authoritative enforcer (PlanPermissionService requires EDITOR on every mutation);
# these checks are an early, friendly gate so a viewer can't craft an un-appliable
# proposal — and so editing requires explicit, verified permission.
_EDIT_ROLES = {"OWNER", "EDITOR"}


async def _assert_can_edit(
    plan_client: PlanClient, bearer_token: str, plan_id: int
) -> dict:
    """Verify the caller may EDIT `plan_id`, raising ForbiddenError (403) otherwise.
    Reads the plan board (which carries the caller's `myRole`) and returns it so the
    caller can reuse it without a second round-trip."""
    try:
        board = await plan_client.get_board(bearer_token, plan_id)
    except UpstreamError as exc:
        # plan-service maps 401/403 to UpstreamError → treat as no access.
        raise ForbiddenError(f"Bạn không có quyền truy cập kế hoạch #{plan_id}.") from exc
    role = str(board.get("myRole") or "").upper()
    if role not in _EDIT_ROLES:
        if role == "VIEWER":
            raise ForbiddenError(
                "Bạn chỉ có quyền XEM kế hoạch này nên không thể chỉnh sửa. "
                "Hãy yêu cầu chủ kế hoạch cấp quyền chỉnh sửa (EDITOR)."
            )
        raise ForbiddenError("Bạn không có quyền chỉnh sửa kế hoạch này.")
    return board


# Detect a plan reference the user typed so the global MAI chat can switch to editing
# that plan — exactly like the old plan-bound editor, but with the id arriving via the
# message. Catches a /plans/<id> URL and natural phrasings ("plan 43", "kế hoạch 43",
# "kế hoạch số 43", "plan #43"). The negative lookahead rejects a count/duration so
# "kế hoạch 3 ngày" / "plan 2 người" are NOT mistaken for a plan id.
_PLAN_UNIT = r"(?!\s*(?:ngày|đêm|người|khách|tuần|tháng|năm|giờ|tiếng|phòng))"
_PLAN_REF_PATTERNS = [
    re.compile(r"/plans?/(\d+)", re.IGNORECASE),
    re.compile(
        r"\b(?:plan|kế\s*hoạch)\s*(?:số|#|id)?\s*[:#]?\s*(\d+)\b" + _PLAN_UNIT,
        re.IGNORECASE,
    ),
]


def _extract_plan_id(text: str) -> int | None:
    for pattern in _PLAN_REF_PATTERNS:
        match = pattern.search(text or "")
        if match:
            try:
                return int(match.group(1))
            except (TypeError, ValueError):
                continue
    return None


async def _one_shot_assistant_stream(
    store: InMemorySessionStore, session_id: str, user_id: int, text: str
) -> AsyncIterator[str]:
    """Emit a single assistant message as an SSE stream (and persist it). Used to refuse
    an edit the user lacks permission for, without leaving the chat hanging."""
    yield _sse_event("session", {"session_id": session_id})
    yield _sse_event("assistant_message", {"text": text})
    session = store.get(session_id, user_id)
    msg = ChatMessage(role=ChatRole.ASSISTANT, content=text)
    session.messages.append(msg)
    store.save(session)
    yield _sse_event(
        "done",
        {"assistant_message": json.loads(msg.model_dump_json()), "plan_id": session.plan_id},
    )


def _to_view(session: PlanSession) -> SessionView:
    return SessionView(
        session_id=session.session_id,
        status=session.status.value,
        user_id=session.user_id,
        constraints=session.constraints,
        messages=session.messages,
        draft=session.draft,
        approved_plan_id=session.approved_plan_id,
        plan_id=session.plan_id,
        pending_edits=session.pending_edits,
    )


def _user_conversation_text(session: PlanSession) -> str:
    """All user turns joined — lets the extractor recover facts (esp. destination)
    stated in an earlier message that the single-message pass would miss."""
    return "\n".join(m.content for m in session.messages if m.role == ChatRole.USER)


def _truncate(text: str, limit: int) -> str:
    text = " ".join((text or "").split())
    return text if len(text) <= limit else text[: limit - 1].rstrip() + "…"


def _summarize_session(session: PlanSession) -> SessionSummary:
    """Build a history-list row: title from the first user message, preview from the
    last message."""
    first_user = next((m.content for m in session.messages if m.role == ChatRole.USER), "")
    last = session.messages[-1].content if session.messages else ""
    return SessionSummary(
        session_id=session.session_id,
        title=_truncate(first_user, 50) or "Hội thoại mới",
        preview=_truncate(last, 70),
        status=session.status.value,
        plan_id=session.plan_id,
        message_count=len(session.messages),
        updated_at=session.updated_at,
    )


def _missing_constraint_fields(constraints) -> list[str]:
    missing: list[str] = []
    if not constraints.destination:
        missing.append("destination")
    # Timing is satisfied by EITHER a trip length (num_days, anchored to today) OR an
    # explicit date range — we no longer require calendar dates.
    has_dates = constraints.start_date is not None and constraints.end_date is not None
    if not constraints.num_days and not has_dates:
        missing.append("num_days")
    return missing


@router.post(
    "/sessions",
    response_model=ApiResponse[SessionView],
    status_code=status.HTTP_201_CREATED,
)
async def create_session(
    body: CreateSessionRequest,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
    plan_client: PlanClient = Depends(get_plan_client),
):
    session = store.create(user.id)
    if body.plan_id is not None:
        # Edit mode: verify the caller may edit this plan BEFORE opening the session,
        # so a viewer never reaches the editor. Raises 403 if not OWNER/EDITOR.
        await _assert_can_edit(plan_client, user.raw_token, body.plan_id)
        session.plan_id = body.plan_id  # edit mode: chat edits this existing plan
    if body.initial_message:
        session.messages.append(ChatMessage(role=ChatRole.USER, content=body.initial_message))
    store.save(session)
    return ApiResponse.ok("Session created", _to_view(session))


@router.get("/sessions", response_model=ApiResponse[List[SessionSummary]])
async def list_sessions(
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
):
    """AI chat history for the current user — newest activity first. Empty sessions
    (created but never used) are hidden so the list stays meaningful."""
    sessions = [s for s in store.list_by_user(user.id) if s.messages]
    return ApiResponse.ok("OK", [_summarize_session(s) for s in sessions])


@router.get("/sessions/{session_id}", response_model=ApiResponse[SessionView])
async def get_session(
    session_id: str,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
):
    session = store.get(session_id, user.id)
    return ApiResponse.ok("OK", _to_view(session))


@router.post(
    "/sessions/{session_id}/messages",
    response_model=ApiResponse[SendMessageResponse],
)
async def send_message(
    session_id: str,
    body: SendMessageRequest,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
    extractor: ConstraintExtractor = Depends(get_constraint_extractor),
    agent: AgentOrchestrator = Depends(get_agent_orchestrator),
):
    session = store.get(session_id, user.id)
    if session.plan_id is not None:
        # Edit mode is streaming-only; the frontend uses /messages/stream.
        raise DomainError("Chế độ chỉnh sửa kế hoạch dùng luồng streaming (/messages/stream).")
    if session.status != SessionStatus.DRAFTING:
        raise DomainError("Session is no longer in DRAFTING state")

    session.messages.append(ChatMessage(role=ChatRole.USER, content=body.content))
    session.constraints = await extractor.update(
        session.constraints, body.content, conversation_text=_user_conversation_text(session)
    )

    # Always let the agent drive the response. It decides whether to chat, ask
    # for clarification, or produce a draft. The agent is the source of truth for
    # constraints — it may refine them mid-turn via set_constraints.
    draft, narrative, updated_constraints = await agent.plan(
        constraints=session.constraints,
        chat_history=session.messages[:-1],  # exclude the message we just appended
        latest_user_message=body.content,
        prior_draft=session.draft,
        bearer_token=user.raw_token,
    )
    session.constraints = updated_constraints
    if draft is not None:
        session.draft = draft

    narrative = linkify_catalog_links(narrative)
    assistant_msg = ChatMessage(role=ChatRole.ASSISTANT, content=narrative)
    session.messages.append(assistant_msg)
    store.save(session)

    missing = _missing_constraint_fields(session.constraints)
    return ApiResponse.ok(
        "OK",
        SendMessageResponse(
            session_id=session.session_id,
            constraints=session.constraints,
            draft=session.draft,
            assistant_message=assistant_msg,
            needs_more_info=bool(missing) and session.draft is None,
            missing_fields=missing,
        ),
    )


logger = logging.getLogger("ai_plan.api")

_SSE_HEADERS = {
    "Cache-Control": "no-cache",
    "X-Accel-Buffering": "no",
    "Connection": "keep-alive",
}


def _sse_event(event: str, payload: dict) -> str:
    return f"event: {event}\ndata: {json.dumps(payload, ensure_ascii=False, default=str)}\n\n"


async def _consume_agent_stream(
    *,
    agent: AgentOrchestrator,
    store: InMemorySessionStore,
    session_id: str,
    user_id: int,
    constraints,
    chat_history,
    latest_user_message: str,
    prior_draft,
    revision_hint: str | None = None,
    bearer_token: str | None = None,
) -> AsyncIterator[str]:
    final_draft = prior_draft
    final_text = ""
    final_constraints = constraints

    yield _sse_event(
        "session",
        {
            "session_id": session_id,
            "constraints": json.loads(constraints.model_dump_json()),
        },
    )

    try:
        async for ev in agent.plan_stream(
            constraints=constraints,
            chat_history=chat_history,
            latest_user_message=latest_user_message,
            revision_hint=revision_hint,
            prior_draft=prior_draft,
            bearer_token=bearer_token,
        ):
            kind = ev.get("event")
            if kind == "draft_ready":
                final_draft = ev["draft"]
                yield _sse_event(
                    "draft_ready",
                    {"draft": json.loads(final_draft.model_dump_json())},
                )
            elif kind == "assistant_message":
                final_text = linkify_catalog_links(ev.get("text", "")) or final_text
                yield _sse_event("assistant_message", {"text": final_text})
            elif kind == "constraints_updated":
                final_constraints = ev.get("constraints") or final_constraints
                yield _sse_event(
                    "constraints_updated",
                    {"constraints": json.loads(final_constraints.model_dump_json())},
                )
            elif kind == "tool_call":
                yield _sse_event(
                    "tool_call",
                    {"name": ev.get("name"), "arguments": ev.get("arguments")},
                )
            elif kind == "tool_result":
                yield _sse_event(
                    "tool_result",
                    {"name": ev.get("name"), "summary": ev.get("summary")},
                )
            elif kind == "thinking":
                yield _sse_event("thinking", {"iteration": ev.get("iteration", 1)})
            elif kind == "error":
                yield _sse_event("error", {"message": ev.get("message", "")})
    except Exception as exc:  # noqa: BLE001
        logger.exception("stream failed: %s", exc)
        yield _sse_event("error", {"message": str(exc)})

    # Persist the result.
    assistant_msg = ChatMessage(role=ChatRole.ASSISTANT, content=final_text or "")
    session_to_save = store.get(session_id, user_id)
    if final_draft is not None:
        session_to_save.draft = final_draft
    session_to_save.constraints = final_constraints
    session_to_save.messages.append(assistant_msg)
    store.save(session_to_save)

    yield _sse_event(
        "done",
        {
            "draft": (
                json.loads(final_draft.model_dump_json()) if final_draft else None
            ),
            "assistant_message": json.loads(assistant_msg.model_dump_json()),
            "constraints": json.loads(session_to_save.constraints.model_dump_json()),
        },
    )


async def _consume_edit_stream(
    *,
    agent: AgentOrchestrator,
    store: InMemorySessionStore,
    session_id: str,
    user_id: int,
    plan_id: int,
    chat_history,
    latest_user_message: str,
    bearer_token: str,
) -> AsyncIterator[str]:
    """Edit-mode SSE consumer: streams the agent's reasoning + an `edit_proposal`
    (operations awaiting approval), then persists the proposal on the session."""
    final_text = ""
    operations: list = []

    yield _sse_event("session", {"session_id": session_id, "plan_id": plan_id})

    try:
        async for ev in agent.edit_stream(
            plan_id=plan_id,
            chat_history=chat_history,
            latest_user_message=latest_user_message,
            bearer_token=bearer_token,
        ):
            kind = ev.get("event")
            if kind == "edit_proposal":
                operations = ev.get("operations") or []
                yield _sse_event("edit_proposal", {"operations": operations})
            elif kind == "assistant_message":
                final_text = linkify_catalog_links(ev.get("text", "")) or final_text
                yield _sse_event("assistant_message", {"text": final_text})
            elif kind == "tool_call":
                yield _sse_event("tool_call", {"name": ev.get("name"), "arguments": ev.get("arguments")})
            elif kind == "tool_result":
                yield _sse_event("tool_result", {"name": ev.get("name"), "summary": ev.get("summary")})
            elif kind == "thinking":
                yield _sse_event("thinking", {"iteration": ev.get("iteration", 1)})
            elif kind == "error":
                yield _sse_event("error", {"message": ev.get("message", "")})
    except Exception as exc:  # noqa: BLE001
        logger.exception("edit stream failed: %s", exc)
        yield _sse_event("error", {"message": str(exc)})

    assistant_msg = ChatMessage(role=ChatRole.ASSISTANT, content=final_text or "")
    session_to_save = store.get(session_id, user_id)
    session_to_save.pending_edits = operations  # latest proposal, awaiting "Áp dụng"
    session_to_save.messages.append(assistant_msg)
    store.save(session_to_save)

    yield _sse_event(
        "done",
        {
            "operations": operations,
            "assistant_message": json.loads(assistant_msg.model_dump_json()),
            "plan_id": plan_id,
        },
    )


@router.post("/sessions/{session_id}/messages/stream")
async def stream_message(
    session_id: str,
    body: SendMessageRequest,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
    extractor: ConstraintExtractor = Depends(get_constraint_extractor),
    agent: AgentOrchestrator = Depends(get_agent_orchestrator),
    plan_client: PlanClient = Depends(get_plan_client),
):
    """SSE-streamed variant of `/messages`. Edits the linked plan when plan_id is set."""

    session = store.get(session_id, user.id)

    session.messages.append(ChatMessage(role=ChatRole.USER, content=body.content))
    store.save(session)

    # Global MAI: if the user points this chat at a specific plan (e.g. pastes
    # /plans/43 or says "kế hoạch số 43"), bind the session to it so the chat edits
    # that plan — same behaviour as the old plan-bound editor, just with the id
    # supplied via the message. Verify edit permission before binding; refuse nicely
    # (as an assistant message, not a hard 403) if the user can't edit it.
    detected_plan_id = _extract_plan_id(body.content)
    if detected_plan_id is not None and detected_plan_id != session.plan_id:
        try:
            await _assert_can_edit(plan_client, user.raw_token, detected_plan_id)
        except ForbiddenError as exc:
            return StreamingResponse(
                _one_shot_assistant_stream(store, session_id, user.id, exc.message),
                media_type="text/event-stream",
                headers=_SSE_HEADERS,
            )
        session.plan_id = detected_plan_id
        store.save(session)

    # Edit mode: chat is bound to an existing plan → propose edits, don't generate a draft.
    if session.plan_id is not None:
        return StreamingResponse(
            _consume_edit_stream(
                agent=agent,
                store=store,
                session_id=session_id,
                user_id=user.id,
                plan_id=session.plan_id,
                chat_history=list(session.messages[:-1]),
                latest_user_message=body.content,
                bearer_token=user.raw_token,
            ),
            media_type="text/event-stream",
            headers=_SSE_HEADERS,
        )

    if session.status != SessionStatus.DRAFTING:
        raise DomainError("Session is no longer in DRAFTING state")
    session.constraints = await extractor.update(
        session.constraints, body.content, conversation_text=_user_conversation_text(session)
    )
    store.save(session)

    return StreamingResponse(
        _consume_agent_stream(
            agent=agent,
            store=store,
            session_id=session_id,
            user_id=user.id,
            constraints=session.constraints,
            chat_history=list(session.messages[:-1]),
            latest_user_message=body.content,
            prior_draft=session.draft,
            bearer_token=user.raw_token,
        ),
        media_type="text/event-stream",
        headers=_SSE_HEADERS,
    )


@router.post("/sessions/{session_id}/regenerate/stream")
async def stream_regenerate(
    session_id: str,
    body: RegenerateRequest,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
    agent: AgentOrchestrator = Depends(get_agent_orchestrator),
):
    """SSE-streamed variant of `/regenerate`."""

    session = store.get(session_id, user.id)
    if session.status == SessionStatus.APPROVED:
        raise DomainError("Cannot regenerate after approval")
    if not session.constraints.is_minimally_complete():
        raise DomainError(
            "Constraints incomplete — send a planning message first to fill destination and dates"
        )
    # Nothing to regenerate until a draft exists — regenerate means "try another
    # variant of the current plan", not "start one".
    if session.draft is None:
        raise DomainError(
            "Chưa có bản nháp để tạo lại. Hãy chat với AI để dựng plan trước."
        )
    # Guard a double-click / duplicate consecutive regenerate: if the most recent
    # turn is an as-yet-unanswered [regenerate] request, one is already in flight —
    # running a second concurrently would interleave and drop turns in the in-memory
    # session. Reject the duplicate instead.
    if (
        session.messages
        and session.messages[-1].role == ChatRole.USER
        and session.messages[-1].content.startswith("[regenerate]")
    ):
        raise DomainError("Đang tạo lại bản nháp, vui lòng đợi kết quả trước khi thử lại.")

    instruction = (body.instructions or "").strip() or "Hãy thử một phương án khác."
    session.messages.append(
        ChatMessage(role=ChatRole.USER, content=f"[regenerate] {instruction}")
    )
    store.save(session)

    return StreamingResponse(
        _consume_agent_stream(
            agent=agent,
            store=store,
            session_id=session_id,
            user_id=user.id,
            constraints=session.constraints,
            chat_history=list(session.messages[:-1]),
            latest_user_message=instruction,
            prior_draft=session.draft,
            revision_hint=instruction,
            bearer_token=user.raw_token,
        ),
        media_type="text/event-stream",
        headers=_SSE_HEADERS,
    )


@router.post(
    "/sessions/{session_id}/regenerate",
    response_model=ApiResponse[SendMessageResponse],
)
async def regenerate(
    session_id: str,
    body: RegenerateRequest,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
    agent: AgentOrchestrator = Depends(get_agent_orchestrator),
):
    session = store.get(session_id, user.id)
    if session.status == SessionStatus.APPROVED:
        raise DomainError("Cannot regenerate after approval")
    if not session.constraints.is_minimally_complete():
        raise DomainError(
            "Constraints incomplete — send a planning message first to fill destination and dates"
        )

    instruction = (body.instructions or "").strip() or "Hãy thử một phương án khác."
    session.messages.append(
        ChatMessage(role=ChatRole.USER, content=f"[regenerate] {instruction}")
    )

    draft, narrative, updated_constraints = await agent.plan(
        constraints=session.constraints,
        chat_history=session.messages[:-1],
        latest_user_message=instruction,
        revision_hint=instruction,
        prior_draft=session.draft,
        bearer_token=user.raw_token,
    )
    session.constraints = updated_constraints
    if draft is not None:
        session.draft = draft
    narrative = linkify_catalog_links(narrative)
    assistant_msg = ChatMessage(role=ChatRole.ASSISTANT, content=narrative)
    session.messages.append(assistant_msg)
    store.save(session)
    missing = _missing_constraint_fields(session.constraints)
    return ApiResponse.ok(
        "Draft regenerated",
        SendMessageResponse(
            session_id=session.session_id,
            constraints=session.constraints,
            draft=session.draft,
            assistant_message=assistant_msg,
            needs_more_info=bool(missing) and session.draft is None,
            missing_fields=missing,
        ),
    )


@router.post(
    "/sessions/{session_id}/approve",
    response_model=ApiResponse[ApprovalResult],
)
async def approve_session(
    session_id: str,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
    approval: ApprovalService = Depends(get_approval_service),
):
    session = store.get(session_id, user.id)
    if session.status == SessionStatus.APPROVED:
        raise DomainError("Plan đã được tạo trước đó cho session này.")
    if session.draft is None:
        raise DomainError("Chưa có bản nháp để duyệt. Hãy chat với AI để dựng plan trước.")

    try:
        result = await approval.approve(session.draft, user.raw_token)
    except UpstreamError as exc:
        # plan-service rejected the write — surface message verbatim, not generic 500.
        logger.warning("approval upstream error session=%s: %s", session_id, exc)
        raise UpstreamError(f"Không tạo được plan: {exc}") from exc
    except RuntimeError as exc:
        logger.exception("approval failed session=%s", session_id)
        raise UpstreamError(str(exc)) from exc

    session.status = SessionStatus.APPROVED
    session.approved_plan_id = result["plan_id"]
    session.messages.append(
        ChatMessage(
            role=ChatRole.ASSISTANT,
            content=f"Đã tạo plan #{result['plan_id']} từ bản nháp. Bạn có thể mở trên board.",
        )
    )
    store.save(session)
    return ApiResponse.ok(
        "Plan created",
        ApprovalResult(plan_id=result["plan_id"], operations=result["operations"]),
    )


@router.post(
    "/sessions/{session_id}/apply-edits",
    response_model=ApiResponse[ApplyEditsResult],
)
async def apply_edits(
    session_id: str,
    user: CurrentUser = Depends(get_current_user),
    store: InMemorySessionStore = Depends(get_store),
    edit_service: EditService = Depends(get_edit_service),
    plan_client: PlanClient = Depends(get_plan_client),
):
    """Execute the agent's last proposed edit operations against the linked plan.
    This is the only place edits are written — gated behind the user pressing Áp dụng."""
    session = store.get(session_id, user.id)
    if session.plan_id is None:
        raise DomainError("Phiên này không gắn với kế hoạch nào để chỉnh sửa.")
    if not session.pending_edits:
        raise DomainError("Chưa có thay đổi nào để áp dụng. Hãy yêu cầu AI chỉnh sửa trước.")
    # Snapshot the exact proposal we are about to apply so we only clear THIS set
    # afterwards — if a newer proposal lands mid-apply we must not clobber it.
    applied_edits = list(session.pending_edits)

    # Re-verify edit permission at write time (role may have been revoked after the
    # session opened). plan-service still enforces per-op, but this fails fast with a
    # clear message instead of a partial-apply 403 storm.
    await _assert_can_edit(plan_client, user.raw_token, session.plan_id)

    try:
        operations = parse_operations(session.pending_edits)
    except Exception as exc:  # noqa: BLE001
        raise DomainError(f"Đề xuất chỉnh sửa không hợp lệ: {exc}") from exc

    try:
        result = await edit_service.apply(session.plan_id, operations, user.raw_token)
    except UpstreamError as exc:
        logger.warning("apply-edits upstream error session=%s: %s", session_id, exc)
        raise UpstreamError(f"Không áp dụng được thay đổi: {exc}") from exc

    summary = f"Đã áp dụng {result['applied']}/{result['total']} thay đổi vào kế hoạch."
    failed = [r for r in result["results"] if not r.get("ok")]
    if failed:
        summary += f" {len(failed)} thay đổi lỗi — bạn xem lại nhé."
    assistant_msg = ChatMessage(role=ChatRole.ASSISTANT, content=summary)
    # Re-read the session: a concurrent edit-stream may have appended a new proposal
    # (or messages) while we awaited the apply above. Append onto the freshest copy.
    session = store.get(session_id, user.id)
    session.messages.append(assistant_msg)
    # Clear pending edits only after the apply succeeded and all post-processing is
    # done — and only if they're still the exact set we applied. If a newer proposal
    # arrived mid-apply, leave it untouched so it isn't silently dropped.
    if session.pending_edits == applied_edits:
        session.pending_edits = []  # consumed
    store.save(session)

    return ApiResponse.ok(
        "Edits applied",
        ApplyEditsResult(
            applied=result["applied"],
            total=result["total"],
            results=result["results"],
            assistant_message=assistant_msg,
        ),
    )
