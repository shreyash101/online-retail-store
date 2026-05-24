package com.online.retail.inventory.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryRequestDto {
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;
}