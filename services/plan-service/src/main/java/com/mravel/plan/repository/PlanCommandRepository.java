package com.mravel.plan.repository;

import com.mravel.plan.model.PlanCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface PlanCommandRepository extends JpaRepository<PlanCommand, String> {

    @Modifying
    @Query("DELETE FROM PlanCommand c WHERE c.createdAt < :before")
    void deleteByCreatedAtBefore(@Param("before") Instant before);
}
