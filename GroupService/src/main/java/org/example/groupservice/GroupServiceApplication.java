package org.example.groupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GroupServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupServiceApplication.class, args);
    }

}
