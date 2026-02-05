package com.sorokaandriy.payment_service.service;

import com.sorokaandriy.payment_service.dto.PaymentRequest;
import com.sorokaandriy.payment_service.dto.PaymentResponse;
import com.sorokaandriy.payment_service.kafka.PaymentNotificationRequest;
import com.sorokaandriy.payment_service.model.Payment;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment fromPaymentRequestToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .paymentMethod(paymentRequest.paymentMethod())
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .build();
    }

    public @Nullable PaymentResponse fromPaymentToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .amount(payment.getAmount())
                .orderId(payment.getOrderId())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }

    public PaymentNotificationRequest fromPaymentRequestToPaymentNotificationRequest(PaymentRequest paymentRequest) {
        return PaymentNotificationRequest.builder()
                .orderReference(paymentRequest.orderReference())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .customerFirstname(paymentRequest.customer().firstname())
                .customerLastName(paymentRequest.customer().lastname())
                .customerEmail(paymentRequest.customer().email())
                .build();
    }
}
