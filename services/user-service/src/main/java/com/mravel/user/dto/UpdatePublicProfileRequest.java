package com.mravel.user.dto;

import lombok.Data;

@Data
public class UpdatePublicProfileRequest {
    private String fullname;
    private String username;
    private String avatar;
    private String coverImage;

    private String bio;
    private String city;
    private String country;
    private String hometown;
    private String occupation;
}