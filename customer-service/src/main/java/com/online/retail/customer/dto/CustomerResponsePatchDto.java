package com.online.retail.customer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerResponsePatchDto extends CustomerResponseDto{
    private String customerEmail;
}