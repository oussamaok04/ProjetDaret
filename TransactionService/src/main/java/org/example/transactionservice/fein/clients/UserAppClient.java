package org.example.transactionservice.fein.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.transactionservice.fein.UserApp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserAppClient {

    @GetMapping("/users/search/{id}")
    @CircuitBreaker(name = "userBreaker", fallbackMethod = "getDefaultUser")
    UserApp getUserById(@PathVariable Long id);

    default UserApp getDefaultUser(Long id, Exception e) {
        return UserApp.builder()
                .email("default@email.com")
                .firstName("default")
                .lastName("default")
                .phoneNumber("0000000000")
                .build();
    }
}
