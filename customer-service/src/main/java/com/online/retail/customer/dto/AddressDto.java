package com.online.retail.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank
    @Size(min = 1, max = 10, message = "Door no must be between 1 and 10 characters")
    private String doorNo;
    @NotBlank
    @Size(min = 1, max = 100, message = "Street name must be between 1 and 100 characters")
    private String streetName;
    @NotBlank
    @Size(min = 1, max = 100, message = "Layout must be between 1 and 100 characters")
    private String layout;
    @NotBlank
    @Size(min = 1, max = 50, message = "City must be between 1 and 50 characters")
    private String city;
    @NotBlank
    @Size(min = 1, max = 20, message = "Pincode must be between 1 and 20 characters")
    private String pincode;
}