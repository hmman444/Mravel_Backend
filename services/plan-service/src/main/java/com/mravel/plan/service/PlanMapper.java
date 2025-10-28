package com.mravel.plan.service;
import java.util.List;

import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.*;
import org.springframework.stereotype.Component;

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
                        .days(p.getDays())
                        .visibility(PlanFeedItem.toUiVisibility(p.getVisibility()))
                        .views(p.getViews())
                        .createdAt(p.getCreatedAt())
                        .author(PlanFeedItem.Author.builder()
                                .id(p.getAuthor().getId())
                                .name(p.getAuthor().getName())
                                .avatar(p.getAuthor().getAvatar())
                                .build())
                        .images(p.getImages())
                        .destinations(p.getDestinations().stream()
                                .map(d -> PlanFeedItem.Destination.builder()
                                        .name(d.getName())
                                        .lat(d.getLat())
                                        .lng(d.getLng())
                                        .build())
                                .collect(Collectors.toList()))
                        .reactions(p.getReactions().stream()
                                .collect(Collectors.groupingBy(PlanReaction::getType, Collectors.counting())))
                        .reactionUsers(p.getReactions().stream()
                                .map(r -> PlanFeedItem.ReactionUser.builder()
                                        .userId(r.getUserId())
                                        .userName(r.getUserName())
                                        .userAvatar(r.getUserAvatar())
                                        .type(r.getType())
                                        .build())
                                .collect(Collectors.toList()))
                        .comments(p.getComments().stream()
                                .filter(c -> c.getParent() == null) // chỉ lấy comment gốc
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
                                ? c.getReplies().stream().map(this::mapComment).collect(Collectors.toList())
                                : List.of())
                        .build();
        }
}
