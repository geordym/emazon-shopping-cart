package com.emazon.shopping_cart.infraestructure.configuration.security;

import com.emazon.shopping_cart.domain.ports.out.security.TokenProviderPort;
import com.emazon.shopping_cart.infraestructure.adapter.security.JwtIOTokenAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanSecurityConfiguration {

    @Bean
    public TokenProviderPort tokenProviderPort(){
        return new JwtIOTokenAdapter();
    }



}
