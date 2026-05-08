package com.mravel.chat.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic chatEventsTopic() {
        // 8 partitions allows parallel consumption by up to 8 consumer threads.
        // NOTE: existing single-partition deployments require manual kafka-topics.sh --alter
        // to take effect. New deployments get 8 partitions automatically.
        return new NewTopic("chat-events", 8, (short) 1);
    }
}
