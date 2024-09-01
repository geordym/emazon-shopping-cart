package com.emazon.shopping_cart.infraestructure.adapter;


import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.infraestructure.entities.CartItemEntity;
import com.emazon.shopping_cart.infraestructure.entities.ShoppingCartEntity;
import com.emazon.shopping_cart.infraestructure.repository.CartItemCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class CartItemRepositoryMySQLAdapter implements CartItemRepositoryPort{

    private final CartItemCrudRepositoryMySQL cartItemCrudRepositoryMySQL;

    @Override
    public void addItemToCart(Long shoppingCartId, Long articleId, int quantity, Double price) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setArticleId(articleId);
        cartItemEntity.setQuantity(quantity);
        cartItemEntity.setPrice(price);
        cartItemEntity.setShoppingCart(new ShoppingCartEntity(shoppingCartId));
        cartItemCrudRepositoryMySQL.save(cartItemEntity);
    }

}
