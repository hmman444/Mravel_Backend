package com.mravel.catalog.search.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.search.PlaceSearchService;
import com.mravel.catalog.search.dto.PlaceSearchResult;

import lombok.RequiredArgsConstructor;

@Service
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "mongo", matchIfMissing = true)
@RequiredArgsConstructor
public class MongoPlaceSearchService implements PlaceSearchService {

    private final MongoTemplate mongo;

    @Override
    public Page<PlaceSearchResult> search(String q, PlaceKind kind, Pageable pageable) {
        List<Criteria> cs = new ArrayList<>();
        cs.add(where("active").is(true));
        if (kind != null) {
            cs.add(where("kind").is(kind));
        } else {
            cs.add(where("kind").in(List.of(PlaceKind.DESTINATION, PlaceKind.POI)));
        }

        if (q != null && !q.isBlank()) {
            cs.add(new Criteria().orOperator(
                    where("name").regex(q, "i"),
                    where("slug").regex(q, "i"),
                    where("addressLine").regex(q, "i"),
                    where("provinceName").regex(q, "i"),
                    where("districtName").regex(q, "i")
            ));
        }

        Query query = new Query().addCriteria(and(cs)).with(pageable);
        applyProjection(query);

        long total = mongo.count(Query.of(query).limit(-1).skip(-1), PlaceDoc.class);
        List<PlaceSearchResult> data = mongo.find(query, PlaceDoc.class).stream()
                .map(MongoPlaceSearchService::toSearchResult)
                .toList();
        return new PageImpl<>(data, pageable, total);
    }

    private static PlaceSearchResult toSearchResult(PlaceDoc p) {
        List<PlaceSearchResult.ImageRef> images = p.getImages() == null ? List.of()
                : p.getImages().stream().filter(Objects::nonNull)
                        .map(img -> new PlaceSearchResult.ImageRef(img.getUrl(), img.getCaption(), img.getCover(), img.getSortOrder()))
                        .toList();

        return new PlaceSearchResult(
                p.getId(), p.getName(), p.getSlug(),
                p.getKind(), p.getVenueType(),
                p.getAddressLine(), p.getProvinceName(),
                p.getLocation(),
                p.getPriceLevel(), p.getAvgRating(), p.getReviewsCount(),
                images, p.getActive());
    }

    private static void applyProjection(Query q) {
        q.fields()
                .include("name").include("slug").include("active").include("kind")
                .include("venueType").include("parentSlug")
                .include("cityName").include("districtName").include("wardName")
                .include("provinceName").include("addressLine").include("location")
                .include("shortDescription").include("priceLevel")
                .include("images").include("categories").include("tags")
                .include("childrenCount");
    }

    private static Criteria and(List<Criteria> cs) {
        return new Criteria().andOperator(cs.toArray(Criteria[]::new));
    }
}
