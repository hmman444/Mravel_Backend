package com.mravel.admin.controller;

import com.mravel.admin.client.CatalogClient;
import com.mravel.common.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminAmenityController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminAmenityControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CatalogClient catalogClient;

    private static final String AUTH = "Bearer test-token";

    // ── POST /api/admin/amenities ────────────────────────────────────────────

    @Test
    void createAmenity_validRequest_delegatesToCatalogClient() throws Exception {
        when(catalogClient.createAmenity(any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/amenities")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hồ bơi\",\"code\":\"POOL\",\"scope\":\"HOTEL\",\"active\":true}"))
                .andExpect(status().isOk());

        verify(catalogClient).createAmenity(any(), eq("test-token"));
    }

    @Test
    void createAmenity_missingName_returns400() throws Exception {
        mvc.perform(post("/api/admin/amenities")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"POOL\"}"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void createAmenity_blankName_returns400() throws Exception {
        mvc.perform(post("/api/admin/amenities")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().is5xxServerError());
    }

    // ── PUT /api/admin/amenities/{id} ────────────────────────────────────────

    @Test
    void updateAmenity_delegatesToCatalogClient() throws Exception {
        when(catalogClient.updateAmenity(anyString(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(put("/api/admin/amenities/POOL")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hồ bơi vô cực\",\"active\":true}"))
                .andExpect(status().isOk());

        verify(catalogClient).updateAmenity(eq("POOL"), any(), eq("test-token"));
    }

    @Test
    void updateAmenity_blankName_returns400() throws Exception {
        mvc.perform(put("/api/admin/amenities/POOL")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().is5xxServerError());
    }

    // ── DELETE /api/admin/amenities/{id} ─────────────────────────────────────

    @Test
    void deleteAmenity_delegatesToCatalogClient() throws Exception {
        when(catalogClient.deleteAmenity(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(delete("/api/admin/amenities/POOL")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).deleteAmenity(eq("POOL"), eq("test-token"));
    }

    // ── GET /api/admin/amenities ──────────────────────────────────────────────

    @Test
    void listAmenities_defaultParams() throws Exception {
        when(catalogClient.listAmenities(any(), anyBoolean(), anyBoolean(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/amenities")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).listAmenities(isNull(), eq(true), eq(false), eq("test-token"));
    }

    @Test
    void listAmenities_withScopeAndGrouped() throws Exception {
        when(catalogClient.listAmenities(anyString(), anyBoolean(), anyBoolean(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/amenities")
                        .header("Authorization", AUTH)
                        .param("scope", "HOTEL")
                        .param("active", "true")
                        .param("grouped", "true"))
                .andExpect(status().isOk());

        verify(catalogClient).listAmenities(eq("HOTEL"), eq(true), eq(true), eq("test-token"));
    }
}