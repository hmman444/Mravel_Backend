package com.mravel.plan.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

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
}
