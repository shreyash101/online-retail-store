package com.online.retail.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {
    private Long orderId;
    private List<LineItemResponseDto> lineItems;
}