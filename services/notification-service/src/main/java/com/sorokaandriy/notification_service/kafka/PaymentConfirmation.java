package com.sorokaandriy.notification_service.kafka;

import com.sorokaandriy.notification_service.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastName,
        String customerEmail
) {
}
