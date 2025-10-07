package com.mravel.auth.service;

import com.mravel.auth.dto.*;
import com.mravel.auth.config.*;
import com.mravel.auth.model.User;
import com.mravel.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mravel.notification.NotificationService;
import com.mravel.auth.config.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final NotificationService notificationService;

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        User user = new User(null, request.getFullname(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                false);
        userRepository.save(user);

        String otp = otpService.generateOtp(user.getEmail());
        notificationService.sendOtpEmail(user.getEmail(), otp);
    }

    public JwtResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Tài khoản chưa xác thực OTP");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new JwtResponse(token);
    }

    public void verifyOtpRegistration(VerifyOtpRequest request) {
        if (!otpService.validateOtp(request.getEmail(), request.getOtpCode())) {
            throw new RuntimeException("OTP không hợp lệ hoặc đã hết hạn");
        }
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void sendForgotPasswordOtp(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email không tồn tại");
        }
        String otp = otpService.generateOtp(email);
        notificationService.sendOtpEmail(email, otp);
    }

    public void resetPassword(ResetPasswordRequest request) {
        if (!otpService.validateOtp(request.getEmail(), request.getOtpCode())) {
            throw new RuntimeException("OTP không hợp lệ hoặc đã hết hạn");
        }
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

}
