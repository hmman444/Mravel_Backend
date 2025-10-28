package com.mravel.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class ResetPasswordRequest {
    private String email;
    private String otpCode;
    private String newPassword;
}
