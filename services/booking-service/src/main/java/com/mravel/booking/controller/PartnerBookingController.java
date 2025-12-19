package com.mravel.booking.controller;

import com.mravel.booking.client.AuthValidateClient;
import com.mravel.booking.client.CatalogPartnerIdsClient;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import com.mravel.booking.model.RestaurantBooking;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;
import com.mravel.booking.service.HotelBookingMapper;
import com.mravel.booking.service.RestaurantBookingMapper;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api/booking/partners")
@RequiredArgsConstructor
public class PartnerBookingController {

    private final AuthValidateClient authValidateClient;
    private final CatalogPartnerIdsClient catalogPartnerIdsClient;
    private final HotelBookingRepository hotelRepo;
    private final RestaurantBookingRepository restaurantRepo;

    private static long nn(Long v) { return v == null ? 0L : v; }
    public record CancelReq(String reason) {}

    // ----------------- LIST / DETAIL -----------------
    @GetMapping("/hotels")
    public ResponseEntity<ApiResponse<?>> listHotelBookings(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);
        List<String> hotelIds = catalogPartnerIdsClient.listMyHotelIds(partnerId, bearer);

        List<HotelBooking> items = filterHotel(hotelIds, status);

        // ✅ map -> summary DTO để khỏi đụng LAZY rooms
        var dtoItems = items.stream()
                .map(HotelBookingMapper::toSummary)
                .toList();

