package com.mravel.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    private Long id; // Trùng với id bên auth-service

    @Column(nullable = false, unique = true)
    private String email;     // Email đăng nhập / chính

    private String fullname;
    private String avatar;
    private String provider;  // local, google, facebook

    // -------- Thông tin cá nhân --------
    @Enumerated(EnumType.STRING)
    private Gender gender;    // MALE, FEMALE, OTHER, UNKNOWN

    private LocalDate dateOfBirth;

    private String city;
    private String country;
    private String addressLine;

    // -------- Email liên hệ phụ --------
    private String secondaryEmail;
    private String tertiaryEmail;

    // -------- Số điện thoại --------
    private String phone1;
    private String phone2;
    private String phone3;

    // -------- Membership & cài đặt --------
    @Enumerated(EnumType.STRING)
    private MembershipTier membershipTier; // BRONZE, SILVER, GOLD, PLATINUM

    private String locale;    // vi-VN, en-US
    private String timeZone;  // Asia/Ho_Chi_Minh

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}