package com.sorokaandriy.notification_service.repository;

import com.sorokaandriy.notification_service.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
