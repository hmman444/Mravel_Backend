package com.mravel.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.auth.config.JwtUtil;
import com.mravel.auth.dto.JwtResponse;
import com.mravel.auth.model.AccountStatus;
import com.mravel.auth.model.Role;
import com.mravel.auth.model.User;
import com.mravel.auth.repository.UserRepository;
import com.mravel.auth.scheduler.OutboxPublisher;
import com.mravel.auth.service.AdminUserService;
import com.mravel.auth.service.AuthService;
import com.mravel.auth.service.OtpService;
import com.mravel.auth.service.RefreshTokenService;
import com.mravel.auth.service.UserProfileClient;
import com.mravel.notification.NotificationService;
import com.mravel.common.response.UserProfileResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean AuthService authService;
    @MockBean AdminUserService adminUserService;
    @MockBean OtpService otpService;
    @MockBean RefreshTokenService refreshTokenService;
    @MockBean OutboxPublisher outboxPublisher;
    @MockBean JwtUtil jwtUtils;
    @MockBean UserProfileClient userProfileClient;
    @MockBean UserRepository userRepository;
    @MockBean NotificationService notificationService;

    // ── GET /api/auth/validate ─────────────────────────────────────────────────

    @Test
    void validate_validToken_returnsValidTrue() throws Exception {
        User user = new User();
        user.setId(5L);
        user.setEmail("user@mravel.com");
        user.setRole(Role.USER);
        user.setEnabled(true);
        user.setStatus(AccountStatus.ACTIVE);

        when(jwtUtils.validateToken("good-token")).thenReturn(true);
        when(jwtUtils.extractUserId("good-token")).thenReturn(5L);
        when(userRepository.findById(5L)).thenReturn(Optional.of(user));
        when(userProfileClient.getUserByEmail("user@mravel.com")).thenReturn(null);

        mvc.perform(get("/api/auth/validate")
                        .header("Authorization", "Bearer good-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.email").value("user@mravel.com"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    void validate_invalidToken_returnsValidFalse() throws Exception {
        when(jwtUtils.validateToken("bad-token")).thenReturn(false);

        mvc.perform(get("/api/auth/validate")
                        .header("Authorization", "Bearer bad-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void validate_userLocked_returnsValidFalse() throws Exception {
        User user = new User();
        user.setId(5L);
        user.setEmail("user@mravel.com");
        user.setRole(Role.USER);
        user.setEnabled(true);
        user.setStatus(AccountStatus.LOCKED);

        when(jwtUtils.validateToken("good-token")).thenReturn(true);
        when(jwtUtils.extractUserId("good-token")).thenReturn(5L);
        when(userRepository.findById(5L)).thenReturn(Optional.of(user));

        mvc.perform(get("/api/auth/validate")
                        .header("Authorization", "Bearer good-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void validate_includesProfileFromUserService() throws Exception {
        User user = new User();
        user.setId(5L);
        user.setEmail("user@mravel.com");
        user.setRole(Role.USER);
        user.setEnabled(true);
        user.setStatus(AccountStatus.ACTIVE);

        UserProfileResponse profile = new UserProfileResponse();
        profile.setFullname("Nguyen Van A");
        profile.setAvatar("https://example.com/avatar.png");

        when(jwtUtils.validateToken("good-token")).thenReturn(true);
        when(jwtUtils.extractUserId("good-token")).thenReturn(5L);
        when(userRepository.findById(5L)).thenReturn(Optional.of(user));
        when(userProfileClient.getUserByEmail("user@mravel.com")).thenReturn(profile);

        mvc.perform(get("/api/auth/validate")
                        .header("Authorization", "Bearer good-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true))
                .andExpect(jsonPath("$.fullname").value("Nguyen Van A"))
                .andExpect(jsonPath("$.avatar").value("https://example.com/avatar.png"));
    }

    // ── GET /api/auth/me ──────────────────────────────────────────────────────

    @Test
    void me_noToken_returns401() throws Exception {
        when(jwtUtils.resolveToken(any())).thenReturn(null);

        mvc.perform(get("/api/auth/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void me_validToken_returnsProfile() throws Exception {
        UserProfileResponse profile = new UserProfileResponse();
        profile.setId(5L);
        profile.setEmail("user@mravel.com");
        profile.setFullname("Nguyen Van A");

        when(jwtUtils.resolveToken(any())).thenReturn("good-token");
        when(jwtUtils.extractEmail("good-token")).thenReturn("user@mravel.com");
        when(userProfileClient.getUserByEmail("user@mravel.com")).thenReturn(profile);

        mvc.perform(get("/api/auth/me")
                        .header("Authorization", "Bearer good-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("user@mravel.com"))
                .andExpect(jsonPath("$.fullname").value("Nguyen Van A"));
    }

    // ── POST /api/auth/register ───────────────────────────────────────────────

    @Test
    void register_success_returnsOk() throws Exception {
        doNothing().when(authService).register(any());

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullname\":\"Nguyen Van A\",\"email\":\"user@mravel.com\",\"password\":\"Secret123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Đăng ký thành công, vui lòng kiểm tra email."));
    }

    @Test
    void register_serviceThrows_returns400() throws Exception {
        doThrow(new RuntimeException("Email đã tồn tại")).when(authService).register(any());

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullname\":\"A\",\"email\":\"dup@mravel.com\",\"password\":\"Secret123\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/verify-otp ─────────────────────────────────────────────

    @Test
    void verifyOtp_success_returnsOk() throws Exception {
        doNothing().when(authService).verifyOtpRegistration(any());

        mvc.perform(post("/api/auth/verify-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"otpCode\":\"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Xác thực thành công. Bạn có thể đăng nhập."));
    }

    @Test
    void verifyOtp_invalidOtp_returns400() throws Exception {
        doThrow(new RuntimeException("OTP không hợp lệ hoặc đã hết hạn"))
                .when(authService).verifyOtpRegistration(any());

        mvc.perform(post("/api/auth/verify-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"otpCode\":\"000000\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/login ──────────────────────────────────────────────────

    @Test
    void login_success_returnsJwtResponse() throws Exception {
        JwtResponse jwt = new JwtResponse("access.token", "refresh.token", 5L, "user@mravel.com", "USER");
        when(authService.login(any())).thenReturn(jwt);

        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"password\":\"Secret123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.accessToken").value("access.token"))
                .andExpect(jsonPath("$.data.email").value("user@mravel.com"))
                .andExpect(jsonPath("$.data.role").value("USER"));
    }

    @Test
    void login_wrongPassword_returns400() throws Exception {
        when(authService.login(any())).thenThrow(new RuntimeException("Sai mật khẩu"));

        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"password\":\"wrong\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_accountLocked_returns400() throws Exception {
        when(authService.login(any())).thenThrow(new RuntimeException("Tài khoản đã bị khóa"));

        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"password\":\"Secret123\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/refresh ────────────────────────────────────────────────

    @Test
    void refresh_success_returnsNewTokens() throws Exception {
        JwtResponse jwt = new JwtResponse("new.access", "new.refresh", 5L, "user@mravel.com", "USER");
        when(authService.refreshToken("old.refresh")).thenReturn(jwt);

        mvc.perform(post("/api/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"refreshToken\":\"old.refresh\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Làm mới token thành công"))
                .andExpect(jsonPath("$.data.accessToken").value("new.access"));
    }

    @Test
    void refresh_invalidRefreshToken_returns400() throws Exception {
        when(authService.refreshToken(anyString()))
                .thenThrow(new RuntimeException("Refresh token không hợp lệ hoặc đã hết hạn"));

        mvc.perform(post("/api/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"refreshToken\":\"expired.token\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/logout ─────────────────────────────────────────────────

    @Test
    void logout_success_returnsOk() throws Exception {
        doNothing().when(authService).logout(anyString());

        mvc.perform(post("/api/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"refreshToken\":\"some.refresh.token\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đăng xuất thành công"));
    }

    // ── POST /api/auth/forgot-password/request ────────────────────────────────

    @Test
    void forgotPasswordRequest_success_returnsOk() throws Exception {
        doNothing().when(authService).sendForgotPasswordOtp(anyString());

        mvc.perform(post("/api/auth/forgot-password/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OTP đã được gửi tới email của bạn."));
    }

    @Test
    void forgotPasswordRequest_emailNotFound_returns400() throws Exception {
        doThrow(new RuntimeException("Email không tồn tại")).when(authService).sendForgotPasswordOtp(anyString());

        mvc.perform(post("/api/auth/forgot-password/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"none@mravel.com\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/forgot-password/reset ──────────────────────────────────

    @Test
    void resetPassword_success_returnsOk() throws Exception {
        doNothing().when(authService).resetPassword(any());

        mvc.perform(post("/api/auth/forgot-password/reset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"otpCode\":\"123456\",\"newPassword\":\"NewSecret456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đặt lại mật khẩu thành công."));
    }

    @Test
    void resetPassword_invalidOtp_returns400() throws Exception {
        doThrow(new RuntimeException("OTP không hợp lệ hoặc đã hết hạn")).when(authService).resetPassword(any());

        mvc.perform(post("/api/auth/forgot-password/reset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"otpCode\":\"000000\",\"newPassword\":\"NewPass\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/social-login ───────────────────────────────────────────

    @Test
    void socialLogin_success_returnsJwt() throws Exception {
        JwtResponse jwt = new JwtResponse("access.token", "refresh.token", 10L, "google@mravel.com", "USER");
        when(authService.socialLogin(any())).thenReturn(jwt);

        mvc.perform(post("/api/auth/social-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"provider\":\"google\",\"token\":\"google-id-token\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đăng nhập mạng xã hội thành công"))
                .andExpect(jsonPath("$.data.role").value("USER"));
    }

    @Test
    void socialLogin_invalidProvider_returns400() throws Exception {
        when(authService.socialLogin(any())).thenThrow(new RuntimeException("Nhà cung cấp không hợp lệ"));

        mvc.perform(post("/api/auth/social-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"provider\":\"twitter\",\"token\":\"xxx\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/partner/register ──────────────────────────────────────

    @Test
    void partnerRegister_success_returnsOk() throws Exception {
        doNothing().when(authService).registerPartner(any());

        mvc.perform(post("/api/auth/partner/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullname\":\"Cong ty ABC\",\"email\":\"partner@mravel.com\"," +
                                "\"password\":\"Secret123\",\"businessName\":\"ABC Travel\",\"phone\":\"0900000000\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đăng ký đối tác thành công, vui lòng kiểm tra email OTP."));
    }

    @Test
    void partnerRegister_emailAlreadyEnabled_returns400() throws Exception {
        doThrow(new RuntimeException("Email đã tồn tại")).when(authService).registerPartner(any());

        mvc.perform(post("/api/auth/partner/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullname\":\"Cong ty\",\"email\":\"dup@mravel.com\",\"password\":\"Secret123\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/partner/verify-otp ────────────────────────────────────

    @Test
    void partnerVerifyOtp_success_returnsOk() throws Exception {
        doNothing().when(authService).verifyOtpPartner(any());

        mvc.perform(post("/api/auth/partner/verify-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"partner@mravel.com\",\"otpCode\":\"654321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Xác thực thành công. Bạn có thể đăng nhập đối tác."));
    }

    @Test
    void partnerVerifyOtp_notPartnerRole_returns400() throws Exception {
        doThrow(new RuntimeException("Tài khoản này không phải đối tác")).when(authService).verifyOtpPartner(any());

        mvc.perform(post("/api/auth/partner/verify-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"otpCode\":\"123456\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/partner/login ──────────────────────────────────────────

    @Test
    void partnerLogin_success_returnsJwtWithPartnerRole() throws Exception {
        JwtResponse jwt = new JwtResponse("access.token", "refresh.token", 20L, "partner@mravel.com", "PARTNER");
        when(authService.loginPartner(any())).thenReturn(jwt);

        mvc.perform(post("/api/auth/partner/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"partner@mravel.com\",\"password\":\"Secret123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đăng nhập đối tác thành công"))
                .andExpect(jsonPath("$.data.role").value("PARTNER"));
    }

    @Test
    void partnerLogin_notPartnerRole_returns400() throws Exception {
        when(authService.loginPartner(any())).thenThrow(new RuntimeException("Tài khoản này không phải đối tác"));

        mvc.perform(post("/api/auth/partner/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@mravel.com\",\"password\":\"Secret123\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── POST /api/auth/partner/social-login ───────────────────────────────────

    @Test
    void partnerSocialLogin_success_returnsJwt() throws Exception {
        JwtResponse jwt = new JwtResponse("access.token", "refresh.token", 20L, "partner@google.com", "PARTNER");
        when(authService.partnerSocialLogin(any())).thenReturn(jwt);

        mvc.perform(post("/api/auth/partner/social-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"provider\":\"google\",\"token\":\"google-token\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đăng nhập đối tác mạng xã hội thành công"))
                .andExpect(jsonPath("$.data.role").value("PARTNER"));
    }

    @Test
    void partnerSocialLogin_existingUserRoleNotPartner_returns400() throws Exception {
        when(authService.partnerSocialLogin(any()))
                .thenThrow(new RuntimeException("Email này đã tồn tại nhưng không phải tài khoản đối tác"));

        mvc.perform(post("/api/auth/partner/social-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"provider\":\"google\",\"token\":\"google-token\"}"))
                .andExpect(status().isBadRequest());
    }
}
