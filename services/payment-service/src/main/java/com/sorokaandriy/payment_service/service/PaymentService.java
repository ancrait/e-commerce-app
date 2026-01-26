package com.sorokaandriy.payment_service.service;

import com.sorokaandriy.payment_service.dto.PaymentRequest;
import com.sorokaandriy.payment_service.dto.PaymentResponse;
import com.sorokaandriy.payment_service.kafka.NotificationProducer;
import com.sorokaandriy.payment_service.model.Payment;
import com.sorokaandriy.payment_service.repository.PaymentRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, NotificationProducer notificationProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.notificationProducer = notificationProducer;
    }

    public @Nullable PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.save(paymentMapper.fromPaymentRequestToPayment(paymentRequest));
        notificationProducer.sendPaymentNotification(paymentMapper.fromPaymentRequestToPaymentNotificationRequest(paymentRequest));

        return paymentMapper.fromPaymentToPaymentResponse(payment);
    }


}
