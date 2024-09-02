package com.emazon.shopping_cart.domain.usecases;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.model.ShoppingCart;
import com.emazon.shopping_cart.domain.ports.in.ShoppingCartUseCase;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ShoppingCartRepositoryPort;
import com.emazon.shopping_cart.infraestructure.mapper.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

    private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;
    private final CartItemRepositoryPort cartItemRepositoryPort;

    @Override
    public void addItemToCart(Long userId, CartItem cartItem) {
        ShoppingCart shoppingCartUser = getOrCreateShoppingCart(userId);
        CartItem cartItemToAdd = getCardItemByArticleIdAndShoppingCartIdOrCreate(
                cartItem.getIdArticle(), shoppingCartUser.getId(), cartItem, shoppingCartUser);

        cartItemRepositoryPort.addItemToCart(cartItemToAdd);
        shoppingCartRepositoryPort.updateShoppingCartUpdatedAt(shoppingCartUser.getId(),
                LocalDate.now());
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {

    }

    private ShoppingCart getOrCreateShoppingCart(Long userId) {
        return shoppingCartRepositoryPort
                .getShoppingCartByUserId(userId)
                .orElseGet(() -> shoppingCartRepositoryPort
                        .createShoppingCartForUser(userId, LocalDate.now(), LocalDate.now())
                );
    }


    private CartItem getCardItemByArticleIdAndShoppingCartIdOrCreate(Long articleId,
                                                                             Long shoppingCartId, CartItem cartItem, ShoppingCart shoppingCart) {
        return cartItemRepositoryPort
                .findByArticleIdAndShoppingCartId(articleId, shoppingCartId)
                .map(existingCartItem -> new CartItem.Builder()
                        .withId(existingCartItem.getId())
                        .withIdArticle(existingCartItem.getIdArticle())
                        .withPrice(existingCartItem.getPrice())
                        .withQuantity(existingCartItem.getQuantity() + cartItem.getQuantity()) // Actualiza la cantidad
                        .withShoppingCart(shoppingCart)
                        .build()
                )
                .orElseGet(() -> new CartItem.Builder()
                        .withId(null) // ID nulo si será generado automáticamente por la base de datos
                        .withIdArticle(articleId) // Establece el ID del artículo
                        .withPrice(cartItem.getPrice())
                        .withQuantity(cartItem.getQuantity())
                        .withShoppingCart(shoppingCart)
                        .build()
                );

    }



}
