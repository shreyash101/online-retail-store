package com.online.retail.shopping_service.client;

import com.online.retail.shopping_service.dto.cart.CartRequestDto;
import com.online.retail.shopping_service.dto.cart.CartResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart", path = "/api/v1/carts")
public interface CartClient {
    @PostMapping
    CartResponseDto createCart(CartRequestDto dto);

    @PutMapping("/{id}")
    CartResponseDto addToCart(@PathVariable("id") Long cartId, @Valid @RequestBody CartRequestDto dto);

    @GetMapping("/{id}")
    CartResponseDto getCart(@PathVariable("id") Long cartId);
}