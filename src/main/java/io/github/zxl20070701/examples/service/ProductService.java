package io.github.zxl20070701.examples.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.zxl20070701.examples.dto.Product;
import io.github.zxl20070701.examples.dto.ProductRequest;
import io.github.zxl20070701.examples.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(ProductRequest request) {
        return productRepository.create(request.name(), request.price());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public boolean update(long id, ProductRequest request) {
        return productRepository.update(id, request.name(), request.price());
    }

    public boolean delete(long id) {
        return productRepository.delete(id);
    }
}
