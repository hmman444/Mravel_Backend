// src/main/java/com/mravel/booking/config/PaymentConfig.java
package com.mravel.booking.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.mravel.booking.payment.vnpay.VnpayProperties;

@Configuration
@EnableConfigurationProperties({ VnpayProperties.class })
public class PaymentConfig {}