package com.mravel.catalog.controller;

import com.mravel.catalog.client.AuthServiceClient;
import com.mravel.catalog.dto.favorite.FavoriteDtos.ToggleFavoriteResponse;
import com.mravel.catalog.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FavoriteController.class)
@AutoConfigureMockMvc(addFilters = false)
class FavoriteControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean FavoriteService favoriteService;
    @MockBean AuthServiceClient authServiceClient;

    // ── POST /api/catalog/favorites/toggle ────────────────────────────────────

    @Test
    void toggle_extractUserIdNull_returns401() throws Exception {
        when(authServiceClient.extractUserId(anyString())).thenReturn(null);

        mvc.perform(post("/api/catalog/favorites/toggle")
                        .header("Authorization", "Bearer invalid-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetType\":\"HOTEL\",\"targetId\":\"h-1\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Vui lòng đăng nhập để sử dụng tính năng yêu thích"));
    }

    @Test
    void toggle_validUser_favorited_returns200() throws Exception {
        when(authServiceClient.extractUserId(anyString())).thenReturn(1L);

        ToggleFavoriteResponse result = mock(ToggleFavoriteResponse.class);
        when(result.isFavorited()).thenReturn(true);
        when(favoriteService.toggle(eq(1L), any(), eq("h-1"))).thenReturn(result);

        mvc.perform(post("/api/catalog/favorites/toggle")
                        .header("Authorization", "Bearer valid-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetType\":\"HOTEL\",\"targetId\":\"h-1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã thêm vào yêu thích"));
    }

    @Test
    void toggle_validUser_unfavorited_returns200() throws Exception {
        when(authServiceClient.extractUserId(anyString())).thenReturn(2L);

        ToggleFavoriteResponse result = mock(ToggleFavoriteResponse.class);
        when(result.isFavorited()).thenReturn(false);
        when(favoriteService.toggle(eq(2L), any(), eq("h-1"))).thenReturn(result);

        mvc.perform(post("/api/catalog/favorites/toggle")
                        .header("Authorization", "Bearer valid-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetType\":\"HOTEL\",\"targetId\":\"h-1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã bỏ yêu thích"));
    }

    // ── GET /api/catalog/favorites/count ─────────────────────────────────────

    @Test
    void getCount_public_returns200() throws Exception {
        when(favoriteService.getCount(any(), eq("h-1"))).thenReturn(null);

        mvc.perform(get("/api/catalog/favorites/count")
                        .param("targetType", "HOTEL")
                        .param("targetId", "h-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── GET /api/catalog/favorites/summary ────────────────────────────────────

    @Test
    void getSummary_noAuth_returns200() throws Exception {
        when(favoriteService.getSummary(isNull(), any(), eq("h-1"))).thenReturn(null);

        mvc.perform(get("/api/catalog/favorites/summary")
                        .param("targetType", "HOTEL")
                        .param("targetId", "h-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void getSummary_withAuth_returns200() throws Exception {
        when(authServiceClient.extractUserId(anyString())).thenReturn(1L);
        when(favoriteService.getSummary(eq(1L), any(), eq("h-1"))).thenReturn(null);

        mvc.perform(get("/api/catalog/favorites/summary")
                        .header("Authorization", "Bearer valid-token")
                        .param("targetType", "HOTEL")
                        .param("targetId", "h-1"))
                .andExpect(status().isOk());
    }

    // ── GET /api/catalog/favorites/me ─────────────────────────────────────────

    @Test
    void getMyFavorites_extractUserIdNull_returns401() throws Exception {
        when(authServiceClient.extractUserId(anyString())).thenReturn(null);

        mvc.perform(get("/api/catalog/favorites/me")
                        .header("Authorization", "Bearer invalid"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Vui lòng đăng nhập"));
    }

    @Test
    void getMyFavorites_validUser_returns200() throws Exception {
        when(authServiceClient.extractUserId(anyString())).thenReturn(1L);
        when(favoriteService.getMyFavorites(eq(1L), any(), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get("/api/catalog/favorites/me")
                        .header("Authorization", "Bearer valid-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
