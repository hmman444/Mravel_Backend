package com.mravel.plan.repository;

import com.mravel.plan.model.PlanCommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanCommentReactionRepository extends JpaRepository<PlanCommentReaction, Long> {

    Optional<PlanCommentReaction> findByComment_IdAndUserId(Long commentId, Long userId);

    List<PlanCommentReaction> findByComment_Id(Long commentId);
}
