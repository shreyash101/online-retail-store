package com.online.retail.cart.service;

import com.online.retail.cart.dto.CartRequestDto;
import com.online.retail.cart.dto.CartResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {
    CartResponseDto addCart(CartRequestDto dto);

    void delete(Long id);

    CartResponseDto updateCart(Long id, CartRequestDto dto);

    CartResponseDto getCart(Long id);

    Page<CartResponseDto> getAllCarts(Pageable pageable);
}
