package org.example.transactionservice.dto;

public record TransactionDTO(
    Double amount,
    String type,
    String description,
    Long senderId,
    Long recieverId,
    Long groupId
)
{
}
