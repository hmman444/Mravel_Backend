package com.mravel.user.controller;

import com.mravel.common.request.UpdateUserProfileRequest;
import com.mravel.common.response.UserProfileResponse;
import com.mravel.user.model.Gender;
import com.mravel.user.model.UserProfile;
import com.mravel.user.repository.UserRepository;
import com.mravel.user.service.AuthTokenClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final AuthTokenClient authTokenClient;

    // ====== 1. Lấy theo email (dùng cho internal, admin, ... nếu cần) ======

    @GetMapping("/by-email")
    public UserProfileResponse getUserByEmail(@RequestParam("email") String email) {
        UserProfile user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        return toResponse(user);
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

    @PutMapping("/by-email")
    public UserProfileResponse updateUserByEmail(@RequestParam("email") String email,
            @RequestBody UpdateUserProfileRequest request) {
        UserProfile user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        applyUpdates(user, request);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return toResponse(user);
    }

    // ====== 2. Cập nhật "current user" dựa trên JWT ======

    @PutMapping("/me")
    public UserProfileResponse updateCurrentUser(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdateUserProfileRequest request) {

        // Gọi auth-service để validate token & lấy email
        Map<String, Object> result = authTokenClient.validateToken(authorizationHeader);

        Boolean valid = (Boolean) result.get("valid");
        if (valid == null || !valid) {
            throw new RuntimeException("Token không hợp lệ");
        }

        String email = (String) result.get("email");
        if (email == null) {
            throw new RuntimeException("Không lấy được email từ token");
        }

        UserProfile user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        applyUpdates(user, request);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return toResponse(user);
    }

    // ====== Hàm dùng chung để update field ======
    private void applyUpdates(UserProfile user, UpdateUserProfileRequest request) {
        if (request.getFullname() != null)
            user.setFullname(request.getFullname());
        if (request.getAvatar() != null)
            user.setAvatar(request.getAvatar());

        if (request.getGender() != null) {
            try {
                user.setGender(Gender.valueOf(request.getGender()));
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("Giới tính không hợp lệ");
            }
        }

        if (request.getDateOfBirth() != null)
            user.setDateOfBirth(request.getDateOfBirth());
        if (request.getCity() != null)
            user.setCity(request.getCity());
        if (request.getCountry() != null)
            user.setCountry(request.getCountry());
        if (request.getAddressLine() != null)
            user.setAddressLine(request.getAddressLine());

        if (request.getSecondaryEmail() != null)
            user.setSecondaryEmail(request.getSecondaryEmail());
        if (request.getTertiaryEmail() != null)
            user.setTertiaryEmail(request.getTertiaryEmail());

        if (request.getPhone1() != null)
            user.setPhone1(request.getPhone1());
        if (request.getPhone2() != null)
            user.setPhone2(request.getPhone2());
        if (request.getPhone3() != null)
            user.setPhone3(request.getPhone3());
    }

    private UserProfileResponse toResponse(UserProfile user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .avatar(user.getAvatar())
                .provider(user.getProvider())
                .gender(user.getGender() != null ? user.getGender().name() : null)
                .dateOfBirth(user.getDateOfBirth())
                .city(user.getCity())
                .country(user.getCountry())
                .addressLine(user.getAddressLine())
                .secondaryEmail(user.getSecondaryEmail())
                .tertiaryEmail(user.getTertiaryEmail())
                .phone1(user.getPhone1())
                .phone2(user.getPhone2())
                .phone3(user.getPhone3())
                .membershipTier(user.getMembershipTier() != null ? user.getMembershipTier().name() : null)
                .locale(user.getLocale())
                .timeZone(user.getTimeZone())
                .build();
    }
}