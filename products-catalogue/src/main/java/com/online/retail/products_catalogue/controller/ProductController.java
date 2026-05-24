package com.online.retail.products_catalogue.controller;

import com.online.retail.products_catalogue.dto.ProductDto;
import com.online.retail.products_catalogue.dto.ProductRequestDto;
import com.online.retail.products_catalogue.dto.ProductResponseDto;
import com.online.retail.products_catalogue.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController(value = "productController")
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto dto) {
        ProductResponseDto productDto = productService.createProduct(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDto.getProductId())
                .toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable(value = "id") Long id, @Valid @RequestBody ProductRequestDto dto) {
        ProductResponseDto productDto = productService.updateProduct(id, dto);
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDto> getProductDetails(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(productService.getProductDetails(id));
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @PageableDefault(page = 0, size = 10)Pageable pageable
            ) {
        return ResponseEntity.ok().body(productService.getAllProducts(pageable));
    }
}