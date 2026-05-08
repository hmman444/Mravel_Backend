package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "plan_comment_reactions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"comment_id", "user_id"}))
public class PlanCommentReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private PlanComment comment;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String type; // like, love, haha, wow, sad, angry

    private Instant createdAt;
}
