"""OpenAI-compatible client. Works with OpenAI, OpenRouter, Groq, Together,
Anyscale, Ollama, vLLM — anything exposing the OpenAI Chat Completions API.

The only knobs are `base_url`, `model`, and `api_key` — all in `.env`.
"""

import json
import logging
from typing import Any, Dict, List, Optional

from app.llm.base import LLMClient, LLMTurn, ToolDefinition, ToolUse

logger = logging.getLogger("ai_plan.llm")


_EXTRACTOR_SYSTEM = (
    "You are a constraint extractor for a Vietnamese travel-planning agent. "
    "Read the user's message together with prior_constraints (already-known facts) "
    "and output a single JSON object that merges them. Schema:\n"
    "{\n"
    '  "destination": string | null,\n'
    '  "start_date": "YYYY-MM-DD" | null,\n'
    '  "end_date": "YYYY-MM-DD" | null,\n'
    '  "travelers": integer (default 2),\n'
    '  "budget_total_vnd": integer | null,\n'
    '  "interests": [string],\n'
    '  "pace": "relaxed" | "balanced" | "packed" | null\n'
    "}\n"
    "Rules: preserve prior values unless contradicted; do not invent dates; "
    "if the user gives a duration in days, anchor start_date 7 days from today if not specified. "
    "Output JSON only, no commentary."
)


class OpenAILLMClient(LLMClient):
    def __init__(
        self,
        api_key: str,
        base_url: str,
        model: str,
        extractor_model: Optional[str] = None,
        app_name: str = "",
        app_url: str = "",
        fallback_models: Optional[List[str]] = None,
    ) -> None:
        from openai import AsyncOpenAI

        default_headers: Dict[str, str] = {}
        # OpenRouter convention — harmless on other providers.
        if app_url:
            default_headers["HTTP-Referer"] = app_url
        if app_name:
            default_headers["X-Title"] = app_name

        self._client = AsyncOpenAI(
            api_key=api_key,
            base_url=base_url.rstrip("/"),
            default_headers=default_headers or None,
        )
        self._model = model
        self._extractor_model = extractor_model or model
        self._fallbacks = fallback_models or []

    def supports_tool_use(self) -> bool:
        return True

    def _chat_models(self) -> List[str]:
        # The primary model is tried twice before falling back: a 9Router *combo*
        # in round-robin mode rotates to a different underlying model each call, so
        # a retry often lands on a healthy model. Then any explicit fallbacks.
        attempts = [self._model, self._model]
        for m in self._fallbacks:
            if m and m != self._model:
                attempts.append(m)
        return attempts

    async def extract_constraints(
        self, user_message: str, prior_constraints: Dict[str, Any]
    ) -> Dict[str, Any]:
        prompt = (
            f"prior_constraints:\n{json.dumps(prior_constraints, ensure_ascii=False, default=str)}\n\n"
            f"user_message:\n{user_message}\n\n"
            "Return only the JSON object."
        )
        # Try the extractor model, then fallbacks, on rate-limit/credit errors.
        seen, models = set(), []
        for m in [self._extractor_model, *self._fallbacks]:
            if m and m not in seen:
                seen.add(m)
                models.append(m)
        response = None
        for i, model in enumerate(models):
            try:
                response = await self._client.chat.completions.create(
                    model=model,
                    max_tokens=512,
                    temperature=0.1,
                    response_format={"type": "json_object"},
                    messages=[
                        {"role": "system", "content": _EXTRACTOR_SYSTEM},
                        {"role": "user", "content": prompt},
                    ],
                )
                break
            except Exception as exc:  # noqa: BLE001 — never let LLM errors kill the session
                logger.error(
                    "extract_constraints model=%s failed (status=%s): %s",
                    model, _status_code(exc), _provider_detail(exc),
                )
                if _is_retryable(exc) and i < len(models) - 1:
                    continue
                return prior_constraints
        if response is None:
            return prior_constraints

        text = response.choices[0].message.content or ""
        try:
            return json.loads(text)
        except json.JSONDecodeError:
            # Some models occasionally wrap JSON in code fences when response_format
            # is not honoured. Strip the fence and retry.
            cleaned = text.strip().strip("`")
            if cleaned.startswith("json"):
                cleaned = cleaned[4:].strip()
            try:
                return json.loads(cleaned)
            except json.JSONDecodeError:
                logger.warning("extractor returned non-JSON: %s", text[:200])
                return prior_constraints

    async def chat_with_tools(
        self,
        system_prompt: str,
        messages: List[Dict[str, Any]],
        tools: List[ToolDefinition],
        max_tokens: int = 2048,
    ) -> LLMTurn:
        full_messages: List[Dict[str, Any]] = [
            {"role": "system", "content": system_prompt},
            *messages,
        ]
        openai_tools = [t.to_openai_tool() for t in tools]

        models = self._chat_models()
        last_error: Optional[Exception] = None
        response = None
        for i, model in enumerate(models):
            try:
                kwargs: Dict[str, Any] = {
                    "model": model,
                    "max_tokens": max_tokens,
                    "temperature": 0.4,
                    "messages": full_messages,
                }
                if openai_tools:
                    kwargs["tools"] = openai_tools
                    kwargs["tool_choice"] = "auto"
                response = await self._client.chat.completions.create(**kwargs)
                break
            except Exception as exc:  # noqa: BLE001
                last_error = exc
                retryable = _is_retryable(exc)
                # Log the FULL provider error (status + raw body) at ERROR — never swallow it.
                logger.error(
                    "chat_with_tools model=%s failed (status=%s, %s): %s",
                    model, _status_code(exc), "retryable" if retryable else "fatal",
                    _provider_detail(exc), exc_info=True,
                )
                if retryable and i < len(models) - 1:
                    continue
                break

        if response is None:
            return LLMTurn(stop_reason="error", error=_friendly_error(last_error))

        choice = response.choices[0]
        msg = choice.message
        finish_reason = choice.finish_reason  # "tool_calls" | "stop" | "length"

        raw_assistant_message: Dict[str, Any] = {"role": "assistant", "content": msg.content or ""}
        if msg.tool_calls:
            raw_assistant_message["tool_calls"] = [
                {
                    "id": tc.id,
                    "type": "function",
                    "function": {"name": tc.function.name, "arguments": tc.function.arguments},
                }
                for tc in msg.tool_calls
            ]

        if msg.tool_calls:
            tool_uses = [
                ToolUse(id=tc.id, name=tc.function.name, arguments=_safe_json(tc.function.arguments))
                for tc in msg.tool_calls
            ]
            return LLMTurn(
                stop_reason="tool_use",
                text=msg.content or "",
                tool_uses=tool_uses,
                raw_assistant_message=raw_assistant_message,
            )

        if finish_reason == "length":
            return LLMTurn(
                stop_reason="max_tokens",
                text=msg.content or "",
                raw_assistant_message=raw_assistant_message,
            )

        return LLMTurn(
            stop_reason="end_turn",
            text=msg.content or "",
            raw_assistant_message=raw_assistant_message,
        )


