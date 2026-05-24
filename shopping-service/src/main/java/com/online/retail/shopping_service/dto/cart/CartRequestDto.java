package com.online.retail.shopping_service.dto.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartRequestDto {
    private final List<LineItemRequestDto> items;
}