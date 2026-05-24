package com.online.retail.customer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer_addresses")
@Data
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(nullable = false, length = 10)
    private String doorNo;
    @Column(nullable = false, length = 100)
    private String streetName;
    @Column(nullable = false, length = 100)
    private String layout;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false, length = 20)
    private String pincode;
}