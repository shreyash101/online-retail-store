package com.online.retail.shopping_service.dto.inventory;

import lombok.Data;

@Data
public class InventoryResponseDto {
    private Long inventoryId;
    private Long productId;
    private Integer quantity;
}