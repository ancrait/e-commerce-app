package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.dto.OrderLineRequest;
import com.sorokaandriy.order_service.dto.OrderLineResponse;
import com.sorokaandriy.order_service.model.OrderLine;
import com.sorokaandriy.order_service.repository.OrderLineRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public OrderLineService(OrderLineRepository repository, OrderLineRepository orderLineRepository, OrderLineMapper mapper) {
        this.repository = repository;
        this.orderLineRepository = orderLineRepository;
        this.mapper = mapper;
    }

    public OrderLine saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = mapper.fromOrderLineRequestToOrderLine(orderLineRequest);
        return orderLineRepository.save(orderLine);
    }


    public @Nullable List<OrderLineResponse> findByOrderId(Long orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(orderLine -> mapper.fromOrderLineToOrderLineResponse(orderLine));
    }
}
