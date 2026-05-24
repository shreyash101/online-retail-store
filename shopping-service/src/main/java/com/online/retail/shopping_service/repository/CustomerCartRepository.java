package com.online.retail.shopping_service.repository;

import com.online.retail.shopping_service.entity.CustomerCart;
import com.online.retail.shopping_service.entity.CustomerCartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerCartRepository extends JpaRepository<CustomerCart, CustomerCartId> {
    Optional<CustomerCart> findByIdCustomerId(Long customerId);
}