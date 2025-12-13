// src/main/java/com/mravel/booking/dto/hotel/HotelBookingDtos.java
package com.mravel.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HotelBookingDtos {

    /**
     * Request tạo booking khách sạn
     * FE gửi khi user bấm "Đặt phòng".
     */
    public record CreateHotelBookingRequest(
            Long userId,              // tạm, sau dùng JWT thì có thể bỏ ra
            String contactName,
            String contactPhone,
            String contactEmail,
            String note,

            String hotelId,           // HotelDoc.id
            String hotelSlug,
            String hotelName,         // FE có thể pass hoặc BE gọi catalog để lấy snapshot

            LocalDate checkInDate,
            LocalDate checkOutDate,

            // Hình thức thanh toán: FULL / DEPOSIT
            String payOption,

            // Danh sách phòng user chọn
            List<SelectedRoom> rooms
    ) {}

    /**
     * Một loại phòng user chọn trong hotel.
     */
    public record SelectedRoom(
            String roomTypeId,        // HotelDoc.RoomType.id
            String roomTypeName,      // snapshot
            String ratePlanId,        // HotelDoc.RatePlan.id
            String ratePlanName,      // snapshot
            Integer quantity,         // số phòng loại này
            BigDecimal pricePerNight  // giá/đêm tại thời điểm FE hiển thị (có thể để BE verify)
    ) {}

    /**
     * Response trả về sau khi tạo booking (trước khi redirect Momo).
     * Chứa bookingCode + số tiền user phải thanh toán + (option) payUrl.
     */
    public record HotelBookingCreatedDTO(
            String bookingCode,
            String hotelName,
            String hotelSlug,

            LocalDate checkInDate,
            LocalDate checkOutDate,
            Integer nights,

            Integer roomsCount,

            String payOption,         // FULL / DEPOSIT
            BigDecimal totalAmount,   // tổng giá trị booking
            BigDecimal depositAmount, // tiền cọc (nếu payOption=DEPOSIT)
            BigDecimal amountPayable, // số tiền phải thanh toán ngay
            String currencyCode,

            // Sau này khi tích hợp Momo có thể add:
            String paymentMethod,     // "MOMO_WALLET"
            String paymentUrl         // payUrl redirect tới Momo (hiện tại có thể để null)
    ) {}
}