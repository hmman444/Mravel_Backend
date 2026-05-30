from datetime import datetime, timezone
from typing import Any, Generic, Optional, TypeVar

from pydantic import BaseModel, Field

T = TypeVar("T")


class ApiResponse(BaseModel, Generic[T]):
    """Matches `com.mravel.common.response.ApiResponse<T>` on the backend.

    Keep field names and ordering aligned so the frontend axiosInstance can read
    the same envelope across Java and Python services.
    """

    success: bool
    message: str
    data: Optional[T] = None
    timestamp: datetime = Field(default_factory=lambda: datetime.now(timezone.utc))

    @classmethod
    def ok(cls, message: str, data: T | None = None) -> "ApiResponse[T]":
        return cls(success=True, message=message, data=data)

    @classmethod
    def err(cls, message: str) -> "ApiResponse[Any]":
        return cls(success=False, message=message, data=None)
