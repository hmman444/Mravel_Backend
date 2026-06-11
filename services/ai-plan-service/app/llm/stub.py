"""Deterministic rule-based extractor used when no external LLM is configured.

Catches Vietnamese + English patterns common in trip-planning requests:
  * destination ("đi Đà Nẵng", "to Da Nang")
  * duration ("3 ngày", "5 days")
  * relative dates ("ngày mai", "tuần sau", "cuối tuần này", "thứ 7 này")
  * absolute dates ("30/05", "30/05/2026", "2026-05-30", "30 tháng 5")
  * people count, budget, interests, pace

It still runs alongside the real LLM extractor: the regex baseline guarantees
something usable even when the LLM hallucinates or refuses JSON mode.
"""

import re
from datetime import date, timedelta
from typing import Any, Dict, List

from app.llm.base import LLMClient, LLMTurn, ToolDefinition

_DURATION_DAYS_RE = re.compile(r"(\d+)\s*(?:ngày|day|days|đêm|night|nights)", re.IGNORECASE)
_PEOPLE_RE = re.compile(r"(\d+)\s*(?:người|pax|people|persons?|adults?)", re.IGNORECASE)
_BUDGET_RE = re.compile(
    r"(\d+(?:[.,]\d+)?)\s*(triệu|tr|million|m|nghìn|k|thousand)", re.IGNORECASE
)
_DESTINATION_RE = re.compile(
    r"(?:đi|tới|đến|ở|tại|to|in|at|visit)\s+"
    r"([A-ZĐÀÁẢÃẠÂẦẤẨẪẬĂẰẮẲẴẶÊỀẾỂỄỆÔỒỐỔỖỘƠỜỚỞỠỢƯỪỨỬỮỰÙÚỦŨỤỲÝỶỸỴa-z"
    r"đàáảãạâầấẩẫậăằắẳẵặêềếểễệôồốổỗộơờớởỡợưừứửữựùúủũụỳýỷỹỵ\s]+?)"
    r"(?:\s+(?:ngày|đêm|tuần|cuối|thứ|từ|trong|for|day|days|week|on|với|with|\d)|[.,!?\n]|$)",
    re.IGNORECASE,
)

# No-trigger phrasings — "du lịch Đà Nẵng", "kế hoạch Đà Nẵng" have no đi/đến/ở,
# yet clearly name a destination. Capture the noun that follows.
_DESTINATION_NOTRIGGER_RE = re.compile(
    r"(?:du lịch|kế hoạch|lịch trình|chuyến|phượt|vi vu|khám phá)\s+"
    r"([A-ZĐÀÁẢÃẠÂẦẤẨẪẬĂẰẮẲẴẶÊỀẾỂỄỆÔỒỐỔỖỘƠỜỚỞỠỢƯỪỨỬỮỰÙÚỦŨỤỲÝỶỸỴa-z"
    r"đàáảãạâầấẩẫậăằắẳẵặêềếểễệôồốổỗộơờớởỡợưừứửữựùúủũụỳýỷỹỵ\s]+?)"
    r"(?:\s+(?:ngày|đêm|tuần|cuối|thứ|từ|trong|for|day|days|week|on|với|with|\d)|[.,!?\n]|$)",
    re.IGNORECASE,
)

# Captured tokens that are never a real destination — guards against "31/5 đi và 3/6"
# matching "đi" + "và" → destination "và".
_DESTINATION_STOPWORDS = {
    "và", "đi", "về", "rồi", "nhé", "nha", "đó", "này", "kia", "ạ", "ấy",
    "với", "cho", "tôi", "mình", "là", "ơi", "ok", "oke", "okay", "luôn",
    "thôi", "đây", "đấy", "vậy", "thì", "mà",
    # Generic travel words that are not a place name.
    "du lịch", "du lich", "chơi", "nghỉ", "nghỉ dưỡng", "đâu", "đâu đó", "phượt",
}

