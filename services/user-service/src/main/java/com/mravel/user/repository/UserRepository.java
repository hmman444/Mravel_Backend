package com.mravel.user.repository;

import com.mravel.user.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByEmail(String email);
}
