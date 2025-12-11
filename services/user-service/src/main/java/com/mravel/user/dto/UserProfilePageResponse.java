package com.mravel.user.dto;

import lombok.Builder;
import lombok.Data;
import com.mravel.user.model.RelationshipType;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserProfilePageResponse {

    @Data
    @Builder
    public static class BasicInfo {
        private Long id;
        private String username;
        private String fullname;
        private String avatar;
        private String coverImage;
        private String bio;
        private String city;
        private String country;
        private String membershipTier;
        private LocalDate joinedDate;
        private Integer totalFriends;
        private Integer mutualFriends;
    }

    @Data
    @Builder
    public static class RelationshipInfo {
        private RelationshipType type;
        private boolean friend;
    }

    @Data
    @Builder
    public static class FriendPreview {
        private Long id;
        private String fullname;
        private String avatar;
        private Integer mutualFriends;
    }

    private BasicInfo user;
    private RelationshipInfo relationship;

    // preview từ plan-service (có thể page riêng)
    private List<PlanFeedItem> plansPreview;
    private List<FriendPreview> friendsPreview;

}
