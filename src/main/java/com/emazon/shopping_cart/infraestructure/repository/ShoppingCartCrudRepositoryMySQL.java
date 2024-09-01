package com.emazon.shopping_cart.infraestructure.repository;

import com.emazon.shopping_cart.infraestructure.entities.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartCrudRepositoryMySQL extends JpaRepository<ShoppingCartEntity, Long> {

    Optional<ShoppingCartEntity> findByCustomerId(Long customerId);


}
