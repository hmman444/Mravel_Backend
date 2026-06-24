package com.mravel.admin.controller;

import com.mravel.admin.client.PlanAdminClient;
import com.mravel.common.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminPlanController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminPlanControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean PlanAdminClient planAdminClient;

    private static final String AUTH = "Bearer test-token";

    @Test
    void stats_defaultDays30() throws Exception {
        when(planAdminClient.stats(anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/plans/stats")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(planAdminClient).stats(eq(30), eq("test-token"));
    }

    @Test
    void stats_customDays() throws Exception {
        when(planAdminClient.stats(anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/plans/stats")
                        .header("Authorization", AUTH)
                        .param("days", "7"))
                .andExpect(status().isOk());

        verify(planAdminClient).stats(eq(7), eq("test-token"));
    }

    @Test
    void listPlans_defaultParams() throws Exception {
        when(planAdminClient.listPlans(any(), any(), any(), any(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/plans/list")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(planAdminClient).listPlans(
                isNull(), isNull(), isNull(), isNull(),
                eq(1), eq(20), eq("createdAt,desc"), eq("test-token"));
    }

    @Test
    void listPlans_withFilters() throws Exception {
        when(planAdminClient.listPlans(any(), any(), any(), any(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/plans/list")
                        .header("Authorization", AUTH)
                        .param("q", "hanoi")
                        .param("visibility", "PUBLIC")
                        .param("status", "ACTIVE")
                        .param("locked", "false")
                        .param("page", "2")
                        .param("size", "10"))
                .andExpect(status().isOk());

        verify(planAdminClient).listPlans(
                eq("hanoi"), eq("PUBLIC"), eq("ACTIVE"), eq(false),
                eq(2), eq(10), eq("createdAt,desc"), eq("test-token"));
    }

    @Test
    void planDetail_delegatesToClient() throws Exception {
        when(planAdminClient.planDetail(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/plans/42/detail")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(planAdminClient).planDetail(eq(42L), eq("test-token"));
    }

    @Test
    void restore_delegatesToClient() throws Exception {
        when(planAdminClient.restore(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(patch("/api/admin/plans/42/restore")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(planAdminClient).restore(eq(42L), eq("test-token"));
    }

    @Test
    void reports_defaultParams() throws Exception {
        when(planAdminClient.listReports(any(), anyInt(), anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/plans/reports")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(planAdminClient).listReports(isNull(), eq(1), eq(20), eq("test-token"));
    }

    @Test
    void reports_filterByStatus() throws Exception {
        when(planAdminClient.listReports(any(), anyInt(), anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/plans/reports")
                        .header("Authorization", AUTH)
                        .param("status", "PENDING"))
                .andExpect(status().isOk());

        verify(planAdminClient).listReports(eq("PENDING"), eq(1), eq(20), eq("test-token"));
    }

    @Test
    void takedown_withReason() throws Exception {
        when(planAdminClient.takedown(anyLong(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(patch("/api/admin/plans/42/takedown")
                        .header("Authorization", AUTH)
                        .param("reason", "Nội dung vi phạm"))
                .andExpect(status().isOk());

        verify(planAdminClient).takedown(eq(42L), eq("Nội dung vi phạm"), eq("test-token"));
    }

    @Test
    void resolveReport_defaultAction() throws Exception {
        when(planAdminClient.resolveReport(anyLong(), anyString(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/plans/reports/7/resolve")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(planAdminClient).resolveReport(eq(7L), eq("NONE"), isNull(), eq("test-token"));
    }

    @Test
    void resolveReport_takedownAction() throws Exception {
        when(planAdminClient.resolveReport(anyLong(), anyString(), any(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(post("/api/admin/plans/reports/7/resolve")
                        .header("Authorization", AUTH)
                        .param("action", "TAKEDOWN")
                        .param("reason", "Spam"))
                .andExpect(status().isOk());

        verify(planAdminClient).resolveReport(eq(7L), eq("TAKEDOWN"), eq("Spam"), eq("test-token"));
    }
}
