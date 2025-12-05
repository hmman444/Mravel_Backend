package com.mravel.catalog.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantStatus;
import com.mravel.catalog.repository.RestaurantDocRepositoryCustom;

@Repository
public class RestaurantDocRepositoryImpl implements RestaurantDocRepositoryCustom {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public Page<RestaurantDoc> searchRestaurants(
            String location, Integer people, Set<String> cuisineSlugs, Pageable pageable
    ) {
        Pageable safePageable = (pageable == null) ? Pageable.unpaged() : pageable;
        Query q = new Query().with(safePageable);

        List<Criteria> cs = new ArrayList<>();
        cs.add(where("active").is(true));
        cs.add(where("moderation.status").is(RestaurantStatus.APPROVED));

        // location
        if (has(location)) {
            String loc = location.trim();
            cs.add(new Criteria().orOperator(
                    where("destinationSlug").is(loc),
                    where("parentPlaceSlug").is(loc),
                    where("cityName").regex(loc, "i"),
                    where("districtName").regex(loc, "i"),
                    where("wardName").regex(loc, "i"),
                    where("addressLine").regex(loc, "i")
            ));
        }

        // people
        if (people != null && people > 0) {
            cs.add(new Criteria().orOperator(
                    where("capacity.maxGroupSize").gte(people),
                    where("capacity.totalCapacity").gte(people)
            ));
        }

        // cuisines
        if (cuisineSlugs != null && !cuisineSlugs.isEmpty()) {
            cs.add(where("cuisines.code").in(cuisineSlugs));
        }

        q.addCriteria(and(cs));

        long total = mongo.count(q, RestaurantDoc.class);
        List<RestaurantDoc> data = mongo.find(q, RestaurantDoc.class);
        return new PageImpl<>(data, safePageable, total);
    }

    // ===== helpers =====

    private static boolean has(String s) {
        return s != null && !s.isBlank();
    }

    private static Criteria and(List<Criteria> cs) {
        return new Criteria().andOperator(cs.toArray(new Criteria[0]));
    }
}