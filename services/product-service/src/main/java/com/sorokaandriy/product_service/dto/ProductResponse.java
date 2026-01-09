package com.sorokaandriy.product_service.dto;

import com.sorokaandriy.product_service.category.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Category category
) {
}
