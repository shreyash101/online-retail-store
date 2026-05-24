package com.online.retail.shopping_service.dto.order;

import com.online.retail.shopping_service.dto.cart.LineItemResponseDto;
import com.online.retail.shopping_service.dto.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long orderId;
    private List<LineItemResponseDto> lineItems;
    private BigDecimal totalAmount;
    private OrderStatus status;
}