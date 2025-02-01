package org.example.transactionservice.fein.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.transactionservice.fein.Group;
import org.example.transactionservice.fein.UserApp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@FeignClient(name = "GROUP-SERVICE")
public interface GroupClient {

    @GetMapping("/groups/search/{id}")
    @CircuitBreaker(name = "groupBreaker", fallbackMethod = "getDefaultGroup")
    Group getGroupById(@PathVariable Long id);

    default Group getDefaultGroup(Long id, Exception e) {
        return Group.builder()
                .name("default Group name")
                .description("default Group description")
                .createdAt(LocalDateTime.now())
                .createdBy(null)
                .build();
    }
}
