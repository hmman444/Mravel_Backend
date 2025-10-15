package com.mravel.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialLoginRequest {
    private String provider;
    private String token;
}