# Canonical Vietnamese destinations + their common surface forms (lowercased).
# Whitelist scan is the highest-precision path: it works regardless of trigger
# words and never mis-captures conjunctions.
_VN_DESTINATIONS: list[tuple[str, tuple[str, ...]]] = [
    ("Đà Nẵng", ("đà nẵng", "da nang", "danang")),
    ("Hà Nội", ("hà nội", "ha noi", "hanoi")),
    ("TP. Hồ Chí Minh", ("hồ chí minh", "ho chi minh", "sài gòn", "sai gon", "saigon", "tphcm", "hcm")),
    ("Nha Trang", ("nha trang", "nhatrang")),
    ("Đà Lạt", ("đà lạt", "da lat", "dalat")),
    ("Phú Quốc", ("phú quốc", "phu quoc", "phuquoc")),
    ("Hội An", ("hội an", "hoi an", "hoian")),
    ("Huế", ("huế", "hue")),
    ("Hạ Long", ("hạ long", "ha long", "halong")),
    ("Sa Pa", ("sa pa", "sapa")),
    ("Vũng Tàu", ("vũng tàu", "vung tau", "vungtau")),
    ("Cần Thơ", ("cần thơ", "can tho")),
    ("Phan Thiết", ("phan thiết", "phan thiet", "mũi né", "mui ne")),
    ("Quy Nhơn", ("quy nhơn", "quy nhon", "quynhon")),
    ("Hà Giang", ("hà giang", "ha giang")),
    ("Ninh Bình", ("ninh bình", "ninh binh", "tràng an", "trang an")),
    ("Côn Đảo", ("côn đảo", "con dao")),
    ("Cát Bà", ("cát bà", "cat ba")),
    ("Tam Đảo", ("tam đảo", "tam dao")),
    ("Mộc Châu", ("mộc châu", "moc chau")),
    ("Buôn Ma Thuột", ("buôn ma thuột", "buon ma thuot")),
    ("Pleiku", ("pleiku", "gia lai")),
    ("Quảng Bình", ("quảng bình", "quang binh", "phong nha")),
    ("Quảng Ninh", ("quảng ninh", "quang ninh")),
]
_PACE_RE = re.compile(r"\b(thư giãn|nghỉ ngơi|relaxed|chill|packed|sôi nổi|balanced)\b", re.IGNORECASE)

_INTEREST_KEYWORDS = {
    "biển": ["beach", "biển"],
    "núi": ["mountain", "núi", "trekking"],
    "ẩm thực": ["food", "ẩm thực", "ăn", "cuisine"],
    "lịch sử": ["history", "lịch sử", "heritage", "di tích"],
    "mua sắm": ["shopping", "mua sắm"],
    "giải trí": ["entertainment", "giải trí", "vui chơi"],
}


def _parse_budget_vnd(text: str) -> int | None:
    m = _BUDGET_RE.search(text)
    if not m:
        return None
    raw = float(m.group(1).replace(",", "."))
    unit = m.group(2).lower()
    if unit in ("triệu", "tr", "million", "m"):
        return int(raw * 1_000_000)
    if unit in ("nghìn", "k", "thousand"):
        return int(raw * 1_000)
    return int(raw)


def _parse_interests(text: str) -> list[str]:
    found: list[str] = []
    lower = text.lower()
    for label, keywords in _INTEREST_KEYWORDS.items():
        if any(k in lower for k in keywords):
            found.append(label)
    return found


def _clean_destination(raw: str) -> str | None:
    """Trim fillers and reject stopwords / junk captures."""
    dest = re.sub(r"\s+(trong|for|từ|on|với|with)\b.*$", "", raw, flags=re.IGNORECASE).strip()
    dest = dest.strip(" ,.!?-")
    if not dest:
        return None
    if dest.lower() in _DESTINATION_STOPWORDS:
        return None
    # A single short token that's a stopword-ish particle, or pure digits, is junk.
    if dest.isdigit() or len(dest) < 2:
        return None
    return dest


def _parse_destination(text: str) -> str | None:
    # 1. Highest precision: known-destination whitelist (trigger-word independent).
    lower = text.lower()
    for canonical, surfaces in _VN_DESTINATIONS:
        if any(s in lower for s in surfaces):
            return canonical
    # 2. No-trigger phrasings: "du lịch X", "kế hoạch X", "chuyến X".
    m = _DESTINATION_NOTRIGGER_RE.search(text)
    if m:
        cleaned = _clean_destination(m.group(1))
        if cleaned:
            return cleaned
    # 3. Trigger-word pattern, but reject conjunctions/particles ("đi và" → "và").
    m = _DESTINATION_RE.search(text)
    if m:
        return _clean_destination(m.group(1))
    return None


_ISO_DATE_RE = re.compile(r"(\d{4})-(\d{1,2})-(\d{1,2})")
_DM_RE = re.compile(r"(?<!\d)(\d{1,2})[/\-.](\d{1,2})(?:[/\-.](\d{2,4}))?(?!\d)")
_DM_VN_RE = re.compile(
    r"(?:ng[aà]y\s+)?(\d{1,2})\s+th[aá]ng\s+(\d{1,2})(?:\s+n[aă]m\s+(\d{2,4}))?",
    re.IGNORECASE,
)

