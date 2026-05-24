package com.online.retail.inventory.mapper;

import com.online.retail.inventory.dto.InventoryRequestDto;
import com.online.retail.inventory.dto.InventoryResponseDto;
import com.online.retail.inventory.entity.Product;

public class InventoryMapper {
    public static Product toEntity(InventoryRequestDto dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setQuantity(dto.getQuantity());
        return product;
    }

    public static InventoryResponseDto toDto(Product product) {
        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setInventoryId(product.getInventoryId());
        dto.setQuantity(product.getQuantity());
        dto.setProductId(product.getProductId());
        return dto;
    }
}