package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanComment;
import com.mravel.plan.model.PlanCommentReaction;
import com.mravel.plan.model.PlanReaction;
import com.mravel.plan.repository.PlanCommentReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanMapper {

        private final UserProfileClient userProfileClient;
        private final PlanCommentReactionRepository commentReactionRepository;

        /**
         * Convenience overload — no viewer context (myReaction will be null for all
         * comments).
         */
        public PlanFeedItem toFeedItem(Plan p) {
                return toFeedItemInternal(p, null, null, Set.of());
        }

        /** Overload with viewer ID so comments include the viewer's own reaction. */
        public PlanFeedItem toFeedItem(Plan p, Long viewerId) {
                return toFeedItemInternal(p, null, viewerId, Set.of());
        }

        /**
         * Overload có danh sách bị chặn (blockedIds) để ẩn comment/reaction của
         * người bị chặn khỏi viewer.
         */
        public PlanFeedItem toFeedItem(Plan p, Long viewerId, Set<Long> blockedIds) {
                return toFeedItemInternal(p, null, viewerId, blockedIds == null ? Set.of() : blockedIds);
        }

        public List<PlanFeedItem> toFeedItems(List<Plan> plans) {
                return toFeedItems(plans, null);
        }

        public List<PlanFeedItem> toFeedItems(List<Plan> plans, Long viewerId) {
                if (plans == null || plans.isEmpty())
                        return List.of();

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
                                .map(p -> toFeedItemInternal(p, authorProfileCache, viewerId, Set.of()))
                                .collect(Collectors.toList());
        }

        private PlanFeedItem toFeedItemInternal(Plan p,
                        Map<Long, UserProfileResponse> authorProfileCache,
                        Long viewerId,
                        Set<Long> blockedIds) {

                final Set<Long> blocked = (blockedIds == null) ? Set.of() : blockedIds;
                Map<Long, UserProfileResponse> localUserCache = new HashMap<>();

                // Reactions hiển thị: loại reaction của người bị chặn (đồng bộ cả summary + danh sách)
                List<PlanReaction> visibleReactions = p.getReactions() == null
                                ? List.of()
                                : p.getReactions().stream()
                                                .filter(r -> !blocked.contains(r.getUserId()))
                                                .collect(Collectors.toList());

                Map<String, Long> reactionSummary = visibleReactions.stream()
                                .collect(Collectors.groupingBy(
                                                PlanReaction::getType,
                                                Collectors.counting()));

                List<PlanFeedItem.ReactionUser> reactionUsers = visibleReactions.isEmpty()
                                ? List.of()
                                : visibleReactions.stream()
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

                // Author
                PlanFeedItem.Author authorDto = null;
                if (p.getAuthorId() != null) {
                        UserProfileResponse profile = resolveUserProfile(
                                        p.getAuthorId(), authorProfileCache, localUserCache);
                        authorDto = PlanFeedItem.Author.builder()
                                        .id(p.getAuthorId())
                                        .name(profile != null ? profile.getFullname() : null)
                                        .avatar(profile != null ? profile.getAvatar() : null)
                                        .build();
                }

                // Comments (root-level only; replies are nested recursively)
                List<PlanFeedItem.Comment> commentDtos = p.getComments() == null
                                ? List.of()
                                : p.getComments().stream()
                                                .filter(c -> c.getParent() == null)
                                                .filter(c -> !blocked.contains(c.getUserId()))
                                                .sorted(Comparator.comparing(PlanComment::getCreatedAt))
                                                .map(c -> mapComment(c, authorProfileCache, localUserCache, viewerId,
                                                                blocked))
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
                                .videos(p.getVideos() != null ? p.getVideos() : List.of())
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
                                .budgetTotal(p.getBudgetTotal())
                                .budgetPerPerson(p.getBudgetPerPerson())
                                .budgetCurrency(p.getBudgetCurrency() != null ? p.getBudgetCurrency() : "VND")
                                .thumbnail(p.getThumbnail())
                                .build();
        }

        private PlanFeedItem.Comment mapComment(PlanComment c,
                        Map<Long, UserProfileResponse> authorProfileCache,
                        Map<Long, UserProfileResponse> localUserCache,
                        Long viewerId,
                        Set<Long> blockedIds) {

                final Set<Long> blocked = (blockedIds == null) ? Set.of() : blockedIds;

                UserProfileResponse profile = resolveUserProfile(
                                c.getUserId(), authorProfileCache, localUserCache);

                PlanFeedItem.Comment.User userDto = PlanFeedItem.Comment.User.builder()
                                .id(c.getUserId())
                                .name(profile != null ? profile.getFullname() : null)
                                .avatar(profile != null ? profile.getAvatar() : null)
                                .build();

                // Always read comment reactions from repository for consistency with
                // plan_comment_reactions table.
                // This avoids empty lazy collections causing missing reactionUsers in feed
                // responses.
                List<PlanCommentReaction> commentReactions = (c.getId() == null
                                ? List.<PlanCommentReaction>of()
                                : commentReactionRepository.findByComment_Id(c.getId()))
                                .stream()
                                .filter(r -> !blocked.contains(r.getUserId()))
                                .collect(Collectors.toList());

                Map<String, Long> reactionSummary = commentReactions.stream()
                                .collect(Collectors.groupingBy(PlanCommentReaction::getType, Collectors.counting()));

                List<PlanFeedItem.ReactionUser> commentReactionUsers = commentReactions.stream()
                                .map(r -> {
                                        UserProfileResponse rp = resolveUserProfile(
                                                        r.getUserId(), authorProfileCache, localUserCache);
                                        return PlanFeedItem.ReactionUser.builder()
                                                        .userId(r.getUserId())
                                                        .userName(rp != null ? rp.getFullname() : null)
                                                        .userAvatar(rp != null ? rp.getAvatar() : null)
                                                        .type(r.getType())
                                                        .build();
                                })
                                .collect(Collectors.toList());

                String myReaction = null;
                if (viewerId != null) {
                        myReaction = commentReactions.stream()
                                        .filter(r -> viewerId.equals(r.getUserId()))
                                        .findFirst()
                                        .map(PlanCommentReaction::getType)
                                        .orElse(null);
                }

                // Replies (recursive)
                List<PlanFeedItem.Comment> replyDtos = c.getReplies() == null
                                ? List.of()
                                : c.getReplies().stream()
                                                .filter(child -> !blocked.contains(child.getUserId()))
                                                .sorted(Comparator.comparing(PlanComment::getCreatedAt))
                                                .map(child -> mapComment(child, authorProfileCache, localUserCache,
                                                                viewerId, blocked))
                                                .collect(Collectors.toList());

                return PlanFeedItem.Comment.builder()
                                .id(c.getId())
                                .user(userDto)
                                .text(c.getText())
                                .createdAt(c.getCreatedAt())
                                .parentId(c.getParent() != null ? c.getParent().getId() : null)
                                .replies(replyDtos)
                                .reactions(reactionSummary)
                                .reactionUsers(commentReactionUsers)
                                .myReaction(myReaction)
                                .build();
        }

        private UserProfileResponse resolveUserProfile(Long userId,
                        Map<Long, UserProfileResponse> authorProfileCache,
                        Map<Long, UserProfileResponse> localUserCache) {
                if (userId == null)
                        return null;
                if (authorProfileCache != null && authorProfileCache.containsKey(userId))
                        return authorProfileCache.get(userId);
                if (localUserCache.containsKey(userId))
                        return localUserCache.get(userId);
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
