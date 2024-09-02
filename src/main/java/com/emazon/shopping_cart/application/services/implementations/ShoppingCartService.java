package com.emazon.shopping_cart.application.services.implementations;

import com.emazon.shopping_cart.application.services.interfaces.IShoppingCartService;
import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.ports.in.ShoppingCartUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShoppingCartService implements IShoppingCartService {

    private final ShoppingCartUseCase shoppingCartUseCase;
    @Override
    public void addItemToCart(Long clientId, CartItem cartItem) {
        shoppingCartUseCase.addItemToCart(clientId,cartItem);
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {
        shoppingCartUseCase.removeItemFromCart(cartItemId);
    }

}
