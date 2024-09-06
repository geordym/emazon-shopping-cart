package com.emazon.shopping_cart.infraestructure.rest;


import com.emazon.shopping_cart.application.services.interfaces.IShoppingCartService;
import com.emazon.shopping_cart.infraestructure.adapter.ClientRepositoryFeignAdapter;
import com.emazon.shopping_cart.infraestructure.configuration.security.CustomUserDetails;
import com.emazon.shopping_cart.infraestructure.mapper.CartItemMapper;
import com.emazon.shopping_cart.infraestructure.rest.dto.request.CardItemAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            clientId = Long.valueOf(userDetails.getUserId());
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
