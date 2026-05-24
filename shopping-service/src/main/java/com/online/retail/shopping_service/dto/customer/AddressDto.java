package com.online.retail.shopping_service.dto.customer;

import lombok.Data;

@Data
public class AddressDto {
    private String doorNo;
    private String streetName;
    private String layout;
    private String city;
    private String pincode;
}