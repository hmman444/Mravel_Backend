// src/main/java/com/mravel/catalog/model/doc/TableInventoryDoc.java
package com.mravel.catalog.model.doc;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "table_inventories")
@CompoundIndexes({
    @CompoundIndex(
        name = "inv_rest_table_slot_unique",
        def = "{'restaurantId':1,'tableTypeId':1,'slotStart':1}",
        unique = true
    )
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TableInventoryDoc {

    @Id
    private String id;

    @Indexed
    private String restaurantId;

    @Indexed
    private String restaurantSlug;

    @Indexed
    private String tableTypeId;

    /** Start time của slot (đã được normalize theo slotMinutes) */
    @Indexed
    private LocalDateTime slotStart;

    private Integer totalTables;
    private Integer bookedTables;
    private Integer heldTables;

    public int getRemainingTables() {
        int total = totalTables != null ? totalTables : 0;
        int booked = bookedTables != null ? bookedTables : 0;
        int held = heldTables != null ? heldTables : 0;
        return total - booked - held;
    }

    public void increaseHeld(int quantity) {
        if (quantity <= 0) return;
        if (heldTables == null) heldTables = 0;
        heldTables += quantity;
    }

    public void decreaseHeld(int quantity) {
        if (quantity <= 0) return;
        if (heldTables == null) heldTables = 0;
        heldTables -= quantity;
        if (heldTables < 0) heldTables = 0;
    }

    public void increaseBooked(int quantity) {
        if (quantity <= 0) return;
        if (bookedTables == null) bookedTables = 0;
        bookedTables += quantity;
    }

    public void decreaseBooked(int quantity) {
        if (quantity <= 0) return;
        if (bookedTables == null) bookedTables = 0;
        bookedTables -= quantity;
        if (bookedTables < 0) bookedTables = 0;
    }

    public void commitHeldToBooked(int quantity) {
        int held = heldTables != null ? heldTables : 0;
        if (held < quantity) {
            throw new IllegalStateException("Held tables không đủ để commit. held=" + held + ", req=" + quantity);
        }
        decreaseHeld(quantity);
        increaseBooked(quantity);
    }
}