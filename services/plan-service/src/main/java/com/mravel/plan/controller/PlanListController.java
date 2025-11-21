package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.board.*;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanListController {

    private final CurrentUserService currentUser;
    private final PlanListService service;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<MyPlanDto>>> getMyPlans() {
        Long userId = currentUser.getId();
        List<MyPlanDto> list = service.getMyPlans(userId);

        return ResponseEntity.ok(
                ApiResponse.success("Lấy danh sách kế hoạch thành công", list));
    }
}
