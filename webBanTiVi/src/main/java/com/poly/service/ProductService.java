package com.poly.service;

import com.poly.entity.Coupon;
import com.poly.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    void delete(Integer id);

    List<Product> findAll();

    Product findById(Integer id);

//    List<ProductDto>getProducts();

    List<Coupon> getByIdCoupon(Integer id);
}
