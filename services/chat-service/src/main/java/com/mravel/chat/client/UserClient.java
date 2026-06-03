package com.mravel.chat.client;

import com.mravel.chat.dto.ChatDtos.UserMiniResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@FeignClient(name = "user-service", url = "${mravel.services.user}")
public interface UserClient {

    @PostMapping("/api/users/batch-mini")
    List<UserMiniResponse> batchMini(@RequestBody List<Long> ids);

    /** Kiểm tra chặn 2 chiều giữa a và b (endpoint nội bộ user-service). */
    @GetMapping("/api/users/internal/block-check")
    Map<String, Object> blockCheck(@RequestHeader("X-Internal-Secret") String secret,
            @RequestParam("a") Long a, @RequestParam("b") Long b);
}
