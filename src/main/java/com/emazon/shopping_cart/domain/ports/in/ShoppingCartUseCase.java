package com.emazon.shopping_cart.domain.ports.in;

import com.emazon.shopping_cart.domain.model.CartItem;

public interface ShoppingCartUseCase {

    void addItemToCart(Long clientId, CartItem cartItem);
    void removeItemFromCart(Long cartItemId);

}
