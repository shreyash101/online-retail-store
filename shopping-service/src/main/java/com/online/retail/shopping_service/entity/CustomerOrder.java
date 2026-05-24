package com.online.retail.shopping_service.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer_order")
@Data
public class CustomerOrder {
    @EmbeddedId
    private CustomerOrderId id;
}