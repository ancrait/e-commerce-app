package com.sorokaandriy.notification_service.kafka;

import com.sorokaandriy.notification_service.model.Notification;
import com.sorokaandriy.notification_service.model.NotificationType;
import com.sorokaandriy.notification_service.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    public NotificationConsumer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation){
        log.info("Consuming the message from payment-topic {}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // todo send email

    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessesNotification(OrderConfirmation orderConfirmation){
        log.info("Consuming the message from order-topic {}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // todo send email
    }
}
