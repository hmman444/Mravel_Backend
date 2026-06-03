package com.mravel.booking.service;

import com.mravel.booking.dto.AdminBookingStatsDtos.*;
import com.mravel.booking.model.BookingBase.BookingStatus;
import com.mravel.booking.model.BookingBase.PaymentStatus;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;

/**
 * Tổng hợp số liệu booking cho admin dashboard.
 * Gộp HotelBooking + RestaurantBooking; doanh thu = sum(amountPaid) khi paymentStatus=SUCCESS.
 */
@Service
@RequiredArgsConstructor
public class AdminBookingStatsService {

    private final HotelBookingRepository hotelRepo;
    private final RestaurantBookingRepository restaurantRepo;

    private static final ZoneId ZONE = ZoneId.of("Asia/Ho_Chi_Minh");
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final List<BookingStatus> CANCEL_STATUSES = List.of(
            BookingStatus.CANCELLED, BookingStatus.CANCELLED_BY_PARTNER, BookingStatus.CANCELLED_BY_GUEST);
    private static final List<PaymentStatus> REFUND_STATUSES = List.of(
            PaymentStatus.REFUNDED, PaymentStatus.PARTIAL_REFUNDED);

    public BookingStats getStats(String range) {
        Instant now = Instant.now();
        LocalDate today = LocalDate.now(ZONE);
        Instant todayStart = today.atStartOfDay(ZONE).toInstant();
        Instant sevenDaysAgo = now.minusSeconds(7L * 24 * 3600);

        long bookingsToday = hotelRepo.countByCreatedAtBetween(todayStart, now)
                + restaurantRepo.countByCreatedAtBetween(todayStart, now);
        long bookings7d = hotelRepo.countByCreatedAtBetween(sevenDaysAgo, now)
                + restaurantRepo.countByCreatedAtBetween(sevenDaysAgo, now);

        BigDecimal revenueToday = nz(hotelRepo.sumAmountPaid(PaymentStatus.SUCCESS, todayStart, now))
                .add(nz(restaurantRepo.sumAmountPaid(PaymentStatus.SUCCESS, todayStart, now)));
        BigDecimal revenue7d = nz(hotelRepo.sumAmountPaid(PaymentStatus.SUCCESS, sevenDaysAgo, now))
                .add(nz(restaurantRepo.sumAmountPaid(PaymentStatus.SUCCESS, sevenDaysAgo, now)));

        long cancelled7d = hotelRepo.countByStatusInAndCreatedAtBetween(CANCEL_STATUSES, sevenDaysAgo, now)
                + restaurantRepo.countByStatusInAndCreatedAtBetween(CANCEL_STATUSES, sevenDaysAgo, now);
        long refunded7d = hotelRepo.countByPaymentStatusInAndCreatedAtBetween(REFUND_STATUSES, sevenDaysAgo, now)
                + restaurantRepo.countByPaymentStatusInAndCreatedAtBetween(REFUND_STATUSES, sevenDaysAgo, now);
        long failed7d = hotelRepo.countByPaymentStatusAndCreatedAtBetween(PaymentStatus.FAILED, sevenDaysAgo, now)
                + restaurantRepo.countByPaymentStatusAndCreatedAtBetween(PaymentStatus.FAILED, sevenDaysAgo, now);

        double cancelRate = pct(cancelled7d, bookings7d);
        double refundRate = pct(refunded7d, bookings7d);
        double payFailRate = pct(failed7d, bookings7d);

        Instant windowStart = windowStart(range, now, todayStart, today);
        List<StatusCount> bookingStatus = mergeStatus(windowStart, now);
        List<RevenuePoint> revenueSeries = buildSeries(range, today, now);
        List<TopService> topServices = buildTopServices(windowStart, now);

        return new BookingStats(
                bookingsToday, bookings7d, revenueToday, revenue7d,
                cancelRate, refundRate, payFailRate, failed7d,
                bookingStatus, revenueSeries, topServices);
    }

    // ===== bookingStatus =====
    private List<StatusCount> mergeStatus(Instant start, Instant end) {
        Map<String, Long> counts = new LinkedHashMap<>();
        for (Object[] r : hotelRepo.countGroupByStatus(start, end)) {
            counts.merge(((BookingStatus) r[0]).name(), ((Number) r[1]).longValue(), Long::sum);
        }
        for (Object[] r : restaurantRepo.countGroupByStatus(start, end)) {
            counts.merge(((BookingStatus) r[0]).name(), ((Number) r[1]).longValue(), Long::sum);
        }
        return counts.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(e -> new StatusCount(e.getKey(), e.getValue()))
                .toList();
    }

