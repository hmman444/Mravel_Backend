package com.mravel.plan.repository;

import com.mravel.plan.document.PlanDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ES repository for PlanDocument.
 * Complex queries are built dynamically in PlanSearchService using ElasticsearchOperations.
 */
public interface PlanEsRepository extends ElasticsearchRepository<PlanDocument, String> {
}
