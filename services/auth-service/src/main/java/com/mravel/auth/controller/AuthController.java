package com.mravel.auth.controller;

import com.mravel.auth.dto.*;
import com.mravel.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "Đăng ký thành công. Vui lòng kiểm tra email để lấy OTP.";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest request) {
        authService.verifyOtpRegistration(request);
        return "Xác thực thành công. Bạn có thể đăng nhập.";
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/forgot-password/request")
    public String forgotPasswordRequest(@RequestBody ForgotPasswordRequest request) {
        authService.sendForgotPasswordOtp(request.getEmail());
        return "OTP đã được gửi tới email của bạn.";
    }

    @PostMapping("/forgot-password/reset")
    public String resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return "Đặt lại mật khẩu thành công.";
    }

}
