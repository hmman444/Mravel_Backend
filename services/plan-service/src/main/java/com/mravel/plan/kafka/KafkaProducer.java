package com.mravel.plan.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.plan.model.PlanRevisionLog;
import com.mravel.plan.repository.PlanRevisionLogRepository;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MeterRegistry meterRegistry;
    private final ObjectMapper objectMapper;
    private final PlanRevisionLogRepository revisionLogRepository;

    /**
     * Phase 2c — publishes to v2 topic (revisioned envelope) AND persists to
     * plan_revision_log for gap recovery.  Also still produces to v1 topic so
     * legacy clients are unaffected during Phase 2–4 transition.
     */
    public void publishBoardEventV2(PlanBoardEventV2 event) {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            String json = objectMapper.writeValueAsString(event);

            // Measure v2 payload size (should be same as v1 in Phase 2; shrinks in Phase 5)
            DistributionSummary.builder("plan.board_event.payload_bytes")
                    .tag("operation_type", event.getOperationType() != null ? event.getOperationType() : "UNKNOWN")
                    .tag("envelope_version", "2")
                    .register(meterRegistry)
                    .record(json.length());

            if (json.length() > 10_240) {
                log.warn("oversized_event planId={} size={}", event.getPlanId(), json.length());
            }

            // Persist to revision log for gap recovery (TTL 1h, purged by job)
            revisionLogRepository.save(PlanRevisionLog.builder()
                    .planId(event.getPlanId())
                    .revision(event.getRevision())
                    .eventEnvelope(json)
                    .createdAt(Instant.now())
                    .build());

            // Produce to v2 topic
            kafkaTemplate.send(
                    KafkaTopics.PLAN_BOARD_EVENTS_V2,
                    String.valueOf(event.getPlanId()),
                    event);

            log.debug("command_published planId={} revision={} operationType={}",
                    event.getPlanId(), event.getRevision(), event.getOperationType());

        } catch (JsonProcessingException e) {
            log.error("Failed to serialize PlanBoardEventV2 for planId={}", event.getPlanId(), e);
        } finally {
            sample.stop(Timer.builder("plan.board_event.publish_latency_ms")
                    .tag("operation_type", event.getOperationType() != null ? event.getOperationType() : "UNKNOWN")
                    .tag("envelope_version", "2")
                    .register(meterRegistry));
        }
    }

    public void publishShare(Long planId, String email, String inviterUserId) {
        var evt = ShareEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .planId(planId)
                .inviteEmail(email)
                .inviterUserId(inviterUserId)
                .createdAt(Instant.now())
                .build();
        kafkaTemplate.send(KafkaTopics.PLAN_SHARED, evt.getEventId(), evt);
    }

    public void publishBoardEvent(PlanBoardEvent event) {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            // Measure serialized payload size for baseline metrics (Phase 0)
            try {
                byte[] bytes = objectMapper.writeValueAsBytes(event);
                DistributionSummary.builder("plan.board_event.payload_bytes")
                        .tag("operation_type", event.getEventType() != null ? event.getEventType() : "UNKNOWN")
                        .tag("envelope_version", "1")
                        .register(meterRegistry)
                        .record(bytes.length);
                if (bytes.length > 10_240) {
                    log.warn("oversized_event planId={} size={}", event.getPlanId(), bytes.length);
                }
            } catch (JsonProcessingException e) {
                log.debug("Could not measure event size: {}", e.getMessage());
            }

            kafkaTemplate.send(
                    KafkaTopics.PLAN_BOARD_EVENTS,
                    String.valueOf(event.getPlanId()),
                    event);
        } finally {
            sample.stop(Timer.builder("plan.board_event.publish_latency_ms")
                    .tag("operation_type", event.getEventType() != null ? event.getEventType() : "UNKNOWN")
                    .register(meterRegistry));
        }
    }
}
