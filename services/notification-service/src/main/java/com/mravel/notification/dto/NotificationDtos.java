package com.mravel.notification.dto;

import lombok.*;

import java.time.Instant;

public class NotificationDtos {

    @Getter
    @Setter
    public static class CreateNotificationRequest {
        private Long recipientId;
        private Long actorId;
        private String type;
        private String title;
        private String message;
        private Object data; // serialize JSON
    }

    @Builder
    @Getter
    @Setter
    public static class ActorSummary {
        private Long id;
        private String fullname;
        private String avatar;
    }

    @Builder
    @Getter
    @Setter
    public static class NotificationResponse {
        private Long id;
        private Long recipientId;
        private Long actorId;
        private ActorSummary actor;
        private String type;
        private String category; // SOCIAL, BOOKING, ACCOUNT, PARTNER, ADMIN
        private String title;
        private String message;

        private String deepLink; // frontend navigation target
        private String image;   // avatar or thumbnail

        private String dataJson;
        private boolean read;
        private Instant createdAt;
        private long unreadCount;
    }

    @Builder
    @Getter
    @Setter
    public static class NotificationEvent {
        private String eventType;
        private Long notificationId;
        private Long recipientId;
        private Long actorId;
        private Instant createdAt;
        private NotificationResponse notification;
    }
}