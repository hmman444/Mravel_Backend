package com.mravel.notification.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class NotificationRenderHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static Map<String, Object> parse(String dataJson) {
        if (dataJson == null || dataJson.isBlank()) return Map.of();
        try {
            return MAPPER.readValue(dataJson, new TypeReference<Map<String, Object>>() {});
        } catch (Exception ignored) {
            return Map.of();
        }
    }

    private static Long asLong(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.longValue();
        try { return Long.parseLong(v.toString()); } catch (Exception ignored) { return null; }
    }

    private static String asString(Object v) {
        return v != null ? v.toString() : null;
    }

    // ── Deep link resolution ─────────────────────────────────────────────────

    public static String deepLink(String type, Long actorId, String dataJson) {
        if (type == null) return null;

        // Social: friend
        if ("FRIEND_REQUEST".equals(type) || "FRIEND_ACCEPTED".equals(type)) {
            return actorId != null ? "/profile/" + actorId : null;
        }

        Map<String, Object> data = parse(dataJson);

        // Producer-supplied deepLink takes priority
        String provided = asString(data.get("deepLink"));
        if (provided != null && !provided.isBlank()) return provided;

        return switch (type) {
            case "PLAN_INVITE" -> {
                String token = asString(data.get("token"));
                if (token != null && !token.isBlank()) yield "/plans/join?token=" + token;
                Long planId = asLong(data.get("planId"));
                yield planId != null ? "/plans/" + planId : null;
            }
            case "COMMENT", "REPLY_COMMENT" -> {
                Long planId = asLong(data.get("planId"));
                Long commentId = asLong(data.get("commentId"));
                if (planId != null && commentId != null) yield "/plans/" + planId + "?commentId=" + commentId;
                yield planId != null ? "/plans/" + planId : null;
            }
            case "REACT" -> {
                Long planId = asLong(data.get("planId"));
                yield planId != null ? "/plans/" + planId : null;
            }
            // Booking: navigate to the bookings list page (no per-booking detail route)
            case "BOOKING_CONFIRMED", "BOOKING_CANCELLED",
                 "BOOKING_CANCELLED_BY_PARTNER", "BOOKING_EXPIRED",
                 "PAYMENT_SUCCESS", "REFUND_PROCESSED" -> "/my-bookings";
            // Partner-facing booking events
            case "BOOKING_NEW_FOR_PARTNER", "BOOKING_CANCELLED_FOR_PARTNER"
                 -> "/partner/bookings";
            // Account events → account profile page
            case "ACCOUNT_LOCKED", "ACCOUNT_UNLOCKED",
                 "PASSWORD_CHANGED", "LOGIN_ALERT" -> "/account/profile";
            // Partner lifecycle → partner dashboard
            case "PARTNER_APPROVED", "PARTNER_REJECTED" -> "/partner/dashboard";
            default -> null;
        };
    }

    // ── Image / thumbnail resolution ─────────────────────────────────────────

    public static String image(String type, String actorAvatar, String dataJson) {
        if (type == null) return null;
        return switch (type) {
            // Social: show actor avatar (like Facebook)
            case "FRIEND_REQUEST", "FRIEND_ACCEPTED",
                 "PLAN_INVITE", "COMMENT", "REPLY_COMMENT", "REACT" -> actorAvatar;
            // System events carry no image; frontend uses a type-specific icon instead
            default -> null;
        };
    }
}
