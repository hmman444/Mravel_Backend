// src/main/java/com/mravel/catalog/service/HotelService.java
package com.mravel.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.SearchRequests.HotelSearchRequest;
import com.mravel.catalog.dto.hotel.HotelDtos.HotelDetailDTO;
import com.mravel.catalog.dto.hotel.HotelDtos.HotelSummaryDTO;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.repository.HotelDocRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelDocRepository repo;

    public Page<HotelSummaryDTO> searchHotels(HotelSearchRequest request, Pageable pageable) {
        if (request == null) {
            // không có bộ lọc gì, chỉ location = null, capacity = null
            return repo.searchHotels(null, null, null, pageable)
                       .map(HotelMapper::toSummary);
        }

        String location = request.location();
        Integer adults = safeInt(request.adults());
        Integer children = safeInt(request.children());
        Integer rooms = safeInt(request.rooms());

        // ----- Quy đổi trẻ em -----
        // 2 trẻ em = 1 người lớn, dùng ceil(children / 2.0)
        int childEquivalent = (children <= 0) ? 0 : (int) Math.ceil(children / 2.0);
        int guestsNormalized = adults + childEquivalent;

        Integer minGuestsPerRoom = null;
        Integer requiredRooms = (rooms != null && rooms > 0) ? rooms : null;

        if (guestsNormalized > 0 && rooms != null && rooms > 0) {
            // chia đều số khách cho số phòng, làm tròn lên
            minGuestsPerRoom = (int) Math.ceil(guestsNormalized * 1.0 / rooms);
        } else if (guestsNormalized > 0) {
            // không chọn số phòng => giả sử 1 phòng
            minGuestsPerRoom = guestsNormalized;
        }

        // checkIn / checkOut hiện tại CHƯA dùng lọc, để dành cho later (booking-service)

        return repo.searchHotels(location, minGuestsPerRoom, requiredRooms, pageable)
                   .map(HotelMapper::toSummary);
    }

    public HotelDetailDTO getBySlug(String slug) {
        HotelDoc h = repo.findBySlugAndActiveTrue(slug)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        return HotelMapper.toDetail(h);
    }

    private Integer safeInt(Integer i) {
        return i == null ? 0 : i;
    }
}