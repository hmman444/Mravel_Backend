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

import com.mravel.catalog.dto.SearchRequests.RestaurantSearchRequest;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.search.RestaurantSearchService;
import com.mravel.catalog.search.dto.RestaurantSearchResult;
import com.mravel.common.i18n.LocaleConstants;
import com.mravel.common.i18n.LocaleContext;
import com.mravel.common.i18n.LocaleUtil;

import lombok.RequiredArgsConstructor;

@Service
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "mongo", matchIfMissing = true)
@RequiredArgsConstructor
public class MongoRestaurantSearchService implements RestaurantSearchService {

    private final MongoTemplate mongo;

    @Override
    public Page<RestaurantSearchResult> search(RestaurantSearchRequest request, Pageable pageable) {
        Query q = buildQuery(request).with(pageable);
        applyProjection(q);

        long total = mongo.count(Query.of(q).limit(-1).skip(-1), RestaurantDoc.class);
        List<RestaurantSearchResult> data = mongo.find(q, RestaurantDoc.class).stream()
                .map(MongoRestaurantSearchService::toSearchResult)
                .toList();
        return new PageImpl<>(data, pageable, total);
    }

    private static RestaurantSearchResult toSearchResult(RestaurantDoc r) {
        String locale = LocaleContext.get();

        List<RestaurantSearchResult.CuisineRef> cuisines = r.getCuisines() == null ? List.of()
                : r.getCuisines().stream().filter(Objects::nonNull)
                        .map(c -> new RestaurantSearchResult.CuisineRef(c.getCode(),
                                LocaleUtil.pick(c.getName(), locale)))
                        .toList();

        List<RestaurantSearchResult.AmbienceRef> ambience = r.getAmbience() == null ? List.of()
                : r.getAmbience().stream().filter(Objects::nonNull)
                        .map(a -> new RestaurantSearchResult.AmbienceRef(a.getCode(),
                                LocaleUtil.pick(a.getLabel(), locale)))
                        .toList();

        RestaurantSearchResult.CapacityRef capacity = r.getCapacity() == null ? null
                : new RestaurantSearchResult.CapacityRef(r.getCapacity().getTotalCapacity());

        List<RestaurantSearchResult.ImageRef> images = r.getImages() == null ? List.of()
                : r.getImages().stream().filter(Objects::nonNull)
                        .map(img -> new RestaurantSearchResult.ImageRef(
                                img.getUrl(),
                                LocaleUtil.pick(img.getCaption(), locale),
                                img.getCover(),
                                img.getSortOrder()))
                        .toList();

        List<RestaurantSearchResult.OpeningHourRef> openingHours = r.getOpeningHours() == null ? List.of()
                : r.getOpeningHours().stream().filter(Objects::nonNull)
                        .map(oh -> new RestaurantSearchResult.OpeningHourRef(
                                oh.getDayOfWeek(), oh.getOpenTime(), oh.getCloseTime(), oh.getOpen24h(), oh.getClosed()))
                        .toList();

        return new RestaurantSearchResult(
                r.getId(),
                LocaleUtil.pick(r.getName(), locale),
                r.getSlug(),
                r.getActive(),
                r.getRestaurantType() == null ? null : r.getRestaurantType().name(),
                r.getDestinationSlug(),
                LocaleUtil.pick(r.getCityName(), locale),
                LocaleUtil.pick(r.getDistrictName(), locale),
                LocaleUtil.pick(r.getWardName(), locale),
                LocaleUtil.pick(r.getAddressLine(), locale),
                r.getLocation(),
                cuisines, ambience, capacity,
                r.getAvgRating(), r.getReviewsCount(),
                LocaleUtil.pick(r.getRatingLabel(), locale),
                r.getMinPricePerPerson(), r.getMaxPricePerPerson(), r.getCurrencyCode(),
                r.getPriceLevel() == null ? null : r.getPriceLevel().name(),
                r.getPriceBucket() == null ? null : r.getPriceBucket().name(),
                images, openingHours);
    }

    private Query buildQuery(RestaurantSearchRequest r) {
        List<Criteria> cs = new ArrayList<>();
        cs.add(where("active").is(true));
        cs.add(where("moderation.status").is(RestaurantDoc.RestaurantStatus.APPROVED));

        if (r == null) {
            return new Query().addCriteria(and(cs));
        }

        if (r.location() != null && !r.location().isBlank()) {
            String loc = r.location().trim();
            cs.add(new Criteria().orOperator(
                    where("destinationSlug").is(loc),
                    where("parentPlaceSlug").is(loc),
                    where("cityName." + LocaleConstants.VI).regex(loc, "i"),
                    where("cityName." + LocaleConstants.EN).regex(loc, "i"),
                    where("districtName." + LocaleConstants.VI).regex(loc, "i"),
                    where("districtName." + LocaleConstants.EN).regex(loc, "i"),
                    where("addressLine." + LocaleConstants.VI).regex(loc, "i"),
                    where("addressLine." + LocaleConstants.EN).regex(loc, "i")
            ));
        }

        if (r.people() != null && r.people() > 0) {
            cs.add(new Criteria().orOperator(
                    where("capacity.maxGroupSize").gte(r.people()),
                    where("capacity.totalCapacity").gte(r.people())
            ));
        }

        if (r.cuisineSlugs() != null && !r.cuisineSlugs().isEmpty()) {
            cs.add(where("cuisines.code").in(r.cuisineSlugs()));
        }

        if (r.priceLevel() != null && !r.priceLevel().isBlank()) {
            cs.add(where("priceLevel").is(r.priceLevel().trim().toUpperCase()));
        }

        if (r.minRating() != null) cs.add(where("avgRating").gte(r.minRating()));

        if (r.amenityCodes() != null && !r.amenityCodes().isEmpty()) {
            cs.add(where("amenityCodes").all(r.amenityCodes()));
        }

        return new Query().addCriteria(and(cs));
    }

    private static void applyProjection(Query q) {
        q.fields()
                .include("name").include("slug").include("active")
                .include("restaurantType")
                .include("destinationSlug").include("cityName").include("districtName")
                .include("wardName").include("addressLine").include("location")
                .include("cuisines.name").include("cuisines.code")
                .include("ambience.label").include("ambience.code")
                .include("capacity.totalCapacity").include("capacity.maxGroupSize")
                .include("avgRating").include("reviewsCount").include("ratingLabel")
                .include("minPricePerPerson").include("maxPricePerPerson")
                .include("currencyCode").include("priceLevel").include("priceBucket")
                .include("images")
                .include("openingHours");
    }

    private static Criteria and(List<Criteria> cs) {
        return new Criteria().andOperator(cs.toArray(Criteria[]::new));
    }
}
