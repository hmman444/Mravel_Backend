package com.mravel.notification.kafka;

import com.mravel.notification.dto.NotificationDtos.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${mravel.topics.notification-events}")
    private String topic;

    public void publish(NotificationEvent event) {
        String key = String.valueOf(event.getRecipientId());
        kafkaTemplate.send(topic, key, event);
    }
}
