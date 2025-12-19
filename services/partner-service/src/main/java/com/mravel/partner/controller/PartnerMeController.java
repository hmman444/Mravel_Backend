package com.mravel.partner.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.partner.security.CurrentPartnerService;
import com.mravel.partner.security.JwtUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner/me")
@RequiredArgsConstructor
public class PartnerMeController {

    private final CurrentPartnerService currentPartnerService;

    @GetMapping
    public ResponseEntity<ApiResponse<JwtUserPrincipal>> me() {
        JwtUserPrincipal p = currentPartnerService.getPrincipalOrThrow();
        return ResponseEntity.ok(ApiResponse.success("OK", p));
    }
}