_WEEKDAY_VN = {
    "thứ hai": 0, "thứ 2": 0, "monday": 0, "mon": 0,
    "thứ ba": 1, "thứ 3": 1, "tuesday": 1, "tue": 1,
    "thứ tư": 2, "thứ 4": 2, "wednesday": 2, "wed": 2,
    "thứ năm": 3, "thứ 5": 3, "thursday": 3, "thu": 3,
    "thứ sáu": 4, "thứ 6": 4, "friday": 4, "fri": 4,
    "thứ bảy": 5, "thứ 7": 5, "saturday": 5, "sat": 5,
    "chủ nhật": 6, "cn": 6, "sunday": 6, "sun": 6,
}


def _next_weekday(today: date, weekday: int, prefer_next: bool = False) -> date:
    delta = (weekday - today.weekday()) % 7
    if delta == 0 and prefer_next:
        delta = 7
    return today + timedelta(days=delta or 7 if prefer_next else delta)


def _try_parse_yyyymmdd(text: str) -> date | None:
    m = _ISO_DATE_RE.search(text)
    if not m:
        return None
    y, mo, d = int(m.group(1)), int(m.group(2)), int(m.group(3))
    try:
        return date(y, mo, d)
    except ValueError:
        return None


def _try_parse_dm(text: str, today: date) -> date | None:
    m = _DM_RE.search(text)
    if not m:
        return None
    d, mo, y_raw = int(m.group(1)), int(m.group(2)), m.group(3)
    if y_raw:
        y = int(y_raw)
        if y < 100:
            y += 2000
    else:
        y = today.year
        # If the date already passed this year, assume next year.
        try:
            candidate = date(y, mo, d)
        except ValueError:
            return None
        if candidate < today:
            y += 1
    try:
        return date(y, mo, d)
    except ValueError:
        return None


def _try_parse_dm_vn(text: str, today: date) -> date | None:
    m = _DM_VN_RE.search(text)
    if not m:
        return None
    d, mo, y_raw = int(m.group(1)), int(m.group(2)), m.group(3)
    if y_raw:
        y = int(y_raw)
        if y < 100:
            y += 2000
    else:
        y = today.year
        try:
            candidate = date(y, mo, d)
        except ValueError:
            return None
        if candidate < today:
            y += 1
    try:
        return date(y, mo, d)
    except ValueError:
        return None


def _try_parse_relative(text: str, today: date) -> date | None:
    """Vietnamese + English relative phrases."""
    lower = text.lower()
    if re.search(r"\bng[aà]y mai\b|\btomorrow\b", lower):
        return today + timedelta(days=1)
    if re.search(r"\bng[aà]y kia\b|\bday after tomorrow\b", lower):
        return today + timedelta(days=2)
    if re.search(r"\bh[oô]m nay\b|\btoday\b", lower):
        return today
    # "tuần sau" / "next week" → next Monday
    if re.search(r"\btu[aầ]n sau\b|\bnext week\b", lower):
        return _next_weekday(today, 0, prefer_next=True)
    # "tuần này" / "this week" → this coming Saturday (typical leisure)
    if re.search(r"\btu[aầ]n n[aà]y\b|\bthis week\b", lower):
        return _next_weekday(today, 5, prefer_next=False)
    # "cuối tuần này" / "this weekend"
    if re.search(r"\bcu[oố]i tu[aầ]n n[aà]y\b|\bthis weekend\b", lower):
        return _next_weekday(today, 5, prefer_next=False)
    # "cuối tuần sau" / "next weekend"
    if re.search(r"\bcu[oố]i tu[aầ]n sau\b|\bnext weekend\b", lower):
        sat = _next_weekday(today, 5, prefer_next=False)
        if sat <= today:
            sat += timedelta(days=7)
        return sat + timedelta(days=7) if today.weekday() >= 5 else sat
    # "thứ N này" / "this Saturday"
    for label, wd in _WEEKDAY_VN.items():
        if re.search(rf"\b{re.escape(label)}\s+(n[aà]y|this)\b", lower):
            return _next_weekday(today, wd, prefer_next=False)
        if re.search(rf"\b{re.escape(label)}\s+(sau|next)\b", lower):
            return _next_weekday(today, wd, prefer_next=True)
    return None


