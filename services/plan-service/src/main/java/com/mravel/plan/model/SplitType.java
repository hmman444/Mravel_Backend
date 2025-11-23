package com.mravel.plan.model;

public enum SplitType {
    NONE, // Không chia, chỉ ghi nhận người trả
    EVEN, // Chia đều cho splitMembers
    EXACT, // Chia theo số tiền cố định từng người
    PERCENT, // Chia theo %
    SHARES // Chia theo số "phần" (2 phần người lớn, 1 phần trẻ em...)
}
