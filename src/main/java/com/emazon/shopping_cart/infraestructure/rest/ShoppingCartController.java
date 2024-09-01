package com.emazon.shopping_cart.infraestructure.rest;


import com.emazon.shopping_cart.application.services.interfaces.IShoppingCartService;
import com.emazon.shopping_cart.infraestructure.mapper.CartItemMapper;
import com.emazon.shopping_cart.infraestructure.rest.dto.request.CardItemAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;
    @PostMapping("/items")
    public ResponseEntity<String> addItemToShoppingCart(@RequestBody CardItemAddRequestDto cardItemAddRequestDto){
        shoppingCartService.addItemToCart(1L, CartItemMapper.dtoToDomain(cardItemAddRequestDto));
        return new ResponseEntity<>("Item added sucesfully", HttpStatus.CREATED);
    }




}
