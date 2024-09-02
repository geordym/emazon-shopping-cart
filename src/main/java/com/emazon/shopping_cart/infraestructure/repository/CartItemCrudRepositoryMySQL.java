package com.emazon.shopping_cart.infraestructure.repository;

import com.emazon.shopping_cart.infraestructure.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemCrudRepositoryMySQL extends JpaRepository<CartItemEntity, Long> {
    @Query("SELECT c FROM CartItemEntity c WHERE c.articleId = :articleId AND c.shoppingCart.id = :shoppingCartId")
    Optional<CartItemEntity> findByArticleIdAndShoppingCartId(
            @Param("articleId") Long articleId,
            @Param("shoppingCartId") Long shoppingCartId
    );

}
