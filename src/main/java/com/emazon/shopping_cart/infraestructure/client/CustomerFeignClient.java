package com.emazon.shopping_cart.infraestructure.client;

import com.emazon.shopping_cart.infraestructure.client.dto.ArticleResponseDto;
import com.emazon.shopping_cart.infraestructure.client.dto.CustomerResponseDto;
import com.emazon.shopping_cart.infraestructure.client.dto.UserInfoDto;
import com.emazon.shopping_cart.infraestructure.configuration.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(name = "USER-API", url = "${external.user.api.base-url}", configuration = FeignClientConfig.class)
public interface CustomerFeignClient {



    @GetMapping(value = "/api/users/info/client/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<CustomerResponseDto> getCustomerById(@PathVariable("customerId") Long customerId);
    @GetMapping(value = "/api/users/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<UserInfoDto> authenticateUserByToken(@RequestHeader("Authorization") String token);


}
