package com.emazon.shopping_cart.infraestructure.repository;

import com.emazon.shopping_cart.infraestructure.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemCrudRepositoryMySQL extends JpaRepository<CartItemEntity, Long> {


}
