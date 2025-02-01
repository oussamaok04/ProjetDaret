package org.example.groupservice;

import org.example.groupservice.entities.Group;
import org.example.groupservice.entities.Member;
import org.example.groupservice.feign.UserApp;
import org.example.groupservice.feign.clients.UserAppClient;
import org.example.groupservice.repositories.GroupRepo;
import org.example.groupservice.repositories.MemberRepo;
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
    @Autowired
    MemberRepo memberRepo;

    public static void main(String[] args) {
        SpringApplication.run(GroupServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserApp userApp1 = userAppClient.getUserById(1L);
        UserApp userApp2 = userAppClient.getUserById(2L);
        UserApp userApp3 = userAppClient.getUserById(3L);

        Group group1 = Group.builder()
                .name("groupe 1")
                .createdByUserId(1L)
                .createdAt(LocalDateTime.now())
                .description("Desc de groupe 1")
                .createdBy(userApp1)
                .build();

        groupRepo.save(group1);
        memberRepo.save(Member.builder()
                .group(group1)
                .role("ADMIN")
                .username("admin 1")
                .userId(1L)
                .userApp(userApp1)
                .build());

        Group group2 = Group.builder()
                .name("groupe 2")
                .createdByUserId(2L)
                .createdAt(LocalDateTime.now())
                .description("Desc de groupe 2")
                .createdBy(userApp2)
                .build();
        groupRepo.save(group2);
        memberRepo.save(Member.builder()
                .group(group2)
                .role("ADMIN")
                .username("admin 2")
                .userId(2L)
                .userApp(userApp2)
                .build());

        Group group3 = Group.builder()
                .name("groupe 3")
                .createdByUserId(3L)
                .createdAt(LocalDateTime.now())
                .description("Desc de groupe 3")
                .createdBy(userApp3)
                .build();
        groupRepo.save(group3);
        memberRepo.save(Member.builder()
                .group(group3)
                .role("ADMIN")
                .username("admin 3")
                .userId(3L)
                .userApp(userApp3)
                .build());
    }

}
