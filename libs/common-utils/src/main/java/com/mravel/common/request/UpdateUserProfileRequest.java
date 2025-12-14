package com.mravel.common.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateUserProfileRequest {
    private String fullname;
    private String avatar;
    private String gender;
    private LocalDate dateOfBirth;

    private String city;
    private String country;
    private String addressLine;

    private String secondaryEmail;
    private String tertiaryEmail;

    private String phone1;
    private String phone2;
    private String phone3;

    private String username;
    private String bio;
    private String coverImage;
    private String hometown;
    private String occupation;
}