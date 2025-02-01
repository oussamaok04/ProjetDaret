package org.example.userservice;

import org.example.userservice.dto.UserAppDTO;
import org.example.userservice.entities.UserApp;
import org.example.userservice.repositories.UserAppRepo;
import org.example.userservice.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {
    @Autowired
    UserAppRepo userAppRepo;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userAppRepo.save(UserApp.builder()
                        .email("oussama.okbani1@gmail.com")
                        .firstName("Oussama1")
                        .lastName("Okbani1")
                        .phoneNumber("0684015748")
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .password("password")
                        .build());

        userAppRepo.save(UserApp.builder()
                .email("oussama.okbani2@gmail.com")
                .firstName("Oussama2")
                .lastName("Okbani2")
                .phoneNumber("0684015748")
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .password("password")
                .build());

        userAppRepo.save(UserApp.builder()
                .email("oussama.okbani3@gmail.com")
                .firstName("Oussama3")
                .lastName("Okbani3")
                .phoneNumber("0684015748")
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .password("password")
                .build());
    }
}
