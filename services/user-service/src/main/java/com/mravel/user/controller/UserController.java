package com.mravel.user.controller;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/by-email")
    public UserProfileResponse getUserByEmail(@RequestParam("email") String email) {
        return userRepository.findByEmail(email)
                .map(user -> UserProfileResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullname(user.getFullname())
                        .avatar(user.getAvatar())
                        .provider(user.getProvider())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }

    @GetMapping("/{id}")
    public UserProfileResponse getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> UserProfileResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullname(user.getFullname())
                        .avatar(user.getAvatar())
                        .provider(user.getProvider())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

}
