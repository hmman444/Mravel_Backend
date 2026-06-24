package com.mravel.catalog.controller;

import com.mravel.catalog.dto.geo.LocationSuggestDTO;
import com.mravel.catalog.service.GeoSuggestService;
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

@WebMvcTest(GeoController.class)
@AutoConfigureMockMvc(addFilters = false)
class GeoControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean GeoSuggestService geoSuggestService;

    private static final String URL = "/api/catalog/geo/suggest";

    private LocationSuggestDTO suggest(String name) {
        return new LocationSuggestDTO(name, name, null, null, null, null, null, null, null, null);
    }

    @Test
    void suggest_returnsListDirectly() throws Exception {
        when(geoSuggestService.suggest(eq("da"), anyInt()))
                .thenReturn(List.of(suggest("Đà Nẵng"), suggest("Đà Lạt")));

        mvc.perform(get(URL).param("q", "da"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Đà Nẵng"));
    }

    @Test
    void suggest_emptyResult_returnsEmptyList() throws Exception {
        when(geoSuggestService.suggest(anyString(), anyInt())).thenReturn(List.of());

        mvc.perform(get(URL).param("q", "xyz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void suggest_customLimit_passedToService() throws Exception {
        when(geoSuggestService.suggest(eq("ho"), eq(3))).thenReturn(List.of(suggest("Hội An")));

        mvc.perform(get(URL).param("q", "ho").param("limit", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
