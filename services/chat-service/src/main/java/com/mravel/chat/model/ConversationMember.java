package com.mravel.chat.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "conversation_members",
    indexes = {
        @Index(name = "idx_cm_user_id", columnList = "user_id"),
        @Index(name = "idx_cm_conv_user", columnList = "conversation_id, user_id", unique = true)
    }
)
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ConversationMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conversation_id", nullable = false)
    private Long conversationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(name = "joined_at", nullable = false)
    private Instant joinedAt;

    /** Null = active member. Non-null = left or removed. */
    @Column(name = "left_at")
    private Instant leftAt;

    public boolean isActive() {
        return leftAt == null;
    }

    public enum MemberRole {
        OWNER, ADMIN, MEMBER;

        public boolean isAtLeast(MemberRole required) {
            return this.ordinal() <= required.ordinal();
        }
    }
}
