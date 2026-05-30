package com.mravel.catalog.model.doc;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.enums.AmenitySection;

import lombok.*;

@Document("amenities")
@CompoundIndex(name = "ux_code_scope", def = "{'code': 1, 'scope': 1}", unique = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmenityCatalogDoc {

    @Id
    private String id;

    /** Unique in scope, uppercase snake recommended: WIFI_FREE */
    private String code;

    /** Display name (localized) */
    private Map<String, String> name;

    /** HOTEL / RESTAURANT / ROOM */
    private AmenityScope scope;

    /** Group for UI (tầng 1) */
    private AmenityGroup group;

    /** Section for UI (tầng 2) */
    private AmenitySection section;

    /** Optional: icon key for FE */
    private String icon;

    /** Optional: description (localized) */
    private Map<String, String> description;

    /** For mapping from external sources / seed */
    private List<String> aliases;

    private Integer sortOrder;

    private boolean active;

    private Instant createdAt;
    private Instant updatedAt;

}
