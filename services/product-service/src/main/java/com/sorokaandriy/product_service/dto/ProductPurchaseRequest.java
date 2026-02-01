package com.sorokaandriy.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        @JsonProperty("productId")
        Long id,
        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}
