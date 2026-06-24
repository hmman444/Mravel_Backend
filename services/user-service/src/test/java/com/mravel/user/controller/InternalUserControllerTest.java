package com.mravel.user.controller;

import com.mravel.user.service.BlockService;
import com.mravel.user.service.FriendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InternalUserController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = "mravel.internal.secret=mravel-internal-2026")
class InternalUserControllerTest {

    @Autowired MockMvc mvc;
    @MockBean FriendService friendService;
    @MockBean BlockService blockService;

    private static final String VALID_SECRET = "mravel-internal-2026";
    private static final String HEADER = "X-Internal-Secret";

    // ── GET /api/users/internal/visibility-context/{userId} ───────────────────

    @Test
    void visibilityContext_validSecret_returns200() throws Exception {
        when(blockService.getAllBlockedIds(1L)).thenReturn(List.of());
        when(friendService.getFriendIds(1L)).thenReturn(List.of(2L, 3L));

        mvc.perform(get("/api/users/internal/visibility-context/1")
                        .header(HEADER, VALID_SECRET))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.friendIds").isArray());
    }

    @Test
    void visibilityContext_wrongSecret_denied() throws Exception {
        // ResponseStatusException(FORBIDDEN) bị handleOther(Exception) bắt → 500
        mvc.perform(get("/api/users/internal/visibility-context/1")
                        .header(HEADER, "wrong-secret"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void visibilityContext_missingSecret_denied() throws Exception {
        mvc.perform(get("/api/users/internal/visibility-context/1"))
                .andExpect(status().is5xxServerError());
    }

    // ── GET /api/users/internal/block-check ──────────────────────────────────

    @Test
    void blockCheck_validSecret_notBlocked_returns200() throws Exception {
        when(blockService.isBlocked(1L, 2L)).thenReturn(false);

        mvc.perform(get("/api/users/internal/block-check")
                        .header(HEADER, VALID_SECRET)
                        .param("a", "1").param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(false));
    }

    @Test
    void blockCheck_wrongSecret_denied() throws Exception {
        mvc.perform(get("/api/users/internal/block-check")
                        .header(HEADER, "bad")
                        .param("a", "1").param("b", "2"))
                .andExpect(status().is5xxServerError());
    }
}
