package com.mravel.plan.config;

import com.mravel.plan.model.*;
import com.mravel.plan.repository.PlanRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

        private final PlanRepository planRepository;

        @PostConstruct
        public void seed() {
                if (planRepository.count() > 0) {
                        System.out.println(" Plans already seeded, skip.");
                        return;
                }

                // 🟢 Plan 1
                // Plan dalat = Plan.builder()
                // .title("Trip Đà Lạt chill chill")
                // .description("Lịch trình 3N2Đ: Dạo Hồ Xuân Hương – Cafe lưng chừng đồi – Săn
                // mây Đồi Chè Cầu Đất – Food tour chợ đêm…")
                // .startDate("12/11/2025")
                // .endDate("14/11/2025")
                // .days(3)
                // .visibility(Visibility.public_)
                // .views(321L)
                // .author(new AuthorSummary("u1", "Lan Phạm",
                // "https://i.pravatar.cc/80?img=11"))
                // .createdAt(Instant.now().minusSeconds(7200))
                // .images(List.of(
                // "https://images.unsplash.com/photo-1542038784456-1ea8e935640e?q=80&w=1200&auto=format&fit=crop",
                // "https://images.unsplash.com/photo-1519681393784-d120267933ba?q=80&w=1200&auto=format&fit=crop",
                // "https://images.unsplash.com/photo-1491553895911-0055eca6402d?q=80&w=1200&auto=format&fit=crop"
                // ))
                // .destinations(List.of(
                // new Destination("Hồ Xuân Hương", null, null),
                // new Destination("Langbiang", null, null),
                // new Destination("Cầu Đất", null, null)
                // ))
                // .build();

                // // Thêm vài người react mẫu
                // dalat.setReactions(List.of(
                // PlanReaction.builder().plan(dalat).userId("u1").userName("Luân").userAvatar("https://i.pravatar.cc/80?img=5").type("like").createdAt(Instant.now().minusSeconds(10000)).build(),
                // PlanReaction.builder().plan(dalat).userId("u2").userName("Nhật").userAvatar("https://i.pravatar.cc/80?img=6").type("like").createdAt(Instant.now().minusSeconds(9000)).build(),
                // PlanReaction.builder().plan(dalat).userId("u3").userName("Mẫn").userAvatar("https://i.pravatar.cc/80?img=7").type("love").createdAt(Instant.now().minusSeconds(8000)).build()
                // ));

                // // 🟢 Plan 2
                // Plan hue = Plan.builder()
                // .title("Huế – Nét trầm cổ kính")
                // .description("2N1Đ tham quan Kinh Thành, Lăng Tự Đức, thưởng thức ẩm thực Huế
                // về đêm, du thuyền sông Hương.")
                // .startDate("18/12/2025")
                // .endDate("19/12/2025")
                // .days(2)
                // .visibility(Visibility.friends)
                // .views(107L)
                // .author(new AuthorSummary("u3", "Khánh Dương",
                // "https://i.pravatar.cc/80?img=9"))
                // .createdAt(Instant.now().minusSeconds(20 * 60 * 60))
                // .images(List.of(
                // "https://images.unsplash.com/photo-1558980664-10ea2927a93e?q=80&w=1200&auto=format&fit=crop",
                // "https://images.unsplash.com/photo-1469474968028-56623f02e42e?q=80&w=1200&auto=format&fit=crop"
                // ))
                // .destinations(List.of(
                // new Destination("Kinh thành Huế", null, null),
                // new Destination("Lăng Tự Đức", null, null)
                // ))
                // .build();

                // hue.setReactions(List.of(
                // PlanReaction.builder().plan(hue).userId("u4").userName("Trang").userAvatar("https://i.pravatar.cc/80?img=8").type("like").createdAt(Instant.now().minusSeconds(3000)).build()
                // ));

                // planRepository.saveAll(List.of(dalat, hue));

        }
}
