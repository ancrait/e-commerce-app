package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.exception.BusinessException;
import com.sorokaandriy.order_service.customer.CustomerClient;
import com.sorokaandriy.order_service.dto.OrderRequest;
import com.sorokaandriy.order_service.dto.OrderResponse;
import com.sorokaandriy.order_service.product.ProductClient;
import com.sorokaandriy.order_service.repository.OrderRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;


    public OrderService(OrderRepository orderRepository, CustomerClient customerClient, ProductClient productClient){
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    public @Nullable OrderResponse createOrder(@Valid OrderRequest orderRequest) {
        // OpenFeign
        var customer = customerClient.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order, no customer exists with provided id " +
                       orderRequest.customerId()));
        // RestTemplate
        productClient.purchaseProducts(orderRequest.products());

    }
}
