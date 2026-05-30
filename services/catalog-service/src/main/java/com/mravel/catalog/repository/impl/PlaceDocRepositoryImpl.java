package com.mravel.catalog.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.repository.PlaceDocRepositoryCustom;
import com.mravel.common.i18n.LocaleConstants;

@Repository
public class PlaceDocRepositoryImpl implements PlaceDocRepositoryCustom {

  @Autowired
  MongoTemplate mongo;

  @Override
  public Page<PlaceDoc> searchPlaces(String qtext, Pageable pageable) {
    final Pageable pb = (pageable != null) ? pageable : Pageable.unpaged();

    Query q = new Query().with(pb);

    List<Criteria> cs = new ArrayList<>();
    cs.add(where("active").is(true));

    final List<PlaceKind> kinds = List.of(PlaceKind.DESTINATION, PlaceKind.POI);
    cs.add(where("kind").in(Objects.requireNonNull(kinds)));

    if (qtext != null && !qtext.isBlank()) {
      final String kw = qtext;

      cs.add(new Criteria().orOperator(
          where("name." + LocaleConstants.VI).regex(kw, "i"),
          where("name." + LocaleConstants.EN).regex(kw, "i"),
          where("slug").regex(kw, "i"),
          where("addressLine." + LocaleConstants.VI).regex(kw, "i"),
          where("addressLine." + LocaleConstants.EN).regex(kw, "i"),
          where("provinceName." + LocaleConstants.VI).regex(kw, "i"),
          where("provinceName." + LocaleConstants.EN).regex(kw, "i"),
          where("districtName." + LocaleConstants.VI).regex(kw, "i"),
          where("districtName." + LocaleConstants.EN).regex(kw, "i"),
          where("wardName." + LocaleConstants.VI).regex(kw, "i"),
          where("wardName." + LocaleConstants.EN).regex(kw, "i")));
    }

    q.addCriteria(Objects.requireNonNull(and(cs)));

    long total = mongo.count(Query.of(q).limit(-1).skip(-1), PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pb, total);
  }

  @Override
  public Page<PlaceDoc> findChildrenByParentSlug(String parentSlug, PlaceKind kind, Pageable pageable) {
    final Pageable pb = (pageable != null) ? pageable : Pageable.unpaged();

    Query q = new Query().with(pb);
    List<Criteria> cs = new ArrayList<>();

    cs.add(where("active").is(true));
    if (kind != null)
      cs.add(where("kind").is(kind));
    cs.add(where("parentSlug").is(parentSlug));

    final Criteria[] arr = cs.toArray(Criteria[]::new);
    Objects.requireNonNull(arr);

    q.addCriteria(new Criteria().andOperator(arr));

    long total = mongo.count(Query.of(q).limit(-1).skip(-1), PlaceDoc.class);
    List<PlaceDoc> data = mongo.find(q, PlaceDoc.class);
    return new PageImpl<>(data, pb, total);
  }

  @Override
  public Page<PlaceDoc> findChildrenByParentSlugIncludeInactive(String parentSlug, PlaceKind kind, Pageable pageable) {
    final Pageable pb = (pageable != null) ? pageable : Pageable.unpaged();

    Query q = new Query()
        .addCriteria(Criteria.where("parentSlug").is(parentSlug))
        .addCriteria(Criteria.where("kind").is(kind))
        .with(pb);

    long total = mongo.count(Query.of(q).limit(-1).skip(-1), PlaceDoc.class);
    return new PageImpl<>(mongo.find(q, PlaceDoc.class), pb, total);
  }

  private static Criteria and(List<Criteria> cs) {
    final Criteria[] arr = cs.toArray(Criteria[]::new);
    Objects.requireNonNull(arr, "criteria array must not be null");
    return new Criteria().andOperator(arr);
  }
}
