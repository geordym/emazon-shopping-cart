package com.emazon.shopping_cart.infraestructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserInfoDto {

    public Long userId;
    public String username;
    public List<String> roles;
}

