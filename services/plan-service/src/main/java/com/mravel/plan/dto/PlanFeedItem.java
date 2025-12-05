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
    private Map<String, Long> reactions;
    private List<ReactionUser> reactionUsers;
    private List<Comment> comments;

    // Người đã react
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReactionUser {
        private Long userId;
        private String userName;
        private String userAvatar;
        private String type;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Destination {
        private String name;
        private Double lat;
        private Double lng;
    }

    public static String toUiVisibility(Enum<?> v) {
        if (v == null)
            return "Public";
        return switch (v.name()) {
            case "PUBLIC" -> "Public";
            case "FRIENDS" -> "Friends";
            case "PRIVATE" -> "Private";
            default -> v.name();
        };
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        private Long id;
        private String name;
        private String avatar;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private Long id;
        private User user;
        private String text;
        private Instant createdAt;
        private Long parentId;
        private List<Comment> replies;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class User {
            private Long id;
            private String name;
            private String avatar;
        }
    }
}
