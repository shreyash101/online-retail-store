package com.online.retail.cart.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineItemRequestDto {
    @NotNull(message = "Product Id required")
    private Long productId;
    @NotBlank
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters")
    private String productName;
    @NotNull
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be rounded to 2 decimal places")
    private BigDecimal price;
}