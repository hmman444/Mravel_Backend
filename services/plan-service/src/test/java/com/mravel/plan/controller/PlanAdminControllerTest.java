package com.mravel.plan.controller;

import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanAdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanAdminControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean PlanService planService;

    private static final Long ADMIN_ID = 1L;
    private static final String BEARER = "Bearer admin-token";
    private static final String BASE = "/api/plans/internal/admin";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(ADMIN_ID);
    }

    // ── GET /api/plans/internal/admin/stats ───────────────────────────────────

    @Test
    void stats_success_returnsStatsResponse() throws Exception {
        when(planService.getAdminStats(anyInt())).thenReturn(null);

        mvc.perform(get(BASE + "/stats").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void stats_withDaysParam_passesParamToService() throws Exception {
        when(planService.getAdminStats(eq(7))).thenReturn(null);

        mvc.perform(get(BASE + "/stats").header("Authorization", BEARER).param("days", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/internal/admin/list ────────────────────────────────────

    @Test
    void list_success_returnsPage() throws Exception {
        when(planService.adminSearchPlans(any(), any(), any(), any(), anyInt(), anyInt(), anyString()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE + "/list").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void list_withFilters_passesFiltersToService() throws Exception {
        when(planService.adminSearchPlans(eq("hà nội"), eq("PUBLIC"), eq("ACTIVE"), eq(false),
                anyInt(), anyInt(), anyString()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE + "/list")
                        .header("Authorization", BEARER)
                        .param("q", "hà nội")
                        .param("visibility", "PUBLIC")
                        .param("status", "ACTIVE")
                        .param("locked", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/internal/admin/plans/{id} ──────────────────────────────

    @Test
    void detail_success_returnsDetail() throws Exception {
        when(planService.adminPlanDetail(eq(1L))).thenReturn(null);

        mvc.perform(get(BASE + "/plans/1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/internal/admin/plans/{id}/takedown ──────────────────

    @Test
    void takedown_success_returns200() throws Exception {
        doNothing().when(planService).takedownPlan(eq(1L), eq(ADMIN_ID), anyString());

        mvc.perform(patch(BASE + "/plans/1/takedown").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/internal/admin/plans/{id}/restore ───────────────────

    @Test
    void restore_success_returns200() throws Exception {
        doNothing().when(planService).restorePlan(eq(1L), eq(ADMIN_ID));

        mvc.perform(patch(BASE + "/plans/1/restore").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/internal/admin/reports ─────────────────────────────────

    @Test
    void listReports_success_returnsPage() throws Exception {
        when(planService.listReports(any(), anyInt(), anyInt()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE + "/reports").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/internal/admin/reports/{id}/resolve ──────────────────

    @Test
    void resolveReport_success_returns200() throws Exception {
        doNothing().when(planService).resolveReport(eq(5L), eq(ADMIN_ID), anyString(), any());

        mvc.perform(post(BASE + "/reports/5/resolve")
                        .header("Authorization", BEARER)
                        .param("action", "TAKEDOWN")
                        .param("reason", "Vi phạm nội quy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
