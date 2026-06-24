package com.mravel.catalog.controller.admin;

import com.mravel.catalog.service.admin.AdminCatalogQueryService;
import com.mravel.catalog.service.admin.AdminHotelModerationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminHotelController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminHotelControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean AdminCatalogQueryService queryService;
    @MockBean AdminHotelModerationService moderationService;

    private static final String BASE = "/api/catalog/admin/hotels";

    // ── GET /api/catalog/admin/hotels ─────────────────────────────────────────

    @Test
    void list_returnsPage() throws Exception {
        when(queryService.listHotels(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── GET /api/catalog/admin/hotels/{id} ────────────────────────────────────

    @Test
    void getDetail_returns200() throws Exception {
        when(moderationService.getByIdForAdmin("h-1")).thenReturn(null);

        mvc.perform(get(BASE + "/h-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/catalog/admin/hotels/{id}:approve ───────────────────────────

    @Test
    void approve_returns200() throws Exception {
        when(moderationService.approve("h-1", 99L)).thenReturn(null);

        mvc.perform(post(BASE + "/h-1:approve").param("adminId", "99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/admin/hotels/{id}:reject ───────────────────────────

    @Test
    void reject_withReason_returns200() throws Exception {
        when(moderationService.reject(eq("h-1"), eq(99L), anyString())).thenReturn(null);

        mvc.perform(post(BASE + "/h-1:reject")
                        .param("adminId", "99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Thiếu hình ảnh hợp lệ\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/admin/hotels/{id}:block ─────────────────────────────

    @Test
    void block_withReason_returns200() throws Exception {
        when(moderationService.block(eq("h-1"), eq(99L), anyString())).thenReturn(null);

        mvc.perform(post(BASE + "/h-1:block")
                        .param("adminId", "99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Vi phạm chính sách\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/admin/hotels/{id}:unblock ───────────────────────────

    @Test
    void unblock_returns200() throws Exception {
        when(moderationService.unblock("h-1", 99L)).thenReturn(null);

        mvc.perform(post(BASE + "/h-1:unblock").param("adminId", "99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
