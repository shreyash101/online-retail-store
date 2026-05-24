package com.online.retail.shopping_service.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
//    private Integer quantity;
}