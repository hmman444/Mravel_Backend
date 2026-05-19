package com.mravel.plan.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Publishes PlanIndexEvent messages to the Elasticsearch sync topic.
 * Injected into any service that mutates Plan data.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlanIndexPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUpsert(Long planId) {
        publish(planId, PlanIndexEvent.Action.UPSERT);
    }

    public void publishDelete(Long planId) {
        publish(planId, PlanIndexEvent.Action.DELETE);
    }

    private void publish(Long planId, PlanIndexEvent.Action action) {
        PlanIndexEvent event = PlanIndexEvent.builder()
                .planId(planId)
                .action(action)
                .build();

        Runnable send = () -> {
            kafkaTemplate.send(KafkaTopics.PLAN_INDEX_EVENTS, String.valueOf(planId), event);
            log.debug("Published plan index event: planId={}, action={}", planId, action);
        };

        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    send.run();
                }
            });
            return;
        }

        send.run();
    }
}
