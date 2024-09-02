package com.emazon.shopping_cart.infraestructure.client;

import com.emazon.shopping_cart.infraestructure.client.dto.ArticleResponseDto;
import com.emazon.shopping_cart.infraestructure.configuration.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "STOCK-API", url = "${external.stock.api.base-url}", configuration = FeignClientConfig.class)
public interface StockFeignClient {

    @GetMapping(value = "/api/articulos/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<ArticleResponseDto> getArticleById(@PathVariable("articleId") Long articleId);


}
