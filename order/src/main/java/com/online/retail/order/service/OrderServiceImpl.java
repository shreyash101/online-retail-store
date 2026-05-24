package com.online.retail.order.service;

import com.online.retail.order.dto.BulkOrderResponseDto;
import com.online.retail.order.dto.OrderRequestDto;
import com.online.retail.order.dto.OrderResponseDto;
import com.online.retail.order.entity.Order;
import com.online.retail.order.entity.LineItem;
import com.online.retail.order.exception.ResourceNotFoundException;
import com.online.retail.order.mapper.OrderMapper;
import com.online.retail.order.mapper.ItemMapper;
import com.online.retail.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDto addOrder(OrderRequestDto dto) {
        Order savedOrder = orderRepository.save(OrderMapper.toEntity(dto));
        return OrderMapper.toDto(savedOrder);
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(OrderMapper::toDto);
    }

    @Override
    public BulkOrderResponseDto getOrdersBulk(List<Long> orderIds) {
        List<Order> orders = orderRepository.findAllById(orderIds);

        List<OrderResponseDto> ordersDto = orders.stream()
                .map(OrderMapper::toDto)
                .toList();
        Set<Long> foundIds = orders.stream()
                .map(Order::getOrderId).collect(Collectors.toSet());
        List<Long> missingIds = orderIds.stream()
                .filter(id -> !foundIds.contains(id)).toList();
        BulkOrderResponseDto responseDto = new BulkOrderResponseDto();
        responseDto.setOrders(ordersDto);
        responseDto.setMissingOrderIds(missingIds);
        return responseDto;
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public OrderResponseDto updateOrder(Long id, OrderRequestDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        List<LineItem> list = dto.getItems().stream().map(ItemMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));
        order.getItems().clear();
        list.forEach(item -> item.setOrder(order));
        order.getItems().addAll(list);
        Order updatedOrder = orderRepository.save(order);
        return OrderMapper.toDto(updatedOrder);
    }

    @Override
    public OrderResponseDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return OrderMapper.toDto(order);
    }
}
