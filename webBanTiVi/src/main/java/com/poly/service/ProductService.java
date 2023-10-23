package com.poly.service;

import com.poly.entity.Product;
import com.poly.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    void delete(Integer id);

    List<Product> findAll();

    Product findById(Integer id);
}
