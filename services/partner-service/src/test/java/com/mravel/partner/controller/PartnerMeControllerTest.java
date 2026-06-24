package com.mravel.partner.controller;

import com.mravel.partner.security.CurrentPartnerService;
import com.mravel.partner.security.JwtUserPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartnerMeController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerMeControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentPartnerService currentPartnerService;

    // ── GET /api/partner/me ───────────────────────────────────────────────────

    @Test
    void me_success_returnsPrincipal() throws Exception {
        var principal = new JwtUserPrincipal(42L, "partner@test.com", "PARTNER", "Test Partner", null);
        when(currentPartnerService.getPrincipalOrThrow()).thenReturn(principal);

        mvc.perform(get("/api/partner/me").header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(42))
                .andExpect(jsonPath("$.data.email").value("partner@test.com"))
                .andExpect(jsonPath("$.data.role").value("PARTNER"));
    }

    @Test
    void me_notAuthenticated_returns401() throws Exception {
        when(currentPartnerService.getPrincipalOrThrow()).thenThrow(new SecurityException("Unauthenticated"));

        mvc.perform(get("/api/partner/me").header("Authorization", "Bearer bad-token"))
                .andExpect(status().isUnauthorized());
    }
}
