package com.mravel.realtime.presence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PresenceController.class)
@AutoConfigureMockMvc(addFilters = false)
class PresenceControllerTest {

    @Autowired MockMvc mvc;
    @MockBean UserPresenceRegistry registry;
    @MockBean PresenceBlockGuard blockGuard;

    private static final String URL = "/api/realtime/presence";

    // ── GET /api/realtime/presence (thành công) ───────────────────────────────

    @Test
    void getOnlineUsers_someOnline_returnsJsonArray() throws Exception {
        when(registry.filterOnline(anyList())).thenReturn(new HashSet<>(Set.of(1L, 3L)));

        mvc.perform(get(URL).param("userIds", "1", "2", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getOnlineUsers_noneOnline_returnsEmptyArray() throws Exception {
        when(registry.filterOnline(anyList())).thenReturn(new HashSet<>());

        mvc.perform(get(URL).param("userIds", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // ── Thiếu param userIds → GlobalExceptionHandler catch-all trả 500 ────────
    // (MissingServletRequestParameterException bị ExceptionHandlerResolver bắt trước
    //  DefaultHandlerExceptionResolver, nên rơi vào handleOther → 500)

    @Test
    void getOnlineUsers_missingUserIds_returnsError() throws Exception {
        mvc.perform(get(URL))
                .andExpect(status().is5xxServerError());
    }

    // ── Có X-User-Id, người bị chặn bị lọc ra ────────────────────────────────

    @Test
    void getOnlineUsers_withViewerId_blockedUserFiltered() throws Exception {
        Set<Long> online = new HashSet<>();
        online.add(1L);
        online.add(2L);
        when(registry.filterOnline(anyList())).thenReturn(online);
        when(blockGuard.isBlocked(eq(10L), eq(2L))).thenReturn(true);
        when(blockGuard.isBlocked(eq(10L), eq(1L))).thenReturn(false);

        mvc.perform(get(URL)
                        .param("userIds", "1", "2")
                        .header("X-User-Id", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").value(1));
    }

    @Test
    void getOnlineUsers_withViewerId_noBlock_returnsAll() throws Exception {
        when(registry.filterOnline(anyList())).thenReturn(new HashSet<>(Set.of(1L, 2L)));
        when(blockGuard.isBlocked(eq(10L), anyLong())).thenReturn(false);

        mvc.perform(get(URL)
                        .param("userIds", "1", "2")
                        .header("X-User-Id", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
