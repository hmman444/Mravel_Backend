package com.mravel.notification.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class NotificationRenderHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static Map<String, Object> parse(String dataJson) {
        if (dataJson == null || dataJson.isBlank())
            return Map.of();
        try {
            return MAPPER.readValue(dataJson, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception ignored) {
            return Map.of();
        }
    }

    private static Long asLong(Object v) {
        if (v == null)
            return null;
        if (v instanceof Number n)
            return n.longValue();
        try {
            return Long.parseLong(v.toString());
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String asString(Object v) {
        return v != null ? v.toString() : null;
    }

    public static String deepLink(String type, Long actorId, String dataJson) {
        // Friend notifications
        if ("FRIEND_REQUEST".equals(type) || "FRIEND_ACCEPTED".equals(type)) {
            return actorId != null ? ("/profile/" + actorId) : null;
        }

        Map<String, Object> data = parse(dataJson);

        // ưu tiên deepLink nếu producer đã truyền vào (plan-service đang truyền
        // deepLink)
        String provided = asString(data.get("deepLink"));
        if (provided != null && !provided.isBlank())
            return provided;

        // PLAN_INVITE: token -> /plans/join?token=...
        if ("PLAN_INVITE".equals(type)) {
            String token = asString(data.get("token"));
            if (token != null && !token.isBlank())
                return "/plans/join?token=" + token;

            Long planId = asLong(data.get("planId"));
            if (planId != null)
                return "/plans/" + planId;

            return null;
        }

        // COMMENT: /plans/{planId}?commentId={commentId}
        if ("COMMENT".equals(type)) {
            Long planId = asLong(data.get("planId"));
            Long commentId = asLong(data.get("commentId"));
            if (planId != null && commentId != null)
                return "/plans/" + planId + "?commentId=" + commentId;
            if (planId != null)
                return "/plans/" + planId;
            return null;
        }

        // Optional future types (nếu bạn dùng)
        if ("REPLY_COMMENT".equals(type)) {
            Long planId = asLong(data.get("planId"));
            Long commentId = asLong(data.get("commentId"));
            if (planId != null && commentId != null)
                return "/plans/" + planId + "?commentId=" + commentId;
            if (planId != null)
                return "/plans/" + planId;
            return null;
        }

        if ("REACT".equals(type)) {
            Long planId = asLong(data.get("planId"));
            if (planId != null)
                return "/plans/" + planId;
            return null;
        }

        // Future: BOOKING_*, etc.
        return null;
    }

    public static String image(String type, String actorAvatar, String dataJson) {
        // Friend: dùng avatar actor
        if ("FRIEND_REQUEST".equals(type) || "FRIEND_ACCEPTED".equals(type)) {
            return actorAvatar;
        }

        // PLAN_INVITE/COMMENT cũng muốn hiện avatar như Facebook
        if ("PLAN_INVITE".equals(type) || "COMMENT".equals(type)
                || "REPLY_COMMENT".equals(type) || "REACT".equals(type)) {
            return actorAvatar;
        }

        return null;
    }
}
