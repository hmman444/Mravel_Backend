package com.mravel.review.controller;

import com.mravel.review.model.TargetType;
import com.mravel.review.security.CurrentUserService;
import com.mravel.review.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminReviewControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean ReviewService reviewService;

    private static final Long USER_ID = 1L;
    private static final String BASE = "/api/reviews/admin";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
        when(currentUser.getRole()).thenReturn("ADMIN");
    }

    // ── GET /api/reviews/admin/negative ────────────────────────────────────────

    @Test
    void negative_admin_returns200() throws Exception {
        when(reviewService.getNegativeReviews(eq(TargetType.HOTEL), isNull(), eq(2), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE + "/negative").param("targetType", "HOTEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void negative_nonAdmin_returns403() throws Exception {
        when(currentUser.getRole()).thenReturn("USER");

        mvc.perform(get(BASE + "/negative").param("targetType", "HOTEL"))
                .andExpect(status().isForbidden());
    }

    // ── GET /api/reviews/admin/negative/count ──────────────────────────────────

    @Test
    void negativeCount_admin_returns200() throws Exception {
        when(reviewService.countNegative(eq(TargetType.HOTEL), eq(2))).thenReturn(42L);

        mvc.perform(get(BASE + "/negative/count").param("targetType", "HOTEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(42));
    }

    @Test
    void negativeCount_nonAdmin_returns403() throws Exception {
        when(currentUser.getRole()).thenReturn("USER");

        mvc.perform(get(BASE + "/negative/count").param("targetType", "HOTEL"))
                .andExpect(status().isForbidden());
    }

    // ── DELETE /api/reviews/admin/{id} ─────────────────────────────────────────

    @Test
    void delete_admin_returns200() throws Exception {
        mvc.perform(delete(BASE + "/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void delete_nonAdmin_returns403() throws Exception {
        when(currentUser.getRole()).thenReturn("USER");

        mvc.perform(delete(BASE + "/10"))
                .andExpect(status().isForbidden());
    }
}
