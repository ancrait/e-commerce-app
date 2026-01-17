package com.sorokaandriy.order_service.dto;

public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productsId,
        double quantity
                               ) {
}
