package com.sorokaandriy.order_service.dto;

import lombok.Builder;

@Builder
public record OrderLineResponse(
    Long id,
    double quantity,
    Long productId
) {
}
