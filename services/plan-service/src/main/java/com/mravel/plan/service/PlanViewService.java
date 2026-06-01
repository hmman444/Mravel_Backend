package com.mravel.plan.service;

import com.mravel.plan.model.PlanView;
import com.mravel.plan.repository.PlanRepository;
import com.mravel.plan.repository.PlanViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Đếm lượt xem theo cửa sổ thời gian (throttle):
 * 1 user xem 1 plan -> tính 1 lượt; trong vòng {@code windowHours} giờ kế tiếp
 * KHÔNG tính thêm; sau cửa sổ đó, xem lại -> tính thêm 1 lượt.
 *
 * Mỗi (user, plan) giữ 1 bản ghi {@link PlanView} lưu thời điểm xem được tính
 * gần nhất (viewedAt). {@code Plan.views} là bộ đếm cộng dồn (tăng nguyên tử).
 * Tách bean riêng để @Transactional có hiệu lực khi PlanService/PlanBoardService gọi.
 */
@Service
@RequiredArgsConstructor
public class PlanViewService {

    private final PlanViewRepository planViewRepository;
    private final PlanRepository planRepository;

    /** Cửa sổ chống lặp (giờ). Mặc định 4h, chỉnh qua mravel.view.window-hours. */
    @Value("${mravel.view.window-hours:4}")
    private long windowHours;

    /**
     * @return true nếu tính được 1 lượt xem mới; false nếu bỏ qua
     *         (ẩn danh / là tác giả / còn trong cửa sổ throttle).
     */
    @Transactional
    public boolean recordView(Long planId, Long viewerId, Long authorId) {
        if (planId == null || viewerId == null)
            return false; // ẩn danh: không định danh được -> không tính
        if (Objects.equals(viewerId, authorId))
            return false; // không tính lượt xem của chính tác giả

        Instant now = Instant.now();
        Instant threshold = now.minus(Duration.ofHours(windowHours));

        Optional<PlanView> existingOpt = planViewRepository.findByUserIdAndPlanId(viewerId, planId);
        if (existingOpt.isPresent()) {
            PlanView pv = existingOpt.get();
            // Còn trong cửa sổ (đã xem trong vòng windowHours) -> không tính thêm
            if (pv.getViewedAt() != null && pv.getViewedAt().isAfter(threshold)) {
                return false;
            }
            // Đã quá cửa sổ -> tính thêm 1 lượt, cập nhật mốc thời gian
            pv.setViewedAt(now);
            planViewRepository.save(pv);
            planRepository.incrementViews(planId);
            return true;
        }

        // Lần xem đầu tiên
        planViewRepository.save(PlanView.builder()
                .userId(viewerId)
                .planId(planId)
                .viewedAt(now)
                .build());
        planRepository.incrementViews(planId);
        return true;
    }
}
