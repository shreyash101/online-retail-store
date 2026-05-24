package com.online.retail.shopping_service.client;

import com.online.retail.shopping_service.dto.customer.CustomerDetailsDto;
import com.online.retail.shopping_service.dto.customer.CustomerRequestDto;
import com.online.retail.shopping_service.dto.customer.CustomerResponseDto;
import com.online.retail.shopping_service.dto.shopping.customer.CreateCustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "customer", path = "/api/v1/customers")
public interface CustomerClient {
    @PostMapping
    CustomerResponseDto createCustomer(CustomerRequestDto dto);
    @GetMapping(path = "/{id}")
    CustomerDetailsDto getCustomerDetails(@PathVariable("id") Long customerId);
}
