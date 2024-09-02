package com.emazon.shopping_cart.domain.ports.out;

import com.emazon.shopping_cart.domain.model.ShoppingCart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ShoppingCartRepositoryPort {
    Optional<ShoppingCart> getShoppingCartById(Long shoppingCartId);
    Optional<ShoppingCart> getShoppingCartByUserId(Long userId);

    ShoppingCart createShoppingCartForUser(Long userId, LocalDateTime createdAt, LocalDateTime updatedAt);

    void updateShoppingCartUpdatedAt(Long shoppingCartId, LocalDateTime updatedAt);

}


