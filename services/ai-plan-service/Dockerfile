# ai-plan-service — Python / FastAPI (build context = this directory)
FROM python:3.11-slim AS base

ENV PYTHONUNBUFFERED=1 \
    PYTHONDONTWRITEBYTECODE=1 \
    PIP_NO_CACHE_DIR=1

WORKDIR /app

# Install dependencies first (better layer caching). pyproject declares the contract.
COPY pyproject.toml ./
COPY app ./app
RUN pip install --upgrade pip && pip install .

# Runtime config (overridable via environment / docker-compose)
ENV AI_PLAN_HOST=0.0.0.0 \
    AI_PLAN_PORT=8092

EXPOSE 8092

CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8092"]
