package com.mravel.realtime.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.realtime.dto.PlanBoardEventV2;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Phase 5 — consumes plan-board-events-v2 (patch-only) and forwards to
 * WebSocket topic /topic/plans/{planId}/board/v2.
 *
 * v2 events carry only a patch payload (no full board snapshot).
 * Legacy v1 listener continues to run on /topic/plans/{planId}/board
 * for remaining v1 consumers (CLEAR_TRASH, DUPLICATE_*, etc.).
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlanBoardEventV2Listener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;
    private final MeterRegistry meterRegistry;

    @KafkaListener(
            topics = "${mravel.topics.plan-board-v2}",
            groupId = "mravel-realtime-v2")
    public void handlePlanBoardEventV2(String messageJson) {
        try {
            PlanBoardEventV2 event = objectMapper.readValue(messageJson, PlanBoardEventV2.class);

            Long planId = event.getPlanId();
            String destination = "/topic/plans/" + planId + "/board/v2";

            // Measure delivery lag using event timestamp
            if (event.getTimestamp() != null) {
                long lagMs = System.currentTimeMillis() - event.getTimestamp();
                DistributionSummary.builder("realtime.ws_delivery_lag_ms")
                        .tag("event_type", event.getOperationType() != null ? event.getOperationType() : "UNKNOWN")
                        .tag("envelope_version", "2")
                        .register(meterRegistry)
                        .record(lagMs);
            }

            Counter.builder("realtime.board_event.delivered")
                    .tag("event_type", event.getOperationType() != null ? event.getOperationType() : "UNKNOWN")
                    .tag("envelope_version", "2")
                    .register(meterRegistry)
                    .increment();

            log.debug("Received PlanBoardEventV2 planId={} revision={} type={} -> {}",
                    planId, event.getRevision(), event.getOperationType(), destination);

            messagingTemplate.convertAndSend(destination, event);

        } catch (Exception e) {
            log.error("Failed to process PlanBoardEventV2: {}", messageJson, e);
        }
    }
}
