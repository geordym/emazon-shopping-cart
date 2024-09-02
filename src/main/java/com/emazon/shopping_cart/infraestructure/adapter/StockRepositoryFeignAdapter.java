package com.emazon.shopping_cart.infraestructure.adapter;

import com.emazon.shopping_cart.domain.ports.out.StockRepositoryPort;
import com.emazon.shopping_cart.infraestructure.client.StockFeignClient;
import com.emazon.shopping_cart.infraestructure.client.dto.ArticleResponseDto;
import com.emazon.shopping_cart.infraestructure.client.dto.CustomerResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class StockRepositoryFeignAdapter implements StockRepositoryPort {

    private final StockFeignClient stockFeignClient;

    @Override
    public boolean existsArticleById(Long articleId) {
        try {
            Optional<ArticleResponseDto> articleResponseDto =stockFeignClient.getArticleById(articleId);
            return articleResponseDto.isPresent();
        } catch (FeignException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }


}
