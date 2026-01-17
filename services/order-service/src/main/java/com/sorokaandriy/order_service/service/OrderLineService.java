package com.sorokaandriy.order_service.service;

import com.sorokaandriy.order_service.dto.OrderLineRequest;
import com.sorokaandriy.order_service.model.OrderLine;
import com.sorokaandriy.order_service.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public OrderLineService(OrderLineRepository orderLineRepository, OrderLineMapper mapper) {
        this.orderLineRepository = orderLineRepository;
        this.mapper = mapper;
    }

    public OrderLine saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = mapper.fromOrderLineRequestToOrderLine(orderLineRequest);
        return orderLineRepository.save(orderLine);
    }


}
