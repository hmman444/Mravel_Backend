package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

                Map<Long, UserProfileResponse> cache = plans.stream()
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
                                .map(p -> toFeedItem(p, cache))
                                .collect(Collectors.toList());
        }

        private PlanFeedItem toFeedItem(Plan p, Map<Long, UserProfileResponse> authorProfileCache) {
                Map<String, Long> reactionSummary = p.getReactions() == null
                                ? Map.of()
                                : p.getReactions().stream()
                                                .collect(Collectors.groupingBy(PlanReaction::getType,
                                                                Collectors.counting()));

                // Danh sách user đã react
                var reactionUsers = p.getReactions() == null
                                ? List.<PlanFeedItem.ReactionUser>of()
                                : p.getReactions().stream()
                                                .map(r -> PlanFeedItem.ReactionUser.builder()
                                                                .userId(r.getUserId())
                                                                .userName(r.getUserName())
                                                                .userAvatar(r.getUserAvatar())
                                                                .type(r.getType())
                                                                .build())
                                                .collect(Collectors.toList());

                // Load author từ cache hoặc từ UserProfileService
                PlanFeedItem.Author authorDto = null;
                if (p.getAuthorId() != null) {
                        UserProfileResponse profile = null;

                        if (authorProfileCache != null) {
                                profile = authorProfileCache.get(p.getAuthorId());
                        } else {
                                try {
                                        profile = userProfileClient.getUserById(p.getAuthorId());
                                } catch (Exception ignored) {
                                }
                        }

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
                                .comments(p.getComments() == null ? List.of()
                                                : p.getComments().stream()
                                                                .filter(c -> c.getParent() == null)
                                                                .map(this::mapComment)
                                                                .collect(Collectors.toList()))
                                .build();
        }

        private PlanFeedItem.Comment mapComment(PlanComment c) {
                return PlanFeedItem.Comment.builder()
                                .id(c.getId())
                                .user(PlanFeedItem.Comment.User.builder()
                                                .id(c.getUserId())
                                                .name(c.getUserName())
                                                .avatar(c.getUserAvatar())
                                                .build())
                                .text(c.getText())
                                .createdAt(c.getCreatedAt())
                                .parentId(c.getParent() != null ? c.getParent().getId() : null)
                                .replies(c.getReplies() != null
                                                ? c.getReplies().stream().map(this::mapComment)
                                                                .collect(Collectors.toList())
                                                : List.of())
                                .build();
        }
}
