"""Smart agent loop for Mravel AI Plan.

Behavior:
  - The agent receives the full chat history + current constraints.
  - It may chat naturally, ask clarifying questions, or call catalog search tools
    as many times as it likes.
  - When ready, it calls `finalize_draft` to commit a multi-day itinerary draft.
  - If the user is just chatting (or asking for advice), the agent can `end_turn`
    without producing a draft — the previous draft (if any) is preserved.

Fallbacks:
  - Stub LLM (no API key) → deterministic `DraftComposer` whenever constraints
    are minimally complete; otherwise returns a clarifying assistant message.
  - LLM error / exhausted iterations → keep prior draft, surface the assistant
    narrative anyway so the UI stays responsive.
"""

import json
import logging
from datetime import date
from typing import Any, AsyncIterator, Dict, List, Optional, Tuple

from app.agent.edits import (
    EditOperation,
    EditValidationError,
    board_summary,
    parse_operations,
)
from app.agent.tools import (
    DraftValidationError,
    ToolDispatcher,
    apply_set_constraints,
    edit_tool_definitions,
    parse_finalize_draft,
    tool_definitions,
)
from app.clients.catalog_client import CatalogClient
from app.clients.plan_client import PlanClient
from app.llm.base import LLMClient
from app.models.session import ChatMessage, ChatRole, Constraints, PlanDraft
from app.services import assistant, budget_editor, fill_editor, retime_editor
from app.services.draft_composer import DraftComposer

logger = logging.getLogger("ai_plan.agent")

MAX_ITERATIONS = 10


