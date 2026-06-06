package com.hcl.lead.service;

import com.hcl.lead.dto.ProductRequest;
import com.hcl.lead.dto.ProductResponse;
import com.hcl.lead.mapper.ProductMapper;
import com.hcl.lead.model.Product;
import com.hcl.lead.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // Spring constructor dependency injection
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.findByProductCode(request.productCode()).isPresent()) {
            throw new IllegalArgumentException("Product tracking code already registered inside database metrics");
        }

        Product product = new Product();
        product.setProductCode(request.productCode());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponseList(products);
    }
}