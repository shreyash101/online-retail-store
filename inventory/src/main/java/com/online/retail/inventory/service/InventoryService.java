package com.online.retail.inventory.service;

import com.online.retail.inventory.dto.InventoryRequestDto;
import com.online.retail.inventory.dto.InventoryResponseDto;
import com.online.retail.inventory.dto.UpdateInventoryDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {
    InventoryResponseDto addInventory(InventoryRequestDto dto);

    void delete(Long id);

    InventoryResponseDto update(Long productId, UpdateInventoryDto dto);

    InventoryResponseDto getInventory(Long id);

    Page<InventoryResponseDto> getAllInventory(Pageable pageable);
}
