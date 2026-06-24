package com.mravel.plan.controller;

import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanGeneralService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanGeneralController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanGeneralControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean PlanGeneralService service;

    private static final Long USER_ID = 1L;
    private static final Long PLAN_ID = 10L;
    private static final String BEARER = "Bearer test-token";
    private static final String BASE = "/api/plans/10";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
    }

    // ── PATCH /api/plans/{planId}/title ───────────────────────────────────────

    @Test
    void updateTitle_success_returns200() throws Exception {
        doNothing().when(service).updateTitle(eq(PLAN_ID), eq(USER_ID), anyString());

        mvc.perform(patch(BASE + "/title")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Du lịch Phú Quốc\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/description ─────────────────────────────────

    @Test
    void updateDescription_success_returns200() throws Exception {
        doNothing().when(service).updateDescription(eq(PLAN_ID), eq(USER_ID), any());

        mvc.perform(patch(BASE + "/description")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Chuyến đi 5 ngày\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/dates ───────────────────────────────────────

    @Test
    void updateDates_success_returns200() throws Exception {
        doNothing().when(service).updateDates(eq(PLAN_ID), eq(USER_ID), any(), any());

        mvc.perform(patch(BASE + "/dates")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"startDate\":\"2026-07-01\",\"endDate\":\"2026-07-05\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/status ──────────────────────────────────────

    @Test
    void updateStatus_success_returns200() throws Exception {
        doNothing().when(service).updateStatus(eq(PLAN_ID), eq(USER_ID), any());

        mvc.perform(patch(BASE + "/status")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"COMPLETED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/thumbnail ───────────────────────────────────

    @Test
    void updateThumbnail_success_returns200() throws Exception {
        doNothing().when(service).updateThumbnail(eq(PLAN_ID), eq(USER_ID), anyString());

        mvc.perform(patch(BASE + "/thumbnail")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://example.com/img.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/images ───────────────────────────────────────

    @Test
    void addImage_success_returns200() throws Exception {
        doNothing().when(service).addImage(eq(PLAN_ID), eq(USER_ID), anyString());

        mvc.perform(post(BASE + "/images")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://example.com/img.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/images ─────────────────────────────────────

    @Test
    void removeImage_success_returns200() throws Exception {
        doNothing().when(service).removeImage(eq(PLAN_ID), eq(USER_ID), anyString());

        mvc.perform(delete(BASE + "/images")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://example.com/img.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/videos ───────────────────────────────────────

    @Test
    void addVideo_success_returns200() throws Exception {
        doNothing().when(service).addVideo(eq(PLAN_ID), eq(USER_ID), anyString());

        mvc.perform(post(BASE + "/videos")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://example.com/vid.mp4\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/videos ─────────────────────────────────────

    @Test
    void removeVideo_success_returns200() throws Exception {
        doNothing().when(service).removeVideo(eq(PLAN_ID), eq(USER_ID), anyString());

        mvc.perform(delete(BASE + "/videos")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://example.com/vid.mp4\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PUT /api/plans/{planId}/budget ────────────────────────────────────────

    @Test
    void updateBudget_success_returns200() throws Exception {
        doNothing().when(service).updateBudget(eq(PLAN_ID), eq(USER_ID), any());

        mvc.perform(put(BASE + "/budget")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"total\":5000000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
