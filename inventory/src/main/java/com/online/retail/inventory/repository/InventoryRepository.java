package com.online.retail.inventory.repository;

import com.online.retail.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository(value = "inventoryRepository")
public interface InventoryRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(Long id);
}
