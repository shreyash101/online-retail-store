package com.online.retail.products_catalogue.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false, length = 50)
    private String productName;
    @Column(nullable = false, length = 500)
    private String productDescription;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal productPrice;
}