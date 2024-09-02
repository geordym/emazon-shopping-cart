package com.emazon.shopping_cart.domain.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {
    private final Long id;
    private List<CartItem> cartItems;
    private final Long customerId;
    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

    private ShoppingCart(Builder builder) {
        this.id = builder.id;
        this.cartItems = builder.cartItems;
        this.customerId = builder.customerId;
        this.updatedAt = builder.updatedAt;
        this.createdAt = builder.createdAt;
    }

    public Long getId() {
        return id;
    }



    public Long getCustomerId() {
        return customerId;
    }



    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Builder estático
    public static class Builder {
        private Long id;
        private List<CartItem> cartItems;
        private Long customerId;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withCarItemList(List<CartItem> cartItems) {
            this.cartItems = cartItems;
            return this;
        }

        public Builder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }



        public Builder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ShoppingCart build() {
            // Aquí puedes añadir lógica de validación si es necesario
            return new ShoppingCart(this);
        }
    }




}
