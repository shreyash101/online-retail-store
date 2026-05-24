package com.online.retail.cart.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineItemResponseDto {
    private Long itemId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}