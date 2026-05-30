"""Manual probe (NOT a pytest test). Run once to verify your LLM credentials
and that the selected model supports tool calling on your provider.

    python tests/manual_probe.py
"""

import asyncio
import json

from app.config import get_settings
from app.llm.base import ToolDefinition
from app.llm.factory import build_llm_client


async def main() -> None:
    settings = get_settings()
    if not settings.has_llm():
        print("[skip] LLM_API_KEY not set — running in stub mode, nothing to probe.")
        return

    print(f"provider base_url : {settings.llm_base_url}")
    print(f"model             : {settings.llm_model}")
    print(f"extractor model   : {settings.effective_extractor_model()}")

    client = build_llm_client(settings)

    print("\n[1a] raw LLM extract_constraints — single-shot")
    try:
        result = await client.extract_constraints(
            "Mình muốn đi Đà Nẵng 3 ngày 2 người ngân sách 10 triệu",
            prior_constraints={"travelers": 2, "interests": []},
        )
        print(json.dumps(result, ensure_ascii=False, indent=2))
    except Exception as exc:
        print(f"  FAILED: {exc!r}")

    print("\n[1b] hybrid ConstraintExtractor (regex baseline + LLM refine)")
    from app.models.session import Constraints
    from app.services.constraint_extractor import ConstraintExtractor

    extractor = ConstraintExtractor(client)
    merged = await extractor.update(
        Constraints(travelers=2),
        "Mình muốn đi Đà Nẵng 3 ngày 2 người ngân sách 10 triệu",
    )
    print(merged.model_dump_json(indent=2))

    print("\n[2] chat_with_tools — tool-calling probe")
    tools = [
        ToolDefinition(
            name="echo",
            description="Echoes back the given string. Always call this once before answering.",
            input_schema={
                "type": "object",
                "properties": {"text": {"type": "string"}},
                "required": ["text"],
            },
        )
    ]
    turn = await client.chat_with_tools(
        system_prompt="You are a probe. You MUST call the `echo` tool once.",
        messages=[{"role": "user", "content": "Please call echo with text='hello'."}],
        tools=tools,
        max_tokens=256,
    )
    print(f"  stop_reason : {turn.stop_reason}")
    print(f"  text        : {turn.text[:200]!r}")
    print(f"  tool_uses   : {[(t.name, t.arguments) for t in turn.tool_uses]}")
    if turn.error:
        print(f"  error       : {turn.error}")

    if turn.stop_reason != "tool_use":
        print("\n[!] This model did NOT emit a tool_use call. The agent loop will")
        print("    not work with this model — orchestrator will fall back to the")
        print("    deterministic composer. Consider switching LLM_MODEL.")


if __name__ == "__main__":
    asyncio.run(main())
