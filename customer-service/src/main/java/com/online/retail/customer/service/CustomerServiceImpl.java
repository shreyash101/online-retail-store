package com.online.retail.customer.service;

import com.online.retail.customer.dto.*;
import com.online.retail.customer.entity.Customer;
import com.online.retail.customer.entity.CustomerAddress;
import com.online.retail.customer.exception.ResourceNotFoundException;
import com.online.retail.customer.mapper.AddressMapper;
import com.online.retail.customer.mapper.CustomerMapper;
import com.online.retail.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired()
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toResponseDto(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }

    @Override
    @Transactional
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setCustomerName(dto.getCustomerName());
        customer.setCustomerEmail(dto.getCustomerEmail());
        CustomerAddress billingAddress = AddressMapper.toEntity(dto.getCustomerBillingAddress());
        CustomerAddress shippingAddress = AddressMapper.toEntity(dto.getCustomerShippingAddress());
        customer.setCustomerBillingAddress(billingAddress);
        customer.setCustomerShippingAddress(shippingAddress);
        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.toResponseDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDetailsDto getCustomerDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return CustomerMapper.toResponseDetailsDto(customer);
    }

    @Override
    @Transactional
    public CustomerResponsePatchDto updateCustomerPartial(Long id, CustomerRequestPatch dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        if(dto.getCustomerEmail() != null) customer.setCustomerEmail(dto.getCustomerEmail());
        if(dto.getCustomerName() != null) customer.setCustomerName(dto.getCustomerName());
        return CustomerMapper.toResponseDtoPatch(customer);
    }

    @Override
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(CustomerMapper::toResponseDto);
    }
}
