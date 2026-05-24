package com.online.retail.customer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerResponseDetailsDto extends CustomerResponseDto {
    private AddressDto customerBillingAddress;
    private AddressDto customerShippingAddress;
}