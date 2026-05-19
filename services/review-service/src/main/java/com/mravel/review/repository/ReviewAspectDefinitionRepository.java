package com.mravel.review.repository;

import com.mravel.review.model.ReviewAspectDefinition;
import com.mravel.review.model.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewAspectDefinitionRepository extends JpaRepository<ReviewAspectDefinition, Integer> {

    List<ReviewAspectDefinition> findByCategoryAndActiveTrueOrderBySortOrderAsc(TargetType category);

    List<ReviewAspectDefinition> findByCategoryOrderBySortOrderAsc(TargetType category);
}
