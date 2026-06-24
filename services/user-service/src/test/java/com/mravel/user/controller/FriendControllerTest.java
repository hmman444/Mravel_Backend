package com.mravel.user.controller;

import com.mravel.user.dto.UserMiniResponse;
import com.mravel.user.model.RelationshipType;
import com.mravel.user.service.AuthTokenClient;
import com.mravel.user.service.BlockService;
import com.mravel.user.service.FriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FriendController.class)
@AutoConfigureMockMvc(addFilters = false)
class FriendControllerTest {

    @Autowired MockMvc mvc;
    @MockBean FriendService friendService;
    @MockBean BlockService blockService;
    @MockBean AuthTokenClient authTokenClient;

    private static final String AUTH = "Bearer test-token";
    private static final Long USER_ID = 1L;

    @BeforeEach
    void setUp() {
        when(authTokenClient.validateToken(anyString()))
                .thenReturn(Map.of("valid", true, "id", USER_ID.intValue()));
    }

    // ── POST /api/users/friends/requests ──────────────────────────────────────

    @Test
    void sendRequest_success_returns200() throws Exception {
        doNothing().when(friendService).sendRequest(eq(USER_ID), eq(2L));

        mvc.perform(post("/api/users/friends/requests")
                        .header("Authorization", AUTH)
                        .param("targetUserId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Gửi lời mời kết bạn thành công"));
    }

    @Test
    void sendRequest_selfFriend_returns500() throws Exception {
        doThrow(new RuntimeException("Không thể kết bạn với chính mình"))
                .when(friendService).sendRequest(eq(USER_ID), eq(USER_ID));

        mvc.perform(post("/api/users/friends/requests")
                        .header("Authorization", AUTH)
                        .param("targetUserId", USER_ID.toString()))
                .andExpect(status().is5xxServerError());
    }

    // ── POST /api/users/friends/{otherUserId}/accept ──────────────────────────

    @Test
    void acceptRequest_success_returns200() throws Exception {
        doNothing().when(friendService).acceptRequest(eq(USER_ID), eq(2L));

        mvc.perform(post("/api/users/friends/2/accept")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void acceptRequest_notFound_returns500() throws Exception {
        doThrow(new RuntimeException("Không tìm thấy lời mời bạn bè"))
                .when(friendService).acceptRequest(eq(USER_ID), eq(99L));

        mvc.perform(post("/api/users/friends/99/accept")
                        .header("Authorization", AUTH))
                .andExpect(status().is5xxServerError());
    }

    // ── DELETE /api/users/friends/{otherUserId} ───────────────────────────────

    @Test
    void removeFriend_success_returns200() throws Exception {
        doNothing().when(friendService).removeFriendOrCancel(eq(USER_ID), eq(2L));

        mvc.perform(delete("/api/users/friends/2")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/users/friends/relationship ───────────────────────────────────

    @Test
    void getRelationship_anonymous_returnsNone() throws Exception {
        when(friendService.getRelationship(isNull(), eq(2L))).thenReturn(RelationshipType.NONE);

        mvc.perform(get("/api/users/friends/relationship").param("profileUserId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("NONE"));
    }

    @Test
    void getRelationship_withToken_returnsFriend() throws Exception {
        when(friendService.getRelationship(eq(USER_ID), eq(2L))).thenReturn(RelationshipType.FRIEND);

        mvc.perform(get("/api/users/friends/relationship")
                        .header("Authorization", AUTH)
                        .param("profileUserId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("FRIEND"));
    }

    // ── GET /api/users/friends/ids ────────────────────────────────────────────

    @Test
    void getFriendIds_success_returns200() throws Exception {
        when(friendService.getFriendIds(USER_ID)).thenReturn(List.of(2L, 3L));

        mvc.perform(get("/api/users/friends/ids").header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray());
    }

    // ── GET /api/users/friends/list ───────────────────────────────────────────

    @Test
    void getFriendList_success_returns200() throws Exception {
        UserMiniResponse mini = UserMiniResponse.builder().id(2L).fullname("Friend B").build();
        when(friendService.getFriendList(USER_ID)).thenReturn(List.of(mini));

        mvc.perform(get("/api/users/friends/list").header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].fullname").value("Friend B"));
    }

    // ── POST /api/users/friends/block/{targetId} ──────────────────────────────

    @Test
    void blockUser_success_returns200() throws Exception {
        doNothing().when(blockService).blockUser(eq(USER_ID), eq(2L));

        mvc.perform(post("/api/users/friends/block/2").header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/users/friends/block/{targetId} ────────────────────────────

    @Test
    void unblockUser_success_returns200() throws Exception {
        doNothing().when(blockService).unblockUser(eq(USER_ID), eq(2L));

        mvc.perform(delete("/api/users/friends/block/2").header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/users/friends/blocked ────────────────────────────────────────

    @Test
    void listBlocked_success_returns200() throws Exception {
        when(blockService.listBlocked(USER_ID)).thenReturn(List.of());

        mvc.perform(get("/api/users/friends/blocked").header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    // ── GET /api/users/friends/block-status ──────────────────────────────────

    @Test
    void blockStatus_notBlocked_returns200WithFalse() throws Exception {
        when(blockService.isBlocked(USER_ID, 2L)).thenReturn(false);

        mvc.perform(get("/api/users/friends/block-status")
                        .header("Authorization", AUTH)
                        .param("targetId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(false));
    }
}
