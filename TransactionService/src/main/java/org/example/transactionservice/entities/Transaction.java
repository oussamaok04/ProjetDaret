package org.example.transactionservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.transactionservice.enums.Type;
import org.example.transactionservice.fein.Group;
import org.example.transactionservice.fein.UserApp;

import java.time.LocalDateTime;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String type;
    private String description;
    private Long senderId;
    private Long recieverId;
    private Long groupId;
    private LocalDateTime sentAt = LocalDateTime.now();

    @Transient
    private UserApp sender;
    @Transient
    private UserApp reciever;
    @Transient
    private Group group;
}
