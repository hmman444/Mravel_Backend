// src/main/java/com/mravel/booking/payment/MomoConfirmRequest.java
package com.mravel.booking.payment;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MomoConfirmRequest {

    // bookingCode = orderId bạn dùng khi tạo payment
    private String orderId;

    // số tiền MoMo thực thanh toán (VND)
    private BigDecimal amount;

    // 0 = thành công, khác 0 = lỗi (cho đồng bộ với MomoIpnRequest)
    private Integer resultCode;

    private String message;
}