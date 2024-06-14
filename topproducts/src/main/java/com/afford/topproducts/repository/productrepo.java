package com.afford.topproducts.repository;

import java.util.List;

import com.afford.topproducts.entity.Product;

public interface productrepo {
    List<Product> getTopProducts(String category,int n, int page, String sortField);
    Product getProductById(Long productId);
}
