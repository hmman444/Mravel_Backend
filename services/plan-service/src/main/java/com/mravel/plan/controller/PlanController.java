package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.*;
import com.mravel.plan.model.Visibility;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

        private final PlanService planService;
        private final CurrentUserService currentUser;

        @GetMapping("/search")
        public ResponseEntity<ApiResponse<PlanSearchResponse>> search(
                        @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                        @RequestParam(name = "q", defaultValue = "") String q,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "8") int userLimit) {
                Long viewerId = currentUser.getId();

                Page<PlanFeedItem> data = planService.searchFeed(page, size, viewerId, authorizationHeader, q);

                PageResponse<PlanFeedItem> pageResponse = PageResponse.<PlanFeedItem>builder()
                                .items(data.getContent())
                                .page(page)
                                .size(size)
                                .total(data.getTotalElements())
                                .hasMore(page * size < data.getTotalElements())
                                .build();

                var users = planService.searchUsersFromUserService(authorizationHeader, q,
                                Math.max(1, Math.min(userLimit, 30)));

                PlanSearchResponse resp = PlanSearchResponse.builder()
                                .query(q)
                                .plans(pageResponse)
                                .users(users)
                                .build();

                return ResponseEntity.ok(ApiResponse.success("Tìm kiếm thành công", resp));
        }

        @GetMapping
        public ResponseEntity<ApiResponse<PageResponse<PlanFeedItem>>> getFeed(
                        @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size) {
                Long viewerId = currentUser.getId();
                Page<PlanFeedItem> data = planService.getFeed(page, size, viewerId, authorizationHeader);

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

        @GetMapping("/{id}/feed")
        public ResponseEntity<ApiResponse<PlanFeedItem>> getFeedDetail(
                        @PathVariable Long id,
                        @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

                PlanFeedItem item = planService.getById(id, authorizationHeader);
                return ResponseEntity.ok(ApiResponse.success("Lấy chi tiết feed thành công", item));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<PlanFeedItem>> getById(
                        @PathVariable Long id,
                        @RequestHeader("Authorization") String authorizationHeader) {

                PlanFeedItem item = planService.getById(id, authorizationHeader);

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
        public ResponseEntity<ApiResponse<PlanFeedItem>> copyPlan(
                        @PathVariable Long id) {
                Long userId = currentUser.getId();
                PlanFeedItem copied = planService.copyPlan(id, userId);
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

        @GetMapping("/me")
        public ResponseEntity<ApiResponse<PageResponse<PlanFeedItem>>> getMyPlans(
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size) {

                Long userId = currentUser.getId();
                Page<PlanFeedItem> data = planService.getUserPlans(userId, userId);

                PageResponse<PlanFeedItem> resp = PageResponse.<PlanFeedItem>builder()
                                .items(data.getContent())
                                .page(page)
                                .size(size)
                                .total(data.getTotalElements())
                                .hasMore(page * size < data.getTotalElements())
                                .build();

                return ResponseEntity.ok(ApiResponse.success("Lấy plan của bản thân thành công", resp));
        }

        @GetMapping("/user/{userId}")
        public ResponseEntity<ApiResponse<PageResponse<PlanFeedItem>>> getPlansOfUser(
                        @PathVariable Long userId,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "false") boolean isFriend) {

                Long viewerId = currentUser.getId();
                Page<PlanFeedItem> data = planService.getUserPlans(userId, viewerId, isFriend);

                PageResponse<PlanFeedItem> resp = PageResponse.<PlanFeedItem>builder()
                                .items(data.getContent())
                                .page(page)
                                .size(size)
                                .total(data.getTotalElements())
                                .hasMore(page * size < data.getTotalElements())
                                .build();

                return ResponseEntity.ok(ApiResponse.success("Lấy plan user khác thành công", resp));
        }

        @GetMapping("/recent")
        public ResponseEntity<ApiResponse<List<PlanFeedItem>>> getRecentPlans(
                        @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
                Long userId = currentUser.getId();
                List<PlanFeedItem> items = planService.getRecentPlans(userId, authorizationHeader);

                return ResponseEntity.ok(
                                ApiResponse.success("Lấy danh sách lịch trình xem gần đây thành công", items));
        }

        @DeleteMapping("/recent/{planId}")
        public ResponseEntity<ApiResponse<?>> removeRecentPlan(@PathVariable Long planId) {
                Long userId = currentUser.getId();
                planService.removeRecentPlan(planId, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Xoá lịch trình khỏi danh sách xem gần đây thành công", null));
        }

        @DeleteMapping("/{planId}")
        public ResponseEntity<ApiResponse<Void>> deletePlan(@PathVariable Long planId) {
                Long userId = currentUser.getId();
                planService.deletePlan(planId, userId);

                return ResponseEntity.ok(
                                ApiResponse.success("Plan deleted successfully", null));
        }
}
