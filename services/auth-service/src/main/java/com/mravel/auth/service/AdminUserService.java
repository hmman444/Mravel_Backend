package com.mravel.auth.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mravel.auth.dto.AdminUserRow;
import com.mravel.auth.model.AccountStatus;
import com.mravel.auth.model.Role;
import com.mravel.auth.model.User;
import com.mravel.auth.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository repo;

    public List<AdminUserRow> list(List<Role> roles, String q, int page, int size) {
        size = Math.max(1, Math.min(size, 50));
        page = Math.max(0, page);
        var users = repo.adminSearch(roles, q, PageRequest.of(page, size));
        return users.stream().map(u -> AdminUserRow.builder()
                .id(u.getId())
                .email(u.getEmail())
                .fullname(u.getFullname())
                .role(u.getRole().name())
                .enabled(u.isEnabled())
                .status(u.getStatus())
                .build()).toList();
    }

    @Transactional
    public void lock(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (u.getRole() == Role.ADMIN)
            throw new RuntimeException("Không được khóa ADMIN");
        u.setStatus(AccountStatus.LOCKED);
        repo.save(u);
    }

    @Transactional
    public void unlock(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        u.setStatus(AccountStatus.ACTIVE);
        repo.save(u);
    }
}
