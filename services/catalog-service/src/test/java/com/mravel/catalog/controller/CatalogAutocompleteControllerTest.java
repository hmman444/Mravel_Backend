package com.mravel.catalog.controller;

import com.mravel.catalog.dto.AutocompleteItemDTO;
import com.mravel.catalog.search.es.ElasticsearchAutocompleteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogAutocompleteController.class)
@AutoConfigureMockMvc(addFilters = false)
class CatalogAutocompleteControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean ElasticsearchAutocompleteService autocompleteService;

    @Test
    void hotelAutocomplete_qShort_returnsEmpty() throws Exception {
        mvc.perform(get("/api/catalog/hotels/autocomplete").param("q", "a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void hotelAutocomplete_validQ_returnsSuggestions() throws Exception {
        var item = new AutocompleteItemDTO("h-1", "Khách sạn Đà Nẵng", "ks-da-nang", "Đà Nẵng", null, "hotel");
        when(autocompleteService.suggestHotels(eq("da"), anyInt())).thenReturn(List.of(item));

        mvc.perform(get("/api/catalog/hotels/autocomplete").param("q", "da"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Khách sạn Đà Nẵng"));
    }

    @Test
    void restaurantAutocomplete_qShort_returnsEmpty() throws Exception {
        mvc.perform(get("/api/catalog/restaurants/autocomplete").param("q", "x"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void restaurantAutocomplete_validQ_returnsSuggestions() throws Exception {
        var item = new AutocompleteItemDTO("r-1", "Nhà hàng Phố Cổ", "nha-hang-pho-co", "Hội An", null, "restaurant");
        when(autocompleteService.suggestRestaurants(eq("ph"), anyInt())).thenReturn(List.of(item));

        mvc.perform(get("/api/catalog/restaurants/autocomplete").param("q", "ph"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Nhà hàng Phố Cổ"));
    }
}
