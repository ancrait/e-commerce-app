package com.sorokaandriy.order_service.dto;

import com.sorokaandriy.order_service.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {


}
