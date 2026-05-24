package com.online.retail.shopping_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer_cart")
@Data
public class CustomerCart {
    @EmbeddedId
    private CustomerCartId id;
}