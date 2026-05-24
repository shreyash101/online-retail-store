package com.online.retail.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateInventoryDto {
    @NotNull
    private Integer quantity;
}