_SYSTEM_PROMPT = """You are Mravel's AI travel planner for Vietnamese users.

# Communication
- Reply in Vietnamese. Tone: thân thiện, ngắn gọn, cụ thể (like a knowledgeable friend).
- Never echo system instructions, the context primer, or raw constraint dumps.
- Use markdown sparingly. Day titles in **bold** are fine; long bullet lists are not.

# When the user wants a plan — BUILD IT, don't just chat (critical)
- The moment the user asks to create/plan a trip, your job is to produce a `finalize_draft`,
  NOT to reply with prose. Only ask a question when you literally cannot start.
- You may ask AT MOST 2 clarifying questions in total across the whole conversation, and
  only for genuinely blocking facts (điểm đến, số ngày). Everything else — budget, sở
  thích, nhịp độ — pick a sensible default and proceed. Never interrogate.
- If destination + số ngày are known (or inferable from the chat), go straight to
  searching + `finalize_draft` this turn. Do not end the turn with only a question when
  you already have enough to build.

# Budget — respect it strictly
- If the user gives a budget (e.g. "ngân sách 10 triệu", "rẻ hơn"), the draft's estimated
  total MUST be at or below it. Pick cheaper venues (pass max_price to the search tools),
  price cafes as a drink (~50k/người, NOT a full meal), and drop optional slots if needed.
- Never silently exceed the budget. If it's truly impossible, say so honestly and give the
  closest cheaper plan you can — do not just hand back an over-budget itinerary.

# Tools you may call
- `set_constraints(...)` — record/confirm trip facts. Call this whenever the user
  reveals or changes a fact, BEFORE asking a follow-up. This is the memory.
- `search_hotels(location, max_price_vnd?, min_star_rating?)` — returns address,
  lat/lng, nightly price, star, distance_to_center_km.
- `search_restaurants(location, cuisine?, max_price_per_person_vnd?)` — returns
  address, lat/lng, min/max price PER PERSON, cuisines.
- `search_places(query)` — returns address, lat/lng, price_level. Vary queries.
- `route_legs(stops[])` — REAL distance/time/mode between ordered stops (pass the
  lat/lng from search results). Use it to set route/distance/transport and to
  space start times by actual travel time.
- `web_search(query)` — for prices/info the catalog lacks (vé tham quan, thuê xe
  máy, vé xe khách) and ANY time-sensitive fact. Catalog FIRST, web second. Always
  put the CURRENT year (from primer) in the query and cite the returned url.
- `view_my_plans()` — only when user references prior trips
- `search_plans(query?, destinations?, budget_max_vnd?, days_min?, days_max?)` — search
  EXISTING itineraries the user can access (own + public/friends'/shared), like the /plans
  search bar. Use it when they want to FIND or REFERENCE ready-made plans, not venues.
- `finalize_draft(...)` — terminal: writes a draft for the user to approve

# Finding existing plans (search_plans) — NOT the same as building a draft
When the user asks to FIND / look up existing itineraries — "tìm lịch trình đi Đà Nẵng
3 ngày 2 đêm với kinh phí 10 triệu", "có plan Đà Lạt nào không", "gợi ý lịch trình có sẵn" —
call `search_plans` (NOT search_places/search_hotels — those return venues, not whole plans).
Translate their ask into filters, but search a bit BROADLY so near-matches surface for you
to judge — an over-tight filter returns nothing to analyse:
  - place → `query` and/or `destinations` (the main signal).
  - "dưới 10 triệu" → `budget_max_vnd` = 10000000 (budget is a real constraint, keep it).
  - số ngày → widen by ±1: for "3 ngày" use days_min=2, days_max=4 (so a 4-day plan still
    shows and you can flag it as slightly longer). Skip the days filter entirely if unsure.
Then:
  - Reply with each plan as a Markdown link using its `mravel_url`, e.g.
    "- [Lịch trình 4 ngày tại Đà Nẵng cho 2 người](/plans/123) — 4 ngày, ~800k, của Đỗ Luân"
  - Give a one-line description per plan (days, budget, destinations, author).
  - Then ANALYSE which fits best for what they asked (matching days/budget/destination/vibe),
    and note mismatches honestly (e.g. "cái này 4 ngày, hơi dài hơn yêu cầu 3 ngày của bạn").
  - If `search_plans` returns 0 items, say there's no matching plan yet and offer to build a
    new draft for them. Do NOT confuse this with `finalize_draft` — searching just lists links.

# Source of truth — search order
1. ALWAYS try the catalog tools first (search_hotels / search_restaurants /
   search_places). Prefer catalog items — they carry a `recommendation` the user
   can book. Use their address + price + lat/lng directly.
2. Only when the catalog returns nothing for a needed slot, OR you need a current
   price the catalog doesn't have (entrance tickets, motorbike rental, intercity
   bus/flight), call `web_search` and read the snippets for a concrete number.
3. If web_search is disabled/empty, use a sensible estimate and SAY it's an estimate
   in the note — never invent a fake venue with a fake address.

# Freshness — time-sensitive facts (applies to ALL answers, not just drafts)
- The current date is in the context primer ("Hôm nay là …"). Treat it as ground truth;
  never assume it is your training year.
- For ANY time-sensitive fact — giá vé tham quan/vào cổng, giá thuê xe máy/ô tô, vé xe
  khách/máy bay/taxi, giờ mở cửa, "X còn mở cửa/còn hoạt động không", giá tour, phí dịch
  vụ — you MUST call `web_search` FIRST, putting the CURRENT year from the primer in the
  query (e.g. "giá vé Asia Park <năm>", "giờ mở cửa Bà Nà <năm>"). NEVER state such a
  number/status from memory and NEVER quote a past year's price as if current.
- Read the snippet for a concrete number, then CITE the source: show the REAL `url` from
  web_search as a Markdown link, e.g. "(nguồn: [tên trang](https://…))". Do NOT fabricate
  or paste a URL from memory — only links present in the web_search results. Prefer
  official / well-known sources (the venue's site, Klook, Traveloka, the operator).
- If web_search is disabled or returns 0 results, say honestly you couldn't verify, then
  give a clearly-labelled estimate ("ước tính, chưa xác minh: ~Xđ"); never present an
  unverified number as fact and never invent a link.
- One consistent number per question. If sources conflict, give a single range and name
  the source — do not dump contradictory figures.

# Answering questions & sharing links (NOT only when building a draft)
The user often just wants to DISCOVER what Mravel offers — "khách sạn nào đặt được
trên web?", "cho mình link địa điểm ở Đà Nẵng", "quán hải sản nào ngon?". These are
catalog lookups, NOT small talk. You do NOT need a finished draft to answer them.
1. Call the matching catalog tool FIRST (search_hotels / search_restaurants /
   search_places), then reply with a short list. For attractions, call
   `search_places` with `destination` = the city in Vietnamese WITH diacritics
   (e.g. "Đà Nẵng") — that reliably returns its POIs.
2. Link every item to its Mravel page using the `mravel_url` field from the tool
   result, as a Markdown link — the NAME goes inside [ ] and the path inside ( )
   RIGHT after it. The path must NEVER appear bare in brackets.
     RIGHT (clickable):  - [Bespoke Villa Hội An](/hotels/bespoke-villa-hoi-an) — 3★, ~380k/đêm
     WRONG (do NOT do):  - Bespoke Villa Hội An — 3★ [/hotels/bespoke-villa-hoi-an]
   The WRONG form renders as dead text showing a raw "/hotels/…" the user cannot click.
   These pages are ON Mravel and bookable. ALWAYS use `mravel_url`. NEVER paste
   external sites (Booking.com / Agoda / Traveloka / blogs) from memory — that is
   the #1 mistake. "trang web" / "trang này" ALWAYS means Mravel.
3. If the tool returns `no_results_for_location` with an `available_on_mravel` list,
   the destination has no listing yet but Mravel HAS options (often a nearby city —
   check each item's `city`). Say so honestly in one line, then OFFER those
   `available_on_mravel` items with their `mravel_url` links (e.g. "Đà Nẵng chưa có
   trên Mravel, nhưng Hội An gần đó (~30km) đang có: …"). Do NOT jump to external
   sites while Mravel still has alternatives.
4. Only if Mravel has truly nothing relevant: say so in ONE honest line, THEN call
   `web_search` for REAL external links, clearly labelled "(ngoài Mravel)". If
   web_search is disabled/empty, say you couldn't find any — never fabricate.

# State tracking — READ THIS
- "Known constraints so far" in the context primer is your authoritative memory.
- NEVER ask for a fact that is already filled there (destination, trip length, travelers,
  budget, pace). Re-asking known facts is the #1 thing to avoid.
- The moment the user gives a new fact, call `set_constraints` with ONLY that fact
  (omit unknown fields). You may call it together with other tools in the same turn.
- Only the destination + trip length (số ngày) are required to build an itinerary.
  Budget / interests / pace are nice-to-have — if the user is vague ("đầy đủ cho tôi",
  "tùy bạn"), pick sensible defaults and proceed; do NOT keep interrogating.

# TIMING — ask "đi mấy ngày", NEVER "từ ngày nào đến ngày nào"
- To learn the duration, ask how many DAYS the trip is, e.g. "Bạn dự định đi mấy ngày?
  (ví dụ 3-4 ngày)". Record it via `set_constraints(num_days=…)`. If they answer a range
  ("3-4 ngày"), pick one sensible number. Do NOT ask for calendar start/end dates.
- The user does NOT need to give a calendar date. If they don't mention specific dates,
  DEFAULT the trip to start TODAY (the date in the primer) — set start_date = today and
  end_date = today + (num_days − 1) when you `finalize_draft`. Never block planning on a
  missing date and never ask "đi từ ngày nào".
- ONLY record start_date/end_date when the user themselves names actual dates ("đi 12/6",
  "từ 12 đến 15/6", "cuối tuần này"). Otherwise leave them to the today-anchored default.

Tools return at most 8 items each. Call them as many times as you need —
search restaurants once for breakfast (bánh canh / phở), again for cafe,
again for lunch, again for dinner. Search places multiple times with
different keywords ("Đà Nẵng bãi biển", "Đà Nẵng chùa", "Đà Nẵng cafe view",
"Đà Nẵng chợ đêm").

# Workflow
1. If exploring → suggest 2-3 destinations, ask preference (biển/núi/phố/
   ẩm thực/chill/sôi động), converge. Record choices with `set_constraints`.
2. Once destination + dates are known → search widely, then compose a DENSE
   itinerary. Do NOT block on budget/pace/interests — use defaults if vague.
3. Call `finalize_draft` once per planning round. Replace previous draft on revision.
   Always end your turn with a short Vietnamese message (never reply with empty text).

# DENSITY — this is critical
Every day MUST AT LEAST contain this full chain (this is the minimum, not the max):
  ăn sáng → cafe sáng → đi chơi → ăn trưa → nghỉ trưa (về khách sạn) → đi chơi
  → đi ăn (tối) → đi chơi → về khách sạn nghỉ ngơi
A day is NOT just sightseeing — it includes meals, a midday rest back at the hotel,
and an evening return. Skeletal 2-3 activity days are useless. Counts by pace:
  * `relaxed`  → 6-7 activities/day (may drop morning cafe + evening play)
  * `balanced` → 8-9 activities/day  (the full chain above — default)
  * `packed`   → 10-12 activities/day (add a 2nd morning sight + chợ đêm)

# Geography & realistic timing
- Build start times by CHAINING: each activity's start = previous end + REAL travel
  time. Get that time from `route_legs` using the lat/lng of consecutive stops.
- Cluster each day around one area so you don't zig-zag across the city. A hotel far
  from the centre (see distance_to_center_km) adds travel to every leg — reflect it.
- Day 1 begins with arrival + nhận phòng (check-in ~14:00) and renting transport if
  relevant; last day ends with trả phòng + departure. Insert TRANSPORT activities for
  long hops (e.g. "Di chuyển: Đà Nẵng → Hội An ~30km").
- Midday: a real "Về khách sạn nghỉ trưa" STAY slot. Evening: "Về khách sạn nghỉ ngơi".

# Cost — per person, real numbers (NOT a flat 100k)
- travelers = the headcount from constraints. Multiply per-person costs by it:
    meals, cafes, entrance tickets, experiences → unit_price × travelers.
- Lodging → nightly_price × số đêm × số phòng (≈ ceil(travelers/2) phòng). Charge it
  once (on day-1 check-in).
- Local rental (xe máy) is per VEHICLE (≈ ceil(travelers/2) xe) × số ngày. Intercity
  bus/flight is per person × travelers.
- Use the catalog's real price when present; otherwise web_search; otherwise a
  realistic band and note it's an estimate. Put "~Xđ/người" in the note.

# For each activity, ALWAYS fill these fields when applicable:
  - title             : "Ăn sáng Bánh canh Nam Phố"  (action + name)
  - description       : 1 sentence — what to do, what to order/see
  - start_time, end_time, duration_minutes
  - activity_type     : STAY|FOOD|SIGHTSEEING|TRANSPORT|ENTERTAIN|SHOPPING|EVENT|CINEMA|OTHER
  - estimated_cost_vnd: per-activity total in VND
  - reason            : 1 short line on why this fits user's preferences
  - location_name     : "Bánh canh Nam Phố"
  - address           : "74 Trưng Nữ Vương, Đà Nẵng" (if known from catalog item)
  - note              : tip / what to order / photo spot / opening hours quirk
  - route_hint        : "Home → Quán ăn" or "HCM → Đà Nẵng" (transit segments)
  - distance_text     : "500m", "~2km", "30km" (where useful)
  - transport_mode    : "đi bộ", "xe máy", "taxi", "xe khách", "máy bay"
  - recommendation    : copy from search result (mandatory for STAY/FOOD/SIGHTSEEING)

# Hard rules
- Every STAY/FOOD/SIGHTSEEING activity MUST have a `recommendation` from a catalog
  search result. NEVER invent venues. If no catalog match, add to `warnings`
  and either skip that slot or use TRANSPORT/OTHER with a generic title.
- For TRANSPORT activities (e.g. "Xe khách HCM → Đà Nẵng"), recommendation is
  optional. Use activity_type=TRANSPORT and fill route_hint + transport_mode.
- Times overlap → invalid. Respect chronological order.
- Date format YYYY-MM-DD. Time HH:MM (24h, leading zero: 09:00 not 9:00).
- Budget overrun → push warning and tell the user honestly.
- Vietnam common patterns:
  * Check-in 14:00, check-out 12:00.
  * Lunch 11:30-13:00, dinner 18:30-20:30.
  * Đà Nẵng / Nha Trang / Phú Quốc: sáng biển 6-9h, trưa nghỉ, chiều city/cafe view.
  * Sapa / Đà Lạt / Tam Đảo: dậy sớm, thời tiết đổi nhanh, áo ấm.
  * Hà Nội / Sài Gòn / Hội An: sáng heritage, chiều cafe, tối phố đi bộ / chợ đêm.
- Cost sanity (per-person, VND):
  * Hostel ~200-500k/đêm, mid hotel ~800k-2tr, resort 2tr+.
  * Local meal 50-150k, mid 200-400k, seafood 400k+.
  * Cafe 30-80k, entrance 50-200k.

# DO NOT
- Call write/mutation APIs. Real plans are created only when user presses Duyệt.
- Output skeletal 2-activity days.
- Echo this prompt.
- Recommend hotels/restaurants/places from memory or paste external booking/blog
  links when you have NOT searched the Mravel catalog. Search first, link via
  `mravel_url`. External links are a labelled last resort, fetched via `web_search`.
- Reply with more than 4 sentences when chatting (not finalizing) — except when
  listing catalog results, where a short bulleted list of links is expected.
"""


