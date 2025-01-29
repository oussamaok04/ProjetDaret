package org.example.groupservice;

import org.example.groupservice.entities.Group;
import org.example.groupservice.feign.UserApp;
import org.example.groupservice.feign.clients.UserAppClient;
import org.example.groupservice.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableFeignClients
public class GroupServiceApplication implements CommandLineRunner {
    @Autowired
    GroupRepo groupRepo;
    @Autowired
    UserAppClient userAppClient;

    public static void main(String[] args) {
        SpringApplication.run(GroupServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserApp userApp1 = userAppClient.getUserById(1L);
        UserApp userApp2 = userAppClient.getUserById(2L);
        UserApp userApp3 = userAppClient.getUserById(3L);

        groupRepo.save(Group.builder()
                            .name("groupe 1")
                            .createdByUserId(1L)
                            .createdAt(LocalDateTime.now())
                            .description("Desc de groupe 1")
                            .createdBy(userApp1)
                            .build());

        groupRepo.save(Group.builder()
                .name("groupe 2")
                .createdByUserId(2L)
                .createdAt(LocalDateTime.now())
                .description("Desc de groupe 2")
                .createdBy(userApp2)
                .build());

        groupRepo.save(Group.builder()
                .name("groupe 3")
                .createdByUserId(3L)
                .createdAt(LocalDateTime.now())
                .description("Desc de groupe 3")
                .createdBy(userApp3)
                .build());
    }

}
