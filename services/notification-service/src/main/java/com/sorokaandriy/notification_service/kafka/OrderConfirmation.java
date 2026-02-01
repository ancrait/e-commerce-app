package com.sorokaandriy.notification_service.kafka;

import com.sorokaandriy.notification_service.dto.Customer;
import com.sorokaandriy.notification_service.dto.Product;
import com.sorokaandriy.notification_service.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customerResponse,
        List<Product> products

) {
}
