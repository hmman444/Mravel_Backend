package com.mravel.chat.repository;

import com.mravel.chat.model.MessageReadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MessageReadStatusRepository extends JpaRepository<MessageReadStatus, Long> {

    Optional<MessageReadStatus> findByConversationIdAndUserId(Long conversationId, Long userId);

    List<MessageReadStatus> findByConversationId(Long conversationId);

    @Query("SELECT rs FROM MessageReadStatus rs WHERE rs.userId = :userId AND rs.conversationId IN :convIds")
    List<MessageReadStatus> findByUserIdAndConversationIdIn(@Param("userId") Long userId,
                                                            @Param("convIds") List<Long> convIds);

    @Modifying
    @Query(value = """
        INSERT INTO message_read_status (conversation_id, user_id, last_read_message_id, read_at)
        VALUES (:convId, :userId, :msgId, NOW())
        ON DUPLICATE KEY UPDATE last_read_message_id = :msgId, read_at = NOW()
        """, nativeQuery = true)
    void upsertLastRead(@Param("convId") Long convId, @Param("userId") Long userId, @Param("msgId") Long msgId);
}
