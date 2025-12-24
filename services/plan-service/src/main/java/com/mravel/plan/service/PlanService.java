package com.mravel.plan.service;

import com.mravel.common.response.RelationshipType;
import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.FriendClient;
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
import com.mravel.plan.repository.PlanInviteTokenRepository;
import com.mravel.plan.repository.PlanReactionRepository;
import com.mravel.plan.repository.PlanRecentViewRepository;
import com.mravel.plan.repository.PlanRepository;
import com.mravel.plan.security.CurrentUserService;
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
        private final PlanInviteTokenRepository inviteTokenRepository;
        private final CurrentUserService currentUser;
        private final FriendClient friendClient;
        private final PlanNotificationService planNotificationService;

        // EntityManager để truy vấn trực tiếp cho comment
        @PersistenceContext
        private EntityManager entityManager;

        public Page<PlanFeedItem> searchFeed(int page, int size, Long viewerId, String authorizationHeader, String q) {
                PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

                List<Long> memberPlanIds = memberRepository.findPlanIdsByUserId(viewerId);
                if (memberPlanIds.isEmpty())
                        memberPlanIds = List.of(-1L);

                List<Long> friendIds = List.of();
                if (authorizationHeader != null) {
                        try {
                                friendIds = friendClient.getFriendIds(authorizationHeader);
                        } catch (Exception ignored) {
                        }
                }
                if (friendIds.isEmpty())
                        friendIds = List.of(-1L);

                String keyword = normalizeSearch(q);

                Page<Plan> pageResult = planRepository.searchFeedForUser(viewerId, memberPlanIds, friendIds, keyword,
                                pageable);

                List<PlanFeedItem> content = pageResult.getContent().stream()
                                .map(planMapper::toFeedItem)
                                .toList();

                return new PageImpl<>(content, pageable, pageResult.getTotalElements());
        }

        public List<UserProfileResponse> searchUsersFromUserService(String authorizationHeader, String q, int limit) {
                String keyword = normalizeSearch(q);
                if (keyword.isBlank())
                        return List.of();
                try {
                        return userProfileClient.searchUsers(authorizationHeader, q, limit);
                } catch (Exception e) {
                        return List.of();
                }
        }

        private String normalizeSearch(String q) {
                if (q == null)
                        return "";
                String s = q.trim();
                if (s.startsWith("@"))
                        s = s.substring(1).trim();
                return s;
        }

        public Page<PlanFeedItem> getFeed(int page, int size, Long viewerId, String authorizationHeader) {
                PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

                List<Long> memberPlanIds = memberRepository.findPlanIdsByUserId(viewerId);
                if (memberPlanIds.isEmpty())
                        memberPlanIds = List.of(-1L);

                List<Long> friendIds = List.of();
                if (authorizationHeader != null) {
                        try {
                                friendIds = friendClient.getFriendIds(authorizationHeader);
                        } catch (Exception ignored) {
                        }
                }
                if (friendIds.isEmpty())
                        friendIds = List.of(-1L);

                Page<Plan> pageResult = planRepository.findFeedForUser(viewerId, memberPlanIds, friendIds, pageable);

                List<PlanFeedItem> content = pageResult.getContent().stream()
                                .map(planMapper::toFeedItem)
                                .toList();

                return new PageImpl<>(content, pageable, pageResult.getTotalElements());
        }

        public PlanFeedItem getById(Long id, String authorizationHeader) {

                Long viewerId = currentUser.getId();

                Plan plan = planRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                RelationshipType relationship = friendClient.getRelationship(
                                authorizationHeader,
                                plan.getAuthorId());

                boolean isFriend = relationship == RelationshipType.FRIEND;

                if (!permissionService.canView(id, viewerId, isFriend)) {
                        throw new RuntimeException("You don't have permission to view this plan.");
                }

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
        public PlanFeedItem copyPlan(Long planId, Long userId) {

                Plan source = planRepository.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                boolean isMember = memberRepository.existsByPlanIdAndUserId(planId, userId);

                if (source.getVisibility() != Visibility.PUBLIC && !isMember) {
                        throw new RuntimeException("You don't have permission to copy this plan.");
                }
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

                Long commentId = comment.getId();
                if (parent == null) {
                        // comment root -> notify owner plan
                        planNotificationService.notifyPlanComment(
                                        userId,
                                        plan.getAuthorId(),
                                        planId,
                                        commentId);
                } else {
                        // reply -> notify chủ comment cha
                        planNotificationService.notifyPlanReplyComment(
                                        userId,
                                        parent.getUserId(),
                                        planId,
                                        parent.getId(),
                                        commentId);
                }
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
                boolean created = false;
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
                        created = true;
                }

                planRepository.save(plan);
                if (created) {
                        Long ownerId = plan.getAuthorId();
                        planNotificationService.notifyPlanReact(userId, ownerId, planId, key);
                }

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
        public List<PlanFeedItem> getRecentPlans(Long viewerId, String authorizationHeader) {
                if (viewerId == null)
                        return List.of();

                List<PlanRecentView> views = recentViewRepository.findTop20ByUserIdOrderByViewedAtDesc(viewerId);

                if (views.isEmpty())
                        return List.of();

                // DEDUPE theo planId, giữ cái viewedAt mới nhất
                LinkedHashMap<Long, PlanRecentView> uniq = new LinkedHashMap<>();
                for (PlanRecentView v : views) {
                        uniq.putIfAbsent(v.getPlanId(), v);
                }
                List<Long> planIds = new ArrayList<>(uniq.keySet());

                List<Plan> plans = planRepository.findAllById(planIds);
                Map<Long, Plan> planMap = plans.stream()
                                .collect(Collectors.toMap(Plan::getId, p -> p));

                List<PlanFeedItem> result = new ArrayList<>();

                for (Long planId : planIds) {
                        Plan p = planMap.get(planId);
                        if (p == null)
                                continue;

                        boolean isFriend = false;

                        // chỉ cần check friend nếu plan FRIENDS
                        if (p.getVisibility() == Visibility.FRIENDS && authorizationHeader != null) {
                                try {
                                        RelationshipType relationship = friendClient
                                                        .getRelationship(authorizationHeader, p.getAuthorId());
                                        isFriend = (relationship == RelationshipType.FRIEND);
                                } catch (Exception ignored) {
                                }
                        }

                        if (!permissionService.canView(p.getId(), viewerId, isFriend))
                                continue;

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

        @Transactional
        public void deletePlan(Long planId, Long userId) {
                inviteTokenRepository.deleteByPlanId(planId);
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Plan not found"));

                if (!Objects.equals(plan.getAuthorId(), userId)) {
                        throw new RuntimeException("Only the owner can delete this plan.");
                }

                recentViewRepository.deleteByPlanId(planId);

                planRepository.delete(plan);

                planRepository.flush();
        }

}
