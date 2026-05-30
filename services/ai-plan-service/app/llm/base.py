"""Provider-neutral LLM abstractions.

Implementations target OpenAI Chat Completions format (messages, tools, tool_calls)
because every major provider — OpenRouter, OpenAI, Groq, Together, Ollama, etc. —
exposes it. Switching providers means changing `LLM_BASE_URL` + `LLM_MODEL`, nothing else.
"""

from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import Any, Dict, List, Literal, Optional


@dataclass
class ToolDefinition:
    name: str
    description: str
    input_schema: Dict[str, Any]  # JSON Schema for the tool's arguments

    def to_openai_tool(self) -> Dict[str, Any]:
        return {
            "type": "function",
            "function": {
                "name": self.name,
                "description": self.description,
                "parameters": self.input_schema,
            },
        }


@dataclass
class ToolUse:
    id: str
    name: str
    arguments: Dict[str, Any]


@dataclass
class LLMTurn:
    """One assistant turn returned by the model."""

    stop_reason: Literal["tool_use", "end_turn", "max_tokens", "error"]
    text: str = ""
    tool_uses: List[ToolUse] = field(default_factory=list)
    # Raw assistant message (OpenAI format) to re-insert into the conversation for
    # the next turn. Implementations should populate this so the orchestrator does
    # not have to reconstruct tool_calls payloads itself.
    raw_assistant_message: Optional[Dict[str, Any]] = None
    error: Optional[str] = None


class LLMClient(ABC):
    @abstractmethod
    def supports_tool_use(self) -> bool:
        """False for the offline stub. When False, callers skip the agent loop."""

    @abstractmethod
    async def extract_constraints(
        self, user_message: str, prior_constraints: Dict[str, Any]
    ) -> Dict[str, Any]:
        """Single-shot JSON output for cheap, per-message constraint merging."""

    @abstractmethod
    async def chat_with_tools(
        self,
        system_prompt: str,
        messages: List[Dict[str, Any]],
        tools: List[ToolDefinition],
        max_tokens: int = 2048,
    ) -> LLMTurn:
        """Run one model turn with tool-calling enabled. `messages` is the full
        OpenAI-format conversation including any prior tool_call / tool messages."""
