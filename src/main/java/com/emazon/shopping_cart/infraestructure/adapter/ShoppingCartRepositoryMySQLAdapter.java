package com.emazon.shopping_cart.infraestructure.adapter;

import com.emazon.shopping_cart.domain.model.ShoppingCart;
import com.emazon.shopping_cart.domain.ports.out.ShoppingCartRepositoryPort;
import com.emazon.shopping_cart.infraestructure.entities.ShoppingCartEntity;
import com.emazon.shopping_cart.infraestructure.mapper.ShoppingCartMapper;
import com.emazon.shopping_cart.infraestructure.repository.ShoppingCartCrudRepositoryMySQL;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
public class ShoppingCartRepositoryMySQLAdapter implements ShoppingCartRepositoryPort {

    private final ShoppingCartCrudRepositoryMySQL shoppingCartCrudRepositoryMySQL;

    @Override
    public Optional<ShoppingCart> getShoppingCartById(Long shoppingCartId) {
        Optional<ShoppingCartEntity> shoppingCartEntityOptional = shoppingCartCrudRepositoryMySQL.findById(shoppingCartId);

        if(shoppingCartEntityOptional.isEmpty()){
            return Optional.empty();
        }

        ShoppingCartEntity shoppingCartEntity = shoppingCartEntityOptional.get();
        return Optional.of(ShoppingCartMapper.entityToDomain(shoppingCartEntity));
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartByUserId(Long customerId) {
        Optional<ShoppingCartEntity> shoppingCartEntityOptional = shoppingCartCrudRepositoryMySQL.findByCustomerId(customerId);

        if(shoppingCartEntityOptional.isEmpty()){
            return Optional.empty();
        }

        ShoppingCartEntity shoppingCartEntity = shoppingCartEntityOptional.get();
        return Optional.of(ShoppingCartMapper.entityToDomain(shoppingCartEntity));
    }

    @Transactional
    public ShoppingCart createShoppingCartForUser(Long userId, LocalDate createdAt, LocalDate updatedAt) {
        ShoppingCartEntity newShoppingCart = new ShoppingCartEntity();
        newShoppingCart.setCustomerId(userId);
        newShoppingCart.setCreatedAt(createdAt);
        newShoppingCart.setUpdatedAt(updatedAt);
        return ShoppingCartMapper.entityToDomain(shoppingCartCrudRepositoryMySQL.save(newShoppingCart));
    }

    @Transactional
    public void updateShoppingCartUpdatedAt(Long shoppingCartId, LocalDate updatedAt) {
        Optional<ShoppingCartEntity> optionalShoppingCart = shoppingCartCrudRepositoryMySQL.findById(shoppingCartId);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCartEntity shoppingCart = optionalShoppingCart.get();
            shoppingCart.setUpdatedAt(updatedAt);
            shoppingCartCrudRepositoryMySQL.save(shoppingCart);
        } else {
            throw new RuntimeException("Shopping cart with ID " + shoppingCartId + " not found.");
        }
    }

}
