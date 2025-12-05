package com.mravel.catalog.dto;

import java.util.Set;

public class SearchRequests {

    // Dịch vụ → Khách sạn (search)
    public record HotelSearchRequest(
            String location,   // destination slug hoặc tên TP
            String checkIn,    // YYYY-MM-DD (để sau dùng với booking/inventory)
            String checkOut,   // YYYY-MM-DD
            Integer adults,
            Integer children,  // số trẻ em
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