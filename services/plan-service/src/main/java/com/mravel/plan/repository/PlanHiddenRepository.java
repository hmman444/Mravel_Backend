package com.mravel.plan.repository;

import com.mravel.plan.model.PlanHidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanHiddenRepository extends JpaRepository<PlanHidden, Long> {

    Optional<PlanHidden> findByUserIdAndPlanId(Long userId, Long planId);

    boolean existsByUserIdAndPlanId(Long userId, Long planId);

    void deleteByUserIdAndPlanId(Long userId, Long planId);

    /** Danh sách planId mà userId đã ẩn (dùng để lọc feed/search). */
    @Query("select h.planId from PlanHidden h where h.userId = :userId")
    List<Long> findPlanIdsByUserId(@Param("userId") Long userId);

    /** Danh sách bản ghi ẩn của user (cho trang "Bài đã ẩn"). */
    List<PlanHidden> findByUserIdOrderByHiddenAtDesc(Long userId);
}
