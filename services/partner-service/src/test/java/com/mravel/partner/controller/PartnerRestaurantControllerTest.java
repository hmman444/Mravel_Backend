package com.mravel.partner.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.partner.client.CatalogPartnerClient;
import com.mravel.partner.security.CurrentPartnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartnerRestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerRestaurantControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CatalogPartnerClient catalogClient;
    @MockBean CurrentPartnerService currentPartnerService;

    private static final Long PARTNER_ID = 42L;
    private static final String BEARER = "Bearer test-token";
    private static final String BASE = "/api/partner/restaurants";

    private ResponseEntity<ApiResponse<?>> ok() {
        ApiResponse<?> body = ApiResponse.success("OK", null);
        return ResponseEntity.ok(body);
    }

    @BeforeEach
    void setUp() {
        when(currentPartnerService.getCurrentPartnerIdOrThrow()).thenReturn(PARTNER_ID);
    }

    // ── GET /api/partner/restaurants ─────────────────────────────────────────

    @Test
    void list_success_forwardsResponse() throws Exception {
        when(catalogClient.listMyRestaurants(eq(PARTNER_ID), any(), any(), any(), anyString())).thenReturn(ok());

        mvc.perform(get(BASE).header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void list_notAuthenticated_returns401() throws Exception {
        when(currentPartnerService.getCurrentPartnerIdOrThrow()).thenThrow(new SecurityException("Unauthenticated"));

        mvc.perform(get(BASE).header("Authorization", BEARER))
                .andExpect(status().isUnauthorized());
    }

    // ── GET /api/partner/restaurants/{id} ────────────────────────────────────

    @Test
    void getById_success_forwardsResponse() throws Exception {
        when(catalogClient.getRestaurantByIdForPartner(eq("r-1"), eq(PARTNER_ID), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/r-1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/partner/restaurants ────────────────────────────────────────

    @Test
    void create_success_forwardsResponse() throws Exception {
        when(catalogClient.createRestaurant(eq(PARTNER_ID), any(), anyString())).thenReturn(ok());

        mvc.perform(post(BASE)
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Restaurant\",\"destinationSlug\":\"da-nang\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void create_missingName_returns400() throws Exception {
        mvc.perform(post(BASE)
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"destinationSlug\":\"da-nang\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void create_missingDestinationSlug_returns400() throws Exception {
        mvc.perform(post(BASE)
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Restaurant\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    // ── PUT /api/partner/restaurants/{id} ────────────────────────────────────

    @Test
    void update_success_forwardsResponse() throws Exception {
        when(catalogClient.updateRestaurant(eq("r-1"), eq(PARTNER_ID), any(), anyString())).thenReturn(ok());

        mvc.perform(put(BASE + "/r-1")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Restaurant\",\"destinationSlug\":\"da-nang\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void update_missingName_returns400() throws Exception {
        mvc.perform(put(BASE + "/r-1")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"destinationSlug\":\"da-nang\"}"))
                .andExpect(status().isBadRequest());
    }

    // ── DELETE /api/partner/restaurants/{id} ─────────────────────────────────

    @Test
    void softDelete_success_forwardsResponse() throws Exception {
        when(catalogClient.softDeleteRestaurant(eq("r-1"), eq(PARTNER_ID), anyString())).thenReturn(ok());

        mvc.perform(delete(BASE + "/r-1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/partner/restaurants/{id}/pause ──────────────────────────────

    @Test
    void pause_success_forwardsResponse() throws Exception {
        when(catalogClient.pauseRestaurant(eq("r-1"), eq(PARTNER_ID), anyString())).thenReturn(ok());

        mvc.perform(post(BASE + "/r-1/pause").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/partner/restaurants/{id}/resume ─────────────────────────────

    @Test
    void resume_success_forwardsResponse() throws Exception {
        when(catalogClient.resumeRestaurant(eq("r-1"), eq(PARTNER_ID), anyString())).thenReturn(ok());

        mvc.perform(post(BASE + "/r-1/resume").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/partner/restaurants/{id}/unlock-request ────────────────────

    @Test
    void unlockRequest_success_forwardsResponse() throws Exception {
        when(catalogClient.requestUnlockRestaurant(eq("r-1"), eq(PARTNER_ID), any(), anyString())).thenReturn(ok());

        mvc.perform(post(BASE + "/r-1/unlock-request")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Đã khắc phục vi phạm menu\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void unlockRequest_missingReason_returns400() throws Exception {
        mvc.perform(post(BASE + "/r-1/unlock-request")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
}
