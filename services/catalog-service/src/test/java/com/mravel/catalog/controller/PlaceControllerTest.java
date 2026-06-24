package com.mravel.catalog.controller;

import com.mravel.catalog.service.PlaceService;
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

@WebMvcTest(PlaceController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlaceControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean PlaceService placeService;

    // ── POST /api/catalog/places/search ───────────────────────────────────────

    @Test
    void search_returnsPage() throws Exception {
        when(placeService.searchPlaces(any(), any(), any())).thenReturn(new PageImpl<>(List.of()));

        mvc.perform(post("/api/catalog/places/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    void search_faceted_returnsOk() throws Exception {
        when(placeService.searchPlacesFaceted(any(), any())).thenReturn(null);

        mvc.perform(post("/api/catalog/places/search/faceted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/catalog/places/poi ───────────────────────────────────────────

    @Test
    void searchLegacy_returnsPage() throws Exception {
        when(placeService.searchPlaces(any(), any(), any())).thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get("/api/catalog/places/poi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── GET /api/catalog/places/{slug} ────────────────────────────────────────

    @Test
    void getDetail_found_returns200() throws Exception {
        when(placeService.getBySlug("ha-noi")).thenReturn(null);

        mvc.perform(get("/api/catalog/places/ha-noi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/catalog/places/{slug}/children ───────────────────────────────

    @Test
    void children_returnsPage() throws Exception {
        when(placeService.findChildrenByParentSlug(any(), any(), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get("/api/catalog/places/ha-noi/children"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── POST /api/catalog/places (admin create) ───────────────────────────────

    @Test
    void create_returns200() throws Exception {
        when(placeService.create(any())).thenReturn(null);

        mvc.perform(post("/api/catalog/places")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Tạo địa điểm thành công"));
    }

    // ── PATCH /api/catalog/places/{id}/lock ───────────────────────────────────

    @Test
    void lock_returns200() throws Exception {
        doNothing().when(placeService).lock("p-1");

        mvc.perform(patch("/api/catalog/places/p-1/lock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Khóa địa điểm thành công"));
    }

    // ── DELETE /api/catalog/places/{id} ──────────────────────────────────────

    @Test
    void hardDelete_returns200() throws Exception {
        doNothing().when(placeService).hardDelete("p-1");

        mvc.perform(delete("/api/catalog/places/p-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Xóa địa điểm (cứng) thành công"));
    }
}
