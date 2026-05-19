package com.mravel.chat.client;

import com.mravel.chat.dto.ChatDtos.UserMiniResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "user-service", url = "${mravel.services.user}")
public interface UserClient {

    @PostMapping("/api/users/batch-mini")
    List<UserMiniResponse> batchMini(@RequestBody List<Long> ids);
}
