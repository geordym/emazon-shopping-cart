package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.PaymentMethod;

import java.util.Optional;

public interface PaymentService {
    Optional<PaymentMethod> getPreferredPaymentMethod(String userId);
}
