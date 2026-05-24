package com.online.retail.shopping_service.dto.inventory;

import lombok.Data;

@Data
public class InventoryRequestDto {
    private Long productId;
    private Integer quantity;
}