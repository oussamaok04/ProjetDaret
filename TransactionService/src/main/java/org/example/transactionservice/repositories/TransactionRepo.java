package org.example.transactionservice.repositories;

import org.example.transactionservice.entities.Transaction;
import org.example.transactionservice.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionsByType(String type);
    List<Transaction> findTransactionsByGroupId(Long groupId);
    List<Transaction> findTransactionsBySenderId(Long senderId);
    List<Transaction> findTransactionsByRecieverId(Long recieverId);
}