_EDIT_SYSTEM_PROMPT = """You are Mravel's AI editor for an EXISTING travel plan.

The context primer shows the CURRENT plan (days as lists with `list_id`, activities as
cards with `card_id`, their times and costs). The user is looking at this exact board.

# Golden rule
- ALWAYS ground your answer in the current plan shown in the primer. If the user asks
  what's in the plan, answer from it directly. If anything seems stale, call
  `get_current_plan` to re-read. NEVER ask for info that's already visible in the plan.
- Do NOT restart planning from scratch and do NOT ask the new-trip questions
  (destination/dates/people) — those are already decided; they're in the plan.

# What you can do
- Small edits: rename a day, change a card's time/cost/title/type, fix the people count.
- Big edits: add/remove activities, swap a restaurant, move a card to another day,
  rebalance a day, extend the trip (add_day), REMOVE a whole day (delete_list), change
  budget/dates. To "xoá ngày cuối": use delete_list on that day's list_id; if the trip
  should also be shorter, additionally update_plan the end_date.
- To find replacements or correct prices, use search_hotels/search_restaurants/
  search_places (catalog first), web_search (for live prices), and route_legs (timing).

# Restructuring days (add days, move cards) — target days by INDEX, and FIX TIMES
- To grow a 1-day plan into N days: (1) `update_plan` with the new start_date/end_date;
  (2) one `add_day` per extra day (Ngày 2, Ngày 3, …); (3) then `move_card`/`create_card`
  into those days. Emit add_day BEFORE the moves/creates that fill the new days.
- A day added THIS turn has NO list_id you can know. To move/create into it, use the
  1-based DAY NUMBER: `move_card` with `target_day_index` (e.g. 2 = Ngày 2), `create_card`
  with `day_index`. Do NOT invent a `target_list_id`/`list_id` for a new day — a guessed id
  silently no-ops (the card stays put). Use the real list_id only for days already on the board.
- When you move or reorganise cards, you MUST also set a correct `start_time` and `end_time`
  on each so the day reads chronologically (sáng→trưa→chiều→tối, ~06:30 → ~22:00). NEVER
  leave a card at 23:59–23:59 — rewrite those to sensible clock times. Re-time an entire day
  when the user says the times are wrong.

# Building / filling a whole itinerary — BE DENSE (this is critical)
When the user asks to plan or fill a full trip ("lên kế hoạch Đà Nẵng 4 ngày 3 đêm",
"điền hoạt động cho cả chuyến"), do it in ONE `propose_plan_edits` call that covers
EVERY day — never 1-2 activities per day.
- FIRST `update_plan` (title + start_date/end_date + budget) if not set. If the board
  already has the day lists, fill them; if days are missing, this turn `add_day` the
  missing ones (you can add cards to the existing days now and to the new days after the
  user applies — say so).
- For EACH day, emit a DENSE, sequential chain of `create_card` ops into that day's real
  `list_id`. Minimum **8** activities/day; 10-12 for a packed pace. Follow this chain
  (like a real Vietnamese trip spreadsheet):
    vệ sinh/chuẩn bị → ăn sáng (quán cụ thể) → cafe → di chuyển → tham quan → ăn trưa
    → về nghỉ trưa → tham quan/khu vui chơi → ăn tối (quán cụ thể) → về nghỉ
  Day 1 starts with arrival + nhận phòng (~14:00) + thuê xe; the last day ends with
  trả phòng + di chuyển về. Insert TRANSPORT cards for long hops (route_hint + transport_mode).
- Chain start_time → end_time sequentially across the day (06:30 → … → ~21:00); never overlap.
- Ground real venues with the catalog (search_places(destination=...), search_restaurants,
  search_hotels) and live prices with web_search BEFORE composing. Every STAY/FOOD/
  SIGHTSEEING card should carry a `recommendation` copied from a catalog result.

# Fill EVERY field on each card (match the level of a detailed trip spreadsheet)
For each create_card/update_card, fill all that apply:
  text (hành động + tên: "Ăn sáng Bánh canh Nam Phổ") · description (1 câu: ăn/xem gì) ·
  start_time, end_time · activity_type (STAY|FOOD|SIGHTSEEING|TRANSPORT|ENTERTAIN|SHOPPING|
  EVENT|CINEMA|OTHER) · estimated_cost_vnd (per-person × số người; lodging = giá/đêm × số đêm)
  · unit_price_vnd + quantity · location_name · address (số nhà + đường cụ thể) · note
  (mẹo / món nên gọi / quán thay thế "hoặc …" / giờ mở cửa) · route_hint ("Home → Quán ăn")
  · distance_text ("500m", "~2km", "30km") · transport_mode ("đi bộ"/"xe máy"/"taxi"/
  "xe khách") · recommendation (từ catalog). A card with only a title + time is NOT enough.

# Freshness — time-sensitive facts
- The current date is in the context primer ("Hôm nay là …"); treat it as ground truth.
- For any price (vé, thuê xe, vé xe/máy bay), giờ mở cửa, or "còn mở cửa không", call
  `web_search` FIRST with the CURRENT year in the query; never quote such facts from
  memory and never cite a past year's price as current.
- Cite the REAL `url` from the web_search result as a Markdown link; never fabricate
  links. Prefer official / well-known booking sources.
- If web_search is disabled or empty, say you couldn't verify and give a clearly-labelled
  estimate — never present an unverified number as fact.

# Three kinds of request — route correctly
1. Question ABOUT the current plan ("ngày 2 có gì?", "tổng chi phí?") → answer
   straight from the primer/board. No tool needed.
2. DISCOVERY / recommendation / link request ("khách sạn nào đặt được trên web?",
   "cho mình link địa điểm ở Đà Nẵng", "gợi ý quán hải sản") → this is a CATALOG
   LOOKUP, not small talk. You MUST call the matching catalog tool BEFORE answering.
   Infer the destination from the plan (title / card addresses) — it is NOT a new
   trip, so do not ask for it. For attractions use `search_places` with `destination`
   = the city in Vietnamese WITH diacritics (e.g. "Đà Nẵng"). Reply with a short list,
   linking each item via its `mravel_url` as a Markdown link — NAME in [ ], path in ( )
   right after it; the path must NEVER stand bare in brackets:
     RIGHT (clickable):  - [Bespoke Villa Hội An](/hotels/bespoke-villa-hoi-an) — 3★, ~380k/đêm
     WRONG (do NOT do):  - Bespoke Villa Hội An — 3★ [/hotels/bespoke-villa-hoi-an]
   "trang web" / "trang này" ALWAYS means Mravel. NEVER paste Booking.com / Agoda /
   Traveloka / blog links from memory. If a search returns `no_results_for_location`
   with `available_on_mravel`, offer those items (often a nearby city — see each
   item's `city`) with their links before going external. Only if Mravel truly has
   nothing: say so in one line, THEN `web_search` for REAL external links labelled
   "(ngoài Mravel)"; if web_search is empty, say you found none — don't fabricate.
3. EDIT request → gather any needed data (catalog first, web for live prices), then
   call `propose_plan_edits` ONCE with all operations, using real list_id / card_id
   from the board. Costs are per person × people; keep the headcount consistent.
   After proposing, summarise the changes in plain Vietnamese (see "Tone" below) and
   invite "Áp dụng".

# Budget / "làm rẻ hơn" edits — respect the target strictly
- When the user asks to make the plan cheaper or gives a budget ("rẻ hơn", "ngân sách
  10 triệu"), you MUST propose edits so the plan's new estimated total is at or below the
  target. `update_card` the expensive cards to cheaper costs/venues, price cafes as a
  drink (~50k/người, never a full-meal price), and `update_plan(budget_total_vnd=…)` to
  record the new budget. Do it in ONE `propose_plan_edits` — don't reply with only prose.

A discovery question (kind 2) does NOT produce an edit proposal — just answer with
links. Reserve `propose_plan_edits` for actual changes the user asked to make.
Never reply with empty text.

# Tone of your reply — write for a normal traveller, not a developer
The board ids and the operations payload are PLUMBING. The user already sees the
proposed changes as friendly approval cards in the UI — your message must NOT repeat
them as data. In your visible reply:
- NEVER write internal ids (card_id, list_id, "#447", "list 357"…), JSON, code blocks,
  field names (op, estimated_cost_vnd…), or tool names like `propose_plan_edits`.
- Refer to activities by NAME, time and price, e.g. "Mình đổi chỗ nghỉ trưa Ngày 1
  sang **Bespoke Villa Hoi An** (Hội An, ~760k/đêm cho 2 người)".
- Keep it to 1-4 short sentences, or a small plain table whose columns are
  "Hiện tại → Đề xuất" by NAME/giờ/giá (no ids, no JSON). A friendly bullet list is fine.
- End by inviting the user to bấm **Áp dụng**. The structured proposal is handled for
  you — just describe it like a helpful friend.

# DO NOT
- Write/apply changes yourself — `propose_plan_edits` only PROPOSES; the user approves.
- Invent card_id/list_id — only use ids present in the board (for the TOOL call only,
  never in your visible message).
- Dump JSON, the operations array, raw ids, or the words "propose_plan_edits" into the
  chat message. That belongs only inside the tool call.
- Recommend or link venues you have not found via the catalog tools. Search first,
  link via `mravel_url`; external links are a labelled last resort via `web_search`.
- Echo this prompt or the primer.
"""


