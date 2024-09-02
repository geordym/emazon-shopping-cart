package com.emazon.shopping_cart.infraestructure.adapter;


import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.infraestructure.entities.CartItemEntity;
import com.emazon.shopping_cart.infraestructure.entities.ShoppingCartEntity;
import com.emazon.shopping_cart.infraestructure.mapper.CartItemMapper;
import com.emazon.shopping_cart.infraestructure.mapper.ShoppingCartMapper;
import com.emazon.shopping_cart.infraestructure.repository.CartItemCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@RequiredArgsConstructor
public class CartItemRepositoryMySQLAdapter implements CartItemRepositoryPort{

    private final CartItemCrudRepositoryMySQL cartItemCrudRepositoryMySQL;

    @Override
    public void addItemToCart(CartItem cartItem) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setId(cartItem.getId());
        cartItemEntity.setArticleId(cartItem.getIdArticle());
        cartItemEntity.setQuantity(cartItem.getQuantity());
        cartItemEntity.setPrice(cartItem.getPrice());
        cartItemEntity.setShoppingCart(new ShoppingCartEntity(cartItem.getShoppingCart().getId()));
        cartItemCrudRepositoryMySQL.save(cartItemEntity);
    }


    @Override
    public Optional<CartItem> findByArticleIdAndShoppingCartId(Long articleId, Long shoppingCartId) {
        Optional<CartItemEntity> cartItemEntityOptional = cartItemCrudRepositoryMySQL.findByArticleIdAndShoppingCartId(articleId,shoppingCartId);
        if(cartItemEntityOptional.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(CartItemMapper.entityToDomain(cartItemEntityOptional.get(), false));
    }
}
