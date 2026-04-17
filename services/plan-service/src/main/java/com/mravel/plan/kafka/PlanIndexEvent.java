package com.mravel.plan.kafka;

import lombok.*;

/**
 * Lightweight Kafka event published whenever a Plan is created, updated, or deleted.
 * The consumer fetches the fresh Plan from MySQL and indexes it into Elasticsearch.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanIndexEvent {

    public enum Action { UPSERT, DELETE }

    private Long planId;
    private Action action;
}