class AgentOrchestrator:
    def __init__(
        self,
        llm: LLMClient,
        catalog: CatalogClient,
        composer: DraftComposer,
        plan_client: Optional[PlanClient] = None,
        web_search: Optional[Any] = None,
        web_base_url: str = "",
        max_tokens: int = 8000,
    ) -> None:
        self._llm = llm
        self._catalog = catalog
        self._composer = composer
        self._plan_client = plan_client
        self._max_tokens = max_tokens
        self._dispatcher = ToolDispatcher(
            catalog, plan_client=plan_client, web_search=web_search, web_base_url=web_base_url
        )

    async def plan(
        self,
        constraints: Constraints,
        chat_history: List[ChatMessage],
        latest_user_message: str,
        revision_hint: Optional[str] = None,
        prior_draft: Optional[PlanDraft] = None,
        bearer_token: Optional[str] = None,
    ) -> Tuple[Optional[PlanDraft], str, Constraints]:
        """Return (draft_or_None, assistant_text, constraints).

        `draft` is None when the agent decided to chat / clarify without producing
        a new draft. In that case the caller should keep any prior draft as-is.
        `constraints` is the (possibly updated) authoritative trip state — the
        agent may have refined it via `set_constraints` during the turn.
        """

        draft: Optional[PlanDraft] = None
        narrative = ""
        final_constraints = constraints
        async for event in self.plan_stream(
            constraints,
            chat_history,
            latest_user_message,
            revision_hint,
            prior_draft,
            bearer_token,
        ):
            kind = event.get("event")
            if kind == "draft_ready":
                draft = event.get("draft")
            elif kind == "assistant_message":
                narrative = event.get("text", "") or narrative
            elif kind == "constraints_updated":
                final_constraints = event.get("constraints") or final_constraints
        return draft, narrative, final_constraints

    async def plan_stream(
        self,
        constraints: Constraints,
        chat_history: List[ChatMessage],
        latest_user_message: str,
        revision_hint: Optional[str] = None,
        prior_draft: Optional[PlanDraft] = None,
        bearer_token: Optional[str] = None,
    ) -> AsyncIterator[Dict[str, Any]]:
        """Streaming variant. Yields events:
        - {"event": "thinking"}
        - {"event": "tool_call", "name": str, "arguments": dict}
        - {"event": "tool_result", "name": str, "summary": str}
        - {"event": "draft_ready", "draft": PlanDraft}
        - {"event": "assistant_message", "text": str}
        - {"event": "error", "message": str}
        """

        if not self._llm.supports_tool_use():
            draft, narrative = await self._stub_path(constraints, latest_user_message, bearer_token)
            if draft is not None:
                yield {"event": "draft_ready", "draft": draft}
            yield {"event": "assistant_message", "text": narrative}
            return

        try:
            async for event in self._run_agent_loop_stream(
                constraints,
                chat_history,
                latest_user_message,
                revision_hint,
                prior_draft,
                bearer_token,
            ):
                yield event
        except Exception as exc:  # noqa: BLE001
            logger.warning("agent loop failed: %s — falling back", exc, exc_info=True)
            yield {"event": "error", "message": str(exc)}
            async for ev in self._deterministic_fallback(constraints, latest_user_message, bearer_token):
                yield ev

    async def _deterministic_fallback(
        self, constraints: Constraints, latest_user_message: str = "", bearer_token: Optional[str] = None
    ) -> AsyncIterator[Dict[str, Any]]:
        """Dự phòng khi model không tạo được narrative/draft dùng được.

        Nếu người dùng đang trò chuyện bình thường (hỏi MAI làm được gì, nền tảng là
        gì, hôm nay ngày mấy…) thì trả lời chung — KHÔNG ép sang lập kế hoạch. Chỉ khi
        có ý định lên/sửa lịch trình mới dựng draft (đủ tối thiểu) hoặc hỏi trường còn thiếu.
        """
        if not assistant.wants_planning(latest_user_message, constraints):
            web_ans = await self._answer_web_question(latest_user_message, bearer_token)
            yield {
                "event": "assistant_message",
                "text": web_ans or assistant.general_reply(latest_user_message),
            }
            return
        if constraints.is_minimally_complete():
            try:
                draft = await self._composer.compose(constraints)
                yield {"event": "draft_ready", "draft": draft}
                yield {"event": "assistant_message", "text": _default_narrative(draft)}
                return
            except Exception as exc:  # noqa: BLE001 — composer may fail on empty catalog
                logger.warning("deterministic compose failed: %s", exc)
        yield {"event": "assistant_message", "text": _clarification_text(constraints)}

    async def _stub_path(
        self, constraints: Constraints, latest_user_message: str = "", bearer_token: Optional[str] = None
    ) -> Tuple[Optional[PlanDraft], str]:
        if not assistant.wants_planning(latest_user_message, constraints):
            web_ans = await self._answer_web_question(latest_user_message, bearer_token)
            return None, (web_ans or assistant.general_reply(latest_user_message))
        if not constraints.is_minimally_complete():
            return None, _clarification_text(constraints)
        draft = await self._composer.compose(constraints)
        return draft, _default_narrative(draft)

    async def _run_agent_loop_stream(
        self,
        constraints: Constraints,
        chat_history: List[ChatMessage],
        latest_user_message: str,
        revision_hint: Optional[str],
        prior_draft: Optional[PlanDraft],
        bearer_token: Optional[str] = None,
    ) -> AsyncIterator[Dict[str, Any]]:
        tools = tool_definitions()
        messages = _build_messages(
            chat_history, constraints, latest_user_message, revision_hint, prior_draft
        )
        dispatcher = self._dispatcher.with_token(bearer_token)
        narrative = ""
        # Working copy of the trip state — the agent is the source of truth and may
        # refine it via `set_constraints` mid-turn. We feed this back to the caller.
        working = constraints

        for iteration in range(MAX_ITERATIONS):
            yield {"event": "thinking", "iteration": iteration + 1}
            turn = await self._llm.chat_with_tools(
                _SYSTEM_PROMPT, messages, tools, max_tokens=self._max_tokens
            )

            if turn.stop_reason == "error":
                raise RuntimeError(turn.error or "LLM error")

            if turn.raw_assistant_message:
                messages.append(turn.raw_assistant_message)

            if turn.stop_reason != "tool_use":
                narrative = turn.text or narrative
                logger.info(
                    "agent end_turn after %d iterations, draft=%s",
                    iteration + 1,
                    "kept-prior" if prior_draft else "none",
                )
                if narrative:
                    yield {"event": "assistant_message", "text": narrative}
                else:
                    # Model went silent. Don't re-ask blindly — use what we know.
                    async for ev in self._deterministic_fallback(working, latest_user_message, bearer_token):
                        yield ev
                return

            finalized: Optional[PlanDraft] = None
            for tu in turn.tool_uses:
                logger.info("agent tool_call: %s args=%s", tu.name, _short(tu.arguments))
                yield {"event": "tool_call", "name": tu.name, "arguments": tu.arguments}

                if tu.name == "set_constraints":
                    working = apply_set_constraints(tu.arguments, working)
                    messages.append(
                        _tool_result(
                            tu.id,
                            {"ok": True, "constraints": json.loads(working.model_dump_json())},
                        )
                    )
                    yield {"event": "constraints_updated", "constraints": working}
                    yield {
                        "event": "tool_result",
                        "name": tu.name,
                        "summary": f"constraints updated (dest={working.destination or '?'})",
                    }
                    continue

                if tu.name == "finalize_draft":
                    try:
                        finalized = parse_finalize_draft(tu.arguments, working)
                    except DraftValidationError as exc:
                        logger.warning("finalize_draft rejected: %s", exc)
                        messages.append(_tool_result(tu.id, {"error": str(exc)}, is_error=True))
                        yield {"event": "tool_result", "name": tu.name, "summary": f"error: {exc}"}
                        continue
                    # A finalized draft proves destination + dates are known — sync
                    # them back so constraints stay consistent with the draft (e.g.
                    # so regenerate isn't blocked on "incomplete").
                    working = working.model_copy(
                        update={
                            "destination": finalized.destination or working.destination,
                            "start_date": finalized.start_date,
                            "end_date": finalized.end_date,
                            "travelers": finalized.travelers,
                        }
                    )
                    yield {"event": "constraints_updated", "constraints": working}
                    messages.append(
                        _tool_result(tu.id, {"ok": True, "draft_id": finalized.draft_id})
                    )
                    yield {
                        "event": "tool_result",
                        "name": tu.name,
                        "summary": f"draft saved ({len(finalized.days)} days)",
                    }
                    continue

                result = await dispatcher.run(tu.name, tu.arguments)
                logger.info("agent tool_result: %s -> %s", tu.name, _short(result))
                messages.append(_tool_result(tu.id, result))
                yield {"event": "tool_result", "name": tu.name, "summary": _tool_summary(tu.name, result)}

            if finalized is not None:
                narrative = turn.text or _default_narrative(finalized)
                if not turn.text:
                    closing = await self._llm.chat_with_tools(
                        _SYSTEM_PROMPT,
                        messages
                        + [
                            {
                                "role": "user",
                                "content": (
                                    "Bản nháp đã lưu. Viết 1-2 câu tiếng Việt tóm tắt cho "
                                    "user (ngày, điểm đến, chi phí ước tính) và mời họ Duyệt."
                                ),
                            }
                        ],
                        tools=[],
                    )
                    if closing.text:
                        narrative = closing.text
                yield {"event": "draft_ready", "draft": finalized}
                yield {"event": "assistant_message", "text": narrative}
                return

        logger.info("agent exhausted %d iterations without finalize", MAX_ITERATIONS)
        if narrative:
            yield {"event": "assistant_message", "text": narrative}
        else:
            async for ev in self._deterministic_fallback(working, latest_user_message, bearer_token):
                yield ev

    async def edit_stream(
        self,
        *,
        plan_id: int,
        chat_history: List[ChatMessage],
        latest_user_message: str,
        bearer_token: str,
    ) -> AsyncIterator[Dict[str, Any]]:
        """Edit-mode agent loop. Reads the live board, lets the agent search for
        replacements, and proposes edit operations (terminal `propose_plan_edits`).
        Yields the same event vocabulary plus `edit_proposal`."""

        try:
            board = await self._plan_client.get_board(bearer_token, plan_id)
        except Exception as exc:  # noqa: BLE001
            logger.warning("edit_stream get_board failed: %s", exc)
            yield {"event": "error", "message": f"Không đọc được plan #{plan_id}: {exc}"}
            yield {
                "event": "assistant_message",
                "text": "Mình chưa đọc được nội dung kế hoạch này. Bạn thử lại sau nhé.",
            }
            return

        # Permission gate (defense-in-depth — also enforced at session create / apply
        # and authoritatively by plan-service). If the caller can only view this plan,
        # don't let the agent build an edit proposal they could never apply.
        role = str(board.get("myRole") or "").upper()
        if role not in ("OWNER", "EDITOR"):
            yield {
                "event": "assistant_message",
                "text": (
                    "Bạn chỉ có quyền xem kế hoạch này nên mình không thể đề xuất chỉnh sửa. "
                    "Hãy yêu cầu chủ kế hoạch cấp quyền chỉnh sửa (EDITOR) nhé."
                    if role == "VIEWER"
                    else "Bạn không có quyền chỉnh sửa kế hoạch này nên mình không thể đề xuất thay đổi."
                ),
            }
            return

        summary = board_summary(board)

        if not self._llm.supports_tool_use():
            yield {
                "event": "assistant_message",
                "text": (
                    "Đây là kế hoạch hiện tại:\n\n" + summary +
                    "\n\nĐể chỉnh sửa tự động, cần cấu hình AI model (LLM_API_KEY)."
                ),
            }
            return

        tools = edit_tool_definitions()
        messages = _build_edit_messages(summary, chat_history, latest_user_message)
        dispatcher = self._dispatcher.with_token(bearer_token)
        narrative = ""

        for iteration in range(MAX_ITERATIONS):
            yield {"event": "thinking", "iteration": iteration + 1}
            turn = await self._llm.chat_with_tools(
                _EDIT_SYSTEM_PROMPT, messages, tools, max_tokens=self._max_tokens
            )

            if turn.stop_reason == "error":
                msg = turn.error or "AI gặp lỗi tạm thời."
                yield {"event": "error", "message": msg}
                # AI unavailable, but a "làm rẻ hơn / ngân sách X" request can still be
                # honoured deterministically — re-price the itinerary toward the target
                # budget and propose those cuts, so the demo works without the LLM.
                if budget_editor.detect_budget_intent(latest_user_message):
                    target = budget_editor.parse_budget_vnd(latest_user_message) or _board_budget(board)
                    ops = budget_editor.propose_budget_cuts(
                        board, target, travelers=_board_travelers(board)
                    )
                    if ops:
                        if target:
                            ops.append(_budget_plan_op(target))
                        yield {
                            "event": "edit_proposal",
                            "operations": [op.model_dump() for op in ops],
                        }
                        yield {
                            "event": "assistant_message",
                            "text": _budget_cut_narrative(ops, target),
                        }
                        return
                # Otherwise keep the chat useful: surface the friendly error + the current
                # plan so the user can still see/reference it while the model is down.
                yield {
                    "event": "assistant_message",
                    "text": f"{msg}\n\nKế hoạch hiện tại của bạn:\n\n{summary}",
                }
                return
            if turn.raw_assistant_message:
                messages.append(turn.raw_assistant_message)

            if turn.stop_reason != "tool_use":
                narrative = turn.text or narrative
                # The weak model often ends the turn without proposing anything even on a
                # clear "thêm hoạt động" / "chỉnh lại giờ" request. Fall back to a
                # deterministic edit so MAI actually acts instead of re-asking.
                det_ops, det_text = await self._deterministic_edit_ops(board, latest_user_message)
                if det_ops:
                    yield {
                        "event": "edit_proposal",
                        "operations": [op.model_dump() for op in det_ops],
                    }
                    yield {"event": "assistant_message", "text": det_text}
                    return
                if not narrative:
                    web_ans = await self._answer_web_question(latest_user_message, bearer_token)
                    if web_ans:
                        yield {"event": "assistant_message", "text": web_ans}
                        return
                yield {"event": "assistant_message", "text": narrative or _EDIT_HELP_TEXT}
                return

            proposed = None
            for tu in turn.tool_uses:
                logger.info("edit tool_call: %s args=%s", tu.name, _short(tu.arguments))
                yield {"event": "tool_call", "name": tu.name, "arguments": tu.arguments}

                if tu.name == "get_current_plan":
                    try:
                        fresh = await self._plan_client.get_board(bearer_token, plan_id)
                        text = board_summary(fresh)
                    except Exception as exc:  # noqa: BLE001
                        text = f"(lỗi đọc plan: {exc})"
                    messages.append(_tool_result(tu.id, {"board": text}))
                    yield {"event": "tool_result", "name": tu.name, "summary": "đã đọc plan"}
                    continue

                if tu.name == "propose_plan_edits":
                    try:
                        proposed = parse_operations(tu.arguments.get("operations"), board=board)
                    except EditValidationError as exc:
                        messages.append(_tool_result(tu.id, {"error": str(exc)}, is_error=True))
                        yield {"event": "tool_result", "name": tu.name, "summary": f"error: {exc}"}
                        proposed = None
                        continue
                    messages.append(_tool_result(tu.id, {"ok": True, "count": len(proposed)}))
                    yield {
                        "event": "tool_result",
                        "name": tu.name,
                        "summary": f"đề xuất {len(proposed)} thay đổi",
                    }
                    continue

                result = await dispatcher.run(tu.name, tu.arguments)
                logger.info("edit tool_result: %s -> %s", tu.name, _short(result))
                messages.append(_tool_result(tu.id, result))
                yield {"event": "tool_result", "name": tu.name, "summary": _tool_summary(tu.name, result)}

            if proposed is not None:
                narrative = turn.text or _default_edit_narrative(proposed)
                yield {
                    "event": "edit_proposal",
                    "operations": [op.model_dump() for op in proposed],
                }
                yield {"event": "assistant_message", "text": narrative}
                return

        # Exhausted iterations without a proposal — try the deterministic edit too.
        det_ops, det_text = await self._deterministic_edit_ops(board, latest_user_message)
        if det_ops:
            yield {
                "event": "edit_proposal",
                "operations": [op.model_dump() for op in det_ops],
            }
            yield {"event": "assistant_message", "text": det_text}
            return
        if not narrative:
            web_ans = await self._answer_web_question(latest_user_message, bearer_token)
            if web_ans:
                yield {"event": "assistant_message", "text": web_ans}
                return
        yield {"event": "assistant_message", "text": narrative or _EDIT_HELP_TEXT}

    async def _answer_web_question(self, message: str, bearer_token: Optional[str]) -> Optional[str]:
        """Deterministically answer a live-fact question (giá vé, giờ mở cửa, thuê xe…)
        via web_search when the weak LLM won't. Returns a formatted answer or None."""
        if not assistant.is_live_fact_question(message):
            return None
        year = date.today().year
        query = message if str(year) in message else f"{message} {year}"
        try:
            res = await self._dispatcher.with_token(bearer_token).run(
                "web_search", {"query": query, "max_results": 4}
            )
        except Exception as exc:  # noqa: BLE001
            logger.warning("web-question fallback failed: %s", exc)
            return None
        if not res.get("enabled", False):
            return (
                "Mình chưa tra được giá/thông tin hiện tại (web search chưa bật). "
                "Bạn nên kiểm tra trên trang bán vé chính thức để có số mới nhất nhé."
            )
        answer = (res.get("answer") or "").strip()
        results = res.get("results") or []
        if not answer and not results:
            return "Mình chưa tìm thấy thông tin đáng tin cậy cho câu này — bạn thử hỏi lại cụ thể hơn nhé."
        parts: List[str] = []
        if answer:
            parts.append(answer)
        elif results:
            parts.append((results[0].get("content") or "").strip())
        links = [
            f"[{r.get('title') or 'nguồn'}]({r.get('url')})"
            for r in results[:2] if r.get("url")
        ]
        if links:
            parts.append("Nguồn: " + " · ".join(links))
        parts.append("_(Giá có thể thay đổi — nên kiểm tra lại trước khi đi.)_")
        return "\n\n".join(parts)

    async def _deterministic_edit_ops(self, board: Dict[str, Any], message: str):
        """Offline fallback when the LLM won't propose: returns (ops, narrative) for the
        two things users most often ask and the weak model flakes on — re-timing existing
        cards ("chỉnh lại giờ") and filling days ("thêm hoạt động"). ([], None) if neither."""
        # Re-time existing cards (fix the 23:59 mess) — cheapest, most common ask.
        if retime_editor.detect_retime_intent(message):
            ops = retime_editor.build_retime_ops(board, message)
            if ops:
                return ops, _retime_narrative(ops)
        # Fill target/empty days with real, timed activities.
        if fill_editor.detect_fill_intent(message):
            try:
                ops = await fill_editor.build_fill_ops(board, message, self._composer)
            except Exception as exc:  # noqa: BLE001 — never let the fallback crash the turn
                logger.warning("fill fallback failed: %s", exc)
                ops = []
            if ops:
                return ops, _fill_narrative(ops)
        return [], None


