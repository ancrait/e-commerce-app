package com.sorokaandriy.order_service.controller;

import com.sorokaandriy.order_service.dto.OrderRequest;
import com.sorokaandriy.order_service.dto.OrderResponse;
import com.sorokaandriy.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody @Valid OrderRequest orderRequest
    ){
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findAllById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findAllById(id));
    }
}
