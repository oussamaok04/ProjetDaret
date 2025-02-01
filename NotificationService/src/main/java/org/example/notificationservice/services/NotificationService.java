package org.example.notificationservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.notificationservice.entity.Notification;
import org.example.notificationservice.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "transactions-topic", groupId = "group_id")
    public void notify(String transaction) throws JSONException {
        JSONObject json = new JSONObject(transaction);
        Notification notification = Notification.builder()
                .type(json.getString("type"))
                .isRead(false)
                .recieverId(json.getLong("recieverId"))
                .message(json.getString("description") + ", amount: " + json.getDouble("amount"))
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
        System.out.println("Notification created: " + notification.toString());
        System.out.println("Transaction created: " + transaction);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Notification with id " + id + " not found")
        );

        notification.setRead(true);
        return notification;
    }

    public List<Notification> getNotificationByRecieverId(Long id){
        return notificationRepository.getNotificationsByRecieverId(id);
    }


}
