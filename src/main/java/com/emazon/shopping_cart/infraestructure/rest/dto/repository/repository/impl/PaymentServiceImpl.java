package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.impl;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.PaymentMethod;
import com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.PaymentService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Optional<PaymentMethod> getPreferredPaymentMethod(String userId) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setType("CREDIT_CARD");

        return Optional.of(paymentMethod);
    }
}