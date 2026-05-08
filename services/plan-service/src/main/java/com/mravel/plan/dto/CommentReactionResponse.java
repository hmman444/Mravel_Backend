package com.mravel.plan.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReactionResponse {
    private Long commentId;
    private Map<String, Long> reactions;
    private String myReaction;                       // null if user removed reaction
    private List<PlanFeedItem.ReactionUser> reactionUsers; // full list for popup
}
