package com.mravel.catalog.controller.admin;

import com.mravel.catalog.service.admin.AdminDashboardCatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminDashboardCatalogController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminDashboardCatalogControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean AdminDashboardCatalogService service;

    private static final String BASE = "/api/catalog/admin/dashboard";

    @Test
    void summary_returns200() throws Exception {
        when(service.summary()).thenReturn(null);

        mvc.perform(get(BASE + "/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void resolvePartners_returns200() throws Exception {
        when(service.resolvePartners(any(), any())).thenReturn(null);

        mvc.perform(post(BASE + "/partner-resolve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hotelIds\":[\"h-1\"],\"restaurantIds\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
