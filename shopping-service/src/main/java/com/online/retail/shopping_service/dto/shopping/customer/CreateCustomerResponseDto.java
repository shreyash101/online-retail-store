package com.online.retail.shopping_service.dto.shopping.customer;

import lombok.Data;

@Data
public class CreateCustomerResponseDto {
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private Long cartId;
}
