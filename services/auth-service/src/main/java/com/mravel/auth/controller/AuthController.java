package com.mravel.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.auth.config.JwtUtil;
import com.mravel.auth.dto.ForgotPasswordRequest;
import com.mravel.auth.dto.JwtResponse;
import com.mravel.auth.dto.LoginRequest;
import com.mravel.auth.dto.PartnerRegisterRequest;
import com.mravel.auth.dto.RefreshTokenRequest;
import com.mravel.auth.dto.RegisterRequest;
import com.mravel.auth.dto.ResetPasswordRequest;
import com.mravel.auth.dto.SocialLoginRequest;
import com.mravel.auth.dto.VerifyOtpRequest;
import com.mravel.auth.model.AccountStatus;
import com.mravel.auth.model.User;
import com.mravel.auth.repository.UserRepository;
import com.mravel.auth.service.AuthService;
import com.mravel.auth.service.UserProfileClient;
import com.mravel.common.response.ApiResponse;
import com.mravel.common.response.UserProfileResponse;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtils;
    private final UserProfileClient userProfileClient;
    private final UserRepository userRepository;

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String header) {
        String token = header.replace("Bearer ", "");
        boolean valid = jwtUtils.validateToken(token);

        Map<String, Object> body = new HashMap<>();
        body.put("valid", valid);

        if (!valid)
            return ResponseEntity.ok(body);

        Long id = jwtUtils.extractUserId(token);
        User u = userRepository.findById(id).orElse(null);
        if (u == null || u.getStatus() == AccountStatus.LOCKED || !u.isEnabled()) {
            body.put("valid", false);
            return ResponseEntity.ok(body);
        }

        body.put("id", id);
        body.put("email", u.getEmail());
        body.put("role", u.getRole().name());

        try {
            UserProfileResponse profile = userProfileClient.getUserByEmail(u.getEmail());
            if (profile != null) {
                body.put("fullname", profile.getFullname());
                body.put("avatar", profile.getAvatar());
            }
        } catch (Exception ignored) {
            body.put("fullname", null);
            body.put("avatar", null);
        }

        return ResponseEntity.ok(body);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getCurrentUser(HttpServletRequest request) {
        String token = jwtUtils.resolveToken(request);
        String email = jwtUtils.extractEmail(token);

        // Gọi sang user-service để lấy profile
        UserProfileResponse profile = userProfileClient.getUserByEmail(email);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(ApiResponse.success("Đăng ký thành công, vui lòng kiểm tra email.", null));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<?>> verifyOtp(@RequestBody VerifyOtpRequest request) {
        authService.verifyOtpRegistration(request);
        return ResponseEntity.ok(ApiResponse.success("Xác thực thành công. Bạn có thể đăng nhập.", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@RequestBody LoginRequest request) {
        JwtResponse jwt = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Đăng nhập thành công", jwt));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<JwtResponse>> refresh(@RequestBody RefreshTokenRequest request) {
        JwtResponse jwt = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(ApiResponse.success("Làm mới token thành công", jwt));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@RequestBody RefreshTokenRequest request) {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.ok(ApiResponse.success("Đăng xuất thành công", null));
    }

    @PostMapping("/forgot-password/request")
    public ResponseEntity<ApiResponse<?>> forgotPasswordRequest(@RequestBody ForgotPasswordRequest request) {
        authService.sendForgotPasswordOtp(request.getEmail());
        return ResponseEntity.ok(ApiResponse.success("OTP đã được gửi tới email của bạn.", null));
    }

    @PostMapping("/forgot-password/reset")
    public ResponseEntity<ApiResponse<?>> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok(ApiResponse.success("Đặt lại mật khẩu thành công.", null));
    }

    @PostMapping("/social-login")
    public ResponseEntity<ApiResponse<JwtResponse>> socialLogin(@RequestBody SocialLoginRequest request) {
        JwtResponse jwt = authService.socialLogin(request);
        return ResponseEntity.ok(ApiResponse.success("Đăng nhập mạng xã hội thành công", jwt));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<?>> handleExpiredJwt(ExpiredJwtException ex) {
        return ResponseEntity
                .status(401)
                .body(ApiResponse.error("JWT expired"));
    }

    @PostMapping("/partner/register")
    public ResponseEntity<ApiResponse<?>> partnerRegister(@RequestBody PartnerRegisterRequest req) {
        authService.registerPartner(req);
        return ResponseEntity.ok(ApiResponse.success("Đăng ký đối tác thành công, vui lòng kiểm tra email OTP.", null));
    }

    @PostMapping("/partner/verify-otp")
    public ResponseEntity<ApiResponse<?>> partnerVerifyOtp(@RequestBody VerifyOtpRequest req) {
        authService.verifyOtpPartner(req);
        return ResponseEntity.ok(ApiResponse.success("Xác thực thành công. Bạn có thể đăng nhập đối tác.", null));
    }

    @PostMapping("/partner/login")
    public ResponseEntity<ApiResponse<JwtResponse>> partnerLogin(@RequestBody LoginRequest req) {
        JwtResponse jwt = authService.loginPartner(req);
        return ResponseEntity.ok(ApiResponse.success("Đăng nhập đối tác thành công", jwt));
    }

    @PostMapping("/partner/social-login")
    public ResponseEntity<ApiResponse<JwtResponse>> partnerSocialLogin(@RequestBody SocialLoginRequest request) {
        JwtResponse jwt = authService.partnerSocialLogin(request);
        return ResponseEntity.ok(ApiResponse.success("Đăng nhập đối tác mạng xã hội thành công", jwt));
    }
}
