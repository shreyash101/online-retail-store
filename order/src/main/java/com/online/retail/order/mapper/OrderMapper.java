package com.online.retail.order.mapper;

import com.online.retail.order.dto.OrderRequestDto;
import com.online.retail.order.dto.OrderResponseDto;
import com.online.retail.order.dto.LineItemResponseDto;
import com.online.retail.order.entity.Order;
import com.online.retail.order.entity.LineItem;

import java.util.List;

public class OrderMapper {
    public static Order toEntity(OrderRequestDto dto) {
        Order order = new Order();
        List<LineItem> items = dto.getItems().stream()
                        .map(ItemMapper::toEntity)
                .toList();
        items.forEach(item -> item.setOrder(order));
        order.setItems(items);
        return order;
    }

    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(order.getOrderId());
        List<LineItemResponseDto> itemsDto = order.getItems().stream()
                .map(ItemMapper::toDto)
                .toList();
        dto.setLineItems(itemsDto);
        return dto;
    }
}