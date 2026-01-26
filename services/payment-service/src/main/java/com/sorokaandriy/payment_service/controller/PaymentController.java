package com.sorokaandriy.payment_service.controller;

import com.sorokaandriy.payment_service.dto.PaymentRequest;
import com.sorokaandriy.payment_service.dto.PaymentResponse;
import com.sorokaandriy.payment_service.model.Payment;
import com.sorokaandriy.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody PaymentRequest paymentRequest
    ){
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }
}