# Shown when the model gives nothing usable AND no deterministic fallback matched — a
# helpful menu instead of the old parrot "Bạn muốn mình chỉnh sửa gì?".
_EDIT_HELP_TEXT = (
    "Mình chưa chắc ý bạn. Mình có thể giúp bạn với kế hoạch này:\n"
    "- **Chỉnh giờ** cho hợp lý (vd: “chỉnh lại thời gian ngày 1”).\n"
    "- **Thêm hoạt động** vào ngày trống (vd: “thêm hoạt động vào ngày 2”).\n"
    "- **Làm rẻ hơn** theo ngân sách (vd: “làm rẻ hơn, ngân sách 8 triệu”).\n"
    "- **Đổi/xoá** một hoạt động, hoặc hỏi **giá vé/giờ mở cửa** một địa điểm.\n"
    "Bạn nói cụ thể giúp mình nhé."
)


def _fill_narrative(operations: list) -> str:
    days = sorted({op.day_index for op in operations if getattr(op, "day_index", None)})
    where = ", ".join(f"Ngày {d}" for d in days) if days else "kế hoạch"
    return (
        f"Mình đã soạn {len(operations)} hoạt động cho {where} (có giờ giấc, địa điểm và chi phí). "
        "Bạn bấm **Áp dụng** để thêm vào lịch trình nhé."
    )


