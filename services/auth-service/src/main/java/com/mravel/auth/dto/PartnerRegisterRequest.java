package com.mravel.auth.dto;

import lombok.Data;

@Data
public class PartnerRegisterRequest {
    private String fullname;
    private String email;
    private String password;

    // optional, bạn muốn có thì thêm
    private String businessName;
    private String phone;
}