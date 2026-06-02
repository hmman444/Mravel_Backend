package com.mravel.plan.dto;

import java.util.List;

/**
 * Ngữ cảnh hiển thị của viewer lấy từ user-service trong 1 lần gọi:
 * - friendIds: bạn bè (đã loại người bị chặn)
 * - blockedIds: tập "vô hình hai chiều" (mình chặn ai ∪ ai chặn mình)
 */
public record VisibilityContext(List<Long> friendIds, List<Long> blockedIds) {
}
