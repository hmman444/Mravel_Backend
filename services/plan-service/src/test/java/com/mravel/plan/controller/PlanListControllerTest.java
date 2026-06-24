package com.mravel.plan.controller;

import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanListController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanListControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean PlanListService service;

    private static final Long USER_ID = 1L;
    private static final String BEARER = "Bearer test-token";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
    }

    // ── GET /api/plans/my ─────────────────────────────────────────────────────

    @Test
    void getMyPlans_success_returnsList() throws Exception {
        when(service.getMyPlans(eq(USER_ID))).thenReturn(List.of());

        mvc.perform(get("/api/plans/my").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
