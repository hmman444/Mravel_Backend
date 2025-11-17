package com.mravel.plan.service;

import com.mravel.plan.dto.board.*;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mravel.plan.service.PlanPermissionService;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanBoardService {
    private final PlanPermissionService permissionService;
    private final PlanRepository planRepository;
    private final PlanListRepository listRepository;
    private final PlanCardRepository cardRepository;
    private final PlanLabelRepository labelRepository;
    private final PlanInviteRepository inviteRepository;
    private final PlanInviteTokenRepository inviteTokenRepo;
    private final PlanMemberRepository memberRepository;
    private final MailService mailService;

    // board
    @Transactional
    public BoardResponse getBoard(Long planId, Long userId, boolean isFriend) {
        if (!permissionService.canView(planId, userId, isFriend))
            throw new RuntimeException("You don't have permission to view this board.");

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        return BoardResponse.builder()
                .planId(plan.getId())
                .planTitle(plan.getTitle())
                .lists(
                        listRepository.findByPlanIdOrderByPositionAsc(planId).stream()
                                .map(l -> ListDto.builder()
                                        .id(l.getId())
                                        .title(l.getTitle())
                                        .position(l.getPosition())
                                        .dayDate(l.getDayDate())
                                        .cards(l.getCards().stream()
                                                .sorted(Comparator.comparingInt(PlanCard::getPosition))
                                                .map(this::toCardDto)
                                                .toList())
                                        .build())
                                .toList())
                .labels(labelRepository.findByPlanId(planId).stream()
                        .map(lb -> LabelDto.builder()
                                .id(lb.getId()).text(lb.getText()).color(lb.getColor())
                                .build())
                        .toList())
                .invites(inviteRepository.findByPlanId(planId).stream()
                        .map(inv -> InviteDto.builder()
                                .id(inv.getId()).email(inv.getEmail()).role(inv.getRole())
                                .build())
                        .toList())
                .build();
    }

    private CardDto toCardDto(PlanCard c) {
        return CardDto.builder()
                .id(c.getId())
                .text(c.getText())
                .description(c.getDescription())
                .priority(c.getPriority())
                .start(c.getStartTime())
                .end(c.getEndTime())
                .done(c.isDone())
                .position(c.getPosition())
                .labels(
                        c.getLabels().stream()
                                .map(l -> LabelDto.builder()
                                        .id(l.getId()).text(l.getText()).color(l.getColor())
                                        .build())
                                .collect(Collectors.toSet()))
                .build();
    }

    // crud list
    @Transactional
    public ListDto createList(Long planId, Long userId, CreateListRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        int nextPos = (int) listRepository.countByPlanId(planId);
        PlanList list = PlanList.builder()
                .title(req.getTitle() == null ? ("Ngày " + (nextPos + 1)) : req.getTitle())
                .position(nextPos)
                .dayDate(req.getDayDate())
                .plan(plan)
                .build();
        listRepository.save(list);

        return ListDto.builder()
                .id(list.getId())
                .title(list.getTitle())
                .position(list.getPosition())
                .dayDate(list.getDayDate())
                .cards(List.of())
                .build();
    }

    @Transactional
    public void renameList(Long planId, Long listId, Long userId, RenameListRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanList list = mustLoadListInPlan(planId, listId);
        list.setTitle(req.getTitle());
        listRepository.save(list);
    }

    @Transactional
    public void deleteList(Long planId, Long userId, Long listId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanList list = mustLoadListInPlan(planId, listId);
        int removedPos = list.getPosition();
        listRepository.delete(list);

        // dồn lại position
        List<PlanList> lists = listRepository.findByPlanIdOrderByPositionAsc(planId);
        for (PlanList l : lists) {
            if (l.getPosition() > removedPos) {
                l.setPosition(l.getPosition() - 1);
            }
        }
    }

    // crud card
    @Transactional
    public CardDto createCard(Long planId, Long listId, Long userId, CreateCardRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanList list = mustLoadListInPlan(planId, listId);
        int nextPos = (int) cardRepository.countByListId(listId);

        PlanCard card = PlanCard.builder()
                .list(list)
                .text(Objects.requireNonNullElse(req.getText(), "New card"))
                .description(req.getDescription())
                .priority(req.getPriority())
                .startTime(parseTime(req.getStart()))
                .endTime(parseTime(req.getEnd()))
                .done(false)
                .position(nextPos)
                .build();

        cardRepository.save(card);
        return toCardDto(card);
    }

    @Transactional
    public CardDto updateCard(Long planId, Long listId, Long cardId, Long userId, UpdateCardRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanCard card = mustLoadCardInList(planId, listId, cardId);

        if (req.getText() != null)
            card.setText(req.getText());
        if (req.getDescription() != null)
            card.setDescription(req.getDescription());
        if (req.getPriority() != null)
            card.setPriority(req.getPriority());
        if (req.getStart() != null)
            card.setStartTime(parseTime(req.getStart()));
        if (req.getEnd() != null)
            card.setEndTime(parseTime(req.getEnd()));
        if (req.getDone() != null)
            card.setDone(req.getDone());

        if (req.getLabelIds() != null) {
            Set<PlanLabel> labels = new HashSet<>(labelRepository.findAllById(req.getLabelIds()));
            card.setLabels(labels);
        }
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

        PlanCard card = mustLoadCardInList(planId, listId, cardId);
        int removedPos = card.getPosition();
        cardRepository.delete(card);

        // dồn lại position
        List<PlanCard> cards = cardRepository.findByListIdOrderByPositionAsc(listId);
        for (PlanCard c : cards) {
            if (c.getPosition() > removedPos) {
                c.setPosition(c.getPosition() - 1);
            }
        }
    }

    @Transactional
    public CardDto duplicateCard(Long planId, Long listId, Long cardId, Long userId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanCard original = mustLoadCardInList(planId, listId, cardId);
        int nextPos = (int) cardRepository.countByListId(listId);

        PlanCard copy = PlanCard.builder()
                .list(original.getList())
                .text(original.getText() + " (Copy)")
                .description(original.getDescription())
                .priority(original.getPriority())
                .startTime(original.getStartTime())
                .endTime(original.getEndTime())
                .done(false)
                .position(nextPos)
                .labels(new HashSet<>(original.getLabels())) // copy nhãn
                .build();

        cardRepository.save(copy);
        return toCardDto(copy);
    }

    // drag drop
    @Transactional
    public BoardResponse reorder(Long planId, Long userId, boolean isFriend, ReorderRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        if ("list".equalsIgnoreCase(req.getType())) {
            List<PlanList> lists = listRepository.findByPlanIdOrderByPositionAsc(planId);
            PlanList moved = lists.remove(req.getSourceIndex().intValue());
            lists.add(req.getDestIndex(), moved);
            // reindex
            for (int i = 0; i < lists.size(); i++)
                lists.get(i).setPosition(i);
            return getBoard(planId, userId, isFriend);
        } else if ("card".equalsIgnoreCase(req.getType())) {
            PlanList source = mustLoadListInPlan(planId, req.getSourceListId());
            PlanList dest = mustLoadListInPlan(planId, req.getDestListId());

            List<PlanCard> sourceCards = cardRepository.findByListIdOrderByPositionAsc(source.getId());
            PlanCard moved = sourceCards.remove(req.getSourceIndex().intValue());

            List<PlanCard> destCards = source.getId().equals(dest.getId())
                    ? sourceCards
                    : cardRepository.findByListIdOrderByPositionAsc(dest.getId());

            // gán lại list nếu di chuyển cross-list
            moved.setList(dest);
            destCards.add(req.getDestIndex(), moved);

            // reindex 2 list
            for (int i = 0; i < sourceCards.size(); i++)
                sourceCards.get(i).setPosition(i);
            if (!source.getId().equals(dest.getId())) {
                for (int i = 0; i < destCards.size(); i++)
                    destCards.get(i).setPosition(i);
            }
            return getBoard(planId, userId, isFriend);
        }
        throw new IllegalArgumentException("Unknown reorder type");
    }

    // label
    @Transactional
    public LabelDto upsertLabel(Long planId, Long userId, LabelUpsertRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanLabel label = labelRepository.findByPlanIdAndText(planId, req.getText())
                .orElseGet(() -> PlanLabel.builder()
                        .plan(plan)
                        .text(req.getText())
                        .color(req.getColor())
                        .build());

        label.setColor(req.getColor());
        labelRepository.save(label);

        return LabelDto.builder()
                .id(label.getId())
                .text(label.getText())
                .color(label.getColor())
                .build();
    }

    @Transactional
    public void deleteLabel(Long planId, Long labelId, Long userId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanLabel label = labelRepository.findById(labelId)
                .orElseThrow(() -> new RuntimeException("Label not found"));
        if (!label.getPlan().getId().equals(planId))
            throw new RuntimeException("Label not in plan");
        labelRepository.delete(label);
    }

    // invites
    @Transactional
    public List<InviteDto> invite(Long planId, Long userId, InviteRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.OWNER);

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanRole role = Optional.ofNullable(req.getRole())
                .orElse(PlanRole.VIEWER);

        List<InviteDto> result = new ArrayList<>();

        for (String email : req.getEmails()) {
            if (inviteTokenRepo.existsByEmailAndPlanId(email, plan.getId())) {
                throw new RuntimeException("Email " + email + " is already invited.");
            }

            // 1. Tạo token
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

            // 2. Gửi email
            mailService.sendInviteEmail(email,
                    plan.getTitle(),
                    "http://localhost:5173/plans/join?token=" + token);

            // 3. Trả về invite DTO
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
        if (inv.getExpiredAt().isBefore(Instant.now()))
            throw new RuntimeException("Invite expired");

        boolean exists = memberRepository.existsByPlanIdAndUserId(planId, userId);
        if (exists) {
            inv.setUsed(true);
            return planId;
        }

        // tạo member
        PlanMember member = PlanMember.builder()
                .plan(inv.getPlan())
                .userId(userId)
                .role(inv.getRole())
                .build();
        memberRepository.save(member);

        inv.setUsed(true);

        return planId;
    }

    // helpers
    private PlanList mustLoadListInPlan(Long planId, Long listId) {
        PlanList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found"));
        if (!list.getPlan().getId().equals(planId))
            throw new RuntimeException("List not in plan");
        return list;
    }

    private PlanCard mustLoadCardInList(Long planId, Long listId, Long cardId) {
        PlanList list = mustLoadListInPlan(planId, listId);
        PlanCard card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        if (!card.getList().getId().equals(list.getId()))
            throw new RuntimeException("Card not in list");
        return card;
    }

    private LocalTime parseTime(String hhmm) {
        if (hhmm == null || hhmm.isBlank())
            return null;
        return LocalTime.parse(hhmm); // "HH:mm"
    }
}
