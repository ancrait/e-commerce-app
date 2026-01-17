package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.dto.OrderLineRequest;
import com.sorokaandriy.order_service.model.Order;
import com.sorokaandriy.order_service.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine fromOrderLineRequestToOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productId(orderLineRequest.productsId())
                .build();
    }
}
