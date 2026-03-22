package com.mravel.plan.kafka;

public final class KafkaTopics {
    private KafkaTopics() {
    }

    public static final String PLAN_SHARED = "plan.v1.shared";

    /** v1 — full BoardResponse snapshot (legacy, deprecated in Phase 5) */
    public static final String PLAN_BOARD_EVENTS = "plan-board-events";

    /** v2 — revisioned envelope with patch payload (Phase 2+) */
    public static final String PLAN_BOARD_EVENTS_V2 = "plan-board-events-v2";
}
