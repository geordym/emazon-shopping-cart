package com.emazon.shopping_cart.domain.ports.out;

public interface StockRepositoryPort {

    boolean existsArticleById(Long articleId);

}
