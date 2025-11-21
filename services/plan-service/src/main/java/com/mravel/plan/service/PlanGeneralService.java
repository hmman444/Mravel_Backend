package com.mravel.plan.service;

import com.mravel.plan.model.*;
import com.mravel.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PlanGeneralService {

    private final PlanRepository planRepo;
    private final PlanPermissionService permission;

    private Plan loadPlan(Long planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    @Transactional
    public void updateTitle(Long planId, Long userId, String title) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setTitle(title);
    }

    @Transactional
    public void updateDescription(Long planId, Long userId, String desc) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setDescription(desc);
    }

    @Transactional
    public void updateDates(Long planId, Long userId, LocalDate start, LocalDate end) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan p = loadPlan(planId);
        p.setStartDate(start);
        p.setEndDate(end);

        if (start != null && end != null && !end.isBefore(start)) {
            p.setDays((int) (end.toEpochDay() - start.toEpochDay() + 1));
        }
    }

    @Transactional
    public void updateStatus(Long planId, Long userId, PlanStatus status) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setStatus(status);
    }

    @Transactional
    public void updateThumbnail(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        plan.setThumbnail(url);
        planRepo.save(plan);
    }

    @Transactional
    public void addImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).getImages().add(url);
    }

    public void removeImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        plan.getImages().remove(url);

        planRepo.save(plan);
    }

}
