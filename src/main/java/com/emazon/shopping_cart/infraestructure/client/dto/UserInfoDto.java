package com.emazon.shopping_cart.infraestructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserInfoDto {

    private Long idUser;
    private String username;
    private List<String> roles;
}
