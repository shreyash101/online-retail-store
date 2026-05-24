package com.online.retail.products_catalogue.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
}