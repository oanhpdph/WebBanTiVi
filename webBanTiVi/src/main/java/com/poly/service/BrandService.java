package com.poly.service;

import com.poly.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    Brand add(Brand brand);
    void delete(Integer id);

    List<Brand> getAll();

    Optional<Brand> findById(Integer id);
}
