package com.emazon.shopping_cart.domain.ports.out;

import com.emazon.shopping_cart.domain.model.CartItem;

public interface CartItemRepositoryPort {
    public void addItemToCart(Long shoppingCartId, Long articleId, int quantity, Double price);

}
