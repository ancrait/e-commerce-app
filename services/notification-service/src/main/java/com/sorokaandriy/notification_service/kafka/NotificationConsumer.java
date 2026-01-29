package com.sorokaandriy.notification_service.kafka;

import com.sorokaandriy.notification_service.model.Notification;
import com.sorokaandriy.notification_service.model.NotificationType;
import com.sorokaandriy.notification_service.repository.NotificationRepository;
import com.sorokaandriy.notification_service.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationConsumer(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-topic", groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic {}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        String fullName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                fullName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference());
    }


    @KafkaListener(topics = "order-topic", groupId = "consumerGroup")
    public void consumeOrderSuccessesNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic {}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        String fullName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderSuccessEmail(
                orderConfirmation.customer().email(),
                fullName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products());
    }
}
