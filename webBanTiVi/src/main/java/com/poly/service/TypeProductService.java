package com.poly.service;

import com.poly.entity.TypeProduct;

import java.util.List;

public interface TypeProductService {
    List<TypeProduct> findAll();

    TypeProduct findById(Integer id);

    List<TypeProduct> findByGroupProduct(Integer idGroup);

}
