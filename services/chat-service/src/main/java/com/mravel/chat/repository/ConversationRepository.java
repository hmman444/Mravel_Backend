package com.mravel.chat.repository;

import com.mravel.chat.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query(value = """
        SELECT c.id FROM conversations c
        JOIN conversation_members m1 ON m1.conversation_id = c.id AND m1.user_id = :userId1 AND m1.left_at IS NULL
        JOIN conversation_members m2 ON m2.conversation_id = c.id AND m2.user_id = :userId2 AND m2.left_at IS NULL
        WHERE c.type = 'PRIVATE'
        LIMIT 1
        """, nativeQuery = true)
    Optional<Long> findPrivateConversationId(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    /** Direct UPDATE to avoid SELECT+UPDATE round-trip on every message send. */
    @Modifying
    @Query("UPDATE Conversation c SET c.updatedAt = :ts WHERE c.id = :id")
    void updateUpdatedAt(@Param("id") Long id, @Param("ts") Instant ts);
}
