package com.mravel.plan.service;

import com.mravel.plan.dto.board.*;
import com.mravel.plan.model.*;
import com.mravel.plan.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanBoardService {

    private final PlanRepository planRepository;
    private final PlanListRepository listRepository;
    private final PlanCardRepository cardRepository;
    private final PlanLabelRepository labelRepository;
    private final PlanInviteRepository inviteRepository;

    /* ========= Query board ========= */
    @Transactional
    public BoardResponse getBoard(Long planId) {
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

    /* ========= List CRUD ========= */
    @Transactional
    public ListDto createList(Long planId, CreateListRequest req) {
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
    public void renameList(Long planId, Long listId, RenameListRequest req) {
        PlanList list = mustLoadListInPlan(planId, listId);
        list.setTitle(req.getTitle());
        listRepository.save(list);
    }

    @Transactional
    public void deleteList(Long planId, Long listId) {
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

    /* ========= Card CRUD ========= */
    @Transactional
    public CardDto createCard(Long planId, Long listId, CreateCardRequest req) {
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
    public CardDto updateCard(Long planId, Long listId, Long cardId, UpdateCardRequest req) {
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
    public void deleteCard(Long planId, Long listId, Long cardId) {
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
    public CardDto duplicateCard(Long planId, Long listId, Long cardId) {
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

    /* ========= Drag & Drop ========= */
    @Transactional
    public BoardResponse reorder(Long planId, ReorderRequest req) {
        if ("list".equalsIgnoreCase(req.getType())) {
            List<PlanList> lists = listRepository.findByPlanIdOrderByPositionAsc(planId);
            PlanList moved = lists.remove(req.getSourceIndex().intValue());
            lists.add(req.getDestIndex(), moved);
            // reindex
            for (int i = 0; i < lists.size(); i++)
                lists.get(i).setPosition(i);
            return getBoard(planId);
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
            return getBoard(planId);
        }
        throw new IllegalArgumentException("Unknown reorder type");
    }

    /* ========= Labels ========= */
    @Transactional
    public LabelDto upsertLabel(Long planId, LabelUpsertRequest req) {
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
    public void deleteLabel(Long planId, Long labelId) {
        PlanLabel label = labelRepository.findById(labelId)
                .orElseThrow(() -> new RuntimeException("Label not found"));
        if (!label.getPlan().getId().equals(planId))
            throw new RuntimeException("Label not in plan");
        labelRepository.delete(label);
    }

    /* ========= Invites ========= */
    @Transactional
    public List<InviteDto> invite(Long planId, InviteRequest req) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        String role = Optional.ofNullable(req.getRole()).orElse("viewer");

        List<InviteDto> result = new ArrayList<>();
        for (String email : req.getEmails()) {
            PlanInvite inv = PlanInvite.builder()
                    .plan(plan)
                    .email(email)
                    .role(role)
                    .build();
            inviteRepository.save(inv);
            result.add(InviteDto.builder().id(inv.getId()).email(inv.getEmail()).role(inv.getRole()).build());
        }
        return result;
    }

    /* ========= Helpers ========= */
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