        return ResponseEntity.ok(ApiResponse.success("OK", toPage(dtoItems, page, size)));
    }

    @GetMapping("/hotels/{code}")
    public ResponseEntity<ApiResponse<?>> hotelDetail(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String code
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);
        List<String> hotelIds = catalogPartnerIdsClient.listMyHotelIds(partnerId, bearer);

        HotelBooking b = hotelRepo.findWithRoomsByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        if (!hotelIds.contains(b.getHotelId())) throw new SecurityException("Not owner");

        return ResponseEntity.ok(ApiResponse.success("OK", HotelBookingMapper.toDetail(b)));
    }

    // ----------------- CANCEL (FIX #3 ở đây) -----------------

    @PostMapping("/hotels/{code}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelHotel(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String code,
            @RequestBody(required = false) CancelReq body
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);
        List<String> hotelIds = catalogPartnerIdsClient.listMyHotelIds(partnerId, bearer);

        HotelBooking b = hotelRepo.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        if (!hotelIds.contains(b.getHotelId())) throw new SecurityException("Not owner");

        if (b.getStatus() == BookingBase.BookingStatus.COMPLETED
                || b.getStatus() == BookingBase.BookingStatus.CANCELLED
                || b.getStatus() == BookingBase.BookingStatus.CANCELLED_BY_PARTNER) {
            throw new IllegalStateException("Cannot cancel");
        }

        b.setStatus(BookingBase.BookingStatus.CANCELLED_BY_PARTNER);

        // --- FIX #3: set reason + cancelledAt (nếu entity có field/setter) ---
        String reason = (body == null) ? null : body.reason();
        try {
            b.setCancelReason(reason);
        } catch (Exception ignore) {}
        try {
            b.setCancelledAt(Instant.now());
        } catch (Exception ignore) {}

        b.setUpdatedAt(Instant.now());
        hotelRepo.save(b);

        return ResponseEntity.ok(ApiResponse.success("OK", HotelBookingMapper.toSummary(b)));
    }

    @GetMapping("/restaurants")
    public ResponseEntity<ApiResponse<?>> listRestaurantBookings(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);
        List<String> ids = catalogPartnerIdsClient.listMyRestaurantIds(partnerId, bearer);

        List<RestaurantBooking> items = filterRestaurant(ids, status);

        var dtoItems = items.stream()
                .map(RestaurantBookingMapper::toSummary)
                .toList();

        return ResponseEntity.ok(ApiResponse.success("OK", toPage(dtoItems, page, size)));
    }

    @GetMapping("/restaurants/{code}")
    public ResponseEntity<ApiResponse<?>> restaurantDetail(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String code
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);
        List<String> ids = catalogPartnerIdsClient.listMyRestaurantIds(partnerId, bearer);

        RestaurantBooking b = restaurantRepo.findWithTablesByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        if (!ids.contains(b.getRestaurantId())) throw new SecurityException("Not owner");

        return ResponseEntity.ok(ApiResponse.success("OK", RestaurantBookingMapper.toDetail(b)));
    }

    @PostMapping("/restaurants/{code}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelRestaurant(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String code,
            @RequestBody(required = false) CancelReq body
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);
        List<String> ids = catalogPartnerIdsClient.listMyRestaurantIds(partnerId, bearer);

        RestaurantBooking b = restaurantRepo.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        if (!ids.contains(b.getRestaurantId())) throw new SecurityException("Not owner");

        if (b.getStatus() == BookingBase.BookingStatus.COMPLETED
                || b.getStatus() == BookingBase.BookingStatus.CANCELLED
                || b.getStatus() == BookingBase.BookingStatus.CANCELLED_BY_PARTNER) {
            throw new IllegalStateException("Cannot cancel");
        }

        b.setStatus(BookingBase.BookingStatus.CANCELLED_BY_PARTNER);

        // --- FIX #3: set reason + cancelledAt (nếu entity có field/setter) ---
        String reason = (body == null) ? null : body.reason();
        try {
            b.setCancelReason(reason);
        } catch (Exception ignore) {}
        try {
            b.setCancelledAt(Instant.now());
        } catch (Exception ignore) {}

        b.setUpdatedAt(Instant.now());
        restaurantRepo.save(b);

        return ResponseEntity.ok(ApiResponse.success("OK", RestaurantBookingMapper.toSummary(b)));
    }

    // ----------------- STATS (FIX #2 ở đây) -----------------

    @GetMapping("/stats/status")
    public ResponseEntity<ApiResponse<?>> statsByStatus(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);

        List<String> hotelIds = catalogPartnerIdsClient.listMyHotelIds(partnerId, bearer);
        List<String> restaurantIds = catalogPartnerIdsClient.listMyRestaurantIds(partnerId, bearer);

        ZoneId zone = ZoneId.systemDefault();

        Map<String, Long> counts = new LinkedHashMap<>();
        filterHotel(hotelIds, null).stream()
                .filter(b -> inRange(b.getCreatedAt(), from, to, zone))
                .forEach(b -> counts.merge("HOTEL_" + b.getStatus().name(), 1L, (a, x) -> nn(a) + nn(x)));

        filterRestaurant(restaurantIds, null).stream()
                .filter(b -> inRange(b.getCreatedAt(), from, to, zone))
                .forEach(b -> counts.merge("RESTAURANT_" + b.getStatus().name(), 1L, (a, x) -> nn(a) + nn(x)));

        return ResponseEntity.ok(ApiResponse.success("OK", counts));
    }

    public record RevenuePoint(String label, BigDecimal hotelRevenue, BigDecimal restaurantRevenue) {}

    @GetMapping("/stats/revenue")
    public ResponseEntity<ApiResponse<?>> statsRevenue(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to,
            @RequestParam(required = false) String group // today|weekly|monthly|yearly
    ) {
        Long partnerId = authValidateClient.requirePartnerId(bearer);

        List<String> hotelIds = catalogPartnerIdsClient.listMyHotelIds(partnerId, bearer);
        List<String> restaurantIds = catalogPartnerIdsClient.listMyRestaurantIds(partnerId, bearer);

        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");

        // Backward compatible: nếu không truyền group -> trả như cũ (HOTEL/RESTAURANT/TOTAL)
        if (group == null || group.isBlank()) {
            BigDecimal hotelRevenue = sumPaidHotel(hotelIds, from, to, zone);
            BigDecimal restaurantRevenue = sumPaidRestaurant(restaurantIds, from, to, zone);

            Map<String, BigDecimal> res = Map.of(
                    "HOTEL", hotelRevenue,
                    "RESTAURANT", restaurantRevenue,
                    "TOTAL", hotelRevenue.add(restaurantRevenue)
            );
            return ResponseEntity.ok(ApiResponse.success("OK", res));
        }

        String g = group.trim().toLowerCase();
        List<RevenuePoint> series = buildRevenueSeries(g, hotelIds, restaurantIds, from, to, zone);

        return ResponseEntity.ok(ApiResponse.success("OK", series));
    }

    private List<RevenuePoint> buildRevenueSeries(
            String group,
            List<String> hotelIds,
            List<String> restaurantIds,
            LocalDate from,
            LocalDate to,
            ZoneId zone
    ) {
        // chuẩn hoá range (nếu null)
        LocalDate end = (to != null) ? to : LocalDate.now(zone);
        LocalDate start = (from != null) ? from : end;

        // tạo buckets theo group
        LinkedHashMap<String, BigDecimal> hotel = new LinkedHashMap<>();
        LinkedHashMap<String, BigDecimal> restaurant = new LinkedHashMap<>();

        if ("weekly".equals(group)) {
            // luôn tạo đủ 7 ngày T2..CN cho đẹp
            List<String> labels = List.of("T2","T3","T4","T5","T6","T7","CN");
            labels.forEach(lb -> { hotel.put(lb, BigDecimal.ZERO); restaurant.put(lb, BigDecimal.ZERO); });

            for (HotelBooking b : filterHotel(hotelIds, null)) {
                if (!isPaidSuccess(b)) continue;
                LocalDate d = toLocalDateSafe(b, zone);
                if (d == null || d.isBefore(start) || d.isAfter(end)) continue;
                String lb = dowLabel(d);
                hotel.put(lb, hotel.get(lb).add(nz(b.getAmountPaid())));
            }

            for (RestaurantBooking b : filterRestaurant(restaurantIds, null)) {
                if (!isPaidSuccess(b)) continue;
                LocalDate d = toLocalDateSafe(b, zone);
                if (d == null || d.isBefore(start) || d.isAfter(end)) continue;
                String lb = dowLabel(d);
                restaurant.put(lb, restaurant.get(lb).add(nz(b.getAmountPaid())));
            }

            return labels.stream()
                    .map(lb -> new RevenuePoint(lb, hotel.get(lb), restaurant.get(lb)))
                    .toList();
        }

        if ("monthly".equals(group)) {
            List<String> labels = List.of("Tuần 1","Tuần 2","Tuần 3","Tuần 4");
            labels.forEach(lb -> { hotel.put(lb, BigDecimal.ZERO); restaurant.put(lb, BigDecimal.ZERO); });

            for (HotelBooking b : filterHotel(hotelIds, null)) {
                if (!isPaidSuccess(b)) continue;
                LocalDate d = toLocalDateSafe(b, zone);
                if (d == null || d.isBefore(start) || d.isAfter(end)) continue;
                int wk = ((d.getDayOfMonth() - 1) / 7) + 1; // 1..5
                wk = Math.min(4, Math.max(1, wk));          // ép về 1..4
                String lb = "Tuần " + wk;
                hotel.put(lb, hotel.get(lb).add(nz(b.getAmountPaid())));
            }

            for (RestaurantBooking b : filterRestaurant(restaurantIds, null)) {
                if (!isPaidSuccess(b)) continue;
                LocalDate d = toLocalDateSafe(b, zone);
                if (d == null || d.isBefore(start) || d.isAfter(end)) continue;
                int wk = ((d.getDayOfMonth() - 1) / 7) + 1;
                wk = Math.min(4, Math.max(1, wk));
                String lb = "Tuần " + wk;
                restaurant.put(lb, restaurant.get(lb).add(nz(b.getAmountPaid())));
            }

            return labels.stream()
                    .map(lb -> new RevenuePoint(lb, hotel.get(lb), restaurant.get(lb)))
                    .toList();
        }

        if ("yearly".equals(group)) {
            List<String> labels = new java.util.ArrayList<>();
            for (int m = 1; m <= 12; m++) labels.add("Th" + m);
            labels.forEach(lb -> { hotel.put(lb, BigDecimal.ZERO); restaurant.put(lb, BigDecimal.ZERO); });

            for (HotelBooking b : filterHotel(hotelIds, null)) {
                if (!isPaidSuccess(b)) continue;
                LocalDate d = toLocalDateSafe(b, zone);
                if (d == null || d.isBefore(start) || d.isAfter(end)) continue;
                String lb = "Th" + d.getMonthValue();
                hotel.put(lb, hotel.get(lb).add(nz(b.getAmountPaid())));
            }

            for (RestaurantBooking b : filterRestaurant(restaurantIds, null)) {
                if (!isPaidSuccess(b)) continue;
                LocalDate d = toLocalDateSafe(b, zone);
                if (d == null || d.isBefore(start) || d.isAfter(end)) continue;
                String lb = "Th" + d.getMonthValue();
                restaurant.put(lb, restaurant.get(lb).add(nz(b.getAmountPaid())));
            }

            return labels.stream()
                    .map(lb -> new RevenuePoint(lb, hotel.get(lb), restaurant.get(lb)))
                    .toList();
        }

        // today (hoặc group khác): trả 1 điểm tổng
        BigDecimal h = sumPaidHotel(hotelIds, start, end, zone);
        BigDecimal r = sumPaidRestaurant(restaurantIds, start, end, zone);
        return List.of(new RevenuePoint("Hôm nay", h, r));
    }

    private boolean isPaidSuccess(BookingBase b) {
        return b != null && b.getPaymentStatus() == BookingBase.PaymentStatus.SUCCESS;
    }

    private BigDecimal nz(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    private LocalDate toLocalDateSafe(BookingBase b, ZoneId zone) {
        if (b == null) return null;
        Instant t = b.getCreatedAt(); // nếu có paidAt thì dùng paidAt sẽ hợp lý hơn
        if (t == null) return null;
        return t.atZone(zone).toLocalDate();
    }

    private String dowLabel(LocalDate d) {
        // Mon=1..Sun=7 => T2..CN
        return switch (d.getDayOfWeek()) {
            case MONDAY -> "T2";
            case TUESDAY -> "T3";
            case WEDNESDAY -> "T4";
            case THURSDAY -> "T5";
            case FRIDAY -> "T6";
            case SATURDAY -> "T7";
            case SUNDAY -> "CN";
        };
    }

    // ----------------- helpers -----------------

    private static boolean inRange(Instant createdAt, LocalDate from, LocalDate to, ZoneId zone) {
        if (createdAt == null) return false;
        LocalDate d = createdAt.atZone(zone).toLocalDate();
        if (from != null && d.isBefore(from)) return false;
        if (to != null && d.isAfter(to)) return false;
        return true;
    }

    private List<HotelBooking> filterHotel(List<String> hotelIds, String status) {
        if (hotelIds == null || hotelIds.isEmpty()) return List.of();
        if (status == null || status.isBlank()) {
            return hotelRepo.findByHotelIdInOrderByCreatedAtDesc(hotelIds);
        }
        BookingBase.BookingStatus st = BookingBase.BookingStatus.valueOf(status);
        return hotelRepo.findByHotelIdInAndStatusOrderByCreatedAtDesc(hotelIds, st);
    }

    private List<RestaurantBooking> filterRestaurant(List<String> ids, String status) {
        if (ids == null || ids.isEmpty()) return List.of();
        if (status == null || status.isBlank()) {
            return restaurantRepo.findByRestaurantIdInOrderByCreatedAtDesc(ids);
        }
        BookingBase.BookingStatus st = BookingBase.BookingStatus.valueOf(status);
        return restaurantRepo.findByRestaurantIdInAndStatusOrderByCreatedAtDesc(ids, st);
    }

    private BigDecimal sumPaidHotel(List<String> hotelIds, LocalDate from, LocalDate to, ZoneId zone) {
        return filterHotel(hotelIds, null).stream()
                .filter(b -> inRange(b.getCreatedAt(), from, to, zone))
                .filter(b -> b.getPaymentStatus() == BookingBase.PaymentStatus.SUCCESS)
                .map(b -> b.getAmountPaid() == null ? BigDecimal.ZERO : b.getAmountPaid())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal sumPaidRestaurant(List<String> ids, LocalDate from, LocalDate to, ZoneId zone) {
        return filterRestaurant(ids, null).stream()
                .filter(b -> inRange(b.getCreatedAt(), from, to, zone))
                .filter(b -> b.getPaymentStatus() == BookingBase.PaymentStatus.SUCCESS)
                .map(b -> b.getAmountPaid() == null ? BigDecimal.ZERO : b.getAmountPaid())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static <T> Map<String, Object> toPage(List<T> items, Integer page, Integer size) {
        int p = page == null ? 0 : page;
        int s = size == null ? 20 : size;

        int from = Math.min(p * s, items.size());
        int to = Math.min(from + s, items.size());

        List<T> content = items.subList(from, to);
        return Map.of(
                "content", content,
                "page", p,
                "size", s,
                "totalElements", items.size()
        );
    }
}