package com.mravel.realtime.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.realtime.dto.PlanBoardEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanBoardEventListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Value("${mravel.topics.plan-board}")
    private String planBoardTopic;

    @KafkaListener(topics = "${mravel.topics.plan-board}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePlanBoardEvent(String messageJson) {
        try {
            PlanBoardEvent event = objectMapper.readValue(messageJson, PlanBoardEvent.class);

            Long planId = event.getPlanId();
            String destination = "/topic/plans/" + planId + "/board";

            log.debug(
                    "Received PlanBoardEvent for plan {} type {} - forwarding to {}",
                    planId, event.getEventType(), destination);

            messagingTemplate.convertAndSend(destination, event);

        } catch (Exception e) {
            log.error("Failed to process PlanBoardEvent: {}", messageJson, e);
        }
    }
}
