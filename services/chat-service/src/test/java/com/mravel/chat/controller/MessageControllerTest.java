package com.mravel.chat.controller;

import com.mravel.chat.client.UserClient;
import com.mravel.chat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MessageControllerTest {

    @Autowired MockMvc mvc;
    @MockBean MessageService messageService;
    @MockBean UserClient userClient;

    private static final String BASE = "/api/chat/conversations/1/messages";
    private static final String USER_HEADER = "X-User-Id";

    // ── POST /api/chat/conversations/{conversationId}/messages ────────────────

    @Test
    void send_returns200() throws Exception {
        when(messageService.sendMessage(eq(1L), eq(1L), any())).thenReturn(null);

        mvc.perform(post(BASE)
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Hello!\", \"messageType\": \"TEXT\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã gửi tin nhắn"));
    }

    @Test
    void send_missingContent_stillAccepted() throws Exception {
        when(messageService.sendMessage(eq(1L), eq(1L), any())).thenReturn(null);

        mvc.perform(post(BASE)
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mediaUrl\": \"https://img.example.com/photo.jpg\", \"messageType\": \"IMAGE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/chat/conversations/{conversationId}/messages ─────────────────

    @Test
    void getMessages_defaultParams_returns200() throws Exception {
        when(messageService.getMessages(eq(1L), eq(1L), isNull(), eq(30))).thenReturn(null);

        mvc.perform(get(BASE).header(USER_HEADER, "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void getMessages_withBefore_returns200() throws Exception {
        when(messageService.getMessages(eq(1L), eq(1L), eq(50L), eq(20))).thenReturn(null);

        mvc.perform(get(BASE)
                        .header(USER_HEADER, "1")
                        .param("before", "50")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/chat/conversations/{conversationId}/messages/seen ───────────

    @Test
    void markSeen_returns200() throws Exception {
        doNothing().when(messageService).markSeen(1L, 1L, 10L);

        mvc.perform(post(BASE + "/seen")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lastMessageId\": 10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/chat/conversations/{conversationId}/messages/typing ─────────

    @Test
    void typing_returns200() throws Exception {
        doNothing().when(messageService).sendTypingIndicator(1L, 1L);

        mvc.perform(post(BASE + "/typing").header(USER_HEADER, "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
