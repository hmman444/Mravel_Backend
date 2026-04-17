package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.document.PlanDocument;
import com.mravel.plan.dto.*;
import com.mravel.plan.model.Visibility;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanSearchService;
import com.mravel.plan.service.PlanService;
import com.mravel.plan.util.CursorUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

        private final PlanService planService;
        private final PlanSearchService planSearchService;
        private final CurrentUserService currentUser;

        /**
         * Advanced search endpoint backed by Elasticsearch (cursor-based pagination).
         * All filter parameters are optional — omit them to get the full accessible feed.
         *
         * Query params:
         *   q             – free-text keyword
         *   budgetMin/Max – VND range
         *   daysMin/Max   – trip duration range
         *   startDateFrom/To – ISO-8601 date range (yyyy-MM-dd)
         *   destinations  – repeated param for destination names
         *   sortBy        – RELEVANCE | NEWEST | MOST_VIEWED | BUDGET_ASC | BUDGET_DESC
         *   cursor        – opaque pagination cursor from previous response (null = first page)
         *   size          – page size (default 10, max 50)
         *   userLimit     – max user results returned alongside plan results
         */
        @GetMapping("/search")
        public ResponseEntity<ApiResponse<PlanSearchResponse>> search(
                        @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                        @RequestParam(name = "q", defaultValue = "") String q,
                        @RequestParam(required = false) Long budgetMin,
                        @RequestParam(required = false) Long budgetMax,
                        @RequestParam(required = false) Integer daysMin,
                        @RequestParam(required = false) Integer daysMax,
                        @RequestParam(required = false) String startDateFrom,
                        @RequestParam(required = false) String startDateTo,
                        @RequestParam(required = false) List<String> destinations,
                        @RequestParam(defaultValue = "RELEVANCE") String sortBy,
                        @RequestParam(required = false) String cursor,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "8") int userLimit) {

                Long viewerId = currentUser.getId();

                PlanFilterRequest filter = PlanFilterRequest.builder()
                                .q(q.isBlank() ? null : q.trim())
                                .budgetMin(budgetMin)
                                .budgetMax(budgetMax)
                                .daysMin(daysMin)
                                .daysMax(daysMax)
                                .startDateFrom(startDateFrom)
                                .startDateTo(startDateTo)
                                .destinations(destinations)
                                .sortBy(sortBy)
                                .cursor(cursor)
                                .size(size)
                                .build();

                // Resolve visibility context
                List<Long> memberPlanIds = planService.getMemberPlanIds(viewerId);
                List<Long> friendIds     = planService.getFriendIds(authorizationHeader);

                SearchHits<PlanDocument> hits = planSearchService.search(filter, viewerId, friendIds, memberPlanIds);

                List<SearchHit<PlanDocument>> hitList = hits.getSearchHits();

                List<PlanFeedItem> planItems = hitList.stream()
                                .map(SearchHit::getContent)
                                .map(planService::toFeedItemFromDocument)
                                .toList();

                // Extract nextCursor from the last hit's sort values.
                // If we got fewer items than requested, there are no more results.
                String nextCursor = null;
                boolean hasMore = planItems.size() == size;
                if (hasMore && !hitList.isEmpty()) {
                        nextCursor = CursorUtils.encode(
                                hitList.get(hitList.size() - 1).getSortValues());
                }

                PageResponse<PlanFeedItem> pageResponse = PageResponse.<PlanFeedItem>builder()
                                .items(planItems)
                                .size(size)
                                .total(hits.getTotalHits())
                                .hasMore(hasMore)
                                .nextCursor(nextCursor)
                                .build();

                // Users are only fetched on the first page (cursor == null) to avoid duplication
                var users = (cursor == null)
                                ? planService.searchUsersFromUserService(authorizationHeader, q,
                                                Math.max(1, Math.min(userLimit, 30)))
                                : List.<com.mravel.common.response.UserProfileResponse>of();

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
                        @PathVariable Long id,
                        @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
                Long userId = currentUser.getId();
                PlanFeedItem copied = planService.copyPlan(id, userId, authorizationHeader);
                return ResponseEntity.ok(ApiResponse.success("Sao chép plan thành công", copied));
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

        @PostMapping("/_sync")
        public ResponseEntity<ApiResponse<String>> syncAll() {
                planService.syncAllToElasticsearch();
                return ResponseEntity.ok(
                                ApiResponse.success("Đồng bộ dữ liệu sang Elasticsearch thành công", "Cứu dữ liệu xong!"));
        }
}
