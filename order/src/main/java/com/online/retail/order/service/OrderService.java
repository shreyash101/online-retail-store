package com.online.retail.order.service;

import com.online.retail.order.dto.BulkOrderResponseDto;
import com.online.retail.order.dto.OrderRequestDto;
import com.online.retail.order.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    void delete(Long id);

    OrderResponseDto updateOrder(Long id, OrderRequestDto dto);

    OrderResponseDto getOrder(Long id);

    OrderResponseDto addOrder(OrderRequestDto dto);

    Page<OrderResponseDto> getAllOrders(Pageable pageable);

    BulkOrderResponseDto getOrdersBulk(List<Long> orderIds);
}
