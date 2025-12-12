package com.mravel.plan.service;

import com.mravel.plan.dto.board.UpdateBudgetRequest;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.PlanListRepository;
import com.mravel.plan.repository.PlanRepository;
import com.mravel.plan.repository.PlanCardRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateTitle(Long planId, Long userId, String title) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setTitle(title);

        planBoardService.publishBoard(planId, userId, "PLAN_TITLE_UPDATED");
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateDescription(Long planId, Long userId, String desc) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setDescription(desc);

        planBoardService.publishBoard(planId, userId, "PLAN_DESCRIPTION_UPDATED");

    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateStatus(Long planId, Long userId, PlanStatus status) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setStatus(status);

        planBoardService.publishBoard(planId, userId, "PLAN_STATUS_UPDATED");

    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateThumbnail(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).setThumbnail(url);

        planBoardService.publishBoard(planId, userId, "PLAN_THUMBNAIL_UPDATED");
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void addImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).getImages().add(url);

        planBoardService.publishBoard(planId, userId, "PLAN_IMAGE_ADDED");
    }

    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void removeImage(Long planId, Long userId, String url) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);
        loadPlan(planId).getImages().remove(url);
        planBoardService.publishBoard(planId, userId, "PLAN_IMAGE_REMOVED");
    }

    // logic update date
    @Transactional
    @CacheEvict(value = "boardSnapshot", key = "#planId", beforeInvocation = true)
    public void updateDates(Long planId, Long userId, LocalDate startNew, LocalDate endNew) {
        permission.checkPermission(planId, userId, PlanRole.EDITOR);

        if (endNew.isBefore(startNew)) {
            throw new RuntimeException("endDate must be >= startDate");
        }

        Plan plan = loadPlan(planId);
        LocalDate startOld = plan.getStartDate();

        List<PlanList> dayLists = loadDayLists(planId);
        PlanList trash = loadTrash(planId);

        int oldCount = dayLists.size();
        int newCount = (int) (endNew.toEpochDay() - startNew.toEpochDay()) + 1;

        boolean startChanged = !startNew.equals(startOld);
        boolean durationUnchanged = newCount == oldCount;

        // -------------------------------------------------------------
        // CASE A — USER CHỈ ĐỔI startDate → GIỮ NGUYÊN duration
        // -------------------------------------------------------------
        if (startChanged && durationUnchanged) {

            int shift = (int) (startNew.toEpochDay() - startOld.toEpochDay());

            for (int i = 0; i < dayLists.size(); i++) {
                dayLists.get(i).setPosition(i);
                dayLists.get(i).setDayDate(startOld.plusDays(i + shift));
            }

            plan.setStartDate(startNew);
            plan.setEndDate(startNew.plusDays(oldCount - 1));

            trash.setPosition(oldCount);

            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
            return;
        }

        // -------------------------------------------------------------
        // CASE B — LOGIC THAY ĐỔI endDate (tăng/giảm/tối ưu ngày)
        // (Toàn bộ các logic cũ giữ nguyên)
        // -------------------------------------------------------------

        // CASE 1 — ÉP VỀ 1 NGÀY
        if (newCount == 1 && oldCount > 1) {

            PlanList keep = dayLists.get(0);
            keep.setPosition(0);
            keep.setDayDate(startNew);

            int basePos = (int) cardRepo.countByListId(trash.getId());
            for (int i = 1; i < dayLists.size(); i++) {
                PlanList rm = dayLists.get(i);

                List<PlanCard> cards = cardRepo.findByListIdOrderByPositionAsc(rm.getId());
                for (PlanCard c : cards) {
                    c.setList(trash);
                    c.setPosition(basePos++);
                }
                if (!cards.isEmpty())
                    cardRepo.saveAll(cards);

                listRepo.delete(rm);
            }

            plan.setStartDate(startNew);
            plan.setEndDate(startNew);
            trash.setPosition(1);

            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
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

            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
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

            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
            return;
        }

        // CASE 4 — RÚT NGẮN NGÀY
        if (newCount < oldCount) {

            int basePos = (int) cardRepo.countByListId(trash.getId());

            for (int i = newCount; i < oldCount; i++) {
                PlanList rm = dayLists.get(i);

                List<PlanCard> cards = cardRepo.findByListIdOrderByPositionAsc(rm.getId());
                for (PlanCard c : cards) {
                    c.setList(trash);
                    c.setPosition(basePos++);
                }
                if (!cards.isEmpty())
                    cardRepo.saveAll(cards);

                listRepo.delete(rm);
            }

            for (int i = 0; i < newCount; i++) {
                dayLists.get(i).setPosition(i);
                dayLists.get(i).setDayDate(startNew.plusDays(i));
            }

            plan.setStartDate(startNew);
            plan.setEndDate(endNew);
            trash.setPosition(newCount);

            planBoardService.publishBoard(planId, userId, "PLAN_DATES_UPDATED");
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
            throw new RuntimeException("budgetTotal must be >= 0");
        }
        if (perPerson != null && perPerson < 0) {
            throw new RuntimeException("budgetPerPerson must be >= 0");
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
        planBoardService.publishBoard(planId, userId, "PLAN_BUDGET_UPDATED");

    }

}
