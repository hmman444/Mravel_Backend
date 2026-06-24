package com.mravel.catalog.controller.admin;

import com.mravel.catalog.search.es.IndexingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminSearchController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminSearchControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean IndexingService indexingService;

    private static final String BASE = "/api/catalog/admin/search";

    @Test
    void reindex_all_returns200() throws Exception {
        when(indexingService.reindexAll()).thenReturn(Map.of("hotels", 5, "restaurants", 3, "places", 2));

        mvc.perform(post(BASE + "/reindex").param("target", "all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Reindex completed"));
    }

    @Test
    void reindex_hotels_returns200() throws Exception {
        when(indexingService.reindexHotels()).thenReturn(5);

        mvc.perform(post(BASE + "/reindex").param("target", "hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.hotels").value(5));
    }

    @Test
    void reindex_invalidTarget_returns400() throws Exception {
        mvc.perform(post(BASE + "/reindex").param("target", "invalid"))
                .andExpect(status().isBadRequest());
    }
}
