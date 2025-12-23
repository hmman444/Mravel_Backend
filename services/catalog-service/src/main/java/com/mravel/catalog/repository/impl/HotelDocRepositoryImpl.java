// src/main/java/com/mravel/catalog/repository/impl/HotelDocRepositoryImpl.java
package com.mravel.catalog.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.HotelDoc.HotelStatus;
import com.mravel.catalog.repository.HotelDocRepositoryCustom;

@Repository
public class HotelDocRepositoryImpl implements HotelDocRepositoryCustom {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public Page<HotelDoc> searchHotels(
            String location,
            Integer minGuestsPerRoom,
            Integer requiredRooms,
            Pageable pageable
    ) {
        Query q = new Query();

        if (pageable != null) {
            if (pageable.isPaged()) q.with(pageable);
            else q.with(pageable.getSort());
        }

        List<Criteria> cs = new ArrayList<>();
        cs.add(where("active").is(true));
        // chỉ show hotel đã duyệt
        cs.add(where("moderation.status").is(HotelStatus.APPROVED));

        // ----- location -----
        if (has(location)) {
            String loc = location.trim();
            cs.add(new Criteria().orOperator(
                    where("destinationSlug").is(loc),
                    where("cityName").regex(loc, "i"),
                    where("districtName").regex(loc, "i"),
                    where("wardName").regex(loc, "i"),
                    where("addressLine").regex(loc, "i")
            ));
        }

        // ----- capacity theo roomTypes -----
        if (minGuestsPerRoom != null || requiredRooms != null) {
            Criteria roomCrit = new Criteria();

            List<Criteria> roomConditions = new ArrayList<>();
            if (minGuestsPerRoom != null) {
                roomConditions.add(where("maxGuests").gte(minGuestsPerRoom));
            }
            if (requiredRooms != null && requiredRooms > 0) {
                roomConditions.add(where("totalRooms").gte(requiredRooms));
            }

            if (!roomConditions.isEmpty()) {
                roomCrit = new Criteria().andOperator(roomConditions.toArray(new Criteria[0]));
                cs.add(where("roomTypes").elemMatch(roomCrit));
            }
        }

        q.addCriteria(and(cs));

        // count phải bỏ limit/skip
        Query countQ = Query.of(q).limit(-1).skip(-1);

        long total = mongo.count(countQ, HotelDoc.class);
        List<HotelDoc> data = mongo.find(q, HotelDoc.class);

        Pageable pb = (pageable != null) ? pageable : Pageable.unpaged();
        return new PageImpl<>(data, pb, total);
    }

    // ===== helpers =====

    private static boolean has(String s) {
        return s != null && !s.isBlank();
    }

    private static Criteria and(List<Criteria> cs) {
        return new Criteria().andOperator(cs.toArray(new Criteria[0]));
    }
}