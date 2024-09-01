package com.emazon.shopping_cart.infraestructure.configuration;


import com.emazon.shopping_cart.application.services.implementations.ShoppingCartService;
import com.emazon.shopping_cart.application.services.interfaces.IShoppingCartService;
import com.emazon.shopping_cart.domain.ports.in.ShoppingCartUseCase;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ShoppingCartRepositoryPort;
import com.emazon.shopping_cart.domain.usecases.ShoppingCartUseCaseImpl;
import com.emazon.shopping_cart.infraestructure.adapter.ShoppingCartRepositoryMySQLAdapter;
import com.emazon.shopping_cart.infraestructure.repository.ShoppingCartCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartBeanConfiguration {

    @Bean
    public IShoppingCartService shoppingCartService(ShoppingCartUseCase shoppingCartUseCase){
      return new ShoppingCartService(shoppingCartUseCase);
    }

    @Bean
    public ShoppingCartUseCase shoppingCartUseCase(
            final ShoppingCartRepositoryPort shoppingCartRepositoryPort,
            final CartItemRepositoryPort cartItemRepositoryPort){
        return new ShoppingCartUseCaseImpl(shoppingCartRepositoryPort, cartItemRepositoryPort );
    }

    @Bean
    public ShoppingCartRepositoryPort shoppingCartRepositoryPort(
            final ShoppingCartCrudRepositoryMySQL shoppingCartCrudRepositoryMySQL){
        return new ShoppingCartRepositoryMySQLAdapter(shoppingCartCrudRepositoryMySQL);
    }


}
