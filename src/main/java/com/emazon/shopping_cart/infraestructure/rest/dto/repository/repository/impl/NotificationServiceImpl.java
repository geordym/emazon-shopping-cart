package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.impl;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.RefundRequest;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.User;
import com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.NotificationService;
import com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.external.EmailMessageServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NotificationServiceImpl implements NotificationService {

    private final EmailMessageServiceImpl emailMessageService;

    public NotificationServiceImpl(EmailMessageServiceImpl emailMessageService) {
        this.emailMessageService = emailMessageService;
    }

    @Override
    public void notifyUserOfRefund(User user, RefundRequest refundRequest) {



        String message = String.format(
                "Hola %s, tu solicitud de reembolso de $%.2f está en estado: %s. Se creó el %s.",
                user.getName(),
                refundRequest.getAmount(),
                refundRequest.getStatus(),
                refundRequest.getCreatedAt().toString()
        );

        emailMessageService.enviarMensaje(message);
        System.out.println("✅ Notificación enviada: " + message);
    }
}