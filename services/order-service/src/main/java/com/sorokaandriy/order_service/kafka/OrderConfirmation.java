package com.sorokaandriy.order_service.kafka;

import com.sorokaandriy.order_service.customer.CustomerResponse;
import com.sorokaandriy.order_service.model.PaymentMethod;
import com.sorokaandriy.order_service.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products

) {
}
