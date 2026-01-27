package com.sorokaandriy.notification_service.dto;

import java.math.BigDecimal;

public record Product(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
