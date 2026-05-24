package com.online.retail.shopping_service.dto.order;

import com.online.retail.shopping_service.dto.cart.LineItemRequestDto;
import com.online.retail.shopping_service.dto.cart.LineItemResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private final List<LineItemResponseDto> items;
}