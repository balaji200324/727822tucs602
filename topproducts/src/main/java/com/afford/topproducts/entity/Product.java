package com.afford.topproducts.entity;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private String category;
    private String company;
    private double rating ;
    private int discount;
    private boolean available;

}
