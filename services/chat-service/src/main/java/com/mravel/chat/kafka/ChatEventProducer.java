package com.mravel.chat.kafka;

import com.mravel.common.event.ChatMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${mravel.topics.chat-events}")
    private String topic;

    public void publish(ChatMessageEvent event) {
        String key = String.valueOf(event.getConversationId());
        log.debug("[ChatKafka] publish eventType={} convId={}", event.getEventType(), event.getConversationId());
        kafkaTemplate.send(topic, key, event).whenComplete((res, ex) -> {
            if (ex != null) {
                log.error("[ChatKafka] publish failed topic={} key={} eventType={}: {}",
                        topic, key, event.getEventType(), ex.getMessage(), ex);
            }
        });
    }
}
