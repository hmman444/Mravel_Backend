package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.board.CreateCardRequest;
import com.mravel.plan.dto.board.CreateListRequest;
import com.mravel.plan.dto.board.RenameListRequest;
import com.mravel.plan.dto.board.UpdateCardRequest;
import com.mravel.plan.dto.command.*;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.IdempotencyService;
import com.mravel.plan.service.PlanBoardCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Phase 3d — granular command endpoints.
 *
 * All mutating endpoints:
 *  - accept an optional {@code Idempotency-Key} header (UUID string)
 *  - return a {@link CommandResponse} with patch-only payload, revision, and operationId
 *  - are idempotent: a repeated call with the same key returns the cached response
 *
 * Routes are under /api/plans/{planId}/board/cmd to coexist with the legacy
 * /api/plans/{planId}/board/* endpoints during the Phase 3–4 migration window.
 */
@Slf4j
@RestController
@RequestMapping("/api/plans/{planId}/board/cmd")
@RequiredArgsConstructor
public class PlanBoardCommandController {

    private final PlanBoardCommandService commandService;
    private final IdempotencyService idempotencyService;
    private final CurrentUserService currentUser;

    // ── List commands ──────────────────────────────────────────────────────────

    /**
     * POST /api/plans/{planId}/board/cmd/lists
     * Creates a new list. Returns CommandResponse with patch containing the created list.
     */
    @PostMapping("/lists")
    public ResponseEntity<ApiResponse<CommandResponse>> createList(
            @PathVariable Long planId,
            @RequestBody CreateListRequest req,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.createList(planId, userId, req, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo danh sách thành công", response));
    }

    /**
     * PATCH /api/plans/{planId}/board/cmd/lists/{listId}/rename
     * Renames a list. Returns CommandResponse with patch containing new title.
     */
    @PatchMapping("/lists/{listId}/rename")
    public ResponseEntity<ApiResponse<CommandResponse>> renameList(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @RequestBody RenameListRequest req,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.renameList(planId, listId, userId, req, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Đổi tên danh sách thành công", response));
    }

    /**
     * DELETE /api/plans/{planId}/board/cmd/lists/{listId}
     * Deletes a list and all its cards.
     */
    @DeleteMapping("/lists/{listId}")
    public ResponseEntity<ApiResponse<CommandResponse>> deleteList(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.deleteList(planId, listId, userId, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Xoá danh sách thành công", response));
    }

    /**
     * PATCH /api/plans/{planId}/board/cmd/lists/reorder
     * Bulk-reorders lists by setting explicit positions.
     */
    @PatchMapping("/lists/reorder")
    public ResponseEntity<ApiResponse<CommandResponse>> reorderLists(
            @PathVariable Long planId,
            @RequestBody List<ListPositionItem> positions,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.reorderLists(planId, userId, positions, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thứ tự danh sách thành công", response));
    }

    // ── Card commands ──────────────────────────────────────────────────────────

    /**
     * POST /api/plans/{planId}/board/cmd/lists/{listId}/cards
     * Creates a new card in the specified list.
     */
    @PostMapping("/lists/{listId}/cards")
    public ResponseEntity<ApiResponse<CommandResponse>> createCard(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @RequestBody CreateCardRequest req,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.createCard(planId, listId, userId, req, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo thẻ thành công", response));
    }

    /**
     * PATCH /api/plans/{planId}/board/cmd/lists/{listId}/cards/{cardId}
     * Updates a card's fields. Supports optimistic locking via If-Match header (card version).
     */
    @PatchMapping("/lists/{listId}/cards/{cardId}")
    public ResponseEntity<ApiResponse<CommandResponse>> updateCard(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @RequestBody UpdateCardRequest req,
            @RequestHeader(value = "If-Match", required = false) Long ifMatch,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.updateCard(planId, listId, cardId, userId, req, ifMatch, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thẻ thành công", response));
    }

    /**
     * DELETE /api/plans/{planId}/board/cmd/lists/{listId}/cards/{cardId}
     * Deletes (soft-deletes / moves to trash) a card.
     */
    @DeleteMapping("/lists/{listId}/cards/{cardId}")
    public ResponseEntity<ApiResponse<CommandResponse>> deleteCard(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.deleteCard(planId, listId, cardId, userId, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Xoá thẻ thành công", response));
    }

    /**
     * PATCH /api/plans/{planId}/board/cmd/cards/{cardId}/done
     * Toggles the done/undone state of a card.
     */
    @PatchMapping("/cards/{cardId}/done")
    public ResponseEntity<ApiResponse<CommandResponse>> toggleDone(
            @PathVariable Long planId,
            @PathVariable Long cardId,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        // toggleDone is NOT idempotent in the strict sense (toggle flips state on each call),
        // but we still honour an explicit Idempotency-Key to handle client retries after a timeout.
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.toggleDone(planId, null, cardId, userId, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật trạng thái thẻ thành công", response));
    }

    /**
     * PATCH /api/plans/{planId}/board/cmd/cards/{cardId}/move
     * Moves a card to a different list (and optional position). Supports optimistic locking via
     * {@code version} field in request body.
     */
    @PatchMapping("/cards/{cardId}/move")
    public ResponseEntity<ApiResponse<CommandResponse>> moveCard(
            @PathVariable Long planId,
            @PathVariable Long cardId,
            @RequestBody MoveCardRequest req,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.moveCard(planId, cardId, userId, req, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Di chuyển thẻ thành công", response));
    }

    /**
     * PATCH /api/plans/{planId}/board/cmd/lists/{listId}/cards/reorder
     * Bulk-reorders cards within a single list.
     */
    @PatchMapping("/lists/{listId}/cards/reorder")
    public ResponseEntity<ApiResponse<CommandResponse>> reorderCards(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @RequestBody List<CardPositionItem> positions,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        Long userId = currentUser.getId();
        String operationId = resolveOperationId(idempotencyKey);

        Optional<CommandResponse> cached = idempotencyService.findCached(operationId);
        if (cached.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success("OK (idempotent)", cached.get()));
        }

        CommandResponse response = commandService.reorderCardsInList(planId, listId, userId, positions, operationId);
        idempotencyService.store(operationId, planId, response);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thứ tự thẻ thành công", response));
    }

    // ── helper ─────────────────────────────────────────────────────────────────

    /**
     * Uses the caller-supplied key if present; otherwise generates a random UUID.
     * A random UUID means no deduplication, which is acceptable for calls without a key.
     */
    private String resolveOperationId(String idempotencyKey) {
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            return idempotencyKey.trim();
        }
        return UUID.randomUUID().toString();
    }
}
