package com.mravel.auth.repository;

import com.mravel.auth.model.Role;
import com.mravel.auth.model.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
                select u from User u
                where u.role in :roles
                  and (:q is null or :q = ''
                       or lower(u.email) like lower(concat('%', :q, '%'))
                       or lower(u.fullname) like lower(concat('%', :q, '%')))
                order by u.id desc
            """)
    List<User> adminSearch(@Param("roles") List<Role> roles,
            @Param("q") String q,
            Pageable pageable);
}
