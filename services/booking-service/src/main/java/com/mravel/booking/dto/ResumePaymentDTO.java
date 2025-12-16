package com.mravel.booking.dto;

public record ResumePaymentDTO(
    String bookingCode,
    String payUrl,
    long expiresInSeconds
) {}