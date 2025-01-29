package org.example.transactionservice.controllers;

import org.example.transactionservice.dto.TransactionDTO;
import org.example.transactionservice.entities.Transaction;
import org.example.transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/search/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/search/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/search/group/{groupId}")
    public List<Transaction> getTransactionsByGroupId(@PathVariable Long groupId) {
        return transactionService.getAllTransactionsByGroupId(groupId);
    }

    @GetMapping("/search/sender/{senderId}")
    public List<Transaction> getTransactionsBySenderId(@PathVariable Long senderId) {
        return transactionService.getAllTransactionsBySenderId(senderId);
    }

    @GetMapping("/search/reciever/{recieverId}")
    public List<Transaction> getTransactionsByRecieverId(@PathVariable Long recieverId) {
        return transactionService.getAllTransactionsByReceiverId(recieverId);
    }

    @GetMapping("/search")
    public List<Transaction> getTransactionsByType(@PathVariable String type) {
        return transactionService.getAllTransactionsByType(type);
    }

    @PostMapping("/save")
    public Transaction saveTransaction(@RequestBody TransactionDTO dto) {
        Transaction transaction = transactionService.saveTransaction(dto);
        kafkaTemplate.send("transactions-topic", transaction.getId().toString());
        return transaction;
    }

    @PutMapping("/update/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO dto) {
        return transactionService.updateTransaction(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteTransaction(id);
    }
}
