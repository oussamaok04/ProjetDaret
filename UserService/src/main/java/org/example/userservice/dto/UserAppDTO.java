package org.example.userservice.dto;

import org.example.userservice.entities.UserApp;

import java.time.LocalDateTime;

public record UserAppDTO(
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber)
{
    public UserApp convertToEntity(){
        return UserApp.builder()
                .email(this.email())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                //TODO: handle password encryption when u switch to security
                .password(this.password())
                .firstName(this.firstName())
                .lastName(this.lastName())
                .phoneNumber(this.phoneNumber())
                .build();
    }
}
