package com.mravel.plan.controller;

import com.mravel.plan.dto.board.BoardResponse;
import com.mravel.plan.repository.PlanRevisionLogRepository;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanBoardSnapshotController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlanBoardSnapshotControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean CurrentUserService currentUserService;
    @MockBean PlanBoardService boardService;
    @MockBean PlanRevisionLogRepository revisionLogRepository;

    private static final Long USER_ID = 1L;
    private static final Long PLAN_ID = 10L;
    private static final String BEARER = "Bearer test-token";
    private static final String URL = "/api/plans/10/board/snapshot";

    private BoardResponse board;

    @BeforeEach
    void setUp() {
        when(currentUserService.getId()).thenReturn(USER_ID);

        board = mock(BoardResponse.class);
        when(board.getBoardRevision()).thenReturn(100L);
        when(boardService.getBoard(eq(PLAN_ID), eq(USER_ID), eq(false))).thenReturn(board);
    }

    // ── GET /api/plans/{planId}/board/snapshot (no afterRevision) ────────────

    @Test
    void snapshot_noAfterRevision_returnsFullSnapshot() throws Exception {
        mvc.perform(get(URL).header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mode").value("full_snapshot"))
                .andExpect(jsonPath("$.currentRevision").value(100));
    }

    // ── GET /api/plans/{planId}/board/snapshot (gap too large) ───────────────

    @Test
    void snapshot_gapTooLarge_returnsFullSnapshot() throws Exception {
        when(revisionLogRepository.countByPlanIdAndRevisionGreaterThan(eq(PLAN_ID), eq(50L)))
                .thenReturn(300L);

        mvc.perform(get(URL).header("Authorization", BEARER).param("afterRevision", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mode").value("full_snapshot"))
                .andExpect(jsonPath("$.currentRevision").value(100));
    }

    // ── GET /api/plans/{planId}/board/snapshot (incremental) ─────────────────

    @Test
    void snapshot_smallGap_returnsIncremental() throws Exception {
        when(revisionLogRepository.countByPlanIdAndRevisionGreaterThan(eq(PLAN_ID), eq(90L)))
                .thenReturn(5L);
        when(revisionLogRepository.findByPlanIdAfterRevision(eq(PLAN_ID), eq(90L)))
                .thenReturn(List.of());

        mvc.perform(get(URL).header("Authorization", BEARER).param("afterRevision", "90"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mode").value("incremental"))
                .andExpect(jsonPath("$.currentRevision").value(100));
    }
}
