package com.online.retail.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryResponseDto {
    private Long inventoryId;
    private Long productId;
    private Integer quantity;
}