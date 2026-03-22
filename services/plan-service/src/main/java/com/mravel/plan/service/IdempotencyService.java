package com.mravel.plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.plan.dto.command.CommandResponse;
import com.mravel.plan.model.PlanCommand;
import com.mravel.plan.repository.PlanCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * Phase 3b — checks and stores idempotency keys for command API calls.
 *
 * Usage pattern:
 * 
 * <pre>
 * Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
 * if (cached.isPresent())
 *     return cached.get();
 * // ... execute command ...
 * idempotencyService.store(operationId, planId, response);
 * return response;
 * </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final PlanCommandRepository commandRepository;
    private final ObjectMapper objectMapper;

    /**
     * Returns a previously stored response for this operationId, if any.
     * Uses REQUIRES_NEW so the lookup doesn't share the caller's transaction
     * and can read committed rows from parallel requests.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Optional<CommandResponse> findCached(String operationId) {
        if (operationId == null || operationId.isBlank())
            return Optional.empty();

        return commandRepository.findById(operationId).map(cmd -> {
            try {
                log.info("idempotent_command_hit operationId={} planId={}", operationId, cmd.getPlanId());
                return objectMapper.readValue(cmd.getResponseSnapshot(), CommandResponse.class);
            } catch (Exception e) {
                log.warn("Failed to deserialize cached command response for operationId={}: {}",
                        operationId, e.getMessage());
                return null;
            }
        });
    }

    /**
     * Persists the command response. Uses REQUIRES_NEW so a failure here
     * does not roll back the caller's mutation transaction.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void store(String operationId, Long planId, CommandResponse response) {
        if (operationId == null || operationId.isBlank())
            return;

        // Skip if already stored (rare race condition on duplicate concurrent requests)
        if (commandRepository.existsById(operationId))
            return;

        try {
            String json = objectMapper.writeValueAsString(response);
            commandRepository.save(PlanCommand.builder()
                    .operationId(operationId)
                    .planId(planId)
                    .responseSnapshot(json)
                    .createdAt(Instant.now())
                    .build());
        } catch (Exception e) {
            log.warn("Failed to store idempotency record for operationId={}: {}",
                    operationId, e.getMessage());
        }
    }
}
