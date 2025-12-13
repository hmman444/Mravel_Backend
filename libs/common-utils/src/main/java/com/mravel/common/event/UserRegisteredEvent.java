package com.mravel.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisteredEvent {
    private Long id;
    private String email;
    private String fullname;
    private String avatar;
    private String provider;
    private String providerId;

    @Builder.Default
    private String version = "v1";
}