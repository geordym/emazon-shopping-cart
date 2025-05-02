package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.external;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailMessageServiceImpl {

    private final Random random = new Random();

    public void enviarMensaje(String message){
        if (random.nextBoolean()) {
            throw new RuntimeException();
        }
    }
}
