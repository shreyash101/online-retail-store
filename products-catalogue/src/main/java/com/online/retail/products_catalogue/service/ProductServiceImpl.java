package com.online.retail.products_catalogue.service;

import com.online.retail.products_catalogue.dto.ProductDto;
import com.online.retail.products_catalogue.dto.ProductRequestDto;
import com.online.retail.products_catalogue.dto.ProductResponseDto;
import com.online.retail.products_catalogue.entity.Product;
import com.online.retail.products_catalogue.exception.ResourceNotFoundException;
import com.online.retail.products_catalogue.mapper.ProductMapper;
import com.online.retail.products_catalogue.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        Product product = ProductMapper.toEntity(dto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResponseDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setProductDescription(dto.getProductDescription());
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        return ProductMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ProductMapper.toResponseDto(product);
    }

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper::toDto);
    }
}
