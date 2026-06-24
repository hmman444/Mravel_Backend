package com.mravel.admin.controller;

import com.mravel.admin.client.CatalogClient;
import com.mravel.common.response.ApiResponse;
import com.mravel.common.security.JwtUserPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCatalogModerationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminCatalogModerationControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CatalogClient catalogClient;

    private static final String AUTH = "Bearer test-token";
    private static final Long ADMIN_ID = 99L;

    @BeforeEach
    void setUpSecurityContext() {
        JwtUserPrincipal principal = new JwtUserPrincipal(ADMIN_ID, "admin@mravel.com", "ADMIN");
        var auth = new UsernamePasswordAuthenticationToken(
                principal, null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    // ── Hotels ──────────────────────────────────────────────────────────────

    @Test
    void listHotels_delegatesToCatalogClient() throws Exception {
        when(catalogClient.adminListHotels(any(), any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/catalog/hotels")
                        .header("Authorization", AUTH)
                        .param("status", "PENDING"))
                .andExpect(status().isOk());

        verify(catalogClient).adminListHotels(eq("PENDING"), isNull(), isNull(), isNull(),
                isNull(), isNull(), isNull(), isNull(), eq("test-token"));
    }

    @Test
    void getHotelDetail_delegatesToCatalogClient() throws Exception {
        when(catalogClient.adminGetHotelDetail(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/catalog/hotels/HOTEL123")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).adminGetHotelDetail(eq("HOTEL123"), eq("test-token"));
    }

    @Test
    void approveHotel_passesAdminIdFromSecurityContext() throws Exception {
        when(catalogClient.adminApproveHotel(anyString(), anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/catalog/hotels/HOTEL123:approve")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).adminApproveHotel(eq("HOTEL123"), eq(ADMIN_ID), eq("test-token"));
    }

    @Test
    void rejectHotel_withReason_delegatesToCatalogClient() throws Exception {
        when(catalogClient.adminRejectHotel(anyString(), anyLong(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/catalog/hotels/HOTEL123:reject")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Thiếu giấy phép\"}"))
                .andExpect(status().isOk());

        verify(catalogClient).adminRejectHotel(eq("HOTEL123"), eq(ADMIN_ID),
                eq("Thiếu giấy phép"), eq("test-token"));
    }

    @Test
    void rejectHotel_blankReason_returns400() throws Exception {
        mvc.perform(post("/api/admin/catalog/hotels/HOTEL123:reject")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"\"}"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void blockHotel_withReason_delegatesToCatalogClient() throws Exception {
        when(catalogClient.adminBlockHotel(anyString(), anyLong(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/catalog/hotels/HOTEL123:block")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Vi phạm\"}"))
                .andExpect(status().isOk());

        verify(catalogClient).adminBlockHotel(eq("HOTEL123"), eq(ADMIN_ID),
                eq("Vi phạm"), eq("test-token"));
    }

    @Test
    void unblockHotel_delegatesToCatalogClient() throws Exception {
        when(catalogClient.adminUnblockHotel(anyString(), anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/catalog/hotels/HOTEL123:unblock")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).adminUnblockHotel(eq("HOTEL123"), eq(ADMIN_ID), eq("test-token"));
    }

    // ── Restaurants ──────────────────────────────────────────────────────────

    @Test
    void listRestaurants_delegatesToCatalogClient() throws Exception {
        when(catalogClient.adminListRestaurants(any(), any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/catalog/restaurants")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).adminListRestaurants(isNull(), isNull(), isNull(), isNull(),
                isNull(), isNull(), isNull(), isNull(), eq("test-token"));
    }

    @Test
    void approveRestaurant_passesAdminIdFromSecurityContext() throws Exception {
        when(catalogClient.adminApproveRestaurant(anyString(), anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/catalog/restaurants/RES123:approve")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).adminApproveRestaurant(eq("RES123"), eq(ADMIN_ID), eq("test-token"));
    }

    @Test
    void rejectRestaurant_blankReason_returns400() throws Exception {
        mvc.perform(post("/api/admin/catalog/restaurants/RES123:reject")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"\"}"))
                .andExpect(status().is5xxServerError());
    }
}
