package com.sorokaandriy.payment_service.kafka;

import com.sorokaandriy.payment_service.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastName,
        String customerEmail
) {


}
