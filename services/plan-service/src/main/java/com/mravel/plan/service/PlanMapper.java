package com.mravel.plan.service;

import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PlanMapper {

        public PlanFeedItem toFeedItem(Plan p) {
                Map<String, Long> reactionSummary = p.getReactions().stream()
                                .collect(Collectors.groupingBy(PlanReaction::getType, Collectors.counting()));

                var reactionUsers = p.getReactions().stream()
                                .map(r -> PlanFeedItem.ReactionUser.builder()
                                                .userId(r.getUserId())
                                                .userName(r.getUserName())
                                                .userAvatar(r.getUserAvatar())
                                                .type(r.getType())
                                                .build())
                                .collect(Collectors.toList());

                return PlanFeedItem.builder()
                                .id(p.getId())
                                .title(p.getTitle())
                                .description(p.getDescription())
                                .startDate(p.getStartDate())
                                .endDate(p.getEndDate())
                                .days(p.getDays() != null ? p.getDays() : 0)
                                .visibility(PlanFeedItem.toUiVisibility(p.getVisibility()))
                                .views(p.getViews() != null ? p.getViews() : 0L)
                                .createdAt(p.getCreatedAt())
                                .author(PlanFeedItem.Author.builder()
                                                .id(p.getAuthor() != null ? p.getAuthor().getId() : null)
                                                .name(p.getAuthor() != null ? p.getAuthor().getName() : null)
                                                .avatar(p.getAuthor() != null ? p.getAuthor().getAvatar() : null)
                                                .build())
                                .images(p.getImages() != null ? p.getImages() : List.of())
                                .destinations(
                                                p.getDestinations() == null ? List.of()
                                                                : p.getDestinations().stream()
                                                                                .map(d -> PlanFeedItem.Destination
                                                                                                .builder()
                                                                                                .name(d.getName())
                                                                                                .lat(d.getLat())
                                                                                                .lng(d.getLng())
                                                                                                .build())
                                                                                .collect(Collectors.toList()))
                                .reactions(reactionSummary)
                                .reactionUsers(reactionUsers)
                                .comments(
                                                p.getComments() == null ? List.of()
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
