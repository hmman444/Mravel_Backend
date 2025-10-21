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
}
