package com.mravel.plan.service;

import com.mravel.plan.dto.board.UpdateBudgetRequest;
import com.mravel.plan.exception.BadRequestException;
import com.mravel.plan.exception.NotFoundException;
import com.mravel.plan.kafka.PlanIndexPublisher;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.PlanListRepository;
import com.mravel.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlanGeneralService {

    private final PlanRepository planRepo;
    private final PlanPermissionService permission;
    private final PlanListRepository listRepo;
    private final PlanBoardService planBoardService;
    private final PlanIndexPublisher planIndexPublisher;

    // helper
    private Plan loadPlan(Long planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new NotFoundException("Plan not found"));
    }

    private PlanList loadTrash(Long planId) {
        return listRepo.findByPlanIdOrderByPositionAsc(planId)
                .stream().filter(l -> l.getType() == PlanListType.TRASH)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Trash list missing"));
    }

    private List<PlanList> loadDayLists(Long planId) {
        return listRepo.findByPlanIdOrderByPositionAsc(planId)
                .stream().filter(l -> l.getType() == PlanListType.DAY)
                .toList();
    }

    // general info

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateTitle(Long planId, Long userId, String title) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setTitle(title);
        long revision = planBoardService.incrementBoardRevision(planId);
        planBoardService.publishBoard(planId, userId, "PLAN_TITLE_UPDATED");
        planBoardService.emitV2Patch(planId, userId, "PLAN", planId, "UPDATE", Map.of("planTitle", title), revision);
        planIndexPublisher.publishUpsert(planId);
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateDescription(Long planId, Long userId, String desc) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setDescription(desc);

        long revision = planBoardService.incrementBoardRevision(planId);
        planBoardService.publishBoard(planId, userId, "PLAN_DESCRIPTION_UPDATED");
        planBoardService.emitV2Patch(planId, userId, "PLAN", planId, "UPDATE", Map.of("description", desc), revision);
        planIndexPublisher.publishUpsert(planId);
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateStatus(Long planId, Long userId, PlanStatus status) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setStatus(status);

        long revision = planBoardService.incrementBoardRevision(planId);
        planBoardService.publishBoard(planId, userId, "PLAN_STATUS_UPDATED");
        planBoardService.emitV2Patch(planId, userId, "PLAN", planId, "UPDATE", Map.of("status", status.name()),
                revision);

    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateThumbnail(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setThumbnail(url);

        long revision = planBoardService.incrementBoardRevision(planId);
        planBoardService.publishBoard(planId, userId, "PLAN_THUMBNAIL_UPDATED");
        planBoardService.emitV2Patch(planId, userId, "PLAN", planId, "UPDATE", Map.of("thumbnail", url), revision);
        planIndexPublisher.publishUpsert(planId);
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void addImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        Plan plan = loadPlan(planId);
        plan.getImages().add(url);

        long revision = planBoardService.incrementBoardRevision(planId);
        planBoardService.publishBoard(planId, userId, "PLAN_IMAGE_ADDED");
        planBoardService.emitV2Patch(planId, userId, "PLAN", planId, "UPDATE", Map.of("images", plan.getImages()),
                revision);
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void removeImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        Plan plan = loadPlan(planId);
        plan.getImages().remove(url);
        long revision = planBoardService.incrementBoardRevision(planId);
        planBoardService.publishBoard(planId, userId, "PLAN_IMAGE_REMOVED");
        planBoardService.emitV2Patch(planId, userId, "PLAN", planId, "UPDATE", Map.of("images", plan.getImages()),
                revision);
    }

    // logic update date
    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateDates(Long planId, Long userId, LocalDate startNew, LocalDate endNew) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);

        if (endNew.isBefore(startNew)) {
            throw new BadRequestException("endDate must be >= startDate");
        }

        Plan plan = loadPlan(planId);
        LocalDate startOld = plan.getStartDate();

        List<PlanList> dayLists = loadDayLists(planId);
        PlanList trash = loadTrash(planId);

        int oldCount = dayLists.size();
        int newCount = (int) (endNew.toEpochDay() - startNew.toEpochDay()) + 1;

        boolean startChanged = !startNew.equals(startOld);
        boolean durationUnchanged = newCount == oldCount;

        //
        // CASE A — USER CHỈ ĐỔI startDate → GIỮ NGUYÊN duration
        //
        if (startChanged && durationUnchanged) {

            int shift = (int) (startNew.toEpochDay() - startOld.toEpochDay());

            for (int i = 0; i < dayLists.size(); i++) {
                dayLists.get(i).setPosition(i);
                dayLists.get(i).setDayDate(startOld.plusDays(i + shift));
            }

            plan.setStartDate(startNew);
            plan.setEndDate(startNew.plusDays(oldCount - 1));

            trash.setPosition(oldCount);

            long revision = planBoardService.incrementBoardRevision(planId);
            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
            planBoardService.emitV2Sync(planId, userId, revision);
            planIndexPublisher.publishUpsert(planId);
            return;
        }

        //
        // CASE B — LOGIC THAY ĐỔI endDate (tăng/giảm/tối ưu ngày)
        // (Toàn bộ các logic cũ giữ nguyên)
        //

        // CASE 1 — ÉP VỀ 1 NGÀY
        if (newCount == 1 && oldCount > 1) {

            PlanList keep = dayLists.get(0);
            keep.setPosition(0);
            keep.setDayDate(startNew);

            for (int i = 1; i < dayLists.size(); i++) {
                PlanList rm = dayLists.get(i);

                planBoardService.moveCardsToTrash(rm, trash);
                listRepo.delete(rm);
            }

            plan.setStartDate(startNew);
            plan.setEndDate(startNew);
            trash.setPosition(1);

            long revision = planBoardService.incrementBoardRevision(planId);
            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
            planBoardService.emitV2Sync(planId, userId, revision);
            planIndexPublisher.publishUpsert(planId);
            return;
        }

        // CASE 2 — GIỮ NGUYÊN SỐ NGÀY (nhưng start không đổi)
        if (newCount == oldCount) {

            // Trường hợp này start không đổi và chỉ đổi endDate → không cần shift
            for (int i = 0; i < dayLists.size(); i++) {
                dayLists.get(i).setPosition(i);
                dayLists.get(i).setDayDate(startNew.plusDays(i));
            }

            plan.setStartDate(startNew);
            plan.setEndDate(endNew);
            trash.setPosition(newCount);

            long revision = planBoardService.incrementBoardRevision(planId);
            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
            planBoardService.emitV2Sync(planId, userId, revision);
            planIndexPublisher.publishUpsert(planId);
            return;
        }

        // CASE 3 — KÉO DÀI NGÀY
        if (newCount > oldCount) {

            int delta = newCount - oldCount;
            LocalDate lastDateOld = dayLists.get(oldCount - 1).getDayDate();

            for (int i = 0; i < delta; i++) {
                listRepo.save(
                        PlanList.builder()
                                .plan(plan)
                                .type(PlanListType.DAY)
                                .title("Danh sách hoạt động")
                                .position(oldCount + i)
                                .cards(new ArrayList<>())
                                .dayDate(lastDateOld.plusDays(i + 1))
                                .build());
            }

            plan.setStartDate(startNew);
            plan.setEndDate(endNew);
            trash.setPosition(newCount);

            // nếu startDate đổi trong khi kéo dài (hiếm), sync lại
            if (startChanged)
                planBoardService.syncDayLists(plan);

            long revision = planBoardService.incrementBoardRevision(planId);
            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
            planBoardService.emitV2Sync(planId, userId, revision);
            return;
        }

        // CASE 4 — RÚT NGẮN NGÀY
        if (newCount < oldCount) {

            for (int i = newCount; i < oldCount; i++) {
                PlanList rm = dayLists.get(i);

                planBoardService.moveCardsToTrash(rm, trash);
                listRepo.delete(rm);
            }

            for (int i = 0; i < newCount; i++) {
                dayLists.get(i).setPosition(i);
                dayLists.get(i).setDayDate(startNew.plusDays(i));
            }

            plan.setStartDate(startNew);
            plan.setEndDate(endNew);
            trash.setPosition(newCount);

            long revision = planBoardService.incrementBoardRevision(planId);
            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
            planBoardService.emitV2Sync(planId, userId, revision);
            planIndexPublisher.publishUpsert(planId);
        }
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateBudget(Long planId, Long userId, UpdateBudgetRequest req) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = loadPlan(planId);

        Long total = req.getBudgetTotal();
        Long perPerson = req.getBudgetPerPerson();

        if (total != null && total < 0) {
            throw new BadRequestException("budgetTotal must be >= 0");
        }
        if (perPerson != null && perPerson < 0) {
            throw new BadRequestException("budgetPerPerson must be >= 0");
        }

        if (req.getBudgetCurrency() != null && !req.getBudgetCurrency().isBlank()) {
            plan.setBudgetCurrency(req.getBudgetCurrency());
        }
        if (total != null) {
            plan.setBudgetTotal(total);
        }
        if (perPerson != null) {
            plan.setBudgetPerPerson(perPerson);
        }
        long revision = planBoardService.incrementBoardRevision(planId);
        planBoardService.publishBoard(planId, userId, "PLAN_BUDGET_UPDATED");

        Map<String, Object> budgetPatch = new HashMap<>();
        budgetPatch.put("budgetCurrency", plan.getBudgetCurrency());
        budgetPatch.put("budgetTotal", plan.getBudgetTotal());
        budgetPatch.put("budgetPerPerson", plan.getBudgetPerPerson());
        planBoardService.emitV2Patch(planId, userId, "PLAN", planId, "UPDATE", budgetPatch, revision);
        planIndexPublisher.publishUpsert(planId);
    }

}
