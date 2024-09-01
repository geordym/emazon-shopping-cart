package com.emazon.shopping_cart.domain.usecases.validators;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.model.ShoppingCart;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ShoppingCartRepositoryPort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ShoppingCartUseCaseValidator {


    private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;
    private final CartItemRepositoryPort cartItemRepositoryPort;


    public void validateAddItemToCart(CartItem cartItem){

    }

    public void validateRemoveItemFromCart(){

    }







}
