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

@WebMvcTest(AdminPlaceController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminPlaceControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CatalogClient catalogClient;

    private static final String AUTH = "Bearer test-token";

    // ── GET /api/admin/places ─────────────────────────────────────────────────

    @Test
    void listPlaces_defaultKindPoi() throws Exception {
        when(catalogClient.listAllPlaces(anyString(), any(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/places")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).listAllPlaces(eq("POI"), isNull(), isNull(), eq("test-token"));
    }

    @Test
    void listPlaces_withKindDestination() throws Exception {
        when(catalogClient.listAllPlaces(anyString(), any(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/places")
                        .header("Authorization", AUTH)
                        .param("kind", "DESTINATION")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk());

        verify(catalogClient).listAllPlaces(eq("DESTINATION"), eq(0), eq(20), eq("test-token"));
    }

    // ── GET /api/admin/places/{slug} ──────────────────────────────────────────

    @Test
    void getPlaceDetail_delegatesToCatalogClient() throws Exception {
        when(catalogClient.getPlaceDetailBySlug(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/places/ho-guom")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).getPlaceDetailBySlug(eq("ho-guom"), eq("test-token"));
    }

    // ── GET /api/admin/places/{slug}/children ─────────────────────────────────

    @Test
    void getChildren_defaultParams() throws Exception {
        when(catalogClient.getChildrenAllByParentSlug(anyString(), anyString(), any(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/places/ha-noi/children")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).getChildrenAllByParentSlug(
                eq("ha-noi"), eq("POI"), isNull(), isNull(), eq("test-token"));
    }

    @Test
    void getChildren_withPaging() throws Exception {
        when(catalogClient.getChildrenAllByParentSlug(anyString(), anyString(), any(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/places/ha-noi/children")
                        .header("Authorization", AUTH)
                        .param("kind", "VENUE")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk());

        verify(catalogClient).getChildrenAllByParentSlug(
                eq("ha-noi"), eq("VENUE"), eq(1), eq(10), eq("test-token"));
    }

    // ── POST /api/admin/places ────────────────────────────────────────────────

    @Test
    void createPlace_validRequest_delegatesToCatalogClient() throws Exception {
        when(catalogClient.createPlace(any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/places")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"kind\":\"POI\",\"name\":{\"vi\":\"Hồ Gươm\",\"en\":\"Hoan Kiem Lake\"},\"active\":true}"))
                .andExpect(status().isOk());

        verify(catalogClient).createPlace(any(), eq("test-token"));
    }

    @Test
    void createPlace_missingKind_returns400() throws Exception {
        mvc.perform(post("/api/admin/places")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":{\"vi\":\"Hồ Gươm\"}}"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void createPlace_emptyName_returns400() throws Exception {
        mvc.perform(post("/api/admin/places")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"kind\":\"POI\",\"name\":{}}"))
                .andExpect(status().is5xxServerError());
    }

    // ── PUT /api/admin/places/{id} ────────────────────────────────────────────

    @Test
    void updatePlace_delegatesToCatalogClient() throws Exception {
        when(catalogClient.updatePlace(anyString(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(put("/api/admin/places/abc123")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"kind\":\"POI\",\"name\":{\"vi\":\"Hồ Gươm (cập nhật)\"},\"active\":true}"))
                .andExpect(status().isOk());

        verify(catalogClient).updatePlace(eq("abc123"), any(), eq("test-token"));
    }

    // ── PATCH /api/admin/places/{id}/lock ─────────────────────────────────────

    @Test
    void lockPlace_delegatesToCatalogClient() throws Exception {
        when(catalogClient.lockPlace(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(patch("/api/admin/places/abc123/lock")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).lockPlace(eq("abc123"), eq("test-token"));
    }

    // ── PATCH /api/admin/places/{id}/unlock ───────────────────────────────────

    @Test
    void unlockPlace_delegatesToCatalogClient() throws Exception {
        when(catalogClient.unlockPlace(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(patch("/api/admin/places/abc123/unlock")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).unlockPlace(eq("abc123"), eq("test-token"));
    }

    // ── DELETE /api/admin/places/{id} ─────────────────────────────────────────

    @Test
    void hardDeletePlace_delegatesToCatalogClient() throws Exception {
        when(catalogClient.hardDeletePlace(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(delete("/api/admin/places/abc123")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(catalogClient).hardDeletePlace(eq("abc123"), eq("test-token"));
    }

    @Test
    void hardDeletePlace_bearerPrefixStripped() throws Exception {
        when(catalogClient.hardDeletePlace(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(delete("/api/admin/places/abc123")
                        .header("Authorization", "Bearer my-jwt"))
                .andExpect(status().isOk());

        verify(catalogClient).hardDeletePlace(eq("abc123"), eq("my-jwt"));
    }
}
