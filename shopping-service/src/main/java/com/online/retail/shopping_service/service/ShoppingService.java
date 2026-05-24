package com.online.retail.shopping_service.service;

import com.online.retail.shopping_service.dto.cart.CartRequestDto;
import com.online.retail.shopping_service.dto.cart.CartResponseDto;
import com.online.retail.shopping_service.dto.customer.CustomerDetailsDto;
import com.online.retail.shopping_service.dto.customer.CustomerOrderDetailsDto;
import com.online.retail.shopping_service.dto.order.OrderResponseDto;
import com.online.retail.shopping_service.dto.shopping.customer.CreateCustomerRequestDto;
import com.online.retail.shopping_service.dto.shopping.customer.CreateCustomerResponseDto;
import com.online.retail.shopping_service.dto.shopping.product.CreateProductRequestDto;
import com.online.retail.shopping_service.dto.shopping.product.CreateProductResponseDto;
import jakarta.validation.Valid;

public interface ShoppingService {
    CreateProductResponseDto createProduct(CreateProductRequestDto dto);

    CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto dto);

    CartResponseDto addToCart(Long customerId, CartRequestDto dto);

    OrderResponseDto createOrder(Long customerId);

    CustomerOrderDetailsDto getAllOrdersForCustomer(Long customerId);
}
