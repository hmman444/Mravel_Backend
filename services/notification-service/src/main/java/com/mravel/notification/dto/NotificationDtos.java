package com.mravel.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;

public class NotificationDtos {

    @Getter
    @Setter
    public static class CreateNotificationRequest {
        @NotNull
        private Long recipientId;
        private Long actorId;
        @NotBlank
        private String type;
        @NotBlank
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

    @Getter
    public static class PagedResult<T> {
        private final List<T> content;
        private final long totalElements;
        private final int totalPages;
        private final int size;
        private final int number;
        private final Long unreadCount;

        public static <T> PagedResult<T> of(Page<T> page) {
            return of(page, null);
        }

        public static <T> PagedResult<T> of(Page<T> page, Long unreadCount) {
            return new PagedResult<>(
                    page.getContent(),
                    page.getTotalElements(),
                    page.getTotalPages(),
                    page.getSize(),
                    page.getNumber(),
                    unreadCount
            );
        }

        private PagedResult(List<T> content, long totalElements, int totalPages, int size, int number, Long unreadCount) {
            this.content = content;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.size = size;
            this.number = number;
            this.unreadCount = unreadCount;
        }
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