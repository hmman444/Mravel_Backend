package com.mravel.catalog.model.doc;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mravel.catalog.model.enums.TargetType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "favorites")
@CompoundIndexes({
    @CompoundIndex(name = "user_target_unique_idx",
                   def = "{'userId': 1, 'targetType': 1, 'targetId': 1}",
                   unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteDoc {

    @Id
    private String id;

    /** ID của user (lấy từ JWT qua auth-service validate) */
    @Indexed
    private Long userId;

    /** Loại đối tượng: HOTEL / RESTAURANT / PLACE */
    private TargetType targetType;

    /** ID của đối tượng (hotelId, restaurantId, placeId) */
    @Indexed
    private String targetId;

    @Builder.Default
    private Instant createdAt = Instant.now();
}
