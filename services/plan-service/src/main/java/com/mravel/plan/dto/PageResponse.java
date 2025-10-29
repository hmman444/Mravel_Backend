package com.mravel.plan.dto;

import lombok.*;
import java.util.List;

/**
 * DTO trả về cho tất cả các API có phân trang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> items;
    private int page;
    private int size;
    private long total;
    private boolean hasMore;
}
