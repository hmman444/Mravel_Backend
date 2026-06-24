package com.mravel.auth.service;

import com.mravel.auth.dto.AdminUserRow;
import com.mravel.auth.dto.AdminUserStats;
import com.mravel.auth.model.AccountStatus;
import com.mravel.auth.model.Role;
import com.mravel.auth.model.User;
import com.mravel.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminUserServiceTest {

    @Mock UserRepository repo;
    @InjectMocks AdminUserService service;

    // ── list() ────────────────────────────────────────────────────────────────

    @Test
    void list_mapsUsersToAdminUserRow() {
        User u = new User();
        u.setId(5L); u.setEmail("user@mravel.com"); u.setFullname("Nguyen Van A");
        u.setRole(Role.USER); u.setEnabled(true); u.setStatus(AccountStatus.ACTIVE);

        when(repo.adminSearch(anyList(), anyString(), any(PageRequest.class))).thenReturn(List.of(u));

        List<AdminUserRow> result = service.list(List.of(Role.USER), "", 0, 20);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(5L);
        assertThat(result.get(0).getEmail()).isEqualTo("user@mravel.com");
        assertThat(result.get(0).getRole()).isEqualTo("USER");
        assertThat(result.get(0).isEnabled()).isTrue();
        assertThat(result.get(0).getStatus()).isEqualTo(AccountStatus.ACTIVE);
    }

    @Test
    void list_sizeClamped_maxFifty() {
        when(repo.adminSearch(anyList(), anyString(), any(PageRequest.class))).thenReturn(List.of());

        service.list(List.of(Role.USER), "", 0, 200);

        verify(repo).adminSearch(anyList(), anyString(), eq(PageRequest.of(0, 50)));
    }

    @Test
    void list_sizeClamped_minOne() {
        when(repo.adminSearch(anyList(), anyString(), any(PageRequest.class))).thenReturn(List.of());

        service.list(List.of(Role.USER), "", 0, 0);

        verify(repo).adminSearch(anyList(), anyString(), eq(PageRequest.of(0, 1)));
    }

    // ── stats() ───────────────────────────────────────────────────────────────

    @Test
    void stats_aggregatesCountsCorrectly() {
        when(repo.count()).thenReturn(120L);
        when(repo.countByRole(Role.PARTNER)).thenReturn(15L);
        when(repo.countByRole(Role.ADMIN)).thenReturn(1L);
        when(repo.countByRole(Role.USER)).thenReturn(104L);

        AdminUserStats stats = service.stats();

        assertThat(stats.totalUsers()).isEqualTo(120L);
        assertThat(stats.totalPartners()).isEqualTo(15L);
        assertThat(stats.totalRegularUsers()).isEqualTo(104L);
        assertThat(stats.totalAdmins()).isEqualTo(1L);
    }

    // ── lock() ────────────────────────────────────────────────────────────────

    @Test
    void lock_setsStatusToLocked() {
        User u = new User();
        u.setId(5L); u.setRole(Role.USER); u.setStatus(AccountStatus.ACTIVE);

        when(repo.findById(5L)).thenReturn(Optional.of(u));

        service.lock(5L);

        assertThat(u.getStatus()).isEqualTo(AccountStatus.LOCKED);
        verify(repo).save(u);
    }

    @Test
    void lock_userNotFound_throwsRuntimeException() {
        when(repo.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.lock(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

    @Test
    void lock_targetIsAdmin_throwsRuntimeException() {
        User admin = new User();
        admin.setId(1L); admin.setRole(Role.ADMIN);

        when(repo.findById(1L)).thenReturn(Optional.of(admin));

        assertThatThrownBy(() -> service.lock(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Không được khóa ADMIN");

        verify(repo, never()).save(any());
    }

    // ── unlock() ──────────────────────────────────────────────────────────────

    @Test
    void unlock_setsStatusToActive() {
        User u = new User();
        u.setId(5L); u.setRole(Role.USER); u.setStatus(AccountStatus.LOCKED);

        when(repo.findById(5L)).thenReturn(Optional.of(u));

        service.unlock(5L);

        assertThat(u.getStatus()).isEqualTo(AccountStatus.ACTIVE);
        verify(repo).save(u);
    }

    @Test
    void unlock_userNotFound_throwsRuntimeException() {
        when(repo.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.unlock(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }
}
