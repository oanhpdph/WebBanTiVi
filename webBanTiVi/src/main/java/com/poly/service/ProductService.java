package com.poly.service;

import com.poly.entity.Product;
import com.poly.entity.ProductDetailView;
import com.poly.entity.Staff;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;


public interface ProductService {

    Product save(Product product);

    void delete(Integer id);

    List<Product> findAll();

    Product findById(Integer id);

    ProductDetailView getProductDetailById(Integer id);
    Product getOne(Integer id);

}
