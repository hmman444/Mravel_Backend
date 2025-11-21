package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.board.ExpenseRequest;
import com.mravel.plan.model.PlanExpense;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans/{planId}/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final CurrentUserService currentUser;
    private final ExpenseService expenseService;

    /**
     * 📌 Tạo chi phí mới
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PlanExpense>> createExpense(
            @PathVariable Long planId,
            @RequestBody ExpenseRequest req) {

        Long userId = currentUser.getId();

        PlanExpense exp = expenseService.createExpense(planId, userId, req);

        return ResponseEntity.ok(
                ApiResponse.success("Tạo chi phí thành công", exp));
    }

    /**
     * 📌 Cập nhật chi phí
     */
    @PutMapping("/{expenseId}")
    public ResponseEntity<ApiResponse<PlanExpense>> updateExpense(
            @PathVariable Long planId,
            @PathVariable Long expenseId,
            @RequestBody ExpenseRequest req) {

        Long userId = currentUser.getId();

        PlanExpense exp = expenseService.updateExpense(planId, expenseId, userId, req);

        return ResponseEntity.ok(
                ApiResponse.success("Cập nhật chi phí thành công", exp));
    }

    /**
     * 📌 Xóa chi phí
     */
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(
            @PathVariable Long planId,
            @PathVariable Long expenseId) {

        Long userId = currentUser.getId();

        expenseService.deleteExpense(planId, expenseId, userId);

        return ResponseEntity.ok(
                ApiResponse.success("Xóa chi phí thành công", null));
    }

    /**
     * 📌 Lấy danh sách chi phí theo plan
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<PlanExpense>>> getExpensesByPlan(
            @PathVariable Long planId) {

        Long userId = currentUser.getId();

        List<PlanExpense> list = expenseService.getExpensesByPlan(planId, userId);

        return ResponseEntity.ok(
                ApiResponse.success("Lấy danh sách chi phí thành công", list));
    }

    /**
     * 📌 Lấy chi phí theo card
     */
    @GetMapping("/card/{cardId}")
    public ResponseEntity<ApiResponse<List<PlanExpense>>> getExpensesByCard(
            @PathVariable Long planId,
            @PathVariable Long cardId) {

        Long userId = currentUser.getId();
        List<PlanExpense> list = expenseService.getExpensesByCard(cardId, userId);

        return ResponseEntity.ok(
                ApiResponse.success("Lấy chi phí của card thành công", list));
    }

    /**
     * 📌 Tính lại tổng chi phí (nếu cần gọi riêng)
     */
    @PutMapping("/update-total")
    public ResponseEntity<ApiResponse<Long>> updateTotalCost(
            @PathVariable Long planId) {

        Long userId = currentUser.getId();

        // cần permission EDIT
        // Bạn có thể add check:
        // permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Long total = expenseService.updatePlanTotalCost(planId);

        return ResponseEntity.ok(
                ApiResponse.success("Cập nhật tổng chi phí thành công", total));
    }
}
