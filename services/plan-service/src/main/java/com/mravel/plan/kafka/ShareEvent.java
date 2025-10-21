package com.mravel.plan.kafka;

import lombok.*;

import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ShareEvent {
    private String eventId;
    private Long planId;
    private String inviteEmail;
    private String inviterUserId;
    private Instant createdAt;
}
