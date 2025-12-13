// src/main/java/com/mravel/catalog/model/doc/RoomInventoryDoc.java
package com.mravel.catalog.model.doc;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "room_inventories")
@CompoundIndexes({
    @CompoundIndex(
        name = "inv_hotel_room_date_unique",
        def = "{'hotelId':1,'roomTypeId':1,'date':1}",
        unique = true
    )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomInventoryDoc {

    @Id
    private String id;

    /** Id của HotelDoc (Mongo ID) – để join inventory theo hotel */
    @Indexed
    private String hotelId;

    /** Slug để query nhanh nếu cần (không bắt buộc) */
    @Indexed
    private String hotelSlug;

    /** Id roomType trong HotelDoc.roomTypes[].id, ví dụ: "rt-bespoke-family" */
    @Indexed
    private String roomTypeId;

    /** Optional: nếu sau này bạn muốn quản lý chi tiết theo rate plan */
    private String ratePlanId;

    /** Inventory cho ĐÊM này (check-in vào ngày này, ngủ qua đêm đó) */
    @Indexed
    private LocalDate date;

    /**
     * Tổng số phòng có thể bán cho roomType này trong ĐÊM đó.
     * Thường = RoomType.totalRooms (nếu không muốn dynamic).
     */
    private Integer totalRooms;

    /** Số phòng đã được giữ/bán (đơn đã xác nhận) */
    private Integer bookedRooms;
    private Integer heldRooms; // NEW

    public int getRemainingRooms() {
        int total = totalRooms != null ? totalRooms : 0;
        int booked = bookedRooms != null ? bookedRooms : 0;
        int held = heldRooms != null ? heldRooms : 0;
        return total - booked - held;
    }

    public void increaseHeld(int quantity) {
        if (quantity <= 0) return;
        if (heldRooms == null) heldRooms = 0;
        heldRooms += quantity;
    }

    public void decreaseHeld(int quantity) {
        if (quantity <= 0) return;
        if (heldRooms == null) heldRooms = 0;
        heldRooms -= quantity;
        if (heldRooms < 0) heldRooms = 0;
    }

    public void commitHeldToBooked(int quantity) {
        if (quantity <= 0) return;
        // đảm bảo held đủ (tuỳ bạn: throw hay clamp)
        int held = heldRooms != null ? heldRooms : 0;
        if (held < quantity) {
            throw new IllegalStateException("Held rooms không đủ để commit. held=" + held + ", req=" + quantity);
        }
        decreaseHeld(quantity);
        increaseBooked(quantity);
    }

    public void increaseBooked(int quantity) {
        if (quantity <= 0) return;
        if (bookedRooms == null) bookedRooms = 0;
        bookedRooms += quantity;
    }

    /**
     * Giảm số phòng đã giữ/bán – dùng khi hủy booking / rollback tồn kho.
     */
    public void decreaseBooked(int quantity) {
        if (quantity <= 0) return;
        if (bookedRooms == null) bookedRooms = 0;
        bookedRooms -= quantity;
        if (bookedRooms < 0) {
            bookedRooms = 0;
        }
    }
}