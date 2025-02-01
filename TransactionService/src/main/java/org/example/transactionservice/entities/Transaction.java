package org.example.transactionservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long senderId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long recieverId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long groupId;
    private LocalDateTime sentAt = LocalDateTime.now();

    @Transient
    private UserApp sender;
    @Transient
    private UserApp reciever;
    @Transient
    private Group group;
}
