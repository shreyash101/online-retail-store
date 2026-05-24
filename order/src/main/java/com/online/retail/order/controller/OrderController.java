package com.online.retail.order.controller;

import com.online.retail.order.dto.BulkOrderResponseDto;
import com.online.retail.order.dto.OrderRequestDto;
import com.online.retail.order.dto.OrderResponseDto;
import com.online.retail.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("orderController")
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(@Valid @RequestBody OrderRequestDto dto) {
        OrderResponseDto dto1 = orderService.addOrder(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto1.getOrderId())
                .toUri();
        return ResponseEntity.created(uri).body(dto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable("id") Long id,
                                                       @Valid @RequestBody OrderRequestDto dto) {
        OrderResponseDto dto1 = orderService.updateOrder(id, dto);
        return ResponseEntity.ok().body(dto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(orderService.getOrder(id));
    }

    @GetMapping()
    public ResponseEntity<Page<OrderResponseDto>> getAllOrders(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok().body(orderService.getAllOrders(pageable));
    }

    @PostMapping("/bulk")
    public ResponseEntity<BulkOrderResponseDto> getOrdersBulk(
            @RequestBody List<Long> orderIds
    ) {
        return ResponseEntity.ok().body(orderService.getOrdersBulk(orderIds));
    }
}