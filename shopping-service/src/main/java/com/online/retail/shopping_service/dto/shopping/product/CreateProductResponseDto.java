package com.online.retail.shopping_service.dto.shopping.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductResponseDto {
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer quantity;
}