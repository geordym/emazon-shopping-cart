package com.emazon.shopping_cart.infraestructure.mapper;

import com.emazon.shopping_cart.domain.model.ShoppingCart;
import com.emazon.shopping_cart.infraestructure.entities.ShoppingCartEntity;

public class ShoppingCartMapper {

    public static ShoppingCart entityToDomain(ShoppingCartEntity shoppingCartEntity){

        return new ShoppingCart.Builder().
                withId(shoppingCartEntity.getId())
                .withCarItemList(CartItemMapper.entityToDomain(shoppingCartEntity.getCartItems()))
                .withCustomerId(shoppingCartEntity.getCustomerId())
                .withCreatedAt(shoppingCartEntity.getCreatedAt())
                .withUpdatedAt(shoppingCartEntity.getUpdatedAt())
                .build();
    }

}
