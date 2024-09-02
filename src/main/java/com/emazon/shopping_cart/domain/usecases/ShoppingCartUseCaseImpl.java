package com.emazon.shopping_cart.domain.usecases;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.model.ShoppingCart;
import com.emazon.shopping_cart.domain.ports.in.ShoppingCartUseCase;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ShoppingCartRepositoryPort;
import com.emazon.shopping_cart.infraestructure.mapper.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.function.Function;

@RequiredArgsConstructor
public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

    private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;
    private final CartItemRepositoryPort cartItemRepositoryPort;

    @Override
    public void addItemToCart(Long userId, CartItem cartItem) {
        ShoppingCart shoppingCartUser = getOrCreateShoppingCart(userId);
        CartItem cartItemToAdd = findOrCreateCartItem(
                cartItem.getIdArticle(), shoppingCartUser.getId(), cartItem, shoppingCartUser);

        cartItemRepositoryPort.addItemToCart(cartItemToAdd);
        shoppingCartRepositoryPort.updateShoppingCartUpdatedAt(shoppingCartUser.getId(),
                LocalDate.now());
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {
        cartItemRepositoryPort.removeItemFromCart(cartItemId);
    }

    private ShoppingCart getOrCreateShoppingCart(Long userId) {
        return shoppingCartRepositoryPort
                .getShoppingCartByUserId(userId)
                .orElseGet(() -> shoppingCartRepositoryPort
                        .createShoppingCartForUser(userId, LocalDate.now(), LocalDate.now())
                );
    }


    private CartItem findOrCreateCartItem(Long articleId, Long shoppingCartId, CartItem cartItem, ShoppingCart shoppingCart) {
        return cartItemRepositoryPort
                .findByArticleIdAndShoppingCartId(articleId, shoppingCartId)
                .map(existingCartItem -> updateExistingCartItem(existingCartItem, cartItem, shoppingCart))
                .orElseGet(() -> createNewCartItem(articleId, cartItem, shoppingCart));
    }

    private CartItem updateExistingCartItem(CartItem existingCartItem, CartItem cartItem, ShoppingCart shoppingCart) {
        return new CartItem.Builder()
                .withId(existingCartItem.getId())
                .withIdArticle(existingCartItem.getIdArticle())
                .withPrice(existingCartItem.getPrice())
                .withQuantity(existingCartItem.getQuantity() + cartItem.getQuantity())
                .withShoppingCart(shoppingCart)
                .build();
    }

    private CartItem createNewCartItem(Long articleId, CartItem cartItem, ShoppingCart shoppingCart) {
        return new CartItem.Builder()
                .withId(null) // ID nulo para generación automática
                .withIdArticle(articleId)
                .withPrice(cartItem.getPrice())
                .withQuantity(cartItem.getQuantity())
                .withShoppingCart(shoppingCart)
                .build();
    }

}
