package com.mravel.chat.service;

import com.mravel.chat.client.UserClient;
import com.mravel.chat.dto.ChatDtos.*;
import com.mravel.chat.kafka.ChatEventProducer;
import com.mravel.chat.model.*;
import com.mravel.chat.model.Conversation.ConversationType;
import com.mravel.chat.model.ConversationMember.MemberRole;
import com.mravel.chat.repository.*;
import com.mravel.common.event.ChatMessageEvent;
import com.mravel.common.event.ChatMessageEvent.ConversationPayload;
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
public class ConversationService {

    private final ConversationRepository conversationRepo;
    private final ConversationMemberRepository memberRepo;
    private final MessageRepository messageRepo;
    private final MessageReadStatusRepository readStatusRepo;
    private final ChatEventProducer eventProducer;
    private final UserClient userClient;

    // ─── Create ──────────────────────────────────────────────────────────────

    @Transactional
    public ConversationResponse findOrCreatePrivate(Long userId, Long recipientId) {
        if (userId.equals(recipientId)) {
            throw new IllegalArgumentException("Không thể tạo cuộc trò chuyện với chính mình");
        }
        Optional<Long> existingId = conversationRepo.findPrivateConversationId(userId, recipientId);
        if (existingId.isPresent()) {
            return buildResponse(conversationRepo.findById(existingId.get()).orElseThrow(), userId);
        }

        Conversation conv = conversationRepo.save(Conversation.builder()
                .type(ConversationType.PRIVATE)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build());

        memberRepo.save(ConversationMember.builder()
                .conversationId(conv.getId()).userId(userId)
                .role(MemberRole.MEMBER).joinedAt(Instant.now()).build());
        memberRepo.save(ConversationMember.builder()
                .conversationId(conv.getId()).userId(recipientId)
                .role(MemberRole.MEMBER).joinedAt(Instant.now()).build());

        return buildResponse(conv, userId);
    }

    @Transactional
    public ConversationResponse createGroup(Long userId, CreateGroupRequest req) {
        Conversation conv = conversationRepo.save(Conversation.builder()
                .type(ConversationType.GROUP)
                .name(req.getName())
                .ownerId(userId)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build());

        memberRepo.save(ConversationMember.builder()
                .conversationId(conv.getId()).userId(userId)
                .role(MemberRole.OWNER).joinedAt(Instant.now()).build());

        for (Long memberId : req.getMemberIds()) {
            if (!memberId.equals(userId)) {
                memberRepo.save(ConversationMember.builder()
                        .conversationId(conv.getId()).userId(memberId)
                        .role(MemberRole.MEMBER).joinedAt(Instant.now()).build());
            }
        }
        return buildResponse(conv, userId);
    }

    // ─── List ─────────────────────────────────────────────────────────────────

