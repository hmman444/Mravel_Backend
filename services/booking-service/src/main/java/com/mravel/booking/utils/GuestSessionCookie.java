// src/main/java/com/mravel/booking/utils/GuestSessionCookie.java
package com.mravel.booking.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import java.util.UUID;

public final class GuestSessionCookie {

    private GuestSessionCookie() {}

    public static final String COOKIE_NAME = "mravel_guest_sid";
    public static final int MAX_AGE_SECONDS = 60 * 60 * 24 * 60; // 60 ngày

    public static String get(HttpServletRequest req) {
        if (req == null || req.getCookies() == null) return null;
        for (Cookie c : req.getCookies()) {
            if (COOKIE_NAME.equals(c.getName())) {
                String v = c.getValue();
                return (v == null || v.isBlank()) ? null : v;
            }
        }
        return null;
    }

    /** Ensure cookie tồn tại: nếu chưa có thì tạo sid mới + set cookie vào response */
    public static String ensure(HttpServletRequest req, HttpServletResponse resp) {
        String sid = get(req);
        if (sid == null) sid = UUID.randomUUID().toString();

        ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, sid)
                .httpOnly(true)
                .secure(false)          // prod => true (https)
                .sameSite("Lax")
                .path("/")
                .maxAge(MAX_AGE_SECONDS)
                .build();

        resp.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return sid;
    }

    public static void clear(HttpServletResponse resp) {
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();

        resp.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}