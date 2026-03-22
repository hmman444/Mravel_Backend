package com.mravel.plan.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mravel.plan.kafka.KafkaTopics;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic planSharedTopic() {
        return new NewTopic("plan.v1.shared", 1, (short) 1);
    }

    @Bean
    public NewTopic planBoardEventsTopic() {
        return new NewTopic(KafkaTopics.PLAN_BOARD_EVENTS, 1, (short) 1);
    }

    /** Phase 2c — v2 topic with same partition count; planId is the message key. */
    @Bean
    public NewTopic planBoardEventsV2Topic() {
        return new NewTopic(KafkaTopics.PLAN_BOARD_EVENTS_V2, 1, (short) 1);
    }
}
