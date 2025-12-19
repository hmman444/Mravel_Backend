package com.mravel.realtime.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.common.event.NotificationRealtimeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${mravel.topics.notification-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void handle(String messageJson) {
        try {
            log.info("[NotiKafka] raw={}", messageJson);
            NotificationRealtimeEvent event = objectMapper.readValue(messageJson, NotificationRealtimeEvent.class);

            Long recipientId = event.getRecipientId();
            String destination = "/topic/users/" + recipientId + "/notifications";

            messagingTemplate.convertAndSend(destination, event);
        } catch (Exception e) {
            log.error("[NotificationEventListener] parse fail: {}", messageJson, e);
        }
    }
}
