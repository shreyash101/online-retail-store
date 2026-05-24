package com.online.retail.shopping_service.dto.customer;

import lombok.Data;

@Data
public class CustomerDetailsDto extends CustomerResponseDto {
    private AddressDto customerBillingAddress;
    private AddressDto customerShippingAddress;
}