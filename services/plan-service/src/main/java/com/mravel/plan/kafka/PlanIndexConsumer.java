package com.mravel.plan.kafka;

import com.mravel.plan.document.PlanDocument;
import com.mravel.plan.model.Destination;
import com.mravel.plan.model.Plan;
import com.mravel.plan.repository.PlanEsRepository;
import com.mravel.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Listens on plan.v1.index-events and keeps the Elasticsearch index in sync
 * with the MySQL source of truth.
 *
 * Strategy — fetch-then-index:
 * UPSERT → load plan from MySQL → build PlanDocument → save to ES
 * DELETE → delete from ES by id
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlanIndexConsumer {

    private final PlanRepository planRepository;
    private final PlanEsRepository planEsRepository;

    @KafkaListener(topics = KafkaTopics.PLAN_INDEX_EVENTS, groupId = "plan-service.es-indexer", containerFactory = "kafkaListenerContainerFactory")
    public void handleIndexEvent(PlanIndexEvent event) {
        if (event == null || event.getPlanId() == null)
            return;

        try {
            if (event.getAction() == PlanIndexEvent.Action.DELETE) {
                planEsRepository.deleteById(String.valueOf(event.getPlanId()));
                log.info("ES: deleted plan {}", event.getPlanId());
                return;
            }

            // UPSERT — fetch fresh state from MySQL
            Optional<Plan> opt = planRepository.findById(event.getPlanId());
            if (opt.isEmpty()) {
                // Plan was deleted between event publish and processing
                planEsRepository.deleteById(String.valueOf(event.getPlanId()));
                return;
            }

            PlanDocument doc = toDocument(opt.get());
            planEsRepository.save(doc);
            log.info("ES: indexed plan {}", event.getPlanId());

        } catch (Exception e) {
            log.error("ES index failed for planId={}: {}", event.getPlanId(), e.getMessage(), e);
            // Do NOT rethrow — let Kafka commit the offset; a full re-index
            // can be triggered via the admin re-index endpoint if needed.
        }
    }

    // ─ mapping ─

    private PlanDocument toDocument(Plan plan) {
        List<String> destNames = plan.getDestinations() == null
                ? Collections.emptyList()
                : plan.getDestinations().stream()
                        .map(Destination::getName)
                        .filter(n -> n != null && !n.isBlank())
                        .toList();

        return PlanDocument.builder()
                .id(String.valueOf(plan.getId()))
                .title(plan.getTitle())
                .titleRaw(plan.getTitle())
                .description(plan.getDescription())
                .descriptionRaw(plan.getDescription())
                .visibility(plan.getVisibility() != null ? plan.getVisibility().name() : "PRIVATE")
                .authorId(plan.getAuthorId())
                .views(plan.getViews() != null ? plan.getViews() : 0L)
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .days(plan.getDays())
                .budgetTotal(plan.getBudgetTotal())
                .budgetCurrency(plan.getBudgetCurrency())
                .createdAt(plan.getCreatedAt())
                .destinationNames(destNames)
                .thumbnail(plan.getThumbnail())
                .build();
    }
}
