package com.poly.service;



import com.poly.entity.ProductFeature;
import com.poly.entity.idClass.ProductFeatureId;

import java.util.List;
import java.util.Optional;

public interface ProductFeatureService {
    ProductFeature save(ProductFeature productFeature);

    void delete(ProductFeatureId id);

    List<ProductFeature> findAll();

    Optional<ProductFeature> findById(ProductFeatureId id);
}
