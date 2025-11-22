package com.mravel.plan.service;

import com.mravel.common.response.UserProfileResponse;
import com.mravel.plan.client.UserProfileClient;
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
    private final UserProfileClient userProfileClient;
    private final PlanRequestRepository requestRepo;

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
                .myRole(myRole != null ? myRole.name() : null)
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
        return LocalTime.parse(hhmm); // "HH:mm"
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
