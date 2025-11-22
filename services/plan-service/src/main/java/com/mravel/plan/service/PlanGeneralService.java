package com.mravel.plan.service;

import com.mravel.plan.model.*;
import com.mravel.plan.repository.PlanListRepository;
import com.mravel.plan.repository.PlanRepository;
import com.mravel.plan.repository.PlanCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanGeneralService {

    private final PlanRepository planRepo;
    private final PlanPermissionService permission;
    private final PlanListRepository listRepo;
    private final PlanCardRepository cardRepo;
    private final PlanBoardService planBoardService;

    // helper
    private Plan loadPlan(Long planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    private PlanList loadTrash(Long planId) {
        return listRepo.findByPlanIdOrderByPositionAsc(planId)
                .stream().filter(l -> l.getType() == PlanListType.TRASH)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Trash list missing"));
    }

    private List<PlanList> loadDayLists(Long planId) {
        return listRepo.findByPlanIdOrderByPositionAsc(planId)
                .stream().filter(l -> l.getType() == PlanListType.DAY)
                .toList();
    }

    // general info

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
    public void updateStatus(Long planId, Long userId, PlanStatus status) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setStatus(status);
    }

    @Transactional
    public void updateThumbnail(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setThumbnail(url);
    }

    @Transactional
    public void addImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).getImages().add(url);
    }

    @Transactional
    public void removeImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).getImages().remove(url);
    }

    // logic update date
    @Transactional
    public void updateDates(Long planId, Long userId, LocalDate startNew, LocalDate endNew) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);

        if (endNew.isBefore(startNew)) {
            throw new RuntimeException("endDate must be >= startDate");
        }

        Plan plan = loadPlan(planId);
        LocalDate startOld = plan.getStartDate();
        LocalDate endOld = plan.getEndDate();

        List<PlanList> dayLists = loadDayLists(planId);
        PlanList trash = loadTrash(planId);

        int oldCount = dayLists.size();
        int newCount = (int) (endNew.toEpochDay() - startNew.toEpochDay()) + 1;

        // ép về 1 ngày
        if (newCount == 1 && oldCount > 1) {

            // giữ lại day 1
            PlanList keep = dayLists.get(0);

            // move cards from other days → trash
            for (int i = 1; i < dayLists.size(); i++) {
                PlanList rm = dayLists.get(i);

                cardRepo.findByListIdOrderByPositionAsc(rm.getId())
                        .forEach(c -> {
                            c.setList(trash);
                            c.setPosition(trash.getCards().size());
                        });

                listRepo.delete(rm);
            }

            // update keep
            keep.setPosition(0);
            keep.setDayDate(startNew);

            // update plan
            plan.setStartDate(startNew);
            plan.setEndDate(startNew);

            // set trash at last pos
            trash.setPosition(1);

            return;
        }

        // đổi ngày nhưng giữ daycount
        if (newCount == oldCount) {
            int shift = (int) (startNew.toEpochDay() - startOld.toEpochDay());

            for (int i = 0; i < dayLists.size(); i++) {
                dayLists.get(i).setPosition(i);
                dayLists.get(i).setDayDate(startOld.plusDays(i + shift));
            }

            plan.setStartDate(startNew);
            plan.setEndDate(endNew);
            trash.setPosition(newCount);

            return;
        }

        // kéo dài daycount
        if (newCount > oldCount) {
            int delta = newCount - oldCount;

            // ngày cuối cũ
            LocalDate lastDateOld = dayLists.get(oldCount - 1).getDayDate();

            for (int i = 0; i < delta; i++) {

                // tên mặc định mới
                String defaultTitle = "Danh sách hoạt động";

                listRepo.save(
                        PlanList.builder()
                                .plan(plan)
                                .type(PlanListType.DAY)
                                .title(defaultTitle)
                                .position(oldCount + i)
                                .dayDate(lastDateOld.plusDays(i + 1))
                                .build());
            }

            // update plan
            plan.setStartDate(startNew);
            plan.setEndDate(endNew);

            // trash always last
            trash.setPosition(newCount);

            // nếu dời startDate → sync lại vị trí + dayDate
            if (!startNew.equals(startOld)) {
                planBoardService.syncDayLists(plan);
            }

            return;
        }

        // rút ngắn datecount
        if (newCount < oldCount) {

            // range to remove: [newCount .. oldCount-1]
            for (int i = newCount; i < oldCount; i++) {
                PlanList rm = dayLists.get(i);

                // move cards → trash
                cardRepo.findByListIdOrderByPositionAsc(rm.getId())
                        .forEach(c -> {
                            c.setList(trash);
                            c.setPosition(trash.getCards().size());
                        });

                listRepo.delete(rm);
            }

            // update plan
            plan.setStartDate(startNew);
            plan.setEndDate(endNew);

            // sync remaining dayLists (position + date)

            planBoardService.syncDayLists(plan);

            trash.setPosition(newCount);

            return;
        }
    }

}
