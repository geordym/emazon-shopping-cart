package com.emazon.shopping_cart.infraestructure.rest.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardItemAddRequestDto {

    private Long article_id;
    private Integer quantity;
    private Double price;

}
