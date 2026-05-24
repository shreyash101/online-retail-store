package com.online.retail.shopping_service.repository;

import com.online.retail.shopping_service.entity.CustomerOrder;
import com.online.retail.shopping_service.entity.CustomerOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, CustomerOrderId> {
    List<CustomerOrder> findByIdCustomerId(Long customerId);
}