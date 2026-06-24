package com.mravel.plan.controller;

import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.ReviewEligibilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanExperienceController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanExperienceControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean ReviewEligibilityService eligibilityService;

    private static final Long USER_ID = 1L;
    private static final String URL = "/api/plans/internal/experienced";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
    }

    // ── GET /api/plans/internal/experienced ───────────────────────────────────

    @Test
    void experienced_eligible_returnsEligibleTrue() throws Exception {
        var result = new ReviewEligibilityService.ExperienceResult(true, LocalDate.of(2026, 1, 15));
        when(eligibilityService.check(eq(USER_ID), eq("h-001"), any(), any())).thenReturn(result);

        mvc.perform(get(URL)
                        .param("targetType", "hotel")
                        .param("targetId", "h-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.eligible").value(true));
    }

    @Test
    void experienced_notEligible_returnsEligibleFalse() throws Exception {
        var result = new ReviewEligibilityService.ExperienceResult(false, (LocalDate) null);
        when(eligibilityService.check(eq(USER_ID), eq("h-999"), any(), any())).thenReturn(result);

        mvc.perform(get(URL)
                        .param("targetType", "hotel")
                        .param("targetId", "h-999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }
}
