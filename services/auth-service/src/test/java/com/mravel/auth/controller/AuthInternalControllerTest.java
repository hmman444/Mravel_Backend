package com.mravel.auth.controller;

import com.mravel.auth.config.JwtUtil;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthInternalController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthInternalControllerTest {

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

    // ── GET /api/auth/internal/admin-ids ──────────────────────────────────────

    @Test
    void adminIds_withAdmins_returnsIdList() throws Exception {
        User admin1 = new User(); admin1.setId(1L); admin1.setRole(Role.ADMIN);
        User admin2 = new User(); admin2.setId(2L); admin2.setRole(Role.ADMIN);

        when(userRepository.adminSearch(eq(List.of(Role.ADMIN)), eq(""), any(PageRequest.class)))
                .thenReturn(List.of(admin1, admin2));

        mvc.perform(get("/api/auth/internal/admin-ids"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0]").value(1))
                .andExpect(jsonPath("$.data[1]").value(2));
    }

    @Test
    void adminIds_noAdmins_returnsEmptyList() throws Exception {
        when(userRepository.adminSearch(eq(List.of(Role.ADMIN)), eq(""), any(PageRequest.class)))
                .thenReturn(List.of());

        mvc.perform(get("/api/auth/internal/admin-ids"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
