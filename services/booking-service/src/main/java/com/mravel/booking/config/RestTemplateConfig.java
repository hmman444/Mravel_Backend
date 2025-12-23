package com.mravel.booking.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
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
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
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
            this.body = body;
        }

        @Override public InputStream getBody() { return new ByteArrayInputStream(body); }
        @Override public HttpStatusCode getStatusCode() throws IOException { return delegate.getStatusCode(); }
        @Override public int getRawStatusCode() throws IOException { return delegate.getRawStatusCode(); }
        @Override public String getStatusText() throws IOException { return delegate.getStatusText(); }
        @Override public void close() { delegate.close(); }
        @Override public HttpHeaders getHeaders() { return delegate.getHeaders(); }
    }
}