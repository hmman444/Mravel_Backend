package com.mravel.plan.service;

import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanComment;
import com.mravel.plan.model.PlanReaction;
import com.mravel.plan.repository.PlanReactionRepository;
import com.mravel.plan.repository.PlanRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanReactionRepository reactionRepository;
    private final PlanMapper planMapper;

    // ✅ Thêm EntityManager để truy vấn trực tiếp
    @PersistenceContext
    private EntityManager entityManager;

    /** Lấy danh sách feed (có phân trang) */
    public Page<PlanFeedItem> getFeed(int page, int size, String viewerId) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<Plan> data = planRepository.findAll(pageable);

        List<PlanFeedItem> mapped = data.getContent()
                .stream()
                .map(planMapper::toFeedItem)
                .collect(Collectors.toList());

        return new PageImpl<>(mapped, pageable, data.getTotalElements());
    }

    /** Lấy plan theo ID (có thể ghi log view theo viewerId) */
    public PlanFeedItem getById(Long id, String viewerId) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        return planMapper.toFeedItem(plan);
    }

    /** Thêm bình luận vào kế hoạch */
    @Transactional
    public PlanFeedItem.Comment addComment(
            Long planId,
            String userId,
            String userName,
            String userAvatar,
            String text,
            Long parentId
    ) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanComment parent = null;
        if (parentId != null) {
            parent = entityManager.find(PlanComment.class, parentId);
            if (parent == null) throw new RuntimeException("Parent comment not found");
        }

        PlanComment comment = PlanComment.builder()
                .plan(plan)
                .parent(parent)
                .userId(userId)
                .userName(userName)
                .userAvatar(userAvatar)
                .text(text)
                .createdAt(Instant.now())
                .build();

        entityManager.persist(comment);
        entityManager.flush(); 

        return PlanFeedItem.Comment.builder()
                .id(comment.getId())                   
                .parentId(parentId)                     
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .user(PlanFeedItem.Comment.User.builder()
                        .id(userId)
                        .name(userName)
                        .avatar(userAvatar)
                        .build())
                .build();
    }

    /** Reaction: toggle / update / add */
    @Transactional
    public PlanFeedItem react(Long planId, String key, String userId, String userName, String userAvatar) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Optional<PlanReaction> existingOpt = reactionRepository.findByPlanIdAndUserId(planId, userId);

        if (existingOpt.isPresent()) {
            PlanReaction existing = existingOpt.get();
            plan.getReactions().remove(existing);
            reactionRepository.delete(existing);
        } else {
            PlanReaction newReact = PlanReaction.builder()
                    .plan(plan)
                    .userId(userId)
                    .userName(userName)
                    .userAvatar(userAvatar)
                    .type(key)
                    .createdAt(Instant.now())
                    .build();

            plan.getReactions().add(newReact);
            reactionRepository.save(newReact);
        }

        planRepository.save(plan);
        return planMapper.toFeedItem(plan);
    }

    /** Tăng lượt xem */
    public void increaseView(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        plan.setViews(Optional.ofNullable(plan.getViews()).orElse(0L) + 1);
        planRepository.save(plan);
    }
}
