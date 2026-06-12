"""Timing behaviour: ask for trip length (num_days), not calendar dates, and anchor an
undated trip to today.

Covers:
  * Constraints.resolved_date_range / is_minimally_complete with num_days only.
  * apply_set_constraints capturing num_days.
  * The clarification text asks "mấy ngày", never "từ ngày nào đến ngày nào".
"""

from datetime import date, timedelta

from app.agent.orchestrator import _clarification_text
from app.agent.tools import apply_set_constraints
from app.models.session import Constraints


def test_num_days_makes_constraints_complete_without_dates() -> None:
    c = Constraints(destination="Đà Nẵng", num_days=3)
    assert c.is_minimally_complete() is True
    assert c.duration_days() == 3


def test_resolved_date_range_anchors_to_today() -> None:
    c = Constraints(destination="Đà Nẵng", num_days=3)
    start, end = c.resolved_date_range()
    assert start == date.today()
    assert end == date.today() + timedelta(days=2)  # 3-day trip: today, +1, +2


def test_resolved_date_range_keeps_explicit_dates() -> None:
    c = Constraints(
        destination="Đà Nẵng",
        start_date=date(2026, 6, 20),
        end_date=date(2026, 6, 23),
    )
    assert c.resolved_date_range() == (date(2026, 6, 20), date(2026, 6, 23))


def test_destination_only_is_incomplete() -> None:
    assert Constraints(destination="Đà Nẵng").is_minimally_complete() is False


def test_apply_set_constraints_captures_num_days() -> None:
    prior = Constraints(destination="Đà Nẵng")
    updated = apply_set_constraints({"num_days": 4}, prior)
    assert updated.num_days == 4
    # A later turn without num_days keeps the prior value.
    again = apply_set_constraints({"travelers": 3}, updated)
    assert again.num_days == 4
    assert again.travelers == 3


def test_clarification_asks_for_days_not_dates() -> None:
    text = _clarification_text(Constraints(destination="Đà Nẵng"))
    assert "mấy ngày" in text
    assert "từ ngày nào đến ngày nào" not in text
