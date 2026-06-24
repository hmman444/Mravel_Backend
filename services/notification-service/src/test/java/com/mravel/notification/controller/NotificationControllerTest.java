package com.mravel.notification.controller;

import com.mravel.notification.client.UserClient;
import com.mravel.notification.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
@AutoConfigureMockMvc(addFilters = false)
class NotificationControllerTest {

    @Autowired MockMvc mvc;
    @MockBean NotificationService service;
    @MockBean UserClient userClient;

    private static final String BASE = "/api/notifications";

    // ── POST /api/notifications (internal create) ─────────────────────────────

    @Test
    void create_returns200() throws Exception {
        when(service.create(any())).thenReturn(null);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipientId\":1,\"type\":\"BOOKING\",\"title\":\"Booking confirmed\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tạo notification thành công"));
    }

    // ── GET /api/notifications ────────────────────────────────────────────────

    @Test
    void list_noCallerId_returns200() throws Exception {
        when(service.list(eq(1L), isNull(), eq(1), eq(20)))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE).param("recipientId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    void list_callerMatchesRecipient_returns200() throws Exception {
        when(service.list(eq(1L), isNull(), eq(1), eq(20)))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE)
                        .header("X-User-Id", "1")
                        .param("recipientId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void list_callerMismatch_returns403() throws Exception {
        mvc.perform(get(BASE)
                        .header("X-User-Id", "2")
                        .param("recipientId", "1"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Bạn không có quyền xem thông báo này"));
    }

    @Test
    void list_withCategory_filters() throws Exception {
        when(service.list(eq(1L), eq("BOOKING"), eq(1), eq(20)))
                .thenReturn(new PageImpl<>(List.of()));

        mvc.perform(get(BASE)
                        .param("recipientId", "1")
                        .param("category", "BOOKING"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    // ── PATCH /api/notifications/{id}/read ────────────────────────────────────

    @Test
    void markRead_callerMatches_returns200() throws Exception {
        when(service.markRead(1L, 5L)).thenReturn(3L);

        mvc.perform(patch(BASE + "/5/read")
                        .header("X-User-Id", "1")
                        .param("recipientId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(3));
    }

    @Test
    void markRead_callerMismatch_returns403() throws Exception {
        mvc.perform(patch(BASE + "/5/read")
                        .header("X-User-Id", "2")
                        .param("recipientId", "1"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Forbidden"));
    }

    @Test
    void markRead_noCallerId_returns200() throws Exception {
        when(service.markRead(1L, 5L)).thenReturn(0L);

        mvc.perform(patch(BASE + "/5/read").param("recipientId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(0));
    }

    // ── PATCH /api/notifications/read-all ─────────────────────────────────────

    @Test
    void markAllRead_callerMatches_returns200() throws Exception {
        when(service.markAllRead(1L)).thenReturn(0L);

        mvc.perform(patch(BASE + "/read-all")
                        .header("X-User-Id", "1")
                        .param("recipientId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(0));
    }

    @Test
    void markAllRead_callerMismatch_returns403() throws Exception {
        mvc.perform(patch(BASE + "/read-all")
                        .header("X-User-Id", "2")
                        .param("recipientId", "1"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Forbidden"));
    }
}
