package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.customer.CustomerResponse;
import com.sorokaandriy.order_service.dto.OrderRequest;
import com.sorokaandriy.order_service.dto.OrderResponse;
import com.sorokaandriy.order_service.model.Order;
import com.sorokaandriy.order_service.product.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMapper {


    public Order fromOrderRequestToOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .reference(orderRequest.reference())
                .paymentMethod(orderRequest.paymentMethod())
                .customerId(orderRequest.customerId())
                .totalAmount(orderRequest.amount())
                .build();

    }

    public OrderResponse fromOrderRequestToOrderResponse(OrderRequest request){
        return OrderResponse.builder()
                .id(request.id())
                .reference(request.reference())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();
    }

    public OrderResponse fromOrderToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .reference(order.getReference())
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .customerId(order.getCustomerId())
                .build();
    }
}
