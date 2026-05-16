package com.mravel.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.FavoriteDoc;
import com.mravel.catalog.model.enums.TargetType;

@Repository
public interface FavoriteDocRepository extends MongoRepository<FavoriteDoc, String> {

    Optional<FavoriteDoc> findByUserIdAndTargetTypeAndTargetId(Long userId, TargetType targetType, String targetId);

    boolean existsByUserIdAndTargetTypeAndTargetId(Long userId, TargetType targetType, String targetId);

    long countByTargetTypeAndTargetId(TargetType targetType, String targetId);

    Page<FavoriteDoc> findByUserIdAndTargetTypeOrderByCreatedAtDesc(Long userId, TargetType targetType, Pageable pageable);

    Page<FavoriteDoc> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