    public List<ConversationResponse> listConversations(Long userId) {
        List<ConversationMember> myMemberships = memberRepo.findByUserIdAndLeftAtIsNull(userId);

        List<Long> convIds = myMemberships.stream().map(ConversationMember::getConversationId).toList();
        if (convIds.isEmpty()) return List.of();

        Map<Long, Conversation> convMap = conversationRepo.findAllById(convIds).stream()
                .collect(Collectors.toMap(Conversation::getId, c -> c));

        // Collect all member userIds for batch user-service call
        Map<Long, List<ConversationMember>> membersByConv = new HashMap<>();
        for (Long cid : convIds) {
            membersByConv.put(cid, memberRepo.findActiveByConversationId(cid));
        }
        Set<Long> allUserIds = membersByConv.values().stream()
                .flatMap(List::stream).map(ConversationMember::getUserId)
                .collect(Collectors.toSet());
        Map<Long, UserMiniResponse> userMap = fetchUsers(allUserIds);

        Map<Long, MessageReadStatus> readStatusMap = readStatusRepo
                .findByUserIdAndConversationIdIn(userId, convIds).stream()
                .collect(Collectors.toMap(MessageReadStatus::getConversationId, rs -> rs));

        return myMemberships.stream()
                .map(membership -> {
                    Conversation conv = convMap.get(membership.getConversationId());
                    if (conv == null) return null;
                    List<ConversationMember> members = membersByConv.get(conv.getId());
                    MessageReadStatus rs = readStatusMap.get(conv.getId());
                    return buildResponseFull(conv, userId, members, userMap, rs);
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(ConversationResponse::getUpdatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    // ─── Get Detail ───────────────────────────────────────────────────────────

    public ConversationResponse getDetail(Long conversationId, Long userId) {
        Conversation conv = getConversationOrThrow(conversationId);
        requireActiveMember(conversationId, userId);
        List<ConversationMember> members = memberRepo.findActiveByConversationId(conversationId);
        Set<Long> userIds = members.stream().map(ConversationMember::getUserId).collect(Collectors.toSet());
        Map<Long, UserMiniResponse> userMap = fetchUsers(userIds);
        MessageReadStatus rs = readStatusRepo.findByConversationIdAndUserId(conversationId, userId).orElse(null);
        return buildResponseFull(conv, userId, members, userMap, rs);
    }

    // ─── Group Management ─────────────────────────────────────────────────────

    @Transactional
    public ConversationResponse renameGroup(Long conversationId, Long userId, String name) {
        Conversation conv = getGroupOrThrow(conversationId);
        ConversationMember caller = requireActiveMember(conversationId, userId);
        requireAtLeastAdmin(caller);

        conv.setName(name);
        conv.setUpdatedAt(Instant.now());
        conversationRepo.save(conv);

        UserMiniResponse actor = fetchUsers(List.of(userId)).get(userId);
        String actorName = actor != null ? actor.getFullname() : "User " + userId;
        publishSystemMessage(conv, userId, actorName + " đã cập nhật thông tin nhóm", List.of());
        return buildResponse(conv, userId);
    }

    @Transactional
    public void addMembers(Long conversationId, Long userId, List<Long> newUserIds) {
        Conversation conv = getGroupOrThrow(conversationId);
        ConversationMember caller = requireActiveMember(conversationId, userId);
        requireAtLeastAdmin(caller);

        for (Long newUserId : newUserIds) {
            Optional<ConversationMember> existing = memberRepo.findByConversationIdAndUserId(conversationId, newUserId);
            if (existing.isPresent()) {
                ConversationMember m = existing.get();
                if (!m.isActive()) {
                    m.setLeftAt(null);
                    m.setJoinedAt(Instant.now());
                    memberRepo.save(m);
                }
            } else {
                memberRepo.save(ConversationMember.builder()
                        .conversationId(conversationId).userId(newUserId)
                        .role(MemberRole.MEMBER).joinedAt(Instant.now()).build());
            }
        }
        conv.setUpdatedAt(Instant.now());
        conversationRepo.save(conv);

        Map<Long, UserMiniResponse> userMap = fetchUsers(combineIds(userId, newUserIds));
        String actorName = userName(userMap, userId);
        List<String> targetNames = newUserIds.stream().map(id -> userName(userMap, id)).toList();
        String content = targetNames.isEmpty()
            ? actorName + " đã thêm thành viên vào nhóm"
            : actorName + " đã thêm " + String.join(", ", targetNames) + " vào nhóm";
        publishSystemMessage(conv, userId, content, newUserIds);
    }

    @Transactional
    public void removeMember(Long conversationId, Long userId, Long targetUserId) {
        Conversation conv = getGroupOrThrow(conversationId);
        ConversationMember caller = requireActiveMember(conversationId, userId);
        requireAtLeastAdmin(caller);

        ConversationMember target = memberRepo.findByConversationIdAndUserId(conversationId, targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Thành viên không tồn tại"));

        if (!target.isActive()) throw new IllegalArgumentException("Thành viên đã rời nhóm");
        if (target.getRole() == MemberRole.OWNER) throw new IllegalArgumentException("Không thể xóa chủ nhóm");
        if (caller.getRole() == MemberRole.ADMIN && target.getRole() == MemberRole.ADMIN) {
            throw new IllegalArgumentException("Admin không thể xóa admin khác");
        }
        target.setLeftAt(Instant.now());
        memberRepo.save(target);
        conv.setUpdatedAt(Instant.now());
        conversationRepo.save(conv);

        Map<Long, UserMiniResponse> userMap = fetchUsers(combineIds(userId, List.of(targetUserId)));
        String actorName = userName(userMap, userId);
        String targetName = userName(userMap, targetUserId);
        publishSystemMessage(conv, userId, actorName + " đã xóa " + targetName + " khỏi nhóm", List.of(targetUserId));
    }

    @Transactional
    public void leaveConversation(Long conversationId, Long userId) {
        Conversation conv = getConversationOrThrow(conversationId);
        ConversationMember member = requireActiveMember(conversationId, userId);

        if (conv.getType() == ConversationType.PRIVATE) {
            throw new IllegalArgumentException("Không thể rời cuộc trò chuyện riêng tư");
        }

        if (member.getRole() == MemberRole.OWNER) {
            List<ConversationMember> others = memberRepo.findActiveByConversationId(conversationId)
                    .stream().filter(m -> !m.getUserId().equals(userId)).toList();
            if (!others.isEmpty()) {
                ConversationMember newOwner = others.stream()
                        .filter(m -> m.getRole() == MemberRole.ADMIN)
                        .findFirst()
                        .orElse(others.get(0));
                newOwner.setRole(MemberRole.OWNER);
                memberRepo.save(newOwner);
                conv.setOwnerId(newOwner.getUserId());
            }
        }

        member.setLeftAt(Instant.now());
        memberRepo.save(member);
        conv.setUpdatedAt(Instant.now());
        conversationRepo.save(conv);

        UserMiniResponse actor = fetchUsers(List.of(userId)).get(userId);
        String actorName = actor != null ? actor.getFullname() : "User " + userId;
        publishSystemMessage(conv, userId, actorName + " đã rời khỏi nhóm", List.of(userId));
    }

    @Transactional
    public void changeMemberRole(Long conversationId, Long userId, Long targetUserId, MemberRole newRole) {
        Conversation conv = getGroupOrThrow(conversationId);
        ConversationMember caller = requireActiveMember(conversationId, userId);
        ConversationMember target = memberRepo.findByConversationIdAndUserId(conversationId, targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Thành viên không tồn tại"));

        if (!target.isActive()) throw new IllegalArgumentException("Thành viên đã rời nhóm");
        if (target.getRole() == MemberRole.OWNER) throw new IllegalArgumentException("Không thể thay đổi vai trò chủ nhóm");
        if (newRole == MemberRole.OWNER) throw new IllegalArgumentException("Dùng API chuyển quyền chủ nhóm");

        if (caller.getRole() == MemberRole.ADMIN && newRole == MemberRole.ADMIN && target.getRole() == MemberRole.MEMBER) {
            // Admin can promote member to admin
        } else if (caller.getRole() != MemberRole.OWNER) {
            throw new IllegalArgumentException("Không có quyền thay đổi vai trò thành viên");
        }

        memberRepo.updateRole(conversationId, targetUserId, newRole);
        conv.setUpdatedAt(Instant.now());
        conversationRepo.save(conv);

        Map<Long, UserMiniResponse> userMap = fetchUsers(combineIds(userId, List.of(targetUserId)));
        String actorName = userName(userMap, userId);
        String targetName = userName(userMap, targetUserId);
        publishSystemMessage(conv, userId, actorName + " đã thay đổi vai trò của " + targetName, List.of(targetUserId));
    }

    @Transactional
    public void transferOwnership(Long conversationId, Long userId, Long newOwnerId) {
        Conversation conv = getGroupOrThrow(conversationId);
        ConversationMember caller = requireActiveMember(conversationId, userId);
        if (caller.getRole() != MemberRole.OWNER) {
            throw new IllegalArgumentException("Chỉ chủ nhóm mới có thể chuyển quyền");
        }
        ConversationMember newOwnerMember = memberRepo.findByConversationIdAndUserId(conversationId, newOwnerId)
                .orElseThrow(() -> new IllegalArgumentException("Thành viên không tồn tại"));
        if (!newOwnerMember.isActive()) throw new IllegalArgumentException("Thành viên đã rời nhóm");

        caller.setRole(MemberRole.ADMIN);
        memberRepo.save(caller);
        newOwnerMember.setRole(MemberRole.OWNER);
        memberRepo.save(newOwnerMember);
        conv.setOwnerId(newOwnerId);
        conv.setUpdatedAt(Instant.now());
        conversationRepo.save(conv);

        Map<Long, UserMiniResponse> userMap = fetchUsers(combineIds(userId, List.of(newOwnerId)));
        String actorName = userName(userMap, userId);
        String newOwnerName = userName(userMap, newOwnerId);
        publishSystemMessage(conv, userId, actorName + " đã chuyển quyền chủ nhóm cho " + newOwnerName, List.of(newOwnerId));
    }

    // ─── Access Check (for realtime-service) ─────────────────────────────────

    public boolean hasAccess(Long conversationId, Long userId) {
        return memberRepo.existsByConversationIdAndUserIdAndLeftAtIsNull(conversationId, userId);
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    private Conversation getConversationOrThrow(Long id) {
        return conversationRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuộc trò chuyện không tồn tại"));
    }

    private Conversation getGroupOrThrow(Long id) {
        Conversation conv = getConversationOrThrow(id);
        if (conv.getType() != ConversationType.GROUP) {
            throw new IllegalArgumentException("Thao tác này chỉ áp dụng cho nhóm chat");
        }
        return conv;
    }

    ConversationMember requireActiveMember(Long conversationId, Long userId) {
        ConversationMember m = memberRepo.findByConversationIdAndUserId(conversationId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Bạn không phải thành viên cuộc trò chuyện này"));
        if (!m.isActive()) throw new IllegalArgumentException("Bạn đã rời cuộc trò chuyện này");
        return m;
    }

    private void requireAtLeastAdmin(ConversationMember member) {
        if (member.getRole() == MemberRole.MEMBER) {
            throw new IllegalArgumentException("Bạn không có quyền thực hiện thao tác này");
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

    private ConversationResponse buildResponse(Conversation conv, Long userId) {
        List<ConversationMember> members = memberRepo.findActiveByConversationId(conv.getId());
        Set<Long> userIds = members.stream().map(ConversationMember::getUserId).collect(Collectors.toSet());
        Map<Long, UserMiniResponse> userMap = fetchUsers(userIds);
        MessageReadStatus rs = readStatusRepo.findByConversationIdAndUserId(conv.getId(), userId).orElse(null);
        return buildResponseFull(conv, userId, members, userMap, rs);
    }

    private ConversationResponse buildResponseFull(Conversation conv, Long userId,
                                                     List<ConversationMember> members,
                                                     Map<Long, UserMiniResponse> userMap,
                                                     MessageReadStatus readStatus) {
        Optional<Message> lastMsg = messageRepo.findTopByConversationIdOrderByIdDesc(conv.getId());

        long unread = computeUnread(conv.getId(), userId, readStatus);

        List<MemberResponse> memberResponses = members.stream().map(m -> {
            UserMiniResponse u = userMap.get(m.getUserId());
            return MemberResponse.builder()
                    .userId(m.getUserId())
                    .fullname(u != null ? u.getFullname() : "User " + m.getUserId())
                    .avatar(u != null ? u.getAvatar() : null)
                    .role(m.getRole())
                    .joinedAt(m.getJoinedAt())
                    .build();
        }).collect(Collectors.toList());

        // For PRIVATE: override name/avatar with the other user's info
        String displayName = conv.getName();
        String displayAvatar = conv.getAvatarUrl();
        if (conv.getType() == ConversationType.PRIVATE) {
            Optional<ConversationMember> other = members.stream()
                    .filter(m -> !m.getUserId().equals(userId)).findFirst();
            if (other.isPresent()) {
                UserMiniResponse otherUser = userMap.get(other.get().getUserId());
                if (otherUser != null) {
                    displayName = otherUser.getFullname();
                    displayAvatar = otherUser.getAvatar();
                }
            }
        }

        MessageSummary lastMessageSummary = null;
        if (lastMsg.isPresent()) {
            Message m = lastMsg.get();
            UserMiniResponse sender = userMap.get(m.getSenderId());
            lastMessageSummary = MessageSummary.builder()
                    .id(m.getId())
                    .senderId(m.getSenderId())
                    .senderName(sender != null ? sender.getFullname() : "User " + m.getSenderId())
                    .content(m.isDeleted() ? null : m.getContent())
                    .messageType(m.getMessageType() != null ? m.getMessageType().name() : "TEXT")
                    .createdAt(m.getCreatedAt())
                    .deleted(m.isDeleted())
                    .build();
        }

        return ConversationResponse.builder()
                .id(conv.getId())
                .type(conv.getType().name())
                .name(displayName)
                .avatarUrl(displayAvatar)
                .ownerId(conv.getOwnerId())
                .memberCount(members.size())
                .lastMessage(lastMessageSummary)
                .unreadCount(unread)
                .updatedAt(conv.getUpdatedAt())
                .members(memberResponses)
                .build();
    }

    private long computeUnread(Long conversationId, Long userId, MessageReadStatus readStatus) {
        if (readStatus == null || readStatus.getLastReadMessageId() == null) {
            return messageRepo.countAllUnread(conversationId, userId);
        }
        return messageRepo.countUnread(conversationId, readStatus.getLastReadMessageId(), userId);
    }

        private void publishSystemMessage(Conversation conv, Long actorId, String content, List<Long> affectedUserIds) {
        Instant now = Instant.now();
        Message msg = messageRepo.save(Message.builder()
            .conversationId(conv.getId())
            .senderId(actorId)
            .content(content)
            .messageType(Message.MessageType.SYSTEM)
            .createdAt(now)
            .deleted(false)
            .build());

        List<Long> recipientIds = memberRepo.findActiveUserIdsByConversationId(conv.getId());
        UserMiniResponse actor = fetchUsers(List.of(actorId)).get(actorId);
        String senderName = actor != null ? actor.getFullname() : "User " + actorId;
        eventProducer.publish(ChatMessageEvent.builder()
            .eventType("NEW_MESSAGE")
                .conversationId(conv.getId())
                .actorId(actorId)
            .timestamp(now)
                .affectedUserIds(affectedUserIds)
                .recipientIds(recipientIds)
            .message(MessagePayload.builder()
                .id(msg.getId())
                .conversationId(conv.getId())
                .senderId(actorId)
                .senderName(senderName)
                .senderAvatar(actor != null ? actor.getAvatar() : null)
                .content(content)
                .messageType(Message.MessageType.SYSTEM.name())
                .createdAt(now)
                .deleted(false)
                .build())
                .conversation(ConversationPayload.builder()
                        .id(conv.getId())
                        .type(conv.getType().name())
                        .name(conv.getName())
                        .avatarUrl(conv.getAvatarUrl())
                        .ownerId(conv.getOwnerId())
                        .build())
                .build());
    }

    private Set<Long> combineIds(Long firstId, Collection<Long> otherIds) {
        Set<Long> ids = new LinkedHashSet<>();
        ids.add(firstId);
        ids.addAll(otherIds);
        return ids;
    }

    private String userName(Map<Long, UserMiniResponse> userMap, Long userId) {
        UserMiniResponse user = userMap.get(userId);
        return user != null ? user.getFullname() : "User " + userId;
    }
}
