package com.mravel.auth.service;

import com.mravel.common.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/users")
public interface UserProfileClient {
    @GetMapping("/by-email")
    UserProfileResponse getUserByEmail(@RequestParam("email") String email);
}
