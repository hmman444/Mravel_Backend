package com.mravel.auth.controller;

import com.mravel.auth.config.JwtUtil;
import com.mravel.auth.dto.AdminUserRow;
import com.mravel.auth.dto.AdminUserStats;
import com.mravel.auth.model.AccountStatus;
import com.mravel.auth.repository.UserRepository;
import com.mravel.auth.scheduler.OutboxPublisher;
import com.mravel.auth.service.AdminUserService;
import com.mravel.auth.service.AuthService;
import com.mravel.auth.service.OtpService;
import com.mravel.auth.service.RefreshTokenService;
import com.mravel.auth.service.UserProfileClient;
import com.mravel.notification.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthAdminUserController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthAdminUserControllerTest {

    @Autowired MockMvc mvc;
    @MockBean AuthService authService;
    @MockBean AdminUserService adminUserService;
    @MockBean OtpService otpService;
    @MockBean RefreshTokenService refreshTokenService;
    @MockBean OutboxPublisher outboxPublisher;
    @MockBean JwtUtil jwtUtils;
    @MockBean UserProfileClient userProfileClient;
    @MockBean UserRepository userRepository;
    @MockBean NotificationService notificationService;

    private static final String ADMIN_TOKEN = "Bearer admin-jwt";

    // ── GET /api/auth/users ───────────────────────────────────────────────────

    @Test
    void list_defaultParams_returnsUserList() throws Exception {
        AdminUserRow row = AdminUserRow.builder()
                .id(5L).email("user@mravel.com").fullname("Nguyen Van A")
                .role("USER").enabled(true).status(AccountStatus.ACTIVE)
                .build();
        when(adminUserService.list(anyList(), anyString(), anyInt(), anyInt()))
                .thenReturn(List.of(row));

        mvc.perform(get("/api/auth/users")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].email").value("user@mravel.com"))
                .andExpect(jsonPath("$.data[0].role").value("USER"));
    }

    @Test
    void list_withRoleFilter_passesRolesToService() throws Exception {
        when(adminUserService.list(anyList(), anyString(), anyInt(), anyInt()))
                .thenReturn(List.of());

        mvc.perform(get("/api/auth/users")
                        .param("role", "PARTNER")
                        .param("q", "abc")
                        .param("page", "1")
                        .param("size", "10")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isOk());

        verify(adminUserService).list(anyList(), eq("abc"), eq(1), eq(10));
    }

    @Test
    void list_invalidRole_returns400() throws Exception {
        mvc.perform(get("/api/auth/users")
                        .param("role", "SUPER_ADMIN")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isBadRequest());
    }

    // ── GET /api/auth/users/stats ─────────────────────────────────────────────

    @Test
    void stats_returnsAggregatedCounts() throws Exception {
        when(adminUserService.stats()).thenReturn(new AdminUserStats(120, 15, 104, 1));

        mvc.perform(get("/api/auth/users/stats")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.totalUsers").value(120))
                .andExpect(jsonPath("$.data.totalPartners").value(15))
                .andExpect(jsonPath("$.data.totalRegularUsers").value(104))
                .andExpect(jsonPath("$.data.totalAdmins").value(1));
    }

    // ── PATCH /api/auth/users/{id}/lock ───────────────────────────────────────

    @Test
    void lock_adminToken_locksAccount() throws Exception {
        when(jwtUtils.extractRole("admin-jwt")).thenReturn("ADMIN");
        doNothing().when(adminUserService).lock(5L);

        mvc.perform(patch("/api/auth/users/5/lock")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã khóa tài khoản"));

        verify(adminUserService).lock(5L);
    }

    @Test
    void lock_nonAdminToken_returns403() throws Exception {
        when(jwtUtils.extractRole("user-jwt")).thenReturn("USER");

        mvc.perform(patch("/api/auth/users/5/lock")
                        .header("Authorization", "Bearer user-jwt"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Chỉ ADMIN mới được khóa tài khoản"));

        verify(adminUserService, never()).lock(anyLong());
    }

    @Test
    void lock_noToken_returns403() throws Exception {
        mvc.perform(patch("/api/auth/users/5/lock"))
                .andExpect(status().isForbidden());

        verify(adminUserService, never()).lock(anyLong());
    }

    @Test
    void lock_userNotFound_returns400() throws Exception {
        when(jwtUtils.extractRole("admin-jwt")).thenReturn("ADMIN");
        doThrow(new RuntimeException("User not found")).when(adminUserService).lock(999L);

        mvc.perform(patch("/api/auth/users/999/lock")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isBadRequest());
    }

    @Test
    void lock_targetIsAdmin_returns400() throws Exception {
        when(jwtUtils.extractRole("admin-jwt")).thenReturn("ADMIN");
        doThrow(new RuntimeException("Không được khóa ADMIN")).when(adminUserService).lock(1L);

        mvc.perform(patch("/api/auth/users/1/lock")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isBadRequest());
    }

    // ── PATCH /api/auth/users/{id}/unlock ─────────────────────────────────────

    @Test
    void unlock_adminToken_unlocksAccount() throws Exception {
        when(jwtUtils.extractRole("admin-jwt")).thenReturn("ADMIN");
        doNothing().when(adminUserService).unlock(5L);

        mvc.perform(patch("/api/auth/users/5/unlock")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã mở khóa tài khoản"));

        verify(adminUserService).unlock(5L);
    }

    @Test
    void unlock_nonAdminToken_returns403() throws Exception {
        when(jwtUtils.extractRole("partner-jwt")).thenReturn("PARTNER");

        mvc.perform(patch("/api/auth/users/5/unlock")
                        .header("Authorization", "Bearer partner-jwt"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Chỉ ADMIN mới được mở khóa tài khoản"));

        verify(adminUserService, never()).unlock(anyLong());
    }

    @Test
    void unlock_userNotFound_returns400() throws Exception {
        when(jwtUtils.extractRole("admin-jwt")).thenReturn("ADMIN");
        doThrow(new RuntimeException("User not found")).when(adminUserService).unlock(999L);

        mvc.perform(patch("/api/auth/users/999/unlock")
                        .header("Authorization", ADMIN_TOKEN))
                .andExpect(status().isBadRequest());
    }
}
