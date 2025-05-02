package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);
}
