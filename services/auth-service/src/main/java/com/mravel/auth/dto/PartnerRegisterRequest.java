package com.mravel.auth.dto;

import lombok.Data;

@Data
public class PartnerRegisterRequest {
    private String fullname;
    private String email;
    private String password;

    private String businessName;
    private String phone;
}