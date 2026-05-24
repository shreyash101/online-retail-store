package com.online.retail.shopping_service.controller;

import com.online.retail.shopping_service.dto.cart.CartRequestDto;
import com.online.retail.shopping_service.dto.cart.CartResponseDto;
import com.online.retail.shopping_service.dto.order.OrderResponseDto;
import com.online.retail.shopping_service.dto.shopping.customer.CreateCustomerRequestDto;
import com.online.retail.shopping_service.dto.shopping.customer.CreateCustomerResponseDto;
import com.online.retail.shopping_service.dto.shopping.product.CreateProductRequestDto;
import com.online.retail.shopping_service.dto.shopping.product.CreateProductResponseDto;
import com.online.retail.shopping_service.service.ShoppingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("shoppingController")
@RequestMapping(path = "/shoppingservice")
@RequiredArgsConstructor
public class ShoppingController {
    private final ShoppingService shoppingService;

    @PostMapping(path = "/products")
    public ResponseEntity<CreateProductResponseDto> createProduct(
            @Valid @RequestBody CreateProductRequestDto dto
            ) {
        CreateProductResponseDto product = shoppingService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(product);
    }

    @PostMapping(path = "/customers")
    public ResponseEntity<CreateCustomerResponseDto> createCustomer(
            @Valid @RequestBody CreateCustomerRequestDto dto
    ) {
        CreateCustomerResponseDto customer = shoppingService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping(path = "/customers/{customerId}/cart")
    public ResponseEntity<CartResponseDto> addToCart(@PathVariable("customerId") Long customerId,
            @Valid @RequestBody CartRequestDto dto
    ) {
        CartResponseDto cartResponseDto = shoppingService.addToCart(customerId, dto);
        return ResponseEntity.ok().body(cartResponseDto);
    }

    @PostMapping(path = "/customers/{customerId}/order")
    public ResponseEntity<OrderResponseDto> createOrder(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok().body(shoppingService.createOrder(customerId));
    }

    @GetMapping(path = "/customers/{customerId}/orders")
    public ResponseEntity<?> getAllOrdersForCustomer(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok().body(shoppingService.getAllOrdersForCustomer(customerId));
    }
}