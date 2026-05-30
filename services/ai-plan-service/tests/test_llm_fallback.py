"""LLM resilience: error classification + fallback to another model on 429/402."""

import types

import pytest

from app.llm.openai_client import (
    OpenAILLMClient,
    _friendly_error,
    _is_retryable,
    _status_code,
)


class _HTTPLike(Exception):
    def __init__(self, code, msg="err"):
        super().__init__(f"Error code: {code} - {msg}")
        self.status_code = code


def test_status_and_retryable_classification():
    assert _status_code(_HTTPLike(429)) == 429
    assert _status_code(_HTTPLike(402)) == 402
    assert _is_retryable(_HTTPLike(429)) is True
    assert _is_retryable(_HTTPLike(402)) is True
    assert _is_retryable(_HTTPLike(400)) is False  # bad request → don't keep trying
    # Scrapes the code from a message string when no attribute is present.
    assert _status_code(RuntimeError("[429]: Rate limit exceeded")) == 429


def test_friendly_error_messages():
    assert "429" in _friendly_error(_HTTPLike(429))
    assert "402" in _friendly_error(_HTTPLike(402))


def _fake_response(text):
    msg = types.SimpleNamespace(content=text, tool_calls=None)
    choice = types.SimpleNamespace(message=msg, finish_reason="stop")
    return types.SimpleNamespace(choices=[choice])


@pytest.mark.asyncio
async def test_chat_falls_back_to_next_model_on_429():
    client = OpenAILLMClient(
        api_key="x", base_url="http://localhost:1/v1", model="primary",
        fallback_models=["backup"],
    )
    calls = []

    async def fake_create(**kwargs):
        calls.append(kwargs["model"])
        if kwargs["model"] == "primary":
            raise _HTTPLike(429)
        return _fake_response("hello from backup")

    client._client.chat.completions.create = fake_create  # type: ignore[attr-defined]

    turn = await client.chat_with_tools("sys", [{"role": "user", "content": "hi"}], tools=[])
    assert turn.stop_reason == "end_turn"
    assert turn.text == "hello from backup"
    # Primary is retried once (round-robin combo rotates) before falling back.
    assert calls == ["primary", "primary", "backup"]


@pytest.mark.asyncio
async def test_chat_returns_friendly_error_when_all_models_fail():
    client = OpenAILLMClient(
        api_key="x", base_url="http://localhost:1/v1", model="primary",
        fallback_models=["backup"],
    )

    async def fake_create(**kwargs):
        raise _HTTPLike(402)

    client._client.chat.completions.create = fake_create  # type: ignore[attr-defined]

    turn = await client.chat_with_tools("sys", [{"role": "user", "content": "hi"}], tools=[])
    assert turn.stop_reason == "error"
    assert "402" in (turn.error or "")
