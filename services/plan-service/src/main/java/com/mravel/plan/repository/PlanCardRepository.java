package com.mravel.plan.repository;

import com.mravel.plan.model.PlanCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanCardRepository extends JpaRepository<PlanCard, Long> {
    List<PlanCard> findByListIdOrderByPositionAsc(Long listId);

    long countByListId(Long listId);
}
