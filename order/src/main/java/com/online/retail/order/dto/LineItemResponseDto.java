package com.online.retail.order.dto;

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