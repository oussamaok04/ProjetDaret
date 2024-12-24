package org.example.transactionservice.fein;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private UserApp createdBy;
}
