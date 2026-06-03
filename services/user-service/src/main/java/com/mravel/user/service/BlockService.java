package com.mravel.user.service;

import com.mravel.user.dto.UserMiniResponse;
import com.mravel.user.model.Friendship;
import com.mravel.user.model.UserBlock;
import com.mravel.user.model.UserProfile;
import com.mravel.user.repository.FriendshipRepository;
import com.mravel.user.repository.UserBlockRepository;
import com.mravel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Quản lý chặn người dùng (Facebook-style, vô hình hai chiều).
 * Nguồn sự thật duy nhất của block toàn hệ thống.
 */
@Service
@RequiredArgsConstructor
public class BlockService {

    private final UserBlockRepository userBlockRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    private Long[] normalizePair(Long a, Long b) {
        return (a < b) ? new Long[] { a, b } : new Long[] { b, a };
    }

    @Transactional
    public void blockUser(Long blockerId, Long blockedId) {
        if (Objects.equals(blockerId, blockedId)) {
            throw new IllegalArgumentException("Không thể tự chặn chính mình");
        }
        // 1) Gỡ mọi quan hệ bạn bè / lời mời đang chờ giữa cặp (PENDING/ACCEPTED/legacy BLOCKED)
        Long[] pair = normalizePair(blockerId, blockedId);
        friendshipRepository.findByUser1IdAndUser2Id(pair[0], pair[1])
                .ifPresent(friendshipRepository::delete);

        // 2) Tạo block nếu chưa có (idempotent)
        if (!userBlockRepository.existsByBlockerIdAndBlockedId(blockerId, blockedId)) {
            userBlockRepository.save(UserBlock.builder()
                    .blockerId(blockerId)
                    .blockedId(blockedId)
                    .createdAt(LocalDateTime.now())
                    .build());
        }
    }

    @Transactional
    public void unblockUser(Long blockerId, Long blockedId) {
        // Bỏ chặn KHÔNG khôi phục bạn bè (đã xóa) — hai người trở thành người lạ.
        userBlockRepository.deleteByBlockerIdAndBlockedId(blockerId, blockedId);
    }

    /** Có quan hệ chặn theo bất kỳ chiều nào giữa a và b. */
    public boolean isBlocked(Long a, Long b) {
        if (a == null || b == null)
            return false;
        return userBlockRepository.existsBetween(a, b);
    }

    /** Tập id "vô hình lẫn nhau" với userId (hợp nhất 2 chiều) — cho các service khác lọc. */
    public List<Long> getAllBlockedIds(Long userId) {
        return userBlockRepository.findAllRelatedBlockedIds(userId);
    }

    /** Danh sách những người mà chính blockerId đã chặn (cho trang quản lý). */
    public List<UserMiniResponse> listBlocked(Long blockerId) {
        List<Long> ids = userBlockRepository.findBlockedIds(blockerId);
        if (ids.isEmpty())
            return List.of();
        List<UserProfile> users = userRepository.findAllById(ids);
        return users.stream()
                .map(u -> UserMiniResponse.builder()
                        .id(u.getId())
                        .fullname(u.getFullname())
                        .avatar(u.getAvatar())
                        .build())
                .toList();
    }

    /**
     * Migration một lần: chuyển các bản ghi Friendship.BLOCKED (legacy) sang UserBlock.
     * Idempotent vì sau khi chuyển sẽ xóa Friendship; lần chạy sau không còn gì để chuyển.
     */
    @Transactional
    public int migrateLegacyBlocked(List<Friendship> legacy) {
        int migrated = 0;
        for (Friendship f : legacy) {
            Long blocker = f.getRequestedById() != null ? f.getRequestedById() : f.getUser1Id();
            Long blocked = Objects.equals(blocker, f.getUser1Id()) ? f.getUser2Id() : f.getUser1Id();
            if (!userBlockRepository.existsByBlockerIdAndBlockedId(blocker, blocked)) {
                userBlockRepository.save(UserBlock.builder()
                        .blockerId(blocker)
                        .blockedId(blocked)
                        .createdAt(f.getCreatedAt() != null ? f.getCreatedAt() : LocalDateTime.now())
                        .build());
                migrated++;
            }
            friendshipRepository.delete(f);
        }
        return migrated;
    }
}
