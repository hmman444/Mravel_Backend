package com.mravel.user.controller;

import com.mravel.common.request.UpdateUserProfileRequest;
import com.mravel.common.response.UserProfileResponse;
import com.mravel.user.client.PlanProfileClient;
import com.mravel.user.dto.PlanFeedItem;
import com.mravel.user.dto.UserMiniResponse;
import com.mravel.user.dto.UserProfilePageResponse;
import com.mravel.user.model.Gender;
import com.mravel.user.model.RelationshipType;
import com.mravel.user.model.UserProfile;
import com.mravel.user.repository.UserRepository;
import com.mravel.user.service.AuthTokenClient;
import com.mravel.user.service.FriendService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final AuthTokenClient authTokenClient;
    private final FriendService friendService;
    private final PlanProfileClient planProfileClient;

    // ---------------------------------- basic query
    // ----------------------------------

    @GetMapping("/by-email")
    public UserProfileResponse getUserByEmail(@RequestParam("email") String email) {
        UserProfile user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        return toResponse(user);
    }

    @GetMapping("/{id}")
    public UserProfileResponse getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @PostMapping("/batch-mini")
    public List<UserMiniResponse> batchMini(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty())
            return List.of();

        // unique + limit để tránh abuse
        List<Long> uniq = ids.stream().distinct().limit(200).toList();
        List<UserProfile> users = userRepository.findAllById(uniq);

        return users.stream()
                .map(u -> UserMiniResponse.builder()
                        .id(u.getId())
                        .fullname(u.getFullname())
                        .avatar(u.getAvatar())
                        .build())
                .toList();
    }

    // dùng cho hệ thống nội bộ, ví dụ admin / service khác
    @PutMapping("/by-email")
    public UserProfileResponse updateUserByEmail(@RequestParam("email") String email,
            @RequestBody UpdateUserProfileRequest request) {
        UserProfile user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        applyFullProfileUpdates(user, request);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return toResponse(user);
    }

    @PutMapping("/me")
    public UserProfileResponse updateCurrentUser(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdateUserProfileRequest request) {

        Long currentUserId = getCurrentUserId(authorizationHeader);

        UserProfile user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found: " + currentUserId));

        applyFullProfileUpdates(user, request);

        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return toResponse(user);
    }

    // ------------------------------ profile-page cho FE
    // ------------------------------

    @GetMapping("/{id}/profile-page")
    public UserProfilePageResponse getUserProfilePage(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        // 1. viewer (nếu có)
        Long tmpViewerId = null;
        if (authorizationHeader != null) {
            try {
                tmpViewerId = getCurrentUserId(authorizationHeader);
            } catch (Exception ignored) {
            }
        }

        final Long viewerId = tmpViewerId;

        // 2. user profile
        UserProfile user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        // 3. relationship
        RelationshipType relationship = friendService.getRelationship(viewerId, id);
        boolean isFriend = relationship == RelationshipType.FRIEND;

        // 4. friends + mutual
        List<Long> friendIds = friendService.getFriendIds(id)
                .stream()
                .filter(fid -> !Objects.equals(fid, viewerId))
                .toList();
        int totalFriends = friendIds.size();
        int mutualFriends = (viewerId != null && !viewerId.equals(id))
                ? friendService.countMutualFriends(viewerId, id)
                : 0;

        // 4b. friends preview
        List<UserProfilePageResponse.FriendPreview> friendsPreview = List.of();
        if (!friendIds.isEmpty()) {
            List<UserProfile> friendProfiles = userRepository.findAllById(friendIds);

            friendsPreview = friendProfiles.stream()
                    .map(f -> {
                        Integer mutualForFriend = null;

                        // Nếu có viewer đăng nhập -> luôn tính mutual giữa viewer và từng friend
                        if (viewerId != null) {
                            mutualForFriend = friendService.countMutualFriends(viewerId, f.getId());
                        }

                        return UserProfilePageResponse.FriendPreview.builder()
                                .id(f.getId())
                                .fullname(f.getFullname())
                                .avatar(f.getAvatar())
                                .mutualFriends(mutualForFriend)
                                .build();
                    })
                    .limit(12) // preview tối đa 12 người
                    .collect(Collectors.toList());
        }

        // 5. plans preview (ảnh lấy từ images trong PlanFeedItem, FE đã xử lý)
        List<PlanFeedItem> plansPreview = List.of();
        try {
            var apiResp = planProfileClient.getPlansOfUser(
                    id,
                    1, // page 1
                    5, // 5 plan gần nhất
                    isFriend,
                    authorizationHeader != null ? authorizationHeader : "");
            if (apiResp != null && apiResp.getData() != null) {
                plansPreview = apiResp.getData().getItems();
            }
        } catch (Exception ex) {
        }

        // 6. map BasicInfo + RelationshipInfo
        UserProfilePageResponse.BasicInfo basicInfo = UserProfilePageResponse.BasicInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .avatar(user.getAvatar())
                .coverImage(user.getCoverImage())
                .bio(user.getBio())
                .city(user.getCity())
                .country(user.getCountry())
                .hometown(user.getHometown())
                .occupation(user.getOccupation())
                .gender(user.getGender() != null ? user.getGender().name() : null)
                .dateOfBirth(user.getDateOfBirth())
                .addressLine(user.getAddressLine())
                .secondaryEmail(user.getSecondaryEmail())
                .tertiaryEmail(user.getTertiaryEmail())
                .phone1((viewerId != null && (viewerId.equals(id) || isFriend)) ? user.getPhone1() : null)
                .phone2((viewerId != null && (viewerId.equals(id) || isFriend)) ? user.getPhone2() : null)
                .phone3((viewerId != null && (viewerId.equals(id) || isFriend)) ? user.getPhone3() : null)
                .membershipTier(user.getMembershipTier() != null ? user.getMembershipTier().name() : null)
                .joinedDate(user.getCreatedAt() != null ? user.getCreatedAt().toLocalDate() : null)
                .totalFriends(totalFriends)
                .mutualFriends(mutualFriends)
                .totalTrips(plansPreview != null ? plansPreview.size() : 0) // hoặc dùng totalItems từ API
                .build();

        UserProfilePageResponse.RelationshipInfo relInfo = UserProfilePageResponse.RelationshipInfo.builder()
                .type(relationship)
                .friend(isFriend)
                .build();

        return UserProfilePageResponse.builder()
                .user(basicInfo)
                .relationship(relInfo)
                .plansPreview(plansPreview)
                .friendsPreview(friendsPreview)
                .build();
    }

    // ------------------------------ helpers ------------------------------

    private UserProfileResponse toResponse(UserProfile user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .avatar(user.getAvatar())
                .provider(user.getProvider())
                .role(user.getRole())
                .gender(user.getGender() != null ? user.getGender().name() : null)
                .dateOfBirth(user.getDateOfBirth())
                .city(user.getCity())
                .country(user.getCountry())
                .addressLine(user.getAddressLine())
                .secondaryEmail(user.getSecondaryEmail())
                .tertiaryEmail(user.getTertiaryEmail())
                .phone1(user.getPhone1())
                .phone2(user.getPhone2())
                .phone3(user.getPhone3())
                .membershipTier(user.getMembershipTier() != null ? user.getMembershipTier().name() : null)
                .locale(user.getLocale())
                .timeZone(user.getTimeZone())
                .build();
    }

    private Long getCurrentUserId(String authorizationHeader) {
        Map<String, Object> result = authTokenClient.validateToken(authorizationHeader);
        Boolean valid = (Boolean) result.get("valid");
        if (valid == null || !valid) {
            throw new UnauthorizedException("JWT expired");
        }

        Integer idInt = (Integer) result.get("id");
        if (idInt == null) {
            throw new RuntimeException("Không lấy được userId từ token");
        }
        return idInt.longValue();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    private void applyFullProfileUpdates(UserProfile user, UpdateUserProfileRequest request) {

        // PUBLIC FIELDS
        if (request.getFullname() != null)
            user.setFullname(request.getFullname());

        if (request.getUsername() != null)
            user.setUsername(request.getUsername());

        if (request.getAvatar() != null)
            user.setAvatar(request.getAvatar());

        if (request.getCoverImage() != null)
            user.setCoverImage(request.getCoverImage());

        if (request.getBio() != null)
            user.setBio(request.getBio());

        if (request.getCity() != null)
            user.setCity(request.getCity());

        if (request.getCountry() != null)
            user.setCountry(request.getCountry());

        if (request.getHometown() != null)
            user.setHometown(request.getHometown());

        if (request.getOccupation() != null)
            user.setOccupation(request.getOccupation());

        // SENSITIVE FIELDS
        String genderStr = request.getGender();
        if (genderStr != null) {
            String g = genderStr.trim();

            if (g.isEmpty()) {
                // FE gửi chuỗi rỗng -> xóa giới tính
                user.setGender(null);
            } else {
                try {
                    String normalized = g.toUpperCase();
                    // hỗ trợ vài case tiếng Việt
                    if (normalized.equals("NAM"))
                        normalized = "MALE";
                    if (normalized.equals("NU") || normalized.equals("NỮ"))
                        normalized = "FEMALE";

                    user.setGender(Gender.valueOf(normalized));
                } catch (IllegalArgumentException ex) {
                    // không hợp lệ thì bỏ qua, KHÔNG throw nữa
                }
            }
        }

        if (request.getDateOfBirth() != null)
            user.setDateOfBirth(request.getDateOfBirth());

        if (request.getAddressLine() != null)
            user.setAddressLine(request.getAddressLine());

        if (request.getSecondaryEmail() != null)
            user.setSecondaryEmail(request.getSecondaryEmail());

        if (request.getTertiaryEmail() != null)
            user.setTertiaryEmail(request.getTertiaryEmail());

        if (request.getPhone1() != null)
            user.setPhone1(request.getPhone1());
        if (request.getPhone2() != null)
            user.setPhone2(request.getPhone2());
        if (request.getPhone3() != null)
            user.setPhone3(request.getPhone3());
    }

    @GetMapping("/search")
    public List<UserProfileResponse> searchUsers(
            @RequestParam(name = "q", required = false, defaultValue = "") String q,
            @RequestParam(defaultValue = "10") int limit) {
        String keyword = normalize(q);
        limit = Math.max(1, Math.min(limit, 30));

        List<UserProfile> users = userRepository.searchUsers(keyword, PageRequest.of(0, limit));

        return users.stream().map(this::toMini).toList();
    }

    private String normalize(String q) {
        if (q == null)
            return "";
        String s = q.trim();
        if (s.startsWith("@"))
            s = s.substring(1).trim();
        return s;
    }

    // trả minimal để list search (nhẹ payload)
    private UserProfileResponse toMini(UserProfile u) {
        return UserProfileResponse.builder()
                .id(u.getId())
                .fullname(u.getFullname())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .provider(u.getProvider())
                .gender(u.getGender() != null ? u.getGender().name() : null)
                .dateOfBirth(u.getDateOfBirth())
                .city(u.getCity())
                .country(u.getCountry())
                .addressLine(u.getAddressLine())
                .secondaryEmail(u.getSecondaryEmail())
                .tertiaryEmail(u.getTertiaryEmail())
                .phone1(u.getPhone1())
                .phone2(u.getPhone2())
                .phone3(u.getPhone3())
                .membershipTier(u.getMembershipTier() != null ? u.getMembershipTier().name() : null)
                .locale(u.getLocale())
                .timeZone(u.getTimeZone())
                .build();
    }
}
