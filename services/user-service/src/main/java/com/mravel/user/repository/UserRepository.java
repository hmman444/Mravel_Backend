package com.mravel.user.repository;

import com.mravel.user.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByEmail(String email);
    Optional<UserProfile> findByEmail(String email);
}
