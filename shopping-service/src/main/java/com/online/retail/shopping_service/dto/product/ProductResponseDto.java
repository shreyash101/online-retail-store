package com.online.retail.shopping_service.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}