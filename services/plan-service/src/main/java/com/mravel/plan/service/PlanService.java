package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.CreatePlanRequest;
import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.dto.board.PlanRecentView;
import com.mravel.plan.model.ExtraCost;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanCard;
import com.mravel.plan.model.PlanComment;
import com.mravel.plan.model.PlanList;
import com.mravel.plan.model.PlanListType;
import com.mravel.plan.model.PlanReaction;
import com.mravel.plan.model.SplitType;
import com.mravel.plan.model.Visibility;
import com.mravel.plan.repository.PlanCardRepository;
import com.mravel.plan.repository.PlanReactionRepository;
import com.mravel.plan.repository.PlanRecentViewRepository;
import com.mravel.plan.repository.PlanRepository;
import com.mravel.plan.repository.PlanListRepository;
import com.mravel.plan.repository.PlanMemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

        private final PlanRepository planRepository;
        private final PlanReactionRepository reactionRepository;
        private final PlanMapper planMapper;
        private final PlanPermissionService permissionService;
        private final UserProfileClient userProfileClient;
        private final PlanListRepository listRepository;
        private final PlanCardRepository cardRepository;
        private final PlanMemberRepository memberRepository;
        private final PlanRecentViewRepository recentViewRepository;
        // EntityManager để truy vấn trực tiếp cho comment
        @PersistenceContext
        private EntityManager entityManager;

        public Page<PlanFeedItem> getFeed(int page, int size, Long viewerId) {
                PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

                Page<Plan> pageResult;

                if (viewerId == null) {
                        pageResult = planRepository.findByVisibility(Visibility.PUBLIC, pageable);
                } else {
                        List<Long> memberPlanIds = memberRepository.findPlanIdsByUserId(viewerId);
                        if (memberPlanIds.isEmpty()) {
                                memberPlanIds = Collections.singletonList(-1L); // tránh IN ()
                        }
                        pageResult = planRepository.findVisibleForUser(viewerId, memberPlanIds, pageable);
                }

                List<PlanFeedItem> content = pageResult.getContent().stream()
                                .map(planMapper::toFeedItem)
                                .toList();

                return new PageImpl<>(content, pageable, pageResult.getTotalElements());
        }

        public PlanFeedItem getById(Long id, Long viewerId, boolean isFriend) {
                Plan plan = planRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                if (!permissionService.canView(id, viewerId, isFriend))
                        throw new RuntimeException("You don't have permission to view this plan.");

                return planMapper.toFeedItem(plan);
        }

        @Transactional
        public PlanFeedItem createPlan(CreatePlanRequest req, Long userId) {

                if (req.getStartDate() == null || req.getEndDate() == null)
                        throw new RuntimeException("Start and end date are required");

                if (req.getEndDate().isBefore(req.getStartDate()))
                        throw new RuntimeException("endDate must be >= startDate");

                int days = (int) (req.getEndDate().toEpochDay() - req.getStartDate().toEpochDay() + 1);

                Plan plan = Plan.builder()
                                .title(req.getTitle())
                                .description(req.getDescription())
                                .visibility(req.getVisibility() != null ? req.getVisibility() : Visibility.PRIVATE)
                                .authorId(userId)
                                .startDate(req.getStartDate())
                                .endDate(req.getEndDate())
                                .days(days)
                                .createdAt(Instant.now())
                                .views(0L)

                                .budgetCurrency(req.getBudgetCurrency() != null ? req.getBudgetCurrency() : "VND")
                                .budgetTotal(req.getBudgetTotal())
                                .budgetPerPerson(req.getBudgetPerPerson())

                                .totalEstimatedCost(0L)
                                .totalActualCost(0L)

                                .build();

                planRepository.save(plan);

                // tạo danh sách ngày
                String defaultTitle = "Danh sách hoạt động";

                for (int i = 0; i < days; i++) {
                        LocalDate date = req.getStartDate().plusDays(i);

                        listRepository.save(
                                        PlanList.builder()
                                                        .plan(plan)
                                                        .type(PlanListType.DAY)
                                                        .position(i)
                                                        .title(defaultTitle)
                                                        .dayDate(date)
                                                        .build());
                }

                // trash list
                listRepository.save(
                                PlanList.builder()
                                                .plan(plan)
                                                .type(PlanListType.TRASH)
                                                .position(days)
                                                .title("Trash")
                                                .dayDate(null)
                                                .build());

                permissionService.ensureOwner(plan.getId(), userId);

                return planMapper.toFeedItem(plan);
        }

        @Transactional
        public PlanFeedItem updateVisibility(Long planId, Long userId, Visibility visibility) {
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                if (!userId.equals(plan.getAuthorId()))
                        throw new RuntimeException("Only the owner can change visibility.");

                plan.setVisibility(visibility);
                planRepository.save(plan);
                return planMapper.toFeedItem(plan);
        }

        @Transactional
        public PlanFeedItem copyPublicPlan(Long planId, Long userId) {

                Plan source = planRepository.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                if (source.getVisibility() != Visibility.PUBLIC)
                        throw new RuntimeException("Only public plans can be copied.");

                // Tạo plan mới
                Plan copy = Plan.builder()
                                .title(source.getTitle() + " (Copy)")
                                .description(source.getDescription())
                                .visibility(Visibility.PRIVATE)
                                .authorId(userId)
                                .startDate(source.getStartDate())
                                .endDate(source.getEndDate())
                                .days(source.getDays())
                                .budgetCurrency(source.getBudgetCurrency())
                                .budgetTotal(source.getBudgetTotal())
                                .budgetPerPerson(source.getBudgetPerPerson())
                                .createdAt(Instant.now())
                                .views(0L)
                                // Tổng chi phí sẽ tính lại sau hoặc để 0
                                .totalEstimatedCost(0L)
                                .totalActualCost(0L)
                                .images(new ArrayList<>(source.getImages()))
                                .destinations(new ArrayList<>(source.getDestinations()))
                                .build();

                planRepository.save(copy);
                permissionService.ensureOwner(copy.getId(), userId);

                List<PlanList> sourceLists = listRepository.findByPlanIdOrderByPositionAsc(source.getId());

                List<PlanList> sourceDays = sourceLists.stream()
                                .filter(l -> l.getType() == PlanListType.DAY)
                                .sorted(Comparator.comparingInt(PlanList::getPosition))
                                .toList();

                Map<Long, PlanList> oldToNewList = new HashMap<>();

                for (int i = 0; i < sourceDays.size(); i++) {
                        PlanList src = sourceDays.get(i);

                        PlanList dst = PlanList.builder()
                                        .plan(copy)
                                        .type(PlanListType.DAY)
                                        .position(i)
                                        .title(src.getTitle())
                                        .dayDate(copy.getStartDate().plusDays(i))
                                        .build();

                        listRepository.save(dst);
                        oldToNewList.put(src.getId(), dst);

                        // copy cards
                        List<PlanCard> srcCards = cardRepository.findByListIdOrderByPositionAsc(src.getId());
                        for (int c = 0; c < srcCards.size(); c++) {

                                PlanCard oc = srcCards.get(c);

                                PlanCard nc = PlanCard.builder()
                                                .list(dst)
                                                .text(oc.getText())
                                                .description(oc.getDescription())
                                                .startTime(oc.getStartTime())
                                                .endTime(oc.getEndTime())
                                                .durationMinutes(oc.getDurationMinutes())
                                                .done(false)
                                                .position(c)

                                                .activityType(oc.getActivityType())
                                                .activityDataJson(oc.getActivityDataJson())

                                                .currencyCode(oc.getCurrencyCode())
                                                .estimatedCost(oc.getEstimatedCost())
                                                // Reset actual về null, để tính lại sau
                                                .actualCost(null)
                                                .actualManual(false)
                                                .budgetAmount(oc.getBudgetAmount())
                                                .participantCount(null)

                                                // reset chia tiền
                                                .splitType(SplitType.NONE)
                                                .includePayerInSplit(true)
                                                .payerId(null)
                                                .build();

                                oc.getExtraCosts().forEach(e -> nc.getExtraCosts().add(
                                                ExtraCost.builder()
                                                                .reason(e.getReason())
                                                                .type(e.getType())
                                                                .estimatedAmount(e.getEstimatedAmount())
                                                                .actualAmount(e.getActualAmount())
                                                                .build()));

                                cardRepository.save(nc);
                        }
                }

                // trash mới
                listRepository.save(
                                PlanList.builder()
                                                .plan(copy)
                                                .type(PlanListType.TRASH)
                                                .position(sourceDays.size())
                                                .title("Trash")
                                                .build());

                return planMapper.toFeedItem(copy);
        }

        @Transactional
        public PlanFeedItem.Comment addComment(
                        Long planId,
                        Long userId,
                        String text,
                        Long parentId) {

                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                PlanComment parent = null;
                if (parentId != null) {
                        parent = entityManager.find(PlanComment.class, parentId);
                        if (parent == null)
                                throw new RuntimeException("Parent comment not found");
                }

                PlanComment comment = PlanComment.builder()
                                .plan(plan)
                                .parent(parent)
                                .userId(userId)
                                .text(text)
                                .createdAt(Instant.now())
                                .build();

                entityManager.persist(comment);
                entityManager.flush();

                // cache profile
                UserProfileResponse profile = null;
                try {
                        profile = userProfileClient.getUserById(userId);
                } catch (Exception ignored) {
                }

                return PlanFeedItem.Comment.builder()
                                .id(comment.getId())
                                .parentId(parentId)
                                .text(comment.getText())
                                .createdAt(comment.getCreatedAt())
                                .user(PlanFeedItem.Comment.User.builder()
                                                .id(userId)
                                                .name(profile != null ? profile.getFullname() : null)
                                                .avatar(profile != null ? profile.getAvatar() : null)
                                                .build())
                                .build();
        }

        @Transactional
        public PlanFeedItem react(Long planId, String key, Long userId) {
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                // Gọi sang user-service lấy thông tin user
                UserProfileResponse user = userProfileClient.getUserById(userId);
                if (user == null)
                        throw new RuntimeException("User not found: " + userId);

                Optional<PlanReaction> existingOpt = reactionRepository.findByPlanIdAndUserId(planId, userId);

                if (existingOpt.isPresent()) {
                        PlanReaction existing = existingOpt.get();
                        plan.getReactions().remove(existing);
                        reactionRepository.delete(existing);
                } else {
                        PlanReaction newReact = PlanReaction.builder()
                                        .plan(plan)
                                        .userId(userId)
                                        .type(key)
                                        .createdAt(Instant.now())
                                        .build();

                        plan.getReactions().add(newReact);
                        reactionRepository.save(newReact);
                }

                planRepository.save(plan);
                return planMapper.toFeedItem(plan);
        }

        public void increaseView(Long planId) {
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                plan.setViews(Optional.ofNullable(plan.getViews()).orElse(0L) + 1);
                planRepository.save(plan);
        }

        public Page<PlanFeedItem> getUserPlans(Long ownerId, Long viewerId) {
                return getUserPlans(ownerId, viewerId, false);
        }

        public Page<PlanFeedItem> getUserPlans(Long ownerId, Long viewerId, boolean isFriend) {

                List<Long> memberPlanIds = memberRepository.findPlanIdsByUserId(viewerId);
                if (memberPlanIds.isEmpty())
                        memberPlanIds = List.of(-1L); // tránh IN ()

                Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdAt"));

                Page<Plan> plans;

                // Nếu xem kế hoạch của chính mình → xem tất cả
                if (Objects.equals(ownerId, viewerId)) {
                        plans = planRepository.findByAuthorId(ownerId, pageable);
                } else {
                        plans = planRepository.findAllPlansOfUserWithVisibility(
                                        ownerId,
                                        isFriend,
                                        memberPlanIds,
                                        pageable);
                }

                List<PlanFeedItem> items = plans.getContent().stream()
                                .map(planMapper::toFeedItem)
                                .toList();

                return new PageImpl<>(items, pageable, plans.getTotalElements());
        }

        @Transactional
        public List<PlanFeedItem> getRecentPlans(Long viewerId) {
                if (viewerId == null) {
                        return List.of();
                }

                // Lấy top 20 lịch trình vừa xem gần đây nhất
                List<PlanRecentView> views = recentViewRepository
                                .findTop20ByUserIdOrderByViewedAtDesc(viewerId);

                if (views.isEmpty())
                        return List.of();

                List<Long> planIds = views.stream()
                                .map(PlanRecentView::getPlanId)
                                .toList();

                // Load plan
                List<Plan> plans = planRepository.findAllById(planIds);

                // Map id -> Plan để giữ đúng thứ tự theo viewedAt
                Map<Long, Plan> planMap = plans.stream()
                                .collect(Collectors.toMap(Plan::getId, p -> p));

                List<PlanFeedItem> result = new ArrayList<>();

                for (PlanRecentView v : views) {
                        Plan p = planMap.get(v.getPlanId());
                        if (p == null)
                                continue;

                        // Chỉ thêm nếu user hiện tại vẫn xem được (tránh case visibility đổi)
                        if (!permissionService.canView(p.getId(), viewerId, false)) {
                                continue;
                        }

                        result.add(planMapper.toFeedItem(p));
                }

                return result;
        }

        @Transactional
        public void removeRecentPlan(Long planId, Long viewerId) {
                if (viewerId == null)
                        return;
                recentViewRepository.deleteByUserIdAndPlanId(viewerId, planId);
        }

}
