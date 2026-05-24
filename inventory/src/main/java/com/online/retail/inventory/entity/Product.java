package com.online.retail.inventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;

@Entity
@Table(name = "inventory_products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Integer quantity;
}