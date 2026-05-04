package com.mravel.common.event;

import lombok.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageEvent {

    /**
     * Event types:
     * NEW_MESSAGE, TYPING, SEEN_UPDATE, GROUP_UPDATED, MEMBER_ADDED,
     * MEMBER_REMOVED, MEMBER_LEFT, ROLE_CHANGED
     */
    private String eventType;

    private Long conversationId;
    private Long actorId;
    private Instant timestamp;

    /** Users directly affected by the event, e.g. added/removed members. */
    private List<Long> affectedUserIds;

    /**
     * All active member IDs — used by realtime-service to fan out to personal
     * topics.
     */
    private List<Long> recipientIds;

    /** Populated for NEW_MESSAGE */
    private MessagePayload message;

    /** Populated for TYPING */
    private Boolean typing;

    /** Populated for SEEN_UPDATE */
    private Long lastSeenMessageId;
    private Long seenByUserId;

    /**
     * Populated for GROUP_UPDATED, MEMBER_ADDED, MEMBER_REMOVED, MEMBER_LEFT,
     * ROLE_CHANGED
     */
    private ConversationPayload conversation;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessagePayload {
        private Long id;
        private Long conversationId;
        private Long senderId;
        private String senderName;
        private String senderAvatar;
        private String content;
        private String messageType;
        private Instant createdAt;
        private boolean deleted;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversationPayload {
        private Long id;
        private String type;
        private String name;
        private String avatarUrl;
        private Long ownerId;
    }
}
