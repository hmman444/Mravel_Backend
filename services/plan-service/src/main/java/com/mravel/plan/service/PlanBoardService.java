package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.board.*;
import com.mravel.plan.kafka.KafkaProducer;
import com.mravel.plan.kafka.PlanBoardEvent;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanBoardService {

    private final PlanPermissionService permissionService;
    private final PlanRepository planRepository;
    private final PlanListRepository listRepository;
    private final PlanCardRepository cardRepository;
    private final PlanInviteTokenRepository inviteTokenRepo;
    private final PlanMemberRepository memberRepository;
    private final MailService mailService;
    private final UserProfileClient userProfileClient;
    private final PlanRequestRepository requestRepo;
    private final KafkaProducer kafkaProducer;

    // helper loaders

    private void validateMemberIds(Long planId, Collection<Long> userIds) {
        if (userIds == null || userIds.isEmpty())
            return;

        Set<Long> ids = userIds.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (ids.isEmpty())
            return;

        Set<Long> memberIds = memberRepository.findUserIdsByPlanId(planId); // custom query
        for (Long uid : ids) {
            if (!memberIds.contains(uid)) {
                throw new RuntimeException("User " + uid + " is not a member of this plan");
            }
        }
    }

    private PlanList mustLoadList(Long planId, Long listId) {
        PlanList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found"));
        if (!list.getPlan().getId().equals(planId)) {
            throw new RuntimeException("List not part of this plan");
        }
        return list;
    }

    private PlanCard mustLoadCard(Long planId, Long listId, Long cardId) {
        PlanList list = mustLoadList(planId, listId);
        PlanCard card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        if (!card.getList().getId().equals(list.getId())) {
            throw new RuntimeException("Card not in this list");
        }
        return card;
    }

    private PlanList findTrash(Long planId) {
        return listRepository.findByPlanIdOrderByPositionAsc(planId)
                .stream()
                .filter(l -> l.getType() == PlanListType.TRASH)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Trash missing"));
    }

    private List<PlanList> findDayLists(Long planId) {
        return listRepository.findByPlanIdOrderByPositionAsc(planId)
                .stream()
                .filter(l -> l.getType() == PlanListType.DAY)
                .toList();
    }

    /**
     * Đồng bộ lại position + dayDate của các list DAY,
     * cập nhật endDate của Plan và vị trí Trash.
     */
    public void syncDayLists(Plan plan) {
        List<PlanList> dayLists = findDayLists(plan.getId());
        LocalDate start = plan.getStartDate();

        for (int i = 0; i < dayLists.size(); i++) {
            PlanList l = dayLists.get(i);
            l.setPosition(i);
            l.setDayDate(start.plusDays(i));
        }

        plan.setEndDate(start.plusDays(dayLists.size() - 1));

        PlanList trash = findTrash(plan.getId());
        trash.setPosition(dayLists.size());
    }

    public void publishBoard(Long planId, Long actorId, String eventType) {
        try {
            BoardResponse board = getBoard(planId, actorId, false);

            PlanBoardEvent evt = PlanBoardEvent.builder()
                    .eventType(eventType)
                    .planId(planId)
                    .actorId(actorId)
                    .timestamp(System.currentTimeMillis())
                    .board(board)
                    .build();

            kafkaProducer.publishBoardEvent(evt);

        } catch (Exception ex) {
            log.error("Failed to publish board event for planId={}", planId, ex);
        }
    }

    private PlanCostSummaryDto buildCostSummary(Plan plan, List<PlanList> lists) {
        Map<PlanActivityType, long[]> byType = new EnumMap<>(PlanActivityType.class);
        Map<LocalDate, long[]> byDay = new HashMap<>();

        for (PlanList list : lists) {
            if (list.getType() == PlanListType.TRASH)
                continue;
            LocalDate date = list.getDayDate();

            for (PlanCard c : list.getCards()) {
                PlanActivityType type = c.getActivityType();
                long est = c.getEstimatedCost() != null ? c.getEstimatedCost() : 0L;
                long act = c.getActualCost() != null ? c.getActualCost() : 0L;

                if (type != null) {
                    byType.computeIfAbsent(type, k -> new long[2]);
                    byType.get(type)[0] += est;
                    byType.get(type)[1] += act;
                }

                if (date != null) {
                    byDay.computeIfAbsent(date, k -> new long[2]);
                    byDay.get(date)[0] += est;
                    byDay.get(date)[1] += act;
                }
            }
        }

        List<PlanCostSummaryDto.ActivityCostDto> byTypeList = byType.entrySet().stream()
                .map(e -> PlanCostSummaryDto.ActivityCostDto.builder()
                        .activityType(e.getKey())
                        .estimatedCost(e.getValue()[0])
                        .actualCost(e.getValue()[1])
                        .build())
                .toList();

        List<PlanCostSummaryDto.DayCostDto> byDayList = byDay.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> PlanCostSummaryDto.DayCostDto.builder()
                        .date(e.getKey())
                        .estimatedCost(e.getValue()[0])
                        .actualCost(e.getValue()[1])
                        .build())
                .toList();

        return PlanCostSummaryDto.builder()
                .planId(plan.getId())
                .budgetCurrency(plan.getBudgetCurrency())
                .budgetTotal(plan.getBudgetTotal())
                .totalEstimatedCost(plan.getTotalEstimatedCost())
                .totalActualCost(plan.getTotalActualCost())
                .byActivityType(byTypeList)
                .byDay(byDayList)
                .build();
    }

    // board

    @Transactional
    public BoardResponse getBoard(Long planId, Long userId, boolean isFriend) {

        if (!permissionService.canView(planId, userId, isFriend)) {
            throw new RuntimeException("You don't have permission to view this board.");
        }

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanRole myRole = permissionService.getUserRole(planId, userId);

        List<PlanList> lists = listRepository.findByPlanIdOrderByPositionAsc(planId);
        PlanCostSummaryDto costSummary = buildCostSummary(plan, lists);
        PlanMemberCostSummaryDto memberCostSummary = buildMemberCostSummary(plan, lists);

        return BoardResponse.builder()
                .planId(plan.getId())
                .planTitle(plan.getTitle())
                .description(plan.getDescription())
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .budgetCurrency(plan.getBudgetCurrency())
                .budgetTotal(plan.getBudgetTotal())
                .budgetPerPerson(plan.getBudgetPerPerson())
                .totalEstimatedCost(plan.getTotalEstimatedCost())
                .totalActualCost(plan.getTotalActualCost())
                .status(plan.getStatus().name())
                .thumbnail(plan.getThumbnail())
                .images(plan.getImages())
                .myRole(myRole != null ? myRole.name() : null)

                .costSummary(costSummary)
                .memberCostSummary(memberCostSummary)

                .lists(
                        listRepository.findByPlanIdOrderByPositionAsc(planId).stream()
                                .map(l -> ListDto.builder()
                                        .id(l.getId())
                                        .title(l.getTitle())
                                        .position(l.getPosition())
                                        .type(l.getType().name())
                                        .dayDate(l.getDayDate())
                                        .cards(
                                                l.getCards().stream()
                                                        .sorted(Comparator.comparingInt(PlanCard::getPosition))
                                                        .map(this::toCardDto)
                                                        .toList())
                                        .build())
                                .toList())
                .build();
    }

    private ExtraCostDto toExtraCostDto(ExtraCost ec) {
        return ExtraCostDto.builder()
                .reason(ec.getReason())
                .type(ec.getType())
                .estimatedAmount(ec.getEstimatedAmount())
                .actualAmount(ec.getActualAmount())
                .build();
    }

    private CardSplitDetailDto toSplitDetailDto(CardSplitDetail d) {
        if (d == null)
            return null;
        return CardSplitDetailDto.builder()
                .userId(d.getUserId())
                .amount(d.getAmount())
                .build();
    }

    private PlanCardPaymentDto toPaymentDto(PlanCardPayment p) {
        if (p == null)
            return null;
        return PlanCardPaymentDto.builder()
                .payerUserId(p.getPayerUserId())
                .amount(p.getAmount())
                .note(p.getNote())
                .build();
    }

    private CardDto toCardDto(PlanCard c) {
        return CardDto.builder()
                .id(c.getId())
                .text(c.getText())
                .description(c.getDescription())
                .startTime(c.getStartTime())
                .endTime(c.getEndTime())
                .durationMinutes(c.getDurationMinutes())
                .done(c.isDone())
                .position(c.getPosition())
                .activityType(c.getActivityType())
                .activityDataJson(c.getActivityDataJson())
                .cost(
                        PlanCardCostDto.builder()
                                .currencyCode(c.getCurrencyCode())
                                .estimatedCost(c.getEstimatedCost())
                                .actualCost(c.getActualCost())
                                .budgetAmount(c.getBudgetAmount())
                                .participantCount(c.getParticipantCount())
                                .participants(
                                        new ArrayList<>(c.getParticipants())) // Set<Long> -> List<Long>
                                .extraCosts(
                                        c.getExtraCosts().stream()
                                                .map(this::toExtraCostDto)
                                                .toList())
                                .build())
                .split(
                        PlanCardSplitConfigDto.builder()
                                .splitType(c.getSplitType())
                                .payerId(c.getPayerId())
                                .includePayerInSplit(c.isIncludePayerInSplit())
                                .splitMembers(
                                        new ArrayList<>(c.getSplitMembers())) // Set<Long> -> List<Long>
                                .splitDetails(
                                        c.getSplitDetails().stream()
                                                .map(this::toSplitDetailDto)
                                                .toList())
                                .payments(
                                        c.getPayments().stream()
                                                .map(this::toPaymentDto)
                                                .toList())
                                .build())

                .build();
    }

    /**
     * Áp dụng cost DTO vào entity Card:
     * - estimatedCost: chi phí ước lượng
     * - budgetAmount: ngân sách cho activity
     * - actualCost: KHÔNG lấy từ DTO, sẽ được BE tự tính lại
     * - extraCosts / participants / participantCount: gán thẳng
     */
    private void applyCost(PlanCard card, PlanCardCostDto dto) {
        if (dto == null)
            return;

        // currency
        if (dto.getCurrencyCode() != null && !dto.getCurrencyCode().isBlank()) {
            card.setCurrencyCode(dto.getCurrencyCode());
        }

        // Estimated cost
        card.setEstimatedCost(dto.getEstimatedCost());
        card.setBudgetAmount(dto.getBudgetAmount());
        card.setParticipantCount(dto.getParticipantCount());

        // Participants
        card.getParticipants().clear();
        if (dto.getParticipants() != null) {
            Long planId = card.getList().getPlan().getId();
            validateMemberIds(planId, dto.getParticipants());
            card.getParticipants().addAll(dto.getParticipants());
        }

        // Extra costs
        card.getExtraCosts().clear();
        if (dto.getExtraCosts() != null) {
            dto.getExtraCosts().forEach(e -> card.getExtraCosts().add(
                    ExtraCost.builder()
                            .reason(e.getReason())
                            .type(e.getType())
                            .estimatedAmount(e.getEstimatedAmount())
                            .actualAmount(e.getActualAmount())
                            .build()));
        }

        if (dto.getActualCost() != null) {
            card.setActualManual(true);
            card.setActualCost(dto.getActualCost());
        } else {
            // Không gửi actual => auto mode
            card.setActualManual(false);
        }
    }

    private void applySplit(PlanCard card, PlanCardSplitConfigDto dto) {
        if (dto == null)
            return;

        Long planId = card.getList().getPlan().getId();

        card.setSplitType(dto.getSplitType());
        card.setIncludePayerInSplit(dto.isIncludePayerInSplit());

        // payerId: cũng phải là member nếu khác null
        if (dto.getPayerId() != null) {
            validateMemberIds(planId, List.of(dto.getPayerId()));
            card.setPayerId(dto.getPayerId());
        } else {
            card.setPayerId(null);
        }

        // splitMembers = list userId
        card.getSplitMembers().clear();
        if (dto.getSplitMembers() != null) {
            validateMemberIds(planId, dto.getSplitMembers());
            card.getSplitMembers().addAll(dto.getSplitMembers());
        }

        // payments
        card.getPayments().clear();
        if (dto.getPayments() != null) {
            // collect all payerUserId để validate
            List<Long> payerIds = dto.getPayments().stream()
                    .map(PlanCardPaymentDto::getPayerUserId)
                    .filter(Objects::nonNull)
                    .toList();
            validateMemberIds(planId, payerIds);

            dto.getPayments().forEach(pDto -> {
                PlanCardPayment payment = PlanCardPayment.builder()
                        .card(card)
                        .payerUserId(pDto.getPayerUserId())
                        .amount(pDto.getAmount())
                        .note(pDto.getNote())
                        .build();
                card.getPayments().add(payment);
            });
        }

        // splitDetails (EXACT): mỗi detail gắn userId
        if (dto.getSplitDetails() != null && dto.getSplitType() == SplitType.EXACT) {
            card.getSplitDetails().clear();

            List<Long> userIds = dto.getSplitDetails().stream()
                    .map(CardSplitDetailDto::getUserId)
                    .filter(Objects::nonNull)
                    .toList();
            validateMemberIds(planId, userIds);

            dto.getSplitDetails().forEach(d -> card.getSplitDetails().add(
                    CardSplitDetail.builder()
                            .userId(d.getUserId())
                            .amount(d.getAmount())
                            .build()));
        }
    }

    // tính phí và chia tiền

    /**
     * Tính lại actualCost cho card:
     * actualCost = estimatedCost + sum(extra.actualAmount)
     */
    private void recalculateCardCosts(PlanCard card) {
        // USER đã nhập actual → KHÔNG tính lại
        if (Boolean.TRUE.equals(card.getActualManual())) {
            return;
        }

        long est = card.getEstimatedCost() != null ? card.getEstimatedCost() : 0;
        long extra = card.getExtraCosts().stream()
                .mapToLong(e -> e.getActualAmount() != null ? e.getActualAmount() : 0)
                .sum();

        card.setActualCost(est + extra);
    }

    /**
     * Tính lại tổng estimated / actual cho Plan (bỏ qua TRASH).
     */
    private void recalculatePlanTotals(Plan plan) {
        long totalEst = 0L;
        long totalAct = 0L;

        for (PlanList l : plan.getLists()) {
            if (l.getType() == PlanListType.TRASH)
                continue;

            for (PlanCard c : l.getCards()) {
                if (c.getEstimatedCost() != null) {
                    totalEst += c.getEstimatedCost();
                }
                if (c.getActualCost() != null) {
                    totalAct += c.getActualCost();
                }
            }
        }

        plan.setTotalEstimatedCost(totalEst);
        plan.setTotalActualCost(totalAct);
    }

    private void recalculateSplitDetails(PlanCard card) {
        SplitType type = card.getSplitType();
        if (type == null || type == SplitType.NONE) {
            card.getSplitDetails().clear();
            return;
        }

        // EXACT: giữ nguyên, đã set ở applySplit
        if (type == SplitType.EXACT) {
            return;
        }

        long total = card.getActualCost() != null
                ? card.getActualCost()
                : (card.getEstimatedCost() != null ? card.getEstimatedCost() : 0L);

        if (total <= 0) {
            card.getSplitDetails().clear();
            return;
        }

        List<Long> members = new ArrayList<>(card.getSplitMembers());
        if (members.isEmpty()) {
            card.getSplitDetails().clear();
            return;
        }

        card.getSplitDetails().clear();

        switch (type) {
            case EVEN -> splitEven(card, members, total);
            default -> {
            }
        }
    }

    private void splitEven(PlanCard card, List<Long> members, long total) {
        int n = members.size();
        long baseShare = total / n;
        long remainder = total % n;

        for (int i = 0; i < n; i++) {
            Long userId = members.get(i);
            long amount = baseShare + (i < remainder ? 1 : 0);

            card.getSplitDetails().add(
                    CardSplitDetail.builder()
                            .userId(userId)
                            .amount(amount)
                            .build());
        }
    }

    // crud list

    @Transactional
    public ListDto createList(Long planId, Long userId, CreateListRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        List<PlanList> dayLists = findDayLists(planId);
        PlanList trash = findTrash(planId);

        LocalDate date = plan.getEndDate().plusDays(1);
        int index = dayLists.size();

        String title = (req.getTitle() != null && !req.getTitle().isBlank())
                ? req.getTitle()
                : "Danh sách hoạt động";

        PlanList list = PlanList.builder()
                .plan(plan)
                .type(PlanListType.DAY)
                .position(index)
                .title(title)
                .dayDate(date)
                .build();

        listRepository.save(list);

        trash.setPosition(index + 1);
        plan.setEndDate(date);

        publishBoard(planId, userId, "CREATE_LIST");
        return ListDto.builder()
                .id(list.getId())
                .title(list.getTitle())
                .position(list.getPosition())
                .type("DAY")
                .dayDate(list.getDayDate())
                .cards(List.of())
                .build();
    }

    @Transactional
    public void renameList(Long planId, Long listId, Long userId, RenameListRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanList list = mustLoadList(planId, listId);

        if (list.getType() == PlanListType.TRASH) {
            throw new RuntimeException("Cannot rename trash");
        }

        list.setTitle(req.getTitle());
        publishBoard(planId, userId, "RENAME_LIST");
    }

    @Transactional
    public void deleteList(Long planId, Long userId, Long listId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanList target = mustLoadList(planId, listId);
        if (target.getType() == PlanListType.TRASH) {
            throw new RuntimeException("Cannot delete trash list");
        }

        List<PlanList> dayLists = findDayLists(planId);
        if (dayLists.size() == 1) {
            throw new RuntimeException("Cannot delete the last day");
        }

        PlanList trash = findTrash(planId);

        List<PlanCard> cards = cardRepository.findByListIdOrderByPositionAsc(target.getId());
        int basePos = (int) cardRepository.countByListId(trash.getId());

        for (int i = 0; i < cards.size(); i++) {
            PlanCard c = cards.get(i);
            c.setList(trash);
            c.setPosition(basePos + i);
        }
        cardRepository.saveAll(cards);

        listRepository.delete(target);

        syncDayLists(target.getPlan());
        publishBoard(planId, userId, "DELETE_LIST");
    }

    // crud card

    @Transactional
    public CardDto createCard(Long planId, Long listId, Long userId, CreateCardRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanList list = mustLoadList(planId, listId);

        if (list.getType() == PlanListType.TRASH) {
            throw new RuntimeException("Cannot create card in trash");
        }

        int nextPos = (int) cardRepository.countByListId(listId);

        PlanCard card = PlanCard.builder()
                .list(list)
                .text(req.getText() != null ? req.getText() : "New card")
                .description(req.getDescription())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .durationMinutes(req.getDurationMinutes())
                .done(false)
                .position(nextPos)
                .activityType(req.getActivityType())
                .activityDataJson(req.getActivityDataJson())
                .build();

        applyCost(card, req.getCost());
        applySplit(card, req.getSplit());

        recalculateCardCosts(card);
        recalculateSplitDetails(card);

        cardRepository.save(card);

        recalculatePlanTotals(plan);
        publishBoard(planId, userId, "CREATE_CARD");

        return toCardDto(card);
    }

    @Transactional
    public CardDto updateCard(Long planId, Long listId, Long cardId, Long userId, UpdateCardRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanCard card = mustLoadCardInList(planId, listId, cardId);

        if (req.getText() != null)
            card.setText(req.getText());
        if (req.getDescription() != null)
            card.setDescription(req.getDescription());

        if (req.getStartTime() != null)
            card.setStartTime(req.getStartTime());
        if (req.getEndTime() != null)
            card.setEndTime(req.getEndTime());
        if (req.getDurationMinutes() != null)
            card.setDurationMinutes(req.getDurationMinutes());

        if (req.getDone() != null)
            card.setDone(req.getDone());

        if (req.getActivityType() != null)
            card.setActivityType(req.getActivityType());
        if (req.getActivityDataJson() != null)
            card.setActivityDataJson(req.getActivityDataJson());

        if (req.getCost() != null)
            applyCost(card, req.getCost());
        if (req.getSplit() != null)
            applySplit(card, req.getSplit());

        recalculateCardCosts(card);
        recalculateSplitDetails(card);
        recalculatePlanTotals(plan);
        publishBoard(planId, userId, "UPDATE_CARD");
        return toCardDto(card);
    }

    @Transactional
    public void deleteCard(Long planId, Long listId, Long cardId, Long userId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanCard card = mustLoadCard(planId, listId, cardId);

        cardRepository.delete(card);

        List<PlanCard> cards = cardRepository.findByListIdOrderByPositionAsc(listId);
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setPosition(i);
        }

        recalculatePlanTotals(plan);
        publishBoard(planId, userId, "DELETE_CARD");
    }

    @Transactional
    public void clearTrash(Long planId, Long userId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanList trash = findTrash(planId);

        List<PlanCard> cardsInTrash = cardRepository.findByListIdOrderByPositionAsc(trash.getId());
        cardRepository.deleteAll(cardsInTrash);

        recalculatePlanTotals(plan);
        publishBoard(planId, userId, "CLEAR_TRASH");
    }

    @Transactional
    public CardDto duplicateCard(Long planId, Long listId, Long cardId, Long userId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanCard original = mustLoadCardInList(planId, listId, cardId);
        int nextPos = (int) cardRepository.countByListId(listId);

        PlanCard copy = PlanCard.builder()
                .list(original.getList())
                .text(original.getText() + " (Copy)")
                .description(original.getDescription())
                .startTime(original.getStartTime())
                .endTime(original.getEndTime())
                .durationMinutes(original.getDurationMinutes())
                .done(false)
                .position(nextPos)
                .activityType(original.getActivityType())
                .activityDataJson(original.getActivityDataJson())
                .currencyCode(original.getCurrencyCode())
                .estimatedCost(original.getEstimatedCost())
                .actualCost(original.getActualCost())
                .budgetAmount(original.getBudgetAmount())
                .participantCount(original.getParticipantCount())
                .includePayerInSplit(original.isIncludePayerInSplit())
                .splitType(original.getSplitType())
                .payerId(original.getPayerId())
                .build();

        // copy participants
        copy.getParticipants().addAll(original.getParticipants());

        // copy extra costs
        original.getExtraCosts().forEach(e -> copy.getExtraCosts().add(
                ExtraCost.builder()
                        .reason(e.getReason())
                        .type(e.getType())
                        .estimatedAmount(e.getEstimatedAmount())
                        .actualAmount(e.getActualAmount())
                        .build()));

        // copy split members
        copy.getSplitMembers().addAll(original.getSplitMembers());

        // copy splitDetails
        original.getSplitDetails().forEach(sd -> copy.getSplitDetails().add(
                CardSplitDetail.builder()
                        .userId(sd.getUserId())
                        .amount(sd.getAmount())
                        .build()));

        // copy payments
        original.getPayments().forEach(p -> copy.getPayments().add(
                PlanCardPayment.builder()
                        .card(copy)
                        .payerUserId(p.getPayerUserId())
                        .amount(p.getAmount())
                        .note(p.getNote())
                        .build()));

        // recalc logic
        recalculateCardCosts(copy);
        recalculateSplitDetails(copy);

        cardRepository.save(copy);
        recalculatePlanTotals(plan);
        publishBoard(planId, userId, "DUPLICATE_CARD");

        return toCardDto(copy);
    }

    // drag drop

    @Transactional
    public BoardResponse reorder(Long planId, Long userId, boolean isFriend, ReorderRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        if ("list".equalsIgnoreCase(req.getType())) {
            return reorderLists(planId, userId, isFriend, req);
        } else if ("card".equalsIgnoreCase(req.getType())) {
            return reorderCards(planId, userId, isFriend, req);
        }
        throw new RuntimeException("Unknown reorder type");
    }

    @Transactional
    public ListDto duplicateList(Long planId, Long userId, Long listId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanList source = mustLoadList(planId, listId);
        if (source.getType() == PlanListType.TRASH) {
            throw new RuntimeException("Cannot duplicate trash list");
        }

        List<PlanList> dayLists = findDayLists(planId);
        PlanList trash = findTrash(planId);

        LocalDate newDate = plan.getEndDate().plusDays(1);
        int newIndex = dayLists.size();

        PlanList cloneList = PlanList.builder()
                .plan(plan)
                .type(PlanListType.DAY)
                .position(newIndex)
                .title(source.getTitle() + " (Copy)")
                .dayDate(newDate)
                .cards(new ArrayList<>())
                .build();

        cloneList = listRepository.save(cloneList);

        trash.setPosition(newIndex + 1);
        plan.setEndDate(newDate);

        List<PlanCard> cards = cardRepository.findByListIdOrderByPositionAsc(source.getId());

        for (int i = 0; i < cards.size(); i++) {
            PlanCard original = cards.get(i);

            PlanCard copy = PlanCard.builder()
                    .list(cloneList)
                    .text(original.getText())
                    .description(original.getDescription())
                    .startTime(original.getStartTime())
                    .endTime(original.getEndTime())
                    .durationMinutes(original.getDurationMinutes())
                    .done(false)
                    .position(i)
                    .activityType(original.getActivityType())
                    .activityDataJson(original.getActivityDataJson())
                    .currencyCode(original.getCurrencyCode())
                    .estimatedCost(original.getEstimatedCost())
                    .actualCost(original.getActualCost())
                    .budgetAmount(original.getBudgetAmount())
                    .participantCount(original.getParticipantCount())
                    .includePayerInSplit(original.isIncludePayerInSplit())
                    .splitType(original.getSplitType())
                    .payerId(original.getPayerId())
                    .build();

            // participants
            copy.getParticipants().addAll(original.getParticipants());

            // extra costs
            original.getExtraCosts().forEach(ec -> copy.getExtraCosts().add(
                    ExtraCost.builder()
                            .reason(ec.getReason())
                            .type(ec.getType())
                            .estimatedAmount(ec.getEstimatedAmount())
                            .actualAmount(ec.getActualAmount())
                            .build()));

            // split members
            copy.getSplitMembers().addAll(original.getSplitMembers());

            // split details
            original.getSplitDetails().forEach(sd -> copy.getSplitDetails().add(
                    CardSplitDetail.builder()
                            .userId(sd.getUserId())
                            .amount(sd.getAmount())
                            .build()));

            // payments
            original.getPayments().forEach(p -> copy.getPayments().add(
                    PlanCardPayment.builder()
                            .card(copy)
                            .payerUserId(p.getPayerUserId())
                            .amount(p.getAmount())
                            .note(p.getNote())
                            .build()));

            cloneList.getCards().add(copy);

            recalculateCardCosts(copy);
            recalculateSplitDetails(copy);

            cardRepository.save(copy);
        }

        recalculatePlanTotals(plan);
        publishBoard(planId, userId, "DUPLICATE_LIST");

        return ListDto.builder()
                .id(cloneList.getId())
                .title(cloneList.getTitle())
                .position(cloneList.getPosition())
                .type("DAY")
                .dayDate(cloneList.getDayDate())
                .cards(
                        cloneList.getCards().stream()
                                .sorted(Comparator.comparingInt(PlanCard::getPosition))
                                .map(this::toCardDto)
                                .toList())
                .build();
    }

    @Transactional
    private BoardResponse reorderLists(Long planId, Long userId, boolean isFriend, ReorderRequest req) {
        List<PlanList> lists = listRepository.findByPlanIdOrderByPositionAsc(planId);

        int trashIndex = -1;
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getType() == PlanListType.TRASH) {
                trashIndex = i;
                break;
            }
        }

        if (req.getSourceIndex() == trashIndex || req.getDestIndex() == trashIndex) {
            throw new RuntimeException("Cannot move trash");
        }

        PlanList moved = lists.remove(req.getSourceIndex().intValue());
        lists.add(req.getDestIndex().intValue(), moved);

        for (int i = 0; i < lists.size(); i++) {
            lists.get(i).setPosition(i);
        }

        Plan plan = planRepository.findById(planId).orElseThrow();
        syncDayLists(plan);
        publishBoard(planId, userId, "REORDER_LIST");

        return getBoard(planId, userId, isFriend);
    }

    @Transactional
    private BoardResponse reorderCards(Long planId, Long userId, boolean isFriend, ReorderRequest req) {

        PlanList source = mustLoadList(planId, req.getSourceListId());
        PlanList dest = mustLoadList(planId, req.getDestListId());

        List<PlanCard> sourceCards = cardRepository.findByListIdOrderByPositionAsc(source.getId());
        PlanCard moved = sourceCards.remove(req.getSourceIndex().intValue());

        List<PlanCard> destCards = source.getId().equals(dest.getId())
                ? sourceCards
                : cardRepository.findByListIdOrderByPositionAsc(dest.getId());

        moved.setList(dest);
        destCards.add(req.getDestIndex().intValue(), moved);

        for (int i = 0; i < sourceCards.size(); i++) {
            sourceCards.get(i).setPosition(i);
        }

        if (!source.getId().equals(dest.getId())) {
            for (int i = 0; i < destCards.size(); i++) {
                destCards.get(i).setPosition(i);
            }
        }
        publishBoard(planId, userId, "REORDER_CARD");

        return getBoard(planId, userId, isFriend);
    }

    // invite / join

    @Transactional
    public List<InviteDto> invite(Long planId, Long userId, InviteRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.OWNER);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanRole role = Optional.ofNullable(req.getRole())
                .orElse(PlanRole.VIEWER);

        List<InviteDto> result = new ArrayList<>();

        for (String email : req.getEmails()) {

            inviteTokenRepo.deleteByEmailAndPlanId(email, plan.getId());

            String token = UUID.randomUUID().toString();

            PlanInviteToken tokenEntity = PlanInviteToken.builder()
                    .plan(plan)
                    .email(email)
                    .role(role)
                    .token(token)
                    .expiredAt(Instant.now().plus(Duration.ofDays(3)))
                    .used(false)
                    .build();

            inviteTokenRepo.save(tokenEntity);

            mailService.sendInviteEmail(
                    email,
                    plan.getTitle(),
                    "http://localhost:5173/plans/join?token=" + token);

            result.add(InviteDto.builder()
                    .id(tokenEntity.getId())
                    .email(email)
                    .role(role)
                    .build());
        }

        return result;
    }

    @Transactional
    public Long joinPlan(String token, Long userId) {

        PlanInviteToken inv = inviteTokenRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        Long planId = inv.getPlan().getId();

        if (inv.isUsed())
            return planId;
        if (inv.getExpiredAt().isBefore(Instant.now())) {
            throw new RuntimeException("Invite expired");
        }

        boolean exists = memberRepository.existsByPlanIdAndUserId(planId, userId);
        if (exists) {
            inv.setUsed(true);
            return planId;
        }

        PlanMember member = PlanMember.builder()
                .plan(inv.getPlan())
                .userId(userId)
                .role(inv.getRole())
                .build();
        memberRepository.save(member);

        inv.setUsed(true);

        return planId;
    }

    // share info

    @Transactional
    public ShareResponse getShareInfo(Long planId, Long userId) {
        if (!permissionService.canView(planId, userId, false)) {
            throw new RuntimeException("You don't have permission to view share info.");
        }

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        List<MemberDto> members = memberRepository.findByPlanId(planId).stream()
                .map(m -> {
                    UserProfileResponse profile = userProfileClient.getUserById(m.getUserId());

                    return MemberDto.builder()
                            .userId(m.getUserId())
                            .email(profile.getEmail())
                            .fullname(profile.getFullname())
                            .avatar(profile.getAvatar())
                            .role(m.getRole().name())
                            .isCurrentUser(userId != null && userId.equals(m.getUserId()))
                            .build();
                })
                .toList();

        List<InviteDto> invites = inviteTokenRepo.findByPlanId(planId).stream()
                .filter(token -> !token.isUsed() && token.getExpiredAt().isAfter(Instant.now()))
                .map(token -> InviteDto.builder()
                        .id(token.getId())
                        .email(token.getEmail())
                        .role(token.getRole())
                        .build())
                .toList();

        return ShareResponse.builder()
                .visibility(plan.getVisibility().name())
                .members(members)
                .invites(invites)
                .build();
    }

    @Transactional
    public void updateMemberRole(Long planId, Long ownerId, UpdateMemberRoleRequest req) {
        permissionService.checkPermission(planId, ownerId, PlanRole.OWNER);

        PlanMember member = memberRepository.findByPlanIdAndUserId(planId, req.getUserId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (member.getRole() == PlanRole.OWNER) {
            throw new RuntimeException("Cannot change role of the owner.");
        }

        PlanRole newRole;
        try {
            newRole = PlanRole.valueOf(req.getRole().toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid role: " + req.getRole());
        }

        member.setRole(newRole);
        memberRepository.save(member);
    }

    @Transactional
    public void removeMember(Long planId, Long ownerId, Long targetUserId) {
        permissionService.checkPermission(planId, ownerId, PlanRole.OWNER);

        if (Objects.equals(ownerId, targetUserId)) {
            throw new RuntimeException("Owner cannot remove themselves.");
        }

        PlanMember member = memberRepository.findByPlanIdAndUserId(planId, targetUserId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (member.getRole() == PlanRole.OWNER) {
            throw new RuntimeException("Cannot remove the owner.");
        }

        memberRepository.delete(member);

        UserProfileResponse profile = userProfileClient.getUserById(targetUserId);
        String email = profile.getEmail();

        inviteTokenRepo.deleteByEmailAndPlanId(email, planId);
    }

    // request access

    @Transactional
    public PlanRequestDto requestAccess(Long planId, Long userId, PlanRequestCreate req) {

        PlanMember member = memberRepository.findByPlanIdAndUserId(planId, userId).orElse(null);

        if (member != null) {
            if (member.getRole() == PlanRole.OWNER || member.getRole() == PlanRole.EDITOR) {
                throw new RuntimeException("Bạn đã có quyền truy cập.");
            }

            if (member.getRole() == PlanRole.VIEWER) {
                if (req.getType() != PlanRequestType.EDIT) {
                    throw new RuntimeException("Bạn đã có quyền xem.");
                }
            }
        }

        if (requestRepo.existsByPlanIdAndUserIdAndStatus(
                planId, userId, PlanRequestStatus.PENDING)) {
            throw new IllegalArgumentException("Bạn đã gửi yêu cầu trước đó.");
        }

        PlanRequest entity = PlanRequest.builder()
                .planId(planId)
                .userId(userId)
                .type(req.getType())
                .status(PlanRequestStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        requestRepo.save(entity);

        Plan plan = planRepository.getReferenceById(planId);
        UserProfileResponse requester = userProfileClient.getUserById(userId);
        UserProfileResponse owner = userProfileClient.getUserById(plan.getAuthorId());

        mailService.sendRequestEmailToOwner(
                owner.getEmail(),
                plan.getTitle(),
                requester.getFullname(),
                requester.getEmail(),
                req.getType().name());

        return PlanRequestDto.builder()
                .id(entity.getId())
                .userId(userId)
                .fullname(requester.getFullname())
                .email(requester.getEmail())
                .avatar(requester.getAvatar())
                .type(entity.getType())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public List<PlanRequestDto> getRequests(Long planId) {
        return requestRepo.findByPlanIdAndStatus(planId, PlanRequestStatus.PENDING)
                .stream()
                .map(r -> {
                    UserProfileResponse profile = userProfileClient.getUserById(r.getUserId());

                    return PlanRequestDto.builder()
                            .id(r.getId())
                            .userId(r.getUserId())
                            .fullname(profile.getFullname())
                            .email(profile.getEmail())
                            .avatar(profile.getAvatar())
                            .type(r.getType())
                            .status(r.getStatus())
                            .createdAt(r.getCreatedAt())
                            .build();
                })
                .toList();
    }

    @Transactional
    public void handleRequest(Long planId, Long reqId, PlanRequestAction action) {

        PlanRequest req = requestRepo.findById(reqId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!req.getPlanId().equals(planId)) {
            throw new RuntimeException("Invalid plan");
        }

        Plan plan = planRepository.getReferenceById(planId);
        UserProfileResponse targetUser = userProfileClient.getUserById(req.getUserId());

        if (action.getAction().equalsIgnoreCase("REJECT")) {

            req.setStatus(PlanRequestStatus.REJECTED);

            mailService.sendRejectEmail(
                    targetUser.getEmail(),
                    plan.getTitle());

            return;
        }

        if (action.getAction().equalsIgnoreCase("APPROVE")) {

            PlanRole assignedRole = PlanRole.VIEWER;

            if (action.getRole() != null) {
                try {
                    assignedRole = PlanRole.valueOf(action.getRole().toUpperCase());
                } catch (Exception ignored) {
                }
            }

            boolean exists = memberRepository.existsByPlanIdAndUserId(planId, req.getUserId());

            if (!exists) {
                memberRepository.save(
                        PlanMember.builder()
                                .plan(plan)
                                .userId(req.getUserId())
                                .role(assignedRole)
                                .build());
            } else {
                PlanMember member = memberRepository.findByPlanIdAndUserId(planId, req.getUserId())
                        .orElseThrow(() -> new RuntimeException("Member not found"));

                if (member.getRole() == PlanRole.OWNER) {
                    throw new RuntimeException("Cannot change role of the owner.");
                }

                member.setRole(assignedRole);
                memberRepository.save(member);
            }

            req.setStatus(PlanRequestStatus.APPROVED);

            mailService.sendApproveEmail(
                    targetUser.getEmail(),
                    plan.getTitle(),
                    assignedRole.name());

            return;
        }

        throw new RuntimeException("Invalid action: " + action.getAction());
    }

    // helpers

    private PlanList mustLoadListInPlan(Long planId, Long listId) {
        PlanList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found"));
        if (!list.getPlan().getId().equals(planId)) {
            throw new RuntimeException("List not in plan");
        }
        return list;
    }

    private PlanCard mustLoadCardInList(Long planId, Long listId, Long cardId) {
        PlanList list = mustLoadListInPlan(planId, listId);
        PlanCard card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        if (!card.getList().getId().equals(list.getId())) {
            throw new RuntimeException("Card not in list");
        }
        return card;
    }

    @Data
    @AllArgsConstructor
    private static class MemberAgg {
        long shareActual;
        int activityCount;

        MemberAgg() {
            this.shareActual = 0L;
            this.activityCount = 0;
        }
    }

    private PlanMemberCostSummaryDto buildMemberCostSummary(Plan plan, List<PlanList> lists) {
        Long planId = plan.getId();

        List<PlanMember> members = memberRepository.findByPlanId(planId);

        // Nếu không có member thì vẫn trả owner
        if (members.isEmpty() && plan.getAuthorId() != null) {
            PlanMember ownerMember = PlanMember.builder()
                    .plan(plan)
                    .userId(plan.getAuthorId())
                    .role(PlanRole.OWNER)
                    .build();
            members = List.of(ownerMember);
        }

        Map<Long, MemberAgg> aggMap = new HashMap<>();
        for (PlanMember m : members) {
            aggMap.put(m.getUserId(), new MemberAgg());
        }

        // Duyệt tất cả card (bỏ TRASH), cộng shareActual + activityCount
        for (PlanList list : lists) {
            if (list.getType() == PlanListType.TRASH)
                continue;

            for (PlanCard card : list.getCards()) {
                if (card.getSplitDetails() == null || card.getSplitDetails().isEmpty())
                    continue;

                Set<Long> countedInThisCard = new HashSet<>();

                for (CardSplitDetail detail : card.getSplitDetails()) {
                    Long uid = detail.getUserId();
                    if (uid == null)
                        continue;

                    MemberAgg agg = aggMap.get(uid);
                    if (agg == null) {
                        // userId trong splitDetails nhưng không còn là member (data cũ) → skip
                        continue;
                    }

                    long amount = detail.getAmount() != null ? detail.getAmount() : 0L;
                    agg.shareActual += amount;

                    if (countedInThisCard.add(uid)) {
                        agg.activityCount++;
                    }
                }
            }
        }

        Map<Long, UserProfileResponse> profileCache = new HashMap<>();

        List<PlanMemberCostSummaryDto.MemberCostDto> memberDtos = members.stream()
                .map(m -> {
                    Long uid = m.getUserId();
                    MemberAgg agg = aggMap.getOrDefault(uid, new MemberAgg());

                    UserProfileResponse profile = profileCache.computeIfAbsent(uid, id -> {
                        try {
                            return userProfileClient.getUserById(id);
                        } catch (Exception ex) {
                            return null;
                        }
                    });

                    String fullname = profile != null ? profile.getFullname() : null;
                    String email = profile != null ? profile.getEmail() : null;
                    String avatar = profile != null ? profile.getAvatar() : null;

                    return PlanMemberCostSummaryDto.MemberCostDto.builder()
                            .userId(uid)
                            .fullname(fullname)
                            .email(email)
                            .avatar(avatar)
                            .role(m.getRole().name())
                            .shareActual(agg.shareActual)
                            .activityCount(agg.activityCount)
                            .memberBudget(plan.getBudgetPerPerson())
                            .build();
                })
                .toList();

        return PlanMemberCostSummaryDto.builder()
                .planId(planId)
                .budgetCurrency(plan.getBudgetCurrency())
                .budgetTotal(plan.getBudgetTotal())
                .budgetPerPerson(plan.getBudgetPerPerson())
                .totalActualCost(plan.getTotalActualCost())
                .members(memberDtos)
                .build();
    }

}
