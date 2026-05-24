package com.online.retail.products_catalogue.repository;

import com.online.retail.products_catalogue.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}