package com.online.retail.cart.controller;

import com.online.retail.cart.dto.CartRequestDto;
import com.online.retail.cart.dto.CartResponseDto;
import com.online.retail.cart.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("cartController")
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> addCart(@Valid @RequestBody CartRequestDto dto) {
        CartResponseDto dto1 = cartService.addCart(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto1.getCartId())
                .toUri();
        return ResponseEntity.created(uri).body(dto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDto> updateCart(@PathVariable("id") Long id,
                                                      @Valid @RequestBody CartRequestDto dto) {
        CartResponseDto dto1 = cartService.updateCart(id, dto);
        return ResponseEntity.ok().body(dto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(cartService.getCart(id));
    }

    @GetMapping()
    public ResponseEntity<Page<CartResponseDto>> getAllCarts(
            @PageableDefault(page = 0, size = 10)Pageable pageable
            ) {
        return ResponseEntity.ok().body(cartService.getAllCarts(pageable));
    }
}