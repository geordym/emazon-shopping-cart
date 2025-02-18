package com.emazon.shopping_cart.infraestructure.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {
    private Long id;
    private String name;
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
