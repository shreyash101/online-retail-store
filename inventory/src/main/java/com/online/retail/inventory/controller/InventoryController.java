package com.online.retail.inventory.controller;

import com.online.retail.inventory.dto.InventoryRequestDto;
import com.online.retail.inventory.dto.InventoryResponseDto;
import com.online.retail.inventory.dto.UpdateInventoryDto;
import com.online.retail.inventory.service.InventoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController(value = "inventoryController")
@RequestMapping(path = "/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping()
    public ResponseEntity<InventoryResponseDto> addInventory(@Valid @RequestBody InventoryRequestDto dto) {
        InventoryResponseDto dto1 = inventoryService.addInventory(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto1.getInventoryId())
                .toUri();

        return ResponseEntity.created(uri).body(dto1);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponseDto> updateInventory(@PathVariable("id") Long productId, @Valid @RequestBody UpdateInventoryDto dto) {
        InventoryResponseDto updateDto = inventoryService.update(productId, dto);
        return ResponseEntity.ok().body(updateDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponseDto> getInventory(@PathVariable("id") Long id) {
        InventoryResponseDto inventory = inventoryService.getInventory(id);
        return ResponseEntity.ok().body(inventory);
    }

    @GetMapping
    public ResponseEntity<Page<InventoryResponseDto>> getAllInventory(
            @PageableDefault(page = 0, size = 10)Pageable pageable
            ) {
        Page<InventoryResponseDto> inventory = inventoryService.getAllInventory(pageable);
        return ResponseEntity.ok().body(inventory);
    }
}
