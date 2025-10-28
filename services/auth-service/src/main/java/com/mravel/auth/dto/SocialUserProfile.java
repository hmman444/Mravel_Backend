package com.mravel.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocialUserProfile {
    private String email;
    private String name;
    private String picture;
    private String providerId;
}