    // ===== topServices (top 10 by revenue, hotel + restaurant merged) =====
    private List<TopService> buildTopServices(Instant start, Instant end) {
        List<TopService> all = new ArrayList<>();
        PageRequest top = PageRequest.of(0, 25);
        for (Object[] r : hotelRepo.topServices(PaymentStatus.SUCCESS, start, end, top)) {
            all.add(new TopService((String) r[0], "HOTEL", (String) r[1],
                    ((Number) r[2]).longValue(), nz((BigDecimal) r[3])));
        }
        for (Object[] r : restaurantRepo.topServices(PaymentStatus.SUCCESS, start, end, top)) {
            all.add(new TopService((String) r[0], "RESTAURANT", (String) r[1],
                    ((Number) r[2]).longValue(), nz((BigDecimal) r[3])));
        }
        return all.stream()
                .sorted((a, b) -> b.revenue().compareTo(a.revenue()))
                .limit(10)
                .toList();
    }

    // ===== revenueSeries =====
    private List<RevenuePoint> buildSeries(String range, LocalDate today, Instant now) {
        List<String> labels = new ArrayList<>();
        Function<LocalDate, String> classify;
        Instant start;

        switch (norm(range)) {
            case "today" -> {
                labels.add("Hôm nay");
                classify = d -> "Hôm nay";
                start = today.atStartOfDay(ZONE).toInstant();
            }
            case "monthly" -> {
                labels.addAll(List.of("Tuần 1", "Tuần 2", "Tuần 3", "Tuần 4"));
                classify = d -> "Tuần " + Math.min(4, ((d.getDayOfMonth() - 1) / 7) + 1);
                start = today.withDayOfMonth(1).atStartOfDay(ZONE).toInstant();
            }
            case "yearly" -> {
                for (int m = 1; m <= 12; m++) labels.add("Th" + m);
                classify = d -> "Th" + d.getMonthValue();
                start = today.withDayOfYear(1).atStartOfDay(ZONE).toInstant();
            }
            default -> { // weekly: 7 ngày gần nhất, nhãn theo thứ
                for (int i = 6; i >= 0; i--) labels.add(dow(today.minusDays(i)));
                classify = this::dow;
                start = today.minusDays(6).atStartOfDay(ZONE).toInstant();
            }
        }

        LinkedHashMap<String, BigDecimal> hotel = new LinkedHashMap<>();
        LinkedHashMap<String, BigDecimal> rest = new LinkedHashMap<>();
        for (String lb : labels) {
            hotel.put(lb, ZERO);
            rest.put(lb, ZERO);
        }
        accumulate(hotelRepo.revenueRows(PaymentStatus.SUCCESS, start, now), classify, hotel);
        accumulate(restaurantRepo.revenueRows(PaymentStatus.SUCCESS, start, now), classify, rest);

        return labels.stream()
                .map(lb -> new RevenuePoint(lb, hotel.get(lb), rest.get(lb)))
                .toList();
    }

    private void accumulate(List<Object[]> rows, Function<LocalDate, String> classify,
            LinkedHashMap<String, BigDecimal> bucket) {
        for (Object[] r : rows) {
            Instant t = (Instant) r[0];
            if (t == null) continue;
            BigDecimal amt = nz((BigDecimal) r[1]);
            String lb = classify.apply(t.atZone(ZONE).toLocalDate());
            if (bucket.containsKey(lb)) bucket.merge(lb, amt, BigDecimal::add);
        }
    }

    // ===== helpers =====
    private Instant windowStart(String range, Instant now, Instant todayStart, LocalDate today) {
        return switch (norm(range)) {
            case "today" -> todayStart;
            case "monthly" -> today.withDayOfMonth(1).atStartOfDay(ZONE).toInstant();
            case "yearly" -> today.withDayOfYear(1).atStartOfDay(ZONE).toInstant();
            default -> now.minusSeconds(7L * 24 * 3600);
        };
    }

    private static String norm(String range) {
        return range == null ? "weekly" : range.trim().toLowerCase();
    }

    private String dow(LocalDate d) {
        return switch (d.getDayOfWeek()) {
            case MONDAY -> "T2";
            case TUESDAY -> "T3";
            case WEDNESDAY -> "T4";
            case THURSDAY -> "T5";
            case FRIDAY -> "T6";
            case SATURDAY -> "T7";
            case SUNDAY -> "CN";
        };
    }

    private static BigDecimal nz(BigDecimal v) {
        return v == null ? ZERO : v;
    }

    private static double pct(long part, long total) {
        if (total <= 0) return 0d;
        return BigDecimal.valueOf(part * 100.0 / total).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}
