package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.board.UpdateBudgetRequest;
import com.mravel.plan.dto.general.*;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans/{planId}")
@RequiredArgsConstructor
public class PlanGeneralController {

    private final CurrentUserService currentUser;
    private final PlanGeneralService service;

    @PatchMapping("/title")
    public ResponseEntity<ApiResponse<?>> updateTitle(
            @PathVariable Long planId,
            @RequestBody UpdateTitleRequest req) {
        service.updateTitle(planId, currentUser.getId(), req.getTitle());
        return ResponseEntity.ok(ApiResponse.success("Đổi tiêu đề thành công", null));
    }

    @PatchMapping("/description")
    public ResponseEntity<ApiResponse<?>> updateDescription(
            @PathVariable Long planId,
            @RequestBody UpdateDescriptionRequest req) {
        service.updateDescription(planId, currentUser.getId(), req.getDescription());
        return ResponseEntity.ok(ApiResponse.success("Đổi mô tả thành công", null));
    }

    @PatchMapping("/dates")
    public ResponseEntity<ApiResponse<?>> updateDates(
            @PathVariable Long planId,
            @RequestBody UpdateDatesRequest req) {
        service.updateDates(planId, currentUser.getId(), req.getStartDate(), req.getEndDate());
        return ResponseEntity.ok(ApiResponse.success("Cập nhật ngày thành công", null));
    }

    @PatchMapping("/status")
    public ResponseEntity<ApiResponse<?>> updateStatus(
            @PathVariable Long planId,
            @RequestBody UpdateStatusRequest req) {
        service.updateStatus(planId, currentUser.getId(), req.getStatus());
        return ResponseEntity.ok(ApiResponse.success("Đổi trạng thái thành công", null));
    }

    @PatchMapping("/thumbnail")
    public ResponseEntity<ApiResponse<?>> updateThumbnail(
            @PathVariable Long planId,
            @RequestBody UpdateThumbnailRequest req) {
        service.updateThumbnail(planId, currentUser.getId(), req.getUrl());
        return ResponseEntity.ok(ApiResponse.success("Đổi thumbnail thành công", null));
    }

    @PostMapping("/images")
    public ResponseEntity<ApiResponse<?>> addImage(
            @PathVariable Long planId,
            @RequestBody AddImageRequest req) {
        service.addImage(planId, currentUser.getId(), req.getUrl());
        return ResponseEntity.ok(ApiResponse.success("Thêm ảnh thành công", null));
    }

    @DeleteMapping("/images")
    public ResponseEntity<ApiResponse<?>> removeImage(
            @PathVariable Long planId,
            @RequestBody RemoveImageRequest req) {

        service.removeImage(planId, currentUser.getId(), req.getUrl());
        return ResponseEntity.ok(ApiResponse.success("Xóa ảnh thành công", null));
    }

    @PutMapping("/budget")
    public ResponseEntity<ApiResponse<?>> updateBudget(
            @PathVariable Long planId,
            @RequestBody UpdateBudgetRequest req) {

        service.updateBudget(planId, currentUser.getId(), req);
        return ResponseEntity.ok(
                ApiResponse.success("Cập nhật ngân sách thành công", null));
    }

}
