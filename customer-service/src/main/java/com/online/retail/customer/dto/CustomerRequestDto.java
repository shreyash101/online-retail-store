package com.online.retail.customer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestDto {
    @NotBlank
    @Size(min = 1, max = 100, message = "Customer name must be between 1 and 100 characters")
    private String customerName;
    @NotBlank
    @Email
    private String customerEmail;
    @NotNull
    @Valid
    private AddressDto customerBillingAddress;
    @NotNull
    @Valid
    private AddressDto customerShippingAddress;
}