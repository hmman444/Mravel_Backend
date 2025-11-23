package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plan_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================== THÔNG TIN CƠ BẢN ==================

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Thời lượng hoạt động (phút).
     * Có thể null, khi đó FE/BE có thể tính từ startTime/endTime.
     */
    private Integer durationMinutes;

    @Builder.Default
    @Column(nullable = false)
    private boolean done = false;

    @Column(nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PlanList list;

    // ================== LOẠI HOẠT ĐỘNG & DATA LINH HOẠT ==================

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private PlanActivityType activityType;

    /**
     * JSON tùy biến, chứa snapshot từ PlaceDoc / HotelDoc / RestaurantDoc,
     * cùng với thông tin cụ thể cho activity này.
     */
    @Lob
    @Column(name = "activity_data_json", columnDefinition = "TEXT")
    private String activityDataJson;

    // ================== THAM GIA HOẠT ĐỘNG ==================

    /**
     * Số người tham gia hoạt động này (dùng để ước lượng chi phí / người).
     * Nếu null, có thể lấy từ participants.size().
     */
    private Integer participantCount;

    /**
     * Danh sách thành viên tham gia hoạt động (PlanMember.userId).
     */
    @ElementCollection
    @CollectionTable(name = "card_participants", joinColumns = @JoinColumn(name = "card_id"))
    @Builder.Default
    private Set<CardPersonRef> participants = new HashSet<>();

    // ================== THÔNG TIN CHI PHÍ ==================

    /**
     * Mã tiền tệ, mặc định VND.
     */
    @Column(name = "currency_code", length = 10)
    @Builder.Default
    private String currencyCode = "VND";

    /**
     * Chi phí "chính" ước lượng (tiền phòng, tiền món, tiền vé...),
     * chưa tính phụ thu / thuế / phí khác.
     */
    @Column(name = "base_estimated_cost")
    private Long baseEstimatedCost;

    /**
     * Chi phí "chính" thực tế.
     */
    @Column(name = "base_actual_cost")
    private Long baseActualCost;

    /**
     * Tổng chi phí ước lượng = baseEstimatedCost + sum(extra.estimatedAmount)
     */
    @Column(name = "estimated_cost")
    private Long estimatedCost;

    /**
     * Tổng chi phí thực tế = baseActualCost + sum(extra.actualAmount)
     */
    @Column(name = "actual_cost")
    private Long actualCost;

    /**
     * Các chi phí phát sinh: phụ thu, thuế, phí gửi xe, tip, giảm giá...
     */
    @ElementCollection
    @CollectionTable(name = "card_extra_costs", joinColumns = @JoinColumn(name = "card_id"))
    @Builder.Default
    private Set<ExtraCost> extraCosts = new HashSet<>();

    // ================== THANH TOÁN & CHIA TIỀN ==================

    /**
     * Người trả tiền chính (dùng hiển thị nhanh).
     * Có thể null nếu nhiều người trả, chi tiết nằm trong payments.
     */
    @Column(name = "payer_id")
    private Long payerId;

    /**
     * Cờ cho biết payer có được tính là người tham gia chia tiền hay không.
     * true: payer cũng chia đều / chia phần.
     * false: payer chỉ ứng tiền hộ.
     */
    @Builder.Default
    @Column(name = "include_payer_in_split", nullable = false)
    private boolean includePayerInSplit = true;

    /**
     * Cấu hình kiểu chia tiền:
     * NONE = không chia, chỉ ghi người trả
     * EVEN = chia đều cho splitMembers
     * EXACT = mỗi người 1 số tiền cố định
     * PERCENT= chia theo %
     * SHARES = chia theo số "phần"
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "split_type")
    private SplitType splitType;

    /**
     * Danh sách thành viên tham gia chia tiền (con nợ).
     */

    @ElementCollection
    @CollectionTable(name = "card_split_members", joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "member_id")
    @Builder.Default
    private Set<CardPersonRef> splitMembers = new HashSet<>();

    /**
     * Chi tiết chia tiền: mỗi memberId phải chịu bao nhiêu.
     * (được tính từ splitType + total actualCost).
     */
    @ElementCollection
    @CollectionTable(name = "card_split_details", joinColumns = @JoinColumn(name = "card_id"))
    @Builder.Default
    private Set<CardSplitDetail> splitDetails = new HashSet<>();

    /**
     * Danh sách thanh toán thực tế: nhiều người có thể trả tiền cho cùng card.
     * Nếu chưa cần phức tạp, bạn có thể bỏ phần này và chỉ dùng payerId.
     */
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<PlanCardPayment> payments = new HashSet<>();

}
