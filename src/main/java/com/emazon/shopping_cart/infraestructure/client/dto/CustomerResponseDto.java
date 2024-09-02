package com.emazon.shopping_cart.infraestructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerResponseDto {
    public Long customerId;
    public String username;
}

