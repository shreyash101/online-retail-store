package com.online.retail.customer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity()
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(nullable = false, length = 100)
    private String customerName;
    @Column(nullable = false, length = 100, unique = true)
    private String customerEmail;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id")
    private CustomerAddress customerBillingAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id")
    private CustomerAddress customerShippingAddress;
}