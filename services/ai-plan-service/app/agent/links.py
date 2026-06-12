"""Make the model's catalog deep-links clickable.

The agent is instructed to emit Mravel links as Markdown — ``[Tên](/hotels/slug)`` — but
the small default model (``gpt-oss-120b:free``) frequently degrades them into a bare
bracketed path, e.g. ``Tên — 3★ [/hotels/slug]``. Markdown renders that as inert text, so
the user sees a raw ``/hotels/...`` instead of a link they can click.

This module rewrites those malformations into proper, clickable Markdown links
deterministically — a safety net that runs regardless of how well the model behaved. It
is idempotent: already-correct ``[text](url)`` links and external ``https://…`` URLs are
left untouched.
"""

from __future__ import annotations

import re

# Catalog detail routes the frontend understands (mirror of catalog_web_url in tools.py).
# NOTE: keep the plural "places" before the singular "place" in the alternation.
_CAT_KINDS = r"hotels|restaurants|places|place|plans"
_PATH_IN_BRACKET = rf"/(?:{_CAT_KINDS})/[^\]\s]+"
_PATH_BARE = rf"/(?:{_CAT_KINDS})/[A-Za-z0-9][A-Za-z0-9\-_/]*"

# "Name – details [/hotels/slug]" → link the name, keep the details after it.
# Separator is an en/em dash or a space-padded hyphen (so hyphenated names survive).
_LISTING_RE = re.compile(
    r"^(?P<bullet>\s*(?:[-*•]|\d+[.)])\s+)?"
    r"(?P<name>[^–—\[\]\n]{1,80}?)"
    r"(?P<sep>\s*[–—]\s*|\s+-\s+)"
    r"(?P<details>.*?)"
    rf"\s*\[\s*(?P<path>{_PATH_IN_BRACKET})\s*\]\s*$"
)
# "Name [/hotels/slug]" — no separator, path at the end of the line.
_NAME_PATH_RE = re.compile(
    r"^(?P<bullet>\s*(?:[-*•]|\d+[.)])\s+)?"
    r"(?P<name>[^\[\]\n]{1,80}?)"
    rf"\s*\[\s*(?P<path>{_PATH_IN_BRACKET})\s*\]\s*$"
)
# Any leftover bracketed bare path that is NOT already a Markdown link ("…](" follows).
_BRACKET_PATH_RE = re.compile(rf"\[\s*(?P<path>{_PATH_IN_BRACKET})\s*\](?!\()")
# A bare path not already inside a link / bracket / the middle of another path.
_BARE_PATH_RE = re.compile(rf"(?<![(\[\w/–—-])(?P<path>{_PATH_BARE})")


def _humanize_slug(path: str) -> str:
    """Last path segment → a readable label, e.g. /hotels/hoa-thu → "Hoa Thu"."""
    seg = path.rstrip("/").rsplit("/", 1)[-1]
    seg = re.split(r"[?#]", seg, maxsplit=1)[0]
    words = [w for w in re.split(r"[-_]+", seg) if w]
    return " ".join(w[:1].upper() + w[1:] for w in words) or seg


def _clean_name(name: str) -> str:
    name = (name or "").strip().strip("*").strip()
    return name.rstrip(":").strip()


def _sweep(line: str) -> str:
    """Make any remaining bare catalog paths clickable, using a humanized label."""
    line = _BRACKET_PATH_RE.sub(
        lambda m: f"[{_humanize_slug(m.group('path'))}]({m.group('path')})", line
    )
    line = _BARE_PATH_RE.sub(
        lambda m: f"[{_humanize_slug(m.group('path'))}]({m.group('path')})", line
    )
    return line


def _linkify_line(line: str) -> str:
    if "/" not in line:
        return line

    m = _LISTING_RE.match(line)
    if m:
        name = _clean_name(m.group("name"))
        if name and len(name) <= 80:
            bullet = m.group("bullet") or ""
            sep = m.group("sep").strip() or "—"
            details = (m.group("details") or "").strip()
            tail = f" {sep} {details}" if details else ""
            return _sweep(f"{bullet}[{name}]({m.group('path')}){tail}")

    m = _NAME_PATH_RE.match(line)
    if m:
        name = _clean_name(m.group("name"))
        if name and len(name) <= 80:
            bullet = m.group("bullet") or ""
            return _sweep(f"{bullet}[{name}]({m.group('path')})")

    return _sweep(line)


def linkify_catalog_links(text: str) -> str:
    """Rewrite malformed catalog links in ``text`` into clickable Markdown links.

    Idempotent; correct ``[text](url)`` links and non-catalog URLs are left untouched."""
    if not text or "/" not in text:
        return text
    return "\n".join(_linkify_line(ln) for ln in text.split("\n"))
