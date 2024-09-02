package com.emazon.shopping_cart.infraestructure.rest;


import com.emazon.shopping_cart.application.services.interfaces.IShoppingCartService;
import com.emazon.shopping_cart.domain.ports.out.ClientRepositoryPort;
import com.emazon.shopping_cart.infraestructure.adapter.ClientRepositoryFeignAdapter;
import com.emazon.shopping_cart.infraestructure.client.dto.UserInfoDto;
import com.emazon.shopping_cart.infraestructure.configuration.security.JwtCustomUserDetails;
import com.emazon.shopping_cart.infraestructure.mapper.CartItemMapper;
import com.emazon.shopping_cart.infraestructure.rest.dto.request.CardItemAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;
    private final ClientRepositoryFeignAdapter clientRepositoryFeignAdapter;
    @PostMapping("/items")
    public ResponseEntity<String> addItemToShoppingCart(@RequestBody CardItemAddRequestDto cardItemAddRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long clientId = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            JwtCustomUserDetails userDetails = (JwtCustomUserDetails) authentication.getPrincipal();
            clientId = userDetails.getId();
        }
        shoppingCartService.addItemToCart(clientId, CartItemMapper.dtoToDomain(cardItemAddRequestDto));
        return new ResponseEntity<>("Item added sucesfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> removeItemFromShoppingCart(@PathVariable Long itemId){
        shoppingCartService.removeItemFromCart(itemId);
        return new ResponseEntity<>("Item deleted sucesfully", HttpStatus.OK);
    }




}
