package com.emazon.shopping_cart.infraestructure.mapper;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.infraestructure.entities.CartItemEntity;
import com.emazon.shopping_cart.infraestructure.rest.dto.request.CardItemAddRequestDto;

import java.util.ArrayList;
import java.util.List;

public class CartItemMapper {

    public static CartItem entityToDomain(CartItemEntity cartItemEntity, boolean includeShoppingCart){

        if(includeShoppingCart){
            return new CartItem.Builder().withId(cartItemEntity.getId())
                    .withIdArticle(cartItemEntity.getArticleId())
                    .withPrice(cartItemEntity.getPrice())
                    .withQuantity(cartItemEntity.getQuantity())
                    .withShoppingCart(ShoppingCartMapper.entityToDomain(cartItemEntity.getShoppingCart()))
                    .build();
        } else {
            return new CartItem.Builder().withId(cartItemEntity.getId())
                    .withIdArticle(cartItemEntity.getArticleId())
                    .withPrice(cartItemEntity.getPrice())
                    .withQuantity(cartItemEntity.getQuantity())
                    .build();
        }


    }

    public static List<CartItem> entityToDomain(List<CartItemEntity> cartItemsEntity){
        if(cartItemsEntity == null){
            return new ArrayList<>();
        }
        List<CartItem> cartItems =
                cartItemsEntity.stream().map(cartItem -> CartItemMapper.entityToDomain(cartItem, false))
                        .toList();
        return cartItems;
    }

    public static CartItem dtoToDomain(CardItemAddRequestDto cardItemAddRequestDto){
        return new CartItem.Builder().withId(null)
                .withIdArticle(cardItemAddRequestDto.getArticle_id())
                .withQuantity(cardItemAddRequestDto.getQuantity())
                .withPrice(cardItemAddRequestDto.getPrice())
                .withShoppingCart(null)
                .build();
    }

}
