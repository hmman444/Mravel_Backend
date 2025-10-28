package com.mravel.catalog.model.enums;

/** Tầng phân cấp của place */
public enum PlaceKind {
    DESTINATION, // địa điểm lớn: TP.HCM, Đà Nẵng...
    POI,         // địa điểm con / tham quan bên trong một destination
    VENUE        // địa điểm dịch vụ: HOTEL / RESTAURANT (gắn với một destination)
}