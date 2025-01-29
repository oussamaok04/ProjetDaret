package org.example.notificationservice.feign.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.notificationservice.feign.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("TRANSACTION-SERVICE")
public interface TransactionClient {
    @GetMapping("/transactions/search/{id}")
    @CircuitBreaker(name = "transactionBreaker", fallbackMethod = "getDefaultTransaction")
    Transaction getTransactionById(@PathVariable Long id);


    default Transaction getDefaultTransaction(Long id, Exception e) {
        return Transaction.builder()
                .type("DEFAULT")
                .description("Default Transaction")
                .build();
    }
}
