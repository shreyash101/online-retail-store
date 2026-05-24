package com.online.retail.customer.controller;

import com.online.retail.customer.dto.*;
import com.online.retail.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController(value = "customerController")
@RequestMapping(path = "/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto dto) {
        CustomerResponseDto customer = customerService.createCustomer(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getCustomerId())
                .toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerRequestDto dto) {
        CustomerResponseDto updatedResponse = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok().body(updatedResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerResponseDetailsDto> getCustomerDetails(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(customerService.getCustomerDetails(id));
    }

    @GetMapping()
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(customerService.getAllCustomers(pageable));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<CustomerResponsePatchDto> updateCustomerPartial(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerRequestPatch dto) {
        return ResponseEntity.ok().body(customerService.updateCustomerPartial(id, dto));
    }
}