package com.mravel.user.repository;

import com.mravel.user.model.UserProfile;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByEmail(String email);

    Optional<UserProfile> findByEmail(String email);

    @Query("""
                select u from UserProfile u
                where
                    (:q is null or :q = ''
                     or lower(u.fullname) like lower(concat('%', :q, '%'))
                     or lower(u.username) like lower(concat('%', :q, '%'))
                    )
                order by
                    case when lower(u.username) = lower(:q) then 0 else 1 end,
                    case when lower(u.username) like lower(concat(:q, '%')) then 0 else 1 end,
                    u.updatedAt desc nulls last,
                    u.createdAt desc nulls last
            """)
    List<UserProfile> searchUsers(String q, Pageable pageable);
}
