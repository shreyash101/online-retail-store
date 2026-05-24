package com.online.retail.shopping_service.client;

import com.online.retail.shopping_service.dto.order.BulkOrderResponseDto;
import com.online.retail.shopping_service.dto.order.OrderRequestDto;
import com.online.retail.shopping_service.dto.order.OrderResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order", path = "/api/v1/orders")
public interface OrderClient {
    @PostMapping
    OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto dto);
    @PostMapping("/bulk")
    BulkOrderResponseDto getOrderDetails(@Valid @RequestBody List<Long> orderIds);
}