package com.online.retail.customer.repository;

import com.online.retail.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
