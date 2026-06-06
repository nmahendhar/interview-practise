package com.hcl.lead.controller;

import com.hcl.lead.dto.ProductRequest;
import com.hcl.lead.dto.ProductResponse;
import com.hcl.lead.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> registerProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse savedProduct = productService.createProduct(request);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchInventorySummary() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}