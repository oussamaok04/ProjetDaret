package org.example.transactionservice.fein.clients;

import org.example.transactionservice.fein.UserApp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserAppClient {

    @GetMapping("/users/search/{id}")
    UserApp getUserById(@PathVariable Long id);
}
