package com.mravel.plan.kafka;

public final class KafkaTopics {
    private KafkaTopics() {
    }

    public static final String PLAN_SHARED = "plan.v1.shared";

    public static final String PLAN_BOARD_EVENTS = "plan-board-events";

    public static final String PLAN_BOARD_EVENTS_V2 = "plan-board-events-v2";

    public static final String PLAN_INDEX_EVENTS = "plan.v1.index-events";
}
