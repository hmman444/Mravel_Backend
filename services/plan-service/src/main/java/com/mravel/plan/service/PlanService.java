package com.mravel.plan.service;

import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanComment;
import com.mravel.plan.model.PlanReaction;
import com.mravel.plan.repository.PlanReactionRepository;
import com.mravel.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanReactionRepository reactionRepository;
    private final PlanMapper planMapper;

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
    public PlanFeedItem.Comment addComment(Long planId,
                                           String userId,
                                           String userName,
                                           String userAvatar,
                                           String text) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanComment comment = PlanComment.builder()
                .userId(userId)
                .userName(userName)
                .userAvatar(userAvatar)
                .text(text)
                .createdAt(Instant.now())
                .plan(plan)
                .build();

        plan.getComments().add(comment);
        planRepository.save(plan);

        return PlanFeedItem.Comment.builder()
                .id(comment.getId())
                .user(PlanFeedItem.Comment.User.builder()
                        .id(userId)
                        .name(userName)
                        .avatar(userAvatar)
                        .build())
                .text(text)
                .createdAt(comment.getCreatedAt())
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
            // Nếu cùng loại => bỏ react
            if (existing.getType().equals(key)) {
                plan.getReactions().remove(existing);
                reactionRepository.delete(existing);
            } else {
                // đổi loại react
                existing.setType(key);
                existing.setCreatedAt(Instant.now());
                reactionRepository.save(existing);
            }
        } else {
            // thêm mới
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
