package com.sorokaandriy.order_service.dto;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Long productId,
    @NotNull(message = "Quantity is mandatory")
    double quantity
) {
}
