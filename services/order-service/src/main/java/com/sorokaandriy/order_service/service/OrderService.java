package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.customer.CustomerResponse;
import com.sorokaandriy.order_service.dto.OrderLineRequest;
import com.sorokaandriy.order_service.dto.PurchaseRequest;
import com.sorokaandriy.order_service.exception.BusinessException;
import com.sorokaandriy.order_service.customer.CustomerClient;
import com.sorokaandriy.order_service.dto.OrderRequest;
import com.sorokaandriy.order_service.dto.OrderResponse;
import com.sorokaandriy.order_service.model.Order;
import com.sorokaandriy.order_service.product.ProductClient;
import com.sorokaandriy.order_service.product.PurchaseResponse;
import com.sorokaandriy.order_service.repository.OrderRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;


    public OrderService(OrderRepository orderRepository, CustomerClient customerClient, ProductClient productClient, OrderMapper mapper, OrderLineService orderLineService){
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.mapper = mapper;
        this.orderLineService = orderLineService;
    }

    public @Nullable OrderResponse createOrder(@Valid OrderRequest orderRequest) {
        // OpenFeign
        var customer = customerClient.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order, no customer exists with provided id " +
                       orderRequest.customerId()));
        // RestTemplate
        productClient.purchaseProducts(orderRequest.products());

        Order order = orderRepository.save(mapper.fromOrderRequestToOrder(orderRequest));

        for (PurchaseRequest purchaseRequest: orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

    }


}
