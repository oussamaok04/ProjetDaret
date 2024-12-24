package org.example.transactionservice.dto.mappers;

import org.example.transactionservice.dto.TransactionDTO;
import org.example.transactionservice.entities.Transaction;
import org.example.transactionservice.fein.Group;
import org.example.transactionservice.fein.UserApp;
import org.example.transactionservice.fein.clients.GroupClient;
import org.example.transactionservice.fein.clients.UserAppClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DtoToTransaction {

    @Autowired
    private UserAppClient userAppClient;
    @Autowired
    private GroupClient groupClient;

    public Transaction convert(TransactionDTO dto){
        UserApp sender = userAppClient.getUserById(dto.senderId());
        UserApp reciever = userAppClient.getUserById(dto.recieverId());

        Group group = groupClient.getGroupById(dto.groupId());

        return Transaction.builder()
                .amount(dto.amount())
                .description(dto.description())
                .type(dto.type())
                .senderId(dto.senderId())
                .recieverId(dto.recieverId())
                .groupId(dto.groupId())
                .sender(sender)
                .reciever(reciever)
                .group(group)
                .sentAt(LocalDateTime.now())
                .build();
    }
}
