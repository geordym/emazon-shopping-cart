package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.RefundRequest;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.User;

public interface NotificationService {
    void notifyUserOfRefund(User user, RefundRequest refundRequest);
}
