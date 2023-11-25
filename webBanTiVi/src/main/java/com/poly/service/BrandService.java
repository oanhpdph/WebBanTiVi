package com.poly.service;

import com.poly.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand findById(Integer id);
}
