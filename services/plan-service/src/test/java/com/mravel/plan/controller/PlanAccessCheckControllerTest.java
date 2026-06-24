package com.mravel.plan.controller;

import com.mravel.plan.service.PlanPermissionService;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(PlanAccessCheckController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanAccessCheckControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean PlanPermissionService permissionService;

    private static final String URL = "/api/plans/10/board/access-check";

    // ── GET /api/plans/{planId}/board/access-check ────────────────────────────

    @Test
    void check_canAccess_returnsTrue() throws Exception {
        when(permissionService.canView(eq(10L), eq(1L), eq(false))).thenReturn(true);

        mvc.perform(get(URL).param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canAccess").value(true));
    }

    @Test
    void check_cannotAccess_returnsFalse() throws Exception {
        when(permissionService.canView(eq(10L), eq(99L), eq(false))).thenReturn(false);

        mvc.perform(get(URL).param("userId", "99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canAccess").value(false));
    }
}
