"""Tests for the deterministic catalog-link fixer (app/agent/links.py).

The small default model often degrades Markdown links into bare bracketed paths like
``Tên — 3★ [/hotels/slug]`` (inert text the user can't click). ``linkify_catalog_links``
rewrites those into proper ``[Name](/path)`` links. These tests lock that in and ensure
already-correct links / external URLs are left alone (idempotency)."""

from app.agent.links import linkify_catalog_links


def test_listing_line_moves_link_to_the_name():
    src = "Bespoke Villa Hoi An – 3★, ~380 k đêm, cách trung tâm 1.2 km. [/hotels/bespoke-villa-hoi-an]"
    out = linkify_catalog_links(src)
    assert out == (
        "[Bespoke Villa Hoi An](/hotels/bespoke-villa-hoi-an)"
        " – 3★, ~380 k đêm, cách trung tâm 1.2 km."
    )


def test_full_listing_block():
    src = "\n".join(
        [
            "Bespoke Villa Hoi An – 3★, ~380 k đêm. [/hotels/bespoke-villa-hoi-an]",
            "Wyndham Hoi An Royal Beachfront Resort & Villas – 5★, bãi biển riêng. [/hotels/wyndham-hoi-an-royal-beachfront-resort-villas]",
            "Hoa Thu Homestay – 2★, phòng ấm cúng. [/hotels/hoa-thu-homestay]",
        ]
    )
    out = linkify_catalog_links(src).split("\n")
    assert out[0].startswith("[Bespoke Villa Hoi An](/hotels/bespoke-villa-hoi-an) – ")
    assert out[1].startswith(
        "[Wyndham Hoi An Royal Beachfront Resort & Villas]"
        "(/hotels/wyndham-hoi-an-royal-beachfront-resort-villas) – "
    )
    assert out[2].startswith("[Hoa Thu Homestay](/hotels/hoa-thu-homestay) – ")
    # No bare "/hotels/..." path left as inert text anywhere.
    assert "[/hotels/" not in out[0] + out[1] + out[2]


def test_bulleted_listing_keeps_bullet():
    src = "- Hoa Thu Homestay – 2★ [/hotels/hoa-thu-homestay]"
    assert linkify_catalog_links(src) == "- [Hoa Thu Homestay](/hotels/hoa-thu-homestay) – 2★"


def test_name_then_bracket_without_separator():
    src = "Bãi biển Mỹ Khê [/place/bai-bien-my-khe]"
    assert linkify_catalog_links(src) == "[Bãi biển Mỹ Khê](/place/bai-bien-my-khe)"


def test_already_correct_link_is_untouched():
    src = "- [Tên khách sạn](/hotels/abc-resort) — 4★, ~1.2tr/đêm, Mỹ Khê"
    assert linkify_catalog_links(src) == src


def test_idempotent():
    src = "Hoa Thu Homestay – 2★ [/hotels/hoa-thu-homestay]"
    once = linkify_catalog_links(src)
    assert linkify_catalog_links(once) == once


def test_inline_bracketed_path_becomes_clickable_with_humanized_label():
    # Path is mid-sentence (not a listing) → humanize the slug as the link text.
    src = "Bạn xem thử [/hotels/hoa-thu-homestay] nhé"
    assert linkify_catalog_links(src) == "Bạn xem thử [Hoa Thu Homestay](/hotels/hoa-thu-homestay) nhé"


def test_plan_link_is_handled_too():
    src = "Lịch trình 4 ngày tại Đà Nẵng [/plans/123]"
    assert linkify_catalog_links(src) == "[Lịch trình 4 ngày tại Đà Nẵng](/plans/123)"


def test_external_url_left_alone():
    src = "(nguồn: [Vé Bà Nà](https://example.com/ve-ba-na))"
    assert linkify_catalog_links(src) == src


def test_bare_path_in_sentence_is_linked():
    src = "Tham khảo /restaurants/be-man để đặt bàn."
    assert linkify_catalog_links(src) == "Tham khảo [Be Man](/restaurants/be-man) để đặt bàn."


def test_plain_text_without_paths_unchanged():
    src = "Chào bạn, mình có thể giúp gì cho chuyến đi Hội An?"
    assert linkify_catalog_links(src) == src
