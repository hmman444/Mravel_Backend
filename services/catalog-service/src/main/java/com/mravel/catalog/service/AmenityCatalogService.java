package com.mravel.catalog.service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mravel.catalog.dto.amenity.AmenityGroupedResponse;
import com.mravel.catalog.dto.amenity.AmenityUpsertRequest;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.enums.AmenitySection;
import com.mravel.catalog.repository.AmenityCatalogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmenityCatalogService {

    private final AmenityCatalogRepository repo;

    public AmenityCatalogDoc create(AmenityUpsertRequest req) {
        AmenityCatalogDoc doc = AmenityCatalogDoc.builder()
                .code(normalizeCode(req.getCode()))
                .name(req.getName())
                .scope(req.getScope())
                .group(req.getGroup())
                .section(req.getSection())
                .icon(req.getIcon())
                .description(req.getDescription())
                .aliases(normalizeAliases(req.getAliases()))
                .sortOrder(req.getSortOrder() != null ? req.getSortOrder() : 0)
                .active(req.getActive() == null ? true : req.getActive())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        // check unique code+scope
        repo.findByCodeAndScope(doc.getCode(), doc.getScope())
                .ifPresent(x -> {
                    throw new IllegalArgumentException("Amenity code already exists in scope");
                });

        return repo.save(doc);
    }

    public AmenityCatalogDoc update(String id, AmenityUpsertRequest req) {
        AmenityCatalogDoc doc = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Amenity not found"));

        if (StringUtils.hasText(req.getCode()))
            doc.setCode(normalizeCode(req.getCode()));
        if (StringUtils.hasText(req.getName()))
            doc.setName(req.getName());
        if (req.getScope() != null)
            doc.setScope(req.getScope());
        if (req.getGroup() != null)
            doc.setGroup(req.getGroup());
        if (req.getSection() != null)
            doc.setSection(req.getSection());
        if (req.getIcon() != null)
            doc.setIcon(req.getIcon());
        if (req.getDescription() != null)
            doc.setDescription(req.getDescription());
        if (req.getAliases() != null)
            doc.setAliases(normalizeAliases(req.getAliases()));
        if (req.getSortOrder() != null)
            doc.setSortOrder(req.getSortOrder());
        if (req.getActive() != null)
            doc.setActive(req.getActive());

        doc.setUpdatedAt(Instant.now());

        // optional: enforce uniqueness again (code+scope) if changed
        repo.findByCodeAndScope(doc.getCode(), doc.getScope())
                .filter(x -> !x.getId().equals(doc.getId()))
                .ifPresent(x -> {
                    throw new IllegalArgumentException("Amenity code already exists in scope");
                });

        return repo.save(doc);
    }

    public void softDelete(String id) {
        AmenityCatalogDoc doc = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Amenity not found"));
        doc.setActive(false);
        doc.setUpdatedAt(Instant.now());
        repo.save(doc);
    }

    public List<AmenityCatalogDoc> list(AmenityScope scope, boolean activeOnly) {
        if (scope == null)
            return repo.findAll();
        if (activeOnly)
            return repo.findByScopeAndActiveTrue(scope);
        // fallback: filter in-memory (hoặc viết query riêng)
        return repo.findAll().stream()
                .filter(a -> a.getScope() == scope)
                .collect(Collectors.toList());
    }

    /** Validate codes exist & active & scope match */
    public List<AmenityCatalogDoc> validateCodes(AmenityScope scope, List<String> codes) {
        List<String> normalized = normalizeCodes(codes);

        if (normalized.isEmpty())
            return List.of();

        List<AmenityCatalogDoc> found = repo.findByScopeAndCodeInAndActiveTrue(scope, normalized);
        Set<String> foundCodes = found.stream().map(AmenityCatalogDoc::getCode).collect(Collectors.toSet());

        List<String> missing = normalized.stream().filter(c -> !foundCodes.contains(c)).toList();
        if (!missing.isEmpty()) {
            throw new IllegalArgumentException("Invalid amenity codes: " + missing);
        }
        return found;
    }

    /** Grouped response for FE form */
    public AmenityGroupedResponse grouped(AmenityScope scope) {
        List<AmenityCatalogDoc> items = repo.findByScopeAndActiveTrue(scope);

        // group(enum) -> section(enum) -> items
        Map<AmenityGroup, Map<AmenitySection, List<AmenityCatalogDoc>>> tree = items.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(a -> Optional.ofNullable(a.getSortOrder()).orElse(0)))
                .collect(Collectors.groupingBy(
                        a -> Optional.ofNullable(a.getGroup()).orElse(AmenityGroup.OTHER),
                        Collectors.groupingBy(a -> Optional.ofNullable(a.getSection()).orElse(AmenitySection.OTHER))));

        List<AmenityGroupedResponse.GroupNode> groups = tree.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Enum::name)))
                .map(g -> AmenityGroupedResponse.GroupNode.builder()
                        .group(g.getKey())
                        .sections(
                                g.getValue().entrySet().stream()
                                        .sorted(Map.Entry.comparingByKey(Comparator.comparing(Enum::name)))
                                        .map(s -> AmenityGroupedResponse.SectionNode.builder()
                                                .section(s.getKey())
                                                .items(
                                                        s.getValue().stream()
                                                                .map(a -> AmenityGroupedResponse.ItemNode.builder()
                                                                        .code(a.getCode())
                                                                        .name(a.getName())
                                                                        .icon(a.getIcon())
                                                                        .sortOrder(a.getSortOrder())
                                                                        .active(a.isActive())
                                                                        .build())
                                                                .toList())
                                                .build())
                                        .toList())
                        .build())
                .toList();

        return AmenityGroupedResponse.builder()
                .scope(scope)
                .groups(groups)
                .build();
    }

    // ---------------- helpers ----------------

    private static String normalizeCode(String code) {
        if (!StringUtils.hasText(code))
            throw new IllegalArgumentException("code is required");
        return code.trim().toUpperCase(Locale.ROOT);
    }

    private static List<String> normalizeCodes(List<String> codes) {
        if (codes == null)
            return List.of();
        return codes.stream()
                .filter(StringUtils::hasText)
                .map(c -> c.trim().toUpperCase(Locale.ROOT))
                .distinct()
                .toList();
    }

    private static List<String> normalizeAliases(List<String> aliases) {
        if (aliases == null)
            return List.of();
        return aliases.stream()
                .filter(StringUtils::hasText)
                .map(a -> a.trim())
                .distinct()
                .toList();
    }
}
