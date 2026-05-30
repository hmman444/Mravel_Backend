"""Test-wide fixtures.

Force a hermetic in-memory session store for the whole test run: tests must not
require a running MongoDB and must not pollute the real Mongo collection or the
legacy `.data/sessions.json` file (that file had grown to ~200 docs precisely because
earlier test runs wrote to it). Each test also starts from a fresh store.
"""

import os

# Set before app.config is imported so pydantic-settings picks it up. Env vars take
# precedence over .env, so this overrides whatever backend/path is configured locally.
os.environ["SESSION_STORE_BACKEND"] = "memory"
os.environ["SESSION_STORE_PATH"] = ""

import pytest  # noqa: E402

from app.config import get_settings  # noqa: E402
from app.services.session_store import reset_session_store_for_tests  # noqa: E402

get_settings.cache_clear()  # drop any settings cached before the env override above


@pytest.fixture(autouse=True)
def _fresh_session_store():
    reset_session_store_for_tests()
    yield
    reset_session_store_for_tests()
