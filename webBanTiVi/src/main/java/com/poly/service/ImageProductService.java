package com.poly.service;

import com.poly.entity.ImageProduct;

import java.util.List;
import java.util.UUID;

public interface ImageProductService {
    void add(ImageProduct imageProduct);
    void delete(Integer id);
    void update(Integer id, ImageProduct imageProduct);
    List<ImageProduct> getAll();
    List<ImageProduct> getByProductDetailId(Integer id);
    ImageProduct getOne(Integer id);
}
