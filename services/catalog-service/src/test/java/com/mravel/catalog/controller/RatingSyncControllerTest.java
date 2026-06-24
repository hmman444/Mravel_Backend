package com.mravel.catalog.controller;

import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.PlaceDocRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.search.es.IndexingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RatingSyncController.class)
@AutoConfigureMockMvc(addFilters = false)
class RatingSyncControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean HotelDocRepository hotelRepo;
    @MockBean RestaurantDocRepository restaurantRepo;
    @MockBean PlaceDocRepository placeRepo;
    @MockBean IndexingService indexingService;

    private static final String BODY = "{\"avgRating\":4.5,\"reviewsCount\":10}";

    // ── PUT /api/catalog/hotels/{id}/rating-sync ──────────────────────────────

    @Test
    void syncHotelRating_notFound_stillReturns200() throws Exception {
        when(hotelRepo.findById("h-1")).thenReturn(Optional.empty());

        mvc.perform(put("/api/catalog/hotels/h-1/rating-sync")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BODY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── PUT /api/catalog/restaurants/{id}/rating-sync ─────────────────────────

    @Test
    void syncRestaurantRating_notFound_stillReturns200() throws Exception {
        when(restaurantRepo.findById("r-1")).thenReturn(Optional.empty());

        mvc.perform(put("/api/catalog/restaurants/r-1/rating-sync")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BODY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── PUT /api/catalog/places/{id}/rating-sync ──────────────────────────────

    @Test
    void syncPlaceRating_notFound_stillReturns200() throws Exception {
        when(placeRepo.findById("p-1")).thenReturn(Optional.empty());

        mvc.perform(put("/api/catalog/places/p-1/rating-sync")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BODY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