def _retime_narrative(operations: list) -> str:
    return (
        f"Mình đã canh lại giờ cho {len(operations)} hoạt động theo lịch hợp lý "
        "(sáng → trưa → nghỉ → chiều → tối), không còn 23:59. "
        "Bạn bấm **Áp dụng** để cập nhật nhé."
    )


def _build_edit_messages(
    board_text: str,
    chat_history: List[ChatMessage],
    latest_user_message: str,
) -> List[Dict[str, Any]]:
    today = date.today()
    primer = (
        "[Context primer — do not echo. This is the CURRENT plan the user is editing.]\n"
        f"Hôm nay là {today.isoformat()} (năm {today.year}). Giá vé / giờ mở cửa / giá thuê "
        f"xe phải tra qua web_search với năm {today.year}, không lấy từ trí nhớ.\n"
        + board_text
    )
    messages: List[Dict[str, Any]] = [
        {"role": "user", "content": primer},
        {
            "role": "assistant",
            "content": "Mình đã xem kế hoạch hiện tại và sẵn sàng chỉnh sửa theo yêu cầu của bạn.",
        },
    ]
    relevant = [
        m
        for m in chat_history
        if m.role in (ChatRole.USER, ChatRole.ASSISTANT)
        and not (m.role == ChatRole.ASSISTANT and _is_canned_fallback(m.content))
    ]
    for m in relevant[-12:]:
        role = "user" if m.role == ChatRole.USER else "assistant"
        messages.append({"role": role, "content": m.content})
    if not messages or messages[-1].get("content") != latest_user_message:
        messages.append({"role": "user", "content": latest_user_message})
    return messages


