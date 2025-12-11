package com.mravel.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPublicProfileResponse {
    private Long id;
    private String username;
    private String fullname;
    private String avatar;
    private String coverImage;
    private String bio;

    private String city;
    private String country;
    private String hometown;
    private String occupation;

    private String membershipTier;
    private String joinedAt;
}