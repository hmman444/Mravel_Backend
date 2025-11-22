package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.CreatePlanRequest;
import com.mravel.plan.dto.PlanFeedItem;
import com.mravel.plan.model.AuthorSummary;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanCard;
import com.mravel.plan.model.PlanComment;
import com.mravel.plan.model.PlanList;
import com.mravel.plan.model.PlanListType;
import com.mravel.plan.model.PlanReaction;
import com.mravel.plan.model.Visibility;
import com.mravel.plan.repository.PlanCardRepository;
import com.mravel.plan.repository.PlanReactionRepository;
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
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
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

        // EntityManager để truy vấn trực tiếp cho comment
        @PersistenceContext
        private EntityManager entityManager;

        public Page<PlanFeedItem> getFeed(int page, int size, Long viewerId) {
                PageRequest pageable = PageRequest.of(page - 1, size);

                List<Plan> visiblePlans;

                if (viewerId == null) {
                        // Khách chỉ thấy public
                        visiblePlans = planRepository.findByVisibility(Visibility.PUBLIC, pageable).getContent();
                } else {
                        // Người dùng đăng nhập
                        List<Plan> publicPlans = planRepository.findByVisibility(Visibility.PUBLIC, pageable)
                                        .getContent();

                        List<Plan> myPlans = planRepository.findByAuthor_Id(viewerId, pageable)
                                        .getContent();

                        // Các plan mà user là thành viên (được mời)
                        List<Long> memberPlanIds = memberRepository.findPlanIdsByUserId(viewerId);
                        List<Plan> memberPlans = memberPlanIds.isEmpty()
                                        ? Collections.emptyList()
                                        : planRepository.findByIdIn(memberPlanIds);

                        Set<Plan> merged = new LinkedHashSet<>();
                        merged.addAll(publicPlans);
                        merged.addAll(myPlans);
                        merged.addAll(memberPlans);

                        visiblePlans = new ArrayList<>(merged);
                }

                List<PlanFeedItem> mapped = visiblePlans.stream()
                                .sorted(Comparator.comparing(Plan::getCreatedAt).reversed())
                                .map(planMapper::toFeedItem)
                                .collect(Collectors.toList());

                return new PageImpl<>(mapped, pageable, mapped.size());
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
                                .author(new AuthorSummary(userId, req.getAuthorName(), req.getAuthorAvatar()))
                                .startDate(req.getStartDate())
                                .endDate(req.getEndDate())
                                .days(days)
                                .createdAt(Instant.now())
                                .views(0L)
                                .build();

                planRepository.save(plan);

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

                // trash
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

                if (!userId.equals(plan.getAuthor().getId()))
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
                                .author(new AuthorSummary(userId, null, null))
                                .startDate(source.getStartDate())
                                .endDate(source.getEndDate())
                                .days(source.getDays())
                                .createdAt(Instant.now())
                                .views(0L)
                                .images(new ArrayList<>(source.getImages()))
                                .destinations(new ArrayList<>(source.getDestinations()))
                                .build();

                planRepository.save(copy);
                permissionService.ensureOwner(copy.getId(), userId);

                // Copy dayLists + cards, tạo trash mới rỗng
                List<PlanList> sourceLists = listRepository.findByPlanIdOrderByPositionAsc(source.getId());

                // day lists của source, sort lại cho chắc
                List<PlanList> sourceDays = sourceLists.stream()
                                .filter(l -> l.getType() == PlanListType.DAY)
                                .sorted(Comparator.comparingInt(PlanList::getPosition))
                                .toList();

                // tạo day list cho copy, map id cũ -> id mới để copy card
                Map<Long, PlanList> oldToNewList = new HashMap<>();

                for (int i = 0; i < sourceDays.size(); i++) {
                        PlanList srcList = sourceDays.get(i);

                        PlanList newList = PlanList.builder()
                                        .plan(copy)
                                        .type(PlanListType.DAY)
                                        .position(i)
                                        .title(srcList.getTitle())
                                        .dayDate(copy.getStartDate() != null ? copy.getStartDate().plusDays(i)
                                                        : srcList.getDayDate())
                                        .build();

                        listRepository.save(newList);
                        oldToNewList.put(srcList.getId(), newList);

                        // copy cards thuộc list này
                        List<PlanCard> srcCards = cardRepository.findByListIdOrderByPositionAsc(srcList.getId());
                        for (int c = 0; c < srcCards.size(); c++) {
                                PlanCard oc = srcCards.get(c);
                                PlanCard nc = PlanCard.builder()
                                                .list(newList)
                                                .text(oc.getText())
                                                .description(oc.getDescription())
                                                .startTime(oc.getStartTime())
                                                .endTime(oc.getEndTime())
                                                .done(false) // khi copy thì reset done
                                                .position(c)
                                                .activityType(oc.getActivityType())
                                                .activityDataJson(oc.getActivityDataJson())
                                                .estimatedCost(oc.getEstimatedCost())
                                                .actualCost(oc.getActualCost())
                                                .payerId(oc.getPayerId())
                                                .splitType(oc.getSplitType())
                                                .splitMembers(oc.getSplitMembers() != null
                                                                ? new HashSet<>(oc.getSplitMembers())
                                                                : new HashSet<>())
                                                .splitResultJson(oc.getSplitResultJson())
                                                .build();
                                cardRepository.save(nc);
                        }
                }

                // tạo trash mới, rỗng
                int dayCount = sourceDays.size();
                listRepository.save(
                                PlanList.builder()
                                                .plan(copy)
                                                .type(PlanListType.TRASH)
                                                .position(dayCount)
                                                .title("Trash")
                                                .dayDate(null)
                                                .build());

                return planMapper.toFeedItem(copy);
        }

        @Transactional
        public PlanFeedItem.Comment addComment(
                        Long planId,
                        Long userId,
                        String userName,
                        String userAvatar,
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
                                .userName(userName)
                                .userAvatar(userAvatar)
                                .text(text)
                                .createdAt(Instant.now())
                                .build();

                entityManager.persist(comment);
                entityManager.flush();

                return PlanFeedItem.Comment.builder()
                                .id(comment.getId())
                                .parentId(parentId)
                                .text(comment.getText())
                                .createdAt(comment.getCreatedAt())
                                .user(PlanFeedItem.Comment.User.builder()
                                                .id(userId)
                                                .name(userName)
                                                .avatar(userAvatar)
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
                                        .userName(user.getFullname())
                                        .userAvatar(user.getAvatar())
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
}
