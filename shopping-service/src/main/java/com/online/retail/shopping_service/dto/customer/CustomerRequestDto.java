package com.online.retail.shopping_service.dto.customer;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String customerName;
    private String customerEmail;
    private AddressDto customerBillingAddress;
    private AddressDto customerShippingAddress;
}