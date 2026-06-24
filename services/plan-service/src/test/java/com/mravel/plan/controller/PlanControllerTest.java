package com.mravel.plan.controller;

import com.mravel.plan.document.PlanDocument;
import com.mravel.plan.dto.VisibilityContext;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanSearchService;
import com.mravel.plan.service.PlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean PlanService planService;
    @MockBean PlanSearchService planSearchService;

    private static final Long USER_ID = 1L;
    private static final String BEARER = "Bearer test-token";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
    }

    // ── POST /api/plans/_sync ─────────────────────────────────────────────────

    @Test
    void syncAll_success_returns200() throws Exception {
        doNothing().when(planService).syncAllToElasticsearch();

        mvc.perform(post("/api/plans/_sync"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans ────────────────────────────────────────────────────────

    @Test
    void getFeed_success_returnsPage() throws Exception {
        when(planService.getFeed(anyInt(), anyInt(), any(), any()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get("/api/plans").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/search ─────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    @Test
    void search_success_returnsSearchResponse() throws Exception {
        when(planService.getMemberPlanIds(any())).thenReturn(List.of());
        when(planService.getVisibilityContext(any()))
                .thenReturn(new VisibilityContext(List.of(), List.of()));
        when(planService.getHiddenPlanIds(any())).thenReturn(List.of());

        SearchHits<PlanDocument> hits = mock(SearchHits.class);
        when(hits.getSearchHits()).thenReturn(List.of());
        when(hits.getTotalHits()).thenReturn(0L);
        when(planSearchService.search(any(), any(), any(), any(), any(), any())).thenReturn(hits);

        when(planService.searchUsersFromUserService(any(), any(), anyInt())).thenReturn(List.of());

        mvc.perform(get("/api/plans/search").header("Authorization", BEARER).param("q", "Đà Nẵng"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/{id}/feed ──────────────────────────────────────────────

    @Test
    void getFeedDetail_success_returnsItem() throws Exception {
        when(planService.getById(eq(1L), any())).thenReturn(null);

        mvc.perform(get("/api/plans/1/feed").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/{id} ───────────────────────────────────────────────────

    @Test
    void getById_success_returnsItem() throws Exception {
        when(planService.getById(eq(1L), anyString())).thenReturn(null);

        mvc.perform(get("/api/plans/1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans ───────────────────────────────────────────────────────

    @Test
    void createPlan_success_returnsCreated() throws Exception {
        when(planService.createPlan(any(), eq(USER_ID))).thenReturn(null);

        mvc.perform(post("/api/plans")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Du lịch Đà Nẵng\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{id}/visibility ─────────────────────────────────────

    @Test
    void updateVisibility_success_returnsUpdated() throws Exception {
        when(planService.updateVisibility(eq(1L), eq(USER_ID), any())).thenReturn(null);

        mvc.perform(patch("/api/plans/1/visibility")
                        .header("Authorization", BEARER)
                        .param("visibility", "PUBLIC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{id}/copy ─────────────────────────────────────────────

    @Test
    void copyPlan_success_returnsCopied() throws Exception {
        when(planService.copyPlan(eq(1L), eq(USER_ID), any())).thenReturn(null);

        mvc.perform(post("/api/plans/1/copy").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{id}/comments ────────────────────────────────────────

    @Test
    void addComment_success_usesUserIdFromBody() throws Exception {
        when(planService.addComment(eq(1L), eq(99L), any(), any())).thenReturn(null);

        mvc.perform(post("/api/plans/1/comments")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":99,\"text\":\"Hay quá!\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/comments/{commentId}/reactions ────────────────────────

    @Test
    void reactComment_success_returns200() throws Exception {
        when(planService.reactComment(eq(5L), eq("LIKE"), eq(USER_ID))).thenReturn(null);

        mvc.perform(post("/api/plans/comments/5/reactions")
                        .header("Authorization", BEARER)
                        .param("type", "LIKE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/reactions ────────────────────────────────────

    @Test
    void react_success_returns200() throws Exception {
        when(planService.react(eq(1L), eq("heart"), eq(USER_ID))).thenReturn(null);

        mvc.perform(post("/api/plans/1/reactions")
                        .header("Authorization", BEARER)
                        .param("key", "heart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{id}/views ────────────────────────────────────────────

    @Test
    void increaseView_success_returns200() throws Exception {
        doNothing().when(planService).increaseView(eq(1L), eq(USER_ID));

        mvc.perform(post("/api/plans/1/views").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/me ─────────────────────────────────────────────────────

    @Test
    void getMyPlans_success_returnsPage() throws Exception {
        when(planService.getUserPlans(eq(USER_ID), eq(USER_ID)))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get("/api/plans/me").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/user/{userId} ──────────────────────────────────────────

    @Test
    void getPlansOfUser_success_returnsPage() throws Exception {
        when(planService.getUserPlans(eq(2L), eq(USER_ID), anyBoolean()))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get("/api/plans/user/2").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{id}/hide ─────────────────────────────────────────────

    @Test
    void hidePlan_success_returns200() throws Exception {
        doNothing().when(planService).hidePlan(eq(1L), eq(USER_ID));

        mvc.perform(post("/api/plans/1/hide").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{id}/hidden ─────────────────────────────────────────

    @Test
    void unhidePlan_success_returns200() throws Exception {
        doNothing().when(planService).unhidePlan(eq(1L), eq(USER_ID));

        mvc.perform(delete("/api/plans/1/hidden").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/hidden ─────────────────────────────────────────────────

    @Test
    void listHidden_success_returnsList() throws Exception {
        when(planService.listHiddenPlans(eq(USER_ID))).thenReturn(List.of());

        mvc.perform(get("/api/plans/hidden").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{id}/report ───────────────────────────────────────────

    @Test
    void reportPlan_success_returns200() throws Exception {
        doNothing().when(planService).reportPlan(eq(1L), eq(USER_ID), anyString(), any());

        mvc.perform(post("/api/plans/1/report")
                        .header("Authorization", BEARER)
                        .param("reason", "SPAM"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/recent ─────────────────────────────────────────────────

    @Test
    void getRecentPlans_success_returnsList() throws Exception {
        when(planService.getRecentPlans(eq(USER_ID), any())).thenReturn(List.of());

        mvc.perform(get("/api/plans/recent").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/recent/{planId} ─────────────────────────────────────

    @Test
    void removeRecentPlan_success_returns200() throws Exception {
        doNothing().when(planService).removeRecentPlan(eq(1L), eq(USER_ID));

        mvc.perform(delete("/api/plans/recent/1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId} ────────────────────────────────────────────

    @Test
    void deletePlan_success_returns200() throws Exception {
        doNothing().when(planService).deletePlan(eq(1L), eq(USER_ID));

        mvc.perform(delete("/api/plans/1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
