package com.mravel.auth.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.auth.model.OutboxEvent;
import com.mravel.auth.repository.OutboxRepository;
import com.mravel.common.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisher {
    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "user.v1.registered";

    @Scheduled(fixedDelay = 5000)
    public void publishPendingEvents() {
        List<OutboxEvent> pending = outboxRepository.findByStatus("PENDING");

        for (OutboxEvent event : pending) {
            try {
                UserRegisteredEvent message = objectMapper.readValue(event.getPayload(), UserRegisteredEvent.class);
                kafkaTemplate.send(TOPIC, message);
                event.setStatus("SENT");
            } catch (Exception e) {
                log.error("❌ Failed to send event {}: {}", event.getId(), e.getMessage());
                event.setStatus("FAILED");
            } finally {
                event.setUpdatedAt(java.time.Instant.now());
                outboxRepository.save(event);
            }
        }
    }
}
