package com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.impl;

import com.emazon.shopping_cart.infraestructure.rest.dto.clases.RefundRequest;
import com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.RefundRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class RefundServiceImpl implements RefundRepository {

    private final Map<String, RefundRequest> refundRequests = new HashMap<>();

    @Override
    public void save(RefundRequest refundRequest) {
        refundRequests.put(refundRequest.getUserId(), refundRequest);
    }

    public Optional<RefundRequest> findById(String refundRequestId) {
        return Optional.ofNullable(refundRequests.get(refundRequestId));
    }

    public Optional<RefundRequest> findByUserIdAndStatus(String userId, String status) {
        return refundRequests.values().stream()
                .filter(r -> r.getUserId().equals(userId) && r.getStatus().equals(status))
                .findFirst();
    }
}