package com.online.retail.shopping_service.dto.shopping.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequestDto {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer quantity;
}