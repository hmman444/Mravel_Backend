package com.mravel.booking.payment.momo;

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
public class MomoIpnRequest {

    // Mã đơn hàng – lúc tạo payment em set = bookingCode
    private String orderId;

    // Số tiền MoMo thanh toán (VND)
    private Long amount;

    // 0 = thành công, khác 0 = lỗi
    private Integer resultCode;

    // Optional – message từ MoMo
    private String message;

    // Optional – chữ ký, để sau verify
    private String signature;
}