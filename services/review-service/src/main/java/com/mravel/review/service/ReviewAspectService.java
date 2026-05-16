package com.mravel.review.service;

import com.mravel.review.dto.ReviewAspectDefinitionDTO;
import com.mravel.review.model.ReviewAspectDefinition;
import com.mravel.review.model.TargetType;
import com.mravel.review.repository.ReviewAspectDefinitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewAspectService {

    private final ReviewAspectDefinitionRepository definitionRepository;

    public List<ReviewAspectDefinitionDTO> getDefinitions(TargetType category) {
        return definitionRepository.findByCategoryAndActiveTrueOrderBySortOrderAsc(category)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ReviewAspectDefinitionDTO> getAllDefinitions(TargetType category) {
        return definitionRepository.findByCategoryOrderBySortOrderAsc(category)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ReviewAspectDefinitionDTO createDefinition(ReviewAspectDefinition def) {
        return toDTO(definitionRepository.save(def));
    }

    public ReviewAspectDefinitionDTO updateDefinition(Integer id, ReviewAspectDefinition update) {
        ReviewAspectDefinition existing = definitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy aspect definition: " + id));
        existing.setLabelVi(update.getLabelVi());
        existing.setLabelEn(update.getLabelEn());
        existing.setIcon(update.getIcon());
        existing.setSortOrder(update.getSortOrder());
        existing.setActive(update.getActive());
        return toDTO(definitionRepository.save(existing));
    }

    public void deleteDefinition(Integer id) {
        ReviewAspectDefinition existing = definitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy aspect definition: " + id));
        existing.setActive(false);
        definitionRepository.save(existing);
    }

    private ReviewAspectDefinitionDTO toDTO(ReviewAspectDefinition d) {
        return ReviewAspectDefinitionDTO.builder()
                .id(d.getId())
                .code(d.getCode())
                .labelVi(d.getLabelVi())
                .labelEn(d.getLabelEn())
                .icon(d.getIcon())
                .sortOrder(d.getSortOrder())
                .build();
    }
}
