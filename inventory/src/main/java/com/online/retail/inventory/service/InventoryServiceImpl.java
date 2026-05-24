package com.online.retail.inventory.service;

import com.online.retail.inventory.dto.InventoryRequestDto;
import com.online.retail.inventory.dto.InventoryResponseDto;
import com.online.retail.inventory.dto.UpdateInventoryDto;
import com.online.retail.inventory.entity.Product;
import com.online.retail.inventory.exception.ResourceNotFoundException;
import com.online.retail.inventory.mapper.InventoryMapper;
import com.online.retail.inventory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "inventoryService")
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryResponseDto addInventory(InventoryRequestDto dto) {
        Product savedProduct = inventoryRepository.save(InventoryMapper.toEntity(dto));
        return InventoryMapper.toDto(savedProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = inventoryRepository.findByProductId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
        inventoryRepository.delete(product);
    }

    @Override
    @Transactional
    public InventoryResponseDto update(Long productId, UpdateInventoryDto dto) {
        Product product = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
        product.setQuantity(product.getQuantity() - dto.getQuantity());
        product.setProductId(productId);
        inventoryRepository.save(product);
        return InventoryMapper.toDto(product);
    }

    @Override
    public InventoryResponseDto getInventory(Long id) {
        Product product = inventoryRepository.findByProductId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
        return InventoryMapper.toDto(product);
    }

    @Override
    public Page<InventoryResponseDto> getAllInventory(Pageable pageable) {
        return inventoryRepository.findAll(pageable)
                .map(InventoryMapper::toDto);
    }
}
