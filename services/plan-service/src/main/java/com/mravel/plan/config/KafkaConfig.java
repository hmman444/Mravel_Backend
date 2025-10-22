package com.mravel.plan.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic planSharedTopic() {
        return new NewTopic("plan.v1.shared", 1, (short)1);
    }
}
