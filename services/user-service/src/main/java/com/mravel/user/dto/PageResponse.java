package com.mravel.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> items;
    private int page;
    private int size;
    private long total;
    private boolean hasMore;
}
