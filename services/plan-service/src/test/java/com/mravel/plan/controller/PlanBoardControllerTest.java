package com.mravel.plan.controller;

import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import com.mravel.plan.service.PlanPermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanBoardController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanBoardControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean PlanBoardService service;
    @MockBean PlanPermissionService permissionService;

    private static final Long USER_ID = 1L;
    private static final Long PLAN_ID = 10L;
    private static final String BEARER = "Bearer test-token";
    private static final String BASE = "/api/plans/10/board";

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
    }

    // ── GET /api/plans/{planId}/board ─────────────────────────────────────────

    @Test
    void getBoard_success_returnsBoard() throws Exception {
        when(service.getBoard(eq(PLAN_ID), eq(USER_ID), any())).thenReturn(null);

        mvc.perform(get(BASE).header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/lists ──────────────────────────────────

    @Test
    void createList_success_returnsList() throws Exception {
        when(service.createList(eq(PLAN_ID), eq(USER_ID), any())).thenReturn(null);

        mvc.perform(post(BASE + "/lists")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Danh sách 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PUT /api/plans/{planId}/board/lists/{listId}/rename ───────────────────

    @Test
    void renameList_success_returns200() throws Exception {
        doNothing().when(service).renameList(eq(PLAN_ID), eq(1L), eq(USER_ID), any());

        mvc.perform(put(BASE + "/lists/1/rename")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Tên mới\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/board/lists/{listId} ───────────────────────

    @Test
    void deleteList_success_returns200() throws Exception {
        doNothing().when(service).deleteList(eq(PLAN_ID), eq(USER_ID), eq(1L));

        mvc.perform(delete(BASE + "/lists/1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/lists/{listId}/duplicate ──────────────

    @Test
    void duplicateList_success_returnsList() throws Exception {
        when(service.duplicateList(eq(PLAN_ID), eq(USER_ID), eq(1L))).thenReturn(null);

        mvc.perform(post(BASE + "/lists/1/duplicate").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/lists/{listId}/cards ───────────────────

    @Test
    void createCard_success_returnsCard() throws Exception {
        when(service.createCard(eq(PLAN_ID), eq(1L), eq(USER_ID), any())).thenReturn(null);

        mvc.perform(post(BASE + "/lists/1/cards")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Thẻ 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PUT /api/plans/{planId}/board/lists/{listId}/cards/{cardId} ───────────

    @Test
    void updateCard_success_returnsCard() throws Exception {
        when(service.updateCard(eq(PLAN_ID), eq(1L), eq(2L), eq(USER_ID), any())).thenReturn(null);

        mvc.perform(put(BASE + "/lists/1/cards/2")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/board/lists/{listId}/cards/{cardId} ────────

    @Test
    void deleteCard_success_returns200() throws Exception {
        doNothing().when(service).deleteCard(eq(PLAN_ID), eq(1L), eq(2L), eq(USER_ID));

        mvc.perform(delete(BASE + "/lists/1/cards/2").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/board/trash/cards ──────────────────────────

    @Test
    void clearTrash_success_returns200() throws Exception {
        doNothing().when(service).clearTrash(eq(PLAN_ID), eq(USER_ID));

        mvc.perform(delete(BASE + "/trash/cards").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/lists/{listId}/cards/{cardId}/duplicate ─

    @Test
    void duplicateCard_success_returnsCard() throws Exception {
        when(service.duplicateCard(eq(PLAN_ID), eq(1L), eq(2L), eq(USER_ID))).thenReturn(null);

        mvc.perform(post(BASE + "/lists/1/cards/2/duplicate").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/reorder ────────────────────────────────

    @Test
    void reorder_success_returnsBoard() throws Exception {
        when(service.reorder(eq(PLAN_ID), eq(USER_ID), anyBoolean(), any())).thenReturn(null);

        mvc.perform(post(BASE + "/reorder")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lists\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/invites ────────────────────────────────

    @Test
    void invite_success_returnsInvites() throws Exception {
        when(service.invite(eq(PLAN_ID), eq(USER_ID), any())).thenReturn(List.of());

        mvc.perform(post(BASE + "/invites")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userIds\":[2,3]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/{planId}/board/share ───────────────────────────────────

    @Test
    void getShareInfo_success_returnsShareInfo() throws Exception {
        when(service.getShareInfo(eq(PLAN_ID), eq(USER_ID))).thenReturn(null);

        mvc.perform(get(BASE + "/share").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/members/role ──────────────────────────

    @Test
    void updateRole_success_returns200() throws Exception {
        doNothing().when(service).updateMemberRole(eq(PLAN_ID), eq(USER_ID), any());

        mvc.perform(patch(BASE + "/members/role")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":2,\"role\":\"EDITOR\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/board/members ──────────────────────────────

    @Test
    void removeMember_success_returns200() throws Exception {
        doNothing().when(service).removeMember(eq(PLAN_ID), eq(USER_ID), any());

        mvc.perform(delete(BASE + "/members")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/requests ───────────────────────────────

    @Test
    void requestAccess_success_returnsRequest() throws Exception {
        when(service.requestAccess(eq(PLAN_ID), eq(USER_ID), any())).thenReturn(null);

        mvc.perform(post(BASE + "/requests")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"Tôi muốn tham gia\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/plans/{planId}/board/requests ────────────────────────────────

    @Test
    void getRequests_success_checksOwnerPermission() throws Exception {
        doNothing().when(permissionService).checkPermission(eq(PLAN_ID), eq(USER_ID), any());
        when(service.getRequests(eq(PLAN_ID))).thenReturn(List.of());

        mvc.perform(get(BASE + "/requests").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/requests/{reqId} ─────────────────────

    @Test
    void handleRequest_success_returns200() throws Exception {
        doNothing().when(permissionService).checkPermission(eq(PLAN_ID), eq(USER_ID), any());
        doNothing().when(service).handleRequest(eq(PLAN_ID), eq(5L), any());

        mvc.perform(patch(BASE + "/requests/5")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"action\":\"APPROVE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
