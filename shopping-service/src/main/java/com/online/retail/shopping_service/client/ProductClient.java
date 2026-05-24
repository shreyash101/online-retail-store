package com.online.retail.shopping_service.client;

import com.online.retail.shopping_service.dto.product.ProductRequestDto;
import com.online.retail.shopping_service.dto.product.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "products-catalogue", path = "/api/v1/products")
public interface ProductClient {
    @PostMapping()
    ProductResponseDto createProduct(ProductRequestDto dto);
}