package com.shopwave.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.dto.ProductDTO;
import com.shopwave.exception.ProductNotFoundException;
import com.shopwave.mapper.ProductMapper;
import com.shopwave.model.Product;
import com.shopwave.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO createProduct(CreateProductRequest req) {
        Product p = Product.builder()
                .name(req.getName())
                .price(req.getPrice())
                .stock(req.getStock())
                .build();

        return ProductMapper.toDTO(productRepository.save(p));
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ProductDTO updateStock(Long id, int delta) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        int newStock = p.getStock() + delta;
        if (newStock < 0) throw new IllegalArgumentException("Stock cannot be negative");

        p.setStock(newStock);
        return ProductMapper.toDTO(productRepository.save(p));
    }
}