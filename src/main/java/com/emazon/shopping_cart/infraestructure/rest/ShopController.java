package com.emazon.shopping_cart.infraestructure.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShopController {


    @PostMapping
    public ResponseEntity<String> buy(){
        return new ResponseEntity<>("Comprando...", HttpStatus.OK);
    }

}

