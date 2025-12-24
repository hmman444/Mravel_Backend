package com.mravel.booking.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        // giữ nguyên logic: Apache HC5 + Buffering để log request/response body
        HttpClient httpClient = HttpClients.createDefault();

        HttpComponentsClientHttpRequestFactory baseFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate rt = builder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(baseFactory))
                .build();

        rt.getInterceptors().add((request, body, execution) -> {
            LoggerFactory.getLogger("HTTP").info("=> {} {}", request.getMethod(), request.getURI());
            LoggerFactory.getLogger("HTTP").info("=> headers: {}", request.getHeaders());
            LoggerFactory.getLogger("HTTP").info("=> body: {}", new String(body, StandardCharsets.UTF_8));

            ClientHttpResponse response = execution.execute(request, body);

            byte[] respBody = response.getBody().readAllBytes();
            LoggerFactory.getLogger("HTTP").info("<= status: {}", response.getStatusCode());
            LoggerFactory.getLogger("HTTP").info("<= headers: {}", response.getHeaders());
            LoggerFactory.getLogger("HTTP").info("<= body: {}", new String(respBody, StandardCharsets.UTF_8));

            return new ClientHttpResponseWrapper(response, respBody);
        });

        return rt;
    }

    static class ClientHttpResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse delegate;
        private final byte[] body;

        ClientHttpResponseWrapper(ClientHttpResponse delegate, byte[] body) {
            this.delegate = delegate;
            this.body = (body == null) ? new byte[0] : body;
        }

        @Override
        public @NonNull InputStream getBody() {
            return new ByteArrayInputStream(body);
        }

        @Override
        public @NonNull HttpStatusCode getStatusCode() throws IOException {
            return delegate.getStatusCode();
        }

        /**
         * getRawStatusCode() bị deprecated từ Spring 6.
         * Giữ behaviour tương đương: trả về mã số của status hiện tại.
         */
        @Override
        public int getRawStatusCode() throws IOException {
            return getStatusCode().value();
        }

        @Override
        public @NonNull String getStatusText() throws IOException {
            return delegate.getStatusText();
        }

        @Override
        public void close() {
            delegate.close();
        }

        @Override
        public @NonNull HttpHeaders getHeaders() {
            return delegate.getHeaders();
        }
    }
}