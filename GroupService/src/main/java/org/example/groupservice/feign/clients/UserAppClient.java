package org.example.groupservice.feign.clients;

import org.example.groupservice.feign.UserApp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserAppClient {

    @GetMapping("/users/search/{id}")
    UserApp getUserById(@PathVariable Long id);

}
