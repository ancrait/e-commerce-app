package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.dto.OrderLineRequest;
import com.sorokaandriy.order_service.dto.PurchaseRequest;
import com.sorokaandriy.order_service.exception.BusinessException;
import com.sorokaandriy.order_service.customer.CustomerClient;
import com.sorokaandriy.order_service.dto.OrderRequest;
import com.sorokaandriy.order_service.dto.OrderResponse;
import com.sorokaandriy.order_service.exception.OrderNotFoundException;
import com.sorokaandriy.order_service.kafka.OrderConfirmation;
import com.sorokaandriy.order_service.kafka.OrderProducer;
import com.sorokaandriy.order_service.model.Order;
import com.sorokaandriy.order_service.product.ProductClient;
import com.sorokaandriy.order_service.repository.OrderRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public OrderService(OrderRepository orderRepository, CustomerClient customerClient, ProductClient productClient, OrderMapper mapper, OrderLineService orderLineService, OrderProducer orderProducer){
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.mapper = mapper;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
    }

    public @Nullable OrderResponse createOrder(@Valid OrderRequest orderRequest) {
        // OpenFeign
        var customer = customerClient.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order, no customer exists with provided id " +
                       orderRequest.customerId()));
        // RestTemplate
        var purchaseProducts = productClient.purchaseProducts(orderRequest.products());

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

        //todo payment process

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return mapper.fromOrderRequestToOrderResponse(orderRequest);

    }


    public @Nullable List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> mapper.fromOrderToOrderResponse(order))
                .collect(Collectors.toList());
    }

    public OrderResponse findAllById(Long id) {
        return mapper.fromOrderToOrderResponse(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + "doesnt exists")));
    }
}
