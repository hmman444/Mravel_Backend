package com.mravel.common.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private Long id;
    private String email;
    private String fullname;
    private String avatar;
    private String provider;

    private String role;

    private String gender;
    private java.time.LocalDate dateOfBirth;

    private String city;
    private String country;
    private String addressLine;

    private String secondaryEmail;
    private String tertiaryEmail;

    private String phone1;
    private String phone2;
    private String phone3;

    private String membershipTier;
    private String locale;
    private String timeZone;
}
