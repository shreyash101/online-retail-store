package com.online.retail.shopping_service.dto.customer;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private Long customerId;
    private String customerName;
    private String customerEmail;
}