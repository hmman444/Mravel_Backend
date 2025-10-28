package com.mravel.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data @Getter
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
