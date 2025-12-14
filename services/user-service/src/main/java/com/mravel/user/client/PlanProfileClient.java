package com.mravel.user.client;

import com.mravel.common.response.ApiResponse;
import com.mravel.user.dto.PageResponse;
import com.mravel.user.dto.PlanFeedItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "plan-service", url = "${mravel.plan-service.url}")
public interface PlanProfileClient {

    @GetMapping("/api/plans/user/{userId}")
    ApiResponse<PageResponse<PlanFeedItem>> getPlansOfUser(
            @PathVariable("userId") Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("isFriend") boolean isFriend,
            @RequestHeader("Authorization") String authorization);
}
