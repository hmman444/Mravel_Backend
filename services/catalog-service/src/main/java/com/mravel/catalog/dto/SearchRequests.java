package com.mravel.catalog.dto;

import java.util.Set;

public class SearchRequests {

    // Dịch vụ → Khách sạn (search)
    public record HotelSearchRequest(
            String location,   // destination slug hoặc tên TP
            String checkIn,    // YYYY-MM-DD (để sau dùng với booking/inventory)
            String checkOut,   // YYYY-MM-DD
            Integer adults,
            Integer children,  // NEW: số trẻ em
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