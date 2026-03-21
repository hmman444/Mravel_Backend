package com.mravel.plan.config;

import com.mravel.plan.repository.PlanCommandRepository;
import com.mravel.plan.repository.PlanRevisionLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Phase 2b / Phase 3b — purges ephemeral tables on a fixed schedule.
 *   plan_revision_log : retention 1h  (gap recovery window)
 *   plan_commands      : retention 24h (idempotency window)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlanRevisionLogPurgeJob {

    private final PlanRevisionLogRepository revisionLogRepository;
    private final PlanCommandRepository commandRepository;

    @Scheduled(fixedDelay = 5 * 60 * 1000) // every 5 minutes
    @Transactional
    public void purgeOldRevisionLogEntries() {
        Instant cutoff = Instant.now().minus(1, ChronoUnit.HOURS);
        revisionLogRepository.deleteByCreatedAtBefore(cutoff);
        log.debug("plan_revision_log purge completed, cutoff={}", cutoff);
    }

    @Scheduled(fixedDelay = 60 * 60 * 1000) // every hour
    @Transactional
    public void purgeOldCommandEntries() {
        Instant cutoff = Instant.now().minus(24, ChronoUnit.HOURS);
        commandRepository.deleteByCreatedAtBefore(cutoff);
        log.debug("plan_commands purge completed, cutoff={}", cutoff);
    }
}
