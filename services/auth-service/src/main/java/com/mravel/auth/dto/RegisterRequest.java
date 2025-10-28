package com.mravel.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class RegisterRequest {
    private String fullname;
    private String email;
    private String password;
}
