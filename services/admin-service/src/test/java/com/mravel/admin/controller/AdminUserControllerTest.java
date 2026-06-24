package com.mravel.admin.controller;

import com.mravel.admin.client.AuthAdminClient;
import com.mravel.admin.client.NotificationClient;
import com.mravel.common.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminUserController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminUserControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;

    @MockBean AuthAdminClient authAdminClient;
    @MockBean NotificationClient notificationClient;

    private static final String AUTH_HEADER = "Bearer test-token";

    @Test
    void listUsers_delegatesToAuthClient() throws Exception {
        when(authAdminClient.listUsers(anyString(), anyString(), any(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/auth/users")
                        .header("Authorization", AUTH_HEADER)
                        .param("role", "USER")
                        .param("q", "nguyen"))
                .andExpect(status().isOk());

        verify(authAdminClient).listUsers(eq("USER"), eq("nguyen"), isNull(), isNull(), eq("test-token"));
    }

    @Test
    void listUsers_defaultParams_roleIsUser() throws Exception {
        when(authAdminClient.listUsers(anyString(), anyString(), any(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/auth/users")
                        .header("Authorization", AUTH_HEADER))
                .andExpect(status().isOk());

        verify(authAdminClient).listUsers(eq("USER"), eq(""), isNull(), isNull(), eq("test-token"));
    }

    @Test
    void lockUser_callsAuthClientAndSendsNotification() throws Exception {
        when(authAdminClient.lock(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("locked", null)));

        mvc.perform(patch("/api/admin/auth/users/42/lock")
                        .header("Authorization", AUTH_HEADER)
                        .header("X-User-Id", "1"))
                .andExpect(status().isOk());

        verify(authAdminClient).lock(eq(42L), eq("test-token"));
        verify(notificationClient).createNotification(eq(42L), eq(1L), any(), any(), any(), any());
    }

    @Test
    void unlockUser_callsAuthClientAndSendsNotification() throws Exception {
        when(authAdminClient.unlock(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("unlocked", null)));

        mvc.perform(patch("/api/admin/auth/users/7/unlock")
                        .header("Authorization", AUTH_HEADER)
                        .header("X-User-Id", "1"))
                .andExpect(status().isOk());

        verify(authAdminClient).unlock(eq(7L), eq("test-token"));
        verify(notificationClient).createNotification(eq(7L), eq(1L), any(), any(), any(), any());
    }

    @Test
    void lockUser_bearerPrefixStripped() throws Exception {
        when(authAdminClient.lock(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("locked", null)));

        mvc.perform(patch("/api/admin/auth/users/1/lock")
                        .header("Authorization", "Bearer my-jwt-token"))
                .andExpect(status().isOk());

        verify(authAdminClient).lock(eq(1L), eq("my-jwt-token"));
    }
}
