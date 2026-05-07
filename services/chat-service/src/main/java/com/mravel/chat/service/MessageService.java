package com.mravel.chat.service;

import com.mravel.chat.client.UserClient;
import com.mravel.chat.dto.ChatDtos.*;
import com.mravel.chat.kafka.ChatEventProducer;
import com.mravel.chat.model.*;
import com.mravel.chat.repository.*;
import com.mravel.common.event.ChatMessageEvent;
import com.mravel.common.event.ChatMessageEvent.MessagePayload;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private static final int DEFAULT_PAGE_SIZE = 30;

    private final MessageRepository messageRepo;
    private final MessageReadStatusRepository readStatusRepo;
    private final ConversationRepository conversationRepo;
    private final ConversationMemberRepository memberRepo;
    private final ChatEventProducer eventProducer;
    private final ConversationService conversationService;
    private final UserClient userClient;

    // ─── Send ─────────────────────────────────────────────────────────────────

    @Transactional
    public MessageResponse sendMessage(Long conversationId, Long userId, SendMessageRequest req) {
        conversationService.requireActiveMember(conversationId, userId);

        Message msg = messageRepo.save(Message.builder()
                .conversationId(conversationId)
                .senderId(userId)
                .content(req.getContent())
                .messageType(Message.MessageType.TEXT)
                .createdAt(Instant.now())
                .deleted(false)
                .build());

        conversationRepo.updateUpdatedAt(conversationId, msg.getCreatedAt());

        UserMiniResponse sender = fetchUser(userId);
        MessageResponse response = toResponse(msg, sender, List.of());

        List<Long> recipientIds = memberRepo.findActiveUserIdsByConversationId(conversationId);
        eventProducer.publish(ChatMessageEvent.builder()
                .eventType("NEW_MESSAGE")
                .conversationId(conversationId)
                .actorId(userId)
                .timestamp(msg.getCreatedAt())
                .recipientIds(recipientIds)
                .message(MessagePayload.builder()
                        .id(msg.getId())
                        .conversationId(conversationId)
                        .senderId(userId)
                        .senderName(sender != null ? sender.getFullname() : "User " + userId)
                        .senderAvatar(sender != null ? sender.getAvatar() : null)
                        .content(msg.getContent())
                        .messageType(msg.getMessageType().name())
                        .createdAt(msg.getCreatedAt())
                        .deleted(false)
                        .build())
                .build());

        return response;
    }

    // ─── Load History ─────────────────────────────────────────────────────────

    public MessagePageResponse getMessages(Long conversationId, Long userId, Long before, int size) {
        conversationService.requireActiveMember(conversationId, userId);
        int pageSize = Math.min(size > 0 ? size : DEFAULT_PAGE_SIZE, 100);
        // Fetch one extra to determine hasMore
        List<Message> raw = (before == null)
                ? messageRepo.findLatest(conversationId, PageRequest.of(0, pageSize + 1))
                : messageRepo.findBeforeCursor(conversationId, before, PageRequest.of(0, pageSize + 1));

        boolean hasMore = raw.size() > pageSize;
        List<Message> page = hasMore ? raw.subList(0, pageSize) : raw;
        Long nextCursor = hasMore ? page.get(page.size() - 1).getId() : null;

        // Reverse to chronological order for the client
        List<Message> chronological = new ArrayList<>(page);
        Collections.reverse(chronological);

        Set<Long> senderIds = chronological.stream().map(Message::getSenderId).collect(Collectors.toSet());
        Map<Long, UserMiniResponse> userMap = fetchUsers(senderIds);

        List<MessageReadStatus> readStatuses = readStatusRepo.findByConversationId(conversationId);

        List<MessageResponse> responses = chronological.stream()
                .map(m -> toResponse(m, userMap.get(m.getSenderId()), buildSeenInfo(m, readStatuses, userMap)))
                .collect(Collectors.toList());

        return MessagePageResponse.builder()
                .messages(responses)
                .nextCursor(nextCursor)
                .hasMore(hasMore)
                .build();
    }

    // ─── Seen / Typing ───────────────────────────────────────────────────────

    @Transactional
    public void markSeen(Long conversationId, Long userId, Long lastMessageId) {
        conversationService.requireActiveMember(conversationId, userId);
        readStatusRepo.upsertLastRead(conversationId, userId, lastMessageId);
        // SEEN_UPDATE is broadcast only to the conversation topic; recipientIds not needed
        eventProducer.publish(ChatMessageEvent.builder()
                .eventType("SEEN_UPDATE")
                .conversationId(conversationId)
                .actorId(userId)
                .timestamp(Instant.now())
                .lastSeenMessageId(lastMessageId)
                .seenByUserId(userId)
                .build());
    }

    public void sendTypingIndicator(Long conversationId, Long userId) {
        conversationService.requireActiveMember(conversationId, userId);
        // TYPING is broadcast only to the conversation topic; recipientIds not needed
        eventProducer.publish(ChatMessageEvent.builder()
                .eventType("TYPING")
                .conversationId(conversationId)
                .actorId(userId)
                .timestamp(Instant.now())
                .typing(true)
                .build());
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private MessageResponse toResponse(Message m, UserMiniResponse sender, List<SeenInfo> seenBy) {
        return MessageResponse.builder()
                .id(m.getId())
                .conversationId(m.getConversationId())
                .senderId(m.getSenderId())
                .senderName(sender != null ? sender.getFullname() : "User " + m.getSenderId())
                .senderAvatar(sender != null ? sender.getAvatar() : null)
                .content(m.isDeleted() ? null : m.getContent())
                .messageType(m.getMessageType() != null ? m.getMessageType().name() : "TEXT")
                .createdAt(m.getCreatedAt())
                .deleted(m.isDeleted())
                .seenBy(seenBy)
                .build();
    }

    private List<SeenInfo> buildSeenInfo(Message message, List<MessageReadStatus> readStatuses,
                                          Map<Long, UserMiniResponse> userMap) {
        return readStatuses.stream()
                .filter(rs -> rs.getLastReadMessageId() != null && rs.getLastReadMessageId() >= message.getId()
                        && !rs.getUserId().equals(message.getSenderId()))
                .map(rs -> {
                    UserMiniResponse u = userMap.get(rs.getUserId());
                    return SeenInfo.builder()
                            .userId(rs.getUserId())
                            .fullname(u != null ? u.getFullname() : "User " + rs.getUserId())
                            .avatar(u != null ? u.getAvatar() : null)
                            .readAt(rs.getReadAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private UserMiniResponse fetchUser(Long userId) {
        try {
            List<UserMiniResponse> result = userClient.batchMini(List.of(userId));
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            log.warn("user-service fetch failed for userId={}: {}", userId, e.getMessage());
            return null;
        }
    }

    private Map<Long, UserMiniResponse> fetchUsers(Collection<Long> ids) {
        if (ids.isEmpty()) return Map.of();
        try {
            return userClient.batchMini(new ArrayList<>(ids)).stream()
                    .collect(Collectors.toMap(UserMiniResponse::getId, u -> u));
        } catch (Exception e) {
            log.warn("user-service batchMini failed: {}", e.getMessage());
            return Map.of();
        }
    }
}
