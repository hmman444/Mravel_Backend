from app.config import Settings
from app.llm.base import LLMClient
from app.llm.stub import StubLLMClient


def build_llm_client(settings: Settings) -> LLMClient:
    """Pick the LLM implementation from settings.

    `LLM_API_KEY` empty → offline stub (regex extractor, no agent loop).
    `LLM_API_KEY` set    → OpenAI-compatible client. Works with OpenRouter,
                           OpenAI, Groq, Together, Ollama — anything that speaks
                           Chat Completions. Switch providers by changing
                           `LLM_BASE_URL` + `LLM_MODEL`.
    """
    if not settings.has_llm():
        return StubLLMClient()

    from app.llm.openai_client import OpenAILLMClient

    return OpenAILLMClient(
        api_key=settings.llm_api_key,
        base_url=settings.llm_base_url,
        model=settings.llm_model,
        extractor_model=settings.effective_extractor_model(),
        app_name=settings.llm_app_name,
        app_url=settings.llm_app_url,
        fallback_models=settings.fallback_model_list(),
    )
