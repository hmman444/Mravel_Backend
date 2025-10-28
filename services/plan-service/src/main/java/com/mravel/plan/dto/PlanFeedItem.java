package com.mravel.plan.dto;

import lombok.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanFeedItem {
    private Long id;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private int days;
    private String visibility;
    private long views;
    private Instant createdAt;
    private Author author;
    private List<String> images;
    private List<Destination> destinations;

    // Tổng hợp số lượng từng loại reaction (like, love, haha...)
    private Map<String, Long> reactions;

    // Danh sách chi tiết từng người đã react
    private List<ReactionUser> reactionUsers;

    private List<Comment> comments;

    // Thêm lớp ReactionUser
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReactionUser {
        private String userId;
        private String userName;
        private String userAvatar;
        private String type; // like, love, haha, ...
    }

    // Destination nested class
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Destination {
        private String name;
        private String lat;
        private String lng;
    }

    // Enum visibility -> text
    public static String toUiVisibility(Enum<?> v) {
        if (v == null) return "public";
        return switch (v.name()) {
            case "public_" -> "Public";
            case "friends" -> "Friends";
            case "private_" -> "Private";
            default -> v.name();
        };
    }

    // Author info
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        private String id;
        private String name;
        private String avatar;
    }

    // Comment info
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private Long id;
        private User user;
        private String text;
        private Instant createdAt;
        private Long parentId; // nếu là reply
        private List<Comment> replies; // danh sách phản hồi con

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class User {
            private String id;
            private String name;
            private String avatar;
        }
    }
}
