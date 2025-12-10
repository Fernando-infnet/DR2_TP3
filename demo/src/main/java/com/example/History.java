package com.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class History {

    private final UUID id;              
    private final String description;     
    private final BigDecimal amount;     
    private final int quantity;         
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt; 
    private final HistoryStatus status;  

    public History(UUID id, String description, BigDecimal amount, int quantity,
                  LocalDateTime createdAt, LocalDateTime updatedAt, HistoryStatus status) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public HistoryStatus getStatus() {
        return status;
    }

    // Retorna um novo registro com valor atualizado (imutabilidade)
    public History updateAmount(BigDecimal newAmount) {
        return new History(id, description, newAmount, quantity, createdAt, LocalDateTime.now(), status);
    }

    // Retorna um novo registro com status atualizado (imutabilidade)
    public History updateStatus(HistoryStatus newStatus) {
        return new History(id, description, amount, quantity, createdAt, LocalDateTime.now(), newStatus);
    }
}
