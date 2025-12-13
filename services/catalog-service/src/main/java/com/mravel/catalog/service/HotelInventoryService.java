// src/main/java/com/mravel/catalog/service/HotelInventoryService.java
package com.mravel.catalog.service;

import com.mravel.catalog.dto.InventoryDtos.AvailabilityResponse;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RoomInventoryDoc;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.RoomInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelInventoryService {

    private final RoomInventoryRepository inventoryRepo;
    private final HotelDocRepository hotelRepo;

    /**
     * Kiểu dữ liệu yêu cầu check tồn kho – align với SelectedRoom bên booking.
     */
    public record RoomRequest(
            String roomTypeId,
            int quantity
    ) {}

    // =====================================================================
    //                      HELPER: LOAD TOTAL ROOMS CƠ BẢN
    // =====================================================================

    public AvailabilityResponse getAvailability(
        String hotelId,
        String hotelSlug,
        String roomTypeId,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        int requestedRooms
) {
    if (requestedRooms <= 0) requestedRooms = 1;
    if (checkInDate == null || checkOutDate == null) {
        return new AvailabilityResponse(0, requestedRooms, false);
    }

    // chuẩn hoá nights: [checkIn, checkOut)
    LocalDate start = checkInDate;
    LocalDate end = checkOutDate.minusDays(1);
    if (end.isBefore(start)) end = start;

    // resolve hotelId nếu chỉ có slug
    String resolvedHotelId = hotelId;
    if ((resolvedHotelId == null || resolvedHotelId.isBlank())
            && hotelSlug != null && !hotelSlug.isBlank()) {
        resolvedHotelId = hotelRepo.findBySlugAndActiveTrue(hotelSlug)
                .map(HotelDoc::getId)
                .orElse(null);
    }

    if (resolvedHotelId == null || resolvedHotelId.isBlank()) {
        // không biết hotelId thì không fallback totalRooms được
        // (trừ khi bạn muốn query theo slug + tự set totalRooms)
        List<RoomInventoryDoc> nights = inventoryRepo
                .findByHotelSlugAndRoomTypeIdAndDateBetween(hotelSlug, roomTypeId, start, end);

        if (nights == null || nights.isEmpty()) {
            return new AvailabilityResponse(0, requestedRooms, false);
        }

        int minRemaining = nights.stream()
                .mapToInt(RoomInventoryDoc::getRemainingRooms)
                .min().orElse(0);

        return new AvailabilityResponse(minRemaining, requestedRooms, minRemaining >= requestedRooms);
    }

    // base totalRooms từ HotelDoc (fallback khi thiếu inventory doc)
    int baseTotal = loadBaseTotalRooms(resolvedHotelId, List.of(roomTypeId))
            .getOrDefault(roomTypeId, 0);

    List<RoomInventoryDoc> nights = inventoryRepo
            .findByHotelIdAndRoomTypeIdAndDateBetween(resolvedHotelId, roomTypeId, start, end);

    Map<LocalDate, RoomInventoryDoc> byDate = (nights == null ? List.<RoomInventoryDoc>of() : nights)
            .stream().collect(Collectors.toMap(RoomInventoryDoc::getDate, x -> x));

    int minRemaining = Integer.MAX_VALUE;

    LocalDate d = start;
    while (!d.isAfter(end)) {
        RoomInventoryDoc inv = byDate.get(d);

        int total = (inv != null && inv.getTotalRooms() != null) ? inv.getTotalRooms() : baseTotal;
        int booked = (inv != null && inv.getBookedRooms() != null) ? inv.getBookedRooms() : 0;
        int held = (inv != null && inv.getHeldRooms() != null) ? inv.getHeldRooms() : 0;

        int remaining = total - booked - held;
        if (remaining < minRemaining) minRemaining = remaining;

        d = d.plusDays(1);
    }

    if (minRemaining == Integer.MAX_VALUE) minRemaining = 0;

    return new AvailabilityResponse(minRemaining, requestedRooms, minRemaining >= requestedRooms);
}
    /**
     * Load thông tin tổng số ph
     * òng của từng roomType từ HotelDoc.
     * Nếu không tìm thấy roomType hoặc totalRooms null => mặc định 0.
     */
    private Map<String, Integer> loadBaseTotalRooms(
            String hotelId,
            Collection<String> roomTypeIds
    ) {
        HotelDoc hotel = hotelRepo.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel không tồn tại: " + hotelId));

        Map<String, Integer> base = new HashMap<>();
        if (hotel.getRoomTypes() != null) {
            for (HotelDoc.RoomType rt : hotel.getRoomTypes()) {
                if (rt == null || rt.getId() == null) continue;
                if (roomTypeIds.contains(rt.getId())) {
                    int total = rt.getTotalRooms() != null ? rt.getTotalRooms() : 0;
                    base.put(rt.getId(), total);
                }
            }
        }

        // ensure tất cả roomTypeIds đều có key
        for (String id : roomTypeIds) {
            base.putIfAbsent(id, 0);
        }

        return base;
    }

    // =====================================================================
    //                      CHECK AVAILABILITY (KHÔNG TRỪ)
    // =====================================================================

    /**
     * Check inventory cho 1 booking (KHÔNG trừ tồn kho).
     * Nếu thiếu phòng => throw IllegalStateException.
     *
     * Logic:
     *  - Nếu có RoomInventoryDoc.totalRooms != null -> dùng giá trị đó.
     *  - Nếu KHÔNG có doc -> fallback sang RoomType.totalRooms từ HotelDoc.
     */
    @Transactional(readOnly = true)
    public void assertAvailability(
            String hotelId,
            LocalDate checkIn,
            LocalDate checkOut,
            List<RoomRequest> requestedRooms
    ) {
        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Ngày check-in/check-out không hợp lệ");
        }
        if (requestedRooms == null || requestedRooms.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }

        List<String> roomTypeIds = requestedRooms.stream()
                .map(RoomRequest::roomTypeId)
                .distinct()
                .toList();

        // Load base totalRooms từ HotelDoc
        Map<String, Integer> baseTotals = loadBaseTotalRooms(hotelId, roomTypeIds);

        // Load inventories trong khoảng ngày
        List<RoomInventoryDoc> inventories = inventoryRepo
                .findByHotelIdAndRoomTypeIdInAndDateBetween(
                        hotelId,
                        roomTypeIds,
                        checkIn,
                        checkOut.minusDays(1) // mỗi đêm
                );

        Map<String, RoomInventoryDoc> invMap = inventories.stream()
                .collect(Collectors.toMap(
                        inv -> inv.getRoomTypeId() + "|" + inv.getDate(),
                        inv -> inv
                ));

        LocalDate d = checkIn;
        while (d.isBefore(checkOut)) {
            LocalDate date = d;

            for (RoomRequest req : requestedRooms) {
                String roomTypeId = req.roomTypeId();
                String key = roomTypeId + "|" + date;
                RoomInventoryDoc inv = invMap.get(key);

                int baseTotal = baseTotals.getOrDefault(roomTypeId, 0);
                int total = inv != null && inv.getTotalRooms() != null
                        ? inv.getTotalRooms()
                        : baseTotal;
                int booked = inv != null && inv.getBookedRooms() != null ? inv.getBookedRooms() : 0;
                int held = inv != null && inv.getHeldRooms() != null ? inv.getHeldRooms() : 0;
                int remaining = total - booked - held;

                if (remaining < req.quantity()) {
                    throw new IllegalStateException(
                            "Hết phòng cho loại phòng " + roomTypeId +
                                    " vào đêm " + date + ". Còn tối đa: " + remaining
                    );
                }
            }

            d = d.plusDays(1);
        }
    }

    // =====================================================================
    //                    CHECK + TRỪ TỒN KHO (CONFIRM)
    // =====================================================================

    /**
     * Check + trừ tồn kho (dùng khi muốn hold/confirm).
     * Với luận văn, bạn có thể gọi hàm này khi booking chuyển sang CONFIRMED/PAID.
     */
    @Transactional
    public void deductInventoryForBooking(
            String hotelId,
            String hotelSlug,
            LocalDate checkIn,
            LocalDate checkOut,
            List<RoomRequest> requestedRooms
    ) {
        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Ngày check-in/check-out không hợp lệ");
        }
        if (requestedRooms == null || requestedRooms.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }

        List<String> roomTypeIds = requestedRooms.stream()
                .map(RoomRequest::roomTypeId)
                .distinct()
                .toList();

        // build all dates in range [checkIn, checkOut)
        List<LocalDate> dates = new ArrayList<>();
        LocalDate d = checkIn;
        while (d.isBefore(checkOut)) {
            dates.add(d);
            d = d.plusDays(1);
        }

        // Load base totalRooms từ HotelDoc
        Map<String, Integer> baseTotals = loadBaseTotalRooms(hotelId, roomTypeIds);

        List<RoomInventoryDoc> toSave = new ArrayList<>();

        // Với mỗi date & roomType, đảm bảo có doc, rồi trừ tồn kho
        for (LocalDate date : dates) {
            for (RoomRequest req : requestedRooms) {
                String roomTypeId = req.roomTypeId();
                int quantity = req.quantity();
                if (quantity <= 0) continue;

                int baseTotal = baseTotals.getOrDefault(roomTypeId, 0);

                // 1. Tìm doc tồn kho cho (hotelId, roomTypeId, date)
                RoomInventoryDoc inv = inventoryRepo
                        .findByHotelIdAndRoomTypeIdAndDate(hotelId, roomTypeId, date)
                        .orElseGet(() -> RoomInventoryDoc.builder()
                                .hotelId(hotelId)
                                .hotelSlug(hotelSlug)
                                .roomTypeId(roomTypeId)
                                .date(date)
                                .totalRooms(baseTotal)
                                .bookedRooms(0)
                                .build()
                        );

                // Nếu totalRooms chưa set, gán = baseTotal
                if (inv.getTotalRooms() == null) {
                    inv.setTotalRooms(baseTotal);
                }

                int total = inv.getTotalRooms();
                int booked = inv.getBookedRooms() != null ? inv.getBookedRooms() : 0;
                int remaining = total - booked;

                if (remaining < quantity) {
                    throw new IllegalStateException(
                            "Không thể trừ tồn kho. Hết phòng cho loại phòng "
                                    + roomTypeId + " đêm " + date + ". Còn: " + remaining
                    );
                }

                inv.increaseBooked(quantity);
                toSave.add(inv);
            }
        }

        inventoryRepo.saveAll(toSave);
    }

    // =====================================================================
    //                    ROLLBACK TỒN KHO KHI HỦY BOOKING
    // =====================================================================

    /**
     * Trả lại tồn kho khi hủy booking (chỉ gọi nếu bạn đã từng deduct).
     * Không check “đủ hay không đủ”, chỉ giảm bookedRooms nhưng không âm.
     */
    @Transactional
    public void rollbackInventoryForBooking(
            String hotelId,
            LocalDate checkIn,
            LocalDate checkOut,
            List<RoomRequest> requestedRooms
    ) {
        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Ngày check-in/check-out không hợp lệ");
        }
        if (requestedRooms == null || requestedRooms.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }

        List<String> roomTypeIds = requestedRooms.stream()
                .map(RoomRequest::roomTypeId)
                .distinct()
                .toList();

        List<LocalDate> dates = new ArrayList<>();
        LocalDate d = checkIn;
        while (d.isBefore(checkOut)) {
            dates.add(d);
            d = d.plusDays(1);
        }

        List<RoomInventoryDoc> exist = inventoryRepo
                .findByHotelIdAndRoomTypeIdInAndDateBetween(
                        hotelId, roomTypeIds,
                        checkIn, checkOut.minusDays(1)
                );

        Map<String, RoomInventoryDoc> invMap = exist.stream()
                .collect(Collectors.toMap(
                        inv -> inv.getRoomTypeId() + "|" + inv.getDate(),
                        inv -> inv
                ));

        List<RoomInventoryDoc> toSave = new ArrayList<>();

        for (LocalDate date : dates) {
            for (RoomRequest req : requestedRooms) {
                String key = req.roomTypeId() + "|" + date;
                RoomInventoryDoc inv = invMap.get(key);
                if (inv == null) continue; // chưa từng deduct => bỏ qua
                inv.decreaseBooked(req.quantity());
                toSave.add(inv);
            }
        }

        if (!toSave.isEmpty()) {
            inventoryRepo.saveAll(toSave);
        }
    }

    // =====================================================================
    //                    HOLD TỒN KHO (PENDING PAYMENT)
    // =====================================================================

    @Transactional
    public void holdInventoryForPendingBooking(
            String hotelId,
            String hotelSlug,
            LocalDate checkIn,
            LocalDate checkOut,
            List<RoomRequest> requestedRooms
    ) {
        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Ngày check-in/check-out không hợp lệ");
        }
        if (requestedRooms == null || requestedRooms.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }

        List<String> roomTypeIds = requestedRooms.stream()
                .map(RoomRequest::roomTypeId)
                .distinct()
                .toList();

        // build all dates in range [checkIn, checkOut)
        List<LocalDate> dates = new ArrayList<>();
        LocalDate d = checkIn;
        while (d.isBefore(checkOut)) {
            dates.add(d);
            d = d.plusDays(1);
        }

        Map<String, Integer> baseTotals = loadBaseTotalRooms(hotelId, roomTypeIds);

        List<RoomInventoryDoc> toSave = new ArrayList<>();

        for (LocalDate date : dates) {
            for (RoomRequest req : requestedRooms) {
                String roomTypeId = req.roomTypeId();
                int quantity = req.quantity();
                if (quantity <= 0) continue;

                int baseTotal = baseTotals.getOrDefault(roomTypeId, 0);

                RoomInventoryDoc inv = inventoryRepo
                        .findByHotelIdAndRoomTypeIdAndDate(hotelId, roomTypeId, date)
                        .orElseGet(() -> RoomInventoryDoc.builder()
                                .hotelId(hotelId)
                                .hotelSlug(hotelSlug)
                                .roomTypeId(roomTypeId)
                                .date(date)
                                .totalRooms(baseTotal)
                                .bookedRooms(0)
                                .heldRooms(0)
                                .build()
                        );

                if (inv.getTotalRooms() == null) inv.setTotalRooms(baseTotal);
                if (inv.getBookedRooms() == null) inv.setBookedRooms(0);
                if (inv.getHeldRooms() == null) inv.setHeldRooms(0);

                int total = inv.getTotalRooms();
                int booked = inv.getBookedRooms();
                int held = inv.getHeldRooms();
                int remaining = total - booked - held;

                if (remaining < quantity) {
                    throw new IllegalStateException(
                            "Không thể HOLD. Hết phòng loại " + roomTypeId +
                                    " đêm " + date + ". Còn: " + remaining
                    );
                }

                inv.increaseHeld(quantity);
                toSave.add(inv);
            }
        }

        inventoryRepo.saveAll(toSave);
    }

    // =====================================================================
    //                 COMMIT SAU KHI THANH TOÁN (HELD -> BOOKED)
    // =====================================================================

    @Transactional
    public void commitInventoryAfterPaid(
            String hotelId,
            String hotelSlug,
            LocalDate checkIn,
            LocalDate checkOut,
            List<RoomRequest> requestedRooms
    ) {
        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Ngày check-in/check-out không hợp lệ");
        }
        if (requestedRooms == null || requestedRooms.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }

        // build all dates in range [checkIn, checkOut)
        List<LocalDate> dates = new ArrayList<>();
        LocalDate d = checkIn;
        while (d.isBefore(checkOut)) {
            dates.add(d);
            d = d.plusDays(1);
        }

        List<RoomInventoryDoc> toSave = new ArrayList<>();

        for (LocalDate date : dates) {
            for (RoomRequest req : requestedRooms) {
                int qty = req.quantity();
                if (qty <= 0) continue;

                RoomInventoryDoc inv = inventoryRepo
                        .findByHotelIdAndRoomTypeIdAndDate(hotelId, req.roomTypeId(), date)
                        .orElseThrow(() -> new IllegalStateException(
                                "Không tìm thấy inventory để COMMIT: "
                                        + req.roomTypeId() + "|" + date
                                        + " (hotelId=" + hotelId + ")"
                        ));

                if (inv.getHeldRooms() == null) inv.setHeldRooms(0);
                if (inv.getBookedRooms() == null) inv.setBookedRooms(0);

                inv.commitHeldToBooked(qty);
                toSave.add(inv);
            }
        }

        inventoryRepo.saveAll(toSave);
    }

    // =====================================================================
    //                RELEASE HOLD KHI CANCEL PENDING / AUTO CANCEL
    // =====================================================================

    @Transactional
    public void releaseHoldForCancelledBooking(
            String hotelId,
            LocalDate checkIn,
            LocalDate checkOut,
            List<RoomRequest> requestedRooms
    ) {
        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Ngày check-in/check-out không hợp lệ");
        }
        if (requestedRooms == null || requestedRooms.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }

        List<LocalDate> dates = new ArrayList<>();
        LocalDate d = checkIn;
        while (d.isBefore(checkOut)) {
            dates.add(d);
            d = d.plusDays(1);
        }

        List<RoomInventoryDoc> toSave = new ArrayList<>();

        for (LocalDate date : dates) {
            for (RoomRequest req : requestedRooms) {
                int qty = req.quantity();
                if (qty <= 0) continue;

                RoomInventoryDoc inv = inventoryRepo
                        .findByHotelIdAndRoomTypeIdAndDate(hotelId, req.roomTypeId(), date)
                        .orElse(null);

                if (inv == null) continue;

                inv.decreaseHeld(qty);
                toSave.add(inv);
            }
        }

        if (!toSave.isEmpty()) {
            inventoryRepo.saveAll(toSave);
        }
    }
}