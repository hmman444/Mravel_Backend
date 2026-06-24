package com.mravel.chat.controller;

import com.mravel.chat.client.UserClient;
import com.mravel.chat.service.ConversationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConversationController.class)
@AutoConfigureMockMvc(addFilters = false)
class ConversationControllerTest {

    @Autowired MockMvc mvc;
    @MockBean ConversationService conversationService;
    @MockBean UserClient userClient;

    private static final String BASE = "/api/chat/conversations";
    private static final String USER_HEADER = "X-User-Id";

    // ── POST /api/chat/conversations/private ──────────────────────────────────

    @Test
    void createPrivate_returns200() throws Exception {
        when(conversationService.findOrCreatePrivate(1L, 2L)).thenReturn(null);

        mvc.perform(post(BASE + "/private")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipientId\": 2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/chat/conversations/group ────────────────────────────────────

    @Test
    void createGroup_returns200() throws Exception {
        when(conversationService.createGroup(eq(1L), any())).thenReturn(null);

        mvc.perform(post(BASE + "/group")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Team Chat\", \"memberIds\": [2, 3]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tạo nhóm thành công"));
    }

    // ── GET /api/chat/conversations ───────────────────────────────────────────

    @Test
    void list_returns200() throws Exception {
        when(conversationService.listConversations(1L)).thenReturn(List.of());

        mvc.perform(get(BASE).header(USER_HEADER, "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    // ── GET /api/chat/conversations/{id} ──────────────────────────────────────

    @Test
    void getDetail_returns200() throws Exception {
        when(conversationService.getDetail(1L, 1L)).thenReturn(null);

        mvc.perform(get(BASE + "/1").header(USER_HEADER, "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/chat/conversations/{id}/name ───────────────────────────────

    @Test
    void rename_returns200() throws Exception {
        when(conversationService.renameGroup(eq(1L), eq(1L), eq("New Name"))).thenReturn(null);

        mvc.perform(patch(BASE + "/1/name")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã đổi tên nhóm"));
    }

    // ── POST /api/chat/conversations/{id}/members ─────────────────────────────

    @Test
    void addMembers_returns200() throws Exception {
        doNothing().when(conversationService).addMembers(eq(1L), eq(1L), any());

        mvc.perform(post(BASE + "/1/members")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userIds\": [2, 3]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã thêm thành viên"));
    }

    // ── DELETE /api/chat/conversations/{id}/members/{targetUserId} ────────────

    @Test
    void removeMember_returns200() throws Exception {
        doNothing().when(conversationService).removeMember(1L, 1L, 2L);

        mvc.perform(delete(BASE + "/1/members/2").header(USER_HEADER, "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã xóa thành viên"));
    }

    // ── DELETE /api/chat/conversations/{id}/leave ─────────────────────────────

    @Test
    void leave_returns200() throws Exception {
        doNothing().when(conversationService).leaveConversation(1L, 1L);

        mvc.perform(delete(BASE + "/1/leave").header(USER_HEADER, "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã rời nhóm"));
    }

    // ── PATCH /api/chat/conversations/{id}/members/{targetUserId}/role ────────

    @Test
    void changeMemberRole_returns200() throws Exception {
        doNothing().when(conversationService).changeMemberRole(eq(1L), eq(1L), eq(2L), any());

        mvc.perform(patch(BASE + "/1/members/2/role")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\": \"MEMBER\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã thay đổi vai trò"));
    }

    // ── PATCH /api/chat/conversations/{id}/transfer ───────────────────────────

    @Test
    void transferOwnership_returns200() throws Exception {
        doNothing().when(conversationService).transferOwnership(1L, 1L, 2L);

        mvc.perform(patch(BASE + "/1/transfer")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"newOwnerId\": 2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã chuyển quyền chủ nhóm"));
    }

    // ── GET /api/chat/conversations/{id}/access-check ─────────────────────────

    @Test
    void accessCheck_canAccess_returnsTrue() throws Exception {
        when(conversationService.hasAccess(1L, 1L)).thenReturn(true);

        mvc.perform(get(BASE + "/1/access-check").param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canAccess").value(true));
    }

    @Test
    void accessCheck_noAccess_returnsFalse() throws Exception {
        when(conversationService.hasAccess(1L, 99L)).thenReturn(false);

        mvc.perform(get(BASE + "/1/access-check").param("userId", "99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canAccess").value(false));
    }
}
