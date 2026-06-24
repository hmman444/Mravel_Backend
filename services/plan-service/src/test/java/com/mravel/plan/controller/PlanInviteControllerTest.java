package com.mravel.plan.controller;

import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanInviteController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanInviteControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean PlanBoardService service;

    private static final Long USER_ID = 1L;
    private static final String BEARER = "Bearer test-token";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
    }

    // ── POST /api/plans/join ──────────────────────────────────────────────────

    @Test
    void joinPlan_success_returnsPlanId() throws Exception {
        when(service.joinPlan(eq("invite-token-abc"), eq(USER_ID))).thenReturn(42L);

        mvc.perform(post("/api/plans/join")
                        .header("Authorization", BEARER)
                        .param("token", "invite-token-abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.planId").value(42));
    }
}
