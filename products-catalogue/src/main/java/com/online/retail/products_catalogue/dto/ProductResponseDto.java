package com.online.retail.products_catalogue.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductResponseDto extends ProductDto {
    private String productDescription;
}