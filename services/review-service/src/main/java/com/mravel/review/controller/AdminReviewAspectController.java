package com.mravel.review.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.review.dto.ReviewAspectDefinitionDTO;
import com.mravel.review.model.ReviewAspectDefinition;
import com.mravel.review.model.TargetType;
import com.mravel.review.service.ReviewAspectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/review-aspects")
@RequiredArgsConstructor
public class AdminReviewAspectController {

    private final ReviewAspectService reviewAspectService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewAspectDefinitionDTO>>> getAll(
            @RequestParam TargetType category) {
        return ResponseEntity.ok(ApiResponse.success("OK", reviewAspectService.getAllDefinitions(category)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewAspectDefinitionDTO>> create(
            @Valid @RequestBody ReviewAspectDefinition def) {
        return ResponseEntity.ok(ApiResponse.success("Tạo thành công", reviewAspectService.createDefinition(def)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewAspectDefinitionDTO>> update(
            @PathVariable Integer id,
            @Valid @RequestBody ReviewAspectDefinition def) {
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thành công", reviewAspectService.updateDefinition(id, def)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        reviewAspectService.deleteDefinition(id);
        return ResponseEntity.ok(ApiResponse.success("Đã vô hiệu hoá", null));
    }
}
