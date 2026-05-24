package com.online.retail.shopping_service.client;

import com.online.retail.shopping_service.dto.inventory.InventoryRequestDto;
import com.online.retail.shopping_service.dto.inventory.InventoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory", path = "/api/v1/inventory")
public interface InventoryClient {
    @PostMapping()
    InventoryResponseDto createInventory(InventoryRequestDto dto);

    @PutMapping("/{id}")
    void updateInventory(@PathVariable("id") Long productId, InventoryRequestDto dto);
}