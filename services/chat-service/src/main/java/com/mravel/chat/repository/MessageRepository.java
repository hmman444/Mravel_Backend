package com.mravel.chat.repository;

import com.mravel.chat.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    /** Cursor-based pagination: messages before a given ID (newest first, then reversed by caller). */
    @Query("SELECT m FROM Message m WHERE m.conversationId = :convId AND m.id < :before ORDER BY m.id DESC")
    List<Message> findBeforeCursor(@Param("convId") Long convId, @Param("before") Long before, Pageable pageable);

    /** Initial load (no cursor): newest messages first. */
    @Query("SELECT m FROM Message m WHERE m.conversationId = :convId ORDER BY m.id DESC")
    List<Message> findLatest(@Param("convId") Long convId, Pageable pageable);

    Optional<Message> findTopByConversationIdOrderByIdDesc(Long conversationId);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.conversationId = :convId AND m.id > :lastReadId AND m.senderId != :userId")
    long countUnread(@Param("convId") Long convId, @Param("lastReadId") Long lastReadId, @Param("userId") Long userId);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.conversationId = :convId AND m.senderId != :userId")
    long countAllUnread(@Param("convId") Long convId, @Param("userId") Long userId);
}
