package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.*;
import com.mravel.plan.model.Visibility;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

        private final PlanService planService;
        private final CurrentUserService currentUser;

        @GetMapping
        public ResponseEntity<ApiResponse<PageResponse<PlanFeedItem>>> getFeed(
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size) {
                Long viewerId = currentUser.getId();
                Page<PlanFeedItem> data = planService.getFeed(page, size, viewerId);

                PageResponse<PlanFeedItem> pageResponse = PageResponse.<PlanFeedItem>builder()
                                .items(data.getContent())
                                .page(page)
                                .size(size)
                                .total(data.getTotalElements())
                                .hasMore(page * size < data.getTotalElements())
                                .build();

                return ResponseEntity.ok(
                                ApiResponse.success("Lấy danh sách plan thành công", pageResponse));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<PlanFeedItem>> getById(
                        @PathVariable Long id,
                        @RequestParam(defaultValue = "false") boolean isFriend) {
                Long viewerId = currentUser.getId();
                PlanFeedItem item = planService.getById(id, viewerId, isFriend);
                return ResponseEntity.ok(
                                ApiResponse.success("Lấy chi tiết plan thành công", item));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<PlanFeedItem>> createPlan(
                        @RequestBody CreatePlanRequest req) {
                Long userId = currentUser.getId();
                PlanFeedItem created = planService.createPlan(req, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Tạo plan thành công", created));
        }

        @PatchMapping("/{id}/visibility")
        public ResponseEntity<ApiResponse<PlanFeedItem>> updateVisibility(
                        @PathVariable Long id,
                        @RequestParam Visibility visibility) {
                Long userId = currentUser.getId();
                PlanFeedItem updated = planService.updateVisibility(id, userId, visibility);
                return ResponseEntity.ok(
                                ApiResponse.success("Cập nhật quyền hiển thị thành công", updated));
        }

        @PostMapping("/{id}/copy")
        public ResponseEntity<ApiResponse<PlanFeedItem>> copyPublicPlan(
                        @PathVariable Long id) {
                Long userId = currentUser.getId();
                PlanFeedItem copied = planService.copyPublicPlan(id, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Sao chép plan công khai thành công", copied));
        }

        @PostMapping("/{id}/comments")
        public ResponseEntity<ApiResponse<PlanFeedItem.Comment>> addComment(
                        @PathVariable Long id,
                        @RequestBody AddCommentRequest req) {
                PlanFeedItem.Comment comment = planService.addComment(
                                id,
                                req.getUserId(),
                                req.getUserName(),
                                req.getUserAvatar(),
                                req.getText(),
                                req.getParentId());

                return ResponseEntity.ok(
                                ApiResponse.success("Thêm bình luận thành công", comment));
        }

        @PostMapping("/{planId}/reactions")
        public ResponseEntity<ApiResponse<PlanFeedItem>> react(
                        @PathVariable Long planId,
                        @RequestParam String key) {
                Long userId = currentUser.getId();
                PlanFeedItem updated = planService.react(planId, key, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Cập nhật reaction thành công", updated));
        }

        @PostMapping("/{id}/views")
        public ResponseEntity<ApiResponse<?>> increaseView(@PathVariable Long id) {
                planService.increaseView(id);
                return ResponseEntity.ok(
                                ApiResponse.success("Tăng lượt xem thành công", null));
        }
}
