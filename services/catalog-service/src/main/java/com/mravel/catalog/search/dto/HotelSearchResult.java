package com.mravel.catalog.search.dto;

import java.math.BigDecimal;
import java.util.List;

public record HotelSearchResult(
        String id,
        String name,
        String slug,
        Boolean active,
        Integer starRating,
        String hotelType,
        String destinationSlug,
        String cityName,
        String districtName,
        String wardName,
        String addressLine,
        double[] location,
        Double avgRating,
        Integer reviewsCount,
        String ratingLabel,
        BigDecimal minNightlyPrice,
        String currencyCode,
        BigDecimal referenceNightlyPrice,
        Integer discountPercent,
        GeneralInfoRef generalInfo,
        List<ImageRef> images,
        List<RoomTypeMini> roomTypes
) {
    public record GeneralInfoRef(String mainFacilitiesSummary, Double distanceToCityCenterKm) {}
    public record ImageRef(String url, String caption, Boolean cover, Integer sortOrder) {}
    public record RoomTypeMini(String id, Integer maxGuests, Integer totalRooms) {}
}
