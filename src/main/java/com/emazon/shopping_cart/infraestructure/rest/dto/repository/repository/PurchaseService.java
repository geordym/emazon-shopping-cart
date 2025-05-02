package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.Purchase;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.User;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    Optional<User> findById(String id);

    List<Purchase> getPurchasesEligibleForRefund(String id);
}
