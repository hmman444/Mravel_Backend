package com.mravel.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.notification.client.UserClient;
import com.mravel.notification.dto.NotificationDtos;
import com.mravel.notification.dto.NotificationDtos.*;
import com.mravel.notification.dto.UserMiniResponse;
import com.mravel.notification.kafka.NotificationEventProducer;
import com.mravel.notification.model.Notification;
import com.mravel.notification.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repo;
    private final ObjectMapper objectMapper;
    private final NotificationEventProducer producer;
    private final UserClient userClient;

    @Transactional
    public NotificationResponse create(CreateNotificationRequest req) {
        try {
            String dataJson = req.getData() != null ? objectMapper.writeValueAsString(req.getData()) : null;

            Notification noti = Notification.builder()
                    .recipientId(req.getRecipientId())
                    .actorId(req.getActorId())
                    .type(req.getType())
                    .title(req.getTitle())
                    .message(req.getMessage())
                    .dataJson(dataJson)
                    .read(false)
                    .createdAt(Instant.now())
                    .build();

            repo.save(noti);

            long unread = repo.countByRecipientIdAndReadFalse(req.getRecipientId());

            Map<Long, UserMiniResponse> actorMap = Map.of();
            if (noti.getActorId() != null) {
                List<UserMiniResponse> minis = userClient.batchMini(List.of(noti.getActorId()));
                actorMap = minis.stream()
                        .collect(java.util.stream.Collectors.toMap(UserMiniResponse::getId, x -> x));
            }

            NotificationResponse resp = toResponse(noti, unread, actorMap);

            producer.publish(NotificationEvent.builder()
                    .eventType("NOTI_CREATED")
                    .notificationId(noti.getId())
                    .recipientId(noti.getRecipientId())
                    .actorId(noti.getActorId())
                    .createdAt(noti.getCreatedAt())
                    .notification(resp)
                    .build());

            return resp;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create notification", e);
        }
    }

    public Page<NotificationResponse> list(Long recipientId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        long unread = repo.countByRecipientIdAndReadFalse(recipientId);

        Page<Notification> p = repo.findByRecipientIdOrderByCreatedAtDesc(recipientId, pageable);

        // 1) batch lấy actorIds
        List<Long> actorIds = p.getContent().stream()
                .map(Notification::getActorId)
                .filter(java.util.Objects::nonNull)
                .distinct()
                .toList();

        java.util.Map<Long, UserMiniResponse> actorMap = java.util.Map.of();
        if (!actorIds.isEmpty()) {
            List<UserMiniResponse> minis = userClient.batchMini(actorIds);
            actorMap = minis.stream().collect(java.util.stream.Collectors.toMap(UserMiniResponse::getId, x -> x));
        }

        final java.util.Map<Long, UserMiniResponse> finalActorMap = actorMap;

        return p.map(n -> toResponse(n, unread, finalActorMap));
    }

    private NotificationResponse toResponse(Notification n, long unreadCount, Map<Long, UserMiniResponse> actorMap) {

        UserMiniResponse mini = (n.getActorId() != null) ? actorMap.get(n.getActorId()) : null;

        NotificationDtos.ActorSummary actor = (mini == null) ? null
                : NotificationDtos.ActorSummary.builder()
                        .id(mini.getId())
                        .fullname(mini.getFullname())
                        .avatar(mini.getAvatar())
                        .build();

        String deepLink = NotificationRenderHelper.deepLink(n.getType(), n.getActorId(), n.getDataJson());
        String image = NotificationRenderHelper.image(n.getType(), actor != null ? actor.getAvatar() : null,
                n.getDataJson());

        return NotificationResponse.builder()
                .id(n.getId())
                .recipientId(n.getRecipientId())
                .actorId(n.getActorId())
                .actor(actor)
                .type(n.getType())
                .title(n.getTitle())
                .message(n.getMessage())
                .deepLink(deepLink)
                .image(image)
                .dataJson(n.getDataJson())
                .read(n.isRead())
                .createdAt(n.getCreatedAt())
                .unreadCount(unreadCount)
                .build();
    }

    @Transactional
    public long markRead(Long recipientId, Long notificationId) {
        Notification n = repo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (!recipientId.equals(n.getRecipientId())) {
            throw new RuntimeException("Forbidden");
        }

        if (!n.isRead()) {
            n.setRead(true);
            repo.save(n);
        }

        return repo.countByRecipientIdAndReadFalse(recipientId);
    }

    @Transactional
    public long markAllRead(Long recipientId) {
        // tối ưu: update query bulk sau, tạm làm đơn giản
        repo.findByRecipientIdOrderByCreatedAtDesc(recipientId, PageRequest.of(0, 2000))
                .forEach(n -> {
                    if (!n.isRead())
                        n.setRead(true);
                });
        // saveAll
        // (JPA dirty-check sẽ flush)
        return repo.countByRecipientIdAndReadFalse(recipientId);
    }

    private NotificationResponse toResponse(Notification n, long unreadCount) {
        return NotificationResponse.builder()
                .id(n.getId())
                .recipientId(n.getRecipientId())
                .actorId(n.getActorId())
                .type(n.getType())
                .title(n.getTitle())
                .message(n.getMessage())
                .dataJson(n.getDataJson())
                .read(n.isRead())
                .createdAt(n.getCreatedAt())
                .unreadCount(unreadCount)
                .build();
    }
}
