package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanInviteController {

    private final PlanBoardService service;
    private final CurrentUserService currentUser;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Map<String, Long>>> joinPlan(
            @RequestParam String token) {
        Long userId = currentUser.getId();
        Long planId = service.joinPlan(token, userId);

        Map<String, Long> data = Map.of("planId", planId);

        return ResponseEntity.ok(
                ApiResponse.success("Tham gia plan thành công", data));
    }
}
