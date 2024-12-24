package org.example.transactionservice.fein.clients;

import org.example.transactionservice.fein.Group;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GROUP-SERVICE")
public interface GroupClient {

    @GetMapping("/groups/search/{id}")
    Group getGroupById(@PathVariable Long id);
}
