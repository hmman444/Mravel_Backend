package com.mravel.user.controller;

import com.mravel.user.dto.UserMiniResponse;
import com.mravel.user.model.RelationshipType;
import com.mravel.user.service.AuthTokenClient;
import com.mravel.user.service.FriendService;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final AuthTokenClient authTokenClient;

    private Long getCurrentUserId(String authorizationHeader) {
        Map<String, Object> result = authTokenClient.validateToken(authorizationHeader);
        Boolean valid = (Boolean) result.get("valid");
        if (valid == null || !valid) {
            // Trả 401 (không phải 500) để FE kích hoạt luồng refresh token và retry
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT expired");
        }
        // id có thể được deserialize thành Integer hoặc Long tùy độ lớn -> ép qua Number cho an toàn
        Object idRaw = result.get("id");
        if (!(idRaw instanceof Number)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Không lấy được userId từ token");
        }
        return ((Number) idRaw).longValue();
    }

    @PostMapping("/requests")
    public ApiResponse<Void> sendRequest(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam Long targetUserId) {

        Long currentUserId = getCurrentUserId(authorizationHeader);
        friendService.sendRequest(currentUserId, targetUserId);
        return ApiResponse.success("Gửi lời mời kết bạn thành công", null);
    }

    @PostMapping("/{otherUserId}/accept")
    public ApiResponse<Void> acceptRequest(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long otherUserId) {

        Long currentUserId = getCurrentUserId(authorizationHeader);
        friendService.acceptRequest(currentUserId, otherUserId);
        return ApiResponse.success("Chấp nhận lời mời kết bạn", null);
    }

    @DeleteMapping("/{otherUserId}")
    public ApiResponse<Void> removeFriend(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long otherUserId) {

        Long currentUserId = getCurrentUserId(authorizationHeader);
        friendService.removeFriendOrCancel(currentUserId, otherUserId);
        return ApiResponse.success("Xóa / hủy kết bạn thành công", null);
    }

    @GetMapping("/relationship")
    public ApiResponse<RelationshipType> getRelationship(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam Long profileUserId) {

        Long viewerId = null;
        if (authorizationHeader != null) {
            try {
                viewerId = getCurrentUserId(authorizationHeader);
            } catch (Exception ignored) {
            }
        }

        RelationshipType type = friendService.getRelationship(viewerId, profileUserId);
        return ApiResponse.success("Lấy quan hệ thành công", type);
    }

    @GetMapping("/ids")
    public ApiResponse<List<Long>> getFriendIds(
            @RequestHeader("Authorization") String authorizationHeader) {
        Long currentUserId = getCurrentUserId(authorizationHeader);
        List<Long> ids = friendService.getFriendIds(currentUserId);
        return ApiResponse.success("Lấy friendIds thành công", ids);
    }

    @GetMapping("/list")
    public ApiResponse<List<UserMiniResponse>> getFriendList(
            @RequestHeader("Authorization") String authorizationHeader) {
        Long currentUserId = getCurrentUserId(authorizationHeader);
        List<UserMiniResponse> friends = friendService.getFriendList(currentUserId);
        return ApiResponse.success("Lấy danh sách bạn bè thành công", friends);
    }

}
