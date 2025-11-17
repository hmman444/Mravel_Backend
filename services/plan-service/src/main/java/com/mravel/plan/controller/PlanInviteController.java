package com.mravel.plan.controller;

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
    public ResponseEntity<Map<String, Long>> joinPlan(@RequestParam String token) {
        Long userId = currentUser.getId();
        Long planId = service.joinPlan(token, userId);

        return ResponseEntity.ok(Map.of("planId", planId));
    }
}
