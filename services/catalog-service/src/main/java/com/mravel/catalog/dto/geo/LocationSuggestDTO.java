package com.mravel.catalog.dto.geo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationSuggestDTO {

    private String name; // tên hiển thị ngắn gọn
    private String label; // alias (FE đang ưu tiên name || label)
    private String fullAddress; // địa chỉ đầy đủ
    private String addressLine; // dòng địa chỉ chính (để FE hiển thị)

    private Double latitude;
    private Double longitude;

    private String placeId; // id của provider (OSM / Google, vv.)

    // Optional - để sau này parse chi tiết hơn
    private String cityName;
    private String districtName;
    private String provinceName;
}
