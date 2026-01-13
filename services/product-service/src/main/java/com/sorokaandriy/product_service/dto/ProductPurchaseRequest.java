package com.sorokaandriy.product_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Long id,
        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}
