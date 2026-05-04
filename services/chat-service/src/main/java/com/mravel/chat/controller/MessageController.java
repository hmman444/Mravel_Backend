package com.mravel.chat.controller;

import com.mravel.chat.dto.ChatDtos.*;
import com.mravel.chat.service.MessageService;
import com.mravel.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat/conversations/{conversationId}/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<ApiResponse<MessageResponse>> send(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long conversationId,
            @Valid @RequestBody SendMessageRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Đã gửi tin nhắn",
                messageService.sendMessage(conversationId, userId, req)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MessagePageResponse>> getMessages(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long conversationId,
            @RequestParam(required = false) Long before,
            @RequestParam(defaultValue = "30") int size) {
        return ResponseEntity.ok(ApiResponse.success("OK",
                messageService.getMessages(conversationId, userId, before, size)));
    }

    @PostMapping("/seen")
    public ResponseEntity<ApiResponse<Void>> markSeen(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long conversationId,
            @Valid @RequestBody MarkSeenRequest req) {
        messageService.markSeen(conversationId, userId, req.getLastMessageId());
        return ResponseEntity.ok(ApiResponse.success("OK", null));
    }

    @PostMapping("/typing")
    public ResponseEntity<ApiResponse<Void>> typing(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long conversationId) {
        messageService.sendTypingIndicator(conversationId, userId);
        return ResponseEntity.ok(ApiResponse.success("OK", null));
    }
}
