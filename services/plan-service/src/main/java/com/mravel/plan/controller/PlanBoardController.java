package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.board.*;
import com.mravel.plan.model.PlanRole;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanBoardService;
import com.mravel.plan.service.PlanPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        public ResponseEntity<ApiResponse<BoardResponse>> getBoard(
                        @PathVariable Long planId,
                        @RequestParam(defaultValue = "false") boolean isFriend) {
                Long userId = currentUser.getId();
                BoardResponse board = service.getBoard(planId, userId, isFriend);
                return ResponseEntity.ok(
                                ApiResponse.success("Lấy board thành công", board));
        }

        // list
        // Owner, Editor
        @PostMapping("/lists")
        public ResponseEntity<ApiResponse<ListDto>> createList(
                        @PathVariable Long planId,
                        @RequestBody CreateListRequest req) {
                Long userId = currentUser.getId();
                ListDto list = service.createList(planId, userId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Tạo danh sách thành công", list));
        }

        @PutMapping("/lists/{listId}/rename")
        public ResponseEntity<ApiResponse<?>> renameList(
                        @PathVariable Long planId,
                        @PathVariable Long listId,
                        @RequestBody RenameListRequest req) {
                Long userId = currentUser.getId();
                service.renameList(planId, listId, userId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Đổi tên danh sách thành công", null));
        }

        @DeleteMapping("/lists/{listId}")
        public ResponseEntity<ApiResponse<?>> deleteList(
                        @PathVariable Long planId,
                        @PathVariable Long listId) {
                Long userId = currentUser.getId();
                service.deleteList(planId, userId, listId);
                return ResponseEntity.ok(
                                ApiResponse.success("Xoá danh sách thành công", null));
        }

        // card
        @PostMapping("/lists/{listId}/cards")
        public ResponseEntity<ApiResponse<CardDto>> createCard(
                        @PathVariable Long planId,
                        @PathVariable Long listId,
                        @RequestBody CreateCardRequest req) {
                Long userId = currentUser.getId();
                CardDto card = service.createCard(planId, listId, userId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Tạo thẻ thành công", card));
        }

        @PutMapping("/lists/{listId}/cards/{cardId}")
        public ResponseEntity<ApiResponse<CardDto>> updateCard(
                        @PathVariable Long planId,
                        @PathVariable Long listId,
                        @PathVariable Long cardId,
                        @RequestBody UpdateCardRequest req) {
                Long userId = currentUser.getId();
                CardDto card = service.updateCard(planId, listId, cardId, userId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Cập nhật thẻ thành công", card));
        }

        @PatchMapping("/lists/{listId}/cards/{cardId}/toggle")
        public ResponseEntity<ApiResponse<CardDto>> toggleDone(
                        @PathVariable Long planId,
                        @PathVariable Long listId,
                        @PathVariable Long cardId) {
                Long userId = currentUser.getId();
                CardDto card = service.toggleDone(planId, listId, cardId, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Cập nhật trạng thái thẻ thành công", card));
        }

        @DeleteMapping("/lists/{listId}/cards/{cardId}")
        public ResponseEntity<ApiResponse<?>> deleteCard(
                        @PathVariable Long planId,
                        @PathVariable Long listId,
                        @PathVariable Long cardId) {
                Long userId = currentUser.getId();
                service.deleteCard(planId, listId, cardId, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Xoá thẻ thành công", null));
        }

        @PostMapping("/lists/{listId}/cards/{cardId}/duplicate")
        public ResponseEntity<ApiResponse<CardDto>> duplicateCard(
                        @PathVariable Long planId,
                        @PathVariable Long listId,
                        @PathVariable Long cardId) {
                Long userId = currentUser.getId();
                CardDto card = service.duplicateCard(planId, listId, cardId, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Nhân bản thẻ thành công", card));
        }

        // drag drop
        @PostMapping("/reorder")
        public ResponseEntity<ApiResponse<BoardResponse>> reorder(
                        @PathVariable Long planId,
                        @RequestParam(defaultValue = "false") boolean isFriend,
                        @RequestBody ReorderRequest req) {
                Long userId = currentUser.getId();
                BoardResponse board = service.reorder(planId, userId, isFriend, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Cập nhật thứ tự thành công", board));
        }

        // Owner
        // invites
        @PostMapping("/invites")
        public ResponseEntity<ApiResponse<List<InviteDto>>> invite(
                        @PathVariable Long planId,
                        @RequestBody InviteRequest req) {
                Long userId = currentUser.getId();
                List<InviteDto> invites = service.invite(planId, userId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Gửi lời mời thành công", invites));
        }

        @GetMapping("/share")
        public ResponseEntity<ApiResponse<ShareResponse>> getShareInfo(
                        @PathVariable Long planId) {
                Long userId = currentUser.getId();
                ShareResponse shareInfo = service.getShareInfo(planId, userId);
                return ResponseEntity.ok(
                                ApiResponse.success("Lấy thông tin chia sẻ thành công", shareInfo));
        }

        @PatchMapping("/members/role")
        public ResponseEntity<ApiResponse<?>> updateRole(
                        @PathVariable Long planId,
                        @RequestBody UpdateMemberRoleRequest req) {
                Long ownerId = currentUser.getId();
                service.updateMemberRole(planId, ownerId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Cập nhật vai trò thành viên thành công", null));
        }

        @DeleteMapping("/members")
        public ResponseEntity<ApiResponse<?>> removeMember(
                        @PathVariable Long planId,
                        @RequestBody RemoveMemberRequest req) {
                Long ownerId = currentUser.getId();
                service.removeMember(planId, ownerId, req.getUserId());
                return ResponseEntity.ok(
                                ApiResponse.success("Xoá thành viên khỏi plan thành công", null));
        }

        @PostMapping("/requests")
        public ResponseEntity<ApiResponse<PlanRequestDto>> requestAccess(
                        @PathVariable Long planId,
                        @RequestBody PlanRequestCreate req) {
                Long userId = currentUser.getId();
                PlanRequestDto dto = service.requestAccess(planId, userId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Gửi yêu cầu truy cập thành công", dto));
        }

        @GetMapping("/requests")
        public ResponseEntity<ApiResponse<List<PlanRequestDto>>> getRequests(
                        @PathVariable Long planId) {
                Long ownerId = currentUser.getId();
                permissionService.checkPermission(planId, ownerId, PlanRole.OWNER);
                List<PlanRequestDto> requests = service.getRequests(planId);
                return ResponseEntity.ok(
                                ApiResponse.success("Lấy danh sách yêu cầu truy cập thành công", requests));
        }

        @PatchMapping("/requests/{reqId}")
        public ResponseEntity<ApiResponse<?>> handleRequest(
                        @PathVariable Long planId,
                        @PathVariable Long reqId,
                        @RequestBody PlanRequestAction req) {
                Long ownerId = currentUser.getId();
                permissionService.checkPermission(planId, ownerId, PlanRole.OWNER);
                service.handleRequest(planId, reqId, req);
                return ResponseEntity.ok(
                                ApiResponse.success("Xử lý yêu cầu truy cập thành công", null));
        }

}
