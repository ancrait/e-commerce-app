package com.sorokaandriy.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
         Long id,
         @NotNull(message = "Product name is required")
         String name,
         @NotNull(message = "Product description is required")
         String description,
         @Positive(message = "Product quantity should be positive")
         double availableQuantity,
         @Positive(message = "Product price should be positive")
         BigDecimal price,
         @NotNull(message = "Category id is required")
         Long categoryId
) {
}
