package com.online.retail.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestPatch {
    @Size(min = 1, max = 100, message = "Customer name must be between 1 and 100 characters")
    private String customerName;
    @Email
    private String customerEmail;
}