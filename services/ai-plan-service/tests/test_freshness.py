"""The agent must know today's date so it searches for current info instead of
quoting stale prices from training memory. Both per-request primers inject it."""

from datetime import date

from app.agent.orchestrator import _build_edit_messages, _format_primer
from app.models.session import Constraints


def test_plan_primer_injects_today():
    primer = _format_primer(Constraints(destination="Đà Nẵng"), None, None)
    today = date.today()
    assert f"Hôm nay là {today.isoformat()}" in primer
    assert str(today.year) in primer
    assert "web_search" in primer  # rule to look up time-sensitive facts


def test_edit_primer_injects_today():
    messages = _build_edit_messages("Plan #5 “Đà Nẵng”", [], "giá vé Asia Park")
    primer = messages[0]["content"]
    today = date.today()
    assert f"Hôm nay là {today.isoformat()}" in primer
    assert "web_search" in primer
