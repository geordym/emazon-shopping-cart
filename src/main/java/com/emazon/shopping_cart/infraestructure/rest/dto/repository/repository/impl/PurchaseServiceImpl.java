package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.impl;


import com.emazon.shopping_cart.infraestructure.rest.dto.clases.Purchase;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.User;
import com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.PurchaseService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PurchaseServiceImpl implements PurchaseService {

    @Override
    public Optional<User> findById(String id) {
        User user = new User();
        user.setId(id);
        user.setName("Geordy");
        return Optional.of(user);
    }

    @Override
    public List<Purchase> getPurchasesEligibleForRefund(String userId) {
        Purchase purchase1 = new Purchase();
        purchase1.setId("purchase-001");
        purchase1.setUserId(userId);
        purchase1.setAmount(50.0);

        Purchase purchase2 = new Purchase();
        purchase2.setId("purchase-002");
        purchase2.setUserId(userId);
        purchase2.setAmount(30.0);

        return List.of(purchase1, purchase2);
    }


}