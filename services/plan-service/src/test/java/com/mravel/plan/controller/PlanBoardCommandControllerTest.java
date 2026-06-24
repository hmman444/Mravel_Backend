package com.mravel.plan.controller;

import com.mravel.plan.dto.command.CommandResponse;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.IdempotencyService;
import com.mravel.plan.service.PlanBoardCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanBoardCommandController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanBoardCommandControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUser;
    @MockBean PlanBoardCommandService commandService;
    @MockBean IdempotencyService idempotencyService;

    private static final Long USER_ID = 1L;
    private static final Long PLAN_ID = 10L;
    private static final String BEARER = "Bearer test-token";
    private static final String BASE = "/api/plans/10/board/cmd";

    private final CommandResponse cmdResponse = CommandResponse.builder()
            .entityType("LIST").entityId(1L).operationType("CREATE").build();

    @BeforeEach
    void setUp() {
        when(currentUser.getId()).thenReturn(USER_ID);
        when(idempotencyService.findCached(anyString())).thenReturn(Optional.empty());
        doNothing().when(idempotencyService).store(anyString(), any(), any());
    }

    // ── POST /api/plans/{planId}/board/cmd/lists ──────────────────────────────

    @Test
    void createList_success_returns201() throws Exception {
        when(commandService.createList(eq(PLAN_ID), eq(USER_ID), any(), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(post(BASE + "/lists")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Danh sách 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void createList_idempotentKey_returns200Cached() throws Exception {
        when(idempotencyService.findCached(eq("key-123"))).thenReturn(Optional.of(cmdResponse));

        mvc.perform(post(BASE + "/lists")
                        .header("Authorization", BEARER)
                        .header("Idempotency-Key", "key-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Danh sách 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/cmd/lists/{listId}/rename ────────────

    @Test
    void renameList_success_returns200() throws Exception {
        when(commandService.renameList(eq(PLAN_ID), eq(1L), eq(USER_ID), any(), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(patch(BASE + "/lists/1/rename")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Tên mới\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/board/cmd/lists/{listId} ──────────────────

    @Test
    void deleteList_success_returns200() throws Exception {
        when(commandService.deleteList(eq(PLAN_ID), eq(1L), eq(USER_ID), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(delete(BASE + "/lists/1").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/cmd/lists/reorder ────────────────────

    @Test
    void reorderLists_success_returns200() throws Exception {
        when(commandService.reorderLists(eq(PLAN_ID), eq(USER_ID), any(), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(patch(BASE + "/lists/reorder")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"listId\":1,\"position\":0}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── POST /api/plans/{planId}/board/cmd/lists/{listId}/cards ──────────────

    @Test
    void createCard_success_returns201() throws Exception {
        when(commandService.createCard(eq(PLAN_ID), eq(1L), eq(USER_ID), any(), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(post(BASE + "/lists/1/cards")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Thẻ mới\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/cmd/lists/{listId}/cards/{cardId} ─────

    @Test
    void updateCard_success_returns200() throws Exception {
        when(commandService.updateCard(eq(PLAN_ID), eq(1L), eq(2L), eq(USER_ID), any(), any(), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(patch(BASE + "/lists/1/cards/2")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── DELETE /api/plans/{planId}/board/cmd/lists/{listId}/cards/{cardId} ────

    @Test
    void deleteCard_success_returns200() throws Exception {
        when(commandService.deleteCard(eq(PLAN_ID), eq(1L), eq(2L), eq(USER_ID), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(delete(BASE + "/lists/1/cards/2").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/cmd/cards/{cardId}/done ──────────────

    @Test
    void toggleDone_success_returns200() throws Exception {
        when(commandService.toggleDone(eq(PLAN_ID), isNull(), eq(3L), eq(USER_ID), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(patch(BASE + "/cards/3/done").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/cmd/cards/{cardId}/move ──────────────

    @Test
    void moveCard_success_returns200() throws Exception {
        when(commandService.moveCard(eq(PLAN_ID), eq(3L), eq(USER_ID), any(), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(patch(BASE + "/cards/3/move")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetListId\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── PATCH /api/plans/{planId}/board/cmd/lists/{listId}/cards/reorder ─────

    @Test
    void reorderCards_success_returns200() throws Exception {
        when(commandService.reorderCardsInList(eq(PLAN_ID), eq(1L), eq(USER_ID), any(), anyString()))
                .thenReturn(cmdResponse);

        mvc.perform(patch(BASE + "/lists/1/cards/reorder")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"cardId\":2,\"position\":0}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
