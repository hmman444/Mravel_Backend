package com.mravel.realtime.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketMetricsListener {

    private final MeterRegistry meterRegistry;

    @EventListener
    public void onConnect(SessionConnectedEvent event) {
        Counter.builder("realtime.ws_connections")
                .tag("action", "connect")
                .register(meterRegistry)
                .increment();
        log.debug("WS connect: sessionId={}", event.getMessage().getHeaders().get("simpSessionId"));
    }

    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        Counter.builder("realtime.ws_connections")
                .tag("action", "disconnect")
                .register(meterRegistry)
                .increment();
        log.debug("WS disconnect: sessionId={} closeStatus={}", event.getSessionId(), event.getCloseStatus());
    }

    @EventListener
    public void onSubscribe(SessionSubscribeEvent event) {
        Counter.builder("realtime.ws_subscriptions")
                .tag("action", "subscribe")
                .register(meterRegistry)
                .increment();
    }
}
