package com.mravel.user.repository;

import com.mravel.user.model.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {

    Optional<UserBlock> findByBlockerIdAndBlockedId(Long blockerId, Long blockedId);

    boolean existsByBlockerIdAndBlockedId(Long blockerId, Long blockedId);

    void deleteByBlockerIdAndBlockedId(Long blockerId, Long blockedId);

    /** Những người mà userId đã chặn. */
    @Query("select b.blockedId from UserBlock b where b.blockerId = :userId")
    List<Long> findBlockedIds(@Param("userId") Long userId);

    /** Những người đã chặn userId. */
    @Query("select b.blockerId from UserBlock b where b.blockedId = :userId")
    List<Long> findBlockerIds(@Param("userId") Long userId);

    /**
     * Tập id hợp nhất 2 chiều: (userId chặn ai) ∪ (ai chặn userId).
     * Đây là set "vô hình lẫn nhau" mà các service khác tiêu thụ.
     */
    @Query("""
            select case when b.blockerId = :userId then b.blockedId else b.blockerId end
            from UserBlock b
            where b.blockerId = :userId or b.blockedId = :userId
            """)
    List<Long> findAllRelatedBlockedIds(@Param("userId") Long userId);

    /** Có quan hệ chặn theo bất kỳ chiều nào giữa a và b hay không. */
    @Query("""
            select case when count(b) > 0 then true else false end
            from UserBlock b
            where (b.blockerId = :a and b.blockedId = :b)
               or (b.blockerId = :b and b.blockedId = :a)
            """)
    boolean existsBetween(@Param("a") Long a, @Param("b") Long b);
}
