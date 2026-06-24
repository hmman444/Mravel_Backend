package com.mravel.admin.service;

import com.mravel.admin.client.AuthAdminClient;
import com.mravel.admin.client.BookingAdminClient;
import com.mravel.admin.client.CatalogClient;
import com.mravel.admin.client.PlanAdminClient;
import com.mravel.admin.dto.dashboard.AdminDashboardDtos.*;
import com.mravel.admin.dto.dashboard.DownstreamDtos.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminDashboardServiceTest {

    @Mock BookingAdminClient bookingClient;
    @Mock CatalogClient catalogClient;
    @Mock AuthAdminClient authClient;
    @Mock PlanAdminClient planClient;

    @InjectMocks AdminDashboardService service;

    private static final String BEARER = "test-token";

    // ── Happy path ──────────────────────────────────────────────────────────

    @Test
    void build_allSourcesAvailable_returnCompleteOverview() {
        BookingStats booking = new BookingStats(
                5, 30,
                BigDecimal.valueOf(2_000_000), BigDecimal.valueOf(15_000_000),
                2.0, 1.0, 1.0, 0,
                List.of(new BookingStatusCount("CONFIRMED", 20)),
                List.of(new BookingRevenuePoint("Mon", BigDecimal.valueOf(1_000_000), BigDecimal.valueOf(500_000))),
                List.of());
        CatalogSummary catalog = new CatalogSummary(10, 3, 5, 2);
        UserStats users = new UserStats(100, 15, 83, 2);

        when(bookingClient.getStats(eq("weekly"), eq(BEARER))).thenReturn(booking);
        when(catalogClient.dashboardSummary(eq(BEARER))).thenReturn(catalog);
        when(authClient.userStats(eq(BEARER))).thenReturn(users);
        when(planClient.pendingReports(eq(BEARER))).thenReturn(0L);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.overview().totalUsers()).isEqualTo(100);
        assertThat(resp.overview().partners()).isEqualTo(15);
        assertThat(resp.overview().servicesActive()).isEqualTo(15); // 10 + 5
        assertThat(resp.overview().servicesPendingApproval()).isEqualTo(5);  // 3 + 2
        assertThat(resp.overview().bookingsToday()).isEqualTo(5);
        assertThat(resp.overview().bookings7d()).isEqualTo(30);
        assertThat(resp.revenueSeries()).hasSize(1);
        assertThat(resp.bookingStatus()).hasSize(1);
        assertThat(resp.range()).isEqualTo("weekly");
    }

    @Test
    void build_nullRange_defaultsToWeekly() {
        when(bookingClient.getStats(eq("weekly"), any())).thenReturn(null);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build(null, BEARER);

        assertThat(resp.range()).isEqualTo("weekly");
    }

    @Test
    void build_blankRange_defaultsToWeekly() {
        when(bookingClient.getStats(eq("weekly"), any())).thenReturn(null);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build("   ", BEARER);

        assertThat(resp.range()).isEqualTo("weekly");
    }

    // ── Fail-soft: downstream unavailable ───────────────────────────────────

    @Test
    void build_bookingServiceNull_overviewShowsZeros() {
        when(bookingClient.getStats(any(), any())).thenReturn(null);
        when(catalogClient.dashboardSummary(any())).thenReturn(new CatalogSummary(2, 1, 3, 0));
        when(authClient.userStats(any())).thenReturn(new UserStats(50, 5, 43, 2));
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.overview().bookingsToday()).isZero();
        assertThat(resp.overview().bookings7d()).isZero();
        assertThat(resp.overview().revenueTodayVnd()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(resp.overview().revenue7dVnd()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(resp.revenueSeries()).isEmpty();
        assertThat(resp.bookingStatus()).isEmpty();
        assertThat(resp.topServices()).isEmpty();
    }

    @Test
    void build_catalogServiceNull_servicesZero() {
        when(bookingClient.getStats(any(), any())).thenReturn(null);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.overview().servicesActive()).isZero();
        assertThat(resp.overview().servicesPendingApproval()).isZero();
        assertThat(resp.overview().totalUsers()).isZero();
        assertThat(resp.overview().partners()).isZero();
    }

    // ── Alerts ──────────────────────────────────────────────────────────────

    @Test
    void build_cancelRateAbove8_generatesWarningAlert() {
        BookingStats booking = new BookingStats(
                1, 10, BigDecimal.ZERO, BigDecimal.ZERO,
                9.5, 1.0, 1.0, 0, List.of(), List.of(), List.of());
        when(bookingClient.getStats(any(), any())).thenReturn(booking);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.alerts())
                .anyMatch(a -> "cancel-spike".equals(a.id()) && "warning".equals(a.level()));
    }

    @Test
    void build_cancelRateBelow8_noAlert() {
        BookingStats booking = new BookingStats(
                1, 10, BigDecimal.ZERO, BigDecimal.ZERO,
                7.9, 1.0, 1.0, 0, List.of(), List.of(), List.of());
        when(bookingClient.getStats(any(), any())).thenReturn(booking);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.alerts()).noneMatch(a -> "cancel-spike".equals(a.id()));
    }

    @Test
    void build_paymentFailAbove3_generatesDangerAlert() {
        BookingStats booking = new BookingStats(
                1, 10, BigDecimal.ZERO, BigDecimal.ZERO,
                1.0, 1.0, 3.5, 2, List.of(), List.of(), List.of());
        when(bookingClient.getStats(any(), any())).thenReturn(booking);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.alerts())
                .anyMatch(a -> "payment-spike".equals(a.id()) && "danger".equals(a.level()));
    }

    @Test
    void build_pendingReportsAbove10_generatesContentReportAlert() {
        when(bookingClient.getStats(any(), any())).thenReturn(null);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(11L);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.alerts())
                .anyMatch(a -> "content-report".equals(a.id()) && "warning".equals(a.level()));
    }

    // ── Action queue ─────────────────────────────────────────────────────────

    @Test
    void build_pendingServicesAndReports_actionQueueHighSeverity() {
        when(bookingClient.getStats(any(), any())).thenReturn(null);
        when(catalogClient.dashboardSummary(any())).thenReturn(new CatalogSummary(0, 4, 0, 2));
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(3L);

        DashboardResponse resp = service.build("weekly", BEARER);

        ActionQueueItem svcItem = resp.actionQueue().stream()
                .filter(i -> "svc-approve".equals(i.id())).findFirst().orElseThrow();
        assertThat(svcItem.count()).isEqualTo(6L); // 4 + 2
        assertThat(svcItem.severity()).isEqualTo("high");

        ActionQueueItem reportItem = resp.actionQueue().stream()
                .filter(i -> "reports".equals(i.id())).findFirst().orElseThrow();
        assertThat(reportItem.count()).isEqualTo(3);
        assertThat(reportItem.severity()).isEqualTo("high");
    }

    // ── Revenue conversion ────────────────────────────────────────────────────

    @Test
    void build_revenueConvertedToMillions() {
        BookingStats booking = new BookingStats(
                0, 0,
                BigDecimal.valueOf(3_500_000), BigDecimal.valueOf(20_000_000),
                0, 0, 0, 0,
                List.of(),
                List.of(new BookingRevenuePoint("Mon",
                        BigDecimal.valueOf(4_000_000),
                        BigDecimal.valueOf(2_000_000))),
                List.of());
        when(bookingClient.getStats(any(), any())).thenReturn(booking);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);

        DashboardResponse resp = service.build("weekly", BEARER);

        RevenuePoint point = resp.revenueSeries().get(0);
        assertThat(point.hotel()).isEqualTo(4L);       // 4_000_000 / 1_000_000
        assertThat(point.restaurant()).isEqualTo(2L);  // 2_000_000 / 1_000_000
        assertThat(point.total()).isEqualTo(6L);
    }

    // ── Top partners ──────────────────────────────────────────────────────────

    @Test
    void build_topPartners_aggregatedByPartnerId() {
        List<BookingTopService> topSrc = List.of(
                new BookingTopService("h1", "HOTEL", "Hotel A", 10, BigDecimal.valueOf(5_000_000)),
                new BookingTopService("h2", "HOTEL", "Hotel B", 5,  BigDecimal.valueOf(3_000_000)),
                new BookingTopService("r1", "RESTAURANT", "Rest A", 8, BigDecimal.valueOf(2_000_000)));
        BookingStats booking = new BookingStats(
                0, 0, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0, 0, 0,
                List.of(), List.of(), topSrc);

        List<PartnerRef> refs = List.of(
                new PartnerRef("h1", "HOTEL", "p1", "Partner One"),
                new PartnerRef("h2", "HOTEL", "p1", "Partner One"), // same partner
                new PartnerRef("r1", "RESTAURANT", "p2", "Partner Two"));

        when(bookingClient.getStats(any(), any())).thenReturn(booking);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);
        when(catalogClient.resolvePartners(any(), any(), any())).thenReturn(refs);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.topPartners()).hasSize(2);
        TopPartnerItem first = resp.topPartners().get(0);
        assertThat(first.name()).isEqualTo("Partner One");
        assertThat(first.services()).isEqualTo(2);           // h1 + h2
        assertThat(first.revenueM()).isEqualTo(8L);          // (5M + 3M) / 1M
    }

    @Test
    void build_resolvePartnersNull_returnsEmptyTopPartners() {
        List<BookingTopService> topSrc = List.of(
                new BookingTopService("h1", "HOTEL", "Hotel A", 10, BigDecimal.valueOf(5_000_000)));
        BookingStats booking = new BookingStats(
                0, 0, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0, 0, 0,
                List.of(), List.of(), topSrc);

        when(bookingClient.getStats(any(), any())).thenReturn(booking);
        when(catalogClient.dashboardSummary(any())).thenReturn(null);
        when(authClient.userStats(any())).thenReturn(null);
        when(planClient.pendingReports(any())).thenReturn(0L);
        when(catalogClient.resolvePartners(any(), any(), any())).thenReturn(null);

        DashboardResponse resp = service.build("weekly", BEARER);

        assertThat(resp.topPartners()).isEmpty();
    }
}
