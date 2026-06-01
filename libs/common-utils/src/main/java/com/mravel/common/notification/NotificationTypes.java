package com.mravel.common.notification;

public final class NotificationTypes {

    private NotificationTypes() {
    }

    // Social
    public static final String FRIEND_REQUEST = "FRIEND_REQUEST";
    public static final String FRIEND_ACCEPTED = "FRIEND_ACCEPTED";
    public static final String PLAN_INVITE = "PLAN_INVITE";
    public static final String PLAN_MEMBER_JOINED = "PLAN_MEMBER_JOINED";
    public static final String COMMENT = "COMMENT";
    public static final String REPLY_COMMENT = "REPLY_COMMENT";
    public static final String REACT = "REACT";
    public static final String COMMENT_REACT = "COMMENT_REACT";

    // Booking (customer-facing)
    public static final String BOOKING_CONFIRMED = "BOOKING_CONFIRMED";
    public static final String BOOKING_CANCELLED = "BOOKING_CANCELLED";
    public static final String BOOKING_CANCELLED_BY_PARTNER = "BOOKING_CANCELLED_BY_PARTNER";
    public static final String BOOKING_EXPIRED = "BOOKING_EXPIRED";
    public static final String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
    public static final String REFUND_PROCESSED = "REFUND_PROCESSED";

    // Booking (partner-facing)
    public static final String BOOKING_NEW_FOR_PARTNER = "BOOKING_NEW_FOR_PARTNER";
    public static final String BOOKING_CANCELLED_FOR_PARTNER = "BOOKING_CANCELLED_FOR_PARTNER";

    // Account / Security
    public static final String ACCOUNT_LOCKED = "ACCOUNT_LOCKED";
    public static final String ACCOUNT_UNLOCKED = "ACCOUNT_UNLOCKED";
    public static final String PASSWORD_CHANGED = "PASSWORD_CHANGED";
    public static final String LOGIN_ALERT = "LOGIN_ALERT";

    // Partner lifecycle
    public static final String PARTNER_APPROVED = "PARTNER_APPROVED";
    public static final String PARTNER_REJECTED = "PARTNER_REJECTED";

    // Partner content (reviews on their properties)
    public static final String REVIEW_NEW_FOR_PARTNER = "REVIEW_NEW_FOR_PARTNER";

    // Admin operations
    public static final String ADMIN_NEW_PARTNER = "ADMIN_NEW_PARTNER";
    public static final String ADMIN_NEW_REVIEW = "ADMIN_NEW_REVIEW";

    // Category constants
    public static final String CATEGORY_SOCIAL = "SOCIAL";
    public static final String CATEGORY_BOOKING = "BOOKING";
    public static final String CATEGORY_ACCOUNT = "ACCOUNT";
    public static final String CATEGORY_PARTNER = "PARTNER";
    public static final String CATEGORY_ADMIN = "ADMIN";

    /** Derives category from a type string. Returns null for unknown types. */
    public static String categoryOf(String type) {
        if (type == null)
            return null;
        return switch (type) {
            case FRIEND_REQUEST, FRIEND_ACCEPTED, PLAN_INVITE, PLAN_MEMBER_JOINED,
                    COMMENT, REPLY_COMMENT, REACT, COMMENT_REACT ->
                CATEGORY_SOCIAL;
            case BOOKING_CONFIRMED, BOOKING_CANCELLED,
                    BOOKING_CANCELLED_BY_PARTNER, BOOKING_EXPIRED,
                    BOOKING_NEW_FOR_PARTNER, BOOKING_CANCELLED_FOR_PARTNER,
                    PAYMENT_SUCCESS, REFUND_PROCESSED ->
                CATEGORY_BOOKING;
            case ACCOUNT_LOCKED, ACCOUNT_UNLOCKED,
                    PASSWORD_CHANGED, LOGIN_ALERT ->
                CATEGORY_ACCOUNT;
            case PARTNER_APPROVED, PARTNER_REJECTED, REVIEW_NEW_FOR_PARTNER -> CATEGORY_PARTNER;
            case ADMIN_NEW_PARTNER, ADMIN_NEW_REVIEW -> CATEGORY_ADMIN;
            default -> null;
        };
    }
}
