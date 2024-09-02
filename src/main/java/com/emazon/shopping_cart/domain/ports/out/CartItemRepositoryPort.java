package com.emazon.shopping_cart.domain.ports.out;

import com.emazon.shopping_cart.domain.model.CartItem;

import java.util.Optional;

public interface CartItemRepositoryPort {
    public void addItemToCart(CartItem cartItem);
    Optional<CartItem> findByArticleIdAndShoppingCartId(Long articleId, Long shoppingCartId);

}
