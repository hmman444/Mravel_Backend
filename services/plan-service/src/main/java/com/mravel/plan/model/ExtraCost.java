package com.mravel.plan.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraCost {

    /**
     * Lý do: "Phụ thu cuối tuần", "Tiền gửi xe", "Tip", "Giảm giá voucher"...
     */
    private String reason;

    /**
     * Loại chi phí: SURCHARGE, TAX, SERVICE_FEE, PARKING, TIP, DISCOUNT, OTHER...
     * Bạn có thể giữ String, hoặc tạo Enum riêng ExtraCostType.
     */
    private String type;

    /**
     * Số tiền ước lượng (có thể âm nếu là giảm giá).
     */
    private Long estimatedAmount;

    /**
     * Số tiền thực tế (có thể âm nếu là giảm giá / hoàn tiền).
     */
    private Long actualAmount;
}