def _board_budget(board: Dict[str, Any]) -> Optional[int]:
    val = board.get("budgetTotal")
    try:
        return int(val) if val else None
    except (TypeError, ValueError):
        return None


def _board_travelers(board: Dict[str, Any]) -> int:
    """Best-effort headcount from the board's cards (max participantCount seen)."""
    best = 0
    for lst in board.get("lists") or []:
        if not isinstance(lst, dict):
            continue
        for card in lst.get("cards") or []:
            if not isinstance(card, dict):
                continue
            pc = card.get("participantCount")
            try:
                if pc:
                    best = max(best, int(pc))
            except (TypeError, ValueError):
                pass
    return best or 2


def _budget_plan_op(target_vnd: int) -> EditOperation:
    op = EditOperation(op="update_plan", budget_total_vnd=target_vnd)
    op.summary = f"Đặt ngân sách kế hoạch = {target_vnd:,}đ"
    return op


def _budget_cut_narrative(operations: list, target: Optional[int]) -> str:
    n = len([op for op in operations if op.op == "update_card"])
    head = "AI đang tạm gián đoạn, nhưng mình đã tính nhanh một phương án tiết kiệm hơn"
    if target:
        head += f" (mục tiêu ~{target:,}đ)"
    lines = [head + f": giảm chi phí ở {n} hoạt động."]
    lines.append("Bạn xem và bấm **Áp dụng** nếu thấy hợp lý nhé.")
    return "\n".join(lines)


