package com.mravel.plan.service;

import com.mravel.common.response.RelationshipType;
import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.FriendClient;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.document.PlanDocument;
import com.mravel.plan.dto.CreatePlanRequest;
import com.mravel.plan.dto.PlanAdminStatsDtos;
import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.dto.VisibilityContext;
import com.mravel.plan.kafka.PlanIndexPublisher;
import com.mravel.plan.dto.board.PlanRecentView;
import com.mravel.plan.exception.BadRequestException;
import com.mravel.plan.exception.ForbiddenException;
import com.mravel.plan.exception.NotFoundException;
import com.mravel.plan.model.Destination;
import com.mravel.plan.model.ExtraCost;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanCard;
import com.mravel.plan.model.PlanComment;
import com.mravel.plan.model.PlanHidden;
import com.mravel.plan.model.PlanList;
import com.mravel.plan.model.PlanListType;
import com.mravel.plan.dto.CommentReactionResponse;
import com.mravel.plan.model.PlanCommentReaction;
import com.mravel.plan.model.PlanReaction;
import com.mravel.plan.model.PlanReport;
import com.mravel.plan.model.SplitType;
import com.mravel.plan.model.Visibility;
import com.mravel.plan.repository.PlanCardRepository;
import com.mravel.plan.repository.PlanCommentReactionRepository;
import com.mravel.plan.repository.PlanInviteTokenRepository;
import com.mravel.plan.repository.PlanEsRepository;
import com.mravel.plan.repository.PlanReactionRepository;
import com.mravel.plan.repository.PlanRecentViewRepository;
import com.mravel.plan.repository.PlanHiddenRepository;
import com.mravel.plan.repository.PlanReportRepository;
import com.mravel.plan.repository.PlanCommentRepository;
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
        private final PlanCommentReactionRepository commentReactionRepository;
        private final PlanMapper planMapper;
        private final PlanPermissionService permissionService;
        private final UserProfileClient userProfileClient;
        private final PlanListRepository listRepository;
        private final PlanCardRepository cardRepository;
        private final PlanMemberRepository memberRepository;
        private final PlanRecentViewRepository recentViewRepository;
        private final PlanHiddenRepository planHiddenRepository;
        private final PlanReportRepository planReportRepository;
        private final PlanCommentRepository planCommentRepository;
        private final PlanInviteTokenRepository inviteTokenRepository;
        private final CurrentUserService currentUser;
        private final FriendClient friendClient;
        private final PlanViewService planViewService;
        private final PlanNotificationService planNotificationService;
        private final PlanIndexPublisher planIndexPublisher;
        private final PlanEsRepository planEsRepository;

        // EntityManager để truy vấn trực tiếp cho comment
        @PersistenceContext
        private EntityManager entityManager;

        // ===== Helpers: block / hidden / visibility-context =====

        /** Tránh IN ()/NOT IN () lỗi: list rỗng -> sentinel [-1]. */
        private List<Long> sentinel(List<Long> ids) {
                return (ids == null || ids.isEmpty()) ? List.of(-1L) : ids;
        }

        /** Lấy {friendIds, blockedIds} 1 lần (đã cache ở FriendClient). Fail-open. */
        private VisibilityContext visibilityContextOf(Long viewerId) {
                if (viewerId == null)
                        return new VisibilityContext(List.of(), List.of());
                try {
                        VisibilityContext ctx = friendClient.getVisibilityContext(viewerId);
                        return ctx != null ? ctx : new VisibilityContext(List.of(), List.of());
                } catch (Exception e) {
                        return new VisibilityContext(List.of(), List.of());
                }
        }

        private List<Long> hiddenPlanIdsOf(Long viewerId) {
                if (viewerId == null)
                        return List.of();
                try {
                        return planHiddenRepository.findPlanIdsByUserId(viewerId);
                } catch (Exception e) {
                        return List.of();
                }
        }

        public Page<PlanFeedItem> searchFeed(int page, int size, Long viewerId, String authorizationHeader, String q) {
                PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

                List<Long> memberPlanIds = sentinel(memberRepository.findPlanIdsByUserId(viewerId));

                VisibilityContext ctx = visibilityContextOf(viewerId);
                List<Long> friendIds = sentinel(ctx.friendIds());
                List<Long> blockedIds = sentinel(ctx.blockedIds());
                List<Long> hiddenPlanIds = sentinel(hiddenPlanIdsOf(viewerId));
                Set<Long> blockedSet = new HashSet<>(ctx.blockedIds());

                String keyword = normalizeSearch(q);

                Page<Plan> pageResult = planRepository.searchFeedForUser(viewerId, memberPlanIds, friendIds,
                                blockedIds, hiddenPlanIds, keyword, pageable);

                List<PlanFeedItem> content = pageResult.getContent().stream()
                                .map(p -> planMapper.toFeedItem(p, viewerId, blockedSet))
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

                List<Long> memberPlanIds = sentinel(memberRepository.findPlanIdsByUserId(viewerId));

                // 1 lần gọi user-service (đã cache) lấy cả friendIds + blockedIds
                VisibilityContext ctx = visibilityContextOf(viewerId);
                List<Long> friendIds = sentinel(ctx.friendIds());
                List<Long> blockedIds = sentinel(ctx.blockedIds());
                List<Long> hiddenPlanIds = sentinel(hiddenPlanIdsOf(viewerId));
                Set<Long> blockedSet = new HashSet<>(ctx.blockedIds());

                Page<Plan> pageResult = planRepository.findFeedForUser(viewerId, memberPlanIds, friendIds,
                                blockedIds, hiddenPlanIds, pageable);

                List<PlanFeedItem> content = pageResult.getContent().stream()
                                .map(p -> planMapper.toFeedItem(p, viewerId, blockedSet))
                                .toList();

                return new PageImpl<>(content, pageable, pageResult.getTotalElements());
        }

        public PlanFeedItem getById(Long id, String authorizationHeader) {

                Long viewerId = currentUser.getId();

                Plan plan = planRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Plan not found"));

                VisibilityContext ctx = visibilityContextOf(viewerId);
                Set<Long> blockedSet = new HashSet<>(ctx.blockedIds());

                boolean isOwner = Objects.equals(plan.getAuthorId(), viewerId);
                boolean isMember = viewerId != null && memberRepository.existsByPlanIdAndUserId(id, viewerId);

                // Chặn: bài của người bị chặn KHÔNG cho xem qua link trực tiếp,
                // TRỪ khi viewer là chủ/thành viên của plan (giữ plan cộng tác chung).
                if (blockedSet.contains(plan.getAuthorId()) && !isOwner && !isMember) {
                        throw new ForbiddenException("This content is not available.");
                }

                // isFriend suy từ visibility-context phía server (KHÔNG tin client)
                boolean isFriend = ctx.friendIds().contains(plan.getAuthorId());

                if (!permissionService.canView(id, viewerId, isFriend)) {
                        throw new ForbiddenException("You don't have permission to view this plan.");
                }

                // Tính 1 lượt xem chuẩn (mỗi user 1 lần, không tính tác giả).
                // Bọc try/catch để lỗi đếm view KHÔNG làm hỏng việc đọc chi tiết.
                try {
                        if (planViewService.recordView(id, viewerId, plan.getAuthorId())) {
                                plan.setViews(Optional.ofNullable(plan.getViews()).orElse(0L) + 1);
                        }
                } catch (Exception ignored) {
                }

                return planMapper.toFeedItem(plan, viewerId, blockedSet);
        }

        @Transactional
        public PlanFeedItem createPlan(CreatePlanRequest req, Long userId) {

                if (req.getStartDate() == null || req.getEndDate() == null)
                        throw new BadRequestException("Start and end date are required");

                if (req.getEndDate().isBefore(req.getStartDate()))
                        throw new BadRequestException("endDate must be >= startDate");

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
                planIndexPublisher.publishUpsert(plan.getId());

                return planMapper.toFeedItem(plan);
        }

        @Transactional
        public PlanFeedItem updateVisibility(Long planId, Long userId, Visibility visibility) {
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new NotFoundException("Plan not found"));

                if (!userId.equals(plan.getAuthorId()))
                        throw new ForbiddenException("Only the owner can change visibility.");

                // Takedown bất khả hồi: bài bị admin gỡ (khóa) -> chủ KHÔNG thể đổi hiển thị (bật lại)
                if (Boolean.TRUE.equals(plan.getAdminLocked()))
                        throw new ForbiddenException("This plan has been taken down by an admin and cannot be changed.");

                plan.setVisibility(visibility);
                planRepository.save(plan);
                planIndexPublisher.publishUpsert(planId);
                return planMapper.toFeedItem(plan);
        }

        @Transactional
        public PlanFeedItem copyPlan(Long planId, Long userId, String authorizationHeader) {

                Plan source = planRepository.findById(planId)
                                .orElseThrow(() -> new NotFoundException("Plan not found"));

                // Takedown bất khả hồi: bài đã bị admin gỡ (khóa) KHÔNG được sao chép
                // (chặn đường né takedown bằng cách copy thành plan PUBLIC mới).
                if (Boolean.TRUE.equals(source.getAdminLocked())) {
                        throw new ForbiddenException("This plan has been taken down and cannot be copied.");
                }

                boolean isMember = memberRepository.existsByPlanIdAndUserId(planId, userId);

                VisibilityContext ctx = visibilityContextOf(userId);

                // Chặn: không cho copy bài của người bị chặn (trừ khi là member của plan chung)
                if (ctx.blockedIds().contains(source.getAuthorId()) && !isMember
                                && !Objects.equals(source.getAuthorId(), userId)) {
                        throw new ForbiddenException("This content is not available.");
                }

                boolean isFriend = ctx.friendIds().contains(source.getAuthorId());

                // CÁCH B: Ai xem được thì copy được
                // - PUBLIC: ai cũng xem/copy
                // - FRIENDS: bạn bè xem/copy
                // - PRIVATE: chỉ member/owner xem/copy
                if (!permissionService.canView(planId, userId, isFriend) && !isMember) {
                        throw new ForbiddenException("You don't have permission to copy this plan.");
                }

                // TẠO PLAN MỚI
                Plan copy = Plan.builder()
                                .title(source.getTitle() + " (Copy)")
                                .description(source.getDescription())
                                .visibility(Visibility.PRIVATE) // copy về PRIVATE
                                .authorId(userId)
                                .startDate(source.getStartDate())
                                .endDate(source.getEndDate())
                                .days(source.getDays())

                                .budgetCurrency(source.getBudgetCurrency())
                                .budgetTotal(source.getBudgetTotal())
                                .budgetPerPerson(source.getBudgetPerPerson())

                                .createdAt(Instant.now())
                                .views(0L)

                                .totalEstimatedCost(0L)
                                .totalActualCost(0L)

                                .images(source.getImages() != null ? new ArrayList<>(source.getImages())
                                                : new ArrayList<>())
                                .destinations(source.getDestinations() != null
                                                ? new ArrayList<>(source.getDestinations())
                                                : new ArrayList<>())
                                .build();

                planRepository.save(copy);
                permissionService.ensureOwner(copy.getId(), userId);

                // COPY LISTS (DAY) + CARDS
                List<PlanList> sourceLists = listRepository.findByPlanIdOrderByPositionAsc(source.getId());

                List<PlanList> sourceDays = sourceLists.stream()
                                .filter(l -> l.getType() == PlanListType.DAY)
                                .sorted(Comparator.comparingInt(PlanList::getPosition))
                                .toList();

                for (int i = 0; i < sourceDays.size(); i++) {
                        PlanList srcList = sourceDays.get(i);

                        PlanList dstList = PlanList.builder()
                                        .plan(copy)
                                        .type(PlanListType.DAY)
                                        .position(i)
                                        .title(srcList.getTitle())
                                        .dayDate(copy.getStartDate().plusDays(i))
                                        .build();

                        // save(...) can return a managed instance different from the transient input
                        // (especially when Spring Data routes to merge). Always use returned entity.
                        PlanList savedDstList = listRepository.save(dstList);

                        // copy cards
                        List<PlanCard> srcCards = cardRepository.findByListIdOrderByPositionAsc(srcList.getId());
                        for (int c = 0; c < srcCards.size(); c++) {

                                PlanCard oc = srcCards.get(c);

                                PlanCard nc = PlanCard.builder()
                                                .list(savedDstList)
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

                                                // reset actual để người copy tự nhập lại
                                                .actualCost(null)
                                                .actualManual(false)

                                                .budgetAmount(oc.getBudgetAmount())
                                                .participantCount(null)

                                                // reset chia tiền
                                                .splitType(SplitType.NONE)
                                                .includePayerInSplit(true)
                                                .payerId(null)
                                                .build();

                                // copy extraCosts (giữ nguyên như bạn đang làm)
                                if (oc.getExtraCosts() != null) {
                                        oc.getExtraCosts().forEach(e -> nc.getExtraCosts().add(
                                                        ExtraCost.builder()
                                                                        .reason(e.getReason())
                                                                        .type(e.getType())
                                                                        .estimatedAmount(e.getEstimatedAmount())
                                                                        .actualAmount(e.getActualAmount())
                                                                        .build()));
                                }

                                cardRepository.save(nc);
                        }
                }

                // TRASH LIST
                listRepository.save(
                                PlanList.builder()
                                                .plan(copy)
                                                .type(PlanListType.TRASH)
                                                .position(sourceDays.size())
                                                .title("Trash")
                                                .dayDate(null)
                                                .build());

                planIndexPublisher.publishUpsert(copy.getId());

                return planMapper.toFeedItem(copy);
        }

        @Transactional
        public PlanFeedItem.Comment addComment(
                        Long planId,
                        Long userId,
                        String text,
                        Long parentId) {

                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new NotFoundException("Plan not found"));

                PlanComment parent = null;
                if (parentId != null) {
                        parent = entityManager.find(PlanComment.class, parentId);
                        if (parent == null)
                                throw new NotFoundException("Parent comment not found");
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
                                .orElseThrow(() -> new NotFoundException("Plan not found"));

                // Gọi sang user-service lấy thông tin user
                UserProfileResponse user = userProfileClient.getUserById(userId);
                if (user == null)
                        throw new NotFoundException("User not found: " + userId);

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

        @Transactional
        public CommentReactionResponse reactComment(Long commentId, String type, Long userId) {
                PlanComment comment = entityManager.find(PlanComment.class, commentId);
                if (comment == null)
                        throw new NotFoundException("Comment not found");

                Optional<PlanCommentReaction> existingOpt =
                        commentReactionRepository.findByComment_IdAndUserId(commentId, userId);

                boolean created = false;
                if (existingOpt.isPresent()) {
                        PlanCommentReaction existing = existingOpt.get();
                        if (existing.getType().equals(type)) {
                                // Same type → toggle off
                                commentReactionRepository.delete(existing);
                        } else {
                                // Different type → change reaction
                                existing.setType(type);
                                commentReactionRepository.save(existing);
                                created = true;
                        }
                } else {
                        PlanCommentReaction newReaction = PlanCommentReaction.builder()
                                        .comment(comment)
                                        .userId(userId)
                                        .type(type)
                                        .createdAt(Instant.now())
                                        .build();
                        commentReactionRepository.save(newReaction);
                        created = true;
                }

                if (created) {
                        planNotificationService.notifyCommentReact(
                                        userId,
                                        comment.getUserId(),
                                        comment.getPlan().getId(),
                                        commentId,
                                        type);
                }

                // Build fresh summary
                List<PlanCommentReaction> all = commentReactionRepository.findByComment_Id(commentId);
                Map<String, Long> summary = all.stream()
                                .collect(Collectors.groupingBy(PlanCommentReaction::getType, Collectors.counting()));
                String myReaction = all.stream()
                                .filter(r -> userId.equals(r.getUserId()))
                                .findFirst()
                                .map(PlanCommentReaction::getType)
                                .orElse(null);

                List<PlanFeedItem.ReactionUser> reactionUsers = all.stream()
                                .map(r -> {
                                        UserProfileResponse profile = null;
                                        try {
                                                profile = userProfileClient.getUserById(r.getUserId());
                                        } catch (Exception ignored) {}
                                        return PlanFeedItem.ReactionUser.builder()
                                                        .userId(r.getUserId())
                                                        .userName(profile != null ? profile.getFullname() : null)
                                                        .userAvatar(profile != null ? profile.getAvatar() : null)
                                                        .type(r.getType())
                                                        .build();
                                })
                                .collect(Collectors.toList());

                return CommentReactionResponse.builder()
                                .commentId(commentId)
                                .reactions(summary)
                                .myReaction(myReaction)
                                .reactionUsers(reactionUsers)
                                .build();
        }

        /**
         * Ghi nhận 1 lượt xem (đã dedup theo user). Có thể gọi tường minh từ FE
         * (POST /plans/{id}/views); việc đếm chính diễn ra tự động trong getById.
         */
        public void increaseView(Long planId, Long viewerId) {
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new NotFoundException("Plan not found"));

                // Không tính view cho bài của người bị chặn (tránh dò/đếm view chéo)
                if (viewerId != null && !Objects.equals(plan.getAuthorId(), viewerId)) {
                        VisibilityContext ctx = visibilityContextOf(viewerId);
                        if (ctx.blockedIds().contains(plan.getAuthorId())) {
                                return;
                        }
                }

                try {
                        planViewService.recordView(planId, viewerId, plan.getAuthorId());
                } catch (Exception ignored) {
                }
        }

        public Page<PlanFeedItem> getUserPlans(Long ownerId, Long viewerId) {
                return getUserPlans(ownerId, viewerId, false);
        }

        public Page<PlanFeedItem> getUserPlans(Long ownerId, Long viewerId, boolean isFriendIgnored) {

                Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdAt"));

                VisibilityContext ctx = visibilityContextOf(viewerId);
                Set<Long> blockedSet = new HashSet<>(ctx.blockedIds());

                // Chặn: không cho xem plan của người bị chặn (trừ khi xem của chính mình)
                if (!Objects.equals(ownerId, viewerId) && blockedSet.contains(ownerId)) {
                        return new PageImpl<>(List.of(), pageable, 0);
                }

                List<Long> memberPlanIds = sentinel(memberRepository.findPlanIdsByUserId(viewerId));

                // isFriend suy phía server từ visibility-context (KHÔNG tin tham số client)
                boolean isFriend = ctx.friendIds().contains(ownerId);

                Page<Plan> plans;
                if (Objects.equals(ownerId, viewerId)) {
                        plans = planRepository.findByAuthorId(ownerId, pageable);
                } else {
                        plans = planRepository.findAllPlansOfUserWithVisibility(
                                        ownerId,
                                        isFriend,
                                        memberPlanIds,
                                        pageable);
                }

                List<Long> hidden = hiddenPlanIdsOf(viewerId);

                List<PlanFeedItem> items = plans.getContent().stream()
                                .filter(p -> !hidden.contains(p.getId()))
                                .map(p -> planMapper.toFeedItem(p, viewerId, blockedSet))
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

                VisibilityContext ctx = visibilityContextOf(viewerId);
                Set<Long> blockedSet = new HashSet<>(ctx.blockedIds());
                Set<Long> hiddenSet = new HashSet<>(hiddenPlanIdsOf(viewerId));

                List<PlanFeedItem> result = new ArrayList<>();

                for (Long planId : planIds) {
                        Plan p = planMap.get(planId);
                        if (p == null)
                                continue;

                        boolean isOwner = Objects.equals(p.getAuthorId(), viewerId);
                        boolean isMember = memberRepository.existsByPlanIdAndUserId(p.getId(), viewerId);

                        // Ẩn bài đã hidden; chặn bài của người bị chặn (trừ chủ/thành viên plan chung)
                        if (hiddenSet.contains(p.getId()))
                                continue;
                        if (blockedSet.contains(p.getAuthorId()) && !isOwner && !isMember)
                                continue;

                        boolean isFriend = ctx.friendIds().contains(p.getAuthorId());

                        if (!permissionService.canView(p.getId(), viewerId, isFriend))
                                continue;

                        result.add(planMapper.toFeedItem(p, viewerId, blockedSet));
                }

                return result;
        }

        @Transactional
        public void removeRecentPlan(Long planId, Long viewerId) {
                if (viewerId == null)
                        return;
                recentViewRepository.deleteByUserIdAndPlanId(viewerId, planId);
        }

        // ===== Ẩn bài riêng (per-user hide) — có thể bỏ ẩn =====

        @Transactional
        public void hidePlan(Long planId, Long userId) {
                if (userId == null)
                        throw new ForbiddenException("Unauthorized");
                if (!planRepository.existsById(planId))
                        throw new NotFoundException("Plan not found");
                if (!planHiddenRepository.existsByUserIdAndPlanId(userId, planId)) {
                        planHiddenRepository.save(PlanHidden.builder()
                                        .userId(userId)
                                        .planId(planId)
                                        .hiddenAt(Instant.now())
                                        .build());
                }
        }

        @Transactional
        public void unhidePlan(Long planId, Long userId) {
                if (userId == null)
                        return;
                planHiddenRepository.deleteByUserIdAndPlanId(userId, planId);
        }

        /** Danh sách bài đã ẩn của user (cho trang "Bài đã ẩn"). */
        public List<PlanFeedItem> listHiddenPlans(Long userId) {
                if (userId == null)
                        return List.of();
                List<Long> ids = planHiddenRepository.findByUserIdOrderByHiddenAtDesc(userId).stream()
                                .map(PlanHidden::getPlanId)
                                .toList();
                if (ids.isEmpty())
                        return List.of();
                Map<Long, Plan> map = planRepository.findAllById(ids).stream()
                                .collect(Collectors.toMap(Plan::getId, p -> p));
                return ids.stream()
                                .map(map::get)
                                .filter(Objects::nonNull)
                                .map(p -> planMapper.toFeedItem(p, userId))
                                .toList();
        }

        // ===== Kiểm duyệt admin: GỠ bài = ép PRIVATE + khóa vĩnh viễn (bất khả hồi) =====

        @Transactional
        public void takedownPlan(Long planId, Long adminId, String reason) {
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new NotFoundException("Plan not found"));
                plan.setVisibility(Visibility.PRIVATE);
                plan.setAdminLocked(true);
                plan.setTakedownBy(adminId);
                plan.setTakedownAt(Instant.now());
                plan.setTakedownReason(reason);
                planRepository.save(plan);
                // Reindex ES với visibility=PRIVATE -> rớt khỏi search công khai (chống ES stale)
                planIndexPublisher.publishUpsert(planId);
        }

        // ===== Report system =====

        @Transactional
        public void reportPlan(Long planId, Long reporterId, String reason, String detail) {
                if (reporterId == null)
                        throw new ForbiddenException("Unauthorized");
                if (!planRepository.existsById(planId))
                        throw new NotFoundException("Plan not found");
                PlanReport.Reason r;
                try {
                        r = PlanReport.Reason.valueOf(reason == null ? "OTHER" : reason.toUpperCase());
                } catch (Exception e) {
                        r = PlanReport.Reason.OTHER;
                }
                planReportRepository.save(PlanReport.builder()
                                .planId(planId)
                                .reporterId(reporterId)
                                .reason(r)
                                .detail(detail)
                                .status(PlanReport.Status.PENDING)
                                .createdAt(Instant.now())
                                .build());
        }

        public Page<PlanReport> listReports(String status, int page, int size) {
                Pageable pageable = PageRequest.of(Math.max(0, page - 1),
                                Math.min(Math.max(size, 1), 100),
                                Sort.by(Sort.Direction.DESC, "createdAt"));
                if (status == null || status.isBlank())
                        return planReportRepository.findAllByOrderByCreatedAtDesc(pageable);
                try {
                        PlanReport.Status st = PlanReport.Status.valueOf(status.toUpperCase());
                        return planReportRepository.findByStatusOrderByCreatedAtDesc(st, pageable);
                } catch (Exception e) {
                        return planReportRepository.findAllByOrderByCreatedAtDesc(pageable);
                }
        }

        @Transactional
        public void resolveReport(Long reportId, Long adminId, String action, String takedownReason) {
                PlanReport report = planReportRepository.findById(reportId)
                                .orElseThrow(() -> new NotFoundException("Report not found"));
                PlanReport.Action act;
                try {
                        act = PlanReport.Action.valueOf(action == null ? "NONE" : action.toUpperCase());
                } catch (Exception e) {
                        act = PlanReport.Action.NONE;
                }
                if (act == PlanReport.Action.TAKEDOWN) {
                        takedownPlan(report.getPlanId(), adminId,
                                        takedownReason != null ? takedownReason : "Báo cáo vi phạm");
                }
                report.setStatus(PlanReport.Status.RESOLVED);
                report.setResolvedBy(adminId);
                report.setResolvedAt(Instant.now());
                report.setActionTaken(act);
                planReportRepository.save(report);
        }

        // ===== Analytics tổng hợp cho admin (on-demand + cache TTL ~1m) =====

        @org.springframework.cache.annotation.Cacheable(value = "adminPlanStats", key = "#days")
        public PlanAdminStatsDtos.StatsResponse getAdminStats(int days) {
                int d = (days <= 0) ? 30 : Math.min(days, 365);
                Instant now = Instant.now();
                Instant from = now.minus(d, java.time.temporal.ChronoUnit.DAYS);
                Instant from7 = now.minus(7, java.time.temporal.ChronoUnit.DAYS);
                Instant from30 = now.minus(30, java.time.temporal.ChronoUnit.DAYS);

                List<PlanAdminStatsDtos.Distribution> byStatus = planRepository.groupByStatus().stream()
                                .map(r -> new PlanAdminStatsDtos.Distribution(
                                                String.valueOf(r[0]), ((Number) r[1]).longValue()))
                                .toList();

                List<PlanAdminStatsDtos.Distribution> byVisibility = planRepository.groupByVisibility().stream()
                                .map(r -> new PlanAdminStatsDtos.Distribution(
                                                String.valueOf(r[0]), ((Number) r[1]).longValue()))
                                .toList();

                long publicPlans = byVisibility.stream()
                                .filter(x -> "PUBLIC".equals(x.key()))
                                .mapToLong(PlanAdminStatsDtos.Distribution::count)
                                .findFirst().orElse(0L);

                List<PlanAdminStatsDtos.TimePoint> series = planRepository.newPlansByDay(from).stream()
                                .map(r -> new PlanAdminStatsDtos.TimePoint(
                                                String.valueOf(r[0]), ((Number) r[1]).longValue()))
                                .toList();

                List<PlanAdminStatsDtos.TopPlan> top = planRepository
                                .findTop10ByVisibilityOrderByViewsDesc(Visibility.PUBLIC).stream()
                                .map(p -> new PlanAdminStatsDtos.TopPlan(
                                                p.getId(), p.getTitle(),
                                                p.getViews() == null ? 0L : p.getViews(), p.getAuthorId()))
                                .toList();

                PlanAdminStatsDtos.Overview overview = new PlanAdminStatsDtos.Overview(
                                planRepository.count(),
                                publicPlans,
                                planRepository.countByCreatedAtAfter(from7),
                                planRepository.countByCreatedAtAfter(from30),
                                planRepository.sumAllViews(),
                                reactionRepository.count(),
                                planCommentRepository.count(),
                                planRepository.countByAdminLockedTrue(),
                                planReportRepository.countByStatus(PlanReport.Status.PENDING));

                return new PlanAdminStatsDtos.StatsResponse(overview, byStatus, byVisibility, series, top);
        }

        @Transactional
        public void deletePlan(Long planId, Long userId) {
                inviteTokenRepository.deleteByPlanId(planId);
                Plan plan = planRepository.findById(planId)
                                .orElseThrow(() -> new NotFoundException("Plan not found"));

                if (!Objects.equals(plan.getAuthorId(), userId)) {
                        throw new ForbiddenException("Only the owner can delete this plan.");
                }

                recentViewRepository.deleteByPlanId(planId);

                planRepository.delete(plan);
                planRepository.flush();

                // Remove from Elasticsearch index
                planIndexPublisher.publishDelete(planId);
        }

        // ─ helpers exposed to PlanController ─

        /** Returns plan IDs where viewerId is a non-owner member (empty-safe). */
        public List<Long> getMemberPlanIds(Long viewerId) {
                if (viewerId == null)
                        return List.of(-1L);
                List<Long> ids = memberRepository.findPlanIdsByUserId(viewerId);
                return ids.isEmpty() ? List.of(-1L) : ids;
        }

        /** Fetches friend IDs from friend-service; returns empty list on failure. */
        public List<Long> getFriendIds(String authorizationHeader) {
                if (authorizationHeader == null)
                        return List.of();
                try {
                        List<Long> ids = friendClient.getFriendIds(authorizationHeader);
                        return ids == null || ids.isEmpty() ? List.of() : ids;
                } catch (Exception ignored) {
                        return List.of();
                }
        }

        /** Visibility-context (friendIds + blockedIds) cho controller/search (đã cache). */
        public VisibilityContext getVisibilityContext(Long viewerId) {
                return visibilityContextOf(viewerId);
        }

        /** Danh sách planId mà viewer đã ẩn (để loại khỏi search). */
        public List<Long> getHiddenPlanIds(Long viewerId) {
                return hiddenPlanIdsOf(viewerId);
        }

        /**
         * Converts a PlanDocument (from ES) back into a PlanFeedItem by loading the
         * full Plan from MySQL. Falls back to a lightweight DTO if the plan is missing.
         */
        public PlanFeedItem toFeedItemFromDocument(PlanDocument doc) {
                try {
                        Long planId = Long.parseLong(doc.getId());
                        return planRepository.findById(planId)
                                        .map(planMapper::toFeedItem)
                                        .orElse(documentToLightweightFeedItem(doc));
                } catch (NumberFormatException e) {
                        return documentToLightweightFeedItem(doc);
                }
        }

        /**
         * Lightweight mapping from PlanDocument when the MySQL record is unavailable.
         */
        private PlanFeedItem documentToLightweightFeedItem(PlanDocument doc) {
                return PlanFeedItem.builder()
                                .id(Long.parseLong(doc.getId()))
                                .title(doc.getTitle())
                                .description(doc.getDescription())
                                .visibility(doc.getVisibility())
                                .views(doc.getViews() != null ? doc.getViews() : 0L)
                                .startDate(doc.getStartDate() != null ? doc.getStartDate().toString() : null)
                                .endDate(doc.getEndDate() != null ? doc.getEndDate().toString() : null)
                                .days(doc.getDays() != null ? doc.getDays() : 0)
                                .createdAt(doc.getCreatedAt())
                                .budgetTotal(doc.getBudgetTotal())
                                .budgetCurrency(doc.getBudgetCurrency() != null ? doc.getBudgetCurrency() : "VND")
                                .thumbnail(doc.getThumbnail())
                                .images(List.of())
                                .destinations(doc.getDestinationNames() == null ? List.of()
                                                : doc.getDestinationNames().stream()
                                                                .map(n -> PlanFeedItem.Destination.builder().name(n)
                                                                                .build())
                                                                .toList())
                                .reactions(Map.of())
                                .reactionUsers(List.of())
                                .comments(List.of())
                                .build();
        }

        /**
         * Đồng bộ thủ công 100% dữ liệu từ MySQL sang Elasticsearch
         */
        @Transactional
        public void syncAllToElasticsearch() {
                List<Plan> allPlans = planRepository.findAll();
                List<PlanDocument> docs = new ArrayList<>();

                for (Plan plan : allPlans) {
                        List<String> destNames = plan.getDestinations() == null
                                        ? Collections.emptyList()
                                        : plan.getDestinations().stream()
                                                        .map(Destination::getName)
                                                        .filter(n -> n != null && !n.isBlank())
                                                        .toList();

                        PlanDocument doc = PlanDocument.builder()
                                        .id(String.valueOf(plan.getId()))
                                        .title(plan.getTitle())
                                        .titleRaw(plan.getTitle())
                                        .description(plan.getDescription())
                                        .descriptionRaw(plan.getDescription())
                                        .visibility(plan.getVisibility() != null ? plan.getVisibility().name()
                                                        : "PRIVATE")
                                        .authorId(plan.getAuthorId())
                                        .views(plan.getViews() != null ? plan.getViews() : 0L)
                                        .startDate(plan.getStartDate())
                                        .endDate(plan.getEndDate())
                                        .days(plan.getDays())
                                        .budgetTotal(plan.getBudgetTotal())
                                        .budgetCurrency(plan.getBudgetCurrency())
                                        .createdAt(plan.getCreatedAt())
                                        .destinationNames(destNames)
                                        .thumbnail(plan.getThumbnail())
                                        .build();

                        docs.add(doc);
                }

                if (!docs.isEmpty()) {
                        planEsRepository.saveAll(docs);
                }
        }
}
