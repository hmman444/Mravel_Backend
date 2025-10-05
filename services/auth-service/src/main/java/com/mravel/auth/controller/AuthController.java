package com.mravel.auth.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.auth.dto.*;
import com.mravel.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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
}
