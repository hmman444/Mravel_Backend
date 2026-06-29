package com.mravel.chat.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic chatEventsTopic() {
        // 8 partition cho tiêu thụ song song.
        return new NewTopic("chat-events", 8, (short) 1);
    }
}
