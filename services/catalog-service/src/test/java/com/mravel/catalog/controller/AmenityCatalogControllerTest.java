package com.mravel.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.catalog.dto.amenity.AmenityGroupedResponse;
import com.mravel.catalog.dto.amenity.AmenityResponseDTO;
import com.mravel.catalog.dto.amenity.AmenityUpsertRequest;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.service.AmenityCatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AmenityCatalogController.class)
@AutoConfigureMockMvc(addFilters = false)
class AmenityCatalogControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean RestTemplate restTemplate;
    @MockBean AmenityCatalogService service;

    private static final String BASE = "/api/catalog/amenities";

    @Test
    void create_success_returns200() throws Exception {
        var req = new AmenityUpsertRequest();
        req.setCode("wifi");
        req.setName("Wifi miễn phí");
        req.setScope(AmenityScope.HOTEL);
        req.setActive(true);

        var dto = new AmenityResponseDTO("id-1", "wifi", "Wifi miễn phí",
                AmenityScope.HOTEL, null, null, null, null, null, null, true, null, null);
        when(service.create(any())).thenReturn(dto);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tạo tiện ích thành công"))
                .andExpect(jsonPath("$.data.code").value("wifi"));
    }

    @Test
    void update_success_returns200() throws Exception {
        var dto = new AmenityResponseDTO("id-1", "pool", "Hồ bơi",
                AmenityScope.HOTEL, null, null, null, null, null, null, true, null, null);
        when(service.update(eq("id-1"), any())).thenReturn(dto);

        mvc.perform(put(BASE + "/id-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"pool\",\"name\":\"Hồ bơi\",\"active\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cập nhật tiện ích thành công"));
    }

    @Test
    void delete_success_returns200() throws Exception {
        doNothing().when(service).softDelete("id-1");

        mvc.perform(delete(BASE + "/id-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Xóa tiện ích (mềm) thành công"));
    }

    @Test
    void list_flat_returnsList() throws Exception {
        var dto = new AmenityResponseDTO("id-1", "wifi", "Wifi miễn phí",
                AmenityScope.HOTEL, null, null, null, null, null, null, true, null, null);
        when(service.list(AmenityScope.HOTEL, true)).thenReturn(List.of(dto));

        mvc.perform(get(BASE).param("scope", "HOTEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].code").value("wifi"));
    }

    @Test
    void list_grouped_returnsGroupedResponse() throws Exception {
        when(service.grouped(AmenityScope.HOTEL)).thenReturn(new AmenityGroupedResponse());

        mvc.perform(get(BASE).param("scope", "HOTEL").param("grouped", "true"))
                .andExpect(status().isOk());
    }

    @Test
    void list_groupedWithoutScope_returnsError() throws Exception {
        mvc.perform(get(BASE).param("grouped", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("scope là bắt buộc khi grouped=true"));
    }
}
