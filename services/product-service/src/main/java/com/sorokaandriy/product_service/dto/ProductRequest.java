package com.sorokaandriy.product_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
     Long id,
     @NotNull(message = "Product name is required")
     String name,
     @NotNull(message = "Description of product is required")
     String description,
     @Positive(message = "Quantity of product should be positive")
     double availableQuantity,
     @Positive(message = "Price of product should be positive")
     BigDecimal price,
     @NotNull(message = "Product category is required")
     Long categoryId

    ){

}

