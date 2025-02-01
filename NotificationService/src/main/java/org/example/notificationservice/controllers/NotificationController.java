package org.example.notificationservice.controllers;

import org.example.notificationservice.entity.Notification;
import org.example.notificationservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PatchMapping("/read/{id}")
    public Notification markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }

    @GetMapping("/search/all")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/search/reciever/{recieverId}")
    public List<Notification> getNotificationsByReciever(@PathVariable Long recieverId) {
        return notificationService.getNotificationByRecieverId(recieverId);
    }
}
