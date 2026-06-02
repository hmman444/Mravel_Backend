package com.mravel.admin.service;

import com.mravel.admin.client.AuthAdminClient;
import com.mravel.admin.client.BookingAdminClient;
import com.mravel.admin.client.CatalogClient;
import com.mravel.admin.client.PlanAdminClient;
import com.mravel.admin.dto.dashboard.AdminDashboardDtos.*;
import com.mravel.admin.dto.dashboard.DownstreamDtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Tổng hợp số liệu dashboard admin từ các service downstream.
 * Mỗi nguồn fail-soft: thiếu service nào thì phần đó về mặc định, dashboard vẫn dựng được.
 */
@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final BookingAdminClient bookingClient;
    private final CatalogClient catalogClient;
    private final AuthAdminClient authClient;
    private final PlanAdminClient planClient;

    private static final BigDecimal MILLION = BigDecimal.valueOf(1_000_000);

    public DashboardResponse build(String range, String bearer) {
        String r = (range == null || range.isBlank()) ? "weekly" : range;

        BookingStats booking = bookingClient.getStats(r, bearer);
        CatalogSummary catalog = catalogClient.dashboardSummary(bearer);
        UserStats users = authClient.userStats(bearer);
        long pendingReports = planClient.pendingReports(bearer);

        long servicesActive = catalog == null ? 0 : catalog.hotelActive() + catalog.restaurantActive();
        long servicesPending = catalog == null ? 0 : catalog.hotelPending() + catalog.restaurantPending();

        Overview overview = new Overview(
                users == null ? 0 : users.totalUsers(),
                null, // activeUsers7d: N/A (User entity chưa có mốc đăng nhập)
                users == null ? 0 : users.totalPartners(),
                servicesActive,
                servicesPending,
                booking == null ? 0 : booking.bookingsToday(),
                booking == null ? 0 : booking.bookings7d(),
                booking == null ? BigDecimal.ZERO : nz(booking.revenueTodayVnd()),
                booking == null ? BigDecimal.ZERO : nz(booking.revenue7dVnd()),
                booking == null ? 0 : booking.cancelRate7dPct(),
                booking == null ? 0 : booking.refundRate7dPct(),
                booking == null ? 0 : booking.paymentFail7dPct());

        long failedPayments = booking == null ? 0 : booking.failedPayments7d();

        // Hàng đợi xử lý: chỉ mục có dữ liệu thật
        List<ActionQueueItem> actionQueue = new ArrayList<>();
        actionQueue.add(new ActionQueueItem("svc-approve", "admin.action_queue.svc_approve_title",
                servicesPending, servicesPending > 0 ? "high" : "low", "admin.action_queue.svc_approve_hint"));
        actionQueue.add(new ActionQueueItem("reports", "admin.action_queue.reports_title",
                pendingReports, pendingReports > 0 ? "high" : "low", "admin.action_queue.reports_hint"));
        actionQueue.add(new ActionQueueItem("payment-failed", "admin.action_queue.payment_failed_title",
                failedPayments, failedPayments > 0 ? "medium" : "low", "admin.action_queue.payment_failed_hint"));

        // Cảnh báo suy ra từ ngưỡng trên số liệu thật
        List<AlertItem> alerts = new ArrayList<>();
        if (overview.cancelRate7dPct() > 8.0)
            alerts.add(new AlertItem("cancel-spike", "warning",
                    "admin.alert.cancel_spike_title", "admin.alert.cancel_spike_desc"));
        if (overview.paymentFail7dPct() > 3.0)
            alerts.add(new AlertItem("payment-spike", "danger",
                    "admin.alert.payment_spike_title", "admin.alert.payment_spike_desc"));
        if (pendingReports > 10)
            alerts.add(new AlertItem("content-report", "warning",
                    "admin.alert.content_report_title", "admin.alert.content_report_desc"));

        // Trạng thái đơn (số lượng, gộp hotel + restaurant)
        List<BookingStatusItem> bookingStatus = new ArrayList<>();
        if (booking != null && booking.bookingStatus() != null) {
            for (BookingStatusCount s : booking.bookingStatus()) {
                bookingStatus.add(new BookingStatusItem(s.status(), s.status(), s.count()));
            }
        }

        // Doanh thu theo thời gian: VND thô -> triệu VND
        List<RevenuePoint> revenueSeries = new ArrayList<>();
        if (booking != null && booking.revenueSeries() != null) {
            for (BookingRevenuePoint p : booking.revenueSeries()) {
                long hotelM = toMillions(p.hotel());
                long restM = toMillions(p.restaurant());
                revenueSeries.add(new RevenuePoint(p.label(), hotelM + restM, hotelM, restM));
            }
        }

        // Dịch vụ nổi bật (top 5)
        List<BookingTopService> topSrc = (booking == null || booking.topServices() == null)
                ? List.of() : booking.topServices();
        List<TopServiceItem> topServices = topSrc.stream()
                .limit(5)
                .map(ts -> new TopServiceItem(ts.name(), ts.type(), ts.orders(), toMillions(ts.revenue())))
                .toList();

        // Đối tác đóng góp (top 5 theo doanh thu) — resolve serviceId -> partner qua catalog
        List<TopPartnerItem> topPartners = buildTopPartners(topSrc, bearer);

        return new DashboardResponse(r, overview, alerts, actionQueue,
                revenueSeries, bookingStatus, topServices, topPartners);
    }

    private List<TopPartnerItem> buildTopPartners(List<BookingTopService> topSrc, String bearer) {
        if (topSrc == null || topSrc.isEmpty())
            return List.of();

        List<String> hotelIds = topSrc.stream()
                .filter(s -> "HOTEL".equals(s.type()))
                .map(BookingTopService::serviceId).filter(Objects::nonNull).distinct().toList();
        List<String> restaurantIds = topSrc.stream()
                .filter(s -> "RESTAURANT".equals(s.type()))
                .map(BookingTopService::serviceId).filter(Objects::nonNull).distinct().toList();

        List<PartnerRef> refs = catalogClient.resolvePartners(hotelIds, restaurantIds, bearer);
        if (refs == null || refs.isEmpty())
            return List.of();

        Map<String, PartnerRef> byServiceId = new HashMap<>();
        for (PartnerRef ref : refs) {
            if (ref.serviceId() != null && ref.partnerId() != null)
                byServiceId.put(ref.serviceId(), ref);
        }

        Map<String, BigDecimal> revenueByPartner = new HashMap<>();
        Map<String, Integer> servicesByPartner = new HashMap<>();
        Map<String, String> nameByPartner = new HashMap<>();
        for (BookingTopService s : topSrc) {
            PartnerRef ref = byServiceId.get(s.serviceId());
            if (ref == null)
                continue;
            revenueByPartner.merge(ref.partnerId(), nz(s.revenue()), BigDecimal::add);
            servicesByPartner.merge(ref.partnerId(), 1, Integer::sum);
            nameByPartner.putIfAbsent(ref.partnerId(),
                    ref.partnerName() == null || ref.partnerName().isBlank() ? ref.partnerId() : ref.partnerName());
        }

        return revenueByPartner.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(e -> new TopPartnerItem(
                        nameByPartner.get(e.getKey()),
                        servicesByPartner.getOrDefault(e.getKey(), 0),
                        toMillions(e.getValue())))
                .toList();
    }

    private static BigDecimal nz(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    private static long toMillions(BigDecimal vnd) {
        if (vnd == null)
            return 0L;
        return vnd.divide(MILLION, 0, RoundingMode.HALF_UP).longValue();
    }
}
