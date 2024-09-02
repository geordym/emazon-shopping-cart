package com.emazon.shopping_cart.infraestructure.configuration;


import com.emazon.shopping_cart.application.services.implementations.ShoppingCartService;
import com.emazon.shopping_cart.application.services.interfaces.IShoppingCartService;
import com.emazon.shopping_cart.domain.ports.in.ShoppingCartUseCase;
import com.emazon.shopping_cart.domain.ports.out.CartItemRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ClientRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.ShoppingCartRepositoryPort;
import com.emazon.shopping_cart.domain.ports.out.StockRepositoryPort;
import com.emazon.shopping_cart.domain.usecases.ShoppingCartUseCaseImpl;
import com.emazon.shopping_cart.domain.usecases.validators.ShoppingCartUseCaseValidator;
import com.emazon.shopping_cart.infraestructure.adapter.ClientRepositoryFeignAdapter;
import com.emazon.shopping_cart.infraestructure.adapter.ShoppingCartRepositoryMySQLAdapter;
import com.emazon.shopping_cart.infraestructure.adapter.StockRepositoryFeignAdapter;
import com.emazon.shopping_cart.infraestructure.client.CustomerFeignClient;
import com.emazon.shopping_cart.infraestructure.client.StockFeignClient;
import com.emazon.shopping_cart.infraestructure.repository.ShoppingCartCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartBeanConfiguration {

    @Bean
    public ClientRepositoryPort clientRepositoryPort(final CustomerFeignClient customerFeignClient){
        return new ClientRepositoryFeignAdapter(customerFeignClient);
    }

    @Bean
    public StockRepositoryPort stockRepositoryPort(final StockFeignClient stockFeignClient){
        return new StockRepositoryFeignAdapter(stockFeignClient);
    }

    @Bean
    public IShoppingCartService shoppingCartService(ShoppingCartUseCase shoppingCartUseCase){
      return new ShoppingCartService(shoppingCartUseCase);
    }

    @Bean
    public ShoppingCartUseCaseValidator shoppingCartUseCaseValidator(final StockRepositoryPort stockRepositoryPort, final ClientRepositoryPort clientRepositoryPort){
        return new ShoppingCartUseCaseValidator(clientRepositoryPort, stockRepositoryPort);
    }

    @Bean
    public ShoppingCartUseCase shoppingCartUseCase(
            final ShoppingCartRepositoryPort shoppingCartRepositoryPort,
            final CartItemRepositoryPort cartItemRepositoryPort,
            final ShoppingCartUseCaseValidator shoppingCartUseCaseValidator

    ){
        return new ShoppingCartUseCaseImpl(shoppingCartRepositoryPort, cartItemRepositoryPort, shoppingCartUseCaseValidator );
    }

    @Bean
    public ShoppingCartRepositoryPort shoppingCartRepositoryPort(
            final ShoppingCartCrudRepositoryMySQL shoppingCartCrudRepositoryMySQL){
        return new ShoppingCartRepositoryMySQLAdapter(shoppingCartCrudRepositoryMySQL);
    }


}
