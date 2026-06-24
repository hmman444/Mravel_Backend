package com.mravel.realtime.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class StompAuthChannelInterceptorTest {

    private static final String SECRET = "mravel-super-secret-key-should-be-at-least-32-chars-test";
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private StompAuthChannelInterceptor interceptor;
    private MessageChannel channel;

    @BeforeEach
    void setUp() {
        // port 1 — không thể kết nối được, đảm bảo access-check luôn ném ConnectException
        // → fail-open (allow) thay vì nhận 401/403 từ service thật đang chạy
        interceptor = new StompAuthChannelInterceptor(
                SECRET,
                "http://localhost:1",
                "http://localhost:1",
                "http://localhost:1");
        channel = mock(MessageChannel.class);
    }

    // ── helpers ───────────────────────────────────────────────────────────────

    private String tokenFor(Long userId) {
        return Jwts.builder()
                .claim("id", userId)
                .setSubject("test@mravel.com")
                .signWith(SIGNING_KEY)
                .compact();
    }

    private Message<?> connectMsg(String authHeader) {
        StompHeaderAccessor acc = StompHeaderAccessor.create(StompCommand.CONNECT);
        if (authHeader != null) {
            acc.addNativeHeader("Authorization", authHeader);
        }
        acc.setSessionAttributes(new HashMap<>());
        return MessageBuilder.createMessage(new byte[0], acc.getMessageHeaders());
    }

    private Message<?> subscribeMsg(String destination, Map<String, Object> sessionAttrs) {
        StompHeaderAccessor acc = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
        acc.setDestination(destination);
        acc.setSessionAttributes(sessionAttrs);
        return MessageBuilder.createMessage(new byte[0], acc.getMessageHeaders());
    }

    // ── CONNECT ───────────────────────────────────────────────────────────────

    @Test
    void connect_validToken_setsUserIdInSession() {
        String token = tokenFor(42L);
        Message<?> msg = connectMsg("Bearer " + token);

        Message<?> result = interceptor.preSend(msg, channel);

        StompHeaderAccessor acc = StompHeaderAccessor.wrap(result);
        Map<String, Object> attrs = acc.getSessionAttributes();
        assertThat(attrs).isNotNull();
        assertThat(attrs.get("userId")).isEqualTo(42L);
        assertThat(attrs.get("accessToken")).isEqualTo(token);
    }

    @Test
    void connect_missingAuthHeader_throwsException() {
        Message<?> msg = connectMsg(null);

        assertThatThrownBy(() -> interceptor.preSend(msg, channel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing or invalid Authorization header");
    }

    @Test
    void connect_noBearer_throwsException() {
        Message<?> msg = connectMsg("Token abc123");

        assertThatThrownBy(() -> interceptor.preSend(msg, channel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing or invalid Authorization header");
    }

    @Test
    void connect_invalidToken_throwsException() {
        Message<?> msg = connectMsg("Bearer not-a-valid-jwt");

        assertThatThrownBy(() -> interceptor.preSend(msg, channel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid or expired JWT token");
    }

    @Test
    void connect_wrongSignature_throwsException() {
        Key wrongKey = Keys.hmacShaKeyFor("completely-different-secret-key-32-chars!".getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder().claim("id", 1L).signWith(wrongKey).compact();
        Message<?> msg = connectMsg("Bearer " + token);

        assertThatThrownBy(() -> interceptor.preSend(msg, channel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid or expired JWT token");
    }

    // ── SUBSCRIBE: board topic ─────────────────────────────────────────────────

    @Test
    void subscribe_boardTopic_noUserInSession_throwsException() {
        // session attrs kosong, không có userId
        Message<?> msg = subscribeMsg("/topic/plans/10/board", new HashMap<>());

        assertThatThrownBy(() -> interceptor.preSend(msg, channel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unauthenticated SUBSCRIBE to board topic");
    }

    @Test
    void subscribe_boardTopic_withUser_failOpenWhenServiceDown() {
        // plan-service không chạy -> fail-open -> trả lại message (không throw)
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("userId", 1L);
        attrs.put("accessToken", tokenFor(1L));
        Message<?> msg = subscribeMsg("/topic/plans/10/board", attrs);

        Message<?> result = interceptor.preSend(msg, channel);

        assertThat(result).isNotNull();
    }

    @Test
    void subscribe_boardV2Topic_withUser_failOpenWhenServiceDown() {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("userId", 1L);
        attrs.put("accessToken", tokenFor(1L));
        Message<?> msg = subscribeMsg("/topic/plans/10/board/v2", attrs);

        Message<?> result = interceptor.preSend(msg, channel);

        assertThat(result).isNotNull();
    }

    // ── SUBSCRIBE: user conversations topic ───────────────────────────────────

    @Test
    void subscribe_userConversationsTopic_ownUser_allowed() {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("userId", 5L);
        Message<?> msg = subscribeMsg("/topic/users/5/conversations", attrs);

        Message<?> result = interceptor.preSend(msg, channel);

        assertThat(result).isNotNull();
    }

    @Test
    void subscribe_userConversationsTopic_differentUser_throwsException() {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("userId", 5L); // session userId = 5
        Message<?> msg = subscribeMsg("/topic/users/99/conversations", attrs); // topic userId = 99

        assertThatThrownBy(() -> interceptor.preSend(msg, channel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Access denied to user conversations topic");
    }

    @Test
    void subscribe_userConversationsTopic_noUserInSession_throwsException() {
        Map<String, Object> attrs = new HashMap<>(); // no userId
        Message<?> msg = subscribeMsg("/topic/users/5/conversations", attrs);

        assertThatThrownBy(() -> interceptor.preSend(msg, channel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Access denied to user conversations topic");
    }

    // ── SUBSCRIBE: notifications topic (không kiểm tra phân quyền) ───────────

    @Test
    void subscribe_notificationsTopic_anyUser_allowed() {
        // Đây là lỗ hổng phân quyền: bất kỳ ai đã CONNECT cũng có thể subscribe
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("userId", 5L);
        Message<?> msg = subscribeMsg("/topic/users/99/notifications", attrs); // subscribe của người khác

        Message<?> result = interceptor.preSend(msg, channel);

        assertThat(result).isNotNull(); // không bị chặn — báo cáo là lỗ hổng
    }

    // ── Non-STOMP command passthrough ─────────────────────────────────────────

    @Test
    void nonStompMessage_passesThrough() {
        StompHeaderAccessor acc = StompHeaderAccessor.create(StompCommand.DISCONNECT);
        acc.setSessionAttributes(new HashMap<>());
        Message<?> msg = MessageBuilder.createMessage(new byte[0], acc.getMessageHeaders());

        Message<?> result = interceptor.preSend(msg, channel);

        assertThat(result).isNotNull();
    }
}