def _provider_detail(exc: Optional[Exception]) -> str:
    """The most informative error text available: the provider's response body if
    the SDK exposes it, else str(exc). 9Router wraps the upstream error (e.g. a
    model's 429) inside the body even when the HTTP status is 400 — we want that."""
    if exc is None:
        return ""
    # openai.APIError exposes .body (parsed) and .response (httpx.Response).
    body = getattr(exc, "body", None)
    if body:
        try:
            if isinstance(body, dict):
                inner = body.get("error", body)
                if isinstance(inner, dict) and inner.get("message"):
                    return str(inner.get("message"))
            return str(body)
        except Exception:  # noqa: BLE001
            pass
    resp = getattr(exc, "response", None)
    text = getattr(resp, "text", None)
    if text:
        return str(text)[:1000]
    return str(exc)


# Provider codes that mean "this model is unavailable right now" → try another.
_RETRYABLE_CODES = (402, 408, 409, 429, 500, 502, 503, 504)


def _embedded_code(text: str) -> Optional[int]:
    """Scrape an upstream HTTP code out of an error body, e.g. '[429]' or 'code: 402'.
    9Router returns HTTP 400 wrapping the real upstream code in the message."""
    import re

    for m in re.finditer(r"\[(\d{3})\]|code[:\s]+(\d{3})", text):
        token = m.group(1) or m.group(2)
        if token and token != "400":  # 400 is the wrapper, look for the real one
            return int(token)
    return None


def _status_code(exc: Optional[Exception]) -> Optional[int]:
    """Effective code for classification: the embedded upstream code if present
    (preferred — 9Router wraps 429/402 inside a 400), else the HTTP status."""
    detail = _provider_detail(exc)
    embedded = _embedded_code(detail)
    if embedded is not None:
        return embedded
    code = getattr(exc, "status_code", None)
    if isinstance(code, int):
        return code
    return _embedded_code(str(exc or ""))


def _is_retryable(exc: Optional[Exception]) -> bool:
    """429 (rate limit) / 402 (no credit) / 4xx-conflict / 5xx → try another model."""
    return _status_code(exc) in _RETRYABLE_CODES


def _friendly_error(exc: Optional[Exception]) -> str:
    """Clear Vietnamese guidance PLUS the real provider detail — never hide the cause."""
    code = _status_code(exc)
    detail = _provider_detail(exc)[:400]
    if code == 429:
        head = "Mô hình AI hết lượt (429 — rate limit). Thử lại sau vài phút hoặc đổi LLM_MODEL/LLM_FALLBACK_MODELS."
    elif code == 402:
        head = "Mô hình AI cần thêm credit (402) trên 9Router. Nạp credit hoặc đổi sang model còn credit."
    elif code in (401, 403):
        head = "Khoá API AI không hợp lệ (401/403). Kiểm tra LLM_API_KEY."
    elif code and code >= 500:
        head = "Máy chủ AI gặp sự cố (5xx). Thử lại sau ít phút."
    else:
        head = f"Không gọi được mô hình AI (mã {code or '?'})."
    return f"{head}\nChi tiết: {detail}" if detail else head


def _safe_json(raw: str | None) -> Dict[str, Any]:
    if not raw:
        return {}
    try:
        parsed = json.loads(raw)
        return parsed if isinstance(parsed, dict) else {}
    except json.JSONDecodeError:
        return {}
