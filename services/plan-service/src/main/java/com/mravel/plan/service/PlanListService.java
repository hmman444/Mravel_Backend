package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.board.*;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlanListService {

    private final PlanRepository planRepository;
    private final PlanMemberRepository memberRepository;
    private final UserProfileClient userClient;

    public List<MyPlanDto> getMyPlans(Long userId) {

        List<Plan> owned = planRepository.findByAuthor_Id(userId);
        List<PlanMember> joined = memberRepository.findByUserId(userId);

        Map<Long, UserProfileResponse> userCache = new HashMap<>();

        List<MyPlanDto> result = new ArrayList<>();

        // owner plans
        for (Plan p : owned) {
            result.add(toDto(p, PlanRole.OWNER, userCache));
        }

        // plans user joined
        for (PlanMember m : joined) {
            Plan p = m.getPlan();
            if (!Objects.equals(p.getAuthor().getId(), userId)) {
                result.add(toDto(p, m.getRole(), userCache));
            }
        }

        return result;
    }

    private MyPlanDto toDto(Plan p, PlanRole myRole, Map<Long, UserProfileResponse> cache) {

        Long ownerId = p.getAuthor().getId();

        UserProfileResponse ownerProfile = cache.computeIfAbsent(
                ownerId, id -> userClient.getUserById(id));

        int memberCount = p.getMembers() != null ? p.getMembers().size() : 1;

        return MyPlanDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .owner(ownerProfile.getFullname())
                .members(memberCount)

                .totalEstimatedCost(
                        p.getTotalEstimatedCost() != null ? p.getTotalEstimatedCost() : 0L)
                .totalActualCost(
                        p.getTotalActualCost() != null ? p.getTotalActualCost() : 0L)
                .budgetCurrency(p.getBudgetCurrency())
                .budgetTotal(p.getBudgetTotal())

                .startDate(p.getStartDate())
                .endDate(p.getEndDate())

                .thumbnail(p.getThumbnail())

                .status(p.getStatus())
                .myRole(myRole)
                .build();
    }
}
