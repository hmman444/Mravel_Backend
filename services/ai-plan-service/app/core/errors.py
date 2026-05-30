from fastapi import FastAPI, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse
from starlette.exceptions import HTTPException as StarletteHTTPException

from app.core.response import ApiResponse


class DomainError(Exception):
    """Raised for business-rule violations. Maps to HTTP 400."""

    def __init__(self, message: str) -> None:
        super().__init__(message)
        self.message = message


class UnauthorizedError(Exception):
    def __init__(self, message: str = "Unauthorized") -> None:
        super().__init__(message)
        self.message = message


class ForbiddenError(Exception):
    """Raised when the caller is authenticated but lacks permission for the action
    (e.g. only VIEWER role on a plan they try to edit). Maps to HTTP 403."""

    def __init__(self, message: str = "Forbidden") -> None:
        super().__init__(message)
        self.message = message


class UpstreamError(Exception):
    """Raised when a downstream service (plan/catalog) fails. Maps to HTTP 502."""

    def __init__(self, message: str) -> None:
        super().__init__(message)
        self.message = message


def register_exception_handlers(app: FastAPI) -> None:
    @app.exception_handler(DomainError)
    async def _domain(_: Request, exc: DomainError) -> JSONResponse:
        return JSONResponse(status_code=400, content=ApiResponse.err(exc.message).model_dump(mode="json"))

    @app.exception_handler(UnauthorizedError)
    async def _unauth(_: Request, exc: UnauthorizedError) -> JSONResponse:
        return JSONResponse(status_code=401, content=ApiResponse.err(exc.message).model_dump(mode="json"))

    @app.exception_handler(ForbiddenError)
    async def _forbidden(_: Request, exc: ForbiddenError) -> JSONResponse:
        return JSONResponse(status_code=403, content=ApiResponse.err(exc.message).model_dump(mode="json"))

    @app.exception_handler(UpstreamError)
    async def _upstream(_: Request, exc: UpstreamError) -> JSONResponse:
        return JSONResponse(status_code=502, content=ApiResponse.err(exc.message).model_dump(mode="json"))

    @app.exception_handler(RequestValidationError)
    async def _validation(_: Request, exc: RequestValidationError) -> JSONResponse:
        first = exc.errors()[0] if exc.errors() else {"msg": "Invalid request"}
        return JSONResponse(
            status_code=400,
            content=ApiResponse.err(str(first.get("msg", "Invalid request"))).model_dump(mode="json"),
        )

    @app.exception_handler(StarletteHTTPException)
    async def _http(_: Request, exc: StarletteHTTPException) -> JSONResponse:
        return JSONResponse(
            status_code=exc.status_code,
            content=ApiResponse.err(str(exc.detail) if exc.detail else "Error").model_dump(mode="json"),
        )