def _parse_dates(text: str, today: date) -> tuple[date | None, date | None]:
    """Return (start, end). Tries multiple strategies:

    1. Explicit ISO/dd-mm/vi-dates → take the first as start.
    2. Relative phrases ("ngày mai", "thứ 7 này", "tuần sau") → start.
    3. Pair extraction: "từ X đến Y" or "X - Y" two consecutive dates → start, end.
    4. Combine with `N ngày/đêm` to derive end if only start is known.
    5. Fallback: only duration → start = today + 7 days.
    """

    # Try to find one or two explicit dates.
    found: List[date] = []
    for matcher in (_try_parse_yyyymmdd, lambda t: _try_parse_dm(t, today), lambda t: _try_parse_dm_vn(t, today)):
        candidate = matcher(text) if matcher.__name__ != "<lambda>" else matcher(text)  # type: ignore[arg-type]
        if candidate:
            found.append(candidate)

    # Second pass: pair of dates "X - Y" / "từ X đến Y" using _DM_RE
    iter_pairs = list(_DM_RE.finditer(text))
    if len(iter_pairs) >= 2:
        first = _try_parse_dm(iter_pairs[0].group(0), today)
        second = _try_parse_dm(iter_pairs[1].group(0), today)
        if first and second and second >= first:
            return first, second

    # Relative
    rel = _try_parse_relative(text, today)
    if rel and rel not in found:
        found.insert(0, rel)

    duration_match = _DURATION_DAYS_RE.search(text)
    duration = max(1, int(duration_match.group(1))) if duration_match else None

    if found:
        start = found[0]
        if duration:
            end = start + timedelta(days=duration - 1)
        elif len(found) >= 2:
            end = max(found[1], start)
        else:
            end = start  # single-day trip
        return start, end

    # Duration alone ("4 ngày") with no anchor date: do NOT invent a start date —
    # silently guessing today+7 produced trips on dates the user never asked for.
    # Leave dates empty so the agent asks "đi từ ngày nào".
    return None, None


def _has_explicit_date_phrase(text: str) -> bool:
    lower = text.lower()
    if _ISO_DATE_RE.search(text) or _DM_RE.search(text) or _DM_VN_RE.search(text):
        return True
    if re.search(r"\bng[aà]y mai\b|\btomorrow\b|\btu[aầ]n sau\b|\bcu[oố]i tu[aầ]n\b", lower):
        return True
    for label in _WEEKDAY_VN:
        if label in lower:
            return True
    return False


def _parse_pace(text: str) -> str | None:
    m = _PACE_RE.search(text)
    if not m:
        return None
    token = m.group(1).lower()
    if token in ("thư giãn", "nghỉ ngơi", "relaxed", "chill"):
        return "relaxed"
    if token in ("packed", "sôi nổi"):
        return "packed"
    return "balanced"


class StubLLMClient(LLMClient):
    def supports_tool_use(self) -> bool:
        # The stub has no model — callers should skip the agent loop and use the
        # deterministic DraftComposer pipeline instead.
        return False

    async def chat_with_tools(
        self,
        system_prompt: str,
        messages: List[Dict[str, Any]],
        tools: List[ToolDefinition],
        max_tokens: int = 2048,
    ) -> LLMTurn:
        return LLMTurn(
            stop_reason="end_turn",
            text="(stub LLM — no agent loop; orchestrator should fall back to deterministic composer)",
        )

    async def extract_constraints(
        self, user_message: str, prior_constraints: Dict[str, Any]
    ) -> Dict[str, Any]:
        today = date.today()
        result: Dict[str, Any] = dict(prior_constraints)

        if not result.get("destination"):
            dest = _parse_destination(user_message)
            # Conservative sanity check (no geocoding): a real place name has at least
            # two alphanumeric characters. Reject empty/whitespace-only or purely
            # symbolic garbage ("???", "---") so it can't corrupt the constraints —
            # mirrors apply_set_constraints in app/agent/tools.py.
            if dest and len(dest.strip()) >= 2 and sum(c.isalnum() for c in dest) >= 2:
                result["destination"] = dest.strip()

        start, end = _parse_dates(user_message, today)
        if start and end:
            # Only override if user actually provided new dates (regex matched).
            if not result.get("start_date") or (
                start.isoformat() != result.get("start_date")
                and _has_explicit_date_phrase(user_message)
            ):
                result["start_date"] = start.isoformat()
                result["end_date"] = end.isoformat()

        people = _PEOPLE_RE.search(user_message)
        if people:
            result["travelers"] = max(1, int(people.group(1)))

        budget = _parse_budget_vnd(user_message)
        if budget:
            result["budget_total_vnd"] = budget

        interests = _parse_interests(user_message)
        if interests:
            prior_interests = result.get("interests") or []
            merged = list(dict.fromkeys([*prior_interests, *interests]))
            result["interests"] = merged

        pace = _parse_pace(user_message)
        if pace:
            result["pace"] = pace

        return result
