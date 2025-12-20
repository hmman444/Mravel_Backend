// com.mravel.notification.client.UserClient
package com.mravel.notification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.mravel.notification.dto.UserMiniResponse;

import java.util.List;

@FeignClient(name = "user-service", url = "${mravel.services.user}")
public interface UserClient {

    @PostMapping("/api/users/batch-mini")
    List<UserMiniResponse> batchMini(@RequestBody List<Long> ids);
}