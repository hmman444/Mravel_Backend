package com.mravel.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "auth-service", url = "http://localhost:8081/api/auth")
public interface AuthTokenClient {

    @GetMapping("/validate")
    Map<String, Object> validateToken(@RequestHeader("Authorization") String authorizationHeader);
}
