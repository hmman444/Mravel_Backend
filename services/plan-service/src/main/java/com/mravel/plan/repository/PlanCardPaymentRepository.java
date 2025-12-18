package com.mravel.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mravel.plan.model.PlanCardPayment;

@Repository
public interface PlanCardPaymentRepository extends JpaRepository<PlanCardPayment, Long> {

    @Modifying
    @Query("""
                delete from PlanCardPayment p
                where p.card.id in (
                    select c.id from PlanCard c where c.list.id = :listId
                )
            """)
    void deleteByCardListId(@Param("listId") Long listId);
}
