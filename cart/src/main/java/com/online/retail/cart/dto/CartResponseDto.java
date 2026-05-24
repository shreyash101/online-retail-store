package com.online.retail.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {
    private Long cartId;
    private List<LineItemResponseDto> lineItems;
}