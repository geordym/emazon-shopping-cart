package com.emazon.shopping_cart.domain.ports.out;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.model.ShoppingCart;

import java.util.Optional;

public interface CartItemRepositoryPort {
    void addItemToCart(CartItem cartItem);
    Optional<CartItem> findByArticleIdAndShoppingCartId(Long articleId, Long shoppingCartId);
    void removeItemFromCart(Long cartItemId);

}
