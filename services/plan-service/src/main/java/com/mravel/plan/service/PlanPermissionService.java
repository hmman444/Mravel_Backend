package com.mravel.plan.service;

import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanPermissionService {

    private final PlanRepository planRepository;
    private final PlanMemberRepository memberRepository;

    // quyền thao tác board
    public void checkPermission(Long planId, Long userId, PlanRole required) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // owner toàn quyền
        if (plan.getAuthor() != null && userId.equals(plan.getAuthor().getId()))
            return;

        // member
        PlanMember member = memberRepository.findByPlanIdAndUserId(planId, userId)
                .orElseThrow(() -> new RuntimeException("You are not invited to this plan."));

        // yêu cầu edit nhưng chỉ là viewer
        if (required == PlanRole.EDITOR && member.getRole() == PlanRole.VIEWER)
            throw new RuntimeException("You don't have permission to edit this plan.");
    }

    /**
     * quyền xem board hoặc feed (VIEW)
     * dựa theo visibility + membership
     * 
     * @param isFriend — được truyền từ user-service (true nếu 2 user là bạn bè)
     */
    public boolean canView(Long planId, Long userId, boolean isFriend) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // owner toàn quyền
        if (plan.getAuthor() != null && userId != null && userId.equals(plan.getAuthor().getId()))
            return true;

        // member
        if (userId != null && memberRepository.findByPlanIdAndUserId(planId, userId).isPresent())
            return true;

        if (plan.getVisibility() == Visibility.PUBLIC)
            return true;

        if (plan.getVisibility() == Visibility.FRIENDS && isFriend)
            return true;

        return false;
    }

    // đảm bảo có owner trong plan
    public void ensureOwner(Long planId, Long userId) {
        boolean exists = memberRepository.existsByPlanIdAndUserIdAndRole(planId, userId, PlanRole.OWNER);
        if (!exists) {
            Plan plan = planRepository.findById(planId)
                    .orElseThrow(() -> new RuntimeException("Plan not found"));
            PlanMember owner = PlanMember.builder()
                    .plan(plan)
                    .userId(userId)
                    .role(PlanRole.OWNER)
                    .build();
            memberRepository.save(owner);
        }
    }
}
