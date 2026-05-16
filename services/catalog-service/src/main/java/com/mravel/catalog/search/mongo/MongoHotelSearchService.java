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

import com.mravel.catalog.dto.SearchRequests.HotelSearchRequest;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.search.HotelSearchService;
import com.mravel.catalog.search.dto.HotelSearchResult;

import lombok.RequiredArgsConstructor;

@Service
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "mongo", matchIfMissing = true)
@RequiredArgsConstructor
public class MongoHotelSearchService implements HotelSearchService {

    private final MongoTemplate mongo;

    @Override
    public Page<HotelSearchResult> search(HotelSearchRequest request, Pageable pageable) {
        Query q = buildQuery(request).with(pageable);
        applyProjection(q);

        long total = mongo.count(Query.of(q).limit(-1).skip(-1), HotelDoc.class);
        List<HotelSearchResult> data = mongo.find(q, HotelDoc.class).stream()
                .map(MongoHotelSearchService::toSearchResult)
                .toList();
        return new PageImpl<>(data, pageable, total);
    }

    private static HotelSearchResult toSearchResult(HotelDoc h) {
        HotelSearchResult.GeneralInfoRef generalInfo = h.getGeneralInfo() == null ? null
                : new HotelSearchResult.GeneralInfoRef(
                        h.getGeneralInfo().getMainFacilitiesSummary(),
                        h.getGeneralInfo().getDistanceToCityCenterKm());

        List<HotelSearchResult.ImageRef> images = h.getImages() == null ? List.of()
                : h.getImages().stream()
                        .filter(Objects::nonNull)
                        .map(img -> new HotelSearchResult.ImageRef(img.getUrl(), img.getCaption(), img.getCover(), img.getSortOrder()))
                        .toList();

        List<HotelSearchResult.RoomTypeMini> roomTypes = h.getRoomTypes() == null ? List.of()
                : h.getRoomTypes().stream()
                        .filter(Objects::nonNull)
                        .map(rt -> new HotelSearchResult.RoomTypeMini(rt.getId(), rt.getMaxGuests(), rt.getTotalRooms()))
                        .toList();

        return new HotelSearchResult(
                h.getId(), h.getName(), h.getSlug(), h.getActive(),
                h.getStarRating(), h.getHotelType() == null ? null : h.getHotelType().name(),
                h.getDestinationSlug(), h.getCityName(), h.getDistrictName(), h.getWardName(), h.getAddressLine(),
                h.getLocation(),
                h.getAvgRating(), h.getReviewsCount(), h.getRatingLabel(),
                h.getMinNightlyPrice(), h.getCurrencyCode(), h.getReferenceNightlyPrice(), h.getDiscountPercent(),
                generalInfo, images, roomTypes);
    }

    private Query buildQuery(HotelSearchRequest r) {
        List<Criteria> cs = new ArrayList<>();
        cs.add(where("active").is(true));
        cs.add(where("moderation.status").is(HotelDoc.HotelStatus.APPROVED));

        if (r == null) {
            return new Query().addCriteria(and(cs));
        }

        if (r.location() != null && !r.location().isBlank()) {
            String loc = r.location().trim();
            cs.add(new Criteria().orOperator(
                    where("destinationSlug").is(loc),
                    where("cityName").regex(loc, "i"),
                    where("districtName").regex(loc, "i"),
                    where("addressLine").regex(loc, "i")
            ));
        }

        Integer minGuestsPerRoom = r.calcMinGuestsPerRoom();
        Integer requiredRooms = r.rooms() != null && r.rooms() > 0 ? r.rooms() : null;
        if (minGuestsPerRoom != null || requiredRooms != null) {
            List<Criteria> roomConds = new ArrayList<>();
            if (minGuestsPerRoom != null) roomConds.add(where("maxGuests").gte(minGuestsPerRoom));
            if (requiredRooms != null) roomConds.add(where("totalRooms").gte(requiredRooms));
            cs.add(where("roomTypes").elemMatch(and(roomConds)));
        }

        if (r.minPrice() != null) cs.add(where("minNightlyPrice").gte(r.minPrice()));
        if (r.maxPrice() != null) cs.add(where("minNightlyPrice").lte(r.maxPrice()));
        if (r.minRating() != null) cs.add(where("avgRating").gte(r.minRating()));
        if (r.starRating() != null && !r.starRating().isEmpty()) cs.add(where("starRating").in(r.starRating()));
        if (r.hotelType() != null && !r.hotelType().isBlank()) cs.add(where("hotelType").is(r.hotelType().trim().toUpperCase()));
        if (r.amenityCodes() != null && !r.amenityCodes().isEmpty()) cs.add(where("amenityCodes").all(r.amenityCodes()));

        return new Query().addCriteria(and(cs));
    }

    private static void applyProjection(Query q) {
        q.fields()
                .include("name").include("slug").include("active")
                .include("starRating").include("hotelType")
                .include("destinationSlug").include("cityName").include("districtName")
                .include("wardName").include("addressLine").include("location")
                .include("avgRating").include("reviewsCount").include("ratingLabel")
                .include("minNightlyPrice").include("currencyCode")
                .include("referenceNightlyPrice").include("discountPercent")
                .include("generalInfo").include("images")
                .include("roomTypes.id").include("roomTypes.maxGuests").include("roomTypes.totalRooms");
    }

    private static Criteria and(List<Criteria> cs) {
        return new Criteria().andOperator(cs.toArray(Criteria[]::new));
    }
}
