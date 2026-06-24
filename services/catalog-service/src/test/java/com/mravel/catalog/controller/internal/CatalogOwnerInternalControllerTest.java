package com.mravel.catalog.controller.internal;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogOwnerInternalController.class)
@AutoConfigureMockMvc(addFilters = false)
class CatalogOwnerInternalControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean HotelDocRepository hotelRepo;
    @MockBean RestaurantDocRepository restaurantRepo;

    // ── GET /api/catalog/internal/hotels/{id}/owner ───────────────────────────

    @Test
    void hotelOwner_found_returns200() throws Exception {
        HotelDoc doc = mock(HotelDoc.class);
        when(doc.getPublisher()).thenReturn(null);
        when(doc.getName()).thenReturn(null);
        when(doc.getSlug()).thenReturn("hotel-slug");
        when(doc.getImages()).thenReturn(null);
        when(hotelRepo.findById("h-1")).thenReturn(Optional.of(doc));

        mvc.perform(get("/api/catalog/internal/hotels/h-1/owner"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void hotelOwner_notFound_returns404() throws Exception {
        when(hotelRepo.findById("h-999")).thenReturn(Optional.empty());

        mvc.perform(get("/api/catalog/internal/hotels/h-999/owner"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Hotel not found"));
    }

    // ── GET /api/catalog/internal/restaurants/{id}/owner ─────────────────────

    @Test
    void restaurantOwner_found_returns200() throws Exception {
        RestaurantDoc doc = mock(RestaurantDoc.class);
        when(doc.getPublisher()).thenReturn(null);
        when(doc.getName()).thenReturn(null);
        when(doc.getSlug()).thenReturn("restaurant-slug");
        when(doc.getImages()).thenReturn(null);
        when(restaurantRepo.findById("r-1")).thenReturn(Optional.of(doc));

        mvc.perform(get("/api/catalog/internal/restaurants/r-1/owner"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void restaurantOwner_notFound_returns404() throws Exception {
        when(restaurantRepo.findById("r-999")).thenReturn(Optional.empty());

        mvc.perform(get("/api/catalog/internal/restaurants/r-999/owner"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Restaurant not found"));
    }
}
