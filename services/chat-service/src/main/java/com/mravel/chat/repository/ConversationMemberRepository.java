package com.mravel.chat.repository;

import com.mravel.chat.model.ConversationMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, Long> {

    List<ConversationMember> findByConversationIdAndLeftAtIsNull(Long conversationId);

    List<ConversationMember> findByUserIdAndLeftAtIsNull(Long userId);

    Optional<ConversationMember> findByConversationIdAndUserId(Long conversationId, Long userId);

    boolean existsByConversationIdAndUserIdAndLeftAtIsNull(Long conversationId, Long userId);

    @Query("SELECT m FROM ConversationMember m WHERE m.conversationId = :convId AND m.leftAt IS NULL ORDER BY m.joinedAt ASC")
    List<ConversationMember> findActiveByConversationId(@Param("convId") Long convId);

    @Query("SELECT m.userId FROM ConversationMember m WHERE m.conversationId = :convId AND m.leftAt IS NULL")
    List<Long> findActiveUserIdsByConversationId(@Param("convId") Long convId);

    /** Batch fetch active members for multiple conversations — eliminates N+1 in list queries. */
    @Query("SELECT m FROM ConversationMember m WHERE m.conversationId IN :convIds AND m.leftAt IS NULL ORDER BY m.conversationId ASC, m.joinedAt ASC")
    List<ConversationMember> findActiveByConversationIdIn(@Param("convIds") List<Long> convIds);

    @Modifying
    @Query("UPDATE ConversationMember m SET m.role = :role WHERE m.conversationId = :convId AND m.userId = :userId")
    void updateRole(@Param("convId") Long convId, @Param("userId") Long userId,
                    @Param("role") ConversationMember.MemberRole role);
}
