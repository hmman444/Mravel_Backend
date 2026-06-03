package com.mravel.catalog.dto.admin;

import java.util.List;

/** DTO cho admin dashboard: đếm dịch vụ theo trạng thái + resolve serviceId -> partner. */
public class AdminDashboardDtos {

    public record CatalogSummary(
            long hotelActive,
            long hotelPending,
            long restaurantActive,
            long restaurantPending) {
    }

    /** Map 1 service (hotel/restaurant) -> partner sở hữu. */
    public record PartnerRef(String serviceId, String type, String partnerId, String partnerName) {
    }

    public record ResolveRequest(List<String> hotelIds, List<String> restaurantIds) {
    }
}
