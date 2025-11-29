package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
import com.mravel.plan.dto.board.*;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PlanBoardService {

    private final PlanPermissionService permissionService;
    private final PlanRepository planRepository;
    private final PlanListRepository listRepository;
    private final PlanCardRepository cardRepository;
    private final PlanInviteRepository inviteRepository;
    private final PlanInviteTokenRepository inviteTokenRepo;
    private final PlanMemberRepository memberRepository;
    private final MailService mailService;
    private final UserProfileClient userProfileClient;
    private final PlanRequestRepository requestRepo;

    // ================== HELPER: LOAD LIST / CARD ==================

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

    // ================== BOARD ==================

    @Transactional
    public BoardResponse getBoard(Long planId, Long userId, boolean isFriend) {

        if (!permissionService.canView(planId, userId, isFriend)) {
            throw new RuntimeException("You don't have permission to view this board.");
        }

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanRole myRole = permissionService.getUserRole(planId, userId);

        return BoardResponse.builder()
                .planId(plan.getId())
                .planTitle(plan.getTitle())
                .description(plan.getDescription())
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .budgetCurrency(plan.getBudgetCurrency())
                .budgetTotal(plan.getBudgetTotal())
                .totalEstimatedCost(plan.getTotalEstimatedCost())
                .totalActualCost(plan.getTotalActualCost())
                .status(plan.getStatus().name())
                .thumbnail(plan.getThumbnail())
                .images(plan.getImages())
                .myRole(myRole != null ? myRole.name() : null)
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
                .invites(
                        inviteRepository.findByPlanId(planId).stream()
                                .map(inv -> InviteDto.builder()
                                        .id(inv.getId())
                                        .email(inv.getEmail())
                                        .role(inv.getRole())
                                        .build())
                                .toList())
                .build();
    }

    // ================== MAPPING: ENTITY -> DTO ==================

    private CardPersonRefDto toPersonDto(CardPersonRef ref) {
        if (ref == null)
            return null;
        return CardPersonRefDto.builder()
                .memberId(ref.getMemberId())
                .displayName(ref.getDisplayName())
                .external(ref.getExternal())
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
        return CardSplitDetailDto.builder()
                .person(toPersonDto(d.getPerson()))
                .amount(d.getAmount())
                .build();
    }

    private PlanCardPaymentDto toPaymentDto(PlanCardPayment p) {
        return PlanCardPaymentDto.builder()
                .payer(toPersonDto(p.getPayer()))
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
                                .estimatedCost(c.getEstimatedCost()) // chi phí ước lượng
                                .actualCost(c.getActualCost()) // BE đã tính
                                .budgetAmount(c.getBudgetAmount()) // ngân sách activity
                                .participantCount(c.getParticipantCount())
                                .participants(
                                        c.getParticipants().stream()
                                                .map(this::toPersonDto)
                                                .toList())
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
                                        c.getSplitMembers().stream()
                                                .map(this::toPersonDto)
                                                .toList())
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

    // ================== MAPPING: DTO -> ENTITY ==================

    private CardPersonRef fromPersonDto(CardPersonRefDto dto) {
        if (dto == null)
            return null;
        return CardPersonRef.builder()
                .memberId(dto.getMemberId())
                .displayName(dto.getDisplayName())
                .external(dto.getExternal())
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

        // Currency
        if (dto.getCurrencyCode() != null && !dto.getCurrencyCode().isBlank()) {
            card.setCurrencyCode(dto.getCurrencyCode());
        }

        // Estimated & Budget
        card.setEstimatedCost(dto.getEstimatedCost());
        card.setBudgetAmount(dto.getBudgetAmount());
        card.setParticipantCount(dto.getParticipantCount());

        // Participants
        card.getParticipants().clear();
        if (dto.getParticipants() != null) {
            dto.getParticipants().forEach(p -> card.getParticipants().add(fromPersonDto(p)));
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

        // Nếu participantCount null nhưng có participants → auto sync
        if (card.getParticipantCount() == null && card.getParticipants() != null) {
            card.setParticipantCount(card.getParticipants().size());
        }
    }

    private void applySplit(PlanCard card, PlanCardSplitConfigDto dto) {
        if (dto == null)
            return;

        card.setSplitType(dto.getSplitType());
        card.setIncludePayerInSplit(dto.isIncludePayerInSplit());

        if (dto.getPayerId() != null) {
            card.setPayerId(dto.getPayerId());
        }

        // splitMembers
        card.getSplitMembers().clear();
        if (dto.getSplitMembers() != null) {
            dto.getSplitMembers().forEach(p -> card.getSplitMembers().add(fromPersonDto(p)));
        }

        // payments
        card.getPayments().clear();
        if (dto.getPayments() != null) {
            dto.getPayments().forEach(pDto -> {
                PlanCardPayment payment = PlanCardPayment.builder()
                        .card(card)
                        .payer(fromPersonDto(pDto.getPayer()))
                        .amount(pDto.getAmount())
                        .note(pDto.getNote())
                        .build();
                card.getPayments().add(payment);
            });
        }

        // splitDetails:
        // - EXACT: FE có thể gửi sẵn; ta lưu luôn
        // - EVEN: sẽ tính lại trong recalculateSplitDetails
        if (dto.getSplitDetails() != null && dto.getSplitType() == SplitType.EXACT) {
            card.getSplitDetails().clear();
            dto.getSplitDetails().forEach(d -> card.getSplitDetails().add(
                    CardSplitDetail.builder()
                            .person(fromPersonDto(d.getPerson()))
                            .amount(d.getAmount())
                            .build()));
        }
    }

    // ================== COST & SPLIT RE-CALC ==================

    /**
     * Tính lại actualCost cho card:
     * actualCost = estimatedCost + sum(extra.actualAmount)
     */
    private void recalculateCardCosts(PlanCard card) {
        long est = card.getEstimatedCost() != null ? card.getEstimatedCost() : 0L;

        long extraActual = card.getExtraCosts().stream()
                .mapToLong(e -> e.getActualAmount() != null ? e.getActualAmount() : 0L)
                .sum();

        card.setActualCost(est + extraActual);
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

    /**
     * Tính lại splitDetails dựa trên actualCost (ưu tiên) hoặc estimatedCost.
     */
    private void recalculateSplitDetails(PlanCard card) {
        SplitType type = card.getSplitType();
        if (type == null || type == SplitType.NONE) {
            card.getSplitDetails().clear();
            return;
        }

        // Với EXACT: giữ nguyên những gì applySplit đã set, không đụng vào
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

        List<CardPersonRef> members = new ArrayList<>(card.getSplitMembers());
        if (members.isEmpty()) {
            card.getSplitDetails().clear();
            return;
        }

        card.getSplitDetails().clear();

        switch (type) {
            case EVEN -> splitEven(card, members, total);
            // các kiểu khác (PERCENT, SHARES...) bổ sung thêm tại đây
            default -> {
            }
        }
    }

    private void splitEven(PlanCard card, List<CardPersonRef> members, long total) {
        int n = members.size();
        long baseShare = total / n;
        long remainder = total % n;

        for (int i = 0; i < n; i++) {
            CardPersonRef p = members.get(i);
            long amount = baseShare + (i < remainder ? 1 : 0);

            card.getSplitDetails().add(
                    CardSplitDetail.builder()
                            .person(p)
                            .amount(amount)
                            .build());
        }
    }

    // ================== COST SUMMARY ==================

    @Transactional
    public PlanCostSummaryDto getCostSummary(Long planId, Long userId, boolean isFriend) {
        if (!permissionService.canView(planId, userId, isFriend)) {
            throw new RuntimeException("You don't have permission to view this board.");
        }

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Map<PlanActivityType, long[]> byType = new EnumMap<>(PlanActivityType.class);
        Map<LocalDate, long[]> byDay = new HashMap<>();

        for (PlanList list : plan.getLists()) {
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

    // ================== CRUD LIST ==================

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
    }

    // ================== CRUD CARD ==================

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

        return toCardDto(card);
    }

    @Transactional
    public CardDto toggleDone(Long planId, Long listId, Long cardId, Long userId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanCard card = mustLoadCardInList(planId, listId, cardId);
        card.setDone(!card.isDone());
        cardRepository.save(card);

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
        original.getParticipants().forEach(p -> copy.getParticipants().add(
                CardPersonRef.builder()
                        .memberId(p.getMemberId())
                        .displayName(p.getDisplayName())
                        .external(p.getExternal())
                        .build()));

        // copy extra costs
        original.getExtraCosts().forEach(e -> copy.getExtraCosts().add(
                ExtraCost.builder()
                        .reason(e.getReason())
                        .type(e.getType())
                        .estimatedAmount(e.getEstimatedAmount())
                        .actualAmount(e.getActualAmount())
                        .build()));

        // copy split members (không copy payments)
        original.getSplitMembers().forEach(p -> copy.getSplitMembers().add(
                CardPersonRef.builder()
                        .memberId(p.getMemberId())
                        .displayName(p.getDisplayName())
                        .external(p.getExternal())
                        .build()));

        // tính lại để đảm bảo logic mới
        recalculateCardCosts(copy);
        recalculateSplitDetails(copy);

        cardRepository.save(copy);
        recalculatePlanTotals(plan);

        return toCardDto(copy);
    }

    // ================== REORDER (DRAG & DROP) ==================

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

        return getBoard(planId, userId, isFriend);
    }

    // ================== INVITES ==================

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

    // ================== SHARE / MEMBERS ==================

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

    // ================== REQUEST ACCESS ==================

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
        UserProfileResponse owner = userProfileClient.getUserById(plan.getAuthor().getId());

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

    // ================== OTHER HELPERS ==================

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

    private LocalTime parseTime(String hhmm) {
        if (hhmm == null || hhmm.isBlank())
            return null;
        return LocalTime.parse(hhmm);
    }
}
