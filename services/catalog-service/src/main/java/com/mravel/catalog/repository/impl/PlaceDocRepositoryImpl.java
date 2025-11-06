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

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.VenueType;
import com.mravel.catalog.model.enums.TagType;
import com.mravel.catalog.repository.PlaceDocRepositoryCustom;

@Repository
public class PlaceDocRepositoryImpl implements PlaceDocRepositoryCustom {

  @Autowired
  MongoTemplate mongo;

  @Override
  public Page<PlaceDoc> searchHotels(String location, String checkIn, String checkOut,
                                     Integer adults, Integer rooms, Pageable pageable) {
    Query q = new Query().with(pageable);
    List<Criteria> cs = baseActiveVenue(VenueType.HOTEL);   // kind=VENUE & venueType=HOTEL
    if (has(location)) cs.add(locationCrit(location));
    q.addCriteria(and(cs));
    long total = mongo.count(q, PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pageable, total);
  }

  @Override
  public Page<PlaceDoc> searchRestaurants(String location, Set<String> cuisineSlugs, Pageable pageable) {
    Query q = new Query().with(pageable);
    List<Criteria> cs = baseActiveVenue(VenueType.RESTAURANT); // kind=VENUE & venueType=RESTAURANT
    if (has(location)) cs.add(locationCrit(location));
    if (cuisineSlugs != null && !cuisineSlugs.isEmpty()) {
      cs.add(
          where("tags").elemMatch(
            new Criteria().andOperator(
              where("type").is(TagType.CUISINE),
              where("slug").in(cuisineSlugs)
            )
          )
        );
    }
    q.addCriteria(and(cs));
    long total = mongo.count(q, PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pageable, total);
  }

  @Override
  public Page<PlaceDoc> searchPlaces(String qtext, Pageable pageable) {
    Query q = new Query().with(pageable);
    List<Criteria> cs = new ArrayList<>();
    cs.add(where("active").is(true));
    // “Địa điểm” & “điểm tham quan” (POI)
    cs.add(where("kind").in(List.of(PlaceKind.DESTINATION, PlaceKind.POI)));
    if (has(qtext)) {
      cs.add(new Criteria().orOperator(
        where("name").regex(qtext, "i"),
        where("slug").regex(qtext, "i"),
        where("addressLine").regex(qtext, "i"),
        where("provinceName").regex(qtext, "i"),
        where("districtName").regex(qtext, "i"),
        where("wardName").regex(qtext, "i")
      ));
    }
    q.addCriteria(and(cs));
    long total = mongo.count(q, PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pageable, total);
  }

  // ===== helpers =====
  private static boolean has(String s) { return s != null && !s.isBlank(); }

  /** kind=VENUE + venueType=... */
  private static List<Criteria> baseActiveVenue(VenueType vt) {
    List<Criteria> cs = new ArrayList<>();
    cs.add(where("active").is(true));
    cs.add(where("kind").is(PlaceKind.VENUE));
    cs.add(where("venueType").is(vt));
    return cs;
  }

  private static Criteria and(List<Criteria> cs) {
    return new Criteria().andOperator(cs.toArray(new Criteria[0]));
  }

  /** Ưu tiên match theo parentSlug (destination slug), rồi tới mã/tên địa lý + địa chỉ */
  private static Criteria locationCrit(String locRaw) {
    String loc = locRaw.trim();
    return new Criteria().orOperator(
      where("parentSlug").is(loc),   // ← nếu bạn có field này trong PlaceDoc
      where("provinceCode").is(loc),
      where("districtCode").is(loc),
      where("wardCode").is(loc),
      where("addressLine").regex(loc, "i"),
      where("provinceName").regex(loc, "i"),
      where("districtName").regex(loc, "i"),
      where("wardName").regex(loc, "i")
    );
  }

  @Override
  public Page<PlaceDoc> findChildrenByParentSlug(String parentSlug, PlaceKind kind, Pageable pageable) {
    Query q = new Query().with(pageable);
    List<Criteria> cs = new ArrayList<>();
    cs.add(where("active").is(true));
    if (kind != null) cs.add(where("kind").is(kind));       // POI mặc định
    cs.add(where("parentSlug").is(parentSlug));             // ràng theo destination
    q.addCriteria(new Criteria().andOperator(cs.toArray(new Criteria[0])));

    long total = mongo.count(q, PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pageable, total);
  }
}