package com.mravel.plan.controller;

import com.mravel.plan.dto.board.*;
import com.mravel.plan.service.PlanBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans/{planId}/board")
@RequiredArgsConstructor
public class PlanBoardController {

    private final PlanBoardService service;

    /* ======== READ BOARD ======== */
    @GetMapping
    public BoardResponse getBoard(@PathVariable Long planId) {
        return service.getBoard(planId);
    }

    /* ======== LISTS ======== */
    @PostMapping("/lists")
    public ListDto createList(@PathVariable Long planId, @RequestBody CreateListRequest req) {
        return service.createList(planId, req);
    }

    @PutMapping("/lists/{listId}/rename")
    public ResponseEntity<Void> renameList(@PathVariable Long planId,
            @PathVariable Long listId,
            @RequestBody RenameListRequest req) {
        service.renameList(planId, listId, req);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/lists/{listId}")
    public ResponseEntity<Void> deleteList(@PathVariable Long planId, @PathVariable Long listId) {
        service.deleteList(planId, listId);
        return ResponseEntity.noContent().build();
    }

    /* ======== CARDS ======== */
    @PostMapping("/lists/{listId}/cards")
    public CardDto createCard(@PathVariable Long planId,
            @PathVariable Long listId,
            @RequestBody CreateCardRequest req) {
        return service.createCard(planId, listId, req);
    }

    @PutMapping("/lists/{listId}/cards/{cardId}")
    public CardDto updateCard(@PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @RequestBody UpdateCardRequest req) {
        return service.updateCard(planId, listId, cardId, req);
    }

    @PatchMapping("/lists/{listId}/cards/{cardId}/toggle")
    public CardDto toggleDone(@PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {
        UpdateCardRequest req = UpdateCardRequest.builder().done(true).build(); // sẽ flip trong service nếu muốn
        // Ở đây gọi updateCard với done = !current -> để đơn giản: service.updateCard
        // nhận done = true/false
        // Nhưng UI của bạn đã có state -> nên tốt hơn gửi đúng trạng thái mới.
        throw new UnsupportedOperationException("Hãy dùng PUT /cards/{id} với field done theo trạng thái mới.");
    }

    @DeleteMapping("/lists/{listId}/cards/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {
        service.deleteCard(planId, listId, cardId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lists/{listId}/cards/{cardId}/duplicate")
    public CardDto duplicateCard(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {
        return service.duplicateCard(planId, listId, cardId);
    }

    /* ======== DRAG & DROP ======== */
    @PostMapping("/reorder")
    public BoardResponse reorder(@PathVariable Long planId, @RequestBody ReorderRequest req) {
        return service.reorder(planId, req);
    }

    /* ======== LABELS ======== */
    @PostMapping("/labels")
    public LabelDto upsertLabel(@PathVariable Long planId, @RequestBody LabelUpsertRequest req) {
        return service.upsertLabel(planId, req);
    }

    @DeleteMapping("/labels/{labelId}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long planId, @PathVariable Long labelId) {
        service.deleteLabel(planId, labelId);
        return ResponseEntity.noContent().build();
    }

    /* ======== INVITES ======== */
    @PostMapping("/invites")
    public java.util.List<InviteDto> invite(@PathVariable Long planId, @RequestBody InviteRequest req) {
        return service.invite(planId, req);
    }
}
