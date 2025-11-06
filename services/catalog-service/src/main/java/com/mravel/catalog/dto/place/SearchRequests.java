package com.mravel.catalog.dto.place;

import java.util.Set;

public class SearchRequests {

    // Dịch vụ → Khách sạn (4 field)
    public record HotelSearchRequest(
            String location,
            String checkIn,
            String checkOut,
            Integer adults,
            Integer rooms
    ) {}

    // Dịch vụ → Quán ăn (2 field)
    public record RestaurantSearchRequest(
            String location,
            Set<String> cuisineSlugs
    ) {}

    // Địa điểm (1 field)
    public record PoiSearchRequest(
            String q
    ) {}
}