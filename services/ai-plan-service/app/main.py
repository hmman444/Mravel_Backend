import logging

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.api.ai_plan import router as ai_plan_router
from app.config import get_settings
from app.core.errors import register_exception_handlers
from app.core.response import ApiResponse

logging.basicConfig(level=logging.INFO)


def create_app() -> FastAPI:
    settings = get_settings()
    app = FastAPI(title="Mravel AI Plan Service", version="0.1.0")

    app.add_middleware(
        CORSMiddleware,
        allow_origins=settings.cors_origin_list(),
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )

    register_exception_handlers(app)
    app.include_router(ai_plan_router)

    @app.get("/api/ai-plan/health")
    async def health() -> ApiResponse[dict]:
        return ApiResponse.ok("OK", {"service": "ai-plan-service", "version": "0.1.0"})

    return app


app = create_app()


if __name__ == "__main__":
    # Single-process, no reload. The VSCode task runs `uvicorn` CLI directly so
    # we don't need `reload=True` here — and avoiding it prevents orphan
    # multiprocessing children that survive parent kills on Windows.
    import uvicorn

    settings = get_settings()
    uvicorn.run("app.main:app", host=settings.ai_plan_host, port=settings.ai_plan_port)
