package com.poly.service;

import com.poly.entity.Brand;

import java.util.List;

public interface BrandService {

    Brand add(Brand brand);
    void delete(Integer id);

    List<Brand> getAll();
}
