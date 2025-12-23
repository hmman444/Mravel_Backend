package com.mravel.catalog.dto;

import java.time.LocalDate;
import java.util.Set;

public class SearchRequests {

    // Dịch vụ → Khách sạn (search)
    public record HotelSearchRequest(
        String location,
        LocalDate checkIn,
        LocalDate checkOut,
        Integer adults,
        Integer children,
        Integer rooms
) {}

    // Dịch vụ → Quán ăn (Restaurant search)
    public record RestaurantSearchRequest(
            String location,
            String visitDate,        // YYYY-MM-DD, optional
            String visitTime,        // HH:mm, optional
            Integer people,          // tổng số khách (người lớn + trẻ em)
            Set<String> cuisineSlugs // filter theo loại ẩm thực
    ) {}

    // Địa điểm (1 field)
    public record PoiSearchRequest(
            String q
    ) {}
}