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
}
