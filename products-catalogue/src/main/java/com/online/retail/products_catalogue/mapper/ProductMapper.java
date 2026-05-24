package com.online.retail.products_catalogue.mapper;

import com.online.retail.products_catalogue.dto.ProductDto;
import com.online.retail.products_catalogue.dto.ProductRequestDto;
import com.online.retail.products_catalogue.dto.ProductResponseDto;
import com.online.retail.products_catalogue.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setProductDescription(dto.getProductDescription());
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        return product;
    }

    public static ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductPrice(product.getProductPrice());
        return dto;
    }

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());
        return dto;
    }
}