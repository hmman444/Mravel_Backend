package com.mravel.catalog.service.admin;

import com.mravel.catalog.dto.admin.AdminCatalogDtos.AdminServiceSummaryDTO;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AdminCatalogQueryService {

    private final MongoTemplate mongoTemplate;

    public Page<AdminServiceSummaryDTO> listHotels(
            String status,
            Boolean active,
            String partnerId,
            String destinationSlug,
            Boolean unlockRequested,
            String q,
            Pageable pageable) {
        Query query = baseQuery(status, active, partnerId, destinationSlug, unlockRequested, q);
        long total = mongoTemplate.count(query, HotelDoc.class);

        query.with(pageable);
        List<HotelDoc> docs = mongoTemplate.find(query, HotelDoc.class);

        List<AdminServiceSummaryDTO> out = new ArrayList<>();
        for (HotelDoc d : docs)
            out.add(mapHotel(d));

        return new PageImpl<>(out, pageable, total);
    }

    public Page<AdminServiceSummaryDTO> listRestaurants(
            String status,
            Boolean active,
            String partnerId,
            String destinationSlug,
            Boolean unlockRequested,
            String q,
            Pageable pageable) {
        Query query = baseQuery(status, active, partnerId, destinationSlug, unlockRequested, q);
        long total = mongoTemplate.count(query, RestaurantDoc.class);

        query.with(pageable);
        List<RestaurantDoc> docs = mongoTemplate.find(query, RestaurantDoc.class);

        List<AdminServiceSummaryDTO> out = new ArrayList<>();
        for (RestaurantDoc d : docs)
            out.add(mapRestaurant(d));

        return new PageImpl<>(out, pageable, total);
    }

    private Query baseQuery(
            String status,
            Boolean active,
            String partnerId,
            String destinationSlug,
            Boolean unlockRequested,
            String q) {
        List<Criteria> and = new ArrayList<>();

        // only not deleted
        and.add(Criteria.where("deletedAt").is(null));

        if (status != null && !status.isBlank()) {
            and.add(Criteria.where("moderation.status").is(status.trim().toUpperCase()));
        }
        if (active != null) {
            and.add(Criteria.where("active").is(active));
        }
        if (partnerId != null && !partnerId.isBlank()) {
            and.add(Criteria.where("publisher.partnerId").is(partnerId.trim()));
        }
        if (destinationSlug != null && !destinationSlug.isBlank()) {
            and.add(Criteria.where("destinationSlug").is(destinationSlug.trim()));
        }
        if (unlockRequested != null) {
            if (unlockRequested)
                and.add(Criteria.where("moderation.unlockRequestedAt").ne(null));
            else
                and.add(Criteria.where("moderation.unlockRequestedAt").is(null));
        }
        if (q != null && !q.isBlank()) {
            String raw = q.trim();
            Pattern p = Pattern.compile(Pattern.quote(raw), Pattern.CASE_INSENSITIVE);

            // match name OR slug
            and.add(new Criteria().orOperator(
                    Criteria.where("name").regex(p),
                    Criteria.where("slug").regex(p)));
        }

        Query query = new Query(new Criteria().andOperator(and.toArray(new Criteria[0])));
        // sort mặc định: mới cập nhật lên đầu
        query.with(Sort.by(Sort.Direction.DESC, "publisher.lastUpdatedAt"));
        return query;
    }

    private AdminServiceSummaryDTO mapHotel(HotelDoc d) {
        var pub = d.getPublisher();
        var mod = d.getModeration();
        return new AdminServiceSummaryDTO(
                d.getId(),
                d.getName(),
                d.getSlug(),
                d.getDestinationSlug(),
                d.getCityName(),
                d.getActive(),

                pub != null ? pub.getPartnerId() : null,
                pub != null ? pub.getPartnerName() : null,
                pub != null ? pub.getPartnerEmail() : null,
                pub != null ? pub.getLastUpdatedAt() : null,

                mod != null && mod.getStatus() != null ? mod.getStatus().name() : null,
                mod != null ? mod.getRejectionReason() : null,
                mod != null ? mod.getBlockedReason() : null,

                mod != null ? mod.getUnlockRequestedAt() : null,
                mod != null ? mod.getUnlockRequestReason() : null,

                mod != null ? mod.getLastActionByAdminId() : null,
                mod != null ? mod.getLastActionAt() : null);
    }

    private AdminServiceSummaryDTO mapRestaurant(RestaurantDoc d) {
        var pub = d.getPublisher();
        var mod = d.getModeration();
        return new AdminServiceSummaryDTO(
                d.getId(),
                d.getName(),
                d.getSlug(),
                d.getDestinationSlug(),
                d.getCityName(),
                d.getActive(),

                pub != null ? pub.getPartnerId() : null,
                pub != null ? pub.getPartnerName() : null,
                pub != null ? pub.getPartnerEmail() : null,
                pub != null ? pub.getLastUpdatedAt() : null,

                mod != null && mod.getStatus() != null ? mod.getStatus().name() : null,
                mod != null ? mod.getRejectionReason() : null,
                mod != null ? mod.getBlockedReason() : null,

                mod != null ? mod.getUnlockRequestedAt() : null,
                mod != null ? mod.getUnlockRequestReason() : null,

                mod != null ? mod.getLastActionByAdminId() : null,
                mod != null ? mod.getLastActionAt() : null);
    }
}
