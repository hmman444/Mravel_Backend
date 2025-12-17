package com.mravel.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.auth.config.JwtUtil;
import com.mravel.auth.dto.*;
import com.mravel.auth.model.OutboxEvent;
import com.mravel.auth.model.RefreshToken;
import com.mravel.auth.model.Role;
import com.mravel.auth.model.User;
import com.mravel.auth.repository.OutboxRepository;
import com.mravel.auth.repository.UserRepository;
import com.mravel.auth.service.social.FacebookAuthService;
import com.mravel.auth.service.social.GoogleAuthService;
import com.mravel.common.event.UserRegisteredEvent;
import com.mravel.notification.NotificationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final NotificationService notificationService;
    private final RefreshTokenService refreshTokenService;
    private final GoogleAuthService googleAuthService;
    private final FacebookAuthService facebookAuthService;
    private final OutboxRepository outboxRepository;
    String defaultAvatar = "https://i.pinimg.com/736x/bc/43/98/bc439871417621836a0eeea768d60944.jpg";

    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email đã tồn tại");

        User user = userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .fullname(request.getFullname())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .provider("local")
                        .role(Role.USER)
                        .enabled(false)
                        .build());

        try {
            UserRegisteredEvent event = UserRegisteredEvent.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .fullname(request.getFullname())
                    .avatar(defaultAvatar)
                    .provider("local")
                    .build();

            String payload = new ObjectMapper().writeValueAsString(event);
            outboxRepository.save(OutboxEvent.builder()
                    .eventType("USER_REGISTERED")
                    .payload(payload)
                    .status("PENDING")
                    .build());

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi serialize UserRegisteredEvent: " + e.getMessage());
        }

        String otp = otpService.generateOtp(user.getEmail());
        notificationService.sendOtpEmail(user.getEmail(), otp);
    }

    public JwtResponse socialLogin(SocialLoginRequest request) {
        SocialUserProfile profile;

        if ("google".equalsIgnoreCase(request.getProvider())) {
            profile = googleAuthService.verifyGoogleToken(request.getToken());
        } else if ("facebook".equalsIgnoreCase(request.getProvider())) {
            profile = facebookAuthService.verifyFacebookToken(request.getToken());
        } else {
            throw new RuntimeException("Nhà cung cấp không hợp lệ");
        }

        // Tìm user theo email
        User user = userRepository.findByEmail(profile.getEmail())
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(profile.getEmail())
                            .enabled(true)
                            .provider(request.getProvider())
                            .providerId(profile.getProviderId())
                            .build();
                    userRepository.save(newUser);

                    // gửi event qua Kafka (Outbox pattern)
                    try {
                        UserRegisteredEvent event = UserRegisteredEvent.builder()
                                .id(newUser.getId())
                                .email(profile.getEmail())
                                .fullname(profile.getName())
                                .avatar(profile.getPicture())
                                .provider(request.getProvider())
                                .providerId(profile.getProviderId())
                                .build();

                        String payload = new ObjectMapper().writeValueAsString(event);
                        outboxRepository.save(OutboxEvent.builder()
                                .eventType("USER_REGISTERED")
                                .payload(payload)
                                .status("PENDING")
                                .build());
                    } catch (Exception e) {
                        throw new RuntimeException("Lỗi khi serialize UserRegisteredEvent: " + e.getMessage());
                    }

                    return newUser;
                });

        // Xóa token cũ
        refreshTokenService.deleteByEmail(user.getEmail());

        // Sinh JWT mới
        String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        RefreshToken refreshToken = refreshTokenService.createToken(user.getEmail());

        return new JwtResponse(
                accessToken,
                refreshToken.getToken(),
                user.getId(),
                user.getEmail(),
                user.getRole().name());
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

        refreshTokenService.deleteByEmail(user.getEmail());

        String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        RefreshToken refreshToken = refreshTokenService.createToken(user.getEmail());

        return new JwtResponse(
                accessToken,
                refreshToken.getToken(),
                user.getId(),
                user.getEmail(),
                user.getRole().name());
    }

    public JwtResponse refreshToken(String refreshToken) {
        if (!refreshTokenService.validate(refreshToken)) {
            throw new RuntimeException("Refresh token không hợp lệ hoặc đã hết hạn");
        }

        String email = jwtUtil.extractEmail(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        refreshTokenService.deleteByToken(refreshToken);

        String newAccessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        RefreshToken newRefreshToken = refreshTokenService.createToken(email);

        return new JwtResponse(
                newAccessToken,
                newRefreshToken.getToken(),
                user.getId(),
                user.getEmail(),
                user.getRole().name());
    }

    public void logout(String refreshToken) {
        refreshTokenService.deleteByToken(refreshToken);
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
