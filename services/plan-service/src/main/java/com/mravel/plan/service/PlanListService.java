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

        List<Plan> owned = planRepository.findByAuthorId(userId);
        List<PlanMember> joined = memberRepository.findByUserId(userId);

        List<MyPlanDto> result = new ArrayList<>();

        for (Plan p : owned) {
            result.add(toDto(p, PlanRole.OWNER));
        }

        for (PlanMember m : joined) {
            Plan p = m.getPlan();
            if (!Objects.equals(p.getAuthorId(), userId)) {
                result.add(toDto(p, m.getRole()));
            }
        }

        return result;
    }

    private MyPlanDto toDto(Plan p, PlanRole myRole) {

        Long ownerId = p.getAuthorId();
        UserProfileResponse ownerProfile = userClient.getUserById(ownerId); // cache

        return MyPlanDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .owner(ownerProfile.getFullname())
                .members(p.getMembers() != null ? p.getMembers().size() : 1)
                .totalEstimatedCost(Optional.ofNullable(p.getTotalEstimatedCost()).orElse(0L))
                .totalActualCost(Optional.ofNullable(p.getTotalActualCost()).orElse(0L))
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
