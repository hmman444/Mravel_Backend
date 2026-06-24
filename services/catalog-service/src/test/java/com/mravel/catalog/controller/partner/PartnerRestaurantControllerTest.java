package com.mravel.catalog.controller.partner;

import com.mravel.catalog.service.partner.PartnerRestaurantService;
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

@WebMvcTest(PartnerRestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerRestaurantControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean PartnerRestaurantService service;

    private static final String BASE = "/api/catalog/partners/restaurants";

    // ── GET /api/catalog/partners/restaurants ─────────────────────────────────

    @Test
    void list_returnsPage() throws Exception {
        when(service.listMyRestaurants(eq(1L), any(), any(), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE).param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── GET /api/catalog/partners/restaurants/{id} ────────────────────────────

    @Test
    void getById_returns200() throws Exception {
        when(service.getByIdForPartner("r-1", 1L)).thenReturn(null);

        mvc.perform(get(BASE + "/r-1").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/restaurants/{id}/pause ────────────────────

    @Test
    void pause_returns200() throws Exception {
        when(service.pause("r-1", 1L)).thenReturn(null);

        mvc.perform(post(BASE + "/r-1/pause").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── DELETE /api/catalog/partners/restaurants/{id} ─────────────────────────

    @Test
    void softDelete_returns200() throws Exception {
        when(service.softDelete("r-1", 1L)).thenReturn(null);

        mvc.perform(delete(BASE + "/r-1").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/restaurants/{id}/resume ────────────────────

    @Test
    void resume_returns200() throws Exception {
        when(service.resume("r-1", 1L)).thenReturn(null);

        mvc.perform(post(BASE + "/r-1/resume").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/restaurants (create) ───────────────────────

    @Test
    void create_returns200() throws Exception {
        when(service.create(eq(1L), any(), any(), any())).thenReturn(null);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partnerId\":1,\"pendingReason\":\"CREATE\",\"payload\":{}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── PUT /api/catalog/partners/restaurants/{id} (update) ──────────────────

    @Test
    void update_returns200() throws Exception {
        when(service.update(eq("r-1"), eq(1L), any(), any(), any())).thenReturn(null);

        mvc.perform(put(BASE + "/r-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partnerId\":1,\"pendingReason\":\"UPDATE\",\"payload\":{}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/restaurants/{id}/unlock-request ────────────

    @Test
    void unlockRequest_returns200() throws Exception {
        when(service.unlockRequest(eq("r-1"), eq(1L), anyString())).thenReturn(null);

        mvc.perform(post(BASE + "/r-1/unlock-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partnerId\":1,\"reason\":\"Đã bổ sung giấy phép kinh doanh\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
