package com.mravel.auth.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType; // e.g. USER_REGISTERED

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String payload;

    private String status; // PENDING, SENT, FAILED
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
