package com.mravel.plan.repository;

import com.mravel.plan.model.PlanCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanCardRepository extends JpaRepository<PlanCard, Long> {
    List<PlanCard> findByListIdOrderByPositionAsc(Long listId);

    long countByListId(Long listId);

    @Modifying
    @Query("delete from PlanCard c where c.list.id = :listId")
    int deleteByListId(@Param("listId") Long listId);
}
