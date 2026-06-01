package com.mravel.auth.controller;

import com.mravel.auth.model.Role;
import com.mravel.auth.model.User;
import com.mravel.auth.repository.UserRepository;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Internal, server-to-server lookups. Reachable without a user JWT
 * ({@code /api/auth/**} is permitAll). Used by other services that need to
 * fan out notifications to admins.
 */
@RestController
@RequestMapping("/api/auth/internal")
@RequiredArgsConstructor
public class AuthInternalController {

    private final UserRepository userRepository;

    /** Returns the user ids of all admin accounts. */
    @GetMapping("/admin-ids")
    public ResponseEntity<ApiResponse<List<Long>>> adminIds() {
        List<Long> ids = userRepository
                .adminSearch(List.of(Role.ADMIN), "", PageRequest.of(0, 500))
                .stream()
                .map(User::getId)
                .toList();
        return ResponseEntity.ok(ApiResponse.success("OK", ids));
    }
}
