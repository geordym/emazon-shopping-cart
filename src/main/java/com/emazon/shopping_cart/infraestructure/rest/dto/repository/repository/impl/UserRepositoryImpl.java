package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.impl;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.User;
import com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryImpl() {
        // Datos de prueba
        User user = new User();
        user.setId("user123");
        user.setName("Geordy");
       // user.setEmail("geordy@example.com");
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }
}