package com.afford.topproducts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afford.topproducts.entity.Product;
import com.afford.topproducts.repository.productrepo;

@RestController
@RequestMapping("/categories")
public class productcontroller {
    @Autowired
    private productrepo productRepository;

    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<Product>> getTopProducts(
            @PathVariable String categoryName,
            @RequestParam(value = "n", defaultValue = "10") int n,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "sort", defaultValue = "price") String sortField
    ) {
        List<Product> products = productRepository.getTopProducts(categoryName, n, page, sortField);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{categoryName}/products/{productId}")
    public ResponseEntity<Product> getProductById(
            @PathVariable String categoryName,
            @PathVariable Long productId
    ) {
        Product product = productRepository.getProductById(productId);
        return ResponseEntity.ok(product);
    }
}
