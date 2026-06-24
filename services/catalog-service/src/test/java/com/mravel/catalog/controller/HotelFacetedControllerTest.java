package com.mravel.catalog.controller;

import com.mravel.catalog.search.es.ElasticsearchHotelSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HotelFacetedController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = "catalog.search.engine=elasticsearch")
class HotelFacetedControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean ElasticsearchHotelSearchService esHotelSearchService;

    @Test
    void searchFaceted_returnsOk() throws Exception {
        when(esHotelSearchService.searchFaceted(any(), any())).thenReturn(null);

        mvc.perform(post("/api/catalog/hotels/search/faceted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tìm kiếm khách sạn thành công"));
    }

    @Test
    void searchFaceted_noBody_returnsOk() throws Exception {
        when(esHotelSearchService.searchFaceted(any(), any())).thenReturn(null);

        mvc.perform(post("/api/catalog/hotels/search/faceted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
