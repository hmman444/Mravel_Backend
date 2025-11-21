package com.mravel.plan.service;

import com.mravel.plan.dto.board.ExpenseRequest;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final PlanRepository planRepo;
    private final PlanCardRepository cardRepo;
    private final PlanExpenseRepository expenseRepo;
    private final PlanPermissionService permissionService;

    @Transactional
    public PlanExpense createExpense(Long planId, Long userId, ExpenseRequest req) {

        // 1. User phải có quyền EDIT trở lên
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanCard card = null;
        if (req.getCardId() != null) {
            card = cardRepo.findById(req.getCardId())
                    .orElseThrow(() -> new RuntimeException("Card not found"));

            if (!card.getList().getPlan().getId().equals(planId)) {
                throw new RuntimeException("Card không thuộc kế hoạch");
            }
        }

        PlanExpense exp = PlanExpense.builder()
                .title(req.getTitle())
                .amount(req.getAmount())
                .category(req.getCategory())
                .note(req.getNote())
                .destination(req.getDestination())
                .card(card)
                .plan(plan)
                .build();

        expenseRepo.save(exp);

        updatePlanTotalCost(planId);

        return exp;
    }

    @Transactional
    public PlanExpense updateExpense(Long planId, Long expenseId, Long userId, ExpenseRequest req) {

        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanExpense exp = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!exp.getPlan().getId().equals(planId)) {
            throw new RuntimeException("Expense không thuộc plan");
        }

        exp.setTitle(req.getTitle());
        exp.setAmount(req.getAmount());
        exp.setCategory(req.getCategory());
        exp.setNote(req.getNote());
        exp.setDestination(req.getDestination());

        if (req.getCardId() != null) {
            PlanCard card = cardRepo.findById(req.getCardId())
                    .orElseThrow(() -> new RuntimeException("Card not found"));
            exp.setCard(card);
        }

        updatePlanTotalCost(planId);
        return exp;
    }

    @Transactional
    public void deleteExpense(Long planId, Long expenseId, Long userId) {

        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanExpense exp = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!exp.getPlan().getId().equals(planId)) {
            throw new RuntimeException("Expense không thuộc plan");
        }

        expenseRepo.delete(exp);
        updatePlanTotalCost(planId);
    }

    public List<PlanExpense> getExpensesByPlan(Long planId, Long userId) {

        if (!permissionService.canView(planId, userId, false)) {
            throw new RuntimeException("No permission to view expenses");
        }

        return expenseRepo.findByPlan_Id(planId);
    }

    public List<PlanExpense> getExpensesByCard(Long cardId, Long userId) {

        PlanCard card = cardRepo.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        if (!permissionService.canView(card.getList().getPlan().getId(), userId, false)) {
            throw new RuntimeException("No permission");
        }

        return expenseRepo.findByCard_Id(cardId);
    }

    @Transactional
    public Long updatePlanTotalCost(Long planId) {

        Long total = expenseRepo.sumAmountByPlanId(planId);

        Plan plan = planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        plan.setTotalCost(total);
        return total;
    }
}
