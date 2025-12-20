// src/main/java/com/mravel/booking/payment/vnpay/VnpayProperties.java
package com.mravel.booking.payment.vnpay;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mravel.payment.vnpay")
public record VnpayProperties(
    String baseUrl,
    String tmnCode,
    String hashSecret,
    String returnUrl,
    String ipnUrl,
    String version,
    String command,
    String currCode,
    String locale,
    String orderType,
    Integer payExpireMinutes
) {}