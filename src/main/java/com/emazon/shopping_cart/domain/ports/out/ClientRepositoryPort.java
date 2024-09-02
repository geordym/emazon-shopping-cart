package com.emazon.shopping_cart.domain.ports.out;

import java.util.Optional;

public interface ClientRepositoryPort {

    boolean existsById(Long clientId);

}
