package com.mravel.plan.controller;

import com.mravel.plan.dto.board.*;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans/{planId}/board")
@RequiredArgsConstructor
public class PlanBoardController {

    private final PlanBoardService service;
    private final CurrentUserService currentUser;

    // board
    // Viewer, Editor, Owner
    @GetMapping
    public BoardResponse getBoard(@PathVariable Long planId,
            @RequestParam(defaultValue = "false") boolean isFriend) {
        Long userId = currentUser.getId();
        return service.getBoard(planId, userId, isFriend);
    }

    // list
    // Owner, Editor
    @PostMapping("/lists")
    public ListDto createList(@PathVariable Long planId,
            @RequestBody CreateListRequest req) {
        Long userId = currentUser.getId();
        return service.createList(planId, userId, req);
    }

    @PutMapping("/lists/{listId}/rename")
    public ResponseEntity<Void> renameList(@PathVariable Long planId,
            @PathVariable Long listId,
            @RequestBody RenameListRequest req) {
        Long userId = currentUser.getId();
        service.renameList(planId, listId, userId, req);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/lists/{listId}")
    public ResponseEntity<Void> deleteList(@PathVariable Long planId,
            @PathVariable Long listId) {
        Long userId = currentUser.getId();
        service.deleteList(planId, userId, listId);
        return ResponseEntity.noContent().build();
    }

    // card
    @PostMapping("/lists/{listId}/cards")
    public CardDto createCard(@PathVariable Long planId,
            @PathVariable Long listId,
            @RequestBody CreateCardRequest req) {
        Long userId = currentUser.getId();
        return service.createCard(planId, listId, userId, req);
    }

    @PutMapping("/lists/{listId}/cards/{cardId}")
    public CardDto updateCard(@PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @RequestBody UpdateCardRequest req) {
        Long userId = currentUser.getId();
        return service.updateCard(planId, listId, cardId, userId, req);
    }

    @PatchMapping("/lists/{listId}/cards/{cardId}/toggle")
    public CardDto toggleDone(@PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {
        Long userId = currentUser.getId();
        return service.toggleDone(planId, listId, cardId, userId);
    }

    @DeleteMapping("/lists/{listId}/cards/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {
        Long userId = currentUser.getId();
        service.deleteCard(planId, listId, cardId, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lists/{listId}/cards/{cardId}/duplicate")
    public CardDto duplicateCard(
            @PathVariable Long planId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {
        Long userId = currentUser.getId();
        return service.duplicateCard(planId, listId, cardId, userId);
    }

    // drad drop
    @PostMapping("/reorder")
    public BoardResponse reorder(@PathVariable Long planId,
            @RequestParam(defaultValue = "false") boolean isFriend,
            @RequestBody ReorderRequest req) {
        Long userId = currentUser.getId();
        return service.reorder(planId, userId, isFriend, req);
    }

    // labels
    @PostMapping("/labels")
    public LabelDto upsertLabel(@PathVariable Long planId,
            @RequestBody LabelUpsertRequest req) {
        Long userId = currentUser.getId();
        return service.upsertLabel(planId, userId, req);
    }

    @DeleteMapping("/labels/{labelId}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long planId,
            @PathVariable Long labelId) {
        Long userId = currentUser.getId();
        service.deleteLabel(planId, labelId, userId);
        return ResponseEntity.noContent().build();
    }

    // Owner
    // invites
    @PostMapping("/invites")
    public java.util.List<InviteDto> invite(@PathVariable Long planId,
            @RequestBody InviteRequest req) {
        Long userId = currentUser.getId();
        return service.invite(planId, userId, req);
    }
}
