package com.online.retail.products_catalogue.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    @NotBlank
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters")
    private String productName;
    @NotBlank
    @Size(min = 1, max = 500, message = "Product description must be between 1 and 500 characters")
    private String productDescription;
    @NotNull(message = "Product price required")
    @Digits(integer = 10, fraction = 2, message = "Product price must be rounded to 2 decimal places")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal productPrice;
}