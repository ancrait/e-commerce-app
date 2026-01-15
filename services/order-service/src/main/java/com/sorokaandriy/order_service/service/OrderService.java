package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.customer.CustomerClient;
import com.sorokaandriy.order_service.dto.OrderRequest;
import com.sorokaandriy.order_service.dto.OrderResponse;
import com.sorokaandriy.order_service.repository.OrderRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;


    public OrderService(OrderRepository orderRepository, CustomerClient customerClient){
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
    }

    public @Nullable OrderResponse createOrder(@Valid OrderRequest orderRequest) {

    }
}
