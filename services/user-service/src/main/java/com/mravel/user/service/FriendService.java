package com.mravel.user.service;

import com.mravel.user.model.RelationshipType;
import com.mravel.user.model.Friendship;
import com.mravel.user.model.FriendshipStatus;
import com.mravel.user.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendshipRepository friendshipRepository;

    private Long[] normalizePair(Long a, Long b) {
        if (a < b)
            return new Long[] { a, b };
        else
            return new Long[] { b, a };
    }

    @Transactional
    public void sendRequest(Long currentUserId, Long targetUserId) {
        if (Objects.equals(currentUserId, targetUserId)) {
            throw new RuntimeException("Không thể kết bạn với chính mình");
        }

        Long[] pair = normalizePair(currentUserId, targetUserId);
        Optional<Friendship> existingOpt = friendshipRepository.findByUser1IdAndUser2Id(pair[0], pair[1]);

        if (existingOpt.isPresent()) {
            Friendship existing = existingOpt.get();
            if (existing.getStatus() == FriendshipStatus.ACCEPTED) {
                throw new RuntimeException("Hai bạn đã là bạn bè rồi");
            }
            if (existing.getStatus() == FriendshipStatus.PENDING) {
                // nếu phía kia gửi trước → có thể convert thành ACCEPTED
                if (!Objects.equals(existing.getRequestedById(), currentUserId)) {
                    existing.setStatus(FriendshipStatus.ACCEPTED);
                    existing.setUpdatedAt(LocalDateTime.now());
                    friendshipRepository.save(existing);
                    return;
                } else {
                    throw new RuntimeException("Bạn đã gửi lời mời trước đó");
                }
            }
        }

        Friendship friendship = Friendship.builder()
                .user1Id(pair[0])
                .user2Id(pair[1])
                .status(FriendshipStatus.PENDING)
                .requestedById(currentUserId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        friendshipRepository.save(friendship);
    }

    @Transactional
    public void acceptRequest(Long currentUserId, Long otherUserId) {
        Long[] pair = normalizePair(currentUserId, otherUserId);
        Friendship friendship = friendshipRepository
                .findByUser1IdAndUser2Id(pair[0], pair[1])
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lời mời bạn bè"));

        // currentUser phải là người nhận, không phải requestedBy
        if (Objects.equals(friendship.getRequestedById(), currentUserId)) {
            throw new RuntimeException("Bạn không thể tự accept lời mời của chính mình");
        }

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendship.setUpdatedAt(LocalDateTime.now());
        friendshipRepository.save(friendship);
    }

    @Transactional
    public void removeFriendOrCancel(Long currentUserId, Long otherUserId) {
        Long[] pair = normalizePair(currentUserId, otherUserId);
        Friendship friendship = friendshipRepository
                .findByUser1IdAndUser2Id(pair[0], pair[1])
                .orElseThrow(() -> new RuntimeException("Không tồn tại quan hệ bạn bè"));

        friendshipRepository.delete(friendship);
    }

    public RelationshipType getRelationship(Long viewerId, Long profileOwnerId) {
        if (viewerId == null)
            return RelationshipType.NONE;
        if (Objects.equals(viewerId, profileOwnerId))
            return RelationshipType.SELF;

        Long[] pair = normalizePair(viewerId, profileOwnerId);
        Optional<Friendship> opt = friendshipRepository.findByUser1IdAndUser2Id(pair[0], pair[1]);

        if (opt.isEmpty())
            return RelationshipType.NONE;

        Friendship f = opt.get();
        if (f.getStatus() == FriendshipStatus.BLOCKED)
            return RelationshipType.BLOCKED;
        if (f.getStatus() == FriendshipStatus.ACCEPTED)
            return RelationshipType.FRIEND;

        // PENDING
        if (Objects.equals(f.getRequestedById(), viewerId)) {
            return RelationshipType.REQUEST_SENT;
        } else {
            return RelationshipType.REQUEST_RECEIVED;
        }
    }

    public List<Long> getFriendIds(Long userId) {
        return friendshipRepository.findFriendIdsOf(userId);
    }

    public int countMutualFriends(Long viewerId, Long profileOwnerId) {
        List<Long> viewerFriends = friendshipRepository.findFriendIdsOf(viewerId);
        List<Long> ownerFriends = friendshipRepository.findFriendIdsOf(profileOwnerId);

        Set<Long> set = new HashSet<>(viewerFriends);
        set.retainAll(ownerFriends);
        return set.size();
    }
}
