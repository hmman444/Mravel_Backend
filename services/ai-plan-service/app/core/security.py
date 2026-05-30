from dataclasses import dataclass
from typing import Optional

import jwt
from fastapi import Header

from app.config import get_settings
from app.core.errors import UnauthorizedError


@dataclass(frozen=True)
class CurrentUser:
    id: int
    raw_token: str  # full bearer token, forwarded to downstream services


def _decode_subject(token: str) -> int:
    settings = get_settings()
    try:
        if settings.jwt_secret:
            payload = jwt.decode(token, settings.jwt_secret, algorithms=[settings.jwt_algorithm])
        else:
            # Auth-service has already validated upstream (gateway/plan-service do it too).
            # We just need user_id for traceability — trust the unverified subject claim.
            payload = jwt.decode(token, options={"verify_signature": False})
    except jwt.PyJWTError as exc:
        raise UnauthorizedError(f"Invalid token: {exc}") from exc

    # Mravel auth-service stores the numeric user id in the custom `id` claim;
    # `sub` holds the user email. Other services use the same convention.
    raw = (
        payload.get("id")
        or payload.get("userId")
        or payload.get("user_id")
        or payload.get("uid")
    )
    if raw is None:
        raise UnauthorizedError("Token missing user id claim")
    try:
        return int(raw)
    except (TypeError, ValueError) as exc:
        raise UnauthorizedError("Token user id claim is not numeric") from exc


async def get_current_user(authorization: Optional[str] = Header(default=None)) -> CurrentUser:
    if not authorization or not authorization.lower().startswith("bearer "):
        raise UnauthorizedError("Missing Authorization: Bearer header")
    token = authorization.split(" ", 1)[1].strip()
    if not token:
        raise UnauthorizedError("Empty bearer token")
    return CurrentUser(id=_decode_subject(token), raw_token=token)