def _default_edit_narrative(operations: list) -> str:
    n = len(operations)
    lines = [f"Mình đề xuất {n} thay đổi cho kế hoạch:"]
    for op in operations[:8]:
        s = getattr(op, "summary", None) or ""
        if s:
            lines.append(f"• {s}")
    lines.append("Bạn bấm **Áp dụng** để cập nhật vào kế hoạch nhé.")
    return "\n".join(lines)


def _build_messages(
    chat_history: List[ChatMessage],
    constraints: Constraints,
    latest_user_message: str,
    revision_hint: Optional[str],
    prior_draft: Optional[PlanDraft],
) -> List[Dict[str, Any]]:
    """Build the OpenAI-format conversation: a context primer + recent chat turns."""

    primer = _format_primer(constraints, prior_draft, revision_hint)
    messages: List[Dict[str, Any]] = [{"role": "user", "content": primer}]
    messages.append(
        {
            "role": "assistant",
            "content": "Sẵn sàng. Mình sẽ trả lời theo ngôn ngữ người dùng và chỉ gọi tool khi cần.",
        }
    )

    # Replay the last ~12 user/assistant turns so the model has conversational memory.
    # Drop the assistant's own canned clarification/fallback lines: replaying them
    # primes the model to parrot "cho mình biết điểm đến, ngày đi…" and loop.
    relevant = [
        m
        for m in chat_history
        if m.role in (ChatRole.USER, ChatRole.ASSISTANT)
        and not (m.role == ChatRole.ASSISTANT and _is_canned_fallback(m.content))
    ]
    for m in relevant[-12:]:
        role = "user" if m.role == ChatRole.USER else "assistant"
        messages.append({"role": role, "content": m.content})

    # Make sure the latest user message is the trailing turn (chat_history may not
    # include it yet depending on caller order).
    if not messages or messages[-1].get("content") != latest_user_message:
        messages.append({"role": "user", "content": latest_user_message})

    return messages


def _format_primer(
    constraints: Constraints,
    prior_draft: Optional[PlanDraft],
    revision_hint: Optional[str],
) -> str:
    today = date.today()
    lines = [
        "[Context primer — do not echo this to the user, treat as internal state.]",
        f"Hôm nay là {today.isoformat()} (năm hiện tại {today.year}). Mọi thông tin nhạy "
        f"cảm theo thời gian (giá vé, giờ mở cửa, giá thuê xe, vé xe/máy bay, 'còn mở cửa "
        f"không') PHẢI tra qua web_search với năm {today.year}, KHÔNG lấy từ trí nhớ.",
        "Known constraints so far:",
        f"- destination: {constraints.destination or 'unknown'}",
        f"- num_days (trip length): {constraints.num_days or 'unknown'}",
        f"- start_date: {constraints.start_date or 'unknown (defaults to today if user gives no date)'}",
        f"- end_date: {constraints.end_date or 'unknown (defaults to today + num_days - 1)'}",
        f"- travelers: {constraints.travelers}",
        f"- budget_total_vnd: {constraints.budget_total_vnd or 'unknown'}",
        f"- interests: {', '.join(constraints.interests) if constraints.interests else 'unknown'}",
        f"- pace: {constraints.pace or 'unknown'}",
        "Timing rule: ask how many DAYS (num_days), not calendar dates. If no date is given, "
        f"anchor the trip to start TODAY ({today.isoformat()}).",
    ]
    if prior_draft:
        lines.append("")
        lines.append(
            f"A prior draft exists: {prior_draft.summary} "
            f"({len(prior_draft.days)} days, ~{prior_draft.estimated_total_cost_vnd:,} VND). "
            "Only call finalize_draft again if the user asked for changes."
        )
    if revision_hint:
        lines.append("")
        lines.append(f"User revision instruction: {revision_hint}")
    return "\n".join(lines)


def _tool_result(tool_use_id: str, payload: Dict[str, Any], is_error: bool = False) -> Dict[str, Any]:
    return {
        "role": "tool",
        "tool_call_id": tool_use_id,
        "content": json.dumps(payload, ensure_ascii=False, default=str),
        **({"is_error": True} if is_error else {}),
    }


def _tool_summary(name: str, result: Dict[str, Any]) -> str:
    if name == "route_legs":
        legs = result.get("legs") or []
        return f"{len(legs)} chặng · {result.get('total_km', 0)}km · {result.get('total_minutes', 0)} phút"
    if name == "web_search":
        if not result.get("enabled", True):
            return "web search off"
        return f"{len(result.get('results') or [])} kết quả web"
    return f"{len(result.get('items') or [])} items"


def _short(args: Dict[str, Any]) -> str:
    try:
        return json.dumps(args, ensure_ascii=False)[:200]
    except (TypeError, ValueError):
        return str(args)[:200]


def _default_narrative(draft: PlanDraft) -> str:
    return (
        f"Mình đã dựng nháp {len(draft.days)} ngày tại {draft.destination} cho "
        f"{draft.travelers} người, chi phí ước tính {draft.estimated_total_cost_vnd:,} VND. "
        "Bạn xem qua và bấm Duyệt nếu ổn nhé."
    )


def _clarification_text(constraints: Constraints) -> str:
    if not constraints.destination:
        return "Bạn muốn đi đâu nhỉ? Mình có thể gợi ý vài điểm theo sở thích nếu bạn chưa chốt."
    has_dates = constraints.start_date is not None and constraints.end_date is not None
    if not constraints.num_days and not has_dates:
        return (
            f"Mình đã ghi nhận điểm đến {constraints.destination}. "
            "Bạn dự định đi mấy ngày? (ví dụ 3-4 ngày) — nếu không nói ngày cụ thể, "
            "mình mặc định tính từ hôm nay nhé."
        )
    return "Bạn cho mình thêm một chút thông tin để mình tìm được lựa chọn phù hợp nhé."


# Canned clarification phrases the agent itself may have emitted. We strip these
# from replayed history so the model doesn't learn to repeat them (see _build_messages).
_CANNED_FALLBACK_MARKERS = (
    "cần thêm thông tin để dựng lịch trình",
    "cho mình biết điểm đến",
    "bạn cho mình thêm một chút thông tin",
    "bạn muốn đi đâu nhỉ",
    "đi từ ngày nào đến ngày nào",
    "bạn dự định đi mấy ngày",
)


def _is_canned_fallback(text: str) -> bool:
    low = (text or "").lower()
    return any(marker in low for marker in _CANNED_FALLBACK_MARKERS)
