// src/main/java/com/mravel/catalog/repository/impl/RestaurantDocRepositoryImpl.java
package com.mravel.catalog.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantStatus;
import com.mravel.catalog.repository.RestaurantDocRepositoryCustom;
import com.mravel.common.i18n.LocaleConstants;

@Repository
public class RestaurantDocRepositoryImpl implements RestaurantDocRepositoryCustom {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public Page<RestaurantDoc> searchRestaurants(
            String location, Integer people, Set<String> cuisineSlugs, Pageable pageable) {
        final Pageable pb = (pageable == null) ? Pageable.unpaged() : pageable;

        Query q = new Query();

        if (pb.isPaged())
            q.with(pb);
        else
            q.with(pb.getSort());

        List<Criteria> cs = new ArrayList<>();
        cs.add(where("active").is(true));
        cs.add(where("moderation.status").is(RestaurantStatus.APPROVED));

        if (location != null && !location.isBlank()) {
            final String loc = location.trim();

            cs.add(new Criteria().orOperator(
                    where("destinationSlug").is(loc),
                    where("parentPlaceSlug").is(loc),
                    where("cityName." + LocaleConstants.VI).regex(loc, "i"),
                    where("cityName." + LocaleConstants.EN).regex(loc, "i"),
                    where("districtName." + LocaleConstants.VI).regex(loc, "i"),
                    where("districtName." + LocaleConstants.EN).regex(loc, "i"),
                    where("wardName." + LocaleConstants.VI).regex(loc, "i"),
                    where("wardName." + LocaleConstants.EN).regex(loc, "i"),
                    where("addressLine." + LocaleConstants.VI).regex(loc, "i"),
                    where("addressLine." + LocaleConstants.EN).regex(loc, "i")));
        }

        if (people != null && people > 0) {
            cs.add(new Criteria().orOperator(
                    where("capacity.maxGroupSize").gte(people),
                    where("capacity.totalCapacity").gte(people)));
        }

        if (cuisineSlugs != null && !cuisineSlugs.isEmpty()) {
            cs.add(where("cuisines.code").in(cuisineSlugs));
        }

        q.addCriteria(Objects.requireNonNull(and(cs)));

        Query countQ = Query.of(q).limit(-1).skip(-1);
        long total = mongo.count(countQ, RestaurantDoc.class);

        List<RestaurantDoc> data = mongo.find(q, RestaurantDoc.class);

        return new PageImpl<>(data, pb, total);
    }

    private static Criteria and(List<Criteria> cs) {
        final Criteria[] arr = cs.toArray(Criteria[]::new);
        Objects.requireNonNull(arr, "criteria array must not be null");
        return new Criteria().andOperator(arr);
    }
}
