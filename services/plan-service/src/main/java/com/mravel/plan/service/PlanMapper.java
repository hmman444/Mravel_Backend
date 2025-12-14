package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanComment;
import com.mravel.plan.model.PlanReaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanMapper {

        private final UserProfileClient userProfileClient;

        public PlanFeedItem toFeedItem(Plan p) {
                return toFeedItem(p, null);
        }

        public List<PlanFeedItem> toFeedItems(List<Plan> plans) {
                if (plans == null || plans.isEmpty())
                        return List.of();

                // Cache sẵn profile của author theo từng plan (dùng cho list)
                Map<Long, UserProfileResponse> authorProfileCache = plans.stream()
                                .map(Plan::getAuthorId)
                                .filter(Objects::nonNull)
                                .distinct()
                                .collect(Collectors.toMap(
                                                id -> id,
                                                id -> {
                                                        try {
                                                                return userProfileClient.getUserById(id);
                                                        } catch (Exception e) {
                                                                return null;
                                                        }
                                                }));

                return plans.stream()
                                .map(p -> toFeedItem(p, authorProfileCache))
                                .collect(Collectors.toList());
        }

        private PlanFeedItem toFeedItem(Plan p,
                        Map<Long, UserProfileResponse> authorProfileCache) {

                // Cache cục bộ cho tất cả user (reactions + comments) của 1 plan
                Map<Long, UserProfileResponse> localUserCache = new HashMap<>();

                // ===== Reactions summary =====
                Map<String, Long> reactionSummary = p.getReactions() == null
                                ? Map.of()
                                : p.getReactions().stream()
                                                .collect(Collectors.groupingBy(
                                                                PlanReaction::getType,
                                                                Collectors.counting()));

                // ===== Reaction users (chi tiết từng user react) =====
                List<PlanFeedItem.ReactionUser> reactionUsers = p.getReactions() == null
                                ? List.of()
                                : p.getReactions().stream()
                                                .map(r -> {
                                                        UserProfileResponse profile = resolveUserProfile(
                                                                        r.getUserId(),
                                                                        authorProfileCache,
                                                                        localUserCache);

                                                        return PlanFeedItem.ReactionUser.builder()
                                                                        .userId(r.getUserId())
                                                                        .userName(profile != null
                                                                                        ? profile.getFullname()
                                                                                        : null)
                                                                        .userAvatar(profile != null
                                                                                        ? profile.getAvatar()
                                                                                        : null)
                                                                        .type(r.getType())
                                                                        .build();
                                                })
                                                .collect(Collectors.toList());

                // ===== Author =====
                PlanFeedItem.Author authorDto = null;
                if (p.getAuthorId() != null) {
                        UserProfileResponse profile = resolveUserProfile(
                                        p.getAuthorId(),
                                        authorProfileCache,
                                        localUserCache);

                        if (profile != null) {
                                authorDto = PlanFeedItem.Author.builder()
                                                .id(p.getAuthorId())
                                                .name(profile.getFullname())
                                                .avatar(profile.getAvatar())
                                                .build();
                        } else {
                                authorDto = PlanFeedItem.Author.builder()
                                                .id(p.getAuthorId())
                                                .name(null)
                                                .avatar(null)
                                                .build();
                        }
                }

                // ===== Comments (root + replies đệ quy) =====
                List<PlanFeedItem.Comment> commentDtos = p.getComments() == null
                                ? List.of()
                                : p.getComments().stream()
                                                .filter(c -> c.getParent() == null) // chỉ lấy root comment
                                                .sorted(Comparator.comparing(PlanComment::getCreatedAt))
                                                .map(c -> mapComment(c, authorProfileCache, localUserCache))
                                                .collect(Collectors.toList());

                return PlanFeedItem.builder()
                                .id(p.getId())
                                .title(p.getTitle())
                                .description(p.getDescription())
                                .startDate(p.getStartDate() != null ? p.getStartDate().toString() : null)
                                .endDate(p.getEndDate() != null ? p.getEndDate().toString() : null)
                                .days(p.getDays() != null ? p.getDays() : 0)
                                .visibility(PlanFeedItem.toUiVisibility(p.getVisibility()))
                                .views(p.getViews() != null ? p.getViews() : 0L)
                                .createdAt(p.getCreatedAt())

                                .author(authorDto)

                                .images(p.getImages() != null ? p.getImages() : List.of())
                                .destinations(p.getDestinations() == null ? List.of()
                                                : p.getDestinations().stream()
                                                                .map(d -> PlanFeedItem.Destination.builder()
                                                                                .name(d.getName())
                                                                                .lat(d.getLat())
                                                                                .lng(d.getLng())
                                                                                .build())
                                                                .collect(Collectors.toList()))
                                .reactions(reactionSummary)
                                .reactionUsers(reactionUsers)
                                .comments(commentDtos)
                                .build();
        }

        private PlanFeedItem.Comment mapComment(PlanComment c,
                        Map<Long, UserProfileResponse> authorProfileCache,
                        Map<Long, UserProfileResponse> localUserCache) {

                UserProfileResponse profile = resolveUserProfile(
                                c.getUserId(),
                                authorProfileCache,
                                localUserCache);

                PlanFeedItem.Comment.User userDto = PlanFeedItem.Comment.User.builder()
                                .id(c.getUserId())
                                .name(profile != null ? profile.getFullname() : null)
                                .avatar(profile != null ? profile.getAvatar() : null)
                                .build();

                List<PlanFeedItem.Comment> replyDtos = c.getReplies() == null
                                ? List.of()
                                : c.getReplies().stream()
                                                .sorted(Comparator.comparing(PlanComment::getCreatedAt))
                                                .map(child -> mapComment(child, authorProfileCache, localUserCache))
                                                .collect(Collectors.toList());

                return PlanFeedItem.Comment.builder()
                                .id(c.getId())
                                .user(userDto)
                                .text(c.getText())
                                .createdAt(c.getCreatedAt())
                                .parentId(c.getParent() != null ? c.getParent().getId() : null)
                                .replies(replyDtos)
                                .build();
        }

        /**
         * Lấy UserProfile theo id với 3 tầng cache:
         * - authorProfileCache: cache tác giả truyền từ toFeedItems (cross-plan)
         * - localUserCache: cache cục bộ cho 1 plan (reactions + comments)
         * - UserProfileClient: cache Caffeine 1m (cross-service)
         */
        private UserProfileResponse resolveUserProfile(Long userId,
                        Map<Long, UserProfileResponse> authorProfileCache,
                        Map<Long, UserProfileResponse> localUserCache) {
                if (userId == null)
                        return null;

                // 1. cache author (dùng cho toFeedItems)
                if (authorProfileCache != null && authorProfileCache.containsKey(userId)) {
                        return authorProfileCache.get(userId);
                }

                // 2. cache cục bộ của plan
                if (localUserCache.containsKey(userId)) {
                        return localUserCache.get(userId);
                }

                // 3. gọi user-service (đã có @Cacheable userProfiles)
                try {
                        UserProfileResponse profile = userProfileClient.getUserById(userId);
                        localUserCache.put(userId, profile);
                        return profile;
                } catch (Exception e) {
                        localUserCache.put(userId, null);
                        return null;
                }
        }
}
