package com.mravel.notification.service;

public class NotificationRenderHelper {

    public static String deepLink(String type, Long actorId, String dataJson) {
        // Friend notifications
        if ("FRIEND_REQUEST".equals(type) || "FRIEND_ACCEPTED".equals(type)) {
            return "/profile/" + actorId;
        }

        // Future:
        // PLAN_INVITE -> "/plans/{planId}"
        // PLAN_COMMENT -> "/plans/{planId}?focus=comment:{commentId}"
        // BOOKING_* -> "/bookings/{bookingId}"
        return null;
    }

    public static String image(String type, String actorAvatar, String dataJson) {
        // Friend: dùng avatar actor
        if ("FRIEND_REQUEST".equals(type) || "FRIEND_ACCEPTED".equals(type)) {
            return actorAvatar;
        }

        // Future: plan/booking dùng thumbnail lấy từ service khác
        return null;
    }
}