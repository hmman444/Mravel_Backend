// com.mravel.notification.client.UserClient
package com.mravel.notification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.mravel.notification.dto.UserMiniResponse;

import java.util.List;
import java.util.Map;

@FeignClient(name = "user-service", url = "${mravel.services.user}")
public interface UserClient {

    @PostMapping("/api/users/batch-mini")
    List<UserMiniResponse> batchMini(@RequestBody List<Long> ids);

    @GetMapping("/api/users/internal/block-check")
    Map<String, Object> blockCheck(@RequestHeader("X-Internal-Secret") String secret,
            @RequestParam("a") Long a, @RequestParam("b") Long b);
}