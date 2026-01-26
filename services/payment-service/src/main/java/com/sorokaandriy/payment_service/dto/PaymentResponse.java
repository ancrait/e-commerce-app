package com.sorokaandriy.payment_service.dto;

import com.sorokaandriy.payment_service.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentResponse(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId
) {
}
