package com.mravel.plan.controller;

import com.mravel.plan.dto.*;
import com.mravel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    // Feed: GET /api/plans?page=1&size=10
    @GetMapping
    public PageResponse<PlanFeedItem> getFeed(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(value = "X-User-Id", required = false) String viewerId
    ) {
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
                                @RequestHeader(value="X-User-Id", required=false) String viewerId) {
        return planService.getById(id, viewerId);
    }

    // Bình luận: POST /api/plans/{id}/comments
    @PostMapping("/{id}/comments")
    public PlanFeedItem.Comment addComment(@PathVariable Long id, @RequestBody AddCommentRequest req) {
            System.out.println("===> Received AddCommentRequest: " + req);

        return planService.addComment(
                id,
                req.getUserId(),
                req.getUserName(),
                req.getUserAvatar(),
                req.getText(),
                req.getParentId()
        );
    }

    // Reaction: POST /api/plans/{id}/reactions
    @PostMapping("/{planId}/reactions")
    public ResponseEntity<PlanFeedItem> react(
            @PathVariable Long planId,
            @RequestParam String key,
            @RequestParam String userId,      
            @RequestParam String userName,
            @RequestParam String userAvatar
    ) {
        return ResponseEntity.ok(planService.react(planId, key, userId, userName, userAvatar));
    }

    // Tăng view: POST /api/plans/{id}/views
    @PostMapping("/{id}/views")
    public void increaseView(@PathVariable Long id) {
        planService.increaseView(id);
    }

    // Chia sẻ (mời qua email): POST /api/plans/{id}/share
    // Ở đây demo: publish sự kiện Kafka, thực tế có thể gọi mail-service
    @PostMapping("/{id}/share")
    public void share(@PathVariable Long id, @RequestBody ShareRequest req) {
        // sẽ publish Kafka event trong KafkaProducer (đính kèm dưới)
        // bạn có thể inject KafkaProducer và gọi .publish(...)
    }
}
