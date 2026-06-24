package com.mravel.catalog.controller;

import com.mravel.catalog.service.RestaurantService;
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

@WebMvcTest(RestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
class RestaurantControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean RestaurantService restaurantService;

    // ── POST /api/catalog/restaurants/search ──────────────────────────────────

    @Test
    void search_noBody_returnsPage() throws Exception {
        when(restaurantService.searchRestaurants(any(), any())).thenReturn(new PageImpl<>(List.of()));

        mvc.perform(post("/api/catalog/restaurants/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tìm kiếm nhà hàng thành công"))
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    void search_withLocation_returnsEmptyPage() throws Exception {
        when(restaurantService.searchRestaurants(any(), any())).thenReturn(new PageImpl<>(List.of()));

        mvc.perform(post("/api/catalog/restaurants/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"location\":\"hoi-an\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── GET /api/catalog/restaurants/{slug} ───────────────────────────────────

    @Test
    void getDetail_found_returns200() throws Exception {
        when(restaurantService.getBySlug(eq("nha-hang-abc"), anyBoolean())).thenReturn(null);

        mvc.perform(get("/api/catalog/restaurants/nha-hang-abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Lấy chi tiết nhà hàng thành công"));
    }

    @Test
    void getDetail_notFound_returns400() throws Exception {
        when(restaurantService.getBySlug(eq("not-found"), anyBoolean()))
                .thenThrow(new IllegalArgumentException("Không tìm thấy nhà hàng"));

        mvc.perform(get("/api/catalog/restaurants/not-found"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Không tìm thấy nhà hàng"));
    }

    // ── POST /api/catalog/restaurants/{restaurantId}/amenities:attach ─────────

    @Test
    void attachAmenities_success_returns200() throws Exception {
        doNothing().when(restaurantService).attachRestaurantAmenities(eq("r-1"), any());

        mvc.perform(post("/api/catalog/restaurants/r-1/amenities:attach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codes\":[\"wifi\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Gán tiện ích vào nhà hàng"));
    }

    @Test
    void detachAmenities_success_returns200() throws Exception {
        doNothing().when(restaurantService).detachRestaurantAmenities(eq("r-1"), any());

        mvc.perform(post("/api/catalog/restaurants/r-1/amenities:detach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codes\":[\"wifi\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Gỡ tiện ích khỏi nhà hàng"));
    }
}
