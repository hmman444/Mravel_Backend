package com.mravel.common.event;

import lombok.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRealtimeEvent {
    private String eventType; // NOTI_CREATED
    private Long notificationId;
    private Long recipientId;
    private Long actorId;
    private Instant createdAt;

    private NotificationPayload notification;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationPayload {
        private Long id;
        private Long recipientId;
        private Long actorId;
        private ActorSummary actor;
        private String type;
        private String title;
        private String message;
        private String deepLink;
        private String image;
        private String dataJson;
        private boolean read;
        private Instant createdAt;
        private long unreadCount;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActorSummary {
        private Long id;
        private String fullname;
        private String avatar;
    }
}
