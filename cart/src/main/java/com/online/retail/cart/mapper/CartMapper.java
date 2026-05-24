package com.online.retail.cart.mapper;

import com.online.retail.cart.dto.CartRequestDto;
import com.online.retail.cart.dto.CartResponseDto;
import com.online.retail.cart.dto.LineItemResponseDto;
import com.online.retail.cart.entity.Cart;
import com.online.retail.cart.entity.LineItem;

import java.util.List;

public class CartMapper {
    public static Cart toEntity(CartRequestDto dto) {
        Cart cart = new Cart();
        List<LineItem> items = dto.getItems().stream()
                        .map(ItemMapper::toEntity)
                .toList();
        items.forEach(item -> item.setCart(cart));
        cart.setItems(items);
        return cart;
    }

    public static CartResponseDto toDto(Cart cart) {
        CartResponseDto dto = new CartResponseDto();
        dto.setCartId(cart.getCartId());
        List<LineItemResponseDto> itemsDto = cart.getItems().stream()
                .map(ItemMapper::toDto)
                .toList();
        dto.setLineItems(itemsDto);
        return dto;
    }
}