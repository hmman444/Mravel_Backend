package com.mravel.catalog.service.admin;

import com.mravel.catalog.dto.admin.AdminDashboardDtos.CatalogSummary;
import com.mravel.catalog.dto.admin.AdminDashboardDtos.PartnerRef;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** Số liệu dịch vụ + resolve partner cho admin dashboard. */
@Service
@RequiredArgsConstructor
public class AdminDashboardCatalogService {

    private final HotelDocRepository hotelRepo;
    private final RestaurantDocRepository restaurantRepo;

    public CatalogSummary summary() {
        long hotelActive = hotelRepo
                .countByActiveTrueAndModeration_StatusAndDeletedAtIsNull(HotelDoc.HotelStatus.APPROVED);
        long hotelPending = hotelRepo
                .countByModeration_StatusAndDeletedAtIsNull(HotelDoc.HotelStatus.PENDING_REVIEW);
        long restaurantActive = restaurantRepo
                .countByActiveTrueAndModeration_StatusAndDeletedAtIsNull(RestaurantDoc.RestaurantStatus.APPROVED);
        long restaurantPending = restaurantRepo
                .countByModeration_StatusAndDeletedAtIsNull(RestaurantDoc.RestaurantStatus.PENDING_REVIEW);
        return new CatalogSummary(hotelActive, hotelPending, restaurantActive, restaurantPending);
    }

    public List<PartnerRef> resolvePartners(List<String> hotelIds, List<String> restaurantIds) {
        List<PartnerRef> out = new ArrayList<>();
        if (hotelIds != null && !hotelIds.isEmpty()) {
            for (HotelDoc d : hotelRepo.findAllById(hotelIds)) {
                HotelDoc.PublisherInfo p = d.getPublisher();
                out.add(new PartnerRef(d.getId(), "HOTEL",
                        p == null ? null : p.getPartnerId(),
                        p == null ? null : p.getPartnerName()));
            }
        }
        if (restaurantIds != null && !restaurantIds.isEmpty()) {
            for (RestaurantDoc d : restaurantRepo.findAllById(restaurantIds)) {
                RestaurantDoc.PublisherInfo p = d.getPublisher();
                out.add(new PartnerRef(d.getId(), "RESTAURANT",
                        p == null ? null : p.getPartnerId(),
                        p == null ? null : p.getPartnerName()));
            }
        }
        return out;
    }
}
