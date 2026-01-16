package com.sorokaandriy.order_service.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Long productId,
        String description,
        BigDecimal price,
        double quantity
) {
}
