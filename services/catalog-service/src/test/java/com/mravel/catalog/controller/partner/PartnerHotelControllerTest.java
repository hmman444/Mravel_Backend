package com.mravel.catalog.controller.partner;

import com.mravel.catalog.service.partner.PartnerHotelService;
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

@WebMvcTest(PartnerHotelController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerHotelControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean PartnerHotelService service;

    private static final String BASE = "/api/catalog/partners/hotels";

    // ── GET /api/catalog/partners/hotels ──────────────────────────────────────

    @Test
    void list_returnsPage() throws Exception {
        when(service.listMyHotels(eq(1L), any(), any(), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE).param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── GET /api/catalog/partners/hotels/{id} ─────────────────────────────────

    @Test
    void getById_returns200() throws Exception {
        when(service.getByIdForPartner("h-1", 1L)).thenReturn(null);

        mvc.perform(get(BASE + "/h-1").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/hotels/{id}/pause ─────────────────────────

    @Test
    void pause_returns200() throws Exception {
        when(service.pause("h-1", 1L)).thenReturn(null);

        mvc.perform(post(BASE + "/h-1/pause").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/hotels/{id}/resume ─────────────────────────

    @Test
    void resume_returns200() throws Exception {
        when(service.resume("h-1", 1L)).thenReturn(null);

        mvc.perform(post(BASE + "/h-1/resume").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── DELETE /api/catalog/partners/hotels/{id} ──────────────────────────────

    @Test
    void softDelete_returns200() throws Exception {
        when(service.softDelete("h-1", 1L)).thenReturn(null);

        mvc.perform(delete(BASE + "/h-1").param("partnerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/hotels (create) ────────────────────────────

    @Test
    void create_returns200() throws Exception {
        when(service.create(eq(1L), any(), any(), any())).thenReturn(null);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partnerId\":1,\"pendingReason\":\"CREATE\",\"payload\":{}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── PUT /api/catalog/partners/hotels/{id} (update) ───────────────────────

    @Test
    void update_returns200() throws Exception {
        when(service.update(eq("h-1"), eq(1L), any(), any(), any())).thenReturn(null);

        mvc.perform(put(BASE + "/h-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partnerId\":1,\"pendingReason\":\"UPDATE\",\"payload\":{}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/catalog/partners/hotels/{id}/unlock-request ────────────────

    @Test
    void unlockRequest_returns200() throws Exception {
        when(service.unlockRequest(eq("h-1"), eq(1L), anyString())).thenReturn(null);

        mvc.perform(post(BASE + "/h-1/unlock-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partnerId\":1,\"reason\":\"Đã bổ sung giấy phép kinh doanh\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
