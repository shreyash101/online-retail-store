package com.online.retail.shopping_service.dto.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {
    private Long cartId;
    private List<LineItemResponseDto> lineItems;
}