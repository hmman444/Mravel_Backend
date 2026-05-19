package com.mravel.chat.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "message_read_status",
    indexes = {
        @Index(name = "idx_mrs_conv_user", columnList = "conversation_id, user_id", unique = true)
    }
)
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MessageReadStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conversation_id", nullable = false)
    private Long conversationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** ID of the last message the user has seen in this conversation. */
    @Column(name = "last_read_message_id")
    private Long lastReadMessageId;

    @Column(name = "read_at")
    private Instant readAt;
}
