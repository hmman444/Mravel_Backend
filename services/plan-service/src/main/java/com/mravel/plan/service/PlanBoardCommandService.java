package com.mravel.plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.plan.dto.board.*;
import com.mravel.plan.dto.command.*;
import com.mravel.plan.exception.ConflictException;
import com.mravel.plan.exception.ForbiddenException;
import com.mravel.plan.kafka.KafkaProducer;
import com.mravel.plan.kafka.PlanBoardEventV2;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

/**
 * Phase 3c — granular command service.
 *
 * Each method:
 * 1. Checks permissions via PlanPermissionService
 * 2. Performs the mutation
 * 3. Increments boardRevision in the same transaction
 * 4. Builds a patch-only CommandResponse
 * 5. Publishes a v2 Kafka event with patch payload (no full board snapshot)
 *
 * The v1 publishBoard() path is NOT called from here — these commands produce
 * patch-only v2 events. v1 consumers will receive nothing from these endpoints
 * until Phase 5 cleanup (that is acceptable: legacy clients fall back to
 * periodic
 * full reloads during the transition window).
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanBoardCommandService {

        private final PlanPermissionService permissionService;
        private final PlanBoardService boardService; // reuses existing helpers
        private final PlanRepository planRepository;
        private final PlanListRepository listRepository;
        private final PlanCardRepository cardRepository;
        private final PlanMemberRepository memberRepository;
        private final KafkaProducer kafkaProducer;
        private final ObjectMapper objectMapper;

        // ── List commands ─────────────────────────────────────────────────────────

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse createList(Long planId, Long userId,
                        CreateListRequest req, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                ListDto created = boardService.createList(planId, userId, req);
                // boardRevision and event publishing are already handled inside
                // boardService.createList.
                long revision = getRevision(planId);

                Map<String, Object> patch = Map.of(
                                "listId", created.getId(),
                                "title", created.getTitle(),
                                "position", created.getPosition(),
                                "type", created.getType(),
                                "dayDate", created.getDayDate() != null ? created.getDayDate().toString() : "");

                CommandResponse response = CommandResponse.builder()
                                .entityType("LIST")
                                .entityId(created.getId())
                                .operationType("CREATE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                // boardService.createList already emits v2 patch + persists revision log.
                // Publishing again here would duplicate (planId, revision) in plan_revision_log
                // and can cause transaction rollback (HTTP 500).
                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse renameList(Long planId, Long listId, Long userId,
                        RenameListRequest req, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                boardService.renameList(planId, listId, userId, req);
                long revision = getRevision(planId);

                Map<String, Object> patch = Map.of("title", req.getTitle());

                CommandResponse response = CommandResponse.builder()
                                .entityType("LIST")
                                .entityId(listId)
                                .operationType("UPDATE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse deleteList(Long planId, Long listId, Long userId, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                boardService.deleteList(planId, userId, listId);
                long revision = getRevision(planId);

                Map<String, Object> patch = Map.of("listId", listId);

                CommandResponse response = CommandResponse.builder()
                                .entityType("LIST")
                                .entityId(listId)
                                .operationType("DELETE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse reorderLists(Long planId, Long userId,
                        List<ListPositionItem> positions, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                // Apply new positions to each list
                for (ListPositionItem item : positions) {
                        PlanList list = listRepository.findById(item.getListId())
                                        .orElseThrow(() -> new RuntimeException("List not found: " + item.getListId()));
                        if (!list.getPlan().getId().equals(planId)) {
                                throw new ForbiddenException(
                                                "List " + item.getListId() + " does not belong to plan " + planId);
                        }
                        list.setPosition(item.getPosition());
                        listRepository.save(list);
                }

                long revision = boardService.incrementBoardRevision(planId);

                // Build patch: array of {listId, position} pairs
                List<Map<String, Object>> positionPatch = positions.stream()
                                .map(p -> {
                                        Map<String, Object> m = new HashMap<>();
                                        m.put("listId", p.getListId());
                                        m.put("position", p.getPosition());
                                        return m;
                                })
                                .toList();
                Map<String, Object> patch = Map.of("positions", positionPatch);

                CommandResponse response = CommandResponse.builder()
                                .entityType("LIST")
                                .entityId(null)
                                .operationType("REORDER")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                publishPatchEvent(planId, userId, "LIST", null, "REORDER", patch, revision, operationId);
                return response;
        }

        // ── Card commands ─────────────────────────────────────────────────────────

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse createCard(Long planId, Long listId, Long userId,
                        CreateCardRequest req, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                CardDto created = boardService.createCard(planId, listId, userId, req);
                long revision = getRevision(planId);

                Map<String, Object> patch = cardToMap(created);
                patch.put("listId", listId);

                CommandResponse response = CommandResponse.builder()
                                .entityType("CARD")
                                .entityId(created.getId())
                                .operationType("CREATE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse updateCard(Long planId, Long listId, Long cardId, Long userId,
                        UpdateCardRequest req, Long clientVersion, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                // Optimistic lock check
                if (clientVersion != null) {
                        PlanCard card = cardRepository.findById(cardId)
                                        .orElseThrow(() -> new RuntimeException("Card not found: " + cardId));
                        if (!clientVersion.equals(card.getVersion())) {
                                throw new ConflictException(card.getVersion(), clientVersion);
                        }
                }

                CardDto updated = boardService.updateCard(planId, listId, cardId, userId, req);
                long revision = getRevision(planId);

                Map<String, Object> patch = cardToMap(updated);

                CommandResponse response = CommandResponse.builder()
                                .entityType("CARD")
                                .entityId(cardId)
                                .operationType("UPDATE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse deleteCard(Long planId, Long listId, Long cardId,
                        Long userId, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                boardService.deleteCard(planId, listId, cardId, userId);
                long revision = getRevision(planId);

                Map<String, Object> patch = Map.of("cardId", cardId, "listId", listId);

                CommandResponse response = CommandResponse.builder()
                                .entityType("CARD")
                                .entityId(cardId)
                                .operationType("DELETE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse toggleDone(Long planId, Long listId, Long cardId,
                        Long userId, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                PlanCard card = cardRepository.findById(cardId)
                                .orElseThrow(() -> new RuntimeException("Card not found: " + cardId));
                card.setDone(!card.isDone());
                cardRepository.save(card);

                long revision = boardService.incrementBoardRevision(planId);

                Map<String, Object> patch = Map.of("cardId", cardId, "done", card.isDone());

                CommandResponse response = CommandResponse.builder()
                                .entityType("CARD")
                                .entityId(cardId)
                                .operationType("UPDATE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                publishPatchEvent(planId, userId, "CARD", cardId, "UPDATE", patch, revision, operationId);
                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse moveCard(Long planId, Long cardId, Long userId,
                        MoveCardRequest req, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                PlanCard card = cardRepository.findById(cardId)
                                .orElseThrow(() -> new RuntimeException("Card not found: " + cardId));

                // Optimistic lock check
                if (req.getVersion() != null && !req.getVersion().equals(card.getVersion())) {
                        throw new ConflictException(card.getVersion(), req.getVersion());
                }

                PlanList targetList = listRepository.findById(req.getTargetListId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Target list not found: " + req.getTargetListId()));
                if (!targetList.getPlan().getId().equals(planId)) {
                        throw new ForbiddenException("Target list does not belong to this plan");
                }

                Long sourceListId = card.getList().getId();
                card.setList(targetList);
                card.setPosition(req.getTargetPosition() != null ? req.getTargetPosition() : 0);
                cardRepository.save(card);

                long revision = boardService.incrementBoardRevision(planId);

                Map<String, Object> patch = Map.of(
                                "cardId", cardId,
                                "sourceListId", sourceListId,
                                "targetListId", req.getTargetListId(),
                                "targetPosition", card.getPosition());

                CommandResponse response = CommandResponse.builder()
                                .entityType("CARD")
                                .entityId(cardId)
                                .operationType("MOVE")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                publishPatchEvent(planId, userId, "CARD", cardId, "MOVE", patch, revision, operationId);
                return response;
        }

        @Transactional
        @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
        public CommandResponse reorderCardsInList(Long planId, Long listId, Long userId,
                        List<CardPositionItem> positions, String operationId) {
                permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

                for (CardPositionItem item : positions) {
                        PlanCard card = cardRepository.findById(item.getCardId())
                                        .orElseThrow(() -> new RuntimeException("Card not found: " + item.getCardId()));
                        if (!card.getList().getId().equals(listId)) {
                                throw new ForbiddenException("Card " + item.getCardId() + " is not in list " + listId);
                        }
                        card.setPosition(item.getPosition());
                        cardRepository.save(card);
                }

                long revision = boardService.incrementBoardRevision(planId);

                List<Map<String, Object>> posPatch = positions.stream()
                                .map(p -> {
                                        Map<String, Object> m = new HashMap<>();
                                        m.put("cardId", p.getCardId());
                                        m.put("position", p.getPosition());
                                        return m;
                                })
                                .toList();
                Map<String, Object> patch = Map.of("listId", listId, "positions", posPatch);

                CommandResponse response = CommandResponse.builder()
                                .entityType("CARD")
                                .entityId(null)
                                .operationType("REORDER")
                                .patch(patch)
                                .operationId(operationId)
                                .revision(revision)
                                .occurredAt(Instant.now().toString())
                                .build();

                publishPatchEvent(planId, userId, "CARD", null, "REORDER", patch, revision, operationId);
                return response;
        }

        // ── helpers ───────────────────────────────────────────────────────────────

        private long getRevision(Long planId) {
                return planRepository.findById(planId)
                                .map(Plan::getBoardRevision)
                                .orElse(0L);
        }

        /**
         * Phase 5 — publishes a patch-only v2 event. No full snapshot.
         */
        private void publishPatchEvent(Long planId, Long actorId, String entityType,
                        Long entityId, String operationType,
                        Map<String, Object> patch, long revision, String operationId) {
                try {
                        PlanBoardEventV2 event = PlanBoardEventV2.builder()
                                        .envelopeVersion("2")
                                        .planId(planId)
                                        .entityType(entityType)
                                        .entityId(entityId)
                                        .operationType(operationType)
                                        .patch(patch)
                                        .actorId(actorId)
                                        .operationId(operationId)
                                        .revision(revision)
                                        .timestamp(System.currentTimeMillis())
                                        .occurredAt(Instant.now().toString())
                                        .build();

                        kafkaProducer.publishBoardEventV2(event);
                } catch (Exception e) {
                        log.error("Failed to publish patch event planId={} entityType={}: {}",
                                        planId, entityType, e.getMessage(), e);
                }
        }

        @SuppressWarnings("unchecked")
        private Map<String, Object> cardToMap(CardDto card) {
                try {
                        return objectMapper.convertValue(card, Map.class);
                } catch (Exception e) {
                        return new HashMap<>(Map.of("id", card.getId(), "text", card.getText()));
                }
        }
}
