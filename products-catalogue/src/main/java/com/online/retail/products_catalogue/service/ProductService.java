package com.online.retail.products_catalogue.service;

import com.online.retail.products_catalogue.dto.ProductDto;
import com.online.retail.products_catalogue.dto.ProductRequestDto;
import com.online.retail.products_catalogue.dto.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto dto);

    void deleteProduct(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

    ProductResponseDto getProductDetails(Long id);

    Page<ProductDto> getAllProducts(Pageable pageable);
}
