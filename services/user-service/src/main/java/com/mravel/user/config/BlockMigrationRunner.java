package com.mravel.user.config;

import com.mravel.user.model.Friendship;
import com.mravel.user.model.FriendshipStatus;
import com.mravel.user.repository.FriendshipRepository;
import com.mravel.user.service.BlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Migration một lần (idempotent): chuyển legacy Friendship.BLOCKED -> UserBlock.
 * Sau khi chuyển, bản ghi Friendship bị xóa nên lần khởi động sau không còn gì để chuyển.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BlockMigrationRunner implements ApplicationRunner {

    private final FriendshipRepository friendshipRepository;
    private final BlockService blockService;

    @Override
    public void run(ApplicationArguments args) {
        List<Friendship> legacy = friendshipRepository.findByStatus(FriendshipStatus.BLOCKED);
        if (legacy.isEmpty())
            return;
        int migrated = blockService.migrateLegacyBlocked(legacy);
        log.info("[BlockMigration] Đã chuyển {} bản ghi Friendship.BLOCKED sang UserBlock", migrated);
    }
}
