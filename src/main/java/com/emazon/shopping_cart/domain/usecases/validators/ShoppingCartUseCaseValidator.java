package com.emazon.shopping_cart.domain.usecases.validators;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.ports.out.StockRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ClientRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ShoppingCartRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ShoppingCartUseCaseValidator {


    private final ClientRepositoryPort clientRepositoryPort;
    private final StockRepositoryPort articleRepositoryPort;

    public void validateAddItemToCart(Long clientId, CartItem cartItem) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID must not be null");
        }
        if (cartItem == null) {
            throw new IllegalArgumentException("CartItem must not be null");
        }
        if (cartItem.getIdArticle() == null) {
            throw new IllegalArgumentException("Article ID must not be null");
        }
        if (cartItem.getQuantity() == null || cartItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer");
        }
        if (cartItem.getPrice() == null || cartItem.getPrice() <= 0.0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }

        if (!clientExists(clientId)) {
            throw new EntityNotFoundException("Client with ID " + clientId + " does not exist");
        }

        if (!articleExists(cartItem.getIdArticle())) {
            throw new EntityNotFoundException("Article with ID " + cartItem.getIdArticle() + " does not exist");
        }
    }


    private boolean clientExists(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID must not be null");
        }

        return clientRepositoryPort.existsById(clientId);
    }


    private boolean articleExists(Long articleId) {
        if (articleId == null) {
            throw new IllegalArgumentException("Article ID must not be null");
        }

        return articleRepositoryPort.existsArticleById(articleId);
    }


    public void validateRemoveItemFromCart(){

    }


}
