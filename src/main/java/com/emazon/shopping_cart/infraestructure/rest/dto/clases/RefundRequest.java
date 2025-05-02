package com.emazon.shopping_cart.infraestructure.rest.dto.clases;

import java.time.LocalDateTime;

public class RefundRequest {
    private String userId;
    private double amount;
    private String status;
    private LocalDateTime createdAt;

    // Getters y Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}