package com.emazon.shopping_cart.infraestructure.adapter;

import com.emazon.shopping_cart.domain.ports.out.ClientRepositoryPort;
import com.emazon.shopping_cart.infraestructure.client.CustomerFeignClient;
import com.emazon.shopping_cart.infraestructure.client.dto.CustomerResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ClientRepositoryFeignAdapter implements ClientRepositoryPort {
    private final CustomerFeignClient customerFeignClient;

    public boolean existsById(Long clientId) {
        try {
            Optional<CustomerResponseDto> customerResponseDto = customerFeignClient.getCustomerById(clientId);
            return customerResponseDto.isPresent();
        } catch (FeignException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
