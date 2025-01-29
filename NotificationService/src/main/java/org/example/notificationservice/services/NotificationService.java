package org.example.notificationservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.notificationservice.entity.Notification;
import org.example.notificationservice.feign.Transaction;
import org.example.notificationservice.feign.clients.TransactionClient;
import org.example.notificationservice.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private TransactionClient transactionClient;

//    @KafkaListener(topics = "transactions-topic", groupId = "group_id")
//    public void consume(String transaction) {
//        System.out.println("Transaction created" + transaction);
//    }

    @KafkaListener(topics = "transactions-topic", groupId = "group_id")
    public void notify(String transactionId) {
        Transaction transaction = transactionClient.getTransactionById(Long.parseLong(transactionId));
        Notification notification = Notification.builder()
//                .type("Transaction: " + transaction.getType())
                .isRead(false)
                .message(transaction.getDescription())
                .build();
        notificationRepository.save(notification);
        System.out.println("Notification created" + notification.toString());
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


}
