package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.RefundRequest;

public interface RefundRepository {
    void save(RefundRequest refundRequest);
}
