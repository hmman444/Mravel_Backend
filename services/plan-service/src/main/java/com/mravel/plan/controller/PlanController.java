package com.mravel.plan.controller;

import com.mravel.plan.dto.*;
import com.mravel.plan.model.Visibility;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final CurrentUserService currentUser;

    // Feed: GET /api/plans?page=1&size=10
    @GetMapping
    public PageResponse<PlanFeedItem> getFeed(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Long viewerId = currentUser.getId();
        Page<PlanFeedItem> data = planService.getFeed(page, size, viewerId);

        return PageResponse.<PlanFeedItem>builder()
                .items(data.getContent())
                .page(page)
                .size(size)
                .total(data.getTotalElements())
                .hasMore(page * size < data.getTotalElements())
                .build();
    }

    // Chi tiết: GET /api/plans/{id}
    @GetMapping("/{id}")
    public PlanFeedItem getById(@PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean isFriend) {
        Long viewerId = currentUser.getId();
        return planService.getById(id, viewerId, isFriend);
    }

    @PostMapping
    public PlanFeedItem createPlan(
            @RequestBody CreatePlanRequest req) {
        Long userId = currentUser.getId();
        return planService.createPlan(req, userId);
    }

    @PatchMapping("/{id}/visibility")
    public ResponseEntity<PlanFeedItem> updateVisibility(
            @PathVariable Long id,
            @RequestParam Visibility visibility) {
        Long userId = currentUser.getId();
        PlanFeedItem updated = planService.updateVisibility(id, userId, visibility);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/copy")
    public ResponseEntity<PlanFeedItem> copyPublicPlan(
            @PathVariable Long id) {
        Long userId = currentUser.getId();
        PlanFeedItem copied = planService.copyPublicPlan(id, userId);
        return ResponseEntity.ok(copied);
    }

    // Bình luận: POST /api/plans/{id}/comments
    @PostMapping("/{id}/comments")
    public PlanFeedItem.Comment addComment(@PathVariable Long id, @RequestBody AddCommentRequest req) {
        return planService.addComment(
                id,
                req.getUserId(),
                req.getUserName(),
                req.getUserAvatar(),
                req.getText(),
                req.getParentId());
    }

    // Reaction: POST /api/plans/{id}/reactions
    @PostMapping("/{planId}/reactions")
    public ResponseEntity<PlanFeedItem> react(
            @PathVariable Long planId,
            @RequestParam String key) {
        Long userId = currentUser.getId();
        return ResponseEntity.ok(planService.react(planId, key, userId));
    }

    // Tăng view: POST /api/plans/{id}/views
    @PostMapping("/{id}/views")
    public void increaseView(@PathVariable Long id) {
        planService.increaseView(id);
    }
}
