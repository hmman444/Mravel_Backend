package com.mravel.review.controller;

import com.mravel.review.dto.ReviewAspectDefinitionDTO;
import com.mravel.review.model.TargetType;
import com.mravel.review.service.ReviewAspectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminReviewAspectController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminReviewAspectControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean ReviewAspectService reviewAspectService;

    private static final String BASE = "/api/admin/review-aspects";

    // ── GET /api/admin/review-aspects?category=HOTEL ──────────────────────────

    @Test
    void getAll_returns200() throws Exception {
        ReviewAspectDefinitionDTO dto = ReviewAspectDefinitionDTO.builder()
                .id(1).code("cleanliness").labelVi("Sạch sẽ").labelEn("Cleanliness").build();
        when(reviewAspectService.getAllDefinitions(eq(TargetType.HOTEL))).thenReturn(List.of(dto));

        mvc.perform(get(BASE).param("category", "HOTEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].code").value("cleanliness"));
    }

    // ── POST /api/admin/review-aspects ────────────────────────────────────────

    @Test
    void create_returns200() throws Exception {
        ReviewAspectDefinitionDTO dto = ReviewAspectDefinitionDTO.builder()
                .id(2).code("location").labelVi("Vị trí").labelEn("Location").build();
        when(reviewAspectService.createDefinition(any())).thenReturn(dto);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"location\",\"category\":\"HOTEL\",\"labelVi\":\"Vị trí\",\"labelEn\":\"Location\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(2))
                .andExpect(jsonPath("$.data.code").value("location"));
    }

    // ── PUT /api/admin/review-aspects/{id} ────────────────────────────────────

    @Test
    void update_returns200() throws Exception {
        ReviewAspectDefinitionDTO dto = ReviewAspectDefinitionDTO.builder()
                .id(1).code("cleanliness").labelVi("Vệ sinh").labelEn("Cleanliness").build();
        when(reviewAspectService.updateDefinition(eq(1), any())).thenReturn(dto);

        mvc.perform(put(BASE + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"cleanliness\",\"category\":\"HOTEL\",\"labelVi\":\"Vệ sinh\",\"labelEn\":\"Cleanliness\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.labelVi").value("Vệ sinh"));
    }

    // ── DELETE /api/admin/review-aspects/{id} ─────────────────────────────────

    @Test
    void delete_returns200() throws Exception {
        doNothing().when(reviewAspectService).deleteDefinition(eq(1));

        mvc.perform(delete(BASE + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
