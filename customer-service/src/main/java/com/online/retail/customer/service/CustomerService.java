package com.online.retail.customer.service;

import com.online.retail.customer.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto dto);

    void deleteCustomer(Long customerId);

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto);

    CustomerResponseDetailsDto getCustomerDetails(Long id);

    CustomerResponsePatchDto updateCustomerPartial(Long id, CustomerRequestPatch dto);

    Page<CustomerResponseDto> getAllCustomers(Pageable pageable);
}