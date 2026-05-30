from datetime import datetime
from typing import Any, Dict, List, Optional

from pydantic import BaseModel, Field

from app.models.session import ChatMessage, Constraints, PlanDraft


class CreateSessionRequest(BaseModel):
    initial_message: Optional[str] = Field(default=None, description="Optional first user message")
    plan_id: Optional[int] = Field(
        default=None,
        description="If set, the session edits this existing plan instead of creating a new one.",
    )


class SessionView(BaseModel):
    session_id: str
    status: str
    user_id: int
    constraints: Constraints
    messages: List[ChatMessage]
    draft: Optional[PlanDraft] = None
    approved_plan_id: Optional[int] = None
    plan_id: Optional[int] = None
    pending_edits: List[Dict[str, Any]] = Field(default_factory=list)


class SessionSummary(BaseModel):
    """A row in the AI chat history list."""

    session_id: str
    title: str
    preview: str = ""
    status: str
    plan_id: Optional[int] = None
    message_count: int = 0
    updated_at: datetime


class ApplyEditsResult(BaseModel):
    applied: int
    total: int
    results: List[Dict[str, Any]]
    assistant_message: Optional[ChatMessage] = None


class SendMessageRequest(BaseModel):
    content: str = Field(min_length=1, max_length=4000)


class SendMessageResponse(BaseModel):
    session_id: str
    constraints: Constraints
    draft: Optional[PlanDraft] = None
    assistant_message: ChatMessage
    needs_more_info: bool
    missing_fields: List[str] = Field(default_factory=list)


class RegenerateRequest(BaseModel):
    instructions: Optional[str] = Field(
        default=None,
        max_length=4000,
        description="Optional natural-language hint to steer the new draft, e.g. "
        "'rẻ hơn, đổi khách sạn khác', 'thêm hoạt động biển'.",
    )


class ApprovalResult(BaseModel):
    plan_id: int
    operations: List[Dict[str, Any]]
