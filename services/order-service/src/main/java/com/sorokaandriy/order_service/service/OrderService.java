package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.customer.CustomerResponse;
import com.sorokaandriy.order_service.dto.*;
import com.sorokaandriy.order_service.exception.BusinessException;
import com.sorokaandriy.order_service.customer.CustomerClient;
import com.sorokaandriy.order_service.exception.OrderNotFoundException;
import com.sorokaandriy.order_service.kafka.OrderConfirmation;
import com.sorokaandriy.order_service.kafka.OrderProducer;
import com.sorokaandriy.order_service.model.Order;
import com.sorokaandriy.order_service.payment.PaymentClient;
import com.sorokaandriy.order_service.product.ProductClient;
import com.sorokaandriy.order_service.product.PurchaseResponse;
import com.sorokaandriy.order_service.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public OrderService(OrderRepository orderRepository, CustomerClient customerClient, ProductClient productClient, PaymentClient paymentClient, OrderMapper mapper, OrderLineService orderLineService, OrderProducer orderProducer){
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.paymentClient = paymentClient;
        this.mapper = mapper;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
    }

    public @Nullable OrderResponse createOrder(@Valid OrderRequest orderRequest) {
        // OpenFeign
        CustomerResponse customer = customerClient.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order, no customer exists with provided id " +
                        orderRequest.customerId()));

        // RestTemplate
        List<PurchaseResponse> purchaseProducts = productClient.purchaseProducts(orderRequest.products());


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

        log.info("{}", customer);
        log.info("{}", purchaseProducts);

        var paymentRequest = paymentClient.createPayment(
                PaymentRequest.builder()
                        .amount(orderRequest.amount())
                        .paymentMethod(orderRequest.paymentMethod())
                        .orderId(order.getId())
                        .orderReference(orderRequest.reference())
                        .customer(customer)
                        .build()
        );



        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return mapper.fromOrderToOrderResponse(order);
    }


    public @Nullable List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrderToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findAllById(Long id) {
        return mapper.fromOrderToOrderResponse(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " doesn't exist")));
    }
}