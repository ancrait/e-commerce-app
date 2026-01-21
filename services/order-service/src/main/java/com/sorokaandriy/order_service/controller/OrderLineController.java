package com.sorokaandriy.order_service.controller;

import com.sorokaandriy.order_service.dto.OrderLineResponse;
import com.sorokaandriy.order_service.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.repeat.RepeatSpec;

import java.util.List;

@RestController
@RequestMapping("/order-lines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id") Long orderId){
        ResponseEntity.ok(orderLineService.findByOrderId(orderId));
    }

}
