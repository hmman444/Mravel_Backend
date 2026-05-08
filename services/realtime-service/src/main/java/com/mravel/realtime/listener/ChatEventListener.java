package com.mravel.realtime.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.common.event.ChatMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatEventListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${mravel.topics.chat-events}",
            groupId = "${spring.kafka.consumer.group-id}",
            concurrency = "${mravel.kafka.chat-listener-concurrency:3}"
    )
    public void handle(String messageJson) {
        try {
            ChatMessageEvent event = objectMapper.readValue(messageJson, ChatMessageEvent.class);
            log.debug("[ChatKafka] eventType={} convId={}", event.getEventType(), event.getConversationId());

            String convDest = "/topic/conversations/" + event.getConversationId() + "/events";
            messagingTemplate.convertAndSend(convDest, event);

            // Push conversation-list updates to each recipient's personal topic.
            // This keeps the sidebar in sync for new messages and group membership changes.
            if ("NEW_MESSAGE".equals(event.getEventType())
                    || "GROUP_UPDATED".equals(event.getEventType())
                    || "MEMBER_ADDED".equals(event.getEventType())
                    || "MEMBER_REMOVED".equals(event.getEventType())
                    || "MEMBER_LEFT".equals(event.getEventType())
                    || "ROLE_CHANGED".equals(event.getEventType())) {
                List<Long> recipients = event.getRecipientIds();
                if (recipients != null) {
                    for (Long recipientId : recipients) {
                        String userDest = "/topic/users/" + recipientId + "/conversations";
                        messagingTemplate.convertAndSend(userDest, event);
                    }
                }
            }
        } catch (Exception e) {
            log.error("[ChatEventListener] parse fail: {}", messageJson, e);
        }
    }
}
