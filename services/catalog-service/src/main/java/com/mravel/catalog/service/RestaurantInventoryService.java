// src/main/java/com/mravel/catalog/service/RestaurantInventoryService.java
package com.mravel.catalog.service;

import com.mravel.catalog.dto.RestaurantInventoryDtos.*;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.doc.TableInventoryDoc;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.repository.TableInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantInventoryService {

    private final TableInventoryRepository inventoryRepo;
    private final RestaurantDocRepository restaurantRepo;

    // ====== public APIs ======

    public TableAvailabilityResponse getAvailability(
        String restaurantId,
        String restaurantSlug,
        String tableTypeId,
        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer durationMinutes,
        Integer requestedTables
    ) {
        String rid = restaurantId;

        if ((rid == null || rid.isBlank()) && restaurantSlug != null && !restaurantSlug.isBlank()) {
            rid = restaurantRepo.findBySlug(restaurantSlug)
                .map(RestaurantDoc::getId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant không tồn tại (slug): " + restaurantSlug));
        }

        if (rid == null || rid.isBlank()) {
            throw new IllegalArgumentException("Thiếu restaurantId hoặc restaurantSlug");
        }
        
        if (requestedTables == null || requestedTables <= 0) requestedTables = 1;

        RestaurantDoc r = restaurantRepo.findById(rid)
            .orElseThrow(() -> new IllegalArgumentException("Restaurant không tồn tại: " + restaurantId));

        int slotMinutes = getSlotMinutes(r);
        int dur = resolveDurationMinutes(r, tableTypeId, durationMinutes);

        LocalDateTime startSlot = normalizeSlot(reservationDate, reservationTime, slotMinutes);
        List<LocalDateTime> slots = expandSlots(startSlot, dur, slotMinutes);

        int baseTotal = loadBaseTotalTables(r, tableTypeId);

        // fetch all inventory docs in [first, lastExclusive)
        LocalDateTime rangeStart = slots.get(0);
        LocalDateTime rangeEndExclusive = slots.get(slots.size() - 1).plusMinutes(slotMinutes);

        List<TableInventoryDoc> docs = inventoryRepo.findByRestaurantIdAndTableTypeIdInAndSlotStartBetween(
            rid,
            List.of(tableTypeId),
            rangeStart,
            rangeEndExclusive
        );

        Map<LocalDateTime, TableInventoryDoc> bySlot = docs.stream()
            .collect(Collectors.toMap(TableInventoryDoc::getSlotStart, x -> x, (a,b) -> a));

        int minRemaining = Integer.MAX_VALUE;

        for (LocalDateTime s : slots) {
            TableInventoryDoc inv = bySlot.get(s);
            int total = (inv != null && inv.getTotalTables() != null) ? inv.getTotalTables() : baseTotal;
            int booked = (inv != null && inv.getBookedTables() != null) ? inv.getBookedTables() : 0;
            int held = (inv != null && inv.getHeldTables() != null) ? inv.getHeldTables() : 0;
            int remaining = total - booked - held;
            minRemaining = Math.min(minRemaining, remaining);
        }

        if (minRemaining == Integer.MAX_VALUE) minRemaining = 0;
        return new TableAvailabilityResponse(minRemaining, requestedTables, minRemaining >= requestedTables);
    }

    @Transactional(readOnly = true)
    public void assertAvailability(CheckTableInventoryRequest req) {
        Objects.requireNonNull(req, "req null");
        RestaurantDoc r = restaurantRepo.findById(req.restaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurant không tồn tại: " + req.restaurantId()));

        int slotMinutes = getSlotMinutes(r);
        validateLeadTime(r, req.reservationDate(), req.reservationTime());

        List<TableRequestDTO> tables = req.tables() == null ? List.of() : req.tables();
        if (tables.isEmpty()) throw new IllegalArgumentException("Chưa chọn bàn");

        // loop từng tableType request
        for (TableRequestDTO t : tables) {
            if (t.quantity() <= 0) continue;

            int dur = resolveDurationMinutes(r, t.tableTypeId(), req.durationMinutes());
            LocalDateTime startSlot = normalizeSlot(req.reservationDate(), req.reservationTime(), slotMinutes);
            List<LocalDateTime> slots = expandSlots(startSlot, dur, slotMinutes);

            int baseTotal = loadBaseTotalTables(r, t.tableTypeId());

            LocalDateTime rangeStart = slots.get(0);
            LocalDateTime rangeEndExclusive = slots.get(slots.size() - 1).plusMinutes(slotMinutes);

            List<TableInventoryDoc> docs = inventoryRepo.findByRestaurantIdAndTableTypeIdInAndSlotStartBetween(
                req.restaurantId(), List.of(t.tableTypeId()), rangeStart, rangeEndExclusive
            );

            Map<LocalDateTime, TableInventoryDoc> bySlot = docs.stream()
                .collect(Collectors.toMap(TableInventoryDoc::getSlotStart, x -> x, (a,b)->a));

            for (LocalDateTime s : slots) {
                TableInventoryDoc inv = bySlot.get(s);
                int total = (inv != null && inv.getTotalTables() != null) ? inv.getTotalTables() : baseTotal;
                int booked = (inv != null && inv.getBookedTables() != null) ? inv.getBookedTables() : 0;
                int held = (inv != null && inv.getHeldTables() != null) ? inv.getHeldTables() : 0;
                int remaining = total - booked - held;

                if (remaining < t.quantity()) {
                    throw new IllegalStateException(
                        "Hết bàn (" + t.tableTypeId() + ") tại slot " + s + ". Còn: " + remaining
                    );
                }
            }
        }
    }

    @Transactional
    public void holdForPending(HoldTableInventoryRequest req) {
        RestaurantDoc r = restaurantRepo.findById(req.restaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurant không tồn tại: " + req.restaurantId()));

        int slotMinutes = getSlotMinutes(r);
        validateLeadTime(r, req.reservationDate(), req.reservationTime());

        List<TableRequestDTO> tables = req.tables() == null ? List.of() : req.tables();
        if (tables.isEmpty()) throw new IllegalArgumentException("Chưa chọn bàn");

        // chặn spam giữ bàn
        int maxTables = (r.getBookingConfig() != null && r.getBookingConfig().getMaxTablesPerBooking() != null)
            ? r.getBookingConfig().getMaxTablesPerBooking()
            : 5;
        int sumQty = tables.stream().mapToInt(x -> Math.max(0, x.quantity())).sum();
        if (sumQty > maxTables) throw new IllegalArgumentException("Vượt giới hạn số bàn/booking: " + maxTables);

        LocalDateTime baseStartSlot = normalizeSlot(req.reservationDate(), req.reservationTime(), slotMinutes);

        List<TableInventoryDoc> toSave = new ArrayList<>();

        for (TableRequestDTO t : tables) {
            if (t.quantity() <= 0) continue;

            int dur = resolveDurationMinutes(r, t.tableTypeId(), req.durationMinutes());
            List<LocalDateTime> slots = expandSlots(baseStartSlot, dur, slotMinutes);

            int baseTotal = loadBaseTotalTables(r, t.tableTypeId());

            for (LocalDateTime s : slots) {
                TableInventoryDoc inv = inventoryRepo
                    .findByRestaurantIdAndTableTypeIdAndSlotStart(req.restaurantId(), t.tableTypeId(), s)
                    .orElseGet(() -> TableInventoryDoc.builder()
                        .restaurantId(req.restaurantId())
                        .restaurantSlug(req.restaurantSlug())
                        .tableTypeId(t.tableTypeId())
                        .slotStart(s)
                        .totalTables(baseTotal)
                        .bookedTables(0)
                        .heldTables(0)
                        .build()
                    );

                if (inv.getTotalTables() == null) inv.setTotalTables(baseTotal);
                if (inv.getBookedTables() == null) inv.setBookedTables(0);
                if (inv.getHeldTables() == null) inv.setHeldTables(0);

                int remaining = inv.getTotalTables() - inv.getBookedTables() - inv.getHeldTables();
                if (remaining < t.quantity()) {
                    throw new IllegalStateException("Không thể HOLD. Hết bàn " + t.tableTypeId() + " slot " + s);
                }

                inv.increaseHeld(t.quantity());
                toSave.add(inv);
            }
        }

        inventoryRepo.saveAll(toSave);
    }

    @Transactional
    public void commitAfterPaid(HoldTableInventoryRequest req) {
        RestaurantDoc r = restaurantRepo.findById(req.restaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurant không tồn tại: " + req.restaurantId()));

        int slotMinutes = getSlotMinutes(r);
        LocalDateTime startSlot = normalizeSlot(req.reservationDate(), req.reservationTime(), slotMinutes);

        List<TableInventoryDoc> toSave = new ArrayList<>();

        for (TableRequestDTO t : req.tables()) {
            if (t.quantity() <= 0) continue;

            int dur = resolveDurationMinutes(r, t.tableTypeId(), req.durationMinutes());
            List<LocalDateTime> slots = expandSlots(startSlot, dur, slotMinutes);

            for (LocalDateTime s : slots) {
                TableInventoryDoc inv = inventoryRepo
                    .findByRestaurantIdAndTableTypeIdAndSlotStart(req.restaurantId(), t.tableTypeId(), s)
                    .orElseThrow(() -> new IllegalStateException("Không tìm thấy inventory để COMMIT: " + s));

                if (inv.getHeldTables() == null) inv.setHeldTables(0);
                if (inv.getBookedTables() == null) inv.setBookedTables(0);

                inv.commitHeldToBooked(t.quantity());
                toSave.add(inv);
            }
        }

        inventoryRepo.saveAll(toSave);
    }

    @Transactional
    public void releaseHold(ReleaseTableHoldRequest req) {
        RestaurantDoc r = restaurantRepo.findById(req.restaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurant không tồn tại: " + req.restaurantId()));

        int slotMinutes = getSlotMinutes(r);
        LocalDateTime startSlot = normalizeSlot(req.reservationDate(), req.reservationTime(), slotMinutes);

        List<TableInventoryDoc> toSave = new ArrayList<>();

        for (TableRequestDTO t : req.tables()) {
            if (t.quantity() <= 0) continue;

            int dur = resolveDurationMinutes(r, t.tableTypeId(), req.durationMinutes());
            List<LocalDateTime> slots = expandSlots(startSlot, dur, slotMinutes);

            for (LocalDateTime s : slots) {
                var invOpt = inventoryRepo.findByRestaurantIdAndTableTypeIdAndSlotStart(req.restaurantId(), t.tableTypeId(), s);
                if (invOpt.isEmpty()) continue;
                TableInventoryDoc inv = invOpt.get();
                inv.decreaseHeld(t.quantity());
                toSave.add(inv);
            }
        }

        if (!toSave.isEmpty()) inventoryRepo.saveAll(toSave);
    }

    @Transactional
    public void rollbackBooked(ReleaseTableHoldRequest req) {
        RestaurantDoc r = restaurantRepo.findById(req.restaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurant không tồn tại: " + req.restaurantId()));

        int slotMinutes = getSlotMinutes(r);
        LocalDateTime startSlot = normalizeSlot(req.reservationDate(), req.reservationTime(), slotMinutes);

        List<TableInventoryDoc> toSave = new ArrayList<>();

        for (TableRequestDTO t : req.tables()) {
            if (t.quantity() <= 0) continue;

            int dur = resolveDurationMinutes(r, t.tableTypeId(), req.durationMinutes());
            List<LocalDateTime> slots = expandSlots(startSlot, dur, slotMinutes);

            for (LocalDateTime s : slots) {
                var invOpt = inventoryRepo.findByRestaurantIdAndTableTypeIdAndSlotStart(req.restaurantId(), t.tableTypeId(), s);
                if (invOpt.isEmpty()) continue;
                TableInventoryDoc inv = invOpt.get();
                inv.decreaseBooked(t.quantity());
                toSave.add(inv);
            }
        }

        if (!toSave.isEmpty()) inventoryRepo.saveAll(toSave);
    }

    // ====== helpers ======

    private int getSlotMinutes(RestaurantDoc r) {
        Integer slot = (r.getBookingConfig() != null) ? r.getBookingConfig().getSlotMinutes() : null;
        return (slot != null && slot > 0) ? slot : 30;
    }

    private int resolveDurationMinutes(RestaurantDoc r, String tableTypeId, Integer requested) {
        // requested -> tableType default/allowed -> restaurant default
        Integer dur = requested;

        RestaurantDoc.TableType tt = findTableType(r, tableTypeId);

        if (dur == null || dur <= 0) {
            if (tt != null && tt.getDefaultDurationMinutes() != null) dur = tt.getDefaultDurationMinutes();
        }

        if (dur == null || dur <= 0) {
            dur = (r.getBookingConfig() != null && r.getBookingConfig().getDefaultDurationMinutes() != null)
                ? r.getBookingConfig().getDefaultDurationMinutes()
                : 90;
        }

        // validate allowed durations
        List<Integer> allowed = (tt != null && tt.getAllowedDurationsMinutes() != null && !tt.getAllowedDurationsMinutes().isEmpty())
            ? tt.getAllowedDurationsMinutes()
            : (r.getBookingConfig() != null ? r.getBookingConfig().getAllowedDurationsMinutes() : List.of(60,90,120));

        if (allowed != null && !allowed.isEmpty() && !allowed.contains(dur)) {
            throw new IllegalArgumentException("durationMinutes không hợp lệ. Allowed=" + allowed);
        }

        return dur;
    }

    private int loadBaseTotalTables(RestaurantDoc r, String tableTypeId) {
        RestaurantDoc.TableType tt = findTableType(r, tableTypeId);
        if (tt == null) throw new IllegalArgumentException("TableType không tồn tại: " + tableTypeId);
        return tt.getTotalTables() != null ? tt.getTotalTables() : 0;
    }

    private RestaurantDoc.TableType findTableType(RestaurantDoc r, String tableTypeId) {
        if (r.getTableTypes() == null) return null;
        return r.getTableTypes().stream()
            .filter(x -> x != null && tableTypeId != null && tableTypeId.equals(x.getId()))
            .findFirst().orElse(null);
    }

    private LocalDateTime normalizeSlot(LocalDate date, LocalTime time, int slotMinutes) {
        if (date == null || time == null) throw new IllegalArgumentException("Thiếu reservationDate/reservationTime");
        LocalDateTime dt = LocalDateTime.of(date, time);

        int minute = dt.getMinute();
        int normalizedMinute = (minute / slotMinutes) * slotMinutes;

        return dt.withMinute(normalizedMinute).withSecond(0).withNano(0);
    }

    private List<LocalDateTime> expandSlots(LocalDateTime startSlot, int durationMinutes, int slotMinutes) {
        if (durationMinutes <= 0) durationMinutes = 90;
        int slotsCount = (int) Math.ceil(durationMinutes * 1.0 / slotMinutes);

        List<LocalDateTime> slots = new ArrayList<>();
        for (int i = 0; i < slotsCount; i++) {
            slots.add(startSlot.plusMinutes((long) i * slotMinutes));
        }
        return slots;
    }

    private void validateLeadTime(RestaurantDoc r, LocalDate date, LocalTime time) {
        int lead = (r.getBookingConfig() != null && r.getBookingConfig().getMinBookingLeadTimeMinutes() != null)
            ? r.getBookingConfig().getMinBookingLeadTimeMinutes()
            : 60;

        LocalDateTime target = LocalDateTime.of(date, time);
        LocalDateTime now = LocalDateTime.now();

        if (target.isBefore(now.plusMinutes(lead))) {
            throw new IllegalArgumentException("Phải đặt trước tối thiểu " + lead + " phút");
        }
    }
}