package com.online.retail.cart.mapper;

import com.online.retail.cart.dto.LineItemRequestDto;
import com.online.retail.cart.dto.LineItemResponseDto;
import com.online.retail.cart.entity.LineItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ItemMapper {

    public static LineItemResponseDto toDto(LineItem item) {
        LineItemResponseDto dto = new LineItemResponseDto();
        dto.setItemId(item.getItemId());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setProductId(item.getProductId());
        dto.setProductName(item.getProductName());
        return dto;
    }

    public static LineItem toEntity(LineItemRequestDto dto) {
        LineItem item = new LineItem();
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        item.setProductName(dto.getProductName());
        item.setProductId(dto.getProductId());
        return item;
    }
}