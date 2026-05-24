package com.online.retail.shopping_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CustomerOrderId {
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private Long orderId;
}