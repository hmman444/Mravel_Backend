package com.mravel.chat.dto;

import com.mravel.chat.model.ConversationMember.MemberRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.Instant;
import java.util.List;

public class ChatDtos {

    // ─── Requests ────────────────────────────────────────────────────────────

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class CreatePrivateRequest {
        @NotNull
        private Long recipientId;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class CreateGroupRequest {
        @NotBlank @Size(max = 100)
        private String name;
        @NotNull @Size(min = 1, max = 99)
        private List<Long> memberIds;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class SendMessageRequest {
        @Size(max = 4000)
        private String content;
        /** TEXT (default) | IMAGE | VIDEO */
        private String messageType;
        @Size(max = 2000)
        private String mediaUrl;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class UpdateGroupNameRequest {
        @NotBlank @Size(max = 100)
        private String name;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class AddMembersRequest {
        @NotNull @Size(min = 1, max = 50)
        private List<Long> userIds;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class ChangeMemberRoleRequest {
        @NotNull
        private MemberRole role;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TransferOwnershipRequest {
        @NotNull
        private Long newOwnerId;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class MarkSeenRequest {
        @NotNull
        private Long lastMessageId;
    }

    // ─── Responses ───────────────────────────────────────────────────────────

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberResponse {
        private Long userId;
        private String fullname;
        private String avatar;
        private MemberRole role;
        private Instant joinedAt;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MessageSummary {
        private Long id;
        private Long senderId;
        private String senderName;
        private String content;
        private String messageType;
        private String mediaUrl;
        private Instant createdAt;
        private boolean deleted;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ConversationResponse {
        private Long id;
        private String type;
        private String name;
        private String avatarUrl;
        private Long ownerId;
        private int memberCount;
        private MessageSummary lastMessage;
        private long unreadCount;
        private Instant updatedAt;
        /** Mini member list (for avatars in group, or other user for private). */
        private List<MemberResponse> members;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MessageResponse {
        private Long id;
        private Long conversationId;
        private Long senderId;
        private String senderName;
        private String senderAvatar;
        private String content;
        private String messageType;
        private String mediaUrl;
        private Instant createdAt;
        private boolean deleted;
        /** Map of userId → readAt for seen receipts. */
        private List<SeenInfo> seenBy;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SeenInfo {
        private Long userId;
        private String fullname;
        private String avatar;
        private Instant readAt;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MessagePageResponse {
        private List<MessageResponse> messages;
        private Long nextCursor;
        private boolean hasMore;
    }

    /** Lightweight response for the access-check endpoint consumed by realtime-service. */
    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AccessCheckResponse {
        private boolean canAccess;
    }

    /** Internal mini user info from user-service. */
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class UserMiniResponse {
        private Long id;
        private String fullname;
        private String avatar;
    }
}
