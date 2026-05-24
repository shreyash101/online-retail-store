package com.online.retail.shopping_service.service;

import com.online.retail.shopping_service.client.*;
import com.online.retail.shopping_service.dto.cart.CartRequestDto;
import com.online.retail.shopping_service.dto.cart.CartResponseDto;
import com.online.retail.shopping_service.dto.cart.LineItemResponseDto;
import com.online.retail.shopping_service.dto.customer.CustomerDetailsDto;
import com.online.retail.shopping_service.dto.customer.CustomerOrderDetailsDto;
import com.online.retail.shopping_service.dto.customer.CustomerRequestDto;
import com.online.retail.shopping_service.dto.customer.CustomerResponseDto;
import com.online.retail.shopping_service.dto.enums.OrderStatus;
import com.online.retail.shopping_service.dto.inventory.InventoryRequestDto;
import com.online.retail.shopping_service.dto.inventory.InventoryResponseDto;
import com.online.retail.shopping_service.dto.order.BulkOrderResponseDto;
import com.online.retail.shopping_service.dto.order.OrderRequestDto;
import com.online.retail.shopping_service.dto.order.OrderResponseDto;
import com.online.retail.shopping_service.dto.product.ProductRequestDto;
import com.online.retail.shopping_service.dto.product.ProductResponseDto;
import com.online.retail.shopping_service.dto.shopping.customer.CreateCustomerRequestDto;
import com.online.retail.shopping_service.dto.shopping.customer.CreateCustomerResponseDto;
import com.online.retail.shopping_service.dto.shopping.product.CreateProductRequestDto;
import com.online.retail.shopping_service.dto.shopping.product.CreateProductResponseDto;
import com.online.retail.shopping_service.entity.CustomerCart;
import com.online.retail.shopping_service.entity.CustomerCartId;
import com.online.retail.shopping_service.entity.CustomerOrder;
import com.online.retail.shopping_service.entity.CustomerOrderId;
import com.online.retail.shopping_service.exception.ResourceNotFoundException;
import com.online.retail.shopping_service.repository.CustomerCartRepository;
import com.online.retail.shopping_service.repository.CustomerOrderRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("shoppingService")
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {

    private final ProductClient productClient;
    private final InventoryClient inventoryClient;
    private final CustomerClient customerClient;
    private final CartClient cartClient;
    private final CustomerCartRepository cartRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final OrderClient orderClient;

    @Override
    public CreateProductResponseDto createProduct(CreateProductRequestDto dto) {
        ProductRequestDto productRequest = new ProductRequestDto();
        productRequest.setProductName(dto.getProductName());
        productRequest.setProductDescription(dto.getProductDescription());
        productRequest.setProductPrice(dto.getProductPrice());

        ProductResponseDto productResponse = productClient.createProduct(productRequest);

        InventoryRequestDto inventoryRequest = new InventoryRequestDto();
        inventoryRequest.setQuantity(dto.getQuantity());
        inventoryRequest.setProductId(productResponse.getProductId());

        InventoryResponseDto inventoryResponse = inventoryClient.createInventory(inventoryRequest);

        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        responseDto.setProductId(productResponse.getProductId());
        responseDto.setProductName(productResponse.getProductName());
        responseDto.setProductDescription(productResponse.getProductDescription());
        responseDto.setProductPrice(productResponse.getProductPrice());
        responseDto.setQuantity(inventoryResponse.getQuantity());
        return responseDto;
    }

    @Override
    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto dto) {
        CustomerRequestDto customerRequest = new CustomerRequestDto();
        customerRequest.setCustomerName(dto.getCustomerName());
        customerRequest.setCustomerEmail(dto.getCustomerEmail());
        customerRequest.setCustomerBillingAddress(dto.getCustomerBillingAddress());
        customerRequest.setCustomerShippingAddress(dto.getCustomerShippingAddress());

        CustomerResponseDto customer = customerClient.createCustomer(customerRequest);

        CartRequestDto cartRequest = new CartRequestDto(List.of());

        CartResponseDto cart = cartClient.createCart(cartRequest);

        CustomerCart customerCart = new CustomerCart();
        CustomerCartId customerCartId = new CustomerCartId();
        customerCartId.setCustomerId(customer.getCustomerId());
        customerCartId.setCartId(cart.getCartId());
        customerCart.setId(customerCartId);
        cartRepository.save(customerCart);

        CreateCustomerResponseDto responseDto = new CreateCustomerResponseDto();
        responseDto.setCustomerId(customer.getCustomerId());
        responseDto.setCustomerEmail(customer.getCustomerEmail());
        responseDto.setCustomerName(customer.getCustomerName());
        responseDto.setCartId(cart.getCartId());
        return responseDto;
    }

    @Override
    public CartResponseDto addToCart(Long customerId, CartRequestDto dto) {
        CustomerCart customerCart = cartRepository.findByIdCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return cartClient.addToCart(customerCart.getId().getCartId(), dto);
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(Long customerId) {
        CustomerCart customerCart = cartRepository.findByIdCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        CartResponseDto cart = cartClient.getCart(customerCart.getId().getCartId());
        OrderResponseDto order = orderClient.createOrder(new OrderRequestDto(cart.getLineItems()));
        order.setStatus(OrderStatus.PLACED);
        BigDecimal total = order.getLineItems().stream().map(
                item -> item.getPrice().multiply(
                        BigDecimal.valueOf(item.getQuantity())
                ))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);
        order.getLineItems().forEach(System.out::println);
        order.getLineItems().forEach(item -> {
            InventoryRequestDto invReq = new InventoryRequestDto();
            invReq.setQuantity(item.getQuantity());
            inventoryClient.updateInventory(item.getProductId(), invReq);
        });
        cartClient.addToCart(cart.getCartId(), new CartRequestDto(List.of()));
        CustomerOrder customerOrder = new CustomerOrder();
        CustomerOrderId orderId = new CustomerOrderId();
        orderId.setCustomerId(customerCart.getId().getCustomerId());
        orderId.setOrderId(order.getOrderId());
        customerOrder.setId(orderId);
        customerOrderRepository.save(customerOrder);
        return order;
    }

    @Override
    public CustomerOrderDetailsDto getAllOrdersForCustomer(Long customerId) {
        List<Long> orderIds = customerOrderRepository.findByIdCustomerId(customerId)
                .stream().map(order -> order.getId().getOrderId()).toList();
        CustomerDetailsDto customerDetails = null;
        try{
            customerDetails = customerClient.getCustomerDetails(customerId);
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        CustomerOrderDetailsDto dto = new CustomerOrderDetailsDto();
        BulkOrderResponseDto orders = new BulkOrderResponseDto();

        BulkOrderResponseDto orderDetails = orderClient.getOrderDetails(orderIds);
        orderDetails.getOrders().forEach(order -> {
            // 1. set total
            BigDecimal totalAmt = order.getLineItems().stream()
                    .map(item -> item.getPrice().multiply(
                            BigDecimal.valueOf(item.getQuantity())
                    ))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalAmount(totalAmt);
            order.setStatus(OrderStatus.PLACED);
        });

        if(orderIds.isEmpty()) {
            orders.setOrders(List.of());
            orders.setMissingOrderIds(orderIds);
        } else {
            orders.setOrders(orderDetails.getOrders());
            orders.setMissingOrderIds(orderDetails.getMissingOrderIds());
        }
        dto.setCustomer(customerDetails);
        dto.setOrders(orders);
        return dto;
    }
}
