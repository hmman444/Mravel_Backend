package com.mravel.realtime.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.realtime.dto.PlanBoardEvent;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanBoardEventListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;
    private final MeterRegistry meterRegistry;

    @KafkaListener(topics = "${mravel.topics.plan-board}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePlanBoardEvent(String messageJson) {
        try {
            PlanBoardEvent event = objectMapper.readValue(messageJson, PlanBoardEvent.class);

            Long planId = event.getPlanId();
            String destination = "/topic/plans/" + planId + "/board";
            String destinationV2 = "/topic/plans/" + planId + "/board/v2";

            // Phase 0 — measure Kafka-produce → WS-send lag using event timestamp
            if (event.getTimestamp() != null) {
                long lagMs = System.currentTimeMillis() - event.getTimestamp();
                DistributionSummary.builder("realtime.ws_delivery_lag_ms")
                        .tag("event_type", event.getEventType() != null ? event.getEventType() : "UNKNOWN")
                        .register(meterRegistry)
                        .record(lagMs);
            }

            Counter.builder("realtime.board_event.delivered")
                    .tag("event_type", event.getEventType() != null ? event.getEventType() : "UNKNOWN")
                    .register(meterRegistry)
                    .increment();

            log.debug("Received PlanBoardEvent for plan {} type {} - forwarding to {}",
                    planId, event.getEventType(), destination);

            messagingTemplate.convertAndSend(destination, event);

            // Bridge v1 legacy events to lightweight v2 SYNC envelope so v2-only
            // clients still refresh for operations that don't emit patch events.
            Long revision = extractBoardRevision(event.getBoard());
            Map<String, Object> patch = new HashMap<>();
            patch.put("eventType", event.getEventType());

            Map<String, Object> syncEvent = new HashMap<>();
            syncEvent.put("envelopeVersion", "2");
            syncEvent.put("planId", planId);
            syncEvent.put("entityType", "BOARD");
            syncEvent.put("entityId", planId);
            syncEvent.put("operationType", "SYNC");
            syncEvent.put("patch", patch);
            syncEvent.put("actorId", event.getActorId());
            syncEvent.put("operationId", UUID.randomUUID().toString());
            syncEvent.put("revision", revision);
            syncEvent.put("occurredAt", Instant.now().toString());
            syncEvent.put("timestamp", System.currentTimeMillis());
            messagingTemplate.convertAndSend(destinationV2, syncEvent);

        } catch (Exception e) {
            log.error("Failed to process PlanBoardEvent: {}", messageJson, e);
        }
    }

    @SuppressWarnings("unchecked")
    private Long extractBoardRevision(Object boardObj) {
        if (boardObj == null) {
            return null;
        }
        try {
            Map<String, Object> boardMap = objectMapper.convertValue(boardObj, Map.class);
            Object revisionValue = boardMap.get("boardRevision");
            if (revisionValue instanceof Number num) {
                return num.longValue();
            }
            if (revisionValue instanceof String str && !str.isBlank()) {
                return Long.parseLong(str);
            }
        } catch (Exception ignored) {
            // Best-effort extraction only.
        }
        return null;
    }
}
