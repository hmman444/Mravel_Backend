package com.mravel.plan.repository;

import com.mravel.plan.model.PlanRevisionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PlanRevisionLogRepository extends JpaRepository<PlanRevisionLog, Long> {

    /**
     * Gap recovery: return events for a plan after a given revision, ordered
     * ascending.
     * Caller caps the result to avoid returning massive result sets.
     */
    @Query("SELECT r FROM PlanRevisionLog r WHERE r.planId = :planId AND r.revision > :afterRevision ORDER BY r.revision ASC")
    List<PlanRevisionLog> findByPlanIdAfterRevision(
            @Param("planId") Long planId,
            @Param("afterRevision") Long afterRevision);

    /** Purge entries older than the given instant (called by scheduled job). */
    @Modifying
    @Query("DELETE FROM PlanRevisionLog r WHERE r.createdAt < :before")
    void deleteByCreatedAtBefore(@Param("before") Instant before);

    /**
     * Count entries for a plan after a revision (used to decide full snapshot vs
     * incremental).
     */
    long countByPlanIdAndRevisionGreaterThan(Long planId, Long revision);
}
