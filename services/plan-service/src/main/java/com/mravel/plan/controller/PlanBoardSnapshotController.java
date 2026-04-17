package com.mravel.plan.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.plan.dto.board.BoardResponse;
import com.mravel.plan.model.PlanRevisionLog;
import com.mravel.plan.repository.PlanRevisionLogRepository;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanBoardSnapshotController {

    /**
     * If gap exceeds this threshold, return a full snapshot instead of individual
     * events.
     */
    private static final int GAP_RECOVERY_MAX_EVENTS = 200;

    private final PlanBoardService boardService;
    private final PlanRevisionLogRepository revisionLogRepository;
    private final CurrentUserService currentUserService;
    private final ObjectMapper objectMapper;

    /**
     * Gap recovery: returns incremental events after {@code afterRevision}.
     * If {@code afterRevision} is absent or the gap is too large,
     * returns a full {@link BoardResponse} as a single-element events array.
     *
     * Response shape:
     * 
     * <pre>
     * {
     *   "mode": "incremental" | "full_snapshot",
     *   "currentRevision": 1043,
     *   "events": [ ...PlanBoardEventV2 JSON objects... ]   // for incremental
     *   "board": { ...BoardResponse... }                    // for full_snapshot
     * }
     * </pre>
     */
    @GetMapping("/{planId}/board/snapshot")
    public ResponseEntity<Map<String, Object>> getSnapshot(
            @PathVariable Long planId,
            @RequestParam(required = false) Long afterRevision,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Long userId = currentUserService.getId();

        // Permission check — canView with conservative isFriend=false
        BoardResponse fullBoard = boardService.getBoard(planId, userId, false);
        long currentRevision = fullBoard.getBoardRevision() != null ? fullBoard.getBoardRevision() : 0L;

        // No afterRevision or stale disconnection: return full snapshot
        if (afterRevision == null) {
            log.info("gap_recovery_triggered planId={} mode=full_snapshot reason=no_revision", planId);
            return ResponseEntity.ok(Map.of(
                    "mode", "full_snapshot",
                    "currentRevision", currentRevision,
                    "board", fullBoard));
        }

        // Gap too large: fall back to full snapshot
        long gapSize = revisionLogRepository.countByPlanIdAndRevisionGreaterThan(planId, afterRevision);
        if (gapSize > GAP_RECOVERY_MAX_EVENTS) {
            log.info("gap_recovery_triggered planId={} mode=full_snapshot reason=gap_too_large gapSize={}", planId,
                    gapSize);
            return ResponseEntity.ok(Map.of(
                    "mode", "full_snapshot",
                    "currentRevision", currentRevision,
                    "board", fullBoard));
        }

        // Incremental: return events from revision log
        List<PlanRevisionLog> logEntries = revisionLogRepository.findByPlanIdAfterRevision(planId, afterRevision);

        List<JsonNode> events = logEntries.stream()
                .map(entry -> {
                    try {
                        return objectMapper.readTree(entry.getEventEnvelope());
                    } catch (Exception e) {
                        log.warn("Failed to parse revision log entry id={}: {}", entry.getId(), e.getMessage());
                        return null;
                    }
                })
                .filter(node -> node != null)
                .toList();

        log.info("gap_recovery_triggered planId={} mode=incremental afterRevision={} eventCount={}",
                planId, afterRevision, events.size());

        return ResponseEntity.ok(Map.of(
                "mode", "incremental",
                "currentRevision", currentRevision,
                "events", events));
    }
}
