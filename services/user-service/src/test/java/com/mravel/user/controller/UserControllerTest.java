package com.mravel.user.controller;

import com.mravel.user.client.PlanProfileClient;
import com.mravel.user.model.RelationshipType;
import com.mravel.user.model.UserProfile;
import com.mravel.user.repository.UserRepository;
import com.mravel.user.service.AuthTokenClient;
import com.mravel.user.service.FriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired MockMvc mvc;
    @MockBean UserRepository userRepository;
    @MockBean AuthTokenClient authTokenClient;
    @MockBean FriendService friendService;
    @MockBean PlanProfileClient planProfileClient;

    private static final String AUTH = "Bearer test-token";
    private static final Map<String, Object> VALID_AUTH = Map.of("valid", true, "id", 1);

    private UserProfile profile;

    @BeforeEach
    void setUp() {
        profile = UserProfile.builder()
                .id(1L).email("a@x.com").fullname("Test User").build();
        when(authTokenClient.validateToken(anyString())).thenReturn(VALID_AUTH);
    }

    // ── GET /api/users/by-email ───────────────────────────────────────────────
    // UserController trả raw DTO (không bọc ApiResponse) — check $.email chứ không $.data.email

    @Test
    void getUserByEmail_found_returns200() throws Exception {
        when(userRepository.findByEmail("a@x.com")).thenReturn(Optional.of(profile));

        mvc.perform(get("/api/users/by-email").param("email", "a@x.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("a@x.com"))
                .andExpect(jsonPath("$.fullname").value("Test User"));
    }

    @Test
    void getUserByEmail_notFound_returns500() throws Exception {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        mvc.perform(get("/api/users/by-email").param("email", "ghost@x.com"))
                .andExpect(status().is5xxServerError());
    }

    // ── GET /api/users/{id} ───────────────────────────────────────────────────

    @Test
    void getUserById_found_returns200() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(profile));

        mvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    // ── POST /api/users/batch-mini ────────────────────────────────────────────

    @Test
    void batchMini_withIds_returns200() throws Exception {
        when(userRepository.findAllById(anyList())).thenReturn(List.of(profile));

        mvc.perform(post("/api/users/batch-mini")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1,2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void batchMini_emptyList_returnsEmpty() throws Exception {
        mvc.perform(post("/api/users/batch-mini")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    // ── PUT /api/users/by-email ───────────────────────────────────────────────

    @Test
    void updateUserByEmail_success_returns200() throws Exception {
        when(userRepository.findByEmail("a@x.com")).thenReturn(Optional.of(profile));
        when(userRepository.save(any())).thenReturn(profile);

        mvc.perform(put("/api/users/by-email")
                        .param("email", "a@x.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullname\":\"New Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("a@x.com"));
    }

    // ── PUT /api/users/me ─────────────────────────────────────────────────────

    @Test
    void updateMe_validToken_returns200() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(userRepository.save(any())).thenReturn(profile);

        mvc.perform(put("/api/users/me")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullname\":\"Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("a@x.com"));
    }

    // ── PUT /api/users/me/locale ──────────────────────────────────────────────

    @Test
    void updateMyLocale_success_returns200() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(userRepository.save(any())).thenReturn(profile);

        mvc.perform(put("/api/users/me/locale")
                        .header("Authorization", AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"locale\":\"en\"}"))
                .andExpect(status().isOk());
    }

    // ── GET /api/users/{id}/profile-page ─────────────────────────────────────

    @Test
    void getProfilePage_anonymous_returns200() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(friendService.getRelationship(isNull(), eq(1L))).thenReturn(RelationshipType.NONE);
        when(friendService.getFriendIds(1L)).thenReturn(List.of());
        // planProfileClient default mock returns null → caught in try/catch → plansPreview=[]

        mvc.perform(get("/api/users/1/profile-page"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.relationship.type").value("NONE"));
    }

    @Test
    void getProfilePage_withToken_returns200() throws Exception {
        when(userRepository.findById(2L)).thenReturn(Optional.of(
                UserProfile.builder().id(2L).email("b@x.com").fullname("User B").build()));
        when(friendService.getRelationship(eq(1L), eq(2L))).thenReturn(RelationshipType.FRIEND);
        when(friendService.getFriendIds(2L)).thenReturn(List.of());
        when(friendService.countMutualFriends(1L, 2L)).thenReturn(3);

        mvc.perform(get("/api/users/2/profile-page")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.relationship.type").value("FRIEND"))
                .andExpect(jsonPath("$.relationship.friend").value(true));
    }

    // ── GET /api/users/search ─────────────────────────────────────────────────

    @Test
    void searchUsers_success_returns200() throws Exception {
        when(userRepository.searchUsers(eq("van"), any())).thenReturn(List.of(profile));

        mvc.perform(get("/api/users/search").param("q", "van").param("limit", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].email").value("a@x.com"));
    }
}
