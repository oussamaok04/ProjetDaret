package org.example.transactionservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.transactionservice.dto.TransactionDTO;
import org.example.transactionservice.dto.mappers.DtoToTransaction;
import org.example.transactionservice.entities.Transaction;
import org.example.transactionservice.enums.Type;
import org.example.transactionservice.fein.Group;
import org.example.transactionservice.fein.UserApp;
import org.example.transactionservice.fein.clients.GroupClient;
import org.example.transactionservice.fein.clients.UserAppClient;
import org.example.transactionservice.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private UserAppClient userAppClient;
    @Autowired
    private GroupClient groupClient;
    @Autowired
    private DtoToTransaction dtoToTransaction;

    public Transaction getTransactionById(Long id) {
        Transaction t = transactionRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Transaction with id " + id + " not found")
        );

        UserApp sender = userAppClient.getUserById(t.getSenderId());
        UserApp receiver = userAppClient.getUserById(t.getRecieverId());

        Group group = groupClient.getGroupById(t.getGroupId());

        t.setGroup(group);
        t.setSender(sender);
        t.setReciever(receiver);

        return t;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll().stream()
                .peek(
                        x -> {
                           UserApp sender = userAppClient.getUserById(x.getSenderId());
                           UserApp receiver = userAppClient.getUserById(x.getRecieverId());
                           Group group = groupClient.getGroupById(x.getGroupId());
                           x.setSender(sender);
                           x.setReciever(receiver);
                           x.setGroup(group);
                        }
                ).toList();

    }

    public Transaction saveTransaction(TransactionDTO dto) {
        return transactionRepo.save(dtoToTransaction.convert(dto));
    }

    public String deleteTransaction(Long id) {
        transactionRepo.deleteById(id);
        return "Transaction with id " + id + " deleted";
    }

    public Transaction updateTransaction(Long id, TransactionDTO dto) {
        Transaction t = transactionRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Transaction with id " + id + " not found")
        );

        Transaction modified = dtoToTransaction.convert(dto);

        t.setAmount(modified.getAmount());
        t.setSender(modified.getSender());
        t.setReciever(modified.getReciever());
        t.setDescription(modified.getDescription());
        t.setSenderId(modified.getSenderId());
        t.setRecieverId(modified.getRecieverId());
        t.setGroupId(modified.getGroupId());
        t.setGroup(modified.getGroup());

        return transactionRepo.save(t);
    }

    public List<Transaction> getAllTransactionsByGroupId(Long groupId) {
        return transactionRepo.findTransactionsByGroupId(groupId).stream()
                .peek(
                        x -> {
                            UserApp sender = userAppClient.getUserById(x.getSenderId());
                            UserApp receiver = userAppClient.getUserById(x.getRecieverId());
                            Group group = groupClient.getGroupById(x.getGroupId());
                            x.setSender(sender);
                            x.setReciever(receiver);
                            x.setGroup(group);
                        }
                ).toList();
    }

    public List<Transaction> getAllTransactionsBySenderId(Long senderId) {
        return transactionRepo.findTransactionsBySenderId(senderId).stream()
                .peek(
                        x -> {
                            UserApp sender = userAppClient.getUserById(x.getSenderId());
                            UserApp receiver = userAppClient.getUserById(x.getRecieverId());
                            Group group = groupClient.getGroupById(x.getGroupId());
                            x.setSender(sender);
                            x.setReciever(receiver);
                            x.setGroup(group);
                        }
                ).toList();
    }

    public List<Transaction> getAllTransactionsByReceiverId(Long receiverId) {
        return transactionRepo.findTransactionsByRecieverId(receiverId).stream()
                .peek(
                        x -> {
                            UserApp sender = userAppClient.getUserById(x.getSenderId());
                            UserApp receiver = userAppClient.getUserById(x.getRecieverId());
                            Group group = groupClient.getGroupById(x.getGroupId());
                            x.setSender(sender);
                            x.setReciever(receiver);
                            x.setGroup(group);
                        }
                ).toList();
    }

    public List<Transaction> getAllTransactionsByType(String type) {
        return transactionRepo.findTransactionsByType(type).stream()
                .peek(
                        x -> {
                            UserApp sender = userAppClient.getUserById(x.getSenderId());
                            UserApp receiver = userAppClient.getUserById(x.getRecieverId());
                            Group group = groupClient.getGroupById(x.getGroupId());
                            x.setSender(sender);
                            x.setReciever(receiver);
                            x.setGroup(group);
                        }
                ).toList();
    }


}
