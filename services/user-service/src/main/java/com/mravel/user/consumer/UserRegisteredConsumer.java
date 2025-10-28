package com.mravel.user.consumer;

import com.mravel.common.event.UserRegisteredEvent;
import com.mravel.user.model.UserProfile;
import com.mravel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegisteredConsumer {
    private final UserRepository userRepository;

    @KafkaListener(topics = "user.v1.registered", groupId = "user-service.v1.consumer")
    public void consume(UserRegisteredEvent event, Acknowledgment ack) {
        try {
            log.info("📩 Received UserRegisteredEvent: {}", event.getEmail());

            if (userRepository.existsByEmail(event.getEmail())) {
                ack.acknowledge();
                return;
            }

            userRepository.save(UserProfile.builder()
                    .id(event.getId())
                    .email(event.getEmail())
                    .fullname(event.getFullname())
                    .avatar(event.getAvatar())
                    .provider(event.getProvider())
                    .build());

            ack.acknowledge(); 
        } catch (DataAccessException ex) {
            log.error("❌ Database error, will retry later: {}", ex.getMessage());
            throw ex; // Kafka sẽ retry theo policy
        } catch (Exception e) {
            log.error("🚨 Unexpected error: {}", e.getMessage(), e);
            ack.acknowledge(); // tránh infinite loop
        }
    }
}
