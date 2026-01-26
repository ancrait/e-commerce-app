package com.sorokaandriy.order_service.dto;


import com.sorokaandriy.order_service.customer.CustomerResponse;
import com.sorokaandriy.order_service.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer

) {
}
