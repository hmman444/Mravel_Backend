package com.mravel.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.catalog.service.HotelService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HotelController.class)
@AutoConfigureMockMvc(addFilters = false)
class HotelControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean RestTemplate restTemplate;
    @MockBean HotelService hotelService;

    // ── POST /api/catalog/hotels/search ──────────────────────────────────────

    @Test
    void search_noBody_returnsPage() throws Exception {
        when(hotelService.searchHotels(any(), any())).thenReturn(new PageImpl<>(List.of()));

        mvc.perform(post("/api/catalog/hotels/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tìm kiếm khách sạn thành công"))
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    void search_withLocation_returnsEmptyPage() throws Exception {
        when(hotelService.searchHotels(any(), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(post("/api/catalog/hotels/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"location\":\"da-nang\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── GET /api/catalog/hotels/{slug} ────────────────────────────────────────

    @Test
    void getDetail_found_returns200() throws Exception {
        when(hotelService.getBySlug(eq("khach-san-abc"), anyBoolean())).thenReturn(null);

        mvc.perform(get("/api/catalog/hotels/khach-san-abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Lấy chi tiết khách sạn thành công"));
    }

    @Test
    void getDetail_notFound_throws() throws Exception {
        when(hotelService.getBySlug(eq("not-found"), anyBoolean()))
                .thenThrow(new IllegalArgumentException("Không tìm thấy khách sạn"));

        mvc.perform(get("/api/catalog/hotels/not-found"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Không tìm thấy khách sạn"));
    }

    // ── POST /api/catalog/hotels/{hotelId}/amenities:attach ──────────────────

    @Test
    void attachAmenities_success_returns200() throws Exception {
        doNothing().when(hotelService).attachHotelAmenities(eq("h-1"), any());

        mvc.perform(post("/api/catalog/hotels/h-1/amenities:attach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codes\":[\"wifi\",\"pool\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Gán tiện ích vào khách sạn"));
    }

    @Test
    void detachAmenities_success_returns200() throws Exception {
        doNothing().when(hotelService).detachHotelAmenities(eq("h-1"), any());

        mvc.perform(post("/api/catalog/hotels/h-1/amenities:detach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codes\":[\"pool\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Gỡ tiện ích khỏi khách sạn"));
    }
}
