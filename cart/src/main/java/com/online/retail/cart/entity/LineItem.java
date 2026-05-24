package com.online.retail.cart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "line_items")
@Data
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false, length = 50)
    private String productName;
    @Column(nullable = false)
    @Min(0)
    private Integer quantity;
    @Column(nullable = false, precision = 12, scale = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}