package com.emazon.shopping_cart.infraestructure.configuration;


import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.infraestructure.adapter.CartItemRepositoryMySQLAdapter;
import com.emazon.shopping_cart.infraestructure.repository.CartItemCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartItemBeanConfiguration {


    @Bean
    public CartItemRepositoryPort cartItemRepositoryPort(final CartItemCrudRepositoryMySQL cartItemCrudRepositoryMySQL){
        return new CartItemRepositoryMySQLAdapter(cartItemCrudRepositoryMySQL);
    }



}
