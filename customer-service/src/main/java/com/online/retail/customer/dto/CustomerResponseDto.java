package com.online.retail.customer.dto;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private Long customerId;
    private String customerName;
    private String customerEmail;
}
