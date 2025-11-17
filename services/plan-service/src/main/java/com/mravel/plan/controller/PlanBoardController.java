package com.mravel.plan.controller;

import com.mravel.plan.dto.board.*;
import com.mravel.plan.model.PlanRole;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import com.mravel.plan.service.PlanPermissionService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans/{planId}/board")
@RequiredArgsConstructor
public class PlanBoardController {

    private final PlanBoardService service;
    private final CurrentUserService currentUser;
    private final PlanPermissionService permissionService;

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

    @GetMapping("/share")
    public ShareResponse getShareInfo(@PathVariable Long planId) {
        Long userId = currentUser.getId();
        return service.getShareInfo(planId, userId);
    }

    @PatchMapping("/members/role")
    public ResponseEntity<Void> updateRole(
            @PathVariable Long planId,
            @RequestBody UpdateMemberRoleRequest req) {

        Long ownerId = currentUser.getId();
        service.updateMemberRole(planId, ownerId, req);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/members")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long planId,
            @RequestBody RemoveMemberRequest req) {

        Long ownerId = currentUser.getId();
        service.removeMember(planId, ownerId, req.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/requests")
    public PlanRequestDto requestAccess(
            @PathVariable Long planId,
            @RequestBody PlanRequestCreate req) {
        Long userId = currentUser.getId();
        return service.requestAccess(planId, userId, req);
    }

    @GetMapping("/requests")
    public List<PlanRequestDto> getRequests(@PathVariable Long planId) {
        Long ownerId = currentUser.getId();
        permissionService.checkPermission(planId, ownerId, PlanRole.OWNER);
        return service.getRequests(planId);
    }

    @PatchMapping("/requests/{reqId}")
    public ResponseEntity<?> handleRequest(
            @PathVariable Long planId,
            @PathVariable Long reqId,
            @RequestBody PlanRequestAction req) {
        Long ownerId = currentUser.getId();
        permissionService.checkPermission(planId, ownerId, PlanRole.OWNER);

        service.handleRequest(planId, reqId, req);

        return ResponseEntity.noContent().build();
    }

}
