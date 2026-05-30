"""Hybrid constraint extraction.

Step 1: always run the deterministic regex stub. Cheap, free, robust against
        weak models that ignore JSON-mode prompts. Catches the obvious
        Vietnamese/English patterns: destination, duration, people, budget.
Step 2: if a real LLM is configured, let it refine the baseline. Useful for
        ambiguous phrasing or for filling fields the regex missed.

If the LLM call fails or returns garbage, the regex baseline still wins —
the user never sees a blank constraint set due to LLM failure.
"""

from datetime import date
from typing import Any, Dict

from app.llm.base import LLMClient
from app.llm.stub import StubLLMClient, _parse_destination
from app.models.session import Constraints

_STUB_EXTRACTOR = StubLLMClient()


class ConstraintExtractor:
    def __init__(self, llm: LLMClient) -> None:
        self._llm = llm

    async def update(
        self,
        prior: Constraints,
        user_message: str,
        conversation_text: str | None = None,
    ) -> Constraints:
        """Merge new facts from `user_message` into `prior`.

        `conversation_text` (all prior user turns joined) lets us recover a
        destination stated earlier that the per-message pass missed — without it,
        a destination named only in turn 1 is lost forever once that message
        scrolls out of the single-message window.
        """
        prior_dict = prior.model_dump(mode="json", exclude_none=False)

        # Step 1 — deterministic regex baseline.
        stub_result = await _STUB_EXTRACTOR.extract_constraints(user_message, prior_dict)
        baseline = _normalize(stub_result, prior)

        # Step 1b — recover a destination mentioned in an earlier turn.
        if not baseline.destination and conversation_text:
            recovered = _parse_destination(conversation_text)
            if recovered:
                baseline = baseline.model_copy(update={"destination": recovered})

        # Step 2 — LLM refinement, only when a real LLM is wired.
        if isinstance(self._llm, StubLLMClient):
            return baseline

        baseline_dict = baseline.model_dump(mode="json", exclude_none=False)
        try:
            llm_result = await self._llm.extract_constraints(user_message, baseline_dict)
        except Exception:  # noqa: BLE001 — never fail the request because of LLM
            return baseline
        return _normalize(llm_result, baseline)


def _normalize(data: Dict[str, Any], prior: Constraints) -> Constraints:
    """Coerce LLM/stub output back into a typed Constraints, preserving prior values."""

    def _date(value: Any) -> date | None:
        if isinstance(value, date):
            return value
        if isinstance(value, str) and value:
            try:
                return date.fromisoformat(value[:10])
            except ValueError:
                return None
        return None

    interests = data.get("interests") or prior.interests or []
    if not isinstance(interests, list):
        interests = [str(interests)]

    travelers_raw = data.get("travelers", prior.travelers)
    try:
        travelers = max(1, int(travelers_raw))
    except (TypeError, ValueError):
        travelers = prior.travelers

    budget_raw = data.get("budget_total_vnd", prior.budget_total_vnd)
    try:
        budget = int(budget_raw) if budget_raw is not None else None
    except (TypeError, ValueError):
        budget = prior.budget_total_vnd

    return Constraints(
        destination=data.get("destination") or prior.destination,
        start_date=_date(data.get("start_date")) or prior.start_date,
        end_date=_date(data.get("end_date")) or prior.end_date,
        travelers=travelers,
        budget_total_vnd=budget,
        interests=[str(i) for i in interests],
        pace=data.get("pace") or prior.pace,
    )
