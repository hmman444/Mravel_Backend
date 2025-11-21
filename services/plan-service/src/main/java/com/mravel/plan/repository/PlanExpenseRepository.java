package com.mravel.plan.repository;

import com.mravel.plan.model.PlanExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanExpenseRepository extends JpaRepository<PlanExpense, Long> {

    List<PlanExpense> findByPlan_Id(Long planId);

    List<PlanExpense> findByCard_Id(Long cardId);

    @Query("""
                SELECT COALESCE(SUM(e.amount), 0)
                FROM PlanExpense e
                WHERE e.plan.id = :planId
            """)
    Long sumAmountByPlanId(Long planId);

    @Query("""
                SELECT COALESCE(SUM(e.amount), 0)
                FROM PlanExpense e
                WHERE e.card.id = :cardId
            """)
    Long sumAmountByCardId(Long cardId);
}
