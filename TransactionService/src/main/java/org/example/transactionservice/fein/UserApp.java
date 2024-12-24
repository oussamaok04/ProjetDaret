package org.example.transactionservice.fein;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApp {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
