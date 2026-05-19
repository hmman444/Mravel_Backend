package com.mravel.chat.controller;

import com.mravel.chat.dto.ChatDtos.*;
import com.mravel.chat.model.ConversationMember.MemberRole;
import com.mravel.chat.service.ConversationService;
import com.mravel.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping("/private")
    public ResponseEntity<ApiResponse<ConversationResponse>> createPrivate(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CreatePrivateRequest req) {
        return ResponseEntity.ok(ApiResponse.success("OK",
                conversationService.findOrCreatePrivate(userId, req.getRecipientId())));
    }

    @PostMapping("/group")
    public ResponseEntity<ApiResponse<ConversationResponse>> createGroup(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CreateGroupRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Tạo nhóm thành công",
                conversationService.createGroup(userId, req)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ConversationResponse>>> list(
            @RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(ApiResponse.success("OK",
                conversationService.listConversations(userId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConversationResponse>> getDetail(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("OK",
                conversationService.getDetail(id, userId)));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<ApiResponse<ConversationResponse>> rename(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @Valid @RequestBody UpdateGroupNameRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Đã đổi tên nhóm",
                conversationService.renameGroup(id, userId, req.getName())));
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<ApiResponse<Void>> addMembers(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @Valid @RequestBody AddMembersRequest req) {
        conversationService.addMembers(id, userId, req.getUserIds());
        return ResponseEntity.ok(ApiResponse.success("Đã thêm thành viên", null));
    }

    @DeleteMapping("/{id}/members/{targetUserId}")
    public ResponseEntity<ApiResponse<Void>> removeMember(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @PathVariable Long targetUserId) {
        conversationService.removeMember(id, userId, targetUserId);
        return ResponseEntity.ok(ApiResponse.success("Đã xóa thành viên", null));
    }

    @DeleteMapping("/{id}/leave")
    public ResponseEntity<ApiResponse<Void>> leave(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id) {
        conversationService.leaveConversation(id, userId);
        return ResponseEntity.ok(ApiResponse.success("Đã rời nhóm", null));
    }

    @PatchMapping("/{id}/members/{targetUserId}/role")
    public ResponseEntity<ApiResponse<Void>> changeMemberRole(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @PathVariable Long targetUserId,
            @Valid @RequestBody ChangeMemberRoleRequest req) {
        conversationService.changeMemberRole(id, userId, targetUserId, req.getRole());
        return ResponseEntity.ok(ApiResponse.success("Đã thay đổi vai trò", null));
    }

    @PatchMapping("/{id}/transfer")
    public ResponseEntity<ApiResponse<Void>> transferOwnership(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @Valid @RequestBody TransferOwnershipRequest req) {
        conversationService.transferOwnership(id, userId, req.getNewOwnerId());
        return ResponseEntity.ok(ApiResponse.success("Đã chuyển quyền chủ nhóm", null));
    }

    /** Used by realtime-service to check if a user can subscribe to a conversation topic. */
    @GetMapping("/{id}/access-check")
    public ResponseEntity<AccessCheckResponse> accessCheck(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return ResponseEntity.ok(new AccessCheckResponse(conversationService.hasAccess(id, userId)));
    }
}
