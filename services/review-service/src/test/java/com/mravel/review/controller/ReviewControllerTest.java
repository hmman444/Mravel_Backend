package com.mravel.review.controller;

import com.mravel.review.dto.ReviewResponse;
import com.mravel.review.dto.ReviewSummaryResponse;
import com.mravel.review.model.TargetType;
import com.mravel.review.security.CurrentUserService;
import com.mravel.review.service.ReviewAspectService;
import com.mravel.review.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
class ReviewControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean ReviewService reviewService;
    @MockBean ReviewAspectService reviewAspectService;

    private static final Long USER_ID = 1L;
    private static final String BASE = "/api/reviews";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
        when(currentUser.getRole()).thenReturn("USER");
    }

    // ── POST /api/reviews ──────────────────────────────────────────────────────

    @Test
    void createReview_validRequest_returns200() throws Exception {
        ReviewResponse resp = ReviewResponse.builder().id(1L).userId(USER_ID).rating(4).build();
        when(reviewService.createReview(eq(USER_ID), any())).thenReturn(resp);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetId\":\"hotel-1\",\"targetType\":\"HOTEL\",\"rating\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void createReview_invalidTargetType_returns400() throws Exception {
        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetId\":\"hotel-1\",\"targetType\":\"INVALID\",\"rating\":4}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createReview_alreadyReviewed_returns409() throws Exception {
        when(reviewService.createReview(eq(USER_ID), any()))
                .thenThrow(new IllegalStateException("Bạn đã đánh giá rồi."));

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetId\":\"hotel-1\",\"targetType\":\"HOTEL\",\"rating\":4}"))
                .andExpect(status().isConflict());
    }

    @Test
    void createReview_noUserId_returns400() throws Exception {
        when(currentUser.getId()).thenReturn(null);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetId\":\"hotel-1\",\"targetType\":\"HOTEL\",\"rating\":4}"))
                .andExpect(status().isBadRequest());
    }

    // ── GET /api/reviews ───────────────────────────────────────────────────────

    @Test
    void getReviews_success_returns200() throws Exception {
        when(reviewService.getReviews(eq(TargetType.HOTEL), eq("hotel-1"), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE).param("targetType", "HOTEL").param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/reviews/summary ───────────────────────────────────────────────

    @Test
    void getSummary_success_returns200() throws Exception {
        ReviewSummaryResponse summary = ReviewSummaryResponse.builder()
                .averageRating(4.2).totalReviews(10L).build();
        when(reviewService.getSummary(eq(TargetType.HOTEL), eq("hotel-1"))).thenReturn(summary);

        mvc.perform(get(BASE + "/summary").param("targetType", "HOTEL").param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalReviews").value(10));
    }

    // ── GET /api/reviews/my ────────────────────────────────────────────────────

    @Test
    void getMyReview_found_returns200() throws Exception {
        ReviewResponse resp = ReviewResponse.builder().id(5L).userId(USER_ID).rating(3).build();
        when(reviewService.getMyReview(eq(USER_ID), eq(TargetType.HOTEL), eq("hotel-1"))).thenReturn(resp);

        mvc.perform(get(BASE + "/my").param("targetType", "HOTEL").param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(5));
    }

    @Test
    void getMyReview_noUserId_returns400() throws Exception {
        when(currentUser.getId()).thenReturn(null);

        mvc.perform(get(BASE + "/my").param("targetType", "HOTEL").param("targetId", "hotel-1"))
                .andExpect(status().isBadRequest());
    }

    // ── PUT /api/reviews/{id} ─────────────────────────────────────────────────

    @Test
    void updateReview_success_returns200() throws Exception {
        ReviewResponse resp = ReviewResponse.builder().id(1L).rating(5).build();
        when(reviewService.updateReview(eq(USER_ID), eq(1L), any())).thenReturn(resp);

        mvc.perform(put(BASE + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rating\":5,\"content\":\"Updated!\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.rating").value(5));
    }

    @Test
    void updateReview_notOwner_returns403() throws Exception {
        when(reviewService.updateReview(eq(USER_ID), eq(1L), any()))
                .thenThrow(new SecurityException("Không có quyền"));

        mvc.perform(put(BASE + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rating\":5}"))
                .andExpect(status().isForbidden());
    }

    // ── DELETE /api/reviews/{id} ──────────────────────────────────────────────

    @Test
    void deleteReview_success_returns200() throws Exception {
        mvc.perform(delete(BASE + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void deleteReview_notOwner_returns403() throws Exception {
        doThrow(new SecurityException("Không có quyền"))
                .when(reviewService).deleteReview(anyLong(), anyString(), eq(1L));

        mvc.perform(delete(BASE + "/1"))
                .andExpect(status().isForbidden());
    }

    // ── GET /api/reviews/aspects ───────────────────────────────────────────────

    @Test
    void getAspects_success_returns200() throws Exception {
        when(reviewAspectService.getDefinitions(eq(TargetType.HOTEL))).thenReturn(List.of());

        mvc.perform(get(BASE + "/aspects").param("category", "HOTEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    // ── GET /api/reviews/can-review ────────────────────────────────────────────

    @Test
    void canReview_eligible_returns200WithTrue() throws Exception {
        when(reviewService.canReview(eq(USER_ID), eq(TargetType.HOTEL), eq("hotel-1"), any(), any()))
                .thenReturn(true);

        mvc.perform(get(BASE + "/can-review").param("targetType", "HOTEL").param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(true));
    }

    @Test
    void canReview_noUserId_returnsNotEligible() throws Exception {
        when(currentUser.getId()).thenReturn(null);

        mvc.perform(get(BASE + "/can-review").param("targetType", "HOTEL").param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }
}
