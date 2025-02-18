package com.emazon.shopping_cart.infraestructure.enums;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoleEnum {
    AUX_BODEGA(1L, "AUX_BODEGA"),
    CLIENTE(2L, "CLIENT"),
    ADMINISTRADOR(3L, "ADMINISTRATOR");


    private final Long id;
    private final String name;

    RoleEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RoleEnum fromName(String name) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getName().equalsIgnoreCase(name)) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el nombre: " + name);
    }

    public static RoleEnum fromId(Long id) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getId() == id) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el ID: " + id);
    }

    public static List<RoleEnum> getAllRoles() {
        return Arrays.asList(RoleEnum.values());
    }

    public static List<Long> getAllIds() {
        return Arrays.stream(values())
                .map(RoleEnum::getId)
                .collect(Collectors.toList());
    }



    public Role toModel() {
        Role role = new Role(this.id, this.name);
        return role;
    }

}