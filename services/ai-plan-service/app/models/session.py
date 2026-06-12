from datetime import date, datetime, timedelta, timezone
from enum import Enum
from typing import Any, Dict, List, Optional
from uuid import uuid4

from pydantic import BaseModel, ConfigDict, Field, model_validator


class PlanActivityType(str, Enum):
    """Mirrors `com.mravel.plan.model.PlanActivityType`."""

    TRANSPORT = "TRANSPORT"
    FOOD = "FOOD"
    STAY = "STAY"
    ENTERTAIN = "ENTERTAIN"
    SIGHTSEEING = "SIGHTSEEING"
    EVENT = "EVENT"
    SHOPPING = "SHOPPING"
    CINEMA = "CINEMA"
    OTHER = "OTHER"


class PlanVisibility(str, Enum):
    PUBLIC = "PUBLIC"
    PRIVATE = "PRIVATE"
    FRIENDS = "FRIENDS"


class Constraints(BaseModel):
    destination: Optional[str] = None
    start_date: Optional[date] = None
    end_date: Optional[date] = None
    # Trip length in days when the user only says "đi 3 ngày 2 đêm" without naming a
    # calendar date. With this set we can plan immediately, anchoring the start to TODAY
    # (see resolved_date_range) — so we never have to ask "đi từ ngày nào đến ngày nào".
    num_days: Optional[int] = None
    travelers: int = 2
    budget_total_vnd: Optional[int] = None
    interests: List[str] = Field(default_factory=list)
    pace: Optional[str] = None  # "relaxed" | "balanced" | "packed"

    @model_validator(mode="after")
    def _validate_date_range(self) -> "Constraints":
        # An inverted range (end_date < start_date) yields a negative duration and an
        # empty itinerary. Normalize by swapping so downstream planning always sees a
        # valid range — raising here would break mid-conversation constraint updates
        # (apply_set_constraints / constraint_extractor build Constraints incrementally).
        # The hard, user-facing validation happens at finalize_draft.
        if (
            self.start_date is not None
            and self.end_date is not None
            and self.end_date < self.start_date
        ):
            self.start_date, self.end_date = self.end_date, self.start_date
        return self

    def is_minimally_complete(self) -> bool:
        # Enough to build an itinerary: a destination plus EITHER an explicit date range
        # OR a trip length (num_days), since num_days anchors to today by default.
        has_dates = self.start_date is not None and self.end_date is not None
        return bool(self.destination) and (has_dates or self.num_days is not None)

    def duration_days(self) -> int:
        if self.start_date and self.end_date:
            return max(1, (self.end_date - self.start_date).days + 1)
        if self.num_days:
            return max(1, self.num_days)
        return 1

    def resolved_date_range(self) -> tuple[date, date]:
        """Concrete (start, end) for planning. When the user gave only a trip length
        (num_days) and no calendar date, anchor the start to TODAY — 'đi 3 ngày' means
        a 3-day trip starting today. An explicit range is returned unchanged."""
        if self.start_date is not None and self.end_date is not None:
            return self.start_date, self.end_date
        start = self.start_date or date.today()
        return start, start + timedelta(days=self.duration_days() - 1)


class RecommendationRef(BaseModel):
    # Catalog mixes Mongo ObjectId strings and (legacy) numeric ids — coerce both to str.
    model_config = ConfigDict(coerce_numbers_to_str=True)

    catalog_kind: str  # "PLACE" | "RESTAURANT" | "HOTEL"
    catalog_id: Optional[str] = None
    catalog_slug: Optional[str] = None
    name: str
    latitude: Optional[float] = None
    longitude: Optional[float] = None
    cover_image_url: Optional[str] = None
    avg_rating: Optional[float] = None
    estimated_cost_vnd: Optional[int] = None


class DraftActivity(BaseModel):
    title: str
    description: str = ""
    day_index: int
    start_time: Optional[str] = None  # "HH:MM"
    end_time: Optional[str] = None
    duration_minutes: int = 60
    activity_type: PlanActivityType = PlanActivityType.OTHER
    estimated_cost_vnd: int = 0  # total = unit_price_vnd * quantity (when those are set)
    # Per-unit breakdown so the plan-service card can show "giá/lượt" × "số lượt/người".
    unit_price_vnd: Optional[int] = None  # price per person / per unit
    quantity: int = 1  # number of people (meals/tickets) or units (rooms/bikes)
    reason: str = ""
    recommendation: Optional[RecommendationRef] = None

    # Optional enrichment fields the AI can fill in to make cards feel real.
    # Mirrors the columns from a typical Vietnamese travel spreadsheet:
    # Địa chỉ, Ghi chú, Lộ trình, Khoảng cách.
    location_name: Optional[str] = None
    address: Optional[str] = None
    note: Optional[str] = None  # tip / reminder / "Ghi chú"
    route_hint: Optional[str] = None  # "Home tạm - Quán ăn", "HCM - Đà Nẵng"
    distance_text: Optional[str] = None  # "10.3km", "~30km", "500m"
    transport_mode: Optional[str] = None  # "xe máy", "đi bộ", "taxi", "xe khách"

    @model_validator(mode="after")
    def _validate_non_negative_cost(self) -> "DraftActivity":
        # A negative cost is never valid (it would understate the trip total and the
        # plan-service card). Reject it as a validation error rather than silently
        # storing a bad figure.
        if self.estimated_cost_vnd < 0:
            raise ValueError("estimated_cost_vnd must not be negative")
        if self.unit_price_vnd is not None and self.unit_price_vnd < 0:
            raise ValueError("unit_price_vnd must not be negative")
        return self


class DraftDay(BaseModel):
    day_index: int
    day_date: date
    title: str
    activities: List[DraftActivity] = Field(default_factory=list)


class PlanDraft(BaseModel):
    draft_id: str = Field(default_factory=lambda: str(uuid4()))
    summary: str = ""
    destination: str = ""
    start_date: date
    end_date: date
    travelers: int = 2
    budget_currency: str = "VND"
    estimated_total_cost_vnd: int = 0
    days: List[DraftDay] = Field(default_factory=list)
    warnings: List[str] = Field(default_factory=list)


class ChatRole(str, Enum):
    USER = "USER"
    ASSISTANT = "ASSISTANT"


class ChatMessage(BaseModel):
    role: ChatRole
    content: str
    created_at: datetime = Field(default_factory=lambda: datetime.now(timezone.utc))


class SessionStatus(str, Enum):
    DRAFTING = "DRAFTING"
    APPROVED = "APPROVED"


class PlanSession(BaseModel):
    session_id: str = Field(default_factory=lambda: str(uuid4()))
    user_id: int
    status: SessionStatus = SessionStatus.DRAFTING
    constraints: Constraints = Field(default_factory=Constraints)
    messages: List[ChatMessage] = Field(default_factory=list)
    draft: Optional[PlanDraft] = None
    approved_plan_id: Optional[int] = None
    # Edit mode: when the chat is opened on an existing plan, plan_id is set and the
    # agent reads/edits that plan instead of generating a new draft. `pending_edits`
    # holds the agent's latest proposed operations awaiting the user's "Áp dụng".
    plan_id: Optional[int] = None
    pending_edits: List[Dict[str, Any]] = Field(default_factory=list)
    created_at: datetime = Field(default_factory=lambda: datetime.now(timezone.utc))
    updated_at: datetime = Field(default_factory=lambda: datetime.now(timezone.utc))
