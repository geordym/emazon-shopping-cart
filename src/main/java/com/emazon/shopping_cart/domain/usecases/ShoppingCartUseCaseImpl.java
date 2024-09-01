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
        cartItemRepositoryPort.addItemToCart(shoppingCartUser.getId(),
                cartItem.getIdArticle(),
                cartItem.getQuantity(),
                cartItem.getPrice()
                );

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

}
