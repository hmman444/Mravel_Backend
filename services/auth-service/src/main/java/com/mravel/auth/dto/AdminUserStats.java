package com.mravel.auth.dto;

/** Số liệu tổng hợp tài khoản cho admin dashboard. */
public record AdminUserStats(
        long totalUsers,
        long totalPartners,
        long totalRegularUsers,
        long totalAdmins) {
}
