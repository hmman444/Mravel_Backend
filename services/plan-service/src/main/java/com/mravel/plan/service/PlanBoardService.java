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
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
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

    private PlanList mustLoadList(Long planId, Long listId) {
        PlanList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found"));
        if (!list.getPlan().getId().equals(planId))
            throw new RuntimeException("List not part of this plan");
        return list;
    }

    private PlanCard mustLoadCard(Long planId, Long listId, Long cardId) {
        PlanList list = mustLoadList(planId, listId);
        PlanCard card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        if (!card.getList().getId().equals(list.getId()))
            throw new RuntimeException("Card not in this list");
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

    // board
    @Transactional
    public BoardResponse getBoard(Long planId, Long userId, boolean isFriend) {

        if (!permissionService.canView(planId, userId, isFriend))
            throw new RuntimeException("You don't have permission to view this board.");

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        PlanRole myRole = permissionService.getUserRole(planId, userId);

        return BoardResponse.builder()
                .planId(plan.getId())
                .planTitle(plan.getTitle())
                .description(plan.getDescription())
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .totalCost(plan.getTotalCost())
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

    private CardDto toCardDto(PlanCard c) {
        return CardDto.builder()
                .id(c.getId())
                .text(c.getText())
                .description(c.getDescription())
                .startTime(c.getStartTime())
                .endTime(c.getEndTime())
                .done(c.isDone())
                .position(c.getPosition())
                .activityType(c.getActivityType())
                .activityDataJson(c.getActivityDataJson())
                .estimatedCost(c.getEstimatedCost())
                .actualCost(c.getActualCost())
                .payerId(c.getPayerId())
                .splitType(c.getSplitType())
                .splitMembers(c.getSplitMembers())
                .splitResultJson(c.getSplitResultJson())
                .build();
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

        if (list.getType() == PlanListType.TRASH)
            throw new RuntimeException("Cannot rename trash");

        list.setTitle(req.getTitle());
    }

    @Transactional
    public void deleteList(Long planId, Long userId, Long listId) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanList target = mustLoadList(planId, listId);
        if (target.getType() == PlanListType.TRASH)
            throw new RuntimeException("Cannot delete trash list");

        List<PlanList> dayLists = findDayLists(planId);
        if (dayLists.size() == 1)
            throw new RuntimeException("Cannot delete the last day");

        PlanList trash = findTrash(planId);

        // move cards → trash
        List<PlanCard> cards = cardRepository.findByListIdOrderByPositionAsc(target.getId());
        int basePos = (int) cardRepository.countByListId(trash.getId());

        for (int i = 0; i < cards.size(); i++) {
            PlanCard c = cards.get(i);
            c.setList(trash);
            c.setPosition(basePos + i);
        }

        listRepository.delete(target);

        // sync
        syncDayLists(target.getPlan());
    }

    // crud card
    @Transactional
    public CardDto createCard(Long planId, Long listId, Long userId, CreateCardRequest req) {
        permissionService.checkPermission(planId, userId, PlanRole.EDITOR);

        PlanList list = mustLoadList(planId, listId);

        if (list.getType() == PlanListType.TRASH)
            throw new RuntimeException("Cannot create card in trash");

        int nextPos = (int) cardRepository.countByListId(listId);

        PlanCard card = PlanCard.builder()
                .list(list)
                .text(req.getText() != null ? req.getText() : "New card")
                .description(req.getDescription())
                .startTime(parseTime(req.getStart()))
                .endTime(parseTime(req.getEnd()))
                .done(false)
                .position(nextPos)
                .activityType(req.getActivityType())
                .activityDataJson(req.getActivityDataJson())
                .estimatedCost(req.getEstimatedCost())
                .actualCost(req.getActualCost())
                .payerId(req.getPayerId())
                .splitType(req.getSplitType())
                .splitMembers(req.getSplitMembers() != null ? new HashSet<>(req.getSplitMembers()) : new HashSet<>())
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

        if (req.getStart() != null)
            card.setStartTime(parseTime(req.getStart()));
        if (req.getEnd() != null)
            card.setEndTime(parseTime(req.getEnd()));

        if (req.getDone() != null)
            card.setDone(req.getDone());

        // activity
        if (req.getActivityType() != null)
            card.setActivityType(req.getActivityType());
        if (req.getActivityDataJson() != null)
            card.setActivityDataJson(req.getActivityDataJson());

        // cost
        if (req.getEstimatedCost() != null)
            card.setEstimatedCost(req.getEstimatedCost());
        if (req.getActualCost() != null)
            card.setActualCost(req.getActualCost());

        // payment
        if (req.getPayerId() != null)
            card.setPayerId(req.getPayerId());
        if (req.getSplitType() != null)
            card.setSplitType(req.getSplitType());
        if (req.getSplitMembers() != null)
            card.setSplitMembers(new HashSet<>(req.getSplitMembers()));
        if (req.getSplitResultJson() != null)
            card.setSplitResultJson(req.getSplitResultJson());

        // JPA dirty checking sẽ tự save vì @Transactional
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

        PlanCard card = mustLoadCard(planId, listId, cardId);
        int removedPos = card.getPosition();

        cardRepository.delete(card);

        List<PlanCard> cards = cardRepository.findByListIdOrderByPositionAsc(listId);
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setPosition(i);
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
                .startTime(original.getStartTime())
                .endTime(original.getEndTime())
                .done(false)
                .position(nextPos)

                .activityType(original.getActivityType())
                .activityDataJson(original.getActivityDataJson())
                .estimatedCost(original.getEstimatedCost())
                .actualCost(original.getActualCost())
                .payerId(original.getPayerId())
                .splitType(original.getSplitType())
                .splitMembers(
                        original.getSplitMembers() != null
                                ? new HashSet<>(original.getSplitMembers())
                                : new HashSet<>())
                .splitResultJson(original.getSplitResultJson())

                .build();

        cardRepository.save(copy);
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

    // ---------- LIST REORDER ----------
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

        if (req.getSourceIndex() == trashIndex || req.getDestIndex() == trashIndex)
            throw new RuntimeException("Cannot move trash");

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

        for (int i = 0; i < sourceCards.size(); i++)
            sourceCards.get(i).setPosition(i);

        if (!source.getId().equals(dest.getId())) {
            for (int i = 0; i < destCards.size(); i++)
                destCards.get(i).setPosition(i);
        }

        return getBoard(planId, userId, isFriend);
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

            // xóa cũ
            inviteTokenRepo.deleteByEmailAndPlanId(email, plan.getId());

            // token mới
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

            // mail
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
        return LocalTime.parse(hhmm);
    }

    @Transactional
    public ShareResponse getShareInfo(Long planId, Long userId) {
        // Kiểm tra quyền
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
                            .role(m.getRole().name()) // OWNER / EDITOR / VIEWER
                            .isCurrentUser(userId != null && userId.equals(m.getUserId()))
                            .build();
                })
                .toList();

        List<InviteDto> invites = inviteTokenRepo.findByPlanId(planId).stream()
                .filter(token -> !token.isUsed() &&
                        token.getExpiredAt().isAfter(Instant.now()))
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

        // xóa invite nếu có
        inviteTokenRepo.deleteByEmailAndPlanId(email, planId);
    }

    @Transactional
    public PlanRequestDto requestAccess(Long planId, Long userId, PlanRequestCreate req) {

        // không cho gửi khi đã là member
        PlanMember member = memberRepository.findByPlanIdAndUserId(planId, userId).orElse(null);

        // nếu đã là member
        if (member != null) {
            // Owner và Editor không cần xin quyền
            if (member.getRole() == PlanRole.OWNER || member.getRole() == PlanRole.EDITOR) {
                throw new RuntimeException("Bạn đã có quyền truy cập.");
            }

            // Viewer được phép gửi request EDIT
            if (member.getRole() == PlanRole.VIEWER) {
                if (req.getType() != PlanRequestType.EDIT) {
                    throw new RuntimeException("Bạn đã có quyền xem.");
                }
            }
        }

        // không cho gửi nhiều lần khi vẫn còn pending
        if (requestRepo.existsByPlanIdAndUserIdAndStatus(
                planId, userId, PlanRequestStatus.PENDING)) {
            throw new IllegalArgumentException("Bạn đã gửi yêu cầu trước đó.");
        }

        // tạo request
        PlanRequest entity = PlanRequest.builder()
                .planId(planId)
                .userId(userId)
                .type(req.getType())
                .status(PlanRequestStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        requestRepo.save(entity);

        // lấy thông tin để gửi mail
        Plan plan = planRepository.getReferenceById(planId);
        UserProfileResponse requester = userProfileClient.getUserById(userId);
        UserProfileResponse owner = userProfileClient.getUserById(plan.getAuthor().getId());

        // gửi mail thông báo cho owner
        mailService.sendRequestEmailToOwner(
                owner.getEmail(),
                plan.getTitle(),
                requester.getFullname(),
                requester.getEmail(),
                req.getType().name());

        // trả DTO enriched
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

        if (!req.getPlanId().equals(planId))
            throw new RuntimeException("Invalid plan");

        Plan plan = planRepository.getReferenceById(planId);
        UserProfileResponse targetUser = userProfileClient.getUserById(req.getUserId());

        // reject
        if (action.getAction().equalsIgnoreCase("REJECT")) {

            req.setStatus(PlanRequestStatus.REJECTED);

            // gửi mail
            mailService.sendRejectEmail(
                    targetUser.getEmail(),
                    plan.getTitle());

            return;
        }

        // approve
        if (action.getAction().equalsIgnoreCase("APPROVE")) {

            // parse role được owner chọn
            PlanRole assignedRole = PlanRole.VIEWER; // default

            if (action.getRole() != null) {
                try {
                    assignedRole = PlanRole.valueOf(action.getRole().toUpperCase());
                } catch (Exception ignored) {
                }
            }

            // nếu user chưa là member → tạo member
            boolean exists = memberRepository.existsByPlanIdAndUserId(planId, req.getUserId());

            if (!exists) {
                // chưa là member → tạo mới
                memberRepository.save(
                        PlanMember.builder()
                                .plan(plan)
                                .userId(req.getUserId())
                                .role(assignedRole)
                                .build());
            } else {
                // đã là viewer → phải UPDATE role
                PlanMember member = memberRepository.findByPlanIdAndUserId(planId, req.getUserId())
                        .orElseThrow(() -> new RuntimeException("Member not found"));

                // Không cho nâng Owner
                if (member.getRole() == PlanRole.OWNER) {
                    throw new RuntimeException("Cannot change role of the owner.");
                }

                member.setRole(assignedRole);
                memberRepository.save(member);
            }

            // cập nhật trạng thái request
            req.setStatus(PlanRequestStatus.APPROVED);

            // gửi mail thông báo
            mailService.sendApproveEmail(
                    targetUser.getEmail(),
                    plan.getTitle(),
                    assignedRole.name());

            return;
        }

        throw new RuntimeException("Invalid action: " + action.getAction());
    }

}
