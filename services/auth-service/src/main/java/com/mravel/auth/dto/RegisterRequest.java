package com.mravel.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    private String fullname;
    private String email;
    private String password;
}
