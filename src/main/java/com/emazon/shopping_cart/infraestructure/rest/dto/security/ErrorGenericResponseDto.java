package com.emazon.shopping_cart.infraestructure.rest.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorGenericResponseDto {
    private String error;
    private String message;
    private String timestamp;

}