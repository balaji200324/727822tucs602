package com.afford.topproducts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.afford.topproducts.entity.Product;
import com.afford.topproducts.repository.productrepo;

public class productimpl implements productrepo {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Product> getTopProducts(String category, int n, int page, String sortField) {

        List<Product> products = new ArrayList<>();
        for (String company : Arrays.asList("AMZ", "BestBuy", "Walmart", "Target", "Newegg")) {
            String url = "https://" + company + ".com/api/products?category=" + category + "&n=" + n + "&page=" + page + "&sort=" + sortField;
            ResponseEntity<ProductList> response = restTemplate.getForEntity(url, ProductList.class);
            products.addAll(response.getBody().getProducts());
        }

        products.sort((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
        return products.subList((page - 1) * n, page * n);
    }

    @Override
    public Product getProductById(Long productId) {
        String url = "https://AMZ.com/api/products/" + productId;
        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
        return response.getBody();
    }
}

class ProductList {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}