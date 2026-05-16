package com.mravel.catalog.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class SearchRequests {

    public record HotelSearchRequest(
            String location,
            Boolean destOnly,
            LocalDate checkIn,
            LocalDate checkOut,
            Integer adults,
            Integer children,
            Integer rooms,
            Integer minPrice,
            Integer maxPrice,
            Double minRating,
            List<Integer> starRating,
            String hotelType,
            Set<String> amenityCodes) {
        public Integer calcMinGuestsPerRoom() {
            int a = adults != null ? adults : 0;
            int c = children != null ? children : 0;
            int r = rooms != null && rooms > 0 ? rooms : 1;
            int total = a + (c <= 0 ? 0 : (int) Math.ceil(c / 2.0));
            if (total <= 0)
                return null;
            return (int) Math.ceil((double) total / r);
        }
    }

    public record RestaurantSearchRequest(
            String location,
            Boolean destOnly,
            String visitDate,
            String visitTime,
            Integer people,
            Set<String> cuisineSlugs,
            String priceLevel,
            Double minRating,
            Set<String> amenityCodes) {
    }

    public record PlaceSearchRequest(
            String q) {
    }
}