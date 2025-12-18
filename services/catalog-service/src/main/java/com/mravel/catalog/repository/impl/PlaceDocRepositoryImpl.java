package com.mravel.catalog.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.repository.PlaceDocRepositoryCustom;

@Repository
public class PlaceDocRepositoryImpl implements PlaceDocRepositoryCustom {

  @Autowired
  MongoTemplate mongo;

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
          where("wardName").regex(qtext, "i")));
    }
    q.addCriteria(and(cs));
    long total = mongo.count(q, PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pageable, total);
  }

  // ===== helpers =====
  private static boolean has(String s) {
    return s != null && !s.isBlank();
  }

  private static Criteria and(List<Criteria> cs) {
    return new Criteria().andOperator(cs.toArray(new Criteria[0]));
  }

  @Override
  public Page<PlaceDoc> findChildrenByParentSlug(String parentSlug, PlaceKind kind, Pageable pageable) {
    Query q = new Query().with(pageable);
    List<Criteria> cs = new ArrayList<>();
    cs.add(where("active").is(true));
    if (kind != null)
      cs.add(where("kind").is(kind)); // POI mặc định
    cs.add(where("parentSlug").is(parentSlug)); // ràng theo destination
    q.addCriteria(new Criteria().andOperator(cs.toArray(new Criteria[0])));

    long total = mongo.count(q, PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pageable, total);
  }

  @Override
  public Page<PlaceDoc> findChildrenByParentSlugIncludeInactive(String parentSlug, PlaceKind kind, Pageable pageable) {
    Query q = new Query()
        .addCriteria(Criteria.where("parentSlug").is(parentSlug))
        .addCriteria(Criteria.where("kind").is(kind))
        .with(pageable);

    long total = mongo.count(Query.of(q).limit(-1).skip(-1), PlaceDoc.class);
    return new PageImpl<>(mongo.find(q, PlaceDoc.class), pageable, total);
  }
}