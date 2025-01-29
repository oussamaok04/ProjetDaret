package org.example.notificationservice.feign;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Transaction {
    private Long id;
    private Double amount;
    private String type;
    private String description;
    private Long senderId;
    private Long recieverId;
    private Long groupId;
    private LocalDateTime sentAt;
}
