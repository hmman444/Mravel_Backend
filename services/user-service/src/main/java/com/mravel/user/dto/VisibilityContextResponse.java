package com.mravel.user.dto;

import java.util.List;

/**
 * Ngữ cảnh hiển thị của 1 user, trả về trong 1 lần gọi cho tầng feed/search:
 * - friendIds: bạn bè (ACCEPTED), đã loại người bị chặn
 * - blockedIds: tập "vô hình hai chiều" (mình chặn ai ∪ ai chặn mình)
 */
public record VisibilityContextResponse(List<Long> friendIds, List<Long> blockedIds) {
}
