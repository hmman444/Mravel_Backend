package com.mravel.chat.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "conversations", indexes = {
    @Index(name = "idx_conv_updated_at", columnList = "updated_at")
})
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ConversationType type;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    /** Only set for GROUP conversations. */
    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public enum ConversationType {
        PRIVATE, GROUP
    }
}
