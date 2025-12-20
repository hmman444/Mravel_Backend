package com.mravel.plan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mravel.plan.client.NotificationClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanNotificationService {

    private final NotificationClient notificationClient;

    private void safeCreate(Long recipientId,
            Long actorId,
            String type,
            String title,
            String message,
            Map<String, Object> data) {
        if (recipientId == null || actorId == null)
            return;
        if (recipientId.equals(actorId))
            return; // không tự notify
        try {
            notificationClient.createNotification(recipientId, actorId, type, title, message, data);
        } catch (Exception ignored) {
        }
    }

    public void notifyPlanReact(Long actorId, Long recipientId, Long planId, String reactionKey) {
        Map<String, Object> data = new HashMap<>();
        data.put("actorId", actorId);
        data.put("planId", planId);
        data.put("reactionKey", reactionKey); // để FE hiển thị icon react nếu muốn
        data.put("deepLink", "/plans/" + planId);

        safeCreate(
                recipientId,
                actorId,
                "REACT",
                "Thích",
                "đã thích kế hoạch của bạn",
                data);
    }

    public void notifyPlanComment(Long actorId, Long recipientId, Long planId, Long commentId) {
        Map<String, Object> data = new HashMap<>();
        data.put("actorId", actorId);
        data.put("planId", planId);
        data.put("commentId", commentId);
        data.put("deepLink", "/plans/" + planId + "?commentId=" + commentId);

        safeCreate(
                recipientId,
                actorId,
                "COMMENT",
                "Bình luận",
                "đã bình luận về kế hoạch của bạn",
                data);
    }

    public void notifyPlanReplyComment(Long actorId,
            Long parentCommentOwnerId,
            Long planId,
            Long parentCommentId,
            Long replyCommentId) {
        Map<String, Object> data = new HashMap<>();
        data.put("actorId", actorId);
        data.put("planId", planId);
        data.put("parentId", parentCommentId);
        data.put("commentId", replyCommentId);
        data.put("deepLink", "/plans/" + planId + "?commentId=" + replyCommentId);

        safeCreate(
                parentCommentOwnerId,
                actorId,
                "REPLY_COMMENT",
                "Trả lời",
                "đã trả lời bình luận của bạn",
                data);
    }

    public void notifyPlanInvite(Long actorId, Long recipientId, Long planId, String role, String token) {
        Map<String, Object> data = new HashMap<>();
        data.put("actorId", actorId);
        data.put("planId", planId);
        data.put("role", role);
        data.put("token", token);
        data.put("deepLink", "/plans/join?token=" + token);

        safeCreate(
                recipientId,
                actorId,
                "PLAN_INVITE",
                "Lời mời tham gia",
                "đã mời bạn tham gia kế hoạch",
                data);
    }
}
