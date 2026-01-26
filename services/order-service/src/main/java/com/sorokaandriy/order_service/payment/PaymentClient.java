package com.sorokaandriy.order_service.payment;

import com.sorokaandriy.order_service.dto.PaymentRequest;
import com.sorokaandriy.order_service.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
     ResponseEntity<PaymentResponse> createPayment(
            @RequestBody PaymentRequest paymentRequest
    );


}
