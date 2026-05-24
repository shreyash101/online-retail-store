package com.online.retail.cart.service;

import com.online.retail.cart.dto.CartRequestDto;
import com.online.retail.cart.dto.CartResponseDto;
import com.online.retail.cart.entity.Cart;
import com.online.retail.cart.entity.LineItem;
import com.online.retail.cart.exception.ResourceNotFoundException;
import com.online.retail.cart.mapper.CartMapper;
import com.online.retail.cart.mapper.ItemMapper;
import com.online.retail.cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("cartService")
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartResponseDto addCart(CartRequestDto dto) {
        Cart savedCart = cartRepository.save(CartMapper.toEntity(dto));
        return CartMapper.toDto(savedCart);
    }

    @Override
    public void delete(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cartRepository.delete(cart);
    }

    @Override
    public CartResponseDto updateCart(Long id, CartRequestDto dto) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        List<LineItem> list = dto.getItems().stream().map(ItemMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));
        cart.getItems().clear();
        list.forEach(item -> item.setCart(cart));
        cart.getItems().addAll(list);
        Cart updatedCart = cartRepository.save(cart);
        return CartMapper.toDto(updatedCart);
    }

    @Override
    public CartResponseDto getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return CartMapper.toDto(cart);
    }

    @Override
    public Page<CartResponseDto> getAllCarts(Pageable pageable) {
        return cartRepository.findAll(pageable)
                .map(CartMapper::toDto);
    }
}
