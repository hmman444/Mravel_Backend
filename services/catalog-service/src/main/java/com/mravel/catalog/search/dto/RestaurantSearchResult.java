package com.mravel.catalog.search.dto;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record RestaurantSearchResult(
        String id,
        String name,
        String slug,
        Boolean active,
        String restaurantType,
        String destinationSlug,
        String cityName,
        String districtName,
        String wardName,
        String addressLine,
        double[] location,
        List<CuisineRef> cuisines,
        List<AmbienceRef> ambience,
        CapacityRef capacity,
        Double avgRating,
        Integer reviewsCount,
        String ratingLabel,
        BigDecimal minPricePerPerson,
        BigDecimal maxPricePerPerson,
        String currencyCode,
        String priceLevel,
        String priceBucket,
        List<ImageRef> images,
        List<OpeningHourRef> openingHours
) {
    public record CuisineRef(String code, String name) {}
    public record AmbienceRef(String code, String label) {}
    public record CapacityRef(Integer totalCapacity) {}
    public record ImageRef(String url, String caption, Boolean cover, Integer sortOrder) {}
    public record OpeningHourRef(DayOfWeek dayOfWeek, LocalTime openTime, LocalTime closeTime, Boolean open24h, Boolean closed) {}
}
