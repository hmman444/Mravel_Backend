package com.mravel.catalog.model.doc;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "room_inventories")
public class RoomInventoryDoc {

    @Id
    private String id;

    private String hotelId;      // hoặc hotelSlug
    private String roomTypeId;   // vd: "rt-bespoke-family"
    private String ratePlanId;   // vd: "rp-bespoke-family-room-only-nonref"

    private LocalDate date;      // inventory cho đêm này

    // 👉 field để “set” số phòng tối đa cho plan này, trong ngày này
    private Integer totalRooms;   

    // số phòng đã bị book (đơn đã confirm)
    private Integer bookedRooms;

    public int getRemainingRooms() {
        return totalRooms - bookedRooms;
    }
}