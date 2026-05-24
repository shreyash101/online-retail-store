package com.online.retail.shopping_service.dto.shopping.customer;

import com.online.retail.shopping_service.dto.customer.AddressDto;
import lombok.Data;

@Data
public class CreateCustomerRequestDto {
    private String customerName;
    private String customerEmail;
    private AddressDto customerBillingAddress;
    private AddressDto customerShippingAddress;
}
