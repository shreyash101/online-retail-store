package com.online.retail.order.mapper;

import com.online.retail.order.dto.LineItemRequestDto;
import com.online.retail.order.dto.LineItemResponseDto;
import com.online.retail.order.entity.LineItem;

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