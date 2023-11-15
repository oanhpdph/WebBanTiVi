package com.poly.service;

import com.poly.dto.ProductDetailDto;
import com.poly.entity.Product;

import java.util.List;


public interface ProductService {

    Product save(Product product);

    void delete(Integer id);

    List<Product> findAll(ProductDetailDto productDetailDto);

    Product findById(Integer id);

    Product getOne(Integer id);

    List<Product> findSameProduct(String same);

}
