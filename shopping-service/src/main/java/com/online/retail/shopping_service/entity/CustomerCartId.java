package com.online.retail.shopping_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CustomerCartId implements Serializable {
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private Long cartId;
}