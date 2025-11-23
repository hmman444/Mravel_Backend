package com.mravel.plan.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardSplitDetail {

    /**
     * Người phải chịu chi phí cho card này.
     * Có thể là member (memberId != null) hoặc người ngoài (external = true).
     */
    @Embedded
    private CardPersonRef person;

    /**
     * Số tiền người này phải trả (theo kết quả chia).
     * Đây là số tiền GROSS, không phải net giữa các thành viên.
     */
    private Long amount;
}
