package com.mravel.user.repository;

import com.mravel.user.model.Friendship;
import com.mravel.user.model.FriendshipStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Optional<Friendship> findByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    @Query("""
            select f from Friendship f
            where (f.user1Id = :userId or f.user2Id = :userId)
              and f.status = :status
            """)
    List<Friendship> findByUserAndStatus(@Param("userId") Long userId,
            @Param("status") FriendshipStatus status);

    @Query("""
            select case
                when (f.user1Id = :a and f.user2Id = :b)
                  or (f.user1Id = :b and f.user2Id = :a)
                then f
                else null
            end
            from Friendship f
            where (f.user1Id = :a and f.user2Id = :b)
               or (f.user1Id = :b and f.user2Id = :a)
            """)
    Optional<Friendship> findBetween(@Param("a") Long a, @Param("b") Long b);

    @Query("""
            select
                case when :userId = f.user1Id then f.user2Id else f.user1Id end
            from Friendship f
            where (f.user1Id = :userId or f.user2Id = :userId)
              and f.status = 'ACCEPTED'
            """)
    List<Long> findFriendIdsOf(@Param("userId") Long userId);
}
