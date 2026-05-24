package com.online.retail.customer.mapper;

import com.online.retail.customer.dto.*;
import com.online.retail.customer.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDto dto) {
        Customer customer = new Customer();
        customer.setCustomerEmail(dto.getCustomerEmail());
        customer.setCustomerName(dto.getCustomerName());
        customer.setCustomerBillingAddress(AddressMapper.toEntity(dto.getCustomerBillingAddress()));
        customer.setCustomerShippingAddress(AddressMapper.toEntity(dto.getCustomerShippingAddress()));
        return customer;
    }

    public static CustomerResponseDto toResponseDto(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setCustomerEmail(customer.getCustomerEmail());
        return dto;
    }

    public static CustomerResponseDetailsDto toResponseDetailsDto(Customer customer) {
        CustomerResponseDetailsDto dto = new CustomerResponseDetailsDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setCustomerEmail(customer.getCustomerEmail());
        dto.setCustomerBillingAddress(AddressMapper.toDto(customer.getCustomerBillingAddress()));
        dto.setCustomerShippingAddress(AddressMapper.toDto(customer.getCustomerShippingAddress()));
        return dto;
    }

    public static CustomerResponsePatchDto toResponseDtoPatch(Customer customer) {
        CustomerResponsePatchDto dto = new CustomerResponsePatchDto();
        dto.setCustomerEmail(customer.getCustomerEmail());
        dto.setCustomerName(customer.getCustomerName());
        dto.setCustomerId(customer.getCustomerId());
        return dto;
    }